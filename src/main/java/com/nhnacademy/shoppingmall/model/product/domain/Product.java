package com.nhnacademy.shoppingmall.model.product.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

public class Product {
    @NotNull
    private String productId;

    @NotNull
    private String productName;

    @PositiveOrZero
    @NotNull
    private int productQuantity;

    @NotNull
    private String productImage;

    @NotNull
    private String productDetailImage;

    @Positive
    @NotNull
    private int productOriginalPrice;

    @Positive
    @NotNull
    private int productSalePrice;

    @NotNull
    private String productContent;

    public Product(String productId, String productName, int productQuantity, String productImage, String productDetailImage, int productOriginalPrice, int productSalePrice, String productContent) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productImage = productImage;
        this.productDetailImage = productDetailImage;
        this.productOriginalPrice = productOriginalPrice;
        this.productSalePrice = productSalePrice;
        this.productContent = productContent;
    }

    public String getProductContent() {
        return productContent;
    }

    public int getProductSalePrice() {
        return productSalePrice;
    }

    public int getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public String getProductDetailImage() {
        return productDetailImage;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductDetailImage(String productDetailImage) {
        this.productDetailImage = productDetailImage;
    }

    public void setProductOriginalPrice(int productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
    }

    public void setProductSalePrice(int productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productQuantity == product.productQuantity && productOriginalPrice == product.productOriginalPrice && productSalePrice == product.productSalePrice && Objects.equals(productId, product.productId) && Objects.equals(productName, product.productName) && Objects.equals(productImage, product.productImage) && Objects.equals(productDetailImage, product.productDetailImage) && Objects.equals(productContent, product.productContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productQuantity, productImage, productDetailImage, productOriginalPrice, productSalePrice, productContent);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productImage='" + productImage + '\'' +
                ", productDetailImage='" + productDetailImage + '\'' +
                ", productOriginalPrice=" + productOriginalPrice +
                ", productSalePrice=" + productSalePrice +
                ", productContent='" + productContent + '\'' +
                '}';
    }
}
