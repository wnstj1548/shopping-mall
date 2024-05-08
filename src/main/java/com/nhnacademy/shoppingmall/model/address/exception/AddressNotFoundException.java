package com.nhnacademy.shoppingmall.model.address.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String addressId){
        super(String.format("address not found :%s", addressId));
    }
}
