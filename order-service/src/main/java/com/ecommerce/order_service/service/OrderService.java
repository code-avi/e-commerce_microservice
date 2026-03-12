package com.ecommerce.order_service.service;

import com.ecommerce.order_service.exception.OrderNotFoundException;
import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.model.OrderStatus;
import com.ecommerce.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){

        this.orderRepository=orderRepository;
    }

    public Order createOrder(Order order){

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        order.getItems().forEach(i -> i.setOrder(order));

        return orderRepository.save(order);
    }

    public List<Order> getOrders(Long userId){

        return orderRepository.findByUserId(userId);
    }

    public Order cancelOrder(Long orderId){

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);

        return orderRepository.save(order);
    }
}