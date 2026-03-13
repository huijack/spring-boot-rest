package com.huijack.springbootrest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, full-class-name.method-name(args)

    @Before("execution(* com.huijack.springbootrest.service.JobService.getJob(..)) || execution(* com.huijack.springbootrest.service.JobService.updateJob(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        LOGGER.info("Method called " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.huijack.springbootrest.service.JobService.getJob(..)) || execution(* com.huijack.springbootrest.service.JobService.updateJob(..))")
    public void logMethodExecuted(JoinPoint joinPoint) {
        LOGGER.info("Method executed " + joinPoint.getSignature().getName());
    }

    @AfterThrowing("execution(* com.huijack.springbootrest.service.JobService.getJob(..)) || execution(* com.huijack.springbootrest.service.JobService.updateJob(..))")
    public void logMethodCrashed(JoinPoint joinPoint) {
        LOGGER.info("Method has some issues " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.huijack.springbootrest.service.JobService.getJob(..)) || execution(* com.huijack.springbootrest.service.JobService.updateJob(..))")
    public void logMethodExecutedSuccess(JoinPoint joinPoint) {
        LOGGER.info("Method executed successfully " + joinPoint.getSignature().getName());
    }
}
