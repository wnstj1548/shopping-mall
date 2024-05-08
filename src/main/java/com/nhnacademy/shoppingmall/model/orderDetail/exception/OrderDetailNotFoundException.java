package com.nhnacademy.shoppingmall.model.orderDetail.exception;

public class OrderDetailNotFoundException extends RuntimeException{
    public OrderDetailNotFoundException(String orderDetailId){
        super(String.format("user not found:"+ orderDetailId));
    }
}
