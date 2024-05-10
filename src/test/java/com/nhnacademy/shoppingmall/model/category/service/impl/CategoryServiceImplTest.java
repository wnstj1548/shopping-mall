package com.nhnacademy.shoppingmall.model.category.service.impl;

import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.exception.CategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.model.category.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
    CategoryService categoryService = new CategoryServiceImpl(categoryRepository);
    Category testCategory = new Category("testCategoryId", "testCategoryName");

    @Test
    @DisplayName("save category")
    void saveCategory() {

        Mockito.when(categoryRepository.countById(anyString())).thenReturn(0);
        Mockito.when(categoryRepository.save(any())).thenReturn(1);
        categoryService.saveCategory(testCategory);
        Mockito.verify(categoryRepository,Mockito.times(1)).countById(anyString());
        Mockito.verify(categoryRepository,Mockito.times(1)).save(any());

    }

    @Test
    @DisplayName("save category -already exist user")
    void saveCategory_exist(){
        Mockito.when(categoryRepository.countById(anyString())).thenReturn(1);
        Throwable throwable = Assertions.assertThrows(CategoryAlreadyExistsException.class,()-> categoryService.saveCategory(testCategory));
        log.debug("error:{}",throwable.getMessage());
    }

    @Test
    @DisplayName("update category")
    void updateCategory() {
        Mockito.when(categoryRepository.countById(anyString())).thenReturn(1);
        Mockito.when(categoryRepository.update(testCategory)).thenReturn(1);
        categoryService.updateCategory(testCategory);
        Mockito.verify(categoryRepository,Mockito.times(1)).update(any());
        Mockito.verify(categoryRepository,Mockito.times(1)).countById(anyString());
    }

    @Test
    @DisplayName("delete category")
    void deleteCategory() {
        Mockito.when(categoryRepository.deleteById(anyString())).thenReturn(1);
        Mockito.when(categoryRepository.countById(anyString())).thenReturn(1);

        categoryService.deleteCategory(testCategory.getCategoryId());

        Mockito.verify(categoryRepository,Mockito.times(1)).deleteById(anyString());
        Mockito.verify(categoryRepository,Mockito.times(1)).countById(anyString());

    }

    @Test
    @DisplayName("get all category")
    void getAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(testCategory);
        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> resultCategoryList = categoryService.getAllCategory();
        Mockito.verify(categoryRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("get all category")
    void getCategoryByProductId() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(testCategory);
        Mockito.when(categoryRepository.findByProductId(anyString())).thenReturn(categoryList);
        List<Category> categoryResult = categoryService.getCategoryByProductId("testProductId");
        Mockito.verify(categoryRepository, Mockito.times(1)).findByProductId("testProductId");
    }
}
