package com.nhnacademy.shoppingmall.model.order.service.impl;

import com.nhnacademy.shoppingmall.model.order.domain.Order;
import com.nhnacademy.shoppingmall.model.order.exception.OrderAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.model.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    OrderService orderService = new OrderServiceImpl(orderRepository);
    Order testOrder = new Order("testOrderId", 10000, Timestamp.valueOf(LocalDateTime.now()), "주문 처리", null, "testOrderName", "testZipcode", "testOrderAddress", "testDetail", "testPhone", "testRequest", 1, "testUserId");

    @Test
    @DisplayName("save order")
    void saveOrder() {

        Mockito.when(orderRepository.countByOrderId(anyString())).thenReturn(0);
        Mockito.when(orderRepository.save(any())).thenReturn(1);
        orderService.saveOrder(testOrder);
        Mockito.verify(orderRepository,Mockito.times(1)).countByOrderId(anyString());
        Mockito.verify(orderRepository,Mockito.times(1)).save(any());

    }

    @Test
    @DisplayName("save order -already exist order")
    void saveOrder_exist(){
        Mockito.when(orderRepository.countByOrderId(anyString())).thenReturn(1);
        Throwable throwable = Assertions.assertThrows(OrderAlreadyExistsException.class,()-> orderService.saveOrder(testOrder));
        log.debug("error:{}",throwable.getMessage());
    }

    @Test
    @DisplayName("update order")
    void updateOrder() {
        Mockito.when(orderRepository.countByOrderId(anyString())).thenReturn(1);
        Mockito.when(orderRepository.update(testOrder)).thenReturn(1);
        orderService.updateOrder(testOrder);
        Mockito.verify(orderRepository,Mockito.times(1)).update(any());
        Mockito.verify(orderRepository,Mockito.times(1)).countByOrderId(anyString());
    }

    @Test
    @DisplayName("delete order")
    void deleteOrder() {
        Mockito.when(orderRepository.deleteByOrderId(anyString())).thenReturn(1);
        Mockito.when(orderRepository.countByOrderId(anyString())).thenReturn(1);

        orderService.deleteOrder(testOrder.getOrderId());

        Mockito.verify(orderRepository,Mockito.times(1)).deleteByOrderId(anyString());
        Mockito.verify(orderRepository,Mockito.times(1)).countByOrderId(anyString());

    }

    @Test
    @DisplayName("get all order")
    void getAllOrder() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(testOrder);
        Mockito.when(orderRepository.findAll()).thenReturn(orderList);
        List<Order> resultOrderList = orderService.getAllOrders();
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("getOrderByUserId")
    void getOrderByUserId() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(testOrder);
        Mockito.when(orderRepository.findByUserId(anyString())).thenReturn(orderList);
        List<Order> orderResult = orderService.getOrderByUserId("testUserId");
        Mockito.verify(orderRepository, Mockito.times(1)).findByUserId("testUserId");
    }
}
