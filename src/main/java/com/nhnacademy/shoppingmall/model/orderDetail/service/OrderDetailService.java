package com.nhnacademy.shoppingmall.model.orderDetail.service;

import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.product.domain.Product;

import java.util.List;

public interface OrderDetailService {
    OrderDetail getOrderDetail(String orderDetailId);
    List<OrderDetail> getOrderDetailsByOrderId(String orderId);
    void updateOrderDetail(OrderDetail orderDetail);
    void deleteOrderDetail(String orderDetailId);
    void saveOrderDetail(OrderDetail orderDetail);
}
