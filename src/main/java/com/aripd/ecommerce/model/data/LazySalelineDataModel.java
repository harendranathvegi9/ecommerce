package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.SalelineEntity;

public class LazySalelineDataModel extends LazyAbstractDataModel<SalelineEntity> {

    public LazySalelineDataModel(CrudService crudService) {
        super(crudService);
    }

}
