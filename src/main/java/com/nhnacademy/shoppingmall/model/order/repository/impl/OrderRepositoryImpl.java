package com.nhnacademy.shoppingmall.model.order.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.order.domain.Order;
import com.nhnacademy.shoppingmall.model.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Optional<Order> findById(String orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="select * from orders where order_id= ?";

        log.debug("sql:{}",sql);
        ResultSet rs = null;

        try( PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, orderId);
            rs = psmt.executeQuery();
            if(rs.next()){
                Order order = new Order(
                        rs.getString("order_id"),
                        rs.getDouble("order_total_price"),
                        rs.getTimestamp("order_date"),
                        Objects.nonNull(rs.getString("order_status")) ? rs.getString("order_status") : null,
                        Objects.nonNull(rs.getString("order_refund")) ? rs.getString("order_refund") : null,
                        rs.getString("order_name"),
                        rs.getString("order_zipcode"),
                        rs.getString("order_address"),
                        rs.getString("order_detail_address"),
                        rs.getString("order_phone_number"),
                        Objects.nonNull(rs.getString("order_request")) ? rs.getString("order_request") : null,
                        Objects.nonNull(rs.getString("order_count")) ? rs.getInt("order_count") : null,
                        rs.getString("user_id")
                );
                return Optional.of(order);
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
    public List<Order> findByUserId(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="select * from orders where user_id= ?";
        List<Order> orders = new ArrayList <>();
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            rs = psmt.executeQuery();
            while(rs.next()){
                Order order = new Order(
                        rs.getString("order_id"),
                        rs.getDouble("order_total_price"),
                        rs.getTimestamp("order_date"),
                        Objects.nonNull(rs.getString("order_status")) ? rs.getString("order_status") : null,
                        Objects.nonNull(rs.getString("order_refund")) ? rs.getString("order_refund") : null,
                        rs.getString("order_name"),
                        rs.getString("order_zipcode"),
                        rs.getString("order_address"),
                        rs.getString("order_detail_address"),
                        rs.getString("order_phone_number"),
                        Objects.nonNull(rs.getString("order_request")) ? rs.getString("order_request") : null,
                        Objects.nonNull(rs.getString("order_count")) ? rs.getInt("order_count") : null,
                        rs.getString("user_id")
                );
                orders.add(order);
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
        return orders;
    }

    @Override
    public int save(Order order) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into orders values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
                    psmt.setString(1,order.getOrderId());
                    psmt.setDouble(2,order.getOrderTotalPrice());
                    psmt.setTimestamp(3,order.getOrderDate());
                    psmt.setString(4, order.getOrderStatus());
                    psmt.setString(5, order.getOrderRefund());
                    psmt.setString(6,order.getOrderName());
                    psmt.setString(7,order.getOrderZipcode());
                    psmt.setString(8,order.getOrderAddress());
                    psmt.setString(9,order.getOrderDetailAddress());
                    psmt.setString(10,order.getOrderPhoneNumber());
                    psmt.setString(11,order.getOrderRequest());
                    psmt.setInt(12,order.getOrderCount());
                    psmt.setString(13,order.getUserId());

                    return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByOrderId(String orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="delete from orders where order_id= ?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, orderId);

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Order order) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE orders SET order_total_price = ?, order_date = ?, order_status = ?, order_refund = ?, order_name = ?,order_zipcode = ?, order_address = ?, order_detail_address = ?, order_phone_number = ?, order_request = ?, order_count = ?, user_id = ? WHERE order_id = ?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setDouble(1,order.getOrderTotalPrice());
            psmt.setTimestamp(2,order.getOrderDate());
            psmt.setString(3, order.getOrderStatus());
            psmt.setString(4, order.getOrderRefund());
            psmt.setString(5,order.getOrderName());
            psmt.setString(6,order.getOrderZipcode());
            psmt.setString(7,order.getOrderAddress());
            psmt.setString(8,order.getOrderDetailAddress());
            psmt.setString(9,order.getOrderPhoneNumber());
            psmt.setString(10,order.getOrderRequest());
            psmt.setInt(11,order.getOrderCount());
            psmt.setString(12,order.getUserId());
            psmt.setString(13,order.getOrderId());

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByOrderId(String orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from orders where order_id= ?";
        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, orderId);
            rs = psmt.executeQuery();
            if(rs.next()){
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

    @Override
    public List<Order> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql ="select * from orders";
        List<Order> orders = new ArrayList <>();
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            rs = psmt.executeQuery();
            while(rs.next()){
                Order order = new Order(
                        rs.getString("order_id"),
                        rs.getDouble("order_total_price"),
                        rs.getTimestamp("order_date"),
                        Objects.nonNull(rs.getString("order_status")) ? rs.getString("order_status") : null,
                        Objects.nonNull(rs.getString("order_refund")) ? rs.getString("order_refund") : null,
                        rs.getString("order_name"),
                        rs.getString("order_zipcode"),
                        rs.getString("order_address"),
                        rs.getString("order_detail_address"),
                        rs.getString("order_phone_number"),
                        Objects.nonNull(rs.getString("order_request")) ? rs.getString("order_request") : null,
                        Objects.nonNull(rs.getString("order_count")) ? rs.getInt("order_count") : null,
                        rs.getString("user_id")
                );
                orders.add(order);
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
        return orders;
    }

}
