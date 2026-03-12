package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.model.Cart;
import com.ecommerce.order_service.model.CartItem;
import com.ecommerce.order_service.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService=cartService;
    }

    @PostMapping("/add/{userId}")
    public Cart addItem(@PathVariable Long userId,
                        @RequestBody CartItem item){

        return cartService.addItem(userId,item);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId){

        return cartService.getCart(userId);
    }

    @DeleteMapping("/remove/{itemId}")
    public String removeItem(@PathVariable Long itemId){

        cartService.removeItem(itemId);

        return "Item removed";
    }
}
