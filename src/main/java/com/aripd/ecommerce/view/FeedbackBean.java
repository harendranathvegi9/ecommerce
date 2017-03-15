package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.FeedbackService;
import com.aripd.ecommerce.entity.FeedbackEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.service.UserService;
import java.io.Serializable;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class FeedbackBean implements Serializable {

    @Inject
    private FeedbackService feedbackService;
    private FeedbackEntity newRecord;

    @Inject
    private UserService userService;
    private UserEntity user;

    public FeedbackBean() {
        newRecord = new FeedbackEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
    }

    public void doSubmit(ActionEvent actionEvent) {
        newRecord.setCreatedBy(user);
        newRecord.setUuid(UUID.randomUUID().toString());
        FeedbackEntity feedback = feedbackService.create(newRecord);
        feedbackService.sendFeedback(feedback);
    }

    public FeedbackEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(FeedbackEntity newRecord) {
        this.newRecord = newRecord;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
