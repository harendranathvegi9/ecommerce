package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.FeedbackEntity;

public class LazyFeedbackDataModel extends LazyAbstractDataModel<FeedbackEntity> {

    public LazyFeedbackDataModel(CrudService crudService) {
        super(crudService);
    }

}
