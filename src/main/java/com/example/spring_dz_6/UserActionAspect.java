package com.example.spring_dz_6;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class UserActionAspect {

    @Pointcut("@annotation(com.example.spring_dz_6.TrackUserAction)")
    public void trackUserAction() {}

    @AfterReturning(value = "trackUserAction()", returning = "result")
    public void afterUserAction(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();
        System.out.println("User action: Method '" + methodName + "' in class '" + className + "' called with args: " + Arrays.toString(args));
    }
}

