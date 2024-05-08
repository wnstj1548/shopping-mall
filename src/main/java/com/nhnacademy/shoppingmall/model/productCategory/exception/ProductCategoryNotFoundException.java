package com.nhnacademy.shoppingmall.model.productCategory.exception;

import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;

public class ProductCategoryNotFoundException extends RuntimeException {
    public ProductCategoryNotFoundException(String productId){
        super(String.format("productCategory already exist:%s", productId));
    }
}
