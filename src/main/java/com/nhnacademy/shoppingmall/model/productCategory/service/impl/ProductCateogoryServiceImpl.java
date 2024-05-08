package com.nhnacademy.shoppingmall.model.productCategory.service.impl;

import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;
import com.nhnacademy.shoppingmall.model.productCategory.exception.ProductCategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.productCategory.exception.ProductCategoryNotFoundException;
import com.nhnacademy.shoppingmall.model.productCategory.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.model.productCategory.service.ProductCategoryService;


public class ProductCateogoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCateogoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public void updatePC(ProductCategory pc) {
        if(productCategoryRepository.countByProductId(pc.getProductId()) <= 0) {
            throw new ProductCategoryNotFoundException(pc.getProductId());
        }
        productCategoryRepository.update(pc);
    }

    @Override
    public void deletePC(String productId) {
        productCategoryRepository.delete(productId);
    }

    @Override
    public void savePC(ProductCategory pc) {
        if(productCategoryRepository.countById(pc.getProductId(), pc.getCategoryId()) > 0) {
            throw new ProductCategoryAlreadyExistsException(pc);
        }
        productCategoryRepository.save(pc);
    }
}
