package com.aripd.ecommerce.entity;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class BannerEntity extends AbstractEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BannerType bannerType;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Lob
    private byte[] bytes;

    private int sortOrder;

    @Column(nullable = false)
    private boolean status = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEnd;

    public BannerEntity() {
    }

    public BannerType getBannerType() {
        return bannerType;
    }

    public void setBannerType(BannerType bannerType) {
        this.bannerType = bannerType;
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

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isStatus() {
        if (!status) {
            Calendar cal = Calendar.getInstance();
            return cal.after(timeStart) && cal.before(timeEnd);
        }
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

}
