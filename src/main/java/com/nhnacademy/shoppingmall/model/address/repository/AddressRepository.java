package com.nhnacademy.shoppingmall.model.address.repository;

import com.nhnacademy.shoppingmall.model.address.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository{
    Optional<Address> findById(String addressId);
    List<Address> findByUserId(String userId);
    int save(Address address);
    int deleteByAddressId(String addressId);
    int update(Address address);
    int countByAddressId(String addressId);
}
