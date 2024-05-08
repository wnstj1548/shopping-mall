package com.nhnacademy.shoppingmall.model.address.domain;

import java.util.Objects;

public class Address {
    private String addressId;
    private String address;
    private String detailAddress;
    private String zipcode;
    private String personName;
    private String personPhoneNumber;
    private String userId;

    public Address(String userId, String personPhoneNumber, String personName, String zipcode, String detailAddress, String address, String addressId) {
        this.userId = userId;
        this.personPhoneNumber = personPhoneNumber;
        this.personName = personName;
        this.zipcode = zipcode;
        this.detailAddress = detailAddress;
        this.address = address;
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonPhoneNumber() {
        return personPhoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId) && Objects.equals(address, address1.address) && Objects.equals(detailAddress, address1.detailAddress) && Objects.equals(zipcode, address1.zipcode) && Objects.equals(personName, address1.personName) && Objects.equals(personPhoneNumber, address1.personPhoneNumber) && Objects.equals(userId, address1.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, detailAddress, zipcode, personName, personPhoneNumber, userId);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", personName='" + personName + '\'' +
                ", personPhoneNumber='" + personPhoneNumber + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
