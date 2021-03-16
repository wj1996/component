package com.wj.classloader;

/**
 * F2 查看文档
 */
public class Demo02 {


    public static void main(String[] args) {
        int[] iArr = new int[]{1,3,10};
        //数组类中的对象不是由类加载创建的，但是它的加载器跟加载元素的加载器是相同的，如果数据数据类型位基础数据，则无类加载器
        System.out.println(iArr.getClass().getClassLoader());
        String[] sArr = new String[]{"a","b","c"};
        System.out.println(String.class.getClassLoader());
        System.out.println(sArr.getClass().getClassLoader());
        p();
        A[] a = new A[10];
        System.out.println(a.getClass().getClassLoader());
        System.out.println(A.class.getClassLoader());
    }

    public static void p() {
        System.out.println("------------------------------");
    }
}

class A {

}