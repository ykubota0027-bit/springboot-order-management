package com.example.demo.Controller;

import com.example.demo.controller.OrderController;
import com.example.demo.dto.request.OrderCreateRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.model.Order;
import com.example.demo.service.OrderQueryService;
import com.example.demo.service.OrderService;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import(GlobalExceptionHandler.class)
class OrderControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @MockitoBean
        private OrderService orderService;

        @MockitoBean
        private OrderQueryService orderQueryService;

        @Test
        @DisplayName("create: 正常系なら201 Createdと注文情報を返す")
        void create_正常系_201と注文情報を返す() throws Exception {
                OrderCreateRequest request = new OrderCreateRequest(
                                1L,
                                10L,
                                2,
                                new BigDecimal("1000"));

                Order order = new Order(1L, 10L, 2, BigDecimal.valueOf(1000));

                when(orderService.create(any(OrderCreateRequest.class))).thenReturn(order);

                mockMvc.perform(post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                                .andExpect(status().isCreated())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.userId").value(1))
                                .andExpect(jsonPath("$.productId").value(10))
                                .andExpect(jsonPath("$.quantity").value(2))
                                .andExpect(jsonPath("$.price").value(1000))
                                .andExpect(jsonPath("$.totalAmount").value(2000))
                                .andExpect(jsonPath("$.status").value("CREATED"));

                verify(orderService).create(any(OrderCreateRequest.class));
        }

        @Test
        void create_userIdがnullなら400を返す() throws Exception {
                String requestBody = """
                                {
                                "userId": null,
                                "productId": 10,
                                "quantity": 2,
                                "price": 1000
                                }
                                """;

                mockMvc.perform(post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.error").value("Validation Error"))
                                .andExpect(jsonPath("$.fieldErrors.userId").value("userId is required"));

                verify(orderService, never()).create(any());
        }

        @Test
        void create_productIdがnullなら400を返す() throws Exception {
                String requestBody = """
                                {
                                "userId": 1,
                                "productId": null,
                                "quantity": 2,
                                "price": 1000
                                }
                                """;
                mockMvc.perform(post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.error").value("Validation Error"))
                                .andExpect(jsonPath("$.fieldErrors.productId").value("productId is required"));
                verify(orderService, never()).create(any());
        }

        @Test
        void create_quantityがnullなら400を返す() throws Exception {
                String requestBody = """
                                {
                                "userId": 1,
                                "productId": 10,
                                "quantity": null,
                                "price": 1000
                                }
                                """;
                mockMvc.perform(post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.error").value("Validation Error"))
                                .andExpect(jsonPath("$.fieldErrors.quantity").value("quantity is required"));
                verify(orderService, never()).create(any());
        }

        @Test
        void create_quantityが0なら400を返す() throws Exception {
                String requestBody = """
                                {
                                "userId": 1,
                                "productId": 10,
                                "quantity": 0,
                                "price": 1000
                                }
                                """;
                mockMvc.perform(post("/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.error").value("Validation Error"))
                                .andExpect(jsonPath("$.fieldErrors.quantity")
                                                .value("quantity must be greater than or equal to 1"));

                verify(orderService, never()).create(any());
        }

        @Test
        void findAll_正常系() throws Exception {
                OrderResponse res = new OrderResponse(
                                1L,
                                2L,
                                3L,
                                10,
                                new BigDecimal("100"),
                                new BigDecimal("1000"),
                                "CREATED");

                OrderResponse res2 = new OrderResponse(
                                2L,
                                3L,
                                4L,
                                15,
                                new BigDecimal("200"),
                                new BigDecimal("3000"),
                                "CANCELLED");

                when(orderQueryService.findAll()).thenReturn(List.of(res, res2));

                mockMvc.perform(get("/orders"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.length()").value(2))
                                .andExpect(jsonPath("$[0].orderId").value(1))
                                .andExpect(jsonPath("$[0].userId").value(2))
                                .andExpect(jsonPath("$[0].productId").value(3))
                                .andExpect(jsonPath("$[0].quantity").value(10))
                                .andExpect(jsonPath("$[0].price").value(100))
                                .andExpect(jsonPath("$[0].totalAmount").value(1000))
                                .andExpect(jsonPath("$[0].status").value("CREATED"))
                                .andExpect(jsonPath("$[1].orderId").value(2))
                                .andExpect(jsonPath("$[1].userId").value(3))
                                .andExpect(jsonPath("$[1].productId").value(4))
                                .andExpect(jsonPath("$[1].quantity").value(15))
                                .andExpect(jsonPath("$[1].price").value(200))
                                .andExpect(jsonPath("$[1].totalAmount").value(3000))
                                .andExpect(jsonPath("$[1].status").value("CANCELLED"));

        }

        @Test
        void findAll_データなしの場合200と空リストを返す() throws Exception {
                when(orderQueryService.findAll()).thenReturn(List.of());

                mockMvc.perform(get("/orders"))
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        void findById_正常系() throws Exception {
                OrderResponse res = new OrderResponse(
                                100L,
                                1L,
                                10L,
                                10,
                                new BigDecimal(1000),
                                new BigDecimal(2000),
                                "CREATED");

                when(orderQueryService.findById(100L)).thenReturn(res);

                mockMvc.perform(get("/orders/100"))
                                .andExpect(status().isOk());
        }
}
