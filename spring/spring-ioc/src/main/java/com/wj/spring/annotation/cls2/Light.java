package com.wj.spring.annotation.cls2;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

//实现BeanNameAware与ApplicationContextAware接口
@Component
public class Light implements BeanNameAware, ApplicationContextAware, EmbeddedValueResolverAware {

    private ApplicationContext applicationContext;

    public void setBeanName(String s) {
        System.out.println("当前bean的名字：" + s);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ioc容器" + applicationContext);
        this.applicationContext = applicationContext;
    }


    /*环境变量 解析*/

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String result = resolver.resolveStringValue("你好${os.name},计算#{3*8}");
        System.out.println(result);

    }
}
