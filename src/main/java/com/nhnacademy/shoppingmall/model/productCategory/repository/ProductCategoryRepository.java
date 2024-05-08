package com.nhnacademy.shoppingmall.model.productCategory.repository;

import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;

public interface ProductCategoryRepository {
    int save(ProductCategory pc);
    int delete(String productId);
    int update(ProductCategory pc);
    int countByProductId(String productId);
    int countById(String productId, String categoryId);
}
