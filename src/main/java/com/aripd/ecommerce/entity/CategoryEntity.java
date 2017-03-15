package com.aripd.ecommerce.entity;

import com.aripd.util.StringUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class CategoryEntity extends AbstractEntity {

    @NotNull
    @Column(nullable = false, unique = true)
    private String code;

    private int sortOrder;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<ProductEntity> products = new ArrayList<>();

    @ManyToOne
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<CategoryEntity> children = new ArrayList<>();

    public CategoryEntity() {
    }

    @Transient
    public String getSlug() {
        return StringUtil.slugify(code);
    }

    @Transient
    public List<CategoryEntity> getBreadcrumb() {
        List<CategoryEntity> parents = new ArrayList<>();
        parents.add(this);
        CategoryEntity p = (this.parent == null) ? null : this.parent;
        while (p != null) {
            CategoryEntity c = p;
            p = (c.parent == null) ? null : c.parent;
            parents.add(c);
        }
        Collections.reverse(parents);
        return parents;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public void setParent(CategoryEntity parent) {
        this.parent = parent;
    }

    public List<CategoryEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryEntity> children) {
        this.children = children;
    }

}
