package com.aripd.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class PageEntity extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String name;

    private String description;

    @NotNull
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public PageEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
