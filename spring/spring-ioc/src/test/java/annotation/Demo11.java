package annotation;

import com.wj.spring.annotation.config.MyConfig9;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Demo11 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig9.class);
        applicationContext.close();
    }
}
