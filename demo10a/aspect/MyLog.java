package edu.miu.demo10a.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLog {
  @Before("execution ( * edu.miu.demo10a.service.ContactService.*(..))")
  public void Log(JoinPoint joinPoint){
    System.out.println("Logging");
  }
}
