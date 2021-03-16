package com.wj.classloader;

public class Demo01 {

    public static void main(String[] args) throws Exception {
        preRun();
        test2();
        System.out.println();
    }


    public static void preRun() {
        System.setProperty("sun.misc.URLClassPath.debugLookupCache","ok");
        System.setProperty("sun.misc.URLClassPath.debug","ok");
    }


    public static void test1() throws ClassNotFoundException {
        ClassLoader classLoader = Demo01.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());

        ClassLoader extClassLoader = classLoader.getParent();

        Class<?> aClass = extClassLoader.loadClass("com.wj.classloader.Apple");
        System.out.println("1");
    }

    public static void test2() {

    }
}
