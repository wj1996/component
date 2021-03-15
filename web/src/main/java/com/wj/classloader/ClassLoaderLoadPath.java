package com.wj.classloader;

public class ClassLoaderLoadPath {

    public static void main(String[] args) {

        getLoadPath();
    }

    static void getLoadPath() {
        //BootStrapClassLoader 加载的 (JVM内置的)
        String[] strs = System.getProperty("sun.boot.class.path").split(";");
        for (String str : strs)
        System.out.println("------------------------------------------");
        //ExtClassLoader
        strs = System.getProperty("java.ext.dirs").split(";");
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
