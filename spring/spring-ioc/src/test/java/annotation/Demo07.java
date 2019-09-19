package annotation;

import com.wj.spring.annotation.cls2.Moon;
import com.wj.spring.annotation.cls2.Sun;
import com.wj.spring.annotation.config.MyConfig6;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Demo07 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig6.class);
        System.out.println("容器创建完成");

        Moon moon = applicationContext.getBean(Moon.class);
        System.out.println(moon);
        Sun sun = applicationContext.getBean(Sun.class);
        System.out.println(sun + "" + (sun.getMoon() == moon));

        applicationContext.close();


    }
}
