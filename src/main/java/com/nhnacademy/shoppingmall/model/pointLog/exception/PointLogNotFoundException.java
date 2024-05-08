package com.nhnacademy.shoppingmall.model.pointLog.exception;

public class PointLogNotFoundException extends RuntimeException {
    public PointLogNotFoundException(String pointLogId){
        super(String.format("pointLog not found :%s", pointLogId));
    }
}
