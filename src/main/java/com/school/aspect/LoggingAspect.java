package com.school.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

/**
 * Class set se login aspects.
 * Log help to trace users in system
 */
@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOG = Logger.getLogger(LoggingAspect.class);

    @Around("execution(* com.school.controller.*.*(..))")
    public Object aroundAllControllerMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        LOG.trace("Begin method " + proceedingJoinPoint.getSignature().getName());

        Object targetMethodResult = proceedingJoinPoint.proceed();

        LOG.trace("End method " + proceedingJoinPoint.getSignature().getName());

        return targetMethodResult;
    }

    @After("execution(* com.school.service.security.ClientDetailService.loadUserByUsername(..))")
    public void afterLoginAdvice() {
        LOG.trace("User logged in system ");

    }
}
