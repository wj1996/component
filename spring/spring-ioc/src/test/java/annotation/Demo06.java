package annotation;

import com.wj.spring.annotation.cls2.MyController;
import com.wj.spring.annotation.cls2.MyService;
import com.wj.spring.annotation.config.MyConfig5;
import com.wj.spring.annotation.config.MyConfig6;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Demo06 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig6.class);
        System.out.println("容器创建完成");

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String str : beanDefinitionNames) {
            System.out.println(str);
        }
        System.out.println("开始》》》》》》》》》》》》》》》》》");
        MyController myController = (MyController) applicationContext.getBean("myController");
        myController.test();

        /*MyService myService = (MyService) applicationContext.getBean("myService");
        System.out.println(myService + "," + myService.getFlag());*/
        MyService myService2 = (MyService) applicationContext.getBean("myService2");
        System.out.println(myService2);


        applicationContext.close();
    }
}
