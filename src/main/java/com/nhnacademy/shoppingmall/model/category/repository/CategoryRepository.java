package com.nhnacademy.shoppingmall.model.category.repository;

import com.nhnacademy.shoppingmall.model.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(String categoryId);
    List<Category> findAll();
    int save(Category category);
    int deleteById(String categoryId);
    int update(Category category);
    int countById(String categoryId);
    List<Category> findByProductId(String productId);
}
