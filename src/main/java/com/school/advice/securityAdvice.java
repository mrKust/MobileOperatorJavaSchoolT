package com.school.advice;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This advice handle the access denied exceptions.
 * It registers this type of exception and handle it
 */
public class securityAdvice implements AccessDeniedHandler {

    private static final Logger LOG = Logger.getLogger(securityAdvice.class);

    /**
     * This handler works with spring's security exceptions when user try to get URL without right credentials
     * @param httpServletRequest request
     * @param httpServletResponse response
     * @param e exceptions with message
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        LOG.setLevel(Level.WARN);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOG.warn("User " + auth.getName() + " try to access forbidden URL: " +
                    httpServletRequest.getRequestURI());
        }

        httpServletResponse.sendRedirect("/errorPage?errorMes=You don't have access to this page");
    }
}
