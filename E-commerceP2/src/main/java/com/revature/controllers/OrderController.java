package com.revature.controllers;

<<<<<<< HEAD
import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.NotAuthenticatedException;
import com.revature.exceptions.NotAuthorizedException;
import com.revature.exceptions.OrderNotFoundException;
import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.models.utilitymodels.ClientMessage;
import com.revature.services.OrderDetailsService;
import com.revature.services.OrderService;
import com.revature.services.OrderStatusService;
import com.revature.services.PaymentService;
=======
import com.revature.exceptions.*;
import com.revature.models.*;
import com.revature.models.utilitymodels.ClientMessage;
import com.revature.services.*;
>>>>>>> Raphael
import com.revature.utils.ClientMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

<<<<<<< HEAD
import java.time.LocalDate;
=======
>>>>>>> Raphael
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://127.0.0.1:5500")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderStatusService orderStatusService;
    @Autowired
    private OrderDetailsService orderDetailsService;
<<<<<<< HEAD
    
    private PaymentService paymentService;


    @PostMapping("/add")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
=======

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.POST, allowedHeaders = "*")
>>>>>>> Raphael
    public @ResponseBody Order addOrder(@RequestBody Order order, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
<<<<<<< HEAD
            order.setDateCreated(LocalDateTime.now());
            order.setOrderOwner(loggedInUser);
            order.setOrderStatus(new OrderStatus(1, "pending"));
            System.out.println(order);
            return orderService.addOrder(order);
=======
            Cart currentUserCart = cartService.getCart(loggedInUser);

            if (currentUserCart != null) {
                List<CartItem> cartItemList = cartItemService.getCartItems(currentUserCart);

                if (cartItemList != null) {
                    double totalOrderPrice = 0;
                    double shipping = 0;
                    double subTotal = 0;
                    for (CartItem item : cartItemList) {
                        subTotal += (item.getProduct().getProductPrice() * item.getProductQuantity());
                        shipping += 2;
                    }
                    double taxes = subTotal * 0.06;

                    totalOrderPrice = subTotal + shipping + taxes;

                    order.setDateCreated(LocalDateTime.now());
                    order.setOrderOwner(loggedInUser);
                    order.setOrderStatus(new OrderStatus(1, "pending"));
                    order.setOrderTotalPrice(totalOrderPrice);

                    System.out.println(order);
                    Order addedOrder = orderService.addOrder(order);

                    if (addedOrder != null) {
                        cartItemList.forEach(item -> {
                            OrderDetails newOrderDetails = new OrderDetails();
                            newOrderDetails.setOrder(addedOrder);
                            newOrderDetails.setProduct(item.getProduct());
                            newOrderDetails.setDateCreated(LocalDateTime.now());
                            newOrderDetails.setOrderItemNumber(item.getCartItemNumber());
                            newOrderDetails.setProductQuantity(item.getProductQuantity());
                            newOrderDetails.setUnitPrice(item.getProduct().getProductPrice());

                            if (orderDetailsService.addOrderDetails(newOrderDetails) == null) {
                                throw new PaymentErrorException("Error while trying to add order details");
                            }
                            cartItemService.delete(item);
                        });

                        cartService.deleteCart(currentUserCart);

                        return addedOrder;
                    } else {
                        throw new PaymentErrorException("Error while trying to add order");
                    }
                } else {
                    throw new PaymentErrorException("Error while trying to retrieve cart information");
                }
            } else {
                throw new CartErrorException("No cart found for this user");
            }


>>>>>>> Raphael
        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
        }
    }

<<<<<<< HEAD

=======
>>>>>>> Raphael
    @GetMapping("/get-user-orders")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public @ResponseBody List<Order> getOrdersByOwner(@RequestParam(required = false) String status, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            if (status != null) {
                OrderStatus orderStatus = orderStatusService.getOrderStatusByOrderStatus(status);

                if (orderStatus != null) {
                    List<Order> orders = orderService.getOrdersByOrderOwner(loggedInUser);
                    if (!orders.isEmpty()) {
                        List<Order> filteredOrders = new ArrayList<>();
                        orders.forEach(order -> {
                            if (order.getOrderStatus().getOrderStatus().equalsIgnoreCase(orderStatus.getOrderStatus())) {
                                filteredOrders.add(order);
                            }
                        });

                        return filteredOrders;
                    } else {
                        throw new OrderNotFoundException("No orders found for this user.");
                    }
                } else {
                    throw new InvalidParameterException("Invalid parameters provided. Please try again.");
                }
            } else {
                List<Order> orders = orderService.getOrdersByOrderOwner(loggedInUser);
                if (!orders.isEmpty()) {
                    return orders;
                } else {
                    throw new OrderNotFoundException("No orders found for this user.");
                }
            }

        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
        }
    }


    @GetMapping("/get-order")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public @ResponseBody Order getOrderById(@RequestParam int id) {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return order;
        } else {
            throw new OrderNotFoundException("No Orders were found with the provided info. Please try again.");
        }
    }

    @GetMapping("/admin/get-orders-by-status")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public @ResponseBody List<Order> getOrdersByStatus(@RequestParam String status, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            if (loggedInUser.getUserRole().getRole().equalsIgnoreCase("admin")) {
                OrderStatus orderStatus = orderStatusService.getOrderStatusByOrderStatus(status);

                if (orderStatus != null) {
                    return orderService.getOrdersByStatus(orderStatus);
                } else {
                    throw new InvalidParameterException("Invalid parameters provided. Please try again.");
                }

            } else {
                throw new NotAuthorizedException("Insufficient privileges to access this resource.");
            }

        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please Log in with your credentials.");
        }

    }

    @PutMapping("/admin/update")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.PUT, allowedHeaders = "*")
    public @ResponseBody ClientMessage updateOrder(@RequestBody Order order, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            if (loggedInUser.getUserRole().getRole().equals("admin")) {
                if (order.getDateShipped() != null) {
                    order.setOrderStatus(orderStatusService.getOrderStatusByOrderStatus("shipped"));
                }
                if (order.getDateDelivered() != null) {
                    order.setOrderStatus(orderStatusService.getOrderStatusByOrderStatus("delivered"));
                }
                order.setDateModified(LocalDateTime.now());
                if (orderService.updateOrder(order) > 0) {
                    return ClientMessageUtil.ORDER_UPDATE_SUCCESSFUL;
                } else {
                    return ClientMessageUtil.ORDER_UPDATE_FAILED;
                }
            } else {
                throw new NotAuthorizedException("Insufficient privileges to access this resource.");
            }

        } else {
            return ClientMessageUtil.NOT_AUTHENTICATED;
        }
    }

    @DeleteMapping("/admin/delete")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.DELETE, allowedHeaders = "*")
    public @ResponseBody ClientMessage deleteOrder(@RequestBody Order order, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            orderService.deleteOrder(order);
            return ClientMessageUtil.USER_DELETION_SUCCESSFUL;
        } else {
            return ClientMessageUtil.USER_DELETION_FAILED;
        }
    }
<<<<<<< HEAD
    
    @PostMapping("/payment/add")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public @ResponseBody ClientMessage addPayment(@RequestBody Payment payment, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            payment.setPaymentDateCreated(LocalDate.now());
            payment.setUser(loggedInUser);
            payment.setPaymentStatus("pending");
            System.out.println(payment);
            return paymentService.addPayment(payment) ? ClientMessageUtil.PAYMENT_SUCCESSFUL : ClientMessageUtil.PAYMENT_FAILED;
        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
        }
    }
    
    
=======

>>>>>>> Raphael
}
