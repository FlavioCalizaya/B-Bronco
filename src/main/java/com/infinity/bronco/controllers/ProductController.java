package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/products")
@CrossOrigin()
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getUserById(productId));
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllUsers() {
        return ResponseEntity.ok(productService.getProducts());
    }
}
