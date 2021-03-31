package com.xjr.jieba;

import java.util.ArrayList;
import java.util.List;

public class extractWords {

    public static  List<String> keywords(String content) throws Exception {
        //去除空格和特殊字符
        String regEx = "[\n\r\t`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？^p]";
        String aa = "";
        content = content.replaceAll(regEx, aa).replaceAll("　", "").replaceAll(" ", "");
        List<String> keywords = new ArrayList<>();
        //提取10个关键词
        TfIdfAnalyzer tfIdfAnalyzer = new TfIdfAnalyzer();
        int top = 10;
        List<Keyword> list = tfIdfAnalyzer.analyze(content, top);
        for (Keyword word : list) {
            keywords.add(word.getName());
        }
        return keywords;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(keywords(
                "法国巴黎 香奈儿代购"));
        System.out.println(keywords(
                "的"));
    }
}
