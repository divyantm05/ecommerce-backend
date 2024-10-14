package com.divyan.ecommerce.ecommerce_backend.Service;

import com.divyan.ecommerce.ecommerce_backend.Entity.Order;
import com.divyan.ecommerce.ecommerce_backend.Entity.OrderItem;
import com.divyan.ecommerce.ecommerce_backend.Respository.OrderItemRepository;
import com.divyan.ecommerce.ecommerce_backend.Respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

   public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
       return orderItemRepository.findByOrderId(orderId);
   }

}
