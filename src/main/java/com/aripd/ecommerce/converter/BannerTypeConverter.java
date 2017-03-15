package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.BannerType;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class BannerTypeConverter extends EnumConverter {

    public BannerTypeConverter() {
        super(BannerType.class);
    }

}
