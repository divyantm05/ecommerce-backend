package com.divyan.ecommerce.ecommerce_backend.Service;

import com.divyan.ecommerce.ecommerce_backend.Entity.Product;
import com.divyan.ecommerce.ecommerce_backend.Entity.User;
import com.divyan.ecommerce.ecommerce_backend.Entity.Wishlist;
import com.divyan.ecommerce.ecommerce_backend.Respository.ProductRepository;
import com.divyan.ecommerce.ecommerce_backend.Respository.UserRepository;
import com.divyan.ecommerce.ecommerce_backend.Respository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<Wishlist> getWishListByUserId(Long userId) {
        return wishListRepository.findById(userId);
    }
    public List<Wishlist> getAllWishLists() {
        return wishListRepository.findAll();
    }

    public Wishlist addProductToWishList(Long userId, Long productId) {
        Wishlist wishlist=wishListRepository.findById(userId).orElse(new Wishlist());
        wishlist.setUser(userRepository.findById(userId).orElse(new User()));
        Set<Product>products=wishlist.getProducts();
        productRepository.findById(productId).ifPresent(products::add);
        wishlist.setProducts(products);
        return wishListRepository.save(wishlist);
    }

    public Wishlist removeProductFromWishList(Long userId, Long productId) {
        Wishlist wishlist=wishListRepository.findById(userId).orElseThrow();
        Set<Product>products=wishlist.getProducts();
        productRepository.findById(productId).ifPresent(products::remove);
        wishlist.setProducts(products);
        return wishListRepository.save(wishlist);
    }
}
