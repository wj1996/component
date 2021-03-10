package com.wj.generic;

public class Person<T> {

    private T value;

    public void setValue(T value) {
        this.value = value;
    }




    public static void test(Person<Integer> a) {

    }
    //问号表示类型不定
    public static void test2(Person<?> a){

    }
    public static void test3(Person<Object> a) {

    }

    public static void main(String[] args) {
        Person person = new Person(); //没有加上泛型会有警告
        Person<Object> person1 = new Person<>();
        test3(person);
        test3(person1);

        test2(person);
        test2(person1);

        test(person);
//        test(person1);  //报错 person相当于object，不完全等同于object

    }
}
