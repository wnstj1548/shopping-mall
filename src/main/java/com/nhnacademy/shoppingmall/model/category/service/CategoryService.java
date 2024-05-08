package com.nhnacademy.shoppingmall.model.category.service;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.product.domain.Product;

import java.util.List;

public interface CategoryService {
    Category getCategory(String categoryId);

    List<Category> getAllCategory();

    void updateCategory(Category category);

    void deleteCategory(String categoryId);

    void saveCategory(Category category);

    List<Category> getCategoryByProductId(String productId);
}
