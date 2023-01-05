package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.common.base.Objects;

@DynamoDBTable(tableName = "Orders")
public class OrderRecord {
    private String id;
    private String productId;
    private String orderDate;
    private String status;
    private String customerName;
    private String customerAddress;
    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }
    @DynamoDBAttribute(attributeName = "orderDate")
    public String getOrderDate() {
        return orderDate;
    }
    @DynamoDBAttribute(attributeName = "productId")
    public String getProductId() {
        return productId;
    }
    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }
    @DynamoDBAttribute(attributeName = "customerName")
    public String getCustomerName() {
        return customerName;
    }
    @DynamoDBAttribute(attributeName = "address")
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setId(String id) {this.id = id;}
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRecord that = (OrderRecord) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
