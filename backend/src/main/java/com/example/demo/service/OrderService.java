package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;

@Service
public class OrderService {
        private final ProductRepository productRepository;
        private final OrderRepository orderRepository;
        private final UserRepository userRepository;

        public OrderService(ProductRepository p, OrderRepository o, UserRepository u) {
                this.productRepository = p;
                this.orderRepository = o;
                this.userRepository = u;
        }

        @Transactional
        public Order create(OrderCreateRequest orderReq) {
                userRepository.findById(orderReq.getUserId())
                                .orElseThrow(() -> new IllegalArgumentException("User not found"));
                Product product = productRepository.findById(orderReq.getProductId())
                                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

                // Orderが入った分、Productの在庫数を減らす
                product.decreaseStock(orderReq.getQuantity());

                // Order(仮)を作成
                Order order = new Order(
                                orderReq.getUserId(),
                                orderReq.getProductId(),
                                orderReq.getQuantity(),
                                orderReq.getPrice());

                productRepository.save(product);
                return orderRepository.save(order);
        }

        @Transactional
        public void cancell(Long orderId) {
                Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new IllegalArgumentException("order not found"));
                Product product = productRepository.findById(order.getProductId())
                                .orElseThrow(() -> new IllegalArgumentException("product not found"));

                order.cancell();
                product.increaseStock(order.getQuantity());

                orderRepository.save(order);
                productRepository.save(product);
        }

}
