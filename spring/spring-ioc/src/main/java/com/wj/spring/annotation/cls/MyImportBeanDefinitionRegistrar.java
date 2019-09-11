package com.wj.spring.annotation.cls;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param annotationMetadata
     * @param beanDefinitionRegistry  BeanDefinition注册类
     *                                把所有需要添加到容器的bean加入；
     *
     */
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //判断容器中是否存在该对象
        boolean b = beanDefinitionRegistry.containsBeanDefinition("com.wj.spring.annotation.cls.Dog");
        boolean b2 = beanDefinitionRegistry.containsBeanDefinition("com.wj.spring.annotation.cls.Cat");
        /*
            对于要注册的bean
            要对bean进行封装 成RootBeanDefinition
         */

        if (b && b2) {
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Pig.class);
            beanDefinitionRegistry.registerBeanDefinition("pig",beanDefinition);
        }

    }
}
