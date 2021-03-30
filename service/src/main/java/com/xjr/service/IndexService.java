package com.xjr.service;

import com.xjr.pojo.Comments;
import com.xjr.pojo.ScheduleDetails;
import com.xjr.pojo.vo.commentVO;
import com.xjr.pojo.vo.productVO;
import com.xjr.pojo.vo.skdDetailAndImgVO;
import com.xjr.pojo.vo.skdDetailVO;
import com.xjr.utils.PagedResult;

import java.math.BigDecimal;
import java.util.List;

public interface IndexService {

    /**
     * 根据当前页码和一页展示条数，以及默认的规则分页展示行程卡片
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PagedResult getSkdCards(Integer currentPage, Integer pageSize);

    /**
     * 根据skdId查询总行程所有的图片
     * @param skdId
     * @return
     */
    public List<String> getScheduleImages(String skdId);

    /**
     * 根据skdId获取该次行程中发布的所有的行程日记
     * @param skdId
     * @return
     */
    public List<skdDetailAndImgVO> getAllSkdDetails(String skdId);

    /**
     * 根据skdId获取该次行程中发布的所有的行程商品
     * @param skdId
     * @return
     */
    public List<productVO> getAllSkdProducts(String skdId);


    /**
     * 根据skdId获取该次行程中发布的所有的行程短评
     * @param skdId
     * @return
     */
    public List<commentVO> getAllSkdComments(String skdId);

    /**
     * 根据skdId查询该行程获得的点赞数
     * @param skdId
     * @return
     */
    public Integer getScheduleLikes(String skdId);


    /**
     * 根据skdId查询该行程评分stars的平均分
     * @param skdId
     * @return
     */
    public BigDecimal getScheduleStars(String skdId);


    /**
     * 获取所有的热搜词
     * @return
     */
    public List<String> getHotWords();


    /**
     * 将查询记录存储到数据库中
     * @param searchContent
     * @param loc
     * @param keywords
     * @param userId
     */
    void saveSearchRecords(String searchContent, String loc, String keywords, String userId);

}
