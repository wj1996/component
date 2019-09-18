package com.wj.spring.annotation.config;

import com.wj.spring.annotation.aop.Calculator;
import com.wj.spring.annotation.aop.LogAspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
日志切面类的方法需要动态感知div()方法运行
通知方法：
    前置通知：执行div()方法之前运行 @Before
    后置通知：执行div()方法结束后运行（不管是否异常）@After
    返回通知：执行div()方法正常返回后运行 @AfterReturning
    异常通知：执行div()方法异常时执行 @AfterThrowing
    环绕通知：动态代理，执行joinPoint.procced() @Around  手动执行目标方法   执行顺序比其他的要优先

 */
@Configuration
@EnableAspectJAutoProxy   //开启支持AOP
public class MyConfig7 {


    @Bean
    public Calculator calculator() {
        return new Calculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }

}
