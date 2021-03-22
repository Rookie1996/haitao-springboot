package com.xjr.service.impl;

import com.xjr.mapper.ScheduleDetailsMapper;
import com.xjr.mapper.SchedulesMapper;
import com.xjr.mapper.SkdDetailImageMapper;
import com.xjr.pojo.ScheduleDetails;
import com.xjr.pojo.Schedules;
import com.xjr.service.SkdDetailService;
import com.xjr.utils.SqlDate;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SkdDetailServiceImpl implements SkdDetailService {

    @Autowired
    private SchedulesMapper schedulesMapper;

    @Autowired
    private ScheduleDetailsMapper scheduleDetailsMapper;

    @Autowired(required = false)
    private Sid sid;

    @Override
    public List<ScheduleDetails> getAllSkdDetatilFromSkd(String skdId) {

        List<ScheduleDetails> list = scheduleDetailsMapper.selectAllSkdDetailBySkdId(skdId);

        return list;
    }

    @Override
    public String submitSkdDetail(ScheduleDetails scheduleDetails) {

        String skdDetailId = scheduleDetails.getSkdDetailId();
//        Integer status = scheduleDetails.getSkdDetailActive();
        // 1、新增行程日记
        if(StringUtils.isEmpty(skdDetailId)){
            // 创建行程日记id
            skdDetailId = sid.nextShort();
            scheduleDetails.setSkdDetailId(skdDetailId);
            scheduleDetailsMapper.insert(scheduleDetails);

        }else{
            // 2、修改行程日记
            scheduleDetailsMapper.updateByPrimaryKey(scheduleDetails);
        }

        return skdDetailId;
    }

    @Transactional
    @Override
    public Boolean isTimePointValid(String skdId, String timepoint) {

        Schedules schedules = schedulesMapper.selectByPrimaryKey(skdId);
        Date startTime = schedules.getSkdStarttime();
        Date endTime = schedules.getSkdEndtime();

        try {
            Date time = SqlDate.convertDateFromString(timepoint);
            // 1、不能制定重复时间点的时间点
            List<ScheduleDetails> list = scheduleDetailsMapper.selectAllSkdDetailBySkdId(skdId);
            for(ScheduleDetails scheduleDetails: list){
                Date tmp = scheduleDetails.getSkdDetailTimepoint();
                if(tmp.getTime() == time.getTime()){
                    // 不能有重复时间点！
                    return false;
                }
            }

            // 2、时间点要在行程时间段之内
            if(time.getTime() < startTime.getTime() || time.getTime() > endTime.getTime()){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void setSkdDetailStatus(String skdDetailId, Integer status) {

        scheduleDetailsMapper.updateStatusBySkdDetailId(skdDetailId, status);
    }
}
