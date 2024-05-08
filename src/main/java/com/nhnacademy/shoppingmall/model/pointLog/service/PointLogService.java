package com.nhnacademy.shoppingmall.model.pointLog.service;

import com.nhnacademy.shoppingmall.model.pointLog.domain.PointLog;
import com.nhnacademy.shoppingmall.model.product.domain.Product;

import java.util.List;

public interface PointLogService {
    PointLog getPointLog(String pointLogId);
    List<PointLog> getPointLogByUserId(String userId);
    void deletePointLog(String pointLogId);
    void savePointLog(PointLog pointLog);
}
