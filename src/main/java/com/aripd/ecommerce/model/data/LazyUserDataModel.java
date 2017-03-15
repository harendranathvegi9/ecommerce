package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.UserEntity;

public class LazyUserDataModel extends LazyAbstractDataModel<UserEntity> {

    public LazyUserDataModel(CrudService crudService) {
        super(crudService);
    }

}
