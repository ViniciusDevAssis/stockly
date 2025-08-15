package com.stockly.services;

import com.stockly.entities.Product;
import com.stockly.entities.User;
import com.stockly.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final UserService service;

    public ProductService(ProductRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Product createProduct(Product product, Long id) {
        product.setUser(service.getUserById(id));
        product.setCreatedAt(LocalDateTime.now());
        return repository.save(product);
    }

    @Transactional
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

    @Transactional
    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }
}
