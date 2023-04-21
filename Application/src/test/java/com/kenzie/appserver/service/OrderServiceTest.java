package com.kenzie.appserver.service;


import com.kenzie.appserver.repositories.OrderRepository;

import com.kenzie.appserver.repositories.model.OrderRecord;

import com.kenzie.appserver.service.model.Order;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }
    /** ------------------------------------------------------------------------
     *  orderService.getOrderById()
     *  ------------------------------------------------------------------------ **/

    @Test
    void getOrderById() {
        //GIVEN
        String id = randomUUID().toString();
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(id);
        orderRecord.setProductId("1");
        orderRecord.setOrderDate("2020-01-01");
        orderRecord.setStatus("pending");
        orderRecord.setCustomerName("John Doe");
        orderRecord.setCustomerAddress("123 Main St");
        when(orderRepository.findById(id)).thenReturn(Optional.of(orderRecord));

        //WHEN
        Order order = orderService.getOrderById(id);

        //THEN
        Assertions.assertEquals(id, order.getId());
        Assertions.assertEquals("1", order.getProductId());
        Assertions.assertEquals("2020-01-01", order.getOrderDate());
        Assertions.assertEquals("pending", order.getStatus());
        Assertions.assertEquals("John Doe", order.getCustomerName());
        Assertions.assertEquals("123 Main St", order.getCustomerAddress());
    }

    @Test
    void getOrderByIdNotFound_throws_NullPointerException() {
        //GIVEN
        String id = randomUUID().toString();
        when(orderRepository.findById(id)).thenReturn(null);

        //WHEN/THEN
        Assertions.assertThrows(NullPointerException.class, () -> {
            orderService.getOrderById(id);
        });
    }

    /** ------------------------------------------------------------------------
     *  orderService.createNewOrder()
     *  ------------------------------------------------------------------------ **/

    @Test
    void createNewOrder() {
        //GIVEN
        String id = randomUUID().toString();
        String productId = "1";
        String orderDate = "2020-01-01";
        String status = "pending";
        String customerName = "John Doe";
        String customerAddress = "123 Main St";
        Order order = new Order(id, productId, orderDate, status, customerName, customerAddress);

        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(id);
        orderRecord.setProductId(productId);
        orderRecord.setOrderDate(orderDate);
        orderRecord.setStatus(status);
        orderRecord.setCustomerName(customerName);
        orderRecord.setCustomerAddress(customerAddress);
        when(orderRepository.save(orderRecord)).thenReturn(orderRecord);

        //WHEN
        Order result = orderService.createNewOrder(order);

        //THEN
        Assertions.assertEquals(id, result.getId());
        Assertions.assertEquals("1", result.getProductId());
        Assertions.assertEquals("2020-01-01", result.getOrderDate());
        Assertions.assertEquals("pending", result.getStatus());
        Assertions.assertEquals("John Doe", result.getCustomerName());
        Assertions.assertEquals("123 Main St", result.getCustomerAddress());
    }


    /** ------------------------------------------------------------------------
     *  orderService.getAllOrders()
     *  ------------------------------------------------------------------------ **/

    @Test
    void getAllOrders() {
        //GIVEN
        String id = randomUUID().toString();
        List<OrderRecord> orderRecords = new ArrayList<>();
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(id);
        orderRecord.setProductId("1");
        orderRecord.setOrderDate("2020-01-01");
        orderRecord.setStatus("pending");
        orderRecord.setCustomerName("John Doe");
        orderRecord.setCustomerAddress("123 Main St");
        orderRecords.add(orderRecord);
        when(orderRepository.findAll()).thenReturn(orderRecords);

        //WHEN
        List<Order> orders = orderService.getAllOrders();

        //THEN
        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(id, orders.get(0).getId());
        Assertions.assertEquals("1", orders.get(0).getProductId());
        Assertions.assertEquals("2020-01-01", orders.get(0).getOrderDate());
        Assertions.assertEquals("pending", orders.get(0).getStatus());
        Assertions.assertEquals("John Doe", orders.get(0).getCustomerName());
        Assertions.assertEquals("123 Main St", orders.get(0).getCustomerAddress());

    }

    @Test
    void getAllOrdersEmptyList_throwsOrderNotFoundException() {
        //GIVEN
        List<OrderRecord> orderRecords = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderRecords);

        //WHEN/THEN
        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderService.getAllOrders();
        });
    }

    /** ------------------------------------------------------------------------
     *  orderService.deleteOrderById()
     *  ------------------------------------------------------------------------ **/

    @Test
    void deleteOrderById() {
        //GIVEN
        String id = randomUUID().toString();
        OrderRecord orderRecord = new OrderRecord();
        orderRecord.setId(id);
        orderRecord.setProductId("1");
        orderRecord.setOrderDate("2020-01-01");
        orderRecord.setStatus("pending");
        orderRecord.setCustomerName("John Doe");
        orderRecord.setCustomerAddress("123 Main St");
        when(orderRepository.findById(id)).thenReturn(Optional.of(orderRecord));

        //WHEN
        orderService.deleteOrderById(orderRecord.getId());


        //THEN
        verify(orderRepository, times(1)).deleteById(orderRecord.getId());
    }
}
