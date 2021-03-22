package com.xjr.service.impl;

import com.xjr.mapper.SkdImageMapper;
import com.xjr.pojo.SkdImage;
import com.xjr.service.SkdImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkdImageServiceImpl implements SkdImageService {

    @Autowired
    private SkdImageMapper skdImageMapper;

    @Override
    public void insertSkdImg(SkdImage skdImage) {
        if (skdImage == null){
            return;
        }
        int cnt = skdImageMapper.countSkdImage(skdImage.getUrl());
        if(cnt != 0){
            return;
        }else{
            // 插入后id自动增加
            // 2021/3/17 (注意id一定要设置自增，不然无法插入)
            skdImageMapper.insertSelective(skdImage);
        }

    }

    @Override
    public void updateSkdImg(SkdImage skdImage) {

    }

    @Override
    public void deleteImgByUrl(String url) {

        skdImageMapper.deleteSkdImageByUrl(url);
    }
}
