package com.demo.flightsearch.controller;

import com.demo.flightsearch.common.BindingErrorProcessor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * The type Base controller.
 */
public class BaseController {

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setBindingErrorProcessor(new BindingErrorProcessor());
    }
}
