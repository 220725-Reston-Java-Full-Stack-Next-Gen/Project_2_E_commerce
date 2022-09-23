package com.revature.controllers;

import com.revature.exceptions.NotAuthenticatedException;
import com.revature.models.*;
import com.revature.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.POST, allowedHeaders = "*")
    @PostMapping("/add")
    public @ResponseBody Payment addPayment (@RequestBody Payment payment, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            payment.setPaymentStatus("Success");
            payment.setPaymentDateCreated(LocalDate.now());
            payment.setPaymentDateLastUpdated(LocalDate.now());
            payment.setUser(loggedInUser);

            Payment newPayment = paymentService.addPayment(payment);
            if (newPayment != null) {
                List<CartItem> cartItemList = cartItemService.getCartItems(cartService.getCart(loggedInUser));

                if (cartItemList != null) {
                    double totalOrderPrice = 0;
                    double shipping = 0;
                    double subTotal = 0;
                    for (CartItem item: cartItemList) {
                        subTotal += (item.getProduct().getProductPrice() * item.getProductQuantity());
                        shipping += 2;
                    }
                    double taxes = subTotal * 0.06;

                    totalOrderPrice = subTotal + shipping + taxes;


                    Order newOrder = new Order();
                    newOrder.setOrderOwner(loggedInUser);
                    newOrder.setDateCreated(LocalDateTime.now());
                    newOrder.setOrderStatus(new OrderStatus(1, "pending"));
                    newOrder.setPayment(newPayment);
                    newOrder.setOrderQuantity(cartItemList.size());
                    newOrder.setOrderTotalPrice(totalOrderPrice);

                    Order addedOrder = orderService.addOrder(newOrder);

                    if (addedOrder != null) {
                        //List<OrderDetails> detailsListToAdd = new ArrayList<>();

                        cartItemList.forEach(item -> {
                            OrderDetails newOrderDetails = new OrderDetails();
                            newOrderDetails.setOrder(addedOrder);
                            newOrderDetails.setProduct(item.getProduct());
                            newOrderDetails.setDateCreated(LocalDateTime.now());
                            newOrderDetails.setOrderItemNumber(item.getCartItemNumber());
                            newOrderDetails.setProductQuantity(item.getProductQuantity());
                            newOrderDetails.setUnitPrice(item.getProduct().getProductPrice());

                            if (orderDetailsService.addOrderDetails(newOrderDetails) == null) {
                                throw new PaymentErrorException("Error While trying to add order details");
                            }
                        });

                    } else {
                        throw new PaymentErrorException("Error while trying to add order");
                    }

                } else {
                    throw new PaymentErrorException("Error while trying to retrieve cart information");
                }

                return payment;

            } else {
                throw new PaymentErrorException("Error while trying to process your payment. Please try again.");
            }
        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
        }
    }


    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.POST, allowedHeaders = "*")
    @PostMapping("/get-prev")
    public @ResponseBody List<Payment> getPreviousPayments (HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            List<Payment> payments = paymentService.getPreviousPayments(loggedInUser);
            if (payments != null) {
                return payments;
            } else {
                throw new PaymentErrorException("Error while trying to retrieve payments. Please try again.");
            }
        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
        }
    }
}
