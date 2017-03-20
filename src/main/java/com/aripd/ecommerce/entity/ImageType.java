package com.aripd.ecommerce.entity;

public enum ImageType {

    SHOWCASE("Showcase"),
    BANNER("Banner");

    private final String label;

    private ImageType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
