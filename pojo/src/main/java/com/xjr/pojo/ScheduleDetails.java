package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "schedule_details")
public class ScheduleDetails implements Serializable {
    @Id
    @Column(name = "skd_detail_id")
    private String skdDetailId;

    /**
     * 类似朋友圈文案
     */
    @Column(name = "skd_detail_article")
    private String skdDetailArticle;

    /**
     * 定位地点
     */
    @Column(name = "skd_detail_place")
    private String skdDetailPlace;

    /**
     * 行程当前的时间点，行程计划按时间排序
     */
    @Column(name = "skd_detail_timepoint")
    private Date skdDetailTimepoint;

    /**
     * 行程进度，0-进度未完成，1-进度已完成
     */
    @Column(name = "skd_detail_active")
    private Integer skdDetailActive;

    /**
     * 对应的行程id
     */
    @Column(name = "skd_id")
    private String skdId;

    /**
     * @return skd_detail_id
     */
    public String getSkdDetailId() {
        return skdDetailId;
    }

    /**
     * @param skdDetailId
     */
    public void setSkdDetailId(String skdDetailId) {
        this.skdDetailId = skdDetailId;
    }

    /**
     * 获取类似朋友圈文案
     *
     * @return skd_detail_article - 类似朋友圈文案
     */
    public String getSkdDetailArticle() {
        return skdDetailArticle;
    }

    /**
     * 设置类似朋友圈文案
     *
     * @param skdDetailArticle 类似朋友圈文案
     */
    public void setSkdDetailArticle(String skdDetailArticle) {
        this.skdDetailArticle = skdDetailArticle;
    }

    /**
     * 获取定位地点
     *
     * @return skd_detail_place - 定位地点
     */
    public String getSkdDetailPlace() {
        return skdDetailPlace;
    }

    /**
     * 设置定位地点
     *
     * @param skdDetailPlace 定位地点
     */
    public void setSkdDetailPlace(String skdDetailPlace) {
        this.skdDetailPlace = skdDetailPlace;
    }

    /**
     * 获取行程当前的时间点，行程计划按时间排序
     *
     * @return skd_detail_timepoint - 行程当前的时间点，行程计划按时间排序
     */
    public Date getSkdDetailTimepoint() {
        return skdDetailTimepoint;
    }

    /**
     * 设置行程当前的时间点，行程计划按时间排序
     *
     * @param skdDetailTimepoint 行程当前的时间点，行程计划按时间排序
     */
    public void setSkdDetailTimepoint(Date skdDetailTimepoint) {
        this.skdDetailTimepoint = skdDetailTimepoint;
    }

    /**
     * 获取行程进度，0-进度未完成，1-进度已完成
     *
     * @return skd_detail_active - 行程进度，0-进度未完成，1-进度已完成
     */
    public Integer getSkdDetailActive() {
        return skdDetailActive;
    }

    /**
     * 设置行程进度，0-进度未完成，1-进度已完成
     *
     * @param skdDetailActive 行程进度，0-进度未完成，1-进度已完成
     */
    public void setSkdDetailActive(Integer skdDetailActive) {
        this.skdDetailActive = skdDetailActive;
    }

    /**
     * 获取对应的行程id
     *
     * @return skd_id - 对应的行程id
     */
    public String getSkdId() {
        return skdId;
    }

    /**
     * 设置对应的行程id
     *
     * @param skdId 对应的行程id
     */
    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }
}