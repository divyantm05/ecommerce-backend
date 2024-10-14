package com.divyan.ecommerce.ecommerce_backend.Controller;

import com.divyan.ecommerce.ecommerce_backend.Entity.Order;
import com.divyan.ecommerce.ecommerce_backend.Entity.OrderItem;
import com.divyan.ecommerce.ecommerce_backend.Entity.User;
import com.divyan.ecommerce.ecommerce_backend.Respository.OrderRepository;
import com.divyan.ecommerce.ecommerce_backend.Respository.UserRepository;
import com.divyan.ecommerce.ecommerce_backend.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(UserRepository userRepository, OrderService orderService, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestParam Long userId, @RequestBody List<OrderItem> orderItems) {
        try{
            User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with user id "+userId));
            Order createdOrder=orderService.createOrder(user, orderItems);
            return ResponseEntity.ok(createdOrder);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        try{
            User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found with user id "+userId));
            List<Order>orders=orderRepository.findByUserId(userId);
            return ResponseEntity.ok(orders);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        try{
            Order order=orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order not found with id "+orderId));
            return ResponseEntity.ok(order);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
