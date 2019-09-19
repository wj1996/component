package com.wj.spring.annotation.extend.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor...调用到了postProcessBeanFactory");
        int count = beanFactory.getBeanDefinitionCount();
        //拿到所有的bean的定义信息，已经加载到beanFactory，但是bean还没有被创建
        System.out.println("定义数量为：" + count);
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        System.out.println(Arrays.asList(beanDefinitionNames));

    }
}
