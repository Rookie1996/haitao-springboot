package com.xjr.service.impl;

import com.xjr.mapper.CommentsMapper;
import com.xjr.mapper.RateMapper;
import com.xjr.mapper.SchedulesMapper;
import com.xjr.mapper.UsersLikeSchedulesMapper;
import com.xjr.pojo.Comments;
import com.xjr.pojo.Rate;
import com.xjr.pojo.UsersLikeSchedules;
import com.xjr.service.EvaluateService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private RateMapper rateMapper;

    @Autowired
    private UsersLikeSchedulesMapper usersLikeSchedulesMapper;

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private SchedulesMapper schedulesMapper;

    @Autowired(required = false)
    private Sid sid;


    @Transactional
    @Override
    public void submitComments(Comments comments) {

        if(comments == null){
            return;
        }
        String id = sid.nextShort();
        String skdId = comments.getSkdId();
        String toUserId = schedulesMapper.selectUserIdBySkdId(skdId);
        Byte status = 0;
        Date createTime = new Date(); // 取当前时间Date

        comments.setId(id);
        comments.setToUserId(toUserId);
        comments.setStatus(status);
        comments.setCreateTime(createTime);

        // 插入comments对象
        commentsMapper.insertSelective(comments);
    }

    @Override
    public void operateLikes(String userId, String skdId, Boolean isLike) {

        if(isLike){
            UsersLikeSchedules usersLikeSchedules = new UsersLikeSchedules();
            String toUserId = schedulesMapper.selectUserIdBySkdId(skdId);
            usersLikeSchedules.setFromUserId(userId);
            usersLikeSchedules.setToUserId(toUserId);
            usersLikeSchedules.setCreateTime(new Date());
            usersLikeSchedules.setSkdId(skdId);

            // 插入用户取消或点赞记录
            usersLikeSchedulesMapper.insertSelective(usersLikeSchedules);

        }else{
            usersLikeSchedulesMapper.deleteByUserIdAndSkdId(userId, skdId);
        }
    }

    @Transactional
    @Override
    public void submitRates(String skdId, String userId, BigDecimal stars) {

        String toUserId = schedulesMapper.selectUserIdBySkdId(skdId);
        Rate rate = new Rate();
        rate.setFromUserId(userId);
        rate.setToUserId(toUserId);
        rate.setCreateTime(new Date());
        rate.setStars(stars);
        rate.setSkdId(skdId);

        BigDecimal tmp = rateMapper.selectRateByUserIdAndSkdId(userId, skdId);
        if(tmp == null){
            // 插入评分记录
            rateMapper.insertSelective(rate);
        }else{
            rateMapper.updateRateByUserIdAndSkdId(userId, skdId, stars);
        }


    }

    @Override
    public BigDecimal getRateStatus(String userId, String skdId) {

        BigDecimal rate = rateMapper.selectRateByUserIdAndSkdId(userId, skdId);

        if(rate == null){
            return new BigDecimal(0);
        }else {
            return rate;
        }

    }

    @Override
    public Boolean getLikeStatus(String userId, String skdId) {

        Integer cnt = usersLikeSchedulesMapper.selectCntByUserIdAndSkdId(userId, skdId);

        if(cnt!=0){
            return false;
        }else{
            return true;
        }
    }
}
