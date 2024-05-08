package com.nhnacademy.shoppingmall.model.orderDetail.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.orderDetail.domain.OrderDetail;
import com.nhnacademy.shoppingmall.model.orderDetail.repository.OrderDetailRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {

    @Override
    public Optional<OrderDetail> findById(String orderDetailId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM orders_detail WHERE order_detail_id = ?";
        ResultSet resultSet = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, orderDetailId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return Optional.of(new OrderDetail(resultSet.getString("order_detail_id"), resultSet.getDouble("order_detail_price"), resultSet.getInt("order_detail_count"), resultSet.getString("product_id"), resultSet.getString("order_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM orders_detail WHERE order_detail_id = ?";
        ResultSet resultSet = null;
        List<OrderDetail> orderDetails = new ArrayList<>();

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, orderId);
            resultSet = psmt.executeQuery();
            if(resultSet.next()) {
                orderDetails.add(new OrderDetail(resultSet.getString("order_detail_id"), resultSet.getDouble("order_detail_price"), resultSet.getInt("order_detail_count"), resultSet.getString("product_id"), resultSet.getString("order_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return orderDetails;
    }

    @Override
    public int save(OrderDetail orderDetail) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into orders_detail values (?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, orderDetail.getOrderDetailId());
            preparedStatement.setDouble(2, orderDetail.getOrderDetailPrice());
            preparedStatement.setInt(3, orderDetail.getOrderDetailCount());
            preparedStatement.setString(4, orderDetail.getProductId());
            preparedStatement.setString(5, orderDetail.getOrderId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByOrderDetailId(String orderDetailId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from orders_detail where order_detail_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, orderDetailId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(OrderDetail orderDetail) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE orders_detail SET order_detail_price = ?, order_detail_count = ?, product_id = ?, order_id = ? WHERE order_detail_id = ?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setDouble(1, orderDetail.getOrderDetailPrice());
            psmt.setInt(2, orderDetail.getOrderDetailCount());
            psmt.setString(3, orderDetail.getProductId());
            psmt.setString(4, orderDetail.getOrderId());
            psmt.setString(5, orderDetail.getOrderDetailId());

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByOrderDetailId(String orderDetailId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from orders_detail where order_detail_id = ?";
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, orderDetailId);
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
