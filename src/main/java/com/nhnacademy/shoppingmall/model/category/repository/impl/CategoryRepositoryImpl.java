package com.nhnacademy.shoppingmall.model.category.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.category.domain.Category;
import com.nhnacademy.shoppingmall.model.category.repository.CategoryRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public Optional<Category> findById(String categoryId) {
        Connection connecton = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM category WHERE category_id = ?";
        ResultSet rs = null;

        try (PreparedStatement psmt = connecton.prepareStatement(sql)) {
            psmt.setString(1, categoryId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Category(categoryId, rs.getString("category_name")));
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
    public List<Category> findAll() {
        Connection connecton = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM category";
        List<Category> categories = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement psmt = connecton.prepareStatement(sql)) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("category_id"), rs.getString("category_name"));
                categories.add(category);
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
        return categories;
    }

    @Override
    public int save(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "INSERT INTO category(category_id, category_name) VALUES(?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, category.getCategoryId());
            psmt.setString(2, category.getCategoryName());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(String categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "DELETE FROM category WHERE category_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, categoryId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "UPDATE category SET category_name = ? WHERE category_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, category.getCategoryName());
            psmt.setString(2, category.getCategoryId());
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countById(String categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT COUNT(*) FROM category WHERE category_id = ?";
        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, categoryId);
            rs = psmt.executeQuery();
            if (rs.next()) {
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
    public List<Category> findByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        List<Category> categories = new ArrayList<>();
        ResultSet rs = null;

        String sql = "SELECT c.category_id, c.category_name " +
                "FROM product_category pc " +
                "JOIN category c ON pc.category_id = c.category_id " +
                "WHERE pc.product_id = ?";

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, productId);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getString("category_id"), rs.getString("category_name"));
                categories.add(category);
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

        return categories;
    }
}
