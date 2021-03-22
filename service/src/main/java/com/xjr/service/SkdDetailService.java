package com.xjr.service;

import com.xjr.pojo.ScheduleDetails;

import java.util.List;

public interface SkdDetailService {

    /**
     * 根据skdId获取该次行程中所有的行程日记
     * @param skdId
     * @return
     */
    public List<ScheduleDetails> getAllSkdDetatilFromSkd(String skdId);

    /**
     * 提交行程日记方法
     * @param scheduleDetails
     * @return
     */
    public String submitSkdDetail(ScheduleDetails scheduleDetails);

    /**
     * 判断日记的时间点是否在行程的时间段之内
     * @param skdId
     * @param timepoint
     * @return
     */
    public Boolean isTimePointValid(String skdId, String timepoint);

    /**
     * 根据skdDetailId修改状态为status
     * @param skdDetailId
     * @param status
     */
    void setSkdDetailStatus(String skdDetailId, Integer status);
}
