package com.xjr.mapper;

import com.xjr.pojo.SkdDetailImage;
import com.xjr.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkdDetailImageMapper extends MyMapper<SkdDetailImage> {

    // 根据url查询是否存在行程日记图片
    public int countSkdDetailImage(@Param("url")String url);

    // 根据外键skdDetailId查询所有urls
    public List<String> selectAllBySkdDetailId(@Param("skdDetailId")String skdDetailId);
}