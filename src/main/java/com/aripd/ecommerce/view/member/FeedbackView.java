package com.aripd.ecommerce.view.member;

import com.aripd.ecommerce.service.FeedbackService;
import com.aripd.ecommerce.entity.FeedbackEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.model.data.LazyFeedbackDataModelByUser;
import com.aripd.ecommerce.service.UserService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;

@Named
@ViewScoped
public class FeedbackView implements Serializable {

    @Inject
    private FeedbackService feedbackService;
    private FeedbackEntity newRecord;
    private FeedbackEntity selectedRecord;
    private LazyDataModel<FeedbackEntity> lazyModel;

    private Long id;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    MessageUtil messageUtil;

    public FeedbackView() {
        newRecord = new FeedbackEntity();
        selectedRecord = new FeedbackEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        lazyModel = new LazyFeedbackDataModelByUser(feedbackService, user);
    }

    public void onLoad() {
        if (id == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        selectedRecord = feedbackService.findOneByUserAndId(user, id);

        if (selectedRecord == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Unknown record.");
            return;
        }

        if (!selectedRecord.isViewed()) {
            selectedRecord.setViewed(true);
            feedbackService.update(selectedRecord);
        }

    }

    public void doSubmit(ActionEvent actionEvent) {
        newRecord.setCreatedBy(user);
        newRecord.setUuid(UUID.randomUUID().toString());
        FeedbackEntity feedback = feedbackService.create(newRecord);
//        feedbackService.sendFeedback(feedback);

        newRecord = new FeedbackEntity();
    }

    public FeedbackEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(FeedbackEntity newRecord) {
        this.newRecord = newRecord;
    }

    public FeedbackEntity getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(FeedbackEntity selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LazyDataModel<FeedbackEntity> getLazyModel() {
        return lazyModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
