package com.school.advice;

import com.school.customException.ServiceLayerException;
import org.apache.log4j.Level;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

/**
 * This advice handle different types of exceptions
 * It registers and handle them
 */
@ControllerAdvice
public class exceptionAdvice {

    private static final Logger LOG = Logger.getLogger(exceptionAdvice.class);


    /**
     * This handler catches all exceptions which doesn't handle somewhere in code
     * @param e exception
     * @return redirect to page with error message
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        LOG.setLevel(Level.ERROR);
        LOG.error(e.getMessage());
        return "redirect:/errorPage?errorMes=" + e.getMessage();
    }

    /**
     * This handler catches errors which hava been found on the service layer
     * @param e exception with message
     * @param principal user's data
     * @param request servlet request
     * @param redir constructor for redirect arguments
     * @return view with data and exception message text
     * @throws ServletException
     * @throws IOException
     */
    @ExceptionHandler(ServiceLayerException.class)
    public RedirectView handleServiceException(ServiceLayerException e, Principal principal,
                                               HttpServletRequest request, RedirectAttributes redir) throws ServletException, IOException {

        LOG.setLevel(Level.ERROR);
        LOG.error("User " + principal.getName() + " " + e.getMessage());
        RedirectView redirectView = new RedirectView(request.getHeader("Referer"), true);
        redir.addFlashAttribute("errorMessage", e.getMessage());
        Map<String, String[]> pageData = request.getParameterMap();
        for(String key : pageData.keySet()) {
            redir.addFlashAttribute(key, pageData.get(key)[0]);
        }
        return redirectView;
    }


}
