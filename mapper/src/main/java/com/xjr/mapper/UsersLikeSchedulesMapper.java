package com.xjr.mapper;

import com.xjr.pojo.UsersLikeSchedules;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersLikeSchedulesMapper extends MyMapper<UsersLikeSchedules> {

    // 根据行程id查询被点赞数量
    public Integer selectNumsBySkdId(String skdId);

    // 根据userId和skdId删除操作记录
    public void deleteByUserIdAndSkdId(String userId, String skdId);

    // 根据userId和skdId查询点赞操作记录
    public Integer selectCntByUserIdAndSkdId(String userId, String skdId);

}