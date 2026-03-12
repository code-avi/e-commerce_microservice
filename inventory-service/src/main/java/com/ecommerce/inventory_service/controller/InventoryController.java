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

    // Add new product
    @PostMapping("/product")
    public Product addProduct(@RequestBody Product product){
        return inventoryService.addProduct(product);
    }

    // Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return inventoryService.getAllProducts();
    }

    // Get product details
    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable Long productId){
        return inventoryService.getProduct(productId);
    }

    // Delete product
    @DeleteMapping("/product/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        inventoryService.deleteProduct(productId);
        return "Product deleted successfully";
    }

    // Get stock of product
    @GetMapping("/stock/{productId}")
    public Stock getStock(@PathVariable Long productId){
        return inventoryService.getStock(productId);
    }

    // Update stock
    @PutMapping("/stock/{productId}")
    public Stock updateStock(@PathVariable Long productId,
                             @RequestParam int quantity){
        return inventoryService.updateStock(productId, quantity);
    }

    // Increase stock (restock)
    @PutMapping("/restock/{productId}")
    public Stock increaseStock(@PathVariable Long productId,
                               @RequestParam int quantity){
        return inventoryService.increaseStock(productId, quantity);
    }

    // Reduce stock when order placed
    @PutMapping("/reduce/{productId}")
    public String reduceStock(@PathVariable Long productId,
                              @RequestParam int quantity){
        return inventoryService.reduceStock(productId, quantity);
    }

    // Check stock availability (used by order service)
    @GetMapping("/check/{productId}")
    public boolean checkStock(@PathVariable Long productId,
                              @RequestParam int quantity){
        return inventoryService.checkStock(productId, quantity);
    }
}