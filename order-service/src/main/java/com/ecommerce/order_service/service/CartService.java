package com.ecommerce.order_service.service;

import com.ecommerce.order_service.exception.CartNotFoundException;
import com.ecommerce.order_service.model.Cart;
import com.ecommerce.order_service.model.CartItem;
import com.ecommerce.order_service.repository.CartItemRepository;
import com.ecommerce.order_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository){

        this.cartRepository=cartRepository;
        this.cartItemRepository=cartItemRepository;
    }

    public Cart getCart(Long userId){

        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
    }

    public Cart addItem(Long userId, CartItem item){

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {

                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    newCart.setCreatedAt(LocalDateTime.now());

                    return cartRepository.save(newCart);
                });

        item.setCart(cart);

        cartItemRepository.save(item);

        return cart;
    }

    public void removeItem(Long itemId){

        cartItemRepository.deleteById(itemId);
    }

}
