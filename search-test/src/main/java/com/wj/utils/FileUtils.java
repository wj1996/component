package com.wj.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {

    public static void main(String[] args) {
        readFile("G:/file/keyword.txt");
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
        headers.put("cookie","__jdv=122270672|direct|-|none|-|1614844646132; __jdu=1614844646131701224818; areaId=15; ipLoc-djd=15-1213-0-0; PCSYCityID=CN_330000_330100_0; shshshfpa=4f6eb3fc-4a0c-2bfb-6e36-fffba5f8c454-1614844648; __jda=122270672.1614844646131701224818.1614844646.1614844646.1614844646.1; __jdb=122270672.4.1614844646131701224818|1.1614844646; __jdc=122270672; shshshfp=5b1c39e1966f2cbdb66d40d5a5747b7a; shshshsID=6eaf0cbe565f4c64069f2a2ef9a705c1_2_1614844651199; shshshfpb=p2O2d5%2F2qp%20hNU1UriL7sXA%3D%3D; qrsc=1; rkv=1.0; 3AB9D23F7A4B3C9B=I36YWTHGF5NCFP4V4QN6A2DAPJWLXA5Y3BEOTDB5ALH67CA4VX4JRD3LF27P3OCE4JZZLPHWBPX2ZM6Z72F4T4LRQ4");
        headers.put("referer","https://www.jd.com/");
        new SpiderHandler2().spiderData(params,3,headers);
    }
}
