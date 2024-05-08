package com.nhnacademy.shoppingmall.model.address.service;

import com.nhnacademy.shoppingmall.model.address.domain.Address;

import java.util.List;

public interface AddressService {
    Address getAddress(String addressId);

    List<Address> getAddressByUserId(String userId);

    void updateAddress(Address address);

    void deleteAddress(String addressId);

    void saveAddress(Address address);
}
