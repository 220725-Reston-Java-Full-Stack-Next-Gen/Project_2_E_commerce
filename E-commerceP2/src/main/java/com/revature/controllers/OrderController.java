package com.revature.controllers;

import com.revature.exceptions.NotAuthenticatedException;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public @ResponseBody List<Order> getOrdersByOwner(HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            return orderService.getOrdersByOrderOwner(loggedInUser);
        } else {
            throw new NotAuthenticatedException("Not Authenticated.");
        }

    }
}
