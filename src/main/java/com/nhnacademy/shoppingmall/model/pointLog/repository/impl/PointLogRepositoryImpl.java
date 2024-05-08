package com.nhnacademy.shoppingmall.model.pointLog.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.pointLog.domain.PointLog;
import com.nhnacademy.shoppingmall.model.pointLog.repository.PointLogRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PointLogRepositoryImpl implements PointLogRepository {
    @Override
    public Optional<PointLog> findById(String pointLogId) {

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM point_log WHERE point_log_id = ?";
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, pointLogId);
            rs = psmt.executeQuery();
            if(rs.next()) {
                return Optional.of(new PointLog(rs.getString("point_log_id"), rs.getTimestamp("point_date"), rs.getInt("point"), rs.getString("userId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<PointLog> findByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM point_log WHERE user_id = ?";
        ResultSet rs = null;
        List<PointLog> pointLogs = new ArrayList<>();

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            while(rs.next()) {
                PointLog pointLog = new PointLog(rs.getString("point_log_id"), rs.getTimestamp("point_date"), rs.getInt("point"), rs.getString("user_id"));
                pointLogs.add(pointLog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return pointLogs;
    }

    @Override
    public int save(PointLog poingLog) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into point_log values (?,?,?,?) ";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, poingLog.getPointLogId());
            psmt.setTimestamp(2, poingLog.getPointDate());
            psmt.setInt(3, poingLog.getPoint());
            psmt.setString(4, poingLog.getUserId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByPointLogId(String pointLogId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from point_log where point_log_id = ? ";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, pointLogId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByPointLogId(String pointLogId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from point_log where point_log_id = ?";
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, pointLogId);
            rs = psmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }
}
