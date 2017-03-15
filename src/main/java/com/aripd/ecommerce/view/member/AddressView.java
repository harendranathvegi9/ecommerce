package com.aripd.ecommerce.view.member;

import com.aripd.util.MessageUtil;
import com.aripd.ecommerce.model.data.LazyAddressDataModelByUser;
import com.aripd.ecommerce.entity.AddressEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.AddressService;
import com.aripd.ecommerce.service.UserService;
import java.io.Serializable;
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
public class AddressView implements Serializable {

    @Inject
    private AddressService addressService;
    private AddressEntity newRecord;
    private AddressEntity selectedRecord;
    private List<AddressEntity> selectedRecords;
    private LazyDataModel<AddressEntity> lazyModel;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    MessageUtil messageUtil;

    public AddressView() {
        newRecord = new AddressEntity();
        selectedRecord = new AddressEntity();
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        if (user != null) {
            lazyModel = new LazyAddressDataModelByUser(addressService, user);
        }
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        newRecord.setCreatedBy(user);
        addressService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        addressService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        addressService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public AddressEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(AddressEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<AddressEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<AddressEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public AddressEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(AddressEntity newRecord) {
        this.newRecord = newRecord;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LazyDataModel<AddressEntity> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<AddressEntity> lazyModel) {
        this.lazyModel = lazyModel;
    }

}
