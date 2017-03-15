package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.UserStatus;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class UserStatusConverter extends EnumConverter {

    public UserStatusConverter() {
        super(UserStatus.class);
    }

}
