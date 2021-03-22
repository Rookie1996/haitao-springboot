package com.xjr.mapper;

import com.xjr.pojo.ScheduleDetails;
import com.xjr.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleDetailsMapper extends MyMapper<ScheduleDetails> {

    // 根据skdId查询所有行程日记
    public List<ScheduleDetails> selectAllSkdDetailBySkdId(@Param("skdId")String skdId);

    // 根据skdDetailId更新状态
    void updateStatusBySkdDetailId(String skdDetailId, Integer status);
}