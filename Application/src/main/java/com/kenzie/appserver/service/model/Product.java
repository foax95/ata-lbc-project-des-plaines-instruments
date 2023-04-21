package com.kenzie.appserver.service.model;

public class Product {
    private final String id;
    private final String type;
    private final String material;
    private final String color;
    private final String syle;

    public Product(String id, String type, String material, String color, String syle) {
        this.id = id;
        this.type = type;
        this.material = material;
        this.color = color;
        this.syle = syle;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public String getColor() {
        return color;
    }

    public String getSyle() {
        return syle;
    }

}
