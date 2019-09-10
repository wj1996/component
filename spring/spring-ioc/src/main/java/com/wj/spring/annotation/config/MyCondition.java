package com.wj.spring.annotation.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition implements Condition {


    /**
     * @param conditionContext   可以使用的上下文
     * @param annotatedTypeMetadata 注解的信息
     * @return
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 是否为Windows环境
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        // 获取当前环境变量
        Environment environment = conditionContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        if (osName.toUpperCase().contains("WINDOWS")) {
            return true;
        }
        return false;
    }
}
