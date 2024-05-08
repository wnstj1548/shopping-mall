package com.nhnacademy.shoppingmall.user.exception;

public class UserRegisterDifferentPasswordException extends RuntimeException {
    public UserRegisterDifferentPasswordException(){
        super(String.format("비밀번호와 비밀번호 확인이 일치하지 않습니다."));
    }
}
