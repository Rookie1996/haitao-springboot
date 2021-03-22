package com.xjr.mapper;

import com.xjr.pojo.SkdImage;
import com.xjr.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkdImageMapper extends MyMapper<SkdImage> {

    // 对图片查询是否存在，如存在返回1，不存在返回0
    public int countSkdImage(@Param("url")String url);

    // 删除图片
    // 设置所有skd_id对应的图片 is_del --- 1
    public void deleteSkdImageBySkdId(@Param("skdId")String skdId);

    // 删除图片
    // 设置一张url对应的图片 is_del --- 1
    public void deleteSkdImageByUrl(@Param("url")String url);

    // 根据skd_id查询所有url is_del --- 0
    public List<String> selectUrlsBySkdId(@Param("skdId")String skdId);
}