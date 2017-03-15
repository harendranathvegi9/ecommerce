package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.ProductEntity;

public class LazyProductDataModel extends LazyAbstractDataModel<ProductEntity> {

    public LazyProductDataModel(CrudService crudService) {
        super(crudService);
    }

}
