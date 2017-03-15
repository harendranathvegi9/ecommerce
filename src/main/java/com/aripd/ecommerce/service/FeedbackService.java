package com.aripd.ecommerce.service;

import com.aripd.ecommerce.entity.FeedbackEntity;
import javax.ejb.Local;

@Local
public interface FeedbackService extends CrudService<FeedbackEntity, Long> {

    public void sendFeedback(FeedbackEntity feedback);
}
