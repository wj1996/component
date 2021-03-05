package com.wj.utils;

public interface SysConstant {
    /**
     * 系统默认字符集
     */
    String DEFAULT_CHARSET = "utf-8";
    /**
     * 需要爬取的网站
     */
    String BASE_URL = "http://search.jd.com/Search";

    interface Header {
        String ACCEPT = "Accept";
        String ACCEPT_ENCODING = "Accept-Encoding";
        String ACCEPT_LANGUAGE = "Accept-Language";
        String CACHE_CONTROL = "Cache-Controle";
        String COOKIE = "Cookie";
        String HOST = "Host";
        String PROXY_CONNECTION = "Proxy-Connection";
        String REFERER = "Referer";
        String USER_AGENT = "User-Agent";
    }
    /**
     * 默认日期格式
     */
    String DEFAULT_DATE_FORMAT = "yyy-MM-dd HH:mm:ss";


    int pageSeg = 1;

    String[] userAgents = {
            "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
            "Mozilla/5.0",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36 Edg/88.0.705.81",
            "Mozilla/5.0",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36",
    };
}
