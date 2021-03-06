package com.aripd.ecommerce.entity;

public enum UserGroup {

    Administrators("Administrators"),
    Members("Members");

    private final String label;

    private UserGroup(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
