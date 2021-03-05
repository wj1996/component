package com.wj.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtils3 {

    public static void main(String[] args) {
        readFile("G:/file/keyword3.txt");
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
        headers.put("cookie","shshshfpa=c89da084-f941-581c-c1ee-8a86fb48ad98-1605858924; shshshfpb=yvJtVCnXEvrZ2tL6g15heug%3D%3D; qrsc=3; pinId=h5zgS-IEMwfsdGSNMbFAu7V9-x-f3wj7; areaId=15; ipLoc-djd=15-1213-3038-59931; __jdv=76161171|baidu-pinzhuan|t_288551095_baidupinzhuan|cpc|0f3d30c8dba7459bb52f2eb5eba8ac7d_0_2fb32e4ac3834b4d81a2214aaa636bb6|1614837863042; __jdu=16148378630411194490172; PCSYCityID=CN_330000_330100_0; __jdc=122270672; rkv=1.0; __jda=122270672.16148378630411194490172.1614837863.1614837863.1614840990.2; shshshfp=a395b0c574c1fe934b7f90d1cf6d6478; __jdb=122270672.8.16148378630411194490172|2.1614840990; shshshsID=95d058aba2ca40a56cae3985bf77be5e_4_1614843312278; 3AB9D23F7A4B3C9B=VTABH5KCMHU7RXWVPI52FW7SY3QVU3RVU4WHMQGS2UCP4LCUT25R6IVPDIJIOB6JVEZ7IOWRIDTXHPHUSMCXCM567M");
        headers.put("referer","https://www.jd.com/");
        new SpiderHandler2().spiderData(params,4,headers);
    }
}
