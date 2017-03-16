package com.aripd.ecommerce.view.member;

import com.aripd.ecommerce.service.FeedbackService;
import com.aripd.ecommerce.entity.FeedbackEntity;
import com.aripd.ecommerce.entity.UserEntity;
import com.aripd.ecommerce.model.data.LazyFeedbackDataModelByUser;
import com.aripd.ecommerce.service.UserService;
import com.aripd.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
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
    private LazyDataModel<FeedbackEntity> lazyModel;
    private List<FeedbackEntity> feedbacks;

    private String uuid;

    private String message;

    @Inject
    private UserService userService;
    private UserEntity user;

    @Inject
    MessageUtil messageUtil;

    public FeedbackView() {
        newRecord = new FeedbackEntity();
    }

    @PostConstruct
    public void init() {
        user = userService.getCurrentUser();
        lazyModel = new LazyFeedbackDataModelByUser(feedbackService, user);
    }

    public void onLoad() {
        if (uuid == null) {
            messageUtil.addGlobalErrorFlashMessage("Bad request. Please use a link from within the system.");
            return;
        }

        feedbacks = feedbackService.findByUuid(uuid);

    }

    public void doSubmit(ActionEvent actionEvent) {
        newRecord.setUser(user);
        newRecord.setUuid(UUID.randomUUID().toString());
        FeedbackEntity feedback = feedbackService.create(newRecord);
//        feedbackService.sendFeedback(feedback);

        newRecord = new FeedbackEntity();
    }

    public void doReplyRecord(ActionEvent actionEvent) {
        FeedbackEntity replyRecord = feedbacks.get(0);
        replyRecord.setMessage(message);
        feedbackService.create(replyRecord);
        messageUtil.addGlobalInfoFlashMessage("Replied");
        
        message = "";
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

    public LazyDataModel<FeedbackEntity> getLazyModel() {
        return lazyModel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
