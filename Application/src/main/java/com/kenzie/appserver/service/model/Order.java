package com.kenzie.appserver.service.model;

public class Order {

    private String id;

    private String productId;

    private String orderDate;

    private String status;

    private String customerName;

    private String address;

    public Order(String id, String productId, String orderDate, String status, String customerName, String address) {
        this.id = id;
        this.productId = productId;
        this.orderDate = orderDate;
        this.status = status;
        this.customerName = customerName;
        this.address = address;
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

    public String getAddress() {
        return address;
    }
}
