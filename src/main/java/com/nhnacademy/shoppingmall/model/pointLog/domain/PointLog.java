package com.nhnacademy.shoppingmall.model.pointLog.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class PointLog {
    private String pointLogId;
    private Timestamp pointDate;
    private int point;
    private String userId;

    public PointLog(String pointLogId, Timestamp pointDate, int point, String userId) {
        this.pointLogId = pointLogId;
        this.pointDate = pointDate;
        this.point = point;
        this.userId = userId;
    }

    public String getPointLogId() {
        return pointLogId;
    }

    public void setPointLogId(String pointLogId) {
        this.pointLogId = pointLogId;
    }

    public Timestamp getPointDate() {
        return pointDate;
    }

    public void setPointDate(Timestamp pointDate) {
        this.pointDate = pointDate;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointLog pointLog = (PointLog) o;
        return point == pointLog.point && Objects.equals(pointLogId, pointLog.pointLogId) && Objects.equals(pointDate, pointLog.pointDate) && Objects.equals(userId, pointLog.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointLogId, pointDate, point, userId);
    }

    @Override
    public String toString() {
        return "PointLog{" +
                "pointLogId='" + pointLogId + '\'' +
                ", pointDate=" + pointDate +
                ", point=" + point +
                ", userId='" + userId + '\'' +
                '}';
    }
}
