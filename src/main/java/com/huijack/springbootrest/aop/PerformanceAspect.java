package com.huijack.springbootrest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAspect.class);

    @Around("execution(* com.huijack.springbootrest.service.JobService.*(..))")
    public Object monitorTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object obj = joinPoint.proceed();

        long end = System.currentTimeMillis();

        LOGGER.info("Time taken by " + joinPoint.getSignature().getName() + " : " + (end - start) + " ms");

        return obj;
    }
}
