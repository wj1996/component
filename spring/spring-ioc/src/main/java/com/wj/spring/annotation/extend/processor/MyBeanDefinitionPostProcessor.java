package com.wj.spring.annotation.extend.processor;

import com.wj.spring.annotation.cls.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionPostProcessor implements BeanDefinitionRegistryPostProcessor {


    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionPostProcessor....postProcessBeanDefinitionRegistry,bean的数量" + registry.getBeanDefinitionCount());
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class);
        registry.registerBeanDefinition("hello",rootBeanDefinition);
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionPostProcessor....postProcessBeanFactory,bean的数量" + beanFactory.getBeanDefinitionCount());
    }
}
