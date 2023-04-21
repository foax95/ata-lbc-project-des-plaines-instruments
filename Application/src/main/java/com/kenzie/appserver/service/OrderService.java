package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.OrderRepository;
import com.kenzie.appserver.repositories.model.OrderRecord;
import com.kenzie.appserver.service.model.Order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(String id) {
        if(id == null) {
            throw new NullPointerException("id cannot be null");
        }

        OrderRecord orderRecord = orderRepository.findById(id).get();
        return new Order(orderRecord.getId(), orderRecord.getProductId(), orderRecord.getOrderDate(), orderRecord.getStatus(), orderRecord.getCustomerName(), orderRecord.getCustomerAddress());
    }

    public Order createNewOrder(Order order) {
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(order.getId());
        orderRecord.setProductId(order.getProductId());
        orderRecord.setOrderDate(order.getOrderDate());
        orderRecord.setStatus(order.getStatus());
        orderRecord.setCustomerName(order.getCustomerName());
        orderRecord.setCustomerAddress(order.getCustomerAddress());
        orderRepository.save(orderRecord);
        return order;
    }

    public Order updateOrder(Order order) {
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(order.getId());
        orderRecord.setProductId(order.getProductId());
        orderRecord.setOrderDate(order.getOrderDate());
        orderRecord.setStatus(order.getStatus());
        orderRecord.setCustomerName(order.getCustomerName());
        orderRecord.setCustomerAddress(order.getCustomerAddress());
        orderRepository.save(orderRecord);
        return order;
    }

    public List<Order> getAllOrders() throws OrderNotFoundException {
        Iterable<OrderRecord> orderRecords = orderRepository.findAll();

        List<Order> orders = new ArrayList<>();
        for(OrderRecord orderRecord : orderRecords) {
            orders.add(new Order(orderRecord.getId(), orderRecord.getProductId(), orderRecord.getOrderDate(), orderRecord.getStatus(), orderRecord.getCustomerName(), orderRecord.getCustomerAddress()));
        }

        if(orders.isEmpty()) {
            throw new OrderNotFoundException();
        }
        return orders;

    }

    public void deleteOrderById(String id) {
        orderRepository.deleteById(id);
    }
}
