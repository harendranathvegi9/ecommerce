package com.aripd.ecommerce.model.data;

import com.aripd.ecommerce.service.CrudService;
import com.aripd.ecommerce.entity.TicketEntity;

public class LazyTicketDataModel extends LazyAbstractDataModel<TicketEntity> {

    public LazyTicketDataModel(CrudService crudService) {
        super(crudService);
    }

}
