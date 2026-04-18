package com.example.demo.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.model.Order;
import com.example.demo.model.OrderStatus;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;

@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void create_正常なリクエストなら注文を作成できる() {
        User user = userRepository.save(new User("yuma", "yuma@example.com"));
        Product product = productRepository.save(new Product("Mouse", BigDecimal.valueOf(1000), 10));

        OrderCreateRequest request = new OrderCreateRequest(
                user.getUserId(),
                product.getProductId(),
                3,
                product.getPrice());

        Order order = orderService.create(request);

        assertNotNull(order);
        assertNotNull(order.getOrderId());
        assertEquals(user.getUserId(), order.getOrderId());
        assertEquals(product.getProductId(), order.getProductId());
        assertEquals(3, order.getQuantity());
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(order.getOrderedPrice()));
        assertEquals(0, BigDecimal.valueOf(3000).compareTo(order.getTotalAmount()));
        assertEquals(OrderStatus.CREATED, order.getStatus());
    }

    @Test
    void create_注文時に在庫が減る() {
        User user = userRepository.save(new User("yuma", "test2@example.com"));
        Product product = productRepository.save(new Product("Mouse", BigDecimal.valueOf(1000), 10));

        OrderCreateRequest request = new OrderCreateRequest(
                user.getUserId(),
                product.getProductId(),
                3,
                product.getPrice());

        orderService.create(request);

        Product updatedProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("product not found"));

        assertEquals(7, updatedProduct.getStock());
    }

    @Test
    void create_在庫不足なら例外を投げる() {
        User user = userRepository.save(new User("test03", "test03@example.com"));
        Product product = productRepository.save(new Product("Mouse", BigDecimal.valueOf(1000), 2));

        OrderCreateRequest request = new OrderCreateRequest(
                user.getUserId(),
                product.getProductId(),
                3,
                product.getPrice());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            orderService.create(request);
        });

        assertEquals("在庫が不足しています", ex.getMessage());
    }

    @Test
    void create_Userが存在しないなら例外を投げる() {
        Product product = productRepository.save(
                new Product("Mouse", BigDecimal.valueOf(1000), 10));

        OrderCreateRequest request = new OrderCreateRequest(
                999L, // 存在しないUser ID
                product.getProductId(),
                2,
                product.getPrice());

        System.out.println(userRepository.findAll());
        System.out.println(productRepository.findAll());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            orderService.create(request);
        });

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void create_Productが存在しないなら例外を投げる() {
        User user = userRepository.save(new User("test05", "test05@example.com"));
        Product product = productRepository.save(new Product("Mouse", BigDecimal.valueOf(1000), 10));

        OrderCreateRequest req = new OrderCreateRequest(
                user.getUserId(),
                999L,
                3,
                product.getPrice());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            orderService.create(req);
        });

        assertEquals("Product not found", ex.getMessage());
    }

    @Test
    void cancell_キャンセル時在庫が元に戻されること() {
        User user = userRepository.save(new User("cancell01", "cancell01@exmaple.com"));
        Product product = productRepository.save(new Product("Mouse", BigDecimal.valueOf(1000), 10));

        OrderCreateRequest req = new OrderCreateRequest(
                user.getUserId(),
                product.getProductId(),
                3,
                product.getPrice());

        Order order = orderService.create(req);
        orderService.cancell(order.getOrderId());

        Order cancelledOrder = orderRepository.findById(order.getOrderId()).orElseThrow();
        Product updateProduct = productRepository.findById(product.getProductId()).orElseThrow();

        assertEquals(3, cancelledOrder.getQuantity());
        assertEquals(OrderStatus.CANCELLED, order.getStatus());
        assertEquals(10, updateProduct.getStock());
    }

    @Test
    void cancell_元の注文が見つからない時例外を投げる() {
        User user = userRepository.save(new User("cancell02", "cancell02@exmaple.com"));
        Product product = productRepository.save(new Product("Mouse", BigDecimal.valueOf(1000), 10));

        OrderCreateRequest req = new OrderCreateRequest(
                user.getUserId(),
                product.getProductId(),
                3,
                product.getPrice());

        orderService.create(req);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            orderService.cancell(999L);
        });
        assertEquals("order not found", ex.getMessage());
    }

    @Test
    void cancell_二重キャンセルされた場合例外を返す() {
        User user = userRepository.save(new User("cancell03", "cancell03@exmaple.com"));
        Product product = productRepository.save(new Product("Mouse", BigDecimal.valueOf(1000), 10));

        OrderCreateRequest req = new OrderCreateRequest(
                user.getUserId(),
                product.getProductId(),
                3,
                product.getPrice());

        Order order = orderService.create(req);
        orderService.cancell(order.getOrderId());

        Order cancelledOrder = orderRepository.findById(order.getOrderId()).orElseThrow();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            orderService.cancell(cancelledOrder.getOrderId());
        });

        assertEquals("order is already cancelled", ex.getMessage());
    }

}
