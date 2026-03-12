package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.entity.Product;
import com.ecommerce.inventory_service.entity.Stock;
import com.ecommerce.inventory_service.repository.ProductRepository;
import com.ecommerce.inventory_service.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    // Add product
    public Product addProduct(Product product){

        Product savedProduct = productRepository.save(product);

        Stock stock = new Stock();
        stock.setProduct(savedProduct);
        stock.setAvailableQuantity(0);

        stockRepository.save(stock);

        return savedProduct;
    }

    // Get all products
    public List<Product> getAllProducts(){
        return productRepository.findAll();

    }

    // Get product details
    public Product getProduct(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    // Delete product
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    // Get stock
    public Stock getStock(Long productId){
        return stockRepository.findById(productId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));
    }

    // Update stock
    public Stock updateStock(Long productId, int quantity){

        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));

        stock.setAvailableQuantity(quantity);

        return stockRepository.save(stock);
    }

    // Increase stock (restock)
    public Stock increaseStock(Long productId, int quantity){

        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));

        stock.setAvailableQuantity(
                stock.getAvailableQuantity() + quantity
        );

        return stockRepository.save(stock);
    }

    // Reduce stock when order placed
    public String reduceStock(Long productId, int quantity){

        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));

        if(stock.getAvailableQuantity() < quantity){
            return "Not enough stock";
        }

        stock.setAvailableQuantity(
                stock.getAvailableQuantity() - quantity
        );

        stockRepository.save(stock);

        return "Stock updated";
    }

    // Check stock availability
    public boolean checkStock(Long productId, int quantity){

        Stock stock = stockRepository.findById(productId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));

        return stock.getAvailableQuantity() >= quantity;
    }
}