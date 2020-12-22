package com.wuhai.aop;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

//用户行为的切面
@Aspect
public class BehaviorAspect {
    // 带有BehaviorTrace的方法构成了这个切面
    @Pointcut("execution(@com.wuhai.aop.BehaviorTrace * *(..))")
    public void annoHaviorTrace() {

    }

    @Around("annoHaviorTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint
                .getSignature();
        // 类名
        String className = methodSignature.getDeclaringType().getSimpleName();
        // 方法名
        String mtdName = methodSignature.getName();
        // 功能名
        BehaviorTrace behaviorTrace = methodSignature.getMethod()
                .getAnnotation(BehaviorTrace.class);
        String fun = behaviorTrace.value();

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        //方法执行后
        long duration = System.currentTimeMillis() - begin;
        Log.d("AOP", String.format("功能：%s功能，%s的%s方法执行，耗时:%d ms ", fun,
                className, mtdName, duration));

        return result;
    }
}
