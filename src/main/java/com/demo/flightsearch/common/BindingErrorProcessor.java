package com.demo.flightsearch.common;

import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.FieldError;

/**
 * The type Binding error processor.
 */
public class BindingErrorProcessor extends DefaultBindingErrorProcessor {
    /**
     * Instantiates a new Binding error processor.
     */
    public BindingErrorProcessor() {
    }

    public void processPropertyAccessException(PropertyAccessException e, BindingResult bindingResult) {
        Throwable cause = e.getCause();
        if (cause != null && cause instanceof ConversionFailedException) {
            cause = cause.getCause();
        }

        FieldError error;
        if (cause != null && cause instanceof IllegalArgumentException) {
            error = this.handlePropertyException(e, bindingResult, cause);
            bindingResult.addError(error);
        } else if (e instanceof TypeMismatchException) {
            error = this.handlePropertyException(e, bindingResult, cause);
            bindingResult.addError(error);
        } else {
            super.processPropertyAccessException(e, bindingResult);
        }

    }

    private FieldError handlePropertyException(PropertyAccessException e, BindingResult bindingResult, Throwable cause) {
        String message = cause != null ? cause.getMessage() : e.getMessage();
        String field = e.getPropertyName();
        Assert.state(field != null, "No field in exception");
        String[] codes = bindingResult.resolveMessageCodes(e.getErrorCode(), field);
        Object[] arguments = this.getArgumentsForBindError(bindingResult.getObjectName(), field);
        Object rejectedValue = e.getValue();
        if (ObjectUtils.isArray(rejectedValue)) {
            rejectedValue = StringUtils.arrayToCommaDelimitedString(ObjectUtils.toObjectArray(rejectedValue));
        }

        FieldError error = new FieldError(bindingResult.getObjectName(), field, rejectedValue, true, codes, arguments, message + " for " + field + ".");
        error.wrap(e);
        return error;
    }
}