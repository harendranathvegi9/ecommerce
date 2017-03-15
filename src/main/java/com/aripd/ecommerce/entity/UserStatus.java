package com.aripd.ecommerce.entity;

public enum UserStatus {

    Unconfirmed("Unconfirmed"),
    Confirmed("Confirmed"),
    Approved("Approved");

    private final String label;

    private UserStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
