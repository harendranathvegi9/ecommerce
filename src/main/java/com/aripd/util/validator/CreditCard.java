package com.aripd.util.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "\\d{16}|\\d{4} \\d{4} \\d{4} \\d{4}|\\d{4}-\\d{4}-\\d{4}-\\d{4}")
@ReportAsSingleViolation
public @interface CreditCard {

    public abstract String message() default "{com.aripd.util.validator.CreditCard.message}";

    public abstract Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
