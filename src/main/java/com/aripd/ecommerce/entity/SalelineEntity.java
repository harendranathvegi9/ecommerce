package com.aripd.ecommerce.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class SalelineEntity extends AbstractEntity {

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private SaleEntity sale;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SalelineStatus salelineStatus;

    @NotNull
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne
    private ProductEntity product;

    private String note;
    private String cargoTrackingLink;

    private String IPN_PID;
    private String IPN_PNAME;
    private String IPN_PCODE;
    private String IPN_INFO;
    private Integer IPN_QTY;
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal IPN_PRICE;
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal IPN_VAT;
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal IPN_TOTAL;

    public SalelineEntity() {
    }

    @Transient
    public BigDecimal getPriceTotalBeforeTax() {
        return IPN_PRICE.multiply(new BigDecimal(IPN_QTY));
    }

    @Transient
    public BigDecimal getTaxTotal() {
        return IPN_VAT.multiply(new BigDecimal(IPN_QTY));
    }

    @Transient
    public BigDecimal getPriceTotalAfterTax() {
//        return IPN_PRICE.add(IPN_VAT).multiply(new BigDecimal(IPN_QTY));
        return IPN_TOTAL.multiply(new BigDecimal(IPN_QTY));
    }

    public SaleEntity getSale() {
        return sale;
    }

    public void setSale(SaleEntity sale) {
        this.sale = sale;
    }

    public SalelineStatus getSalelineStatus() {
        return salelineStatus;
    }

    public void setSalelineStatus(SalelineStatus salelineStatus) {
        this.salelineStatus = salelineStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCargoTrackingLink() {
        return cargoTrackingLink;
    }

    public void setCargoTrackingLink(String cargoTrackingLink) {
        this.cargoTrackingLink = cargoTrackingLink;
    }

    public String getIPN_PID() {
        return IPN_PID;
    }

    public void setIPN_PID(String IPN_PID) {
        this.IPN_PID = IPN_PID;
    }

    public String getIPN_PNAME() {
        return IPN_PNAME;
    }

    public void setIPN_PNAME(String IPN_PNAME) {
        this.IPN_PNAME = IPN_PNAME;
    }

    public String getIPN_PCODE() {
        return IPN_PCODE;
    }

    public void setIPN_PCODE(String IPN_PCODE) {
        this.IPN_PCODE = IPN_PCODE;
    }

    public String getIPN_INFO() {
        return IPN_INFO;
    }

    public void setIPN_INFO(String IPN_INFO) {
        this.IPN_INFO = IPN_INFO;
    }

    public Integer getIPN_QTY() {
        return IPN_QTY;
    }

    public void setIPN_QTY(Integer IPN_QTY) {
        this.IPN_QTY = IPN_QTY;
    }

    public BigDecimal getIPN_PRICE() {
        return IPN_PRICE;
    }

    public void setIPN_PRICE(BigDecimal IPN_PRICE) {
        this.IPN_PRICE = IPN_PRICE;
    }

    public BigDecimal getIPN_VAT() {
        return IPN_VAT;
    }

    public void setIPN_VAT(BigDecimal IPN_VAT) {
        this.IPN_VAT = IPN_VAT;
    }

    public BigDecimal getIPN_TOTAL() {
        return IPN_TOTAL;
    }

    public void setIPN_TOTAL(BigDecimal IPN_TOTAL) {
        this.IPN_TOTAL = IPN_TOTAL;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}
