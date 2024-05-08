package com.nhnacademy.shoppingmall.model.order.exception;

public class OrderAlreadyExistsException extends RuntimeException {
    public OrderAlreadyExistsException(String userId){
        super(String.format("irder already exist:%s",userId));
    }
}
