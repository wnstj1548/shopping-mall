package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class PointChannelRequest extends ChannelRequest {

    int point;
    String userId;

    public PointChannelRequest(int point, String userId) {
        this.point = point;
        this.userId = userId;
    }

    @Override
    public void execute() throws SQLException {
        DbConnectionThreadLocal.initialize();
        //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            addPoint(connection, point, userId);
        } finally {
            log.debug("pointChannel execute");
            DbConnectionThreadLocal.reset();
        }
    }

    private static void addPoint(Connection connection, int point, String userId) {
        String sql = "update users set user_point = ? where user_id =?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setInt(1, point);
            psmt.setString(2, userId);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
