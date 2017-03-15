package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.entity.CategoryEntity;
import com.aripd.ecommerce.model.data.LazyUserDataModel;
import com.aripd.ecommerce.service.UserService;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.entity.UserGroup;
import com.aripd.ecommerce.entity.UserStatus;
import com.aripd.util.locale.LocaleProvider;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import com.aripd.ecommerce.service.CategoryService;

@Named
@ViewScoped
public class UserController implements Serializable {

    @Inject
    private UserService userService;
    private UserEntity newRecord;
    private UserEntity selectedRecord;
    private List<UserEntity> selectedRecords;
    private LazyDataModel<UserEntity> lazyModel;

    private Long id;

    @Inject
    private CategoryService categoryService;

    @Inject
    MessageUtil messageUtil;

    public UserController() {
        newRecord = new UserEntity();
        selectedRecord = new UserEntity();
        selectedRecords = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        lazyModel = new LazyUserDataModel(userService);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = userService.find(id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

    }

    public List<UserGroup> getUserGroups() {
        return Arrays.asList(UserGroup.values());
    }

    public List<UserStatus> getUserStatuses() {
        return Arrays.asList(UserStatus.values());
    }

    public List<CategoryEntity> getCategories() {
        return categoryService.findAll();
    }

    public int sortByFullname(UserEntity e1, UserEntity e2) {
        Collator collator = Collator.getInstance(LocaleProvider.getLocale());
        return collator.compare(e1.getFullname(), e2.getFullname());
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        userService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        userService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        userService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            userService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public UserEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(UserEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<UserEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<UserEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public UserEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(UserEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<UserEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
