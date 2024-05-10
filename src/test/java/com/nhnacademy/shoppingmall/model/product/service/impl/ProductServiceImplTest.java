package com.nhnacademy.shoppingmall.model.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;


@Slf4j
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    ProductService productService = new ProductServiceImpl(productRepository);
    Product testProduct = new Product("testProductId", "testProductName", 10, "/resources/no-image.png", "/resources/no-image.png",10000,9000,"테스트 상품 설명");

    @Test
    @DisplayName("getProduct")
    void getProduct() {
        Mockito.when(productRepository.findById(anyString())).thenReturn(Optional.of(testProduct));
        productService.getProduct(testProduct.getProductId());
        Mockito.verify(productRepository , Mockito.times(1)).findById(anyString());
    }

    @Test
    @DisplayName("getProduct - product not found -> return null")
    void getProduct_not_found(){
        Mockito.when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        Product product = productService.getProduct(testProduct.getProductId());
        Assertions.assertNull(product);
    }

    @Test
    @DisplayName("save product")
    void saveProduct() {

        Mockito.when(productRepository.countByProductId(anyString())).thenReturn(0);
        Mockito.when(productRepository.save(any())).thenReturn(1);
        productService.saveProduct(testProduct);
        Mockito.verify(productRepository,Mockito.times(1)).countByProductId(anyString());
        Mockito.verify(productRepository,Mockito.times(1)).save(any());

    }

    @Test
    @DisplayName("save product -already exist user")
    void saveProduct_exist(){
        Mockito.when(productRepository.countByProductId(anyString())).thenReturn(1);
        Throwable throwable = Assertions.assertThrows(ProductAlreadyExistsException.class,()-> productService.saveProduct(testProduct));
        log.debug("error:{}",throwable.getMessage());
    }

    @Test
    @DisplayName("update product")
    void updateProduct() {
        Mockito.when(productRepository.countByProductId(anyString())).thenReturn(1);
        Mockito.when(productRepository.update(testProduct)).thenReturn(1);
        productService.updateProduct(testProduct);
        Mockito.verify(productRepository,Mockito.times(1)).update(any());
        Mockito.verify(productRepository,Mockito.times(1)).countByProductId(anyString());
    }

    @Test
    @DisplayName("delete product")
    void deleteProduct() {
        Mockito.when(productRepository.deleteByProductId(anyString())).thenReturn(1);
        Mockito.when(productRepository.countByProductId(anyString())).thenReturn(1);

        productService.deleteProduct(testProduct.getProductId());

        Mockito.verify(productRepository,Mockito.times(1)).deleteByProductId(anyString());
        Mockito.verify(productRepository,Mockito.times(1)).countByProductId(anyString());

    }

    @Test
    @DisplayName("get all product return page")
    void getAllProduct() {
        Page<Product> productPage = new Page<>(Collections.singletonList(testProduct), 10);
        Mockito.when(productRepository.getAllProduct(anyInt(), anyInt())).thenReturn(productPage);
        Page<Product> resultPage = productService.getAllProduct(1, 10);
        Mockito.verify(productRepository, Mockito.times(1)).getAllProduct(1, 10);
    }

    @Test
    @DisplayName("get all product return page")
    void getProductByCategoryId() {
        Page<Product> productPage = new Page<>(Collections.singletonList(testProduct), 10);
        Mockito.when(productRepository.findByCategoryId(anyInt(), anyInt(), anyString())).thenReturn(productPage);
        Page<Product> resultPage = productService.getProductByCategoryId(1, 10, "testCategoryId");
        Mockito.verify(productRepository, Mockito.times(1)).findByCategoryId(1, 10, "testCategoryId");
    }

}
