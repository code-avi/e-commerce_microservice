package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService=orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){

        return orderService.createOrder(order);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrders(@PathVariable Long userId){

        return orderService.getOrders(userId);
    }

    @PutMapping("/cancel/{orderId}")
    public Order cancelOrder(@PathVariable Long orderId){

        return orderService.cancelOrder(orderId);
    }
}