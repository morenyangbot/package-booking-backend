package com.oocl.packagebooking.enums;

public enum PackageEnum {

    PENDING("PENDING"),
    RESERVING("RESERVING"),
    FINISHED("FINISHED");

    private String statusName;

    PackageEnum(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
