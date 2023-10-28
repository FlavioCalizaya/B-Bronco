package com.infinity.bronco.repositories;

import com.infinity.bronco.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Resto de tus importaciones

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByEstado(int estado);
    List<Product> findByNombreProductoContaining(String productName);
}
