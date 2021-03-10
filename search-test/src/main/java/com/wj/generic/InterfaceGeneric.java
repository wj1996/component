package com.wj.generic;

public interface InterfaceGeneric<T> {
    T get();
}
class Impl1 implements InterfaceGeneric {

    @Override
    public Object get() {
        return null;
    }
}

class Impl2<T> implements InterfaceGeneric<T> {
    @Override
    public T get() {
        return null;
    }
}
class Impl3<T> implements InterfaceGeneric {

    @Override
    public Object get() {
        return null;
    }
}

class Imp4 implements InterfaceGeneric<String> {
    @Override
    public String get() {
        return null;
    }
}


