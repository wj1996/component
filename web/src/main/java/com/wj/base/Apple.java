package com.wj.base;

public class Apple {

    private int months;
    private String color;
    private String brand;

    public Apple(int months,String color,String brand) {
        System.out.print("test-");
        System.out.println("create apple");
        if (0 == months || null == color || brand == null) {
            System.out.println("属性不能为空");
            return;
        }
        this.months = months;
        this.brand = brand;
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }

    public static void main(String[] args) {
        Apple apple = new Apple(1,"red","shandong");

    }

}




