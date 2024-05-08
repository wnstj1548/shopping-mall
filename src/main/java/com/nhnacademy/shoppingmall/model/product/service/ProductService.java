package com.nhnacademy.shoppingmall.model.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.model.product.domain.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(String productId);

    Page<Product> getAllProduct(int page, int pageSize);

    void updateProduct(Product product);

    void deleteProduct(String productId);

    void saveProduct(Product product);

    Page<Product> getProductByCategoryId(int page, int pageSize, String categoryId);
}
