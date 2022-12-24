package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class OrderCreateRequest {
    @NotEmpty
    @JsonProperty("id")
    private String id;

    @NotEmpty
    @JsonProperty("product_id")
    private String productId;

    @NotEmpty
    @JsonProperty("order_date")
    private String orderDate;

    @NotEmpty
    @JsonProperty("status")
    private String status;

    @NotEmpty
    @JsonProperty("customer_name")
    private String customerName;

    @NotEmpty
    @JsonProperty("address")
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
