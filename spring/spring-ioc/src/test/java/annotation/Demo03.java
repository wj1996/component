package annotation;

import com.wj.spring.annotation.config.MyConfig3;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试@Component
 */
public class Demo03 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig3.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str : beanDefinitionNames) System.out.println(str);
    }
}
