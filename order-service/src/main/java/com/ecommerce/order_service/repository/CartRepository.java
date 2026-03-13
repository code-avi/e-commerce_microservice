package com.ecommerce.order_service.repository;

import com.ecommerce.order_service.model.Cart;
import com.ecommerce.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findByUserId(String userId);
}