package com.revature.controllers;

import com.revature.exceptions.InvalidParameterException;
import com.revature.exceptions.NotAuthenticatedException;
import com.revature.models.Order;
import com.revature.models.OrderStatus;
import com.revature.models.User;
import com.revature.models.utilitymodels.ClientMessage;
import com.revature.services.OrderDetailsService;
import com.revature.services.OrderService;
import com.revature.services.OrderStatusService;
import com.revature.utils.ClientMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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


    @PostMapping("/add")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.POST, allowedHeaders = "*")
    public @ResponseBody Order addOrder(@RequestBody Order order, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            return orderService.addOrder(order);
        } else {
            throw new NotAuthenticatedException("Not Authenticated");
        }
    }


    @GetMapping("/get-user-orders")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public @ResponseBody List<Order> getOrdersByOwner(HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

        if (loggedInUser != null) {
            return orderService.getOrdersByOrderOwner(loggedInUser);
        } else {
            throw new NotAuthenticatedException("Not Authenticated.");
        }
    }


    @GetMapping("/get-order")
    public @ResponseBody Order getOrderById(@RequestParam int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("get-orders-by-status")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.GET, allowedHeaders = "*")
    public @ResponseBody List<Order> getOrdersByStatus(@RequestParam String status) {
        OrderStatus orderStatus = orderStatusService.getOrderStatusByOrderStatus(status);
        if (orderStatus != null) {
            return orderService.getOrdersByStatus(orderStatus);
        } else {
            throw new InvalidParameterException("invalid parameter");
        }

    }

    @PutMapping("/update")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.PUT, allowedHeaders = "*")
    public @ResponseBody ClientMessage updateOrder(@RequestBody Order order, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            if (orderService.updateOrder(order)) {
                return ClientMessageUtil.UPDATE_SUCCESSFUL;
            } else {
                return ClientMessageUtil.UPDATE_FAILED;
            }
        } else {
            return ClientMessageUtil.UPDATE_FAILED;
        }
    }

    @DeleteMapping("/delete")
    @CrossOrigin(allowCredentials = "true", methods = RequestMethod.DELETE, allowedHeaders = "*")
    public @ResponseBody ClientMessage deleteOrder(@RequestBody Order order, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");
        if (loggedInUser != null) {
            orderService.deleteOrder(order);
            return ClientMessageUtil.DELETION_SUCCESSFUL;
        } else {
            return ClientMessageUtil.DELETION_FAILED;
        }
    }

}
