package com.nhnacademy.shoppingmall.model.category.service.impl;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.exception.CategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.category.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.model.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import com.nhnacademy.shoppingmall.model.product.domain.Product;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(String categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.orElse(null);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void updateCategory(Category category) {
        if(categoryRepository.countById(category.getCategoryId()) <= 0 ) {
            throw new CategoryNotFoundException(category.getCategoryId());
        }

        categoryRepository.update(category);
    }

    @Override
    public void deleteCategory(String categoryId) {
        if(categoryRepository.countById(categoryId) <= 0 ) {
            throw new CategoryNotFoundException(categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void saveCategory(Category category) {
        if(categoryRepository.countById(category.getCategoryId()) > 0 ) {
            throw new CategoryAlreadyExistsException(category.getCategoryId());
        }
        categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategoryByProductId(String productId) {
        return categoryRepository.findByProductId(productId);
    }
}
