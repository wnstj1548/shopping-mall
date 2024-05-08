package com.nhnacademy.shoppingmall.model.cart.domain;

import java.util.Objects;

public class Cart {
    private String cartId;
    private int cartCount;
    private String productId;
    private String userId;

    public Cart(String cartId, int cartCount, String productId, String userId) {
        this.cartId = cartId;
        this.cartCount = cartCount;
        this.productId = productId;
        this.userId = userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public int getCartCount() {
        return cartCount;
    }

    public void setCartCount(int cartCount) {
        this.cartCount = cartCount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return cartCount == cart.cartCount && Objects.equals(cartId, cart.cartId) && Objects.equals(productId, cart.productId) && Objects.equals(userId, cart.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, cartCount, productId, userId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                ", cartCount=" + cartCount +
                ", productId='" + productId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
