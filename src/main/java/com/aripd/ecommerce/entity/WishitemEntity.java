package com.aripd.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class WishitemEntity extends AbstractEntity {

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private UserEntity createdBy;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private ProductEntity product;

    public WishitemEntity() {
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

}
