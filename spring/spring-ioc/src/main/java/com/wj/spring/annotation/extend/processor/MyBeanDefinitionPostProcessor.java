package com.wj.spring.annotation.extend.processor;

import com.wj.spring.annotation.cls.Dog;
import com.wj.spring.annotation.cls2.Moon;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;

@Component
public class MyBeanDefinitionPostProcessor implements BeanDefinitionRegistryPostProcessor {


    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanDefinitionPostProcessor....postProcessBeanDefinitionRegistry,bean的数量" + registry.getBeanDefinitionCount());

        //注册bean 方式1
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Dog.class);
        registry.registerBeanDefinition("hello",rootBeanDefinition);

        //注册bean 方式2
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Moon.class).getBeanDefinition();
        registry.registerBeanDefinition("hello2",beanDefinition);
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionPostProcessor....postProcessBeanFactory,bean的数量" + beanFactory.getBeanDefinitionCount());
    }
}
