package com.wj.generic;

public class MethodGeneric {


    public static void main(String[] args) {
        test("hah");
    }

    public static <T> void test(T a) {
        System.out.println(a);
    }
}
