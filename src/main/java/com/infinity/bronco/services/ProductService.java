package com.infinity.bronco.services;

import com.infinity.bronco.models.Product;
import com.infinity.bronco.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ProductService ( ProductRepository productRepository  ) {
        this.productRepository = productRepository;
    }

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
        try {

            String base64Image = product.getImagen().split(",")[1];

            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            String imageName = "imagen_" + System.currentTimeMillis() + ".jpg";

            File file = new File(uploadDir + File.separator + imageName);
            file.createNewFile();

            FileUtils.writeByteArrayToFile(file, imageBytes);

            product.setImagen( imageName );

            return productRepository.save( product );

        } catch ( IOException e ) {
            e.printStackTrace();
        }
        return productRepository.save(product);
    }

    public Product removeProduct(Integer id) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();

            existingProduct.setEstado(0);

            return productRepository.save(existingProduct);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();

            productToUpdate.setNombreProducto(updatedProduct.getNombreProducto());
            productToUpdate.setCategoria(updatedProduct.getCategoria());
            productToUpdate.setCodigo(updatedProduct.getCodigo());
            productToUpdate.setImagen(updatedProduct.getImagen());
            productToUpdate.setPrecioVenta(updatedProduct.getPrecioVenta());
            productToUpdate.setAlto(updatedProduct.getAlto());
            productToUpdate.setAncho(updatedProduct.getAncho());
            productToUpdate.setEspesor(updatedProduct.getEspesor());
            productToUpdate.setMarca(updatedProduct.getMarca());
            productToUpdate.setTipo(updatedProduct.getTipo());
            productToUpdate.setDescripcion(updatedProduct.getDescripcion());

            return productRepository.save(productToUpdate);
        } else {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
    }
    public List<Product> searchProductByName(String productName) {

        // Utiliza el repository para buscar por nombre
        return productRepository.findByNombreProductoContaining(productName);
    }
}
