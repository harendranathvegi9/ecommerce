package com.aripd.ecommerce.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class SaleEntity extends AbstractEntity {

    @NotNull
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @NotNull
    @JoinColumn(nullable = false, updatable = false)
    @ManyToOne
    private UserEntity createdBy;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SaleStatus saleStatus;

    private String bankTransactionNumber;

    private String billFirstname;
    private String billLastname;
    private String billLine;
    private String billCity;
    private String billZipcode;
    private String billCountry;
    private String billPhone;

    private String deliveryFirstname;
    private String deliveryLastname;
    private String deliveryLine;
    private String deliveryCity;
    private String deliveryZipcode;
    private String deliveryCountry;
    private String deliveryPhone;

    @OneToMany(mappedBy = "sale", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<SalelineEntity> salelines = new ArrayList<>();

    private String SALEDATE;
    private String PAYMENTDATE;
    private String COMPLETE_DATE;

    private String REFNO;

    @NotNull
    @Column(nullable = false, unique = true)
    private String REFNOEXT;

    private String ORDERNO;

    private String PAYMETHOD;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod PAYMETHOD_CODE;

    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal IPN_PAID_AMOUNT = BigDecimal.ZERO;
    private String IPN_INSTALLMENTS_PROGRAM;
    private String IPN_INSTALLMENTS_NUMBER;
    private BigDecimal IPN_INSTALLMENTS_PROFIT = BigDecimal.ZERO;

    private String currency;
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal IPN_TOTALGENERAL = BigDecimal.ZERO;
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal IPN_SHIPPING = BigDecimal.ZERO;
    private BigDecimal IPN_GLOBALDISCOUNT = BigDecimal.ZERO;
    @Column(precision = 10, scale = 2, columnDefinition = "DECIMAL(10,2)")
    private BigDecimal IPN_COMMISSION = BigDecimal.ZERO;
    private String IPN_DATE;
    private String HASH;

    public SaleEntity() {
    }

    @PrePersist
    protected void prePersist() {
        createdAt = new Date();
    }

    @Transient
    public String getBillAddress() {
        return String.format("%s, %s, %s, %s",
                billLine,
                billZipcode,
                billCity,
                billCountry
        );
    }

    @Transient
    public String getDeliveryAddress() {
        return String.format("%s, %s, %s, %s",
                deliveryLine,
                deliveryZipcode,
                deliveryCity,
                deliveryCountry
        );
    }

    @Transient
    public BigDecimal getPriceTotalBeforeTax() {
        BigDecimal s = BigDecimal.ZERO;
        for (SalelineEntity e : this.getSalelines()) {
            BigDecimal sl = e.getPriceTotalBeforeTax();
            s = s.add(sl);
        }
        return s;
    }

    @Transient
    public BigDecimal getPriceTotalAfterTax() {
        BigDecimal s = BigDecimal.ZERO;
        for (SalelineEntity e : this.getSalelines()) {
            BigDecimal sl = e.getPriceTotalAfterTax();
            s = s.add(sl);
        }
        return s;
    }

    @Transient
    public BigDecimal getPriceTotalAfterTaxAfterDiscount() {
        BigDecimal s = BigDecimal.ZERO;
        for (SalelineEntity e : this.getSalelines()) {
            BigDecimal sl = e.getPriceTotalAfterTaxAfterDiscount();
            s = s.add(sl);
        }
        return s;
    }

    @Transient
    public BigDecimal getTaxTotal() {
        BigDecimal s = BigDecimal.ZERO;
        for (SalelineEntity e : this.getSalelines()) {
            BigDecimal sl = e.getIPN_VAT().multiply(new BigDecimal(e.getIPN_QTY()));
            s = s.add(sl);
        }
        return s;

    }

    @Transient
    public BigDecimal getDiscountTotal() {
        BigDecimal s = BigDecimal.ZERO;
        for (SalelineEntity e : this.getSalelines()) {
            BigDecimal sl = e.getIPN_DISCOUNT().multiply(new BigDecimal(e.getIPN_QTY()));
            s = s.add(sl);
        }
        return s;

    }

    @Transient
    public Integer getQuantityTotal() {
        Integer total = 0;
        for (SalelineEntity e : this.getSalelines()) {
            Integer quantity = e.getIPN_QTY();
            total += quantity;
        }
        return total;
    }

    public String getREFNOEXT() {
        return REFNOEXT;
    }

    public void setREFNOEXT(String REFNOEXT) {
        this.REFNOEXT = REFNOEXT;
    }

    public String getBankTransactionNumber() {
        return bankTransactionNumber;
    }

    public void setBankTransactionNumber(String bankTransactionNumber) {
        this.bankTransactionNumber = bankTransactionNumber;
    }

    public String getBillFirstname() {
        return billFirstname;
    }

    public void setBillFirstname(String billFirstname) {
        this.billFirstname = billFirstname;
    }

    public String getBillLastname() {
        return billLastname;
    }

    public void setBillLastname(String billLastname) {
        this.billLastname = billLastname;
    }

    public String getBillLine() {
        return billLine;
    }

    public void setBillLine(String billLine) {
        this.billLine = billLine;
    }

    public String getBillCity() {
        return billCity;
    }

    public void setBillCity(String billCity) {
        this.billCity = billCity;
    }

    public String getBillZipcode() {
        return billZipcode;
    }

    public void setBillZipcode(String billZipcode) {
        this.billZipcode = billZipcode;
    }

    public String getBillCountry() {
        return billCountry;
    }

    public void setBillCountry(String billCountry) {
        this.billCountry = billCountry;
    }

    public String getBillPhone() {
        return billPhone;
    }

    public void setBillPhone(String billPhone) {
        this.billPhone = billPhone;
    }

    public String getDeliveryFirstname() {
        return deliveryFirstname;
    }

    public void setDeliveryFirstname(String deliveryFirstname) {
        this.deliveryFirstname = deliveryFirstname;
    }

    public String getDeliveryLastname() {
        return deliveryLastname;
    }

    public void setDeliveryLastname(String deliveryLastname) {
        this.deliveryLastname = deliveryLastname;
    }

    public String getDeliveryLine() {
        return deliveryLine;
    }

    public void setDeliveryLine(String deliveryLine) {
        this.deliveryLine = deliveryLine;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getDeliveryZipcode() {
        return deliveryZipcode;
    }

    public void setDeliveryZipcode(String deliveryZipcode) {
        this.deliveryZipcode = deliveryZipcode;
    }

    public String getDeliveryCountry() {
        return deliveryCountry;
    }

    public void setDeliveryCountry(String deliveryCountry) {
        this.deliveryCountry = deliveryCountry;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone;
    }

    public List<SalelineEntity> getSalelines() {
        return salelines;
    }

    public void setSalelines(List<SalelineEntity> salelines) {
        this.salelines = salelines;
    }

    public String getSALEDATE() {
        return SALEDATE;
    }

    public void setSALEDATE(String SALEDATE) {
        this.SALEDATE = SALEDATE;
    }

    public String getPAYMENTDATE() {
        return PAYMENTDATE;
    }

    public void setPAYMENTDATE(String PAYMENTDATE) {
        this.PAYMENTDATE = PAYMENTDATE;
    }

    public String getCOMPLETE_DATE() {
        return COMPLETE_DATE;
    }

    public void setCOMPLETE_DATE(String COMPLETE_DATE) {
        this.COMPLETE_DATE = COMPLETE_DATE;
    }

    public String getREFNO() {
        return REFNO;
    }

    public void setREFNO(String REFNO) {
        this.REFNO = REFNO;
    }

    public String getORDERNO() {
        return ORDERNO;
    }

    public void setORDERNO(String ORDERNO) {
        this.ORDERNO = ORDERNO;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getPAYMETHOD() {
        return PAYMETHOD;
    }

    public void setPAYMETHOD(String PAYMETHOD) {
        this.PAYMETHOD = PAYMETHOD;
    }

    public PaymentMethod getPAYMETHOD_CODE() {
        return PAYMETHOD_CODE;
    }

    public void setPAYMETHOD_CODE(PaymentMethod PAYMETHOD_CODE) {
        this.PAYMETHOD_CODE = PAYMETHOD_CODE;
    }

    public BigDecimal getIPN_PAID_AMOUNT() {
        return IPN_PAID_AMOUNT;
    }

    public void setIPN_PAID_AMOUNT(BigDecimal IPN_PAID_AMOUNT) {
        this.IPN_PAID_AMOUNT = IPN_PAID_AMOUNT;
    }

    public String getIPN_INSTALLMENTS_PROGRAM() {
        return IPN_INSTALLMENTS_PROGRAM;
    }

    public void setIPN_INSTALLMENTS_PROGRAM(String IPN_INSTALLMENTS_PROGRAM) {
        this.IPN_INSTALLMENTS_PROGRAM = IPN_INSTALLMENTS_PROGRAM;
    }

    public String getIPN_INSTALLMENTS_NUMBER() {
        return IPN_INSTALLMENTS_NUMBER;
    }

    public void setIPN_INSTALLMENTS_NUMBER(String IPN_INSTALLMENTS_NUMBER) {
        this.IPN_INSTALLMENTS_NUMBER = IPN_INSTALLMENTS_NUMBER;
    }

    public BigDecimal getIPN_INSTALLMENTS_PROFIT() {
        return IPN_INSTALLMENTS_PROFIT;
    }

    public void setIPN_INSTALLMENTS_PROFIT(BigDecimal IPN_INSTALLMENTS_PROFIT) {
        this.IPN_INSTALLMENTS_PROFIT = IPN_INSTALLMENTS_PROFIT;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getIPN_TOTALGENERAL() {
        return IPN_TOTALGENERAL;
    }

    public void setIPN_TOTALGENERAL(BigDecimal IPN_TOTALGENERAL) {
        this.IPN_TOTALGENERAL = IPN_TOTALGENERAL;
    }

    public BigDecimal getIPN_SHIPPING() {
        return IPN_SHIPPING;
    }

    public void setIPN_SHIPPING(BigDecimal IPN_SHIPPING) {
        this.IPN_SHIPPING = IPN_SHIPPING;
    }

    public BigDecimal getIPN_GLOBALDISCOUNT() {
        return IPN_GLOBALDISCOUNT;
    }

    public void setIPN_GLOBALDISCOUNT(BigDecimal IPN_GLOBALDISCOUNT) {
        this.IPN_GLOBALDISCOUNT = IPN_GLOBALDISCOUNT;
    }

    public BigDecimal getIPN_COMMISSION() {
        return IPN_COMMISSION;
    }

    public void setIPN_COMMISSION(BigDecimal IPN_COMMISSION) {
        this.IPN_COMMISSION = IPN_COMMISSION;
    }

    public String getIPN_DATE() {
        return IPN_DATE;
    }

    public void setIPN_DATE(String IPN_DATE) {
        this.IPN_DATE = IPN_DATE;
    }

    public String getHASH() {
        return HASH;
    }

    public void setHASH(String HASH) {
        this.HASH = HASH;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserEntity createdBy) {
        this.createdBy = createdBy;
    }

}
