package com.wj.spring.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

//日志切面类
@Aspect
public class LogAspects {
    /*
    *
    * 此处定义的切面执行表达式
    *   可以将div(int,int)写成通用匹配方式 *(..)
    *
    *   如果每个通知上面都要写这个执行表达式，太麻烦了
    *   可以使用@PointCut注解来解决
    *
    *
    * */

    @Pointcut("execution(public int com.wj.spring.annotation.aop.Calculator.div(int,int))")
    public void pointCut() {}



//    @Before("execution(public int com.wj.spring.annotation.aop.Calculator.div(int,int))")
    @Before("pointCut()")   //写法转变 下面都要这样写 为了区分好看 这样写 有对比
    public void logStart(JoinPoint joinPoint) {
        System.out.println("before" + Arrays.asList(joinPoint.getArgs()) + ",方法名：" + joinPoint.getSignature());
    }

    @After("execution(public int com.wj.spring.annotation.aop.Calculator.div(int,int))")
    public void logEnd() {
        System.out.println("after");
    }

    @AfterReturning("execution(public int com.wj.spring.annotation.aop.Calculator.div(int,int))")
    public void logReturn() {
        System.out.println("afterReturning");
    }

    @AfterThrowing(value = "execution(public int com.wj.spring.annotation.aop.Calculator.div(int,int))",throwing = "exception")
    public void logThrow(Exception exception) {
        System.out.println(exception.getMessage());
        System.out.println("afterThrowing");
    }

    @Around("execution(public int com.wj.spring.annotation.aop.Calculator.div(int,int))")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("执行之前");
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed();   //相当于调用div方法
        } catch (Throwable throwable) {
            throw throwable;
        }

        System.out.println("执行之后");

        return obj;
    }


}
