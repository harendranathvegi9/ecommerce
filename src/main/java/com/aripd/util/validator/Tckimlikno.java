package com.aripd.util.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Custom validator verifying Id Number of Turkish Republic
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TckimliknoValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
public @interface Tckimlikno {

    String message() default "{com.aripd.annotation.Tckimlikno.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}