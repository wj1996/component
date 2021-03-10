package com.wj.generic;

public class ClassGeneric<T> {

    private T key;

    public ClassGeneric(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }
}
