package com.wj.boot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        /*
        问题：
            下述的输出类加载器在直接idea中运行main方法和使用java -jar 运行输出的内容一样吗？？？
         */
        System.out.println(MyApplication.class.getClassLoader());
        SpringApplication.run(MyApplication.class);

        //写法转变
        /*SpringApplication springApplication = new SpringApplication(MyApplication.class);
        //通过springApplication来设置相关配置
        springApplication.setBannerMode(Banner.Mode.OFF); //进制banner显示
        springApplication.run(args);*/

    }
}
