package annotation;

import com.wj.spring.annotation.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试@Component
 */
public class Demo01 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str : beanDefinitionNames) System.out.println(str);
    }
}
