package com.nhnacademy.shoppingmall.model.order.service;

import com.nhnacademy.shoppingmall.model.order.domain.Order;
import com.nhnacademy.shoppingmall.user.domain.User;

import java.util.List;

public interface OrderService {
    Order getOrder(String orderId);

    List<Order> getOrderByUserId(String userId);

    void saveOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(String orderId);

    List<Order> getAllOrders();

}
