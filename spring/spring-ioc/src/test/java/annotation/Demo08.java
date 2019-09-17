package annotation;

import com.wj.spring.annotation.config.MyConfig6;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Demo08 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig6.class);
        System.out.println("容器创建完成" + applicationContext);
        applicationContext.close();
    }
}
