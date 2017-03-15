package com.aripd.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class BasketitemEntity extends AbstractEntity {

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private UserEntity createdBy;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private ProductEntity product;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    private String note;

    public BasketitemEntity() {
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
