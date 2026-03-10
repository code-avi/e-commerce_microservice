package com.ecommerce.inventory_service.service;

import com.ecommerce.inventory_service.entity.Product;
import com.ecommerce.inventory_service.entity.Stock;
import com.ecommerce.inventory_service.repository.ProductRepository;
import com.ecommerce.inventory_service.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    // Admin: Add product
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    // Admin: Add stock
    public Stock addStock(Stock stock){
        return stockRepository.save(stock);
    }

    // Admin: View all products
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    // Customer/Admin: Check stock
    public Stock getStock(Long productId){
        return stockRepository.findByProductId(productId);
    }

    // Admin: Update stock
    public Stock updateStock(Long productId, int quantity){
        Stock stock = stockRepository.findByProductId(productId);
        stock.setQuantity(quantity);
        return stockRepository.save(stock);
    }

    // Customer: Reduce stock when order placed
    public String reduceStock(Long productId, int quantity){

        Stock stock = stockRepository.findByProductId(productId);

        if(stock.getQuantity() >= quantity){
            stock.setQuantity(stock.getQuantity() - quantity);
            stockRepository.save(stock);
            return "Stock updated";
        }
        else{
            return "Not enough stock";
        }
    }
}