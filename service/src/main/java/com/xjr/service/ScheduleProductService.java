package com.xjr.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xjr.pojo.ProductImage;
import com.xjr.pojo.vo.productVO;

import java.util.List;

public interface ScheduleProductService {

    /**
     * 根据skdId查询所有行程商品信息（productVO需要进行组装）
     * @param skdId
     * @return
     */
    List<productVO> getAllProductVOBySkdId(String skdId);

    /**
     * 根据pid设置商品上下架状态
     * @param pidList
     * @param status
     */
    void setProductStatusByPidList(List<String> pidList, Integer status);

    /**
     * 提交商品信息
     * @param productVO
     * @return
     */
    String submitProducts(productVO productVO);

    /**
     * 查询商品名是否重复
     * @param pName
     * @return
     */
    Boolean isProductNameValid(String pName);

    /**
     * 新增或修改时的商品图片上传
     * @param productImage
     */
    void insertSkdProductImg(ProductImage productImage);

    /**
     * 根据url和pid删除product_image商品图片
     * @param url
     * @param pid
     */
    void deleteProductImageByUrlAndPid(String url, String pid);


    /**
     * 根据pid删除product表中的商品
     * @param pid
     */
    void deleteSkdProduct(String pid);

    /**
     * 根据pid删除product_image中所有pid商品图片
     * @param pid
     */
    void deleteProductImageByPid(String pid);


    /**
     * 根据pid删除product_spec中的所有型号
     * @param pid
     */
    void deleteProductSpecByPid(String pid);

    /**
     * 根据pid删除stock
     * @param pid
     */
    void deleteProductStockByPid(String pid);

    /**
     * 根据pid查询并封装商品VO
     * @param pid
     * @return
     */
    public productVO getProductVOByPid(String pid);
}
