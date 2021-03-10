package com.wj.generic;

public class Demo04 {

    public static void main(String[] args) {
       /* Object score = 40;
        int num = (int)score; //强制转换
        System.out.println(num);*/
        Student student = new Student();
        student.setJava(100);
        student.setOracle(80);

        int java = (Integer)student.getJava();
        System.out.println(java);
        //手动类型检测 防止类型转换异常
        if (student.getOracle() instanceof String) {
            String oracle = (String)student.getOracle();
        }
    }
}
