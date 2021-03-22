package com.xjr.mapper;

import com.xjr.pojo.Schedules;
import com.xjr.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulesMapper extends MyMapper<Schedules> {

    /**
     * 改造为查询已发布行程
     * //按uid查询正在进行的行程
     * @param uid
     * @return
     */
    public List<Schedules> selectPublishedSkdByUid(@Param("uid")String uid);


    /**
     * 按uid和单一行程状态查询行程信息
     * @param uid
     * @param status
     * @return
     */
    public List<Schedules> selectSkdByUIdAndStatus(@Param("uid")String uid, @Param("status")Byte status);

    /**
     * 根据url删除主图片,设置为null
     * @param url
     */
    public void updateNullByUrl(@Param("url")String url);


}