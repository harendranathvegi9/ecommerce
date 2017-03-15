package com.aripd.ecommerce.entity;

import com.aripd.util.StringUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class ProductEntity extends AbstractEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus productStatus;

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

    private String bannerHeadline;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bannerStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bannerEnd;

    public ProductEntity() {
    }

    @Transient
    public boolean getBannerStatus() {
        if (productStatus.equals(ProductStatus.ACTIVE)) {
            Calendar cal = Calendar.getInstance();
            return cal.after(bannerStart) && cal.before(bannerEnd);
        }
        return false;
    }

    @Transient
    public PriceEntity getPrice() {
//        return prices.stream().min((p1, p2) -> Integer.compare(p1.getQuantity(), p2.getQuantity())).get();
        return Collections.min(prices, new Comparator<PriceEntity>() {
            @Override
            public int compare(PriceEntity o1, PriceEntity o2) {
                int rollno1 = o1.getQuantity();
                int rollno2 = o2.getQuantity();
                return rollno1 - rollno2;//For ascending order
                //return rollno2-rollno1;//For descending order
            }
        });
    }

    @Transient
    public ImageEntity getImage() {
//        return images.stream().min((p1, p2) -> Integer.compare(p1.getSortOrder(), p2.getSortOrder())).get();
        try {
            return Collections.min(images, new Comparator<ImageEntity>() {
                @Override
                public int compare(ImageEntity o1, ImageEntity o2) {
                    int rollno1 = o1.getSortOrder();
                    int rollno2 = o2.getSortOrder();
                    return rollno1 - rollno2;//For ascending order
                    //return rollno2-rollno1;//For descending order
                }
            });
        } catch (NoSuchElementException ex) {
            return null;
        }
    }

    @Transient
    public String getSlug() {
        return StringUtil.slugify(name);
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
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
        Collections.sort(images, new Comparator<ImageEntity>() {

            @Override
            public int compare(ImageEntity o1, ImageEntity o2) {

                int rollno1 = o1.getSortOrder();
                int rollno2 = o2.getSortOrder();

                return rollno1 - rollno2;//For ascending order
                //return rollno2-rollno1;//For descending order
            }
        });

        return images;
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

    public String getBannerHeadline() {
        return bannerHeadline;
    }

    public void setBannerHeadline(String bannerHeadline) {
        this.bannerHeadline = bannerHeadline;
    }

    public Date getBannerStart() {
        return bannerStart;
    }

    public void setBannerStart(Date bannerStart) {
        this.bannerStart = bannerStart;
    }

    public Date getBannerEnd() {
        return bannerEnd;
    }

    public void setBannerEnd(Date bannerEnd) {
        this.bannerEnd = bannerEnd;
    }

}
