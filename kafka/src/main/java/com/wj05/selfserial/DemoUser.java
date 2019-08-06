package com.wj05.selfserial;

public class DemoUser {
    private int id;
    private String name;

    public DemoUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public DemoUser(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}