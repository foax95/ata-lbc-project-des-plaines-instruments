package com.kenzie.appserver.service.model;


public class Order {

    private final String id;
    private final String productId;
    private final String orderDate;
    private final String status;
    private final String customerName;
    private final String customerAddress;

    public Order(String id, String productId, String orderDate, String status, String customerName, String customerAddress) {
        this.id = id;
        this.productId = productId;
        this.orderDate = orderDate;
        this.status = status;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }
}
