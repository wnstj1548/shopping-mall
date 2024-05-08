package com.nhnacademy.shoppingmall.model.productCategory.exception;

import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;

public class ProductCategoryAlreadyExistsException extends RuntimeException {
    public ProductCategoryAlreadyExistsException(ProductCategory pc){
        super(String.format("productCategory already exist:%s %s", pc.getProductId(), pc.getCategoryId()));
    }
}
