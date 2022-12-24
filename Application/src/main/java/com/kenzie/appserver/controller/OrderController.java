package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.OrderCreateRequest;
import com.kenzie.appserver.controller.model.OrderResponse;
import com.kenzie.appserver.service.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getOrders(){
        List<Order> orders = orderService.getAll(); //CONTAINS TEMP NAMES

        if(orders == null || orders.isEmpty()) return ResponseEntity.noContent().build();

        List<OrderResponse> responses = orders.stream().map(this::convertOrder).collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("orderId") String orderId){
        Order order = orderService.getById(orderId); //CONTAINS TEMP NAMES
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(convertOrder(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderCreateRequest orderCreateRequest){
        Order order = new Order(UUID.randomUUID().toString(),
                orderCreateRequest.getProductId(),
                orderCreateRequest.getOrderDate(),
                orderCreateRequest.getStatus(),
                orderCreateRequest.getCustomerName(),
                orderCreateRequest.getAddress());

        orderService.createOrder(order); //CONTAINS TEMP NAMES

        OrderResponse orderResponse = convertOrder(order);

        return ResponseEntity.created(URI.create("/orders/"+orderResponse.getId())).body(orderResponse);
    }

    @DeleteMapping
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable("orderId") String orderId){
        orderService.deleteOrder(orderId); //CONTAINS TEMP NAMES
        return ResponseEntity.noContent().build();
    }

    private OrderResponse convertOrder(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setProductId(order.getProductId());
        orderResponse.setOrderDate(order.getOrderDate());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setCustomerName(order.getCustomerName());
        orderResponse.setAddress(order.getAddress());
        return orderResponse;
    }
}
