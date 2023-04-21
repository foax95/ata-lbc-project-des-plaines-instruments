package com.kenzie.appserver.service;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Could not find order");
    }
}
