package com.xjr.service;

import com.xjr.pojo.SkdDetailImage;

import java.util.List;

public interface SkdDetailImageService {

    // 插入行程日记图片接口 url已存在的不需要插入
    public void insertSkdDetailImg(SkdDetailImage skdDetailImage);

    // 根据skdDetailId查询所有的图片url
    public List<String> selectAllUrlsBySkdDetailId(String skdDetailId);

}
