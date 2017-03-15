package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.PageEntity;

public class LazyPageDataModel extends LazyAbstractDataModel<PageEntity> {

    public LazyPageDataModel(CrudService crudService) {
        super(crudService);
    }

}
