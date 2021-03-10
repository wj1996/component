package com.wj.generic;

public class Demo03 {

    public static void main(String[] args) {
        InterfaceGeneric interfaceGeneric = new InterfaceGenericImpl();
//        InterfaceGenericImpl interfaceGeneric = new InterfaceGenericImpl();
        System.out.println(interfaceGeneric.get());
    }
}
