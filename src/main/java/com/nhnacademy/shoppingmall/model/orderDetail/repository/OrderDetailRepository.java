package com.nhnacademy.shoppingmall.model.orderDetail.repository;

import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.user.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository {
    Optional<OrderDetail> findById(String orderDetailId);
    List<OrderDetail> findByOrderId(String orderId);
    int save(OrderDetail orderDetail);
    int deleteByOrderDetailId(String orderDetailId);
    int update(OrderDetail orderDetail);
    int countByOrderDetailId(String orderDetailId);
}
