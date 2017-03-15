package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.SaleEntity;

public class LazySaleDataModel extends LazyAbstractDataModel<SaleEntity> {

    public LazySaleDataModel(CrudService crudService) {
        super(crudService);
    }

}
