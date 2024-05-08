package com.nhnacademy.shoppingmall.model.pointLog.exception;

public class PointLogAlreadyExistsExeption extends RuntimeException {
    public PointLogAlreadyExistsExeption(String pointLogId){
        super(String.format("pointLog already exists :%s", pointLogId));
    }
}
