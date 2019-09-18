package annotation;

import com.wj.spring.annotation.aop.Calculator;
import com.wj.spring.annotation.config.MyConfig7;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Demo09 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig7.class);
        Calculator calculator = applicationContext.getBean(Calculator.class);
        calculator.div(1,0);
        applicationContext.close();
    }
}
