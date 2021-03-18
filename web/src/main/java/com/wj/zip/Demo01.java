package com.wj.zip;

import java.io.*;
import java.util.zip.*;

public class Demo01 {

    private static String SUFFIX = ".zip";

    public static void main(String[] args) throws Exception {
//        testCompress("I:\\ws\\code\\test");
//        readCompressFile("I:\\ws\\code\\test.zip");


        testDecompress("I:\\\\ws\\\\code\\\\test.zip","I:/ws/code/test2");

    }


    //处理压缩文件
    public static void readCompressFile(String path) {
        readCompressFile(new File(path));
    }

    public static void readCompressFile(File file) {
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipEntry nextEntry = null;
            while (null != (nextEntry = zipInputStream.getNextEntry())) {
                System.out.println(nextEntry.getName());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void testCompress(String path) {
        testCompress(new File(path));
    }

    public static void testCompress(File file) {
        if (!file.exists()) throw new RuntimeException("文件不存在" + file.getAbsolutePath());
        String parentPath = file.getParent();
        String zipFileName = file.getName() + SUFFIX;
        CheckedOutputStream cos = null;
        ZipOutputStream zos = null;
        try {
            cos = new CheckedOutputStream(new FileOutputStream(new File(parentPath,zipFileName)),new CRC32());
            zos = new ZipOutputStream(cos);
            handleFile(file,zos,"");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != zos) zos.close();
                if (null != cos) cos.close();
            } catch (Exception e) {

            }
        }

    }

    public static void handleFile(File file,ZipOutputStream zos,String parentName) throws IOException {
        if (file.isFile()) {
            compressSingleFile(file,zos,parentName);
            return;
        }
        System.out.println("handleFile name is : " + file.getName() + ",  parentName----" + parentName);
        String path = (null != parentName && "" != parentName) ? parentName + "/" : "";
        ZipEntry zipEntry = new ZipEntry(path + file.getName() + "/");
        zos.putNextEntry(zipEntry);
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                handleFile(f,zos,path + file.getName());
            } else {
                compressSingleFile(f,zos,path + file.getName());
            }

        }
    }

    public static void compressSingleFile(File file,ZipOutputStream zos,String parentName) {
        System.out.println("----compressSingleFile filename is " + file.getName() + ", parentName = " + parentName);
        InputStream is = null;
        try {
            byte[] buf = new byte[1024];
            int readLength = 0;
            ZipEntry zipEntry = null;
            String path = (null != parentName && "" != parentName) ? parentName + "/" : "";
            zipEntry = new ZipEntry(path + file.getName());
            zipEntry.setSize(file.length());
            zipEntry.setSize(file.length());
            zipEntry.setTime(file.lastModified());
            zos.putNextEntry(zipEntry);
            is = new BufferedInputStream(new FileInputStream(file));
            while (-1 != (readLength = is.read(buf,0,buf.length))) {
                zos.write(buf,0,readLength);
            }
        } catch (Exception e) {

        } finally {
            try {
                if (null != is) is.close();
            } catch (Exception e) {

            }

        }
    }


    public static void testDecompress(String path,String parent) throws IOException {
        testDecompress(new File(path),parent);
    }
    public static void testDecompress(File file,String parent) throws IOException {
        BufferedOutputStream bos = null;
        ZipInputStream zipInputStream = null;
        try {
            zipInputStream = new ZipInputStream(new FileInputStream(file));
            ZipEntry zipEntry = null;
            byte[] buf = new byte[1024];
            int readLength = 0;
            while (null != (zipEntry = zipInputStream.getNextEntry())) {
                if (zipEntry.isDirectory()) {
                    File cfile = new File(parent,zipEntry.getName());
                    if (!cfile.exists()) {
                        cfile.mkdirs();
                    }
                    continue;
                }
                File cfile = createFile(parent,zipEntry.getName());
                bos = new BufferedOutputStream(new FileOutputStream(cfile));
                while (-1 != (readLength = zipInputStream.read(buf,0,buf.length))) {
                    bos.write(buf,0,readLength);
                }
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bos) bos.close();
            if (null != zipInputStream) zipInputStream.close();
        }
    }

    private static File createFile(String parent, String name) {
        String[] dirs = name.split("/");
        File file = new File(parent);
        if (dirs.length > 1) { //文件有上级目录
            for (int i = 0 ; i < dirs.length - 1; i++) {
                file = new File(file,dirs[i]);
            }
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return new File(file,dirs[dirs.length - 1]);
    }
}
