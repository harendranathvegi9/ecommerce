package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.ImageType;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ImageTypeConverter extends EnumConverter {

    public ImageTypeConverter() {
        super(ImageType.class);
    }

}
