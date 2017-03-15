package com.aripd.ecommerce.converter;

import com.aripd.ecommerce.entity.PaymentMethod;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.convert.EnumConverter;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PaymentMethodConverter extends EnumConverter {

    public PaymentMethodConverter() {
        super(PaymentMethod.class);
    }

}
