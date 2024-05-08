package com.nhnacademy.shoppingmall.model.pointLog.repository;

import com.nhnacademy.shoppingmall.model.pointLog.domain.PointLog;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface PointLogRepository {
    Optional<PointLog> findById(String pointLogId);
    List<PointLog> findByUserId(String userId);
    int save(PointLog poingLog);
    int deleteByPointLogId(String pointLogId);
    int countByPointLogId(String pointLogId);
}
