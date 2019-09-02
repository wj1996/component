package com;

public class Test2 {

    public static void main(String[] args) {
        User user = new User();
        test1(user);
        System.out.println(user);
    }

    public static void test1(User user) {
        user.setAge(11);
    }
}

class User {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
