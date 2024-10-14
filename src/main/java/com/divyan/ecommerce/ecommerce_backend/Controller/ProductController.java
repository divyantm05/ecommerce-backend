package com.divyan.ecommerce.ecommerce_backend.Controller;

import com.divyan.ecommerce.ecommerce_backend.Entity.Product;
import com.divyan.ecommerce.ecommerce_backend.Respository.ProductRepository;
import com.divyan.ecommerce.ecommerce_backend.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private  ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(Product product) {
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }
    @PostMapping
    public ResponseEntity<Product> uploadProduct(@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("price") Double price,@RequestParam("image") MultipartFile image,@RequestParam("isAvailable")Boolean isAvaialble,@RequestParam("category") String category) {
        try{
            Product product=new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setAvailable(isAvaialble);
            product.setStock(10);
            product.setCategory(category);
            product.setImage(image.getBytes());
            productRepository.save(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<String> getProductImage(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        byte[] image = product.getImage();
        if(image==null){
            return ResponseEntity.status(404).body("Image not found");
        }
        String base64Image = Base64.getEncoder().encodeToString(image);
        return ResponseEntity.ok(base64Image);
    }
    @PutMapping("/update")
    public Product updateProduct(Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete")
    public void deleteProductById(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam("category") String category) {
        List<Product>products=products=productRepository.findByCategory(category);
        return ResponseEntity.ok(products);
    }
}
