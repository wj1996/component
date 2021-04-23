package com.wj.test;

import com.wj.Rpc;
import com.wj.RpcServer;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.List;

public class RpcServerTest {

    @Test
    public void test() throws IOException {
        new RpcServer(10000).start();
    }

    @Test
    public void test2() throws IOException {
        new RpcServer(10001).start();
    }

    @Test
    public void test3() throws Exception {
        File file = new File("D:\\ws\\component\\component\\distributed\\rpc-server\\target\\classes");
        File[] files = file.listFiles();
        for (File f : files) {
           handleFile(f,"");
        }
    }

    public List<Class> handleFile(File file, String pkgName) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files)
                handleFile(f,getPkgName(pkgName,file.getName()));
        } else {
            if (file.getName().endsWith(".class") || !file.getName().contains("$")) {
               handleAnnotation(pkgName,file.getName());
            }
        }
        return null;
    }

    private void handleAnnotation(String pkgName,String name) {
        try {
            Class<?> clazz = Class.forName(pkgName + "." + name.split(".class")[0]);
            Rpc annotation = clazz.getDeclaredAnnotation(Rpc.class);
            if (null != annotation) {
                Class<?>[] interfaces = clazz.getInterfaces();
                if (null != interfaces && interfaces.length == 1) {
                    Method[] methods = interfaces[0].getMethods();
                    interfaces[0].getName();
                    for (Method method : methods) {

                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String getPkgName(String pkgName, String name) {
        if ("" == pkgName) return name;
        return pkgName + "." + name;
    }
}
