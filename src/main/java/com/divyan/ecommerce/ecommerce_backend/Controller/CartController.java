package com.divyan.ecommerce.ecommerce_backend.Controller;

import com.divyan.ecommerce.ecommerce_backend.Entity.CartItem;
import com.divyan.ecommerce.ecommerce_backend.Respository.CartItemRepository;
import com.divyan.ecommerce.ecommerce_backend.Service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController{
    private final CartItemService cartItemService;
    private final CartItemRepository cartItemRepository;

    public CartController(CartItemService cartItemService, CartItemRepository cartItemRepository) {
        this.cartItemService = cartItemService;
        this.cartItemRepository = cartItemRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) {
            CartItem cartItem=cartItemService.addProductToCartItem(userId, productId, quantity);
            return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> removeCartItem(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        CartItem cartItem= (CartItem) cartItemRepository.findByUserId(userId);
        cartItemService.removeProductFromCartItem(userId,cartItem.getId(), cartItem.getQuantity());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<CartItem> updateCartItem(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity){
        CartItem cartItem= cartItemService.updateCartItem(userId, productId, quantity);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
}
