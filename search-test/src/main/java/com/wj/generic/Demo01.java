package com.wj.generic;

import java.util.ArrayList;
import java.util.List;

public class Demo01 {

    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("aaa");
        list.add(100);
        for (int i = 0 ; i < list.size() ; i++) {
            String item = (String) list.get(i);
            System.out.println("item = " + item);
        }
    }
}
