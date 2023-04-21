package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.OrderCreateRequest;
import com.kenzie.appserver.service.OrderService;
import com.kenzie.appserver.service.model.Order;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@IntegrationTest
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    OrderService orderService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getOrders_returnsListOfOrders() throws Exception {
        String id = UUID.randomUUID().toString();

        Order order = new Order(id, UUID.randomUUID().toString(), LocalDateTime.now().toString(),
                mockNeat.strings().valStr(), mockNeat.names().valStr(), mockNeat.addresses().valStr());

        orderService.createNewOrder(order);

        mvc.perform(get("/orders/all").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk());

        orderService.deleteOrderById(id);
    }

    @Test
    public void createOrder_withValidContents_goodStatus() throws Exception {
        String id = UUID.randomUUID().toString();

        OrderCreateRequest orderCreateRequest = new OrderCreateRequest();
        orderCreateRequest.setProductId(UUID.randomUUID().toString());
        orderCreateRequest.setCustomerName(mockNeat.names().valStr());
        orderCreateRequest.setAddress(mockNeat.addresses().valStr());

        MvcResult result = mvc.perform(post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(orderCreateRequest)))

                .andExpect(jsonPath("id")
                        .exists())
                .andExpect(jsonPath("product_id")
                        .exists())
                .andExpect(jsonPath("order_date")
                        .exists())
                .andExpect(jsonPath("status")
                        .exists())
                .andExpect(jsonPath("customer_name")
                        .exists())
                .andExpect(jsonPath("address")
                        .exists())
                .andExpect(status().isCreated())
                .andReturn();
        orderService.deleteOrderById(JsonPath.read(result.getResponse().getContentAsString(), "$.id"));
    }

    @Test
    public void getOrderById_withValidId_returnsCorrectOrder() throws Exception {
        String id = UUID.randomUUID().toString();

        Order order = new Order(id, UUID.randomUUID().toString(), LocalDateTime.now().toString(),
                mockNeat.strings().valStr(), mockNeat.names().valStr(), mockNeat.addresses().valStr());

        orderService.createNewOrder(order);

        mvc.perform(get("/orders/{id}", id)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("id")
                        .exists())
                .andExpect(jsonPath("product_id")
                        .exists())
                .andExpect(jsonPath("order_date")
                        .exists())
                .andExpect(jsonPath("status")
                        .exists())
                .andExpect(jsonPath("customer_name")
                        .exists())
                .andExpect(jsonPath("address")
                        .exists())
                .andExpect(status().isOk());
        orderService.deleteOrderById(id);
    }

    @Test
    public void getOrderById_withInvalidId_notFoundStatus() throws Exception {
        String id = UUID.randomUUID().toString();

        mvc.perform(get("/orders/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteOrder_withValidId_deletesOrder() throws Exception {
        String id = UUID.randomUUID().toString();

        Order order = new Order(id, UUID.randomUUID().toString(), LocalDateTime.now().toString(),
                mockNeat.strings().valStr(), mockNeat.names().valStr(), mockNeat.addresses().valStr());

        orderService.createNewOrder(order);

        mvc.perform(delete("/orders/{id}", id)
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isNoContent());

    }

    @Test
    public void deleteOrder_withInvalidId_notFoundStatus() throws Exception {
        String id = UUID.randomUUID().toString();

        mvc.perform(delete("/orders/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isNotFound());
    }

}
