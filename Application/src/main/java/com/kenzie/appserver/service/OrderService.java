package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.OrderRepository;
import com.kenzie.appserver.repositories.model.OrderRecord;
import com.kenzie.appserver.service.model.Order;


import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(String id) {
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

    public Order getAllOrders() throws OrderNotFoundException {
        Iterable<OrderRecord> orderRecords = orderRepository.findAll();

        if(orderRecords.iterator().hasNext()) {
            OrderRecord orderRecord = orderRecords.iterator().next();
            return new Order(orderRecord.getId(), orderRecord.getProductId(), orderRecord.getOrderDate(), orderRecord.getStatus(), orderRecord.getCustomerName(), orderRecord.getCustomerAddress());
        }else {
            throw new OrderNotFoundException();
        }

    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
