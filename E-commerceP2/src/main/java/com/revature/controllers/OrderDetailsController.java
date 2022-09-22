package com.revature.controllers;

import com.revature.exceptions.NotAuthenticatedException;
import com.revature.exceptions.OrderNotFoundException;
import com.revature.models.Order;
import com.revature.models.OrderDetails;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.utilitymodels.ClientMessage;
import com.revature.services.OrderDetailsService;
import com.revature.utils.ClientMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/add")
    public @ResponseBody ClientMessage addOrderDetailsList(@RequestBody List<OrderDetails> orderDetailsList, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        System.out.println(orderDetailsList);
        if (loggedInUser != null) {
            for (OrderDetails details : orderDetailsList) {
                details.setDateCreated(LocalDateTime.now());
                details.setProduct(new Product());
                if (orderDetailsService.addOrderDetails(details) == null) {
                    return ClientMessageUtil.ORDER_UPDATE_FAILED;
                }
            }
            return ClientMessageUtil.ORDER_UPDATE_SUCCESSFUL;
        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
        }
    }

    @GetMapping("get-details")
    public @ResponseBody List<OrderDetails> getOrderDetailsList(@RequestBody Order order, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            List<OrderDetails> orderDetailsList = orderDetailsService.getOrderDetailsByOrder(order);
            if (orderDetailsList != null) {
                return orderDetailsList;
            } else {
                throw new OrderNotFoundException("No orders were found for the provided info. Please try again");
            }
        } else {
            throw new NotAuthenticatedException("Not Authenticated. Please log in with your credentials.");
        }
    }
}
