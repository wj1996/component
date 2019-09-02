package com.wj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration  //引入第三方主件
@ComponentScan("com.wj.controller")  //必须组件扫描
/*衍生》》》上面两个注解实现的功能可以用@SpringBootApplication注解实现，一个注解即可
* 见App2
* */
public class App {

    public static void main(String[] args) {
        //启动类
        //定制化
        SpringApplication.run(App.class);
    }



}
