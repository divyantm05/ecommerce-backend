package com.divyan.ecommerce.ecommerce_backend.Service;


import com.divyan.ecommerce.ecommerce_backend.Entity.Product;
import com.divyan.ecommerce.ecommerce_backend.Respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        Optional<Product> product= productRepository.findById(id);
        return product.orElse(null);
    }
    public List<Product> findProductsByCategory(String category) {
        List<Product> product= productRepository.findByCategory(category);
        return product;
    }
    public Product addProduct(Product product) {
       productRepository.save(product);
       return product;
    }

    public Product updateProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
