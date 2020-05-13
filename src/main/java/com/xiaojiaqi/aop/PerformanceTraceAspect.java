package com.xiaojiaqi.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/23 12:38 PM
 */
@Aspect
@Component
@Slf4j
public class PerformanceTraceAspect {

    @Pointcut("execution(public void method1())")
    public void method1(){}

    @Pointcut("execution(public void method2())")
    public void method2(){}

    @Pointcut("method1() || method2()")
    public void compositePointcut(){}

    @Around("compositePointcut()")
    public Object performanceTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            return joinPoint.proceed();
        }finally {
            System.out.println(("comsuming time:{}"+ (System.currentTimeMillis() - start)));
        }

    }

}
