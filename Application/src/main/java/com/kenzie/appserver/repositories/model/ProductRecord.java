package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.common.base.Objects;

@DynamoDBTable(tableName = "Products")
public class ProductRecord {
    private String id;
    private String type;
    private String material;
    private String color;
    private String syle;
    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }
    @DynamoDBRangeKey(attributeName = "type")
    public String getType() {
        return type;
    }
    @DynamoDBAttribute(attributeName = "material")
    public String getMaterial() {
        return material;
    }
    @DynamoDBAttribute(attributeName = "color")
    public String getColor() {
        return color;
    }
    @DynamoDBAttribute(attributeName = "style")
    public String getSyle() {
        return syle;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSyle(String syle) {
        this.syle = syle;
    }
}
