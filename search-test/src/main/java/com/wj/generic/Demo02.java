package com.wj.generic;

import java.util.ArrayList;
import java.util.List;

public class Demo02 {

    public static void main(String[] args) {
        List<Integer> iList = new ArrayList<>();
        List<String> sList = new ArrayList<>();

        Class clazz1 = iList.getClass();
        Class clazz2 = sList.getClass();

        if (clazz1.equals(clazz2)) {
            System.out.println("equal");
        }
    }
}

