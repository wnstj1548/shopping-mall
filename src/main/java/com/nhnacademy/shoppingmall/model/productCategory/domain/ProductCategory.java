package com.nhnacademy.shoppingmall.model.productCategory.domain;

import java.util.Objects;

public class ProductCategory {
    private String productId;
    private String categoryId;

    public ProductCategory(String productId, String categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategory that = (ProductCategory) o;
        return Objects.equals(productId, that.productId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, categoryId);
    }

    @Override
    public String toString() {
        return "productCategory{" +
                "productId='" + productId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
