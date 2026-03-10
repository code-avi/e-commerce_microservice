package com.ecommerce.inventory_service.controller;

import com.ecommerce.inventory_service.entity.Product;
import com.ecommerce.inventory_service.entity.Stock;
import com.ecommerce.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Admin: Add product
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){
        return inventoryService.addProduct(product);
    }

    // Admin: Add stock
    @PostMapping("/stock")
    public Stock addStock(@RequestBody Stock stock){
        return inventoryService.addStock(stock);
    }

    // Admin: View all products
    @GetMapping("/products")
    public List<Product> getProducts(){
        return inventoryService.getAllProducts();
    }

    // Customer/Admin: Check stock
    @GetMapping("/stock/{productId}")
    public Stock getStock(@PathVariable Long productId){
        return inventoryService.getStock(productId);
    }

    // Admin: Update stock
    @PutMapping("/stock/{productId}")
    public Stock updateStock(@PathVariable Long productId,
                             @RequestParam int quantity){
        return inventoryService.updateStock(productId, quantity);
    }

    // Customer: Reduce stock after order
    @PutMapping("/reduce/{productId}")
    public String reduceStock(@PathVariable Long productId,
                              @RequestParam int quantity){
        return inventoryService.reduceStock(productId, quantity);
    }
}