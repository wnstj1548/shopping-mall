package com.nhnacademy.shoppingmall.model.address.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.model.address.domain.Address;
import com.nhnacademy.shoppingmall.model.address.repository.AddressRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressRepositoryImpl implements AddressRepository {
    @Override
    public Optional<Address> findById(String addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM address WHERE address_id = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, addressId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(
                        new Address(resultSet.getString("addressId"),
                                resultSet.getString("address"),
                                resultSet.getString("detailAddress"),
                                resultSet.getString("zipcode"),
                                resultSet.getString("personName"),
                                resultSet.getString("personPhoneNumber"),
                                resultSet.getString("userId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Address> findByUserId(String userId) {

        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "SELECT * FROM address WHERE user_id = ?";
        ResultSet resultSet = null;
        List<Address> addressList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                addressList.add(
                        new Address(resultSet.getString("addressId"),
                                resultSet.getString("address"),
                                resultSet.getString("detailAddress"),
                                resultSet.getString("zipcode"),
                                resultSet.getString("personName"),
                                resultSet.getString("personPhoneNumber"),
                                resultSet.getString("userId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return addressList;
    }

    @Override
    public int save(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into address values (?,?,?,?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, address.getAddressId());
            preparedStatement.setString(2, address.getAddress());
            preparedStatement.setString(3, address.getDetailAddress());
            preparedStatement.setString(4, address.getZipcode());
            preparedStatement.setString(5, address.getPersonName());
            preparedStatement.setString(6, address.getPersonPhoneNumber());
            preparedStatement.setString(7, address.getUserId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByAddressId(String addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from address where address_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, addressId);

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Address address) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update address set address = ?, detail_address = ? , zipcode = ?, person_name = ?, person_phone_number = ?, user_id =? where address_id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, address.getAddress());
            preparedStatement.setString(2, address.getDetailAddress());
            preparedStatement.setString(3, address.getZipcode());
            preparedStatement.setString(4, address.getPersonName());
            preparedStatement.setString(5, address.getPersonPhoneNumber());
            preparedStatement.setString(6, address.getUserId());
            preparedStatement.setString(7, address.getAddressId());

            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int countByAddressId(String addressId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from address where address_id = ?";
        ResultSet rs = null;

        try(PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, addressId);
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
