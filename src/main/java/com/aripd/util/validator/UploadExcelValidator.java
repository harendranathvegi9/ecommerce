package com.aripd.util.validator;

import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.model.UploadedFile;

@FacesValidator(value = "uploadExcelValidator")
public class UploadExcelValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        UploadedFile file = (UploadedFile) value;

        if (file == null) {
            FacesMessage msg = new FacesMessage("Unable to upload the file", "File cannot be empty");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        List<String> contentTypes = Arrays.asList("application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        if (!contentTypes.contains(file.getContentType())) {
            FacesMessage msg = new FacesMessage("Unable to upload the file", "This file type is not supported");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (file.getSize() > 1000000) {
            FacesMessage msg = new FacesMessage("Unable to upload the file", "This file size is not supported");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}
