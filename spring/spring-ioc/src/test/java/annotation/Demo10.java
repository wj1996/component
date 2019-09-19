package annotation;

import com.wj.spring.annotation.config.MyConfig8;
import com.wj.spring.annotation.transaction.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Demo10 {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfig8.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.insert();
    }
}
