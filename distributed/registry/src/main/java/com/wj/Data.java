package com.wj;

import lombok.Builder;

import java.io.Serializable;

@lombok.Data
@Builder
public class Data implements Serializable {
    private String className;
    private String methodName;
    private Class[] paramTypes;
    private Object[] paramValues;



}
