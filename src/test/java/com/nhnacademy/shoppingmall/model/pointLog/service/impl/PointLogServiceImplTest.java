package com.nhnacademy.shoppingmall.model.pointLog.service.impl;

import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetail.exception.OrderDetailAlreadyExistException;
import com.nhnacademy.shoppingmall.model.pointLog.domain.PointLog;
import com.nhnacademy.shoppingmall.model.pointLog.exception.PointLogAlreadyExistsExeption;
import com.nhnacademy.shoppingmall.model.pointLog.repository.PointLogRepository;
import com.nhnacademy.shoppingmall.model.pointLog.service.PointLogService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class PointLogServiceImplTest {

    PointLogRepository pointLogRepository = Mockito.mock(PointLogRepository.class);
    PointLogService pointLogService = new PointLogServiceImpl(pointLogRepository);
    PointLog testPointLog = new PointLog("testPointLogId", Timestamp.valueOf(LocalDateTime.now()), 100, "testOrderId");

    @Test
    @DisplayName("getOrderDetail")
    void getOrderDetailById() {
        Mockito.when(pointLogRepository.findById(anyString())).thenReturn(Optional.of(testPointLog));
        pointLogService.getPointLog(testPointLog.getPointLogId());
    }

    @Test
    @DisplayName("getOrderDetail - orderDetail not found -> return null")
    void getOrderDetail_not_found(){
        Mockito.when(pointLogRepository.findById(anyString())).thenReturn(Optional.empty());
        PointLog pointLog = pointLogService.getPointLog(testPointLog.getPointLogId());
        Assertions.assertNull(pointLog);
    }

    @Test
    @DisplayName("save orderDetail")
    void saveOrderDetail() {

        Mockito.when(pointLogRepository.countByPointLogId(anyString())).thenReturn(0);
        Mockito.when(pointLogRepository.save(any())).thenReturn(1);
        pointLogService.savePointLog(testPointLog);
        Mockito.verify(pointLogRepository,Mockito.times(1)).countByPointLogId(anyString());
        Mockito.verify(pointLogRepository,Mockito.times(1)).save(any());

    }

    @Test
    @DisplayName("save orderDetail -already exist orderDetail")
    void saveOrderDetail_exist(){
        Mockito.when(pointLogRepository.countByPointLogId(anyString())).thenReturn(1);
        Throwable throwable = Assertions.assertThrows(PointLogAlreadyExistsExeption.class,()-> pointLogService.savePointLog(testPointLog));
        log.debug("error:{}",throwable.getMessage());
    }


    @Test
    @DisplayName("delete orderDetail")
    void deleteOrderDetail() {
        Mockito.when(pointLogRepository.deleteByPointLogId(anyString())).thenReturn(1);
        Mockito.when(pointLogRepository.countByPointLogId(anyString())).thenReturn(1);

        pointLogService.deletePointLog(testPointLog.getPointLogId());

        Mockito.verify(pointLogRepository,Mockito.times(1)).deleteByPointLogId(anyString());
        Mockito.verify(pointLogRepository,Mockito.times(1)).deleteByPointLogId(anyString());

    }

    @Test
    @DisplayName("get orderDetailByOrderId")
    void getOrderDetailByOrderId() {
        List<PointLog> pointLogList = new ArrayList<>();
        pointLogList.add(testPointLog);
        Mockito.when(pointLogRepository.findByUserId("testUserId")).thenReturn(pointLogList);
        List<PointLog> resultPointLogByUserId = pointLogService.getPointLogByUserId("testOrderId");
        Mockito.verify(pointLogRepository, Mockito.times(1)).findByUserId("testOrderId");
    }
}
