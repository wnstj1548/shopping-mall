package com.nhnacademy.shoppingmall.model.order.repository;

import com.nhnacademy.shoppingmall.model.order.domain.Order;
import com.nhnacademy.shoppingmall.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(String orderId);
    List<Order> findByUserId(String userId);
    int save(Order order);
    int deleteByOrderId(String orderId);
    int update(Order order);
    int countByOrderId(String orderId);
}
