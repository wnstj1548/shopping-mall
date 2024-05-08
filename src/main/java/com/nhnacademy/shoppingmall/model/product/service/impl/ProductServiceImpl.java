package com.nhnacademy.shoppingmall.model.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.model.product.service.ProductService;
import com.nhnacademy.shoppingmall.model.product.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(String productId) {
        Optional<Product> productOptional =  productRepository.findById(productId);
        return productOptional.orElse(null);
    }

    @Override
    public Page<Product> getAllProduct(int page, int pageSize) {
        return productRepository.getAllProduct(page, pageSize);
    }

    @Override
    public void updateProduct(Product product) {
        if(productRepository.countByProductId(product.getProductId()) > 0){
            productRepository.update(product);
        }
    }

    @Override
    public void deleteProduct(String productId) {
        if(productRepository.countByProductId(productId) <= 0){
            throw new ProductNotFoundException(productId);
        }
        productRepository.deleteByProductId(productId);
    }

    @Override
    public void saveProduct(Product product) {
        if(productRepository.countByProductId(product.getProductId()) > 0){
            throw new ProductAlreadyExistsException(product.getProductId());
        }
        productRepository.save(product);
    }

    @Override
    public Page<Product> getProductByCategoryId(int page, int pageSize, String categoryId) {
        return productRepository.findByCategoryId(page, pageSize, categoryId);
    }
}
