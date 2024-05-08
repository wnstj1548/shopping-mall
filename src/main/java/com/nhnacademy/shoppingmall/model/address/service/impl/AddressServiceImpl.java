package com.nhnacademy.shoppingmall.model.address.service.impl;

import com.nhnacademy.shoppingmall.model.address.domain.Address;
import com.nhnacademy.shoppingmall.model.address.exception.AddressAlreadyExistsException;
import com.nhnacademy.shoppingmall.model.address.exception.AddressNotFoundException;
import com.nhnacademy.shoppingmall.model.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.model.address.service.AddressService;

import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getAddress(String addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        return addressOptional.orElse(null);
    }

    @Override
    public List<Address> getAddressByUserId(String userId) {
        return addressRepository.findByUserId(userId);
    }

    @Override
    public void updateAddress(Address address) {
        if(addressRepository.countByAddressId(address.getAddressId()) <= 0) {
            throw new AddressNotFoundException(address.getAddressId());
        }

        addressRepository.update(address);
    }

    @Override
    public void deleteAddress(String addressId) {
        if(addressRepository.countByAddressId(addressId) <= 0) {
            throw new AddressNotFoundException(addressId);
        }

        addressRepository.deleteByAddressId(addressId);
    }

    @Override
    public void saveAddress(Address address) {
        if(addressRepository.countByAddressId(address.getAddressId()) > 0) {
            throw new AddressAlreadyExistsException(address.getAddressId());
        }

        addressRepository.save(address);
    }
}
