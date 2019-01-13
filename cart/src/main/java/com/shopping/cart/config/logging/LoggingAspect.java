package com.shopping.cart.config.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Burak Naroglu
 */

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com..*Service.*(..))")
    public void loggingPointcut() {
    }

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}",
                new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                        e.getCause() != null ? e.getCause() : "NULL"});
    }

    @Around("loggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Enter: {}.{}() with argument[s] = {}",
                new Object[]{joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                        Arrays.toString(joinPoint.getArgs())});
        try {
            Object result = joinPoint.proceed();
            log.info("Exit: {}.{}() with result = {}", new Object[]{
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result});
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", new Object[]{Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName()});
            throw e;
        }
    }

}

    