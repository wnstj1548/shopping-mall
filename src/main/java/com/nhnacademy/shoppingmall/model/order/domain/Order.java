package com.nhnacademy.shoppingmall.model.order.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {
    private String orderId;
    private double orderTotalPrice;
    private Timestamp orderDate;
    private String orderStatus;
    private String orderRefund;
    private String orderName;
    private String orderZipcode;
    private String orderAddress;
    private String orderDetailAddress;
    private String orderPhoneNumber;
    private String orderRequest;
    private String orderCount;
    private String userId;

    public Order(String orderId, double orderTotalPrice, Timestamp orderDate, String orderStatus, String orderRefund, String orderName, String orderZipcode, String orderAddress, String orderDetailAddress, String orderPhoneNumber, String orderRequest, String orderCount, String userId) {
        this.orderId = orderId;
        this.orderTotalPrice = orderTotalPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderRefund = orderRefund;
        this.orderName = orderName;
        this.orderZipcode = orderZipcode;
        this.orderAddress = orderAddress;
        this.orderDetailAddress = orderDetailAddress;
        this.orderPhoneNumber = orderPhoneNumber;
        this.orderRequest = orderRequest;
        this.orderCount = orderCount;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getOrderRefund() {
        return orderRefund;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getOrderZipcode() {
        return orderZipcode;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getOrderDetailAddress() {
        return orderDetailAddress;
    }

    public String getOrderPhoneNumber() {
        return orderPhoneNumber;
    }

    public String getOrderRequest() {
        return orderRequest;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderRefund(String orderRefund) {
        this.orderRefund = orderRefund;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setOrderZipcode(String orderZipcode) {
        this.orderZipcode = orderZipcode;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public void setOrderDetailAddress(String orderDetailAddress) {
        this.orderDetailAddress = orderDetailAddress;
    }

    public void setOrderPhoneNumber(String orderPhoneNumber) {
        this.orderPhoneNumber = orderPhoneNumber;
    }

    public void setOrderRequest(String orderRequest) {
        this.orderRequest = orderRequest;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(orderTotalPrice, order.orderTotalPrice) == 0 && Objects.equals(orderId, order.orderId) && Objects.equals(orderDate, order.orderDate) && Objects.equals(orderStatus, order.orderStatus) && Objects.equals(orderRefund, order.orderRefund) && Objects.equals(orderName, order.orderName) && Objects.equals(orderZipcode, order.orderZipcode) && Objects.equals(orderAddress, order.orderAddress) && Objects.equals(orderDetailAddress, order.orderDetailAddress) && Objects.equals(orderPhoneNumber, order.orderPhoneNumber) && Objects.equals(orderRequest, order.orderRequest) && Objects.equals(orderCount, order.orderCount) && Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, orderTotalPrice, orderDate, orderStatus, orderRefund, orderName, orderZipcode, orderAddress, orderDetailAddress, orderPhoneNumber, orderRequest, orderCount, userId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderRefund='" + orderRefund + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderZipcode='" + orderZipcode + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderDetailAddress='" + orderDetailAddress + '\'' +
                ", orderPhoneNumber='" + orderPhoneNumber + '\'' +
                ", orderRequest='" + orderRequest + '\'' +
                ", orderCount='" + orderCount + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
