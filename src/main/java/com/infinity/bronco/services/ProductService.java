package com.infinity.bronco.services;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Iterable<Product> getProducts() {
        return productRepository.findByEstado(1);
    }

    public Optional<Product> getProductById(Integer idProducto) {
        return productRepository.findById( idProducto );
    }

    public Product saveProduct(Product product) {
        if (productRepository.existsById(product.getIdProducto())) {
            product.setIdProducto(null);
        }
        return productRepository.save(product);
    }
    public Product removeProduct(Integer id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            existingProduct.setEstado(updatedProduct.getEstado());

            return productRepository.save(existingProduct);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
    }
}
