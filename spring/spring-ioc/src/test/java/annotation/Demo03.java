package annotation;

import com.wj.spring.annotation.config.MyConfig3;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试@Component
 */
public class Demo03 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig3.class);


        Object factoryBean = applicationContext.getBean("factoryBean");
        System.out.println(factoryBean.getClass());
        Object factoryBean2 = applicationContext.getBean("&factoryBean");  //注意这里的写法
        System.out.println(factoryBean2.getClass());


        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str : beanDefinitionNames) System.out.println(str);


    }
}
