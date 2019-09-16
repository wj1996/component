package annotation;

import com.wj.spring.annotation.cls2.Bird;
import com.wj.spring.annotation.config.MyConfig5;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


public class Demo05 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig5.class);
        System.out.println("容器创建完成");

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str : beanDefinitionNames) {
            System.out.println(str);
        }

        Bird bird = (Bird) applicationContext.getBean("bird");
        System.out.println(bird);


        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("bird.color"));


    }
}
