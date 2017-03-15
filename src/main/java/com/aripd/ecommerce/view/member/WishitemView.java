package com.aripd.ecommerce.view.member;

import com.aripd.ecommerce.entity.ProductEntity;
import com.aripd.util.MessageUtil;
import com.aripd.ecommerce.model.data.LazyWishitemDataModelByUser;
import com.aripd.ecommerce.entity.WishitemEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.WishitemService;
import com.aripd.ecommerce.service.UserService;
import com.aripd.util.currency.CurrencyBean;
import com.aripd.util.currency.PriceHelper;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class WishitemView implements Serializable {

    @Inject
    private WishitemService wishitemService;
    private WishitemEntity newRecord;
    private WishitemEntity selectedRecord;
    private List<WishitemEntity> selectedRecords;
    private LazyDataModel<WishitemEntity> lazyModel;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    CurrencyBean currencyBean;

    @Inject
    PriceHelper priceHelper;

    @Inject
    MessageUtil messageUtil;

    public WishitemView() {
        newRecord = new WishitemEntity();
        selectedRecord = new WishitemEntity();
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        if (user != null) {
            lazyModel = new LazyWishitemDataModelByUser(wishitemService, user);
        }
    }

    public BigDecimal getPriceTaxedExchanged(ProductEntity product) {
        return priceHelper.getPriceTaxedExchanged(product, 1, currencyBean.getCurrency().getCurrencyCode());
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        newRecord.setCreatedBy(user);
        wishitemService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        wishitemService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public String doDeleteRecord() {
        wishitemService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");

        return "/member/wishlist?faces-redirect=true";
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            wishitemService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public WishitemEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(WishitemEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<WishitemEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<WishitemEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public WishitemEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(WishitemEntity newRecord) {
        this.newRecord = newRecord;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LazyDataModel<WishitemEntity> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<WishitemEntity> lazyModel) {
        this.lazyModel = lazyModel;
    }

}
