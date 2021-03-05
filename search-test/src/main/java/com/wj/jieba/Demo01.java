package com.wj.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.List;

public class Demo01 {


    private static JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();

    public static void main(String[] args) {
        String content = "三星 Galaxy S21 5G（SM-G9910）双模5G 骁龙888 超高清专业摄像 120Hz护目";
        /*
        第二个参数：分词模式
         */
        List<SegToken> tokens = jiebaSegmenter.process(content, JiebaSegmenter.SegMode.SEARCH);

        for (SegToken segToken : tokens)
            System.out.println(segToken.word);

    }
}
