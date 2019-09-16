package com.wj.spring.annotation.cls2;

import org.springframework.beans.factory.annotation.Value;

public class Bird {


    /*
    * 使用@Value赋值，1.基本字符 2.SpringEL表达式 3. properties文件
    * */

    @Value("james")
    private String name;
    @Value("#{20-2}")
    private Integer age;


    @Value("${bird.color}")
    private String color;

    public Bird() {
    }

    public Bird(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
