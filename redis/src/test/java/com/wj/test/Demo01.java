package com.wj.test;

import com.wj.apply.article.RedisArticleService;
import com.wj.apply.article.RedisArticleServiceImpl;
import org.junit.Test;

public class Demo01 {

    @Test
    public void test1() {
        RedisArticleService redisArticleService = new RedisArticleServiceImpl();

        //String title,String content,String link,String userId
//        redisArticleService.postArticle("title1","内容1","www.baidu.com","1");

//        redisArticleService.postArticle("title2","内容2","www.baidu2.com","2");
//
//        redisArticleService.postArticle("title3","内容3","www.baidu3.com","3");


        redisArticleService.postArticle("title4","内容4","www.baidu4.com","4");

    }


    @Test
    public void test2() {
        RedisArticleService redisArticleService = new RedisArticleServiceImpl();
        redisArticleService.articleVote("6","article:1");

    }
}
