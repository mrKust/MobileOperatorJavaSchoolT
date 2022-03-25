package com.school.advice;

import com.school.customException.BusinessLogicException;
import com.school.customException.ServiceLayerException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.log4j.Logger;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


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

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException e) {

        String[] partsOfException = e.getMessage().split("/");
        LOG.error(partsOfException[0]);
        return "redirect:/common/errorPage?errorMes=" + partsOfException[1];
    }

    @ExceptionHandler(ServiceLayerException.class)
    public String handleServiceException(ServiceLayerException e, Model model, Principal principal,
                                         HttpServletRequest request) {

        LOG.error("User " + principal.getName() + " " + e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());

        return "redirect:" + request.getHeader("Referer");
    }


}
