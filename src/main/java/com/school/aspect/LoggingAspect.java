package com.school.aspect;

import org.apache.log4j.Level;
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

    /**
     * This method log when all controller methods begin and end their work
     * @param proceedingJoinPoint joinPoint
     * @return result of method works
     * @throws Throwable
     */
    @Around("execution(* com.school.controller.*.*(..))")
    public Object aroundAllControllerMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        LOG.setLevel(Level.TRACE);
        LOG.trace("Begin method " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
                proceedingJoinPoint.getSignature().getName());

        Object targetMethodResult = proceedingJoinPoint.proceed();

        LOG.trace("End method " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
                proceedingJoinPoint.getSignature().getName());

        return targetMethodResult;
    }

    /**
     * This method log when user login in system
     */
    @After("execution(* com.school.service.security.ClientDetailService.loadUserByUsername(..))")
    public void afterLoginAdvice() {
        LOG.setLevel(Level.TRACE);
        LOG.trace("User logged in system ");

    }

    /**
     * This method log when all service methods begins and ends their work
     * @param proceedingJoinPoint joinPoint
     * @return result of method works
     * @throws Throwable
     */
    @Around("execution(* com.school.service.impl.*.*(..))")
    public Object aroundAllServiceMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        this.LOG.setLevel(Level.INFO);
        LOG.info("Begin method " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
                proceedingJoinPoint.getSignature().getName());

        Object targetMethodResult = proceedingJoinPoint.proceed();

        LOG.info("End method " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " +
                proceedingJoinPoint.getSignature().getName());

        return targetMethodResult;
    }
}
