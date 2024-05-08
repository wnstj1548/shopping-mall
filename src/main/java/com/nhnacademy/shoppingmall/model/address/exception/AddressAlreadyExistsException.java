package com.nhnacademy.shoppingmall.model.address.exception;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException(String addressId){
        super(String.format("address already exist:%s", addressId));
    }
}
