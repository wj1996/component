package com.wj.utils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HttpClientUtils {


    private final static String GET_METHOD = "GET";
    private final static String POST_METHOD = "POST";

    /**
     * GET请求
     *
     * @param url
     *            请求url
     * @param headers
     *            头部
     * @param params
     *            参数
     * @return
     */
    public static String sendGet(String url, Map<String, String> headers, Map<String, String> params) {
        // 创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        StringBuilder reqUrl = new StringBuilder(url);
        String result = "";
        /*
         * 设置param参数
         */
        if (params != null && params.size() > 0) {
            reqUrl.append("?");
            for (Map.Entry<String, String> param : params.entrySet()) {
//                reqUrl.append(param.getKey() + "=" + param.getValue() + "&");
            }
            reqUrl.append("keyword=" + params.get("keyword")).append("&enc=").append(params.get("enc"));
            reqUrl.append("&wq=" + params.get("wq")).append("&pvid=").append(params.get("pvid")).append("&page=").append(params.get("page"));
//            url = reqUrl.subSequence(0, reqUrl.length() - 1).toString();
            url += reqUrl;
        }
        headers.put(":path","/Search" + reqUrl);
        HttpGet httpGet = new HttpGet(url);
//        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36");
        httpGet.addHeader("User-Agent",SysConstant.userAgents[new Random().nextInt(4)]);
//        httpGet.addHeader("referer",url);
        /**
         * 设置头部
         */
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpGet.addHeader(header.getKey(), header.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            TimeUnit.SECONDS.sleep(1);
            response = client.execute(httpGet);
            /**
             * 请求成功
             */
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
            }
        } catch (IOException e) {

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
            }
        }
        return result;
    }

    /**
     * POST请求
     *
     * @param url
     *            请求url
     * @param headers
     *            头部
     * @param params
     *            参数
     * @return
     */
    public static String sendPost(String url, Map<String, String> headers, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        /**
         * 设置参数
         */
        if (params != null && params.size() > 0) {
            List<NameValuePair> paramList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> param : params.entrySet()) {
                paramList.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            // 模拟表单提交
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, SysConstant.DEFAULT_CHARSET);
                httpPost.setEntity(entity);
            } catch (UnsupportedEncodingException e) {
            }
            /**
             * 设置头部
             */
            if (headers != null && headers.size() > 0) {
                if (headers != null && headers.size() > 0) {
                    for (Map.Entry<String, String> header : headers.entrySet()) {
                        httpPost.addHeader(header.getKey(), header.getValue());
                    }
                }
            }
            CloseableHttpResponse response = null;
            try {
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
            } catch (IOException e) {
            } finally {
                try {
                    if (response != null) {
                        response.close();
                    }
                    client.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }
    /**
     * post请求发送json
     * @param url
     * @param json
     * @param headers
     * @return
     */
    public static String senPostJson(String url, String json, Map<String, String> headers) {
        CloseableHttpClient client = HttpClients.createDefault();
        String result = "";
        HttpPost httpPost = new HttpPost(url);
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        /**
         * 设置头部
         */
        if (headers != null && headers.size() > 0) {
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    httpPost.addHeader(header.getKey(), header.getValue());
                }
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, SysConstant.DEFAULT_CHARSET);
        } catch (IOException e) {
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
            }
        }
        return result;
    }
}
