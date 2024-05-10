package com.nhnacademy.shoppingmall.model.orderDetail.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Objects;

public class OrderDetail {
    @NotNull
    private String orderDetailId;

    @Positive
    @PositiveOrZero
    private double orderDetailPrice;

    @NotNull
    @PositiveOrZero
    private int orderDetailCount;

    @NotNull
    private String productId;

    @NotNull
    private String orderId;

    public OrderDetail(String orderDetailId, double orderDetailPrice, int orderDetailCount, String productId, String orderId) {
        this.orderDetailId = orderDetailId;
        this.orderDetailPrice = orderDetailPrice;
        this.orderDetailCount = orderDetailCount;
        this.productId = productId;
        this.orderId = orderId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public double getOrderDetailPrice() {
        return orderDetailPrice;
    }

    public int getOrderDetailCount() {
        return orderDetailCount;
    }

    public String getProductId() {
        return productId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setOrderDetailPrice(double orderDetailPrice) {
        this.orderDetailPrice = orderDetailPrice;
    }

    public void setOrderDetailCount(int orderDetailCount) {
        this.orderDetailCount = orderDetailCount;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Double.compare(orderDetailPrice, that.orderDetailPrice) == 0 && orderDetailCount == that.orderDetailCount && Objects.equals(orderDetailId, that.orderDetailId) && Objects.equals(productId, that.productId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDetailId, orderDetailPrice, orderDetailCount, productId, orderId);
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId='" + orderDetailId + '\'' +
                ", orderDetailPrice=" + orderDetailPrice +
                ", orderDetailCount=" + orderDetailCount +
                ", productId='" + productId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
