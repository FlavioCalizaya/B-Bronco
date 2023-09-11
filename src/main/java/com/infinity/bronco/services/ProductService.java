package com.infinity.bronco.services;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getUserById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            product.setId(null);
        }
        return productRepository.save(product);
    }
}
