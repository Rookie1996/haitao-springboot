package com.xjr.mapper;

import com.xjr.pojo.Rate;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface RateMapper extends MyMapper<Rate> {

    // 根据行程id查询评分平均数
    public BigDecimal selectAverageStarsBySkdId(String skdId);

    // 根据评分用户id和被评分行程id，查询评分记录
    public BigDecimal selectRateByUserIdAndSkdId(String userId, String skdId);

    // 根据评分用户id和被评分行程id，更新评分记录
    public void updateRateByUserIdAndSkdId(String userId, String skdId, BigDecimal stars);
}