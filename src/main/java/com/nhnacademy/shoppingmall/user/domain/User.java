package com.nhnacademy.shoppingmall.user.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    public enum Auth{
        ROLE_ADMIN,ROLE_USER
    }

    @NotNull
    private String userId;

    @NotNull
    private String userName;

    @NotNull
    @Size(min = 8, max = 20, message="비밀번호는 8자 이상이어야 합니다.")
    private String userPassword;

    @NotNull
    @Size(min = 8, max = 8, message="생년월일은 8자입니다.")
    private String userBirth;

    @NotNull
    private Auth userAuth;

    @PositiveOrZero
    @NotNull
    private int userPoint;

    @PastOrPresent
    @NotNull
    private LocalDateTime createdAt;

    @PastOrPresent
    private LocalDateTime latestLoginAt;

    public User (String userId, String userName, String userPassword, String userBirth, Auth userAuth, int userPoint, LocalDateTime createdAt, LocalDateTime latestLoginAt ){
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userAuth = userAuth;
        this.userPoint = userPoint;
        this.createdAt = createdAt;
        this.latestLoginAt=latestLoginAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public Auth getUserAuth() {
        return userAuth;
    }

    public int getUserPoint() {
        return userPoint;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLatestLoginAt() {
        return latestLoginAt;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public void setUserAuth(Auth userAuth) {
        this.userAuth = userAuth;
    }

    public void setUserPoint(int userPoint) {
        this.userPoint = userPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userPoint == user.userPoint &&
                Objects.equals(userId, user.userId) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userPassword, user.userPassword) &&
                Objects.equals(userBirth, user.userBirth) &&
                userAuth == user.userAuth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword, userBirth, userAuth, userPoint);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userAuth=" + userAuth +
                ", userPoint=" + userPoint +
                ", createdAt=" + createdAt +
                ", latestLoginAt=" + latestLoginAt +
                '}';
    }
}