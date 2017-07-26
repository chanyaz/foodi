package com.artinrayan.foodi.web.controller;

import exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by asus on 5/26/2017.
 */
@Controller
public class ExceptionController {

    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleCustomException(BusinessException ex) {

        ModelAndView model = new ModelAndView("views/error");
        model.addObject("errCode", ex.getCause());
        model.addObject("errMsg", ex.getMessage());

        return model;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("views/error");
        model.addObject("errMsg", "this is Exception.class");

        return model;

    }
}
