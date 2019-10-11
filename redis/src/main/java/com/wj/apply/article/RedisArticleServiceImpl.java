package com.wj.apply.article;

import com.wj.apply.constant.Constants;
import com.wj.utils.JedisUtils;

import java.util.*;

public class RedisArticleServiceImpl implements RedisArticleService {


    private JedisUtils jedisUtils = new JedisUtils();

    /**
     * 文章发布
     * @param title
     * @param content
     * @param link
     * @param userId
     * @return
     */
    public String postArticle(String title, String content, String link, String userId) {
        //基于redis  key --->唯一标识
        //myslq  主键自增

        //文章ID，自增UUID
        String articleId = String.valueOf(jedisUtils.incr("article:"));
        //用来记录投票 key voted:1
        String voted = "voted:" + articleId;

        jedisUtils.sadd(voted,userId);
        //设置失效时间
        jedisUtils.expire(voted, Constants.ONE_WEEK_IN_SECONDS);
        //删除数据之前，是否要转移？？？？？？
        long now = System.currentTimeMillis() / 1000;
        String article = "article:" + articleId;

        HashMap<String,String> articleData = new HashMap<String,String>();
        articleData.put("title",title);
        articleData.put("link",link);
        articleData.put("user",userId);
        articleData.put("now",String.valueOf(now));
        articleData.put("votes","1");
        //发布一篇文章，hmset article：1 title 标签 baidu.com user username
        jedisUtils.hmset(article,articleData);
        //zadd user:zan 200 james james的点赞数1，返回操作成功的条数1
        jedisUtils.zadd("score:info",Constants.VOTE_SCORE,article); //根据分值排序的有序集合
        jedisUtils.zadd("time:",now,article);  //根据文章发布时间排序文章的有序集合
        return articleId;
    }

    /**
     * 投票
     * @param userId
     * @param article
     */
    public void articleVote(String userId, String article) {
        //计算投票截止时间
        long cutoff = (System.currentTimeMillis() / 1000) - Constants.ONE_WEEK_IN_SECONDS;
        //检测是否还可以对文章进行投票
        if (jedisUtils.zscore("time:",article) < cutoff) {
            return;
        }

        //获取文章主键ID
        String articleId = article.substring(article.indexOf(":") + 1);
        //将投票的用户加入到键为
        if (jedisUtils.sadd("voted:" + articleId,userId) == 1) {
            jedisUtils.zincrby("score:info",Constants.VOTE_SCORE,article);
            jedisUtils.hincrBy(article,"votes",1);  //投票+1
        }
    }

    /**
     * 文章列表查询（分页）
     * @param  page  key
     * @return redis查询结果
     */
    @Override
    public List<Map<String, String>> getArticles(int page, String key) {
        int start = (page - 1) * Constants.ARTICLES_PER_PAGE;
        int end = start + Constants.ARTICLES_PER_PAGE - 1;
        //倒序查询出投票数最高的文章，zset有序集合，分值递减
        Set<String> ids = jedisUtils.zrevrange(key, start, end);
        List<Map<String,String>> articles = new ArrayList<Map<String,String>>();
        for (String id : ids){
            Map<String,String> articleData = jedisUtils.hgetAll(id);
            articleData.put("id", id);
            articles.add(articleData);
        }

        return articles;
    }


}
