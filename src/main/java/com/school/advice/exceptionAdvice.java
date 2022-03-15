package com.school.advice;

import com.school.customException.BusinessLogicException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptionAdvice {

    private static final Logger LOG = Logger.getLogger(exceptionAdvice.class);

    @ExceptionHandler(BusinessLogicException.class)
    public String handleBusinessException(BusinessLogicException e) {
        LOG.error(e.getMessage());
        return e.getRedirectUrl();
    }

    @ExceptionHandler(HibernateException.class)
    public String handleHibernateException(HibernateException e) {
        LOG.error(e.getMessage());
        return "redirect:/";
    }


}
