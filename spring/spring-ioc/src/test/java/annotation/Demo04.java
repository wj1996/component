package annotation;

import com.wj.spring.annotation.config.MyConfig3;
import com.wj.spring.annotation.config.MyConfig4;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试@Component
 */
public class Demo04 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig4.class);
        System.out.println("容器创建完成");
        applicationContext.close();
    }
}
