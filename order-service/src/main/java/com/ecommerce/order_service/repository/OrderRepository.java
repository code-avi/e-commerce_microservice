package com.ecommerce.order_service.repository;

import com.ecommerce.order_service.model.Order;
import com.ecommerce.order_service.model.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get all orders for a specific user
    List<Order> findByUserId(String userId);

    // Get orders by status
    List<Order> findByStatus(OrderStatus status);

    // Get orders of a user with a specific status
    List<Order> findByUserIdAndStatus(String userId, OrderStatus status);

}