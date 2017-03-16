package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.TicketType;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TicketTypeConverter extends EnumConverter {

    public TicketTypeConverter() {
        super(TicketType.class);
    }

}
