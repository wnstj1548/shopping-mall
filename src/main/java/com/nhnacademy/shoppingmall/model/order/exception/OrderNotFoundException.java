package com.nhnacademy.shoppingmall.model.order.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String orderId){
        super(String.format("order not found:"+orderId));
    }
}
