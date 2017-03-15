package com.aripd.util.validator;

import com.aripd.ecommerce.service.ProductService;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@FacesValidator(value = "codeValidator")
public class CodeValidator implements Validator {

//    @Inject
//    private ProductService productService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Object oldValue = ((UIInput) component).getValue();

        String newValue = value.toString();

        if (StringUtils.containsWhitespace(newValue)) {
            FacesMessage msg = new FacesMessage("Incorrect input provided", "The input must not have only blank spaces");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        /**
         * TODO buranın kontrolüne gerek yok. İsteyen istediği ürün kodunu
         * girsin. Arama sonucunda bütün aynı ürün kodları listelensin
         */
//        boolean isExist = false;
//        if (oldValue == null) {
//            isExist = productService.isExistByCode(value.toString());
//        } else {
//            isExist = productService.isExistByCodeExceptItself(value.toString(), oldValue.toString());
//        }
//
//        if (isExist) {
//            FacesMessage msg = new FacesMessage("Available code provided", "The code is not unreplicable");
//            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//            throw new ValidatorException(msg);
//        }
    }
}
