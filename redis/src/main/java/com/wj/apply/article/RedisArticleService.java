package com.wj.apply.article;

import java.util.List;
import java.util.Map;

public interface RedisArticleService {


    public String postArticle(String title,String content,String link,String userId);

    public void articleVote(String userId,String article);

    public List<Map<String, String>> getArticles(int page, String key);
}
