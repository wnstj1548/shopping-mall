package com.nhnacademy.shoppingmall.model.category.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String categoryId){
        super(String.format("category not found:%s", categoryId));
    }
}
