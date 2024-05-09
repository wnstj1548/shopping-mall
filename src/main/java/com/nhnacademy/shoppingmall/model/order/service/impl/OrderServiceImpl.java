package com.nhnacademy.shoppingmall.model.order.service.impl;

import com.nhnacademy.shoppingmall.model.order.domain.Order;
import com.nhnacademy.shoppingmall.model.order.exception.OrderAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.order.exception.OrderNotFoundException;
import com.nhnacademy.shoppingmall.model.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.model.order.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order getOrder(String orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElse(null);
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void saveOrder(Order order) {
        if(orderRepository.countByOrderId(order.getOrderId()) > 0) {
            throw new OrderAlreadyExistsException(order.getOrderId());
        }
        orderRepository.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        if(orderRepository.countByOrderId(order.getOrderId()) <= 0) {
            throw new OrderNotFoundException(order.getOrderId());
        }
        orderRepository.update(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        if(orderRepository.countByOrderId(orderId) <= 0) {
            throw new OrderNotFoundException(orderId);
        }
        orderRepository.deleteByOrderId(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
