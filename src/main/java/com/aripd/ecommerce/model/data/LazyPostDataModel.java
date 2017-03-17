package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.PostEntity;

public class LazyPostDataModel extends LazyAbstractDataModel<PostEntity> {

    public LazyPostDataModel(CrudService crudService) {
        super(crudService);
    }

}
