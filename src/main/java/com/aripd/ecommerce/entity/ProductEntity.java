package com.aripd.ecommerce.entity;

import com.aripd.util.StringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class ProductEntity extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private boolean status = false;

    @NotNull
    @Column(nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false, precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal taxRate;

    @ManyToOne
    private CategoryEntity category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<ImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<PriceEntity> prices = new ArrayList<>();

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<BasketitemEntity> basketitems = new ArrayList<>();

    public ProductEntity() {
    }

    @Transient
    public boolean getBannerStatus() {
        if (status) {
            return !images
                    .stream()
                    .filter(i -> i.getImageType().equals(ImageType.BANNER))
                    .collect(Collectors.toList())
                    .isEmpty();
        }
        return false;
    }

    @Transient
    public PriceEntity getPrice() {
        return prices
                .stream()
                .min((p1, p2) -> Integer.compare(p1.getQuantity(), p2.getQuantity()))
                .get();
    }

    @Transient
    public ImageEntity getImage() {
        return images
                .stream()
                .filter(i -> i.getImageType().equals(ImageType.SHOWCASE))
                .min((p1, p2) -> Integer.compare(p1.getSortOrder(), p2.getSortOrder()))
                .get();
    }

    @Transient
    public ImageEntity getBanner() {
        return images
                .stream()
                .filter(i -> i.getImageType().equals(ImageType.BANNER))
                .min((p1, p2) -> Integer.compare(p1.getSortOrder(), p2.getSortOrder()))
                .get();
    }

    @Transient
    public String getSlug() {
        return StringUtil.slugify(name);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<BasketitemEntity> getBasketitems() {
        return basketitems;
    }

    public void setBasketitems(List<BasketitemEntity> basketitems) {
        this.basketitems = basketitems;
    }

    public List<ImageEntity> getImages() {
        return images
                .stream()
//                .filter(i -> i.getImageType().equals(ImageType.SHOWCASE))
                .sorted((p1, p2) -> Integer.compare(p1.getSortOrder(), p2.getSortOrder()))
                .collect(Collectors.toList());
    }

    public void setImages(List<ImageEntity> images) {
        this.images = images;
    }

    public List<PriceEntity> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceEntity> prices) {
        this.prices = prices;
    }

}
