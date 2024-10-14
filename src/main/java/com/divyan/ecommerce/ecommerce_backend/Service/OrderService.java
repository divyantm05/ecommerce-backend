package com.divyan.ecommerce.ecommerce_backend.Service;

import com.divyan.ecommerce.ecommerce_backend.Entity.Order;
import com.divyan.ecommerce.ecommerce_backend.Entity.OrderItem;
import com.divyan.ecommerce.ecommerce_backend.Entity.User;
import com.divyan.ecommerce.ecommerce_backend.Respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(User user,List<OrderItem> orderItems) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setOrderDate(new Date(String.valueOf(LocalDate.now())));
        double total = order.getOrderItems().stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotal(total);
        return orderRepository.save(order);
    }


}
