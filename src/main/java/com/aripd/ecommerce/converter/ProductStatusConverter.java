package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.ProductStatus;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ProductStatusConverter extends EnumConverter {

    public ProductStatusConverter() {
        super(ProductStatus.class);
    }

}
