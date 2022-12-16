package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.common.base.Objects;

@DynamoDBTable(tableName = "Order")
public class OrderRecord {
    private String productId;
    private String orderDate;
    //status seems to be a Java keyword or something
    private String orderStatus;
    private String customerName;
    private String customerAddress;
    @DynamoDBHashKey(attributeName = "productId")
    public String getProductId() {
        return productId;
    }
    @DynamoDBAttribute(attributeName = "orderDate")
    public String getOrderDate() {
        return orderDate;
    }
    @DynamoDBAttribute(attributeName = "orderStatus")
    public String getOrderStatus() {
        return orderStatus;
    }
    @DynamoDBAttribute(attributeName = "customerName")
    public String getCustomerName() {
        return customerName;
    }
    @DynamoDBAttribute(attributeName = "customerAddress")
    public String getCustomerAddress() {
        return customerAddress;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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
        return Objects.equal(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productId);
    }
}
