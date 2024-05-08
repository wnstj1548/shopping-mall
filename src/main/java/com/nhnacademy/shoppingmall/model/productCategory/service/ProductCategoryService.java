package com.nhnacademy.shoppingmall.model.productCategory.service;

import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    void updatePC(ProductCategory pc);
    void deletePC(String productId);
    void savePC(ProductCategory pc);
}
