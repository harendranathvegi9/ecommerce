package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.UserGroup;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserGroupConverter extends EnumConverter {

    public UserGroupConverter() {
        super(UserGroup.class);
    }

}
