package com.aripd.ecommerce.view.administrator;

import com.aripd.ecommerce.service.FeedbackService;
import com.aripd.ecommerce.entity.FeedbackEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.model.data.LazyFeedbackDataModel;
import com.aripd.ecommerce.service.UserService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class FeedbackController implements Serializable {

    @Inject
    private FeedbackService feedbackService;
    private FeedbackEntity newRecord;
    private FeedbackEntity selectedRecord;
    private List<FeedbackEntity> selectedRecords;
    private LazyDataModel<FeedbackEntity> lazyModel;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    MessageUtil messageUtil;

    public FeedbackController() {
        newRecord = new FeedbackEntity();
        selectedRecord = new FeedbackEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        lazyModel = new LazyFeedbackDataModel(feedbackService);
    }

    public void doCreateRecord(ActionEvent actionEvent) {
        feedbackService.create(newRecord);
        messageUtil.addGlobalInfoFlashMessage("Created");
    }

    public void doReplyRecord(ActionEvent actionEvent) {
        newRecord = selectedRecord;
        newRecord.setCreatedBy(user);
        feedbackService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doUpdateRecord(ActionEvent actionEvent) {
        feedbackService.update(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Updated");
    }

    public void doDeleteRecord(ActionEvent actionEvent) {
        feedbackService.delete(selectedRecord);
        messageUtil.addGlobalInfoFlashMessage("Deleted");
    }

    public void doDeleteRecords(ActionEvent actionEvent) {
        if (selectedRecords.isEmpty()) {
            messageUtil.addGlobalErrorFlashMessage("Please select at least one item");
        } else {
            feedbackService.deleteItems(selectedRecords);
            messageUtil.addGlobalInfoFlashMessage("Deleted");
        }
    }

    public FeedbackEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(FeedbackEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<FeedbackEntity> getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(List<FeedbackEntity> selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public FeedbackEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(FeedbackEntity newRecord) {
        this.newRecord = newRecord;
    }

    public LazyDataModel<FeedbackEntity> getLazyModel() {
        return lazyModel;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
