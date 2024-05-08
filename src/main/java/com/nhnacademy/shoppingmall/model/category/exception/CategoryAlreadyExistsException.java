package com.nhnacademy.shoppingmall.model.category.exception;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException(String categoryId){
        super(String.format("category already exist:%s", categoryId));
    }
}
