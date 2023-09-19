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
    public ResponseEntity<Optional<Product>> findById(@PathVariable Integer idProducto) {
        return ResponseEntity.ok(productService.getProductById( idProducto ));
    }

    @PutMapping( path = "/{id}")
    public ResponseEntity<Product> removeProduct( @PathVariable Integer id) {
        Product updated = productService.removeProduct(id);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }
}
