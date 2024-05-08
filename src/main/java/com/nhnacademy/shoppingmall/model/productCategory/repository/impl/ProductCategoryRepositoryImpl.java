package com.nhnacademy.shoppingmall.model.productCategory.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.productCategory.domain.ProductCategory;
import com.nhnacademy.shoppingmall.model.productCategory.repository.ProductCategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
    @Override
    public int save(ProductCategory pc) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO product_category VALUES (?, ?)";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, pc.getProductId());
            psmt.setString(2, pc.getCategoryId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM product_category WHERE product_id = ?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, productId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(ProductCategory pc) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE product_category SET product_id = ?, category_id = ? WHERE product_id = ?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, pc.getProductId());
            psmt.setString(2, pc.getCategoryId());
            psmt.setString(3, pc.getProductId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int countByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM product_category WHERE product_id = ?";
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, productId);
            rs = psmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int countById(String productId, String categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM product_category WHERE product_id = ? and category_id =? ";
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, productId);
            psmt.setString(2, categoryId);
            rs = psmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
