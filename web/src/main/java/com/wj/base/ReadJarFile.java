package com.wj.base;

import java.io.*;
import java.util.StringTokenizer;

public class ReadJarFile {

    public static void main(String[] args) {
//        readJarFile("I:/ws/code/test.jar");

//        readJarFile("C:\\Program Files\\Java\\jdk1.8.0_211\\jre\\lib\\ext\\access-bridge-64.jar");

        test();
    }


    public static void readJarFile(File file) {
        try {
            BufferedReader var2 = new BufferedReader(new FileReader(file));
            file = file.getCanonicalFile();
            String line = "";
            while (null != (line = var2.readLine())) {
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readJarFile(String path) {
        readJarFile(new File(path));
    }


    public static void test() {
        String var0 = System.getProperty("java.ext.dirs");
        File[] var1;
        if (var0 != null) {
            StringTokenizer var2 = new StringTokenizer(var0, File.pathSeparator);
            int var3 = var2.countTokens();
            var1 = new File[var3];

            for(int var4 = 0; var4 < var3; ++var4) {
                var1[var4] = new File(var2.nextToken());
            }
        } else {
            var1 = new File[0];
        }

    }
}
