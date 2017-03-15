package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.BannerEntity;

public class LazyBannerDataModel extends LazyAbstractDataModel<BannerEntity> {

    public LazyBannerDataModel(CrudService crudService) {
        super(crudService);
    }

}
