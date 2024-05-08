package com.nhnacademy.shoppingmall.model.pointLog.service.impl;

import com.nhnacademy.shoppingmall.model.pointLog.domain.PointLog;
import com.nhnacademy.shoppingmall.model.pointLog.exception.PointLogAlreadyExistsExeption;
import com.nhnacademy.shoppingmall.model.pointLog.exception.PointLogNotFoundException;
import com.nhnacademy.shoppingmall.model.pointLog.repository.PointLogRepository;
import com.nhnacademy.shoppingmall.model.pointLog.service.PointLogService;

import java.util.List;
import java.util.Optional;

public class PointLogServiceImpl implements PointLogService {

    private final PointLogRepository pointLogRepository;

    public PointLogServiceImpl(PointLogRepository pointLogRepository) {
        this.pointLogRepository = pointLogRepository;
    }

    @Override
    public PointLog getPointLog(String pointLogId) {
        Optional<PointLog> pointLogOptional = pointLogRepository.findById(pointLogId);
        return pointLogOptional.orElse(null);
    }

    @Override
    public List<PointLog> getPointLogByUserId(String userId) {
        return pointLogRepository.findByUserId(userId);
    }

    @Override
    public void deletePointLog(String pointLogId) {
        if(pointLogRepository.countByPointLogId(pointLogId) <= 0) {
            throw new PointLogNotFoundException(pointLogId);
        }
        pointLogRepository.deleteByPointLogId(pointLogId);
    }

    @Override
    public void savePointLog(PointLog pointLog) {
        if(pointLogRepository.countByPointLogId(pointLog.getPointLogId()) > 0) {
            throw new PointLogAlreadyExistsExeption(pointLog.getPointLogId());
        }
        pointLogRepository.save(pointLog);
    }
}
