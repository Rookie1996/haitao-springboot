package com.xjr.service;

import com.xjr.pojo.Schedules;
import com.xjr.utils.PagedResult;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

public interface ScheduleService {


    /**
     * 分页获取草稿行程信息的方法
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public PagedResult getDraftSkds(String userId, Integer page, Integer pageSize);

    /**
     * 分页获取已发布行程信息的方法
     * @param userId
     * @param page 当前页码
     * @param pageSize 每页显示数量
     * @return
     */
    public PagedResult getPublishedSkds(String userId, Integer page, Integer pageSize);

    /**
     * 按uid查询所有状态的行程信息的通用方法
     * @param uid
     * @return
     */
    public List<Schedules> selectSkdByUIdAndStatus(String uid, Byte status);

    /**
     * 提交行程总览
     * @param schedules
     * @return
     */
    public String submitSkd(Schedules schedules);

    /**
     * 行程删除
     * @param skdId
     */
    public void deleteSkd(String skdId);

    /**
     * 完成行程
     * @param skdId
     */
    public void finishSkd(String skdId);

    /**
     * 根据skdId更新主图片url
     * @param skdId
     * @param uploadFilePath
     */
    public void submitMainUrl(String skdId, String uploadFilePath);

    /**
     * 根据uid，查询schedules表，[已发布]的行程时间段 (在草稿发布和行程修改时可重用)
     * @param startTime
     * @param endTime
     * @return
     */
    public Boolean isValidTimeInterval(String uid, String startTime, String endTime);

    /**
     * 根据skdId查询schedule
     * @param skdId
     * @return
     */
    public Schedules selectSkdByPrimaryKey(String skdId);


    /**
     * 根据skdId在skd_img中查询所有行程图片
     * @param skdId
     * @return
     */
    public List<String> selectAllImgBySkdId(String skdId);

    /**
     * 根据url在schedules中将url替换为空字符串
     * @param url
     */
    public void deleteUrlIfExist(String url);

    /**
     * 根据各种条件查询行程
     * @param keywords
     * @param countMap
     * @param currentPage
     * @param pageSize
     * @param skdTime
     * @param likesOrSales
     * @return
     */
    public PagedResult getSkdsByCondition(List<String>keywords, Map<String,Integer>countMap, Integer currentPage,
                                          Integer pageSize, String skdTime, String likesOrSales, String status);
}
