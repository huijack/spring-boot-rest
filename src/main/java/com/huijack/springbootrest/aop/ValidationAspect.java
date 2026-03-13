package com.huijack.springbootrest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.huijack.springbootrest.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint joinPoint, int postId) throws Throwable {

        if (postId < 0) {
            LOGGER.info("Negative postId, converting to positive");
            postId = -postId;
            LOGGER.info("New postId is " + postId);
        }

        Object obj = joinPoint.proceed(new Object[]{postId});

        return obj;
    }

}
