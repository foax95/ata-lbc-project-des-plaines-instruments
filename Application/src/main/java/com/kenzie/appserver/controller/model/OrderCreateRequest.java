package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class OrderCreateRequest {
    @NotEmpty
    @JsonProperty("product_id")
    private String productId;
    @NotEmpty
    @JsonProperty("customer_name")
    private String customerName;

    @NotEmpty
    @JsonProperty("address")
    private String address;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
