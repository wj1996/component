package com.wj.utils;

import com.alibaba.fastjson.JSON;
import com.wj.dao.GoodsInfoDao;
import com.wj.model.GoodsInfo;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.SQLException;
import java.util.*;

public class SpiderService {

    private static String HTTPS_PROTOCOL = "https:";

    GoodsInfoDao goodsInfoDao = new GoodsInfoDao();

    public List<GoodsInfo> spiderData(String url, Map<String, String> params,int page,Map<String,String> headers)  {
        List goodsInfos = new ArrayList();
        for (int i = page ; i < SysConstant.pageSeg + page; i++) {
            System.out.println("当前查询页数为：" + i);
            String html = HttpClientUtils.sendGet(url, headers, params);
            if(!StringUtils.isBlank(html)) {
                List<GoodsInfo> infos = parseHtml(html);
                if (infos == null || infos.size() < 1)
                    System.out.println("获取到的数据为空");
                goodsInfos.addAll(infos);
            }
            try {
                Thread.sleep(new Random().nextInt(200));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            goodsInfoDao.batchInsertData(goodsInfos);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return goodsInfos;
        /*String html = HttpClientUtils.sendGet(url, null, params);
        if(!StringUtils.isBlank(html)) {
            List<GoodsInfo> goodsInfos =parseHtml(html);
            try {
                goodsInfoDao.batchInsertData(goodsInfos);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return goodsInfos;
        }*/
    }
    /**
     * 解析html
     * @param html
     */
    private List<GoodsInfo> parseHtml(String html) {
        //商品集合
        List<GoodsInfo> goods = new ArrayList<GoodsInfo>();
        /**
         * 获取dom并解析
         */
        Document document = Jsoup.parse(html);
        Elements elements = document.
                select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        int index = 0;
        for(Element element : elements) {
            String goodsId = element.attr("data-sku");
            String goodsName = element.select("div[class=p-name p-name-type-2]").select("em").text();
            String goodsPrice = element.select("div[class=p-price]").select("strong").select("i").text();
            String imgUrl = HTTPS_PROTOCOL + element.select("div[class=p-img]").select("a").select("img").attr("data-lazy-img");
            GoodsInfo goodsInfo = new GoodsInfo(goodsId, goodsName, imgUrl, Double.valueOf(goodsPrice));
            goods.add(goodsInfo);
            String jsonStr = JSON.toJSONString(goodsInfo);
//            System.out.println("成功爬取【" + goodsName + "】的基本信息 ");
//            System.out.println(jsonStr);
        }
        return goods;
    }
}
