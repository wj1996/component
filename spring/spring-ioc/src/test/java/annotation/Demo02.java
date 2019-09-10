package annotation;

import com.wj.spring.annotation.config.MyConfig;
import com.wj.spring.annotation.config.MyConfig2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试@Component
 */
public class Demo02 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig2.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str : beanDefinitionNames) System.out.println(str);
    }
}
