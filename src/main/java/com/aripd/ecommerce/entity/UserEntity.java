package com.aripd.ecommerce.entity;

import com.aripd.util.RequestUtil;
import com.aripd.util.SHA512;
import com.aripd.util.validator.EmailAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class UserEntity extends AbstractEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserGroup userGroup;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus;

    private String uuid;

    @EmailAddress
    @NotNull
    @Column(unique = true, nullable = false, length = 255)
    private String email;
//    @Password
    @NotNull
    @Column(nullable = false, length = 128)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String firstname;
    @NotNull
    @Column(nullable = false)
    private String lastname;

    @OneToMany(mappedBy = "createdBy", orphanRemoval = true)
    private List<AddressEntity> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", orphanRemoval = true)
    private List<SaleEntity> sales = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", orphanRemoval = true)
    private List<BasketitemEntity> basketitems = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy", orphanRemoval = true)
    private List<WishitemEntity> wishitems = new ArrayList<>();

    public UserEntity() {
    }

    @Transient
    public String getToken() {
        return SHA512.hashText(password + email);
    }

    @Transient
    public String getFullname() {
        return String.format("%s %s", firstname, lastname);
    }

    @Transient
    public URL getValidationUrl() {
        String file = String.format("/api/users/validate/?token=%s", getToken());
        return RequestUtil.getFullAddress(file);
    }

    @Transient
    public URL getNewPasswordRequestUrl() {
        String file = String.format("/api/users/newPasswordRequest/?token=%s", getToken());
        return RequestUtil.getFullAddress(file);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<SaleEntity> getSales() {
        return sales;
    }

    public void setSales(List<SaleEntity> sales) {
        this.sales = sales;
    }

    public List<BasketitemEntity> getBasketitems() {
        return basketitems;
    }

    public void setBasketitems(List<BasketitemEntity> basketitems) {
        this.basketitems = basketitems;
    }

    public List<WishitemEntity> getWishitems() {
        return wishitems;
    }

    public void setWishitems(List<WishitemEntity> wishitems) {
        this.wishitems = wishitems;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }

}
