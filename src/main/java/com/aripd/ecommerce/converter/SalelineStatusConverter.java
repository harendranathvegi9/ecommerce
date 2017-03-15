package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.SalelineStatus;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class SalelineStatusConverter extends EnumConverter {

    public SalelineStatusConverter() {
        super(SalelineStatus.class);
    }

}
