package com.aripd.ecommerce.view;

import com.aripd.ecommerce.service.FeedbackService;
import com.aripd.ecommerce.entity.FeedbackEntity;
import java.io.Serializable;
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

    public FeedbackBean() {
        newRecord = new FeedbackEntity();
    }

    @PostConstruct
    public void init() {
    }

    public void doSubmit(ActionEvent actionEvent) {
        FeedbackEntity feedback = feedbackService.create(newRecord);
        feedbackService.sendFeedback(feedback);
    }

    public FeedbackEntity getNewRecord() {
        return newRecord;
    }

    public void setNewRecord(FeedbackEntity newRecord) {
        this.newRecord = newRecord;
    }


}
