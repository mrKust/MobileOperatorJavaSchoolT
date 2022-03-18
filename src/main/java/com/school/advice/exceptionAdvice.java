package com.school.advice;

import com.school.customException.BusinessLogicException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.log4j.Logger;


@ControllerAdvice
public class exceptionAdvice {

    private static final Logger LOG = Logger.getLogger(exceptionAdvice.class);

    @ExceptionHandler(BusinessLogicException.class)
    public String handleBusinessException(BusinessLogicException e, Model model) {
        LOG.error(e.getMessage());
        model.addAttribute("errorMessage", e.getErrorMessageForUser());
        return e.getRedirectUrl();
    }

    @ExceptionHandler(Exception.class)
    public String handleHibernateException(Exception e) {
        LOG.error(e.getMessage());
        return "redirect:/common/errorPage?errorMes=" + e.getMessage();
    }


}
