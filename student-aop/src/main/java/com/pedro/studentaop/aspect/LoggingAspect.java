package com.pedro.studentaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.pedro.studentaop.service.StudentService.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        log.info("[AOP-BEFORE] calling: {}", joinPoint.getSignature());
    }

    @After("execution(* com.pedro.studentaop.service.StudentService.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        log.info("[AOP-AFTER] finished: {}", joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.pedro.studentaop.service.StudentService.getAllStudents(..))", returning = "ret")
    public void afterReturningGetAll(JoinPoint jp, Object ret) {
        log.info("[AOP-RETURNING] getAllStudents returned: {}", ret);
    }
}
