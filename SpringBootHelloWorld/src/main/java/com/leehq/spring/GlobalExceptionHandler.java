package com.leehq.spring;

import com.leehq.spring.contacts.ContactsController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by putao_lhq on 17-4-27.
 */
@ControllerAdvice(basePackageClasses = ContactsController.class)
public class GlobalExceptionHandler
{
    public static final String DEFAULT_ERROR_VIEW = "error_advice";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defualtErrorHandler(HttpServletRequest req, Exception e) throws Exception
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("url", req.getRequestURI());
        modelAndView.setViewName(DEFAULT_ERROR_VIEW);
        return modelAndView;
    }
}
