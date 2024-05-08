package com.nhnacademy.shoppingmall.model.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.model.product.domain.Product;
import com.nhnacademy.shoppingmall.model.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findById(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from products where product_id= ?";

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, productId);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getInt("product_quantity"), rs.getString("product_image"), rs.getString("product_detail_image"), rs.getInt("product_original_price"), rs.getInt("product_sale_price"), rs.getString("product_content")));
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
    public Page<Product> getAllProduct(int page, int pageSize) {

        int offset = (page-1) * pageSize;
        int limit = pageSize;

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from products ORDER BY product_id LIMIT ?,?";

        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            List<Product> products = new ArrayList<>(pageSize);
            psmt.setInt(1,offset);
            psmt.setInt(2,limit);
            rs = psmt.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("product_id"), rs.getString("product_name"), rs.getInt("product_quantity"), rs.getString("product_image"),
                        rs.getString("product_detail_image"), rs.getInt("product_original_price"), rs.getInt("product_sale_price"), rs.getString("product_content"));
                products.add(product);
            }

            long total =0;

            if(!products.isEmpty()){
                total = totalCount();
            }

            return  new Page<Product> (products,total);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int save(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into products values (?,?,?,?,?,?,?,?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {

            psmt.setString(1, product.getProductId());
            psmt.setString(2, product.getProductName());
            psmt.setInt(3, product.getProductQuantity());
            psmt.setString(4, product.getProductImage());
            psmt.setString(5, product.getProductDetailImage());
            psmt.setInt(6, product.getProductOriginalPrice());
            psmt.setInt(7, product.getProductSalePrice());
            psmt.setString(8, product.getProductContent());

            int result = psmt.executeUpdate();
            log.info(String.valueOf(result));
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from products where product_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, productId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update products set product_name = ?, product_quantity = ?, product_image =? , product_detail_image = ?, product_original_price = ?, product_sale_price = ?, product_content = ? where product_id = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, product.getProductName());
            psmt.setInt(2, product.getProductQuantity());
            psmt.setString(3, product.getProductImage());
            psmt.setString(4, product.getProductDetailImage());
            psmt.setInt(5, product.getProductOriginalPrice());
            psmt.setInt(6, product.getProductSalePrice());
            psmt.setString(7, product.getProductContent());
            psmt.setString(8, product.getProductId());

            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(String productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from products where product_id = ?";
        ResultSet rs = null;

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, productId);
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
    public long totalCount() {
        //todo#4 totalCount 구현
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from products";
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            rs = psmt.executeQuery();
            if(rs.next()){
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if(Objects.nonNull(rs)) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0l;
    }

    @Override
    public Page<Product> findByCategoryId(int page, int pageSize, String categoryId) {

        int offset = (page-1) * pageSize;
        int limit = pageSize;

        Connection connection = DbConnectionThreadLocal.getConnection();
        ResultSet rs = null;

        String sql = "SELECT p.product_id, p.product_name, p.product_quantity, p.product_image, p.product_detail_image, " +
                "p.product_original_price, p.product_sale_price, p.product_content " +
                "FROM product_category pc " +
                "JOIN products p ON pc.product_id = p.product_id " +
                "WHERE pc.category_id = ? ORDER BY p.product_id LIMIT ?,?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, categoryId);
            psmt.setInt(2, offset);
            psmt.setInt(3, limit);
            rs = psmt.executeQuery();
            List<Product> products = new ArrayList<>(pageSize);

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("product_quantity"),
                        rs.getString("product_image"),
                        rs.getString("product_detail_image"),
                        rs.getInt("product_original_price"),
                        rs.getInt("product_sale_price"),
                        rs.getString("product_content")
                );
                products.add(product);
            }
            long total = 0;

            if(!products.isEmpty()) {
                total = totalCount();
            }

            return  new Page<Product>(products,total);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
