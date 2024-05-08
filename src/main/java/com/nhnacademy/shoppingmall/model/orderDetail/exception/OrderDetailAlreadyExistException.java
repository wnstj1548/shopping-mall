package com.nhnacademy.shoppingmall.model.orderDetail.exception;

public class OrderDetailAlreadyExistException extends RuntimeException {
    public OrderDetailAlreadyExistException(String orderDetailId){
        super(String.format("orderDetail already exist:%s",orderDetailId));
    }
}
