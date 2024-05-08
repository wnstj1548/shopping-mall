package com.nhnacademy.shoppingmall.model.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.model.product.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(String productId);
    Page<Product> getAllProduct(int page, int pageSize);
    int save(Product product);
    int deleteByProductId(String productId);
    int update(Product product);
    int countByProductId(String productId);
    long totalCount();
    Page<Product> findByCategoryId(int page, int pageSize, String categoryId);
}
