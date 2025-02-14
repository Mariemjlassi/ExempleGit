package com.project.app.controller;

import com.project.app.model.Order;
import com.project.app.repository.OrderRepository;
import com.project.app.model.User;
import com.project.app.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")	
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserClient userClient; // Le client Feign pour récupérer l'utilisateur

    @GetMapping
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            if (order.getUserId() != null) {
                // Récupérer l'utilisateur en fonction de l'userId de la commande
                User user = userClient.getUserById(order.getUserId());
                order.setUser(user); // Associer l'utilisateur à la commande
            }
        }
        return orders;
    }

    // Endpoint pour récupérer une commande spécifique par son ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null && order.getUserId() != null) {
            User user = userClient.getUserById(order.getUserId());
            order.setUser(user);
        }
        return order;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
