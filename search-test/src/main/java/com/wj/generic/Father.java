package com.wj.generic;

/**
 * 要么同时檫除，要么子类大于等于父类的类型
 * 不能子类檫除，父类泛型
 * 属性类型：
 *      父类中，随父类而定
 *      子类中，随子类而定
 * 方法重写：
 *      随父类而定
 *
 * @param <T>
 */
public abstract class Father<T> {

    T name;

    abstract void test(T t);
}

/**
 * 子类声明时指定具体类型
 */
class Child1 extends Father<String> {

    String t2;
    @Override
    void test(String name) {

    }
}

/**
 * 子类为泛型类
 */
class Child2<T> extends Father<T> {
    String t2;
    @Override
    void test(T name) {
    }
}

/**
 * 子类为泛型类，父类不指定类型，泛型的檫除，使用Object替换
 * @param <T>
 */
class Child3<T> extends Father {

    T name;
    @Override
    void test(Object o) {

    }
}

/**
 * 子类父类同时檫除泛型
 */
class Child4 extends Father {

    @Override
    void test(Object o) {

    }
}
/**
 * 子类檫除，父类泛型 (错误)
 */
/*class Child5 extends Father<T> {

    @Override
    void test(T t) {

    }
}*/
