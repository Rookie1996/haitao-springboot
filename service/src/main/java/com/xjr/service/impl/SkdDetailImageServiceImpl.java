package com.xjr.service.impl;

import com.xjr.mapper.SkdDetailImageMapper;
import com.xjr.pojo.SkdDetailImage;
import com.xjr.service.SkdDetailImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkdDetailImageServiceImpl implements SkdDetailImageService {

    @Autowired
    private SkdDetailImageMapper skdDetailImageMapper;

    @Override
    public void insertSkdDetailImg(SkdDetailImage skdDetailImage) {
        if(skdDetailImage == null){
            return;
        }
        int cnt = skdDetailImageMapper.countSkdDetailImage(skdDetailImage.getUrl());
        if(cnt != 0){
            return;
        }
        // 直接插入即可id自增
        skdDetailImageMapper.insertSelective(skdDetailImage);

    }

    @Override
    public List<String> selectAllUrlsBySkdDetailId(String skdDetailId) {

        List<String> list = skdDetailImageMapper.selectAllBySkdDetailId(skdDetailId);

        return list;
    }
}
