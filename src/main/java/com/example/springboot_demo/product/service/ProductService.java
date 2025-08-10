// ProductService.java
package com.example.springboot_demo.product.service;

import com.example.springboot_demo.product.model.Product;
import com.example.springboot_demo.product.repository.ProductRepository;
import com.example.springboot_demo.shared.ProductCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private final ApplicationEventPublisher eventPublisher;

    public ProductService(ProductRepository productRepository, ApplicationEventPublisher eventPublisher) {
        this.productRepository = productRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        Product createdProduct = productRepository.save(product);
        // 发布事件
        eventPublisher.publishEvent(new ProductCreatedEvent(createdProduct.getId(), createdProduct.getName()));
        return createdProduct;
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productRepository.delete(product);
    }
}
