package com.demo.flightsearch.api.validators;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.UUID;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The interface Existing airline validator. Used for custom validation
 */
@Target({METHOD, FIELD, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {ExistingAirlineValidator.ValidatorProxy.class})
public @interface ExistingAirlineValidator {

    /**
     * Required boolean.
     *
     * @return the boolean
     */
    boolean required() default true;

    /**
     * Message string.
     *
     * @return the string
     */
    String message() default "AirlineCode '${validatedValue}' is not valid.";

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * The interface Validator.
     */
    interface Validator {
        /**
         * Is valid airline boolean.
         *
         * @param uuid the uuid
         * @return the boolean
         */
        boolean isValidAirline(String uuid);
    }

    /**
     * The type Validator proxy.
     */
    class ValidatorProxy implements ConstraintValidator<ExistingAirlineValidator, String> {

        private boolean required;

        @Autowired
        private Validator delegate;

        @Override
        public void initialize(ExistingAirlineValidator parameter) {
            this.required = parameter.required();
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (!required && value == null) return true;

            return delegate != null && delegate.isValidAirline(value);
        }
    }

}