package com.nhnacademy.shoppingmall.model.productCategory.service.impl;

import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;
import com.nhnacademy.shoppingmall.model.productCategory.exception.ProductCategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.productCategory.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.model.productCategory.service.ProductCategoryService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@Slf4j
@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceImplTest {

    ProductCategoryRepository pcRepository = Mockito.mock(ProductCategoryRepository.class);
    ProductCategoryService pcService = new ProductCateogoryServiceImpl(pcRepository);
    ProductCategory testPc = new ProductCategory("productId", "categoryId");


    @Test
    @DisplayName("save pc")
    void savePC() {

        Mockito.when(pcRepository.countById(anyString(), anyString())).thenReturn(0);
        Mockito.when(pcRepository.save(any())).thenReturn(1);
        pcService.savePC(testPc);
        Mockito.verify(pcRepository,Mockito.times(1)).countById(anyString(), anyString());
        Mockito.verify(pcRepository,Mockito.times(1)).save(any());

    }

    @Test
    @DisplayName("save pc -aready exist pc")
    void savePC_exist(){
        Mockito.when(pcRepository.countById(anyString(), anyString())).thenReturn(1);
        Throwable throwable = Assertions.assertThrows(ProductCategoryAlreadyExistsException.class,()->pcService.savePC(testPc));
        log.debug("error:{}",throwable.getMessage());
    }


    @Test
    @DisplayName("delete pc")
    void deletePC() {
        Mockito.when(pcRepository.delete(anyString())).thenReturn(1);
        Mockito.when(pcRepository.countByProductId(anyString())).thenReturn(1);

        pcService.deletePC(testPc.getProductId());

        Mockito.verify(pcRepository,Mockito.times(1)).delete(anyString());
        Mockito.verify(pcRepository,Mockito.times(1)).countByProductId(anyString());

    }
}
