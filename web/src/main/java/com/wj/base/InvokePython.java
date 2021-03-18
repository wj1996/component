package com.wj.base;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class InvokePython {

    public static void main(String[] args) throws IOException, InterruptedException {
        String exe = "python";
        String command = "G:\\dingby\\PyCharm 2018.3.5\\test\\test.py";
        String num1 = "1";
        String num2 = "2";
//        String[] cmdArr = new String[] {exe， command， num1， num2};
        String[] cmdArr = new String[]{exe,command};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        String str = dis.readLine();
        process.waitFor();
        System.out.println(str);
    }
}
