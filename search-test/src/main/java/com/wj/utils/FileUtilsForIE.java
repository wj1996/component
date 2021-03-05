package com.wj.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtilsForIE {

    public static void main(String[] args) {
        readFile("G:/file/keywordforie.txt");
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
        headers.put("cookie","qrsc=1; shshshfp=b74f22abce53a88f608829feb83bb8be; __jda=76161171.16147738301141858861611.1614773830.1614773830.1614844495.2; __jdu=16147738301141858861611; ipLoc-djd=15-1213-0-0; shshshfpb=ijkv84V9hXyt5EHlGtbENBA%3D%3D; areaId=15; shshshfpa=2d0d7311-e1bb-a464-2b9e-4522425edee3-1614774429; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_1958c338498c42c29518de954ac2f5eb|1614844494946; unpl=V2_ZzNtbUJfRxwnCkRdfhFVV2IAQF5KU0McIFxOUH5JDgYwB0YOclRCFnUUR1ZnGFkUZwcZWUdcRxFFCEdkeBBVAWMDE1VGZxBFLV0CFSNGF1wjU00zQwBBQHcJFF0uSgwDYgcaDhFTQEJ2XBVQL0oMDDdRFAhyZ0AVRQhHZHseXABhBRBUQFZDFXAIT1ByEFsFYAMSbXJQcyVFDE9TfRpfNWYzE20AAx8WdQ9AUnhUXAJnBhRbQF5BFHUIQ1RyHVUMYAMVXUJnQiV2; __jdb=76161171.1.16147738301141858861611|2.1614844495; __jdc=76161171; PCSYCityID=CN_330000_330100_0; shshshsID=b83802c6d1d80e653c9f7d5c8be3e2cd_1_1614844496806");
        headers.put("referer","https://www.jd.com/");
        new SpiderHandler2().spiderData(params,4,headers);
    }
}
