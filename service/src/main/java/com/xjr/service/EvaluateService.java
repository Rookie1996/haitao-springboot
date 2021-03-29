package com.xjr.service;

import com.xjr.pojo.Comments;

import java.math.BigDecimal;

public interface EvaluateService {

    /**
     * 提交短评方法
     * @param comments
     */
    public void submitComments(Comments comments);


    /**
     * 操作点赞方法 isLike-true表示点赞，-false表示取消点赞
     * @param userId
     * @param skdId
     * @param isLike
     */
    public void operateLikes(String userId, String skdId, Boolean isLike);

    /**
     * 提交评分方法
     * @param userId
     * @param skdId
     * @param stars
     */
    public void submitRates(String userId, String skdId, BigDecimal stars);


    /**
     * 根据评分用户id和被评分行程id，查询评分记录
     * @param userId
     * @param skdId
     * @return
     */
    public BigDecimal getRateStatus(String userId, String skdId);


    /**
     * 根据点赞用户id和被点赞行程id，查询点赞记录
     * @param userId
     * @param skdId
     * @return
     */
    public Boolean getLikeStatus(String userId, String skdId);
}
