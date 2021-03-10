package com.wj.jvm.agent;

import java.lang.instrument.Instrumentation;

/**
 * 对象内存分配
 */
public class ObjectSizeAgent {

    private static Instrumentation inst;

    public static void premain(String agentArgs, Instrumentation _inst) {
        inst = _inst;
    }

    public static long sizeOf(Object o) {
        return inst.getObjectSize(o);
    }

}
