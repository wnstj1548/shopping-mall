package com.nhnacademy.shoppingmall.model.orderDetail.service.impl;

import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetail.exception.OrderDetailAlreadyExistException;
import com.nhnacademy.shoppingmall.model.orderDetail.repository.OrderDetailRepository;
import com.nhnacademy.shoppingmall.model.orderDetail.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OrderDetailServiceImplTest {
    OrderDetailRepository orderDetailRepository = Mockito.mock(OrderDetailRepository.class);
    OrderDetailService orderDetailService = new OrderDetailServiceImpl(orderDetailRepository);
    OrderDetail testOrderDetail = new OrderDetail("testOrderDetailId", 10000, 1, "testProductId", "testOrderId");

    @Test
    @DisplayName("getOrderDetail")
    void getOrderDetailById() {
        Mockito.when(orderDetailRepository.findById(anyString())).thenReturn(Optional.of(testOrderDetail));
        orderDetailService.getOrderDetail(testOrderDetail.getOrderDetailId());
    }

    @Test
    @DisplayName("getOrderDetail - orderDetail not found -> return null")
    void getOrderDetail_not_found(){
        Mockito.when(orderDetailRepository.findById(anyString())).thenReturn(Optional.empty());
        OrderDetail orderDetail = orderDetailService.getOrderDetail(testOrderDetail.getOrderDetailId());
        Assertions.assertNull(orderDetail);
    }

    @Test
    @DisplayName("save orderDetail")
    void saveOrderDetail() {

        Mockito.when(orderDetailRepository.countByOrderDetailId(anyString())).thenReturn(0);
        Mockito.when(orderDetailRepository.save(any())).thenReturn(1);
        orderDetailService.saveOrderDetail(testOrderDetail);
        Mockito.verify(orderDetailRepository,Mockito.times(1)).countByOrderDetailId(anyString());
        Mockito.verify(orderDetailRepository,Mockito.times(1)).save(any());

    }

    @Test
    @DisplayName("save orderDetail -already exist orderDetail")
    void saveOrderDetail_exist(){
        Mockito.when(orderDetailRepository.countByOrderDetailId(anyString())).thenReturn(1);
        Throwable throwable = Assertions.assertThrows(OrderDetailAlreadyExistException.class,()-> orderDetailService.saveOrderDetail(testOrderDetail));
        log.debug("error:{}",throwable.getMessage());
    }

    @Test
    @DisplayName("update orderDetail")
    void updateOrderDetail() {
        Mockito.when(orderDetailRepository.countByOrderDetailId(anyString())).thenReturn(1);
        Mockito.when(orderDetailRepository.update(testOrderDetail)).thenReturn(1);
        orderDetailService.updateOrderDetail(testOrderDetail);
        Mockito.verify(orderDetailRepository,Mockito.times(1)).update(any());
        Mockito.verify(orderDetailRepository,Mockito.times(1)).countByOrderDetailId(anyString());
    }

    @Test
    @DisplayName("delete orderDetail")
    void deleteOrderDetail() {
        Mockito.when(orderDetailRepository.deleteByOrderDetailId(anyString())).thenReturn(1);
        Mockito.when(orderDetailRepository.countByOrderDetailId(anyString())).thenReturn(1);

        orderDetailService.deleteOrderDetail(testOrderDetail.getOrderDetailId());

        Mockito.verify(orderDetailRepository,Mockito.times(1)).deleteByOrderDetailId(anyString());
        Mockito.verify(orderDetailRepository,Mockito.times(1)).deleteByOrderDetailId(anyString());

    }

    @Test
    @DisplayName("get orderDetailByOrderId")
    void getOrderDetailByOrderId() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(testOrderDetail);
        Mockito.when(orderDetailRepository.findByOrderId("testOrderId")).thenReturn(orderDetailList);
        List<OrderDetail> resultOrderDetailList = orderDetailService.getOrderDetailsByOrderId("testOrderId");
        Mockito.verify(orderDetailRepository, Mockito.times(1)).findByOrderId("testOrderId");
    }
}
