package com.wj.generic;

public class Fruit {
    private String color;

    public static void main(String[] args) {
//        A<Fruit> a = new A<Apple>(); //能这些写吗？
        Fruit apple = new Apple();
    }
}

class Apple extends Fruit {

}

class Apple2 extends Apple {

}
