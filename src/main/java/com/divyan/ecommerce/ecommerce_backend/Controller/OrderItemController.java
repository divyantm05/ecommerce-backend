package com.divyan.ecommerce.ecommerce_backend.Controller;

import com.divyan.ecommerce.ecommerce_backend.Entity.OrderItem;
import com.divyan.ecommerce.ecommerce_backend.Respository.OrderItemRepository;
import com.divyan.ecommerce.ecommerce_backend.Respository.OrderRepository;
import com.divyan.ecommerce.ecommerce_backend.Service.OrderItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderItemController(OrderItemService orderItemService, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderItemService = orderItemService;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderId(@PathVariable Long orderId) {
        try{
            List<OrderItem> orderItems=orderItemService.getOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(orderItems);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/order/{orderItemId}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long orderItemId) {
        try{
            OrderItem orderItem=orderItemRepository.findById(orderItemId).orElseThrow(()->new RuntimeException("OrderItem Not Found"));
            return ResponseEntity.ok(orderItem);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{orderItemId}")
    public ResponseEntity<OrderItem> updateOrderItemById(@PathVariable Long orderItemId, @RequestBody OrderItem orderItem) {
        try{
            orderItemRepository.save(orderItem);
            return ResponseEntity.ok(orderItem);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{orderItemId}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long orderItemId){
        try{
            orderItemRepository.deleteById(orderItemId);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
