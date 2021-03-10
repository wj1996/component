package com.wj.generic;

public class Generic02<T> {

    public static void main(String[] args) {
//        Generic02<?> generic02 = new Generic02<?>(); //不支持，只能用在声明上
        Generic02<?> generic02 = new Generic02<>();
        Generic02<?> generic03 = new Generic02<String>();


        test(new Generic02<>()); //这样写，可以，此时的类型到底是什么 (泛型檫除)
        test(new Generic02<Apple>());
        test(new Generic02<Apple2>());
//        test(new Generic02<Object>()); //报错
//        test(new Generic02<String>()); //报错

        test3(new Generic02<>());
        test3(new Generic02<Apple>());
        test3(new Generic02<Apple2>());
        test3(new Generic02<Object>());
        test3(new Generic02<String>());

        test2(new Generic02<>());
//        test2(new Generic02<Object>()); //报错
        test2(new Generic02<Fruit>());
//        test2(new Generic02<Apple>()); //报错

        test6(new Generic02<>());
        test6(new Generic02<Object>());
        test6(new Generic02<Fruit>());
        test6(new Generic02<Apple>());
//        test6(new Generic02<Apple2>()); //报错
    }

    public static void test3(Generic02<?> gen) {

    }

    public static void test2(Generic02<Fruit> gen) {

    }

    public static void test(Generic02<? extends Fruit> gen) {

    }

    public static void test6(Generic02<? super Apple> gen) {

    }
}
