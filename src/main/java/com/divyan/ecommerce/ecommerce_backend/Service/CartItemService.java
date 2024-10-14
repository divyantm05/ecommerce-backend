package com.divyan.ecommerce.ecommerce_backend.Service;


import com.divyan.ecommerce.ecommerce_backend.Entity.CartItem;
import com.divyan.ecommerce.ecommerce_backend.Entity.Product;
import com.divyan.ecommerce.ecommerce_backend.Entity.User;
import com.divyan.ecommerce.ecommerce_backend.Respository.CartItemRepository;
import com.divyan.ecommerce.ecommerce_backend.Respository.ProductRepository;
import com.divyan.ecommerce.ecommerce_backend.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public List<CartItem> getCartItemsByUserId(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }
    public CartItem addProductToCartItem(Long userId, Long productId, Integer quantity) {
        Product product=productRepository.findById(productId).orElseThrow();
        User user=userRepository.findById(userId).orElseThrow();
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
        return cartItem;
    }
    public CartItem updateCartItem(Long userId, Long productId, Integer quantity) {
        CartItem cartItem=cartItemRepository.findById(userId).orElseThrow();
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public void removeProductFromCartItem(Long userId, Long cartItemId, Integer quantity) {
        cartItemRepository.deleteById(cartItemId);
    }

}
