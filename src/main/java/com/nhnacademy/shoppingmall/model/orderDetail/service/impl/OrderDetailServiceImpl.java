package com.nhnacademy.shoppingmall.model.orderDetail.service.impl;

import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetail.exception.OrderDetailAlreadyExistException;
import com.nhnacademy.shoppingmall.model.orderDetail.exception.OrderDetailNotFoundException;
import com.nhnacademy.shoppingmall.model.orderDetail.repository.OrderDetailRepository;
import com.nhnacademy.shoppingmall.model.orderDetail.service.OrderDetailService;

import java.util.List;
import java.util.Optional;

public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetail getOrderDetail(String orderDetailId) {
        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(orderDetailId);
        return orderDetailOptional.orElse(null);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(String orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public void updateOrderDetail(OrderDetail orderDetail) {
        if(orderDetailRepository.countByOrderDetailId(orderDetail.getOrderDetailId()) <= 0) {
            throw new OrderDetailNotFoundException(orderDetail.getOrderDetailId());
        }
        orderDetailRepository.update(orderDetail);
    }

    @Override
    public void deleteOrderDetail(String orderDetailId) {
        if(orderDetailRepository.countByOrderDetailId(orderDetailId) <= 0) {
            throw new OrderDetailNotFoundException(orderDetailId);
        }
        orderDetailRepository.deleteByOrderDetailId(orderDetailId);
    }

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        if(orderDetailRepository.countByOrderDetailId(orderDetail.getOrderDetailId()) > 0) {
            throw new OrderDetailAlreadyExistException(orderDetail.getOrderDetailId());
        }
        orderDetailRepository.save(orderDetail);
    }
}
