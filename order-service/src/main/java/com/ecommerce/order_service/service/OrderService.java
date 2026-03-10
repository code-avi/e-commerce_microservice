package com.ecommerce.order_service.service;

import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.model.OrderStatus;
import com.ecommerce.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    // Create Order
    public Order createOrder(Order order) {

        order.getItems().forEach(item -> item.setOrder(order));

        return orderRepository.save(order);
    }


    // Get Order By ID
    public Order getOrderById(Long orderId) {

        Optional<Order> order = orderRepository.findById(orderId);

        return order.orElse(null);
    }


    // Get All Orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


    // Get Orders By User
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }


    // Cancel Order
    public Order cancelOrder(Long orderId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(optionalOrder.isPresent()){

            Order order = optionalOrder.get();
            order.setStatus(OrderStatus.CANCELLED);

            return orderRepository.save(order);
        }

        return null;
    }
    //update Order Status
    public Order updateOrderStatus(Long orderId, OrderStatus status){

        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if(optionalOrder.isPresent()){

            Order order = optionalOrder.get();
            order.setStatus(status);

            return orderRepository.save(order);
        }

        throw new RuntimeException("Order not found");
    }
}