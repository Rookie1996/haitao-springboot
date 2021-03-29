package com.xjr.mapper;

import com.xjr.pojo.ProductImage;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageMapper extends MyMapper<ProductImage> {

    /**
     * 根据pid查询所有的商品图片
     * @param pid
     * @return
     */
    public List<String> selectUrlsByPid(String pid);

    /**
     * 根据url判断商品图片是否已存在
     * @param url
     * @return
     */
    public Integer countSkdProductImage(String url);


    /**
     * 根据url和pid设置is_del为1
     * @param url
     * @param pid
     */
    public void deleteImageByUrlAndPid(String url, String pid);

    /**
     * 根据pid设置is_del为1
     * @param pid
     */
    public void deleteImageByPid(String pid);

}