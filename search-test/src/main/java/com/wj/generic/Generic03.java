package com.wj.generic;

public class Generic03<T> {

    private T value;

    public static void main(String[] args) {
        Generic03<Generic02<String>> t = new Generic03<>();
        //从内到外拆分
        Generic02<String> tt = t.value;


    }
}
