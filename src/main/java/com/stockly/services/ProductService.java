package com.stockly.services;

import com.stockly.entities.Product;
import com.stockly.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, Product obj) {
        Product product = getProductById(id);

        product.setName(
                obj.getName() != null && !obj.getName().isBlank()
                        ? obj.getName() : product.getName()
        );
        product.setCategory(
                obj.getCategory() != null && !obj.getCategory().isBlank()
                        ? obj.getCategory() : product.getCategory()
        );
        product.setInventory(
                obj.getInventory() != null ? obj.getInventory() : product.getInventory()
        );
        product.setPrice(
                obj.getPrice() != null ? obj.getPrice() : product.getPrice()
        );

        product.setUpdatedAt(LocalDateTime.now());
        repository.save(product);
        return product;
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }
}
