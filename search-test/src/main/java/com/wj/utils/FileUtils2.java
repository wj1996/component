package com.wj.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtils2 {

    public static void main(String[] args) {
        readFile("G:/file/keyword2.txt");
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
        headers.put("cookie","unpl=V2_ZzNtbUpUShIhXUcGK0oLUmJWQA1KBEYdIQtFUigeVQRkUUFYclRCFnUUR1ZnGFkUZwcZWENcQxxFCEdkeBBVAWMDE1VGZxBFLV0CFSNGF1wjU00zQwBBQHcJFF0uSgwDYgcaDhFTQEJ2XBVQL0oMDDdRFAhyZ0AVRQhHZHsbXgBnBxFcQVRzJXI4dmR8H10EbgUiXHJWc1chVEVdehpeASoDEF9HV0cWdAtFZHopXw==; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_9286ed0cabff4dca9b49e227b6802cb4|1614843618071; __jdu=1334152022; areaId=15; ipLoc-djd=15-1213-0-0; PCSYCityID=CN_330000_330100_0; shshshfpa=94e25741-3e5d-3866-e99e-e43e9a587972-1614843619; shshshfpb=gNp9gUkUGzWNWEawasPILSA==; __jda=122270672.1334152022.1614843617.1614843617.1614843618.1; __jdc=122270672; shshshfp=5b1c39e1966f2cbdb66d40d5a5747b7a; rkv=1.0; qrsc=3; 3AB9D23F7A4B3C9B=I36YWTHGF5NCFP4V4QN6A2DAPJWLXA5Y3BEOTDB5ALH67CA4VX4JRD3LF27P3OCE4JZZLPHWBPX2ZM6Z72F4T4LRQ4; __jdb=122270672.6.1334152022|1.1614843618; shshshsID=bca2c7161656fa4f47b68bd6ae0f75e2_6_1614843677869");
        headers.put("referer","https://www.jd.com/");
        new SpiderHandler2().spiderData(params,1,headers);
    }
}
