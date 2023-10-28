package com.infinity.bronco.controllers;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@RestController
@RequestMapping("api/v1/products")
@CrossOrigin()
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.saveProduct( product );
        return  ResponseEntity.ok( addedProduct );
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable("id") Integer idProducto) {
        Optional<Product> product = productService.getProductById(idProducto);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product updatedProduct) {
            Product updated = productService.updateProduct(id, updatedProduct);
            return ResponseEntity.ok(updated);
    }

    @PutMapping( path = "/remove/{id}")
    public ResponseEntity<Product> removeProduct( @PathVariable Integer id) {
        Product updated = productService.removeProduct(id);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/names/{productName}")
    public ResponseEntity<List<Product>> searchProductByName(@PathVariable String productName) {

        List<Product> products = productService.searchProductByName(productName);
        return ResponseEntity.ok(products);
    }


}
