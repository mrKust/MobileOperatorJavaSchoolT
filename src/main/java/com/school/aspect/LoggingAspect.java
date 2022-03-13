package com.school.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOG = Logger.getLogger(LoggingAspect.class);

    @Around("execution(* com.school.database.dao.*.*(..))")
    public Object aroundAllDaoMethodsAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String methodName = methodSignature.getName();

        LOG.trace("Begin of " + methodName);

        Object targetMethodResult = proceedingJoinPoint.proceed();

        LOG.trace("End of " + methodName);

        return targetMethodResult;
    }

    @After("execution(* com.school.service.ClientDetailService.loadUserByUsername(..))")
    public void afterLoginAdvice() {
        LOG.info("User logged in system ");

    }
}
