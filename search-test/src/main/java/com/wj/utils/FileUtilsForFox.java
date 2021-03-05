package com.wj.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtilsForFox {

    public static void main(String[] args) {
        readFile("G:/file/keywordforfox.txt");
    }

    public static void readFile(String path) {
        File file = new File(path);
        if (!file.exists()) return;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            while (null != (line = bufferedReader.readLine())) {
                if (StringUtils.isBlank(line)) continue;
//                System.out.println("获取的数据为：" + line);
                String[] keywords = line.split(" ");
                for (String keyword : keywords) {
                    System.out.println("keyword:" + keyword);
                    handleData(keyword);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void handleData(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("wc", name);
        params.put("enc", "utf-8");
        params.put("keyword", name);
        params.put("pvid","ab0271a61d77427498bd3d6986804909");
        Map<String, String> headers = new HashMap<>();
        headers.put(":authority","search.jd.com");
        headers.put(":method","GET");
        headers.put(":method","GET");
        headers.put("::scheme","https");
        headers.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.put("accept-encoding","gzip, deflate, br");
        headers.put("accept-language","zh-CN,zh;q=0.9");
        headers.put("cache-control","max-age=0");
        headers.put("cookie","__jda=122270672.1614845608759869619781.1614845608.1614845608.1614845609.1; unpl=V2_ZzNtbRECQhB8XRFTKxkMUmIBQghLVkJFdgwWVnoZW1EzVhJcclRCFnUUR1ZnGFkUZwQZXUBcQB1FCEdkex5fDGQzEl9DVUMScg5BZEsaXDVmMxJaQlJKEHEPR119HVQMYAsVXUpRShVFOEZcfyls0fKoytbVBQJXrLjjgtG3bAJiBBRUS1JHJXQ4R2Qwd11IZwQSWEtSRxJ0AUBQcxBbDWADGltLV3MURQs%3d; __jdb=122270672.2.1614845608759869619781|1.1614845609; __jdc=122270672; __jdv=76161171|c.duomai.com|t_16282_130316676|tuiguang|bd048df6a1af43ad800a25a3016eed10|1614845609147; __jdu=1614845608759869619781; areaId=15; ipLoc-djd=15-1213-0-0; PCSYCityID=CN_330000_330100_0; shshshfp=e8ef461d8d93af4b68c78b26879e764c; shshshfpa=dbba0f5d-3c4a-3957-da54-803d182aa0e4-1614845610; shshshsID=4d30cb38aa81bfaf79728bbc508b3868_2_1614845615013; shshshfpb=kvLOmc6JYfhqq3j4%20X%20taZA%3D%3D; qrsc=1; rkv=1.0; 3AB9D23F7A4B3C9B=E7EC2SZL3E4XZBL5L6SLP7PK7ZITIP5MUCCUNR3HR6AWIS2J7QTIH6I7V6BV7HTCJ7SIITJAGLWBQVXDZILAAWAVRY");
        headers.put("referer","https://www.jd.com/");
        new SpiderHandler2().spiderData(params,4,headers);
    }
}
