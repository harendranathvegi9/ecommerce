package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.SaleStatus;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class SaleStatusConverter extends EnumConverter {

    public SaleStatusConverter() {
        super(SaleStatus.class);
    }

}
