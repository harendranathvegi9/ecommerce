package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.entity.CategoryEntity;
import com.aripd.ecommerce.service.CrudService;

public class LazyCategoryDataModel extends LazyAbstractDataModel<CategoryEntity> {

    public LazyCategoryDataModel(CrudService crudService) {
        super(crudService);
    }

}
