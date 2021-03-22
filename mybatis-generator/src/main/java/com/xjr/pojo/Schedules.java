package com.xjr.pojo;

import java.util.Date;
import javax.persistence.*;

public class Schedules {
    @Id
    @Column(name = "skd_id")
    private String skdId;

    @Column(name = "skd_title")
    private String skdTitle;

    @Column(name = "skd_desc")
    private String skdDesc;

    /**
     * 行程图片有默认图片
     */
    @Column(name = "skd_img_url")
    private String skdImgUrl;

    @Column(name = "skd_starttime")
    private Date skdStarttime;

    @Column(name = "skd_endtime")
    private Date skdEndtime;

    /**
     * 点赞数
     */
    @Column(name = "skd_likes")
    private Integer skdLikes;

    /**
     * 平均评星
     */
    @Column(name = "skd_starlevel")
    private Integer skdStarlevel;

    /**
     * 行程目的地
     */
    @Column(name = "skd_destination")
    private String skdDestination;

    /**
     * 0-未完成,1-已完成，2-草稿
     */
    @Column(name = "skd_status")
    private Byte skdStatus;

    /**
     * 行程总览是某个用户发布的
     */
    private String uid;

    /**
     * @return skd_id
     */
    public String getSkdId() {
        return skdId;
    }

    /**
     * @param skdId
     */
    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }

    /**
     * @return skd_title
     */
    public String getSkdTitle() {
        return skdTitle;
    }

    /**
     * @param skdTitle
     */
    public void setSkdTitle(String skdTitle) {
        this.skdTitle = skdTitle;
    }

    /**
     * @return skd_desc
     */
    public String getSkdDesc() {
        return skdDesc;
    }

    /**
     * @param skdDesc
     */
    public void setSkdDesc(String skdDesc) {
        this.skdDesc = skdDesc;
    }

    /**
     * 获取行程图片有默认图片
     *
     * @return skd_img_url - 行程图片有默认图片
     */
    public String getSkdImgUrl() {
        return skdImgUrl;
    }

    /**
     * 设置行程图片有默认图片
     *
     * @param skdImgUrl 行程图片有默认图片
     */
    public void setSkdImgUrl(String skdImgUrl) {
        this.skdImgUrl = skdImgUrl;
    }

    /**
     * @return skd_starttime
     */
    public Date getSkdStarttime() {
        return skdStarttime;
    }

    /**
     * @param skdStarttime
     */
    public void setSkdStarttime(Date skdStarttime) {
        this.skdStarttime = skdStarttime;
    }

    /**
     * @return skd_endtime
     */
    public Date getSkdEndtime() {
        return skdEndtime;
    }

    /**
     * @param skdEndtime
     */
    public void setSkdEndtime(Date skdEndtime) {
        this.skdEndtime = skdEndtime;
    }

    /**
     * 获取点赞数
     *
     * @return skd_likes - 点赞数
     */
    public Integer getSkdLikes() {
        return skdLikes;
    }

    /**
     * 设置点赞数
     *
     * @param skdLikes 点赞数
     */
    public void setSkdLikes(Integer skdLikes) {
        this.skdLikes = skdLikes;
    }

    /**
     * 获取平均评星
     *
     * @return skd_starlevel - 平均评星
     */
    public Integer getSkdStarlevel() {
        return skdStarlevel;
    }

    /**
     * 设置平均评星
     *
     * @param skdStarlevel 平均评星
     */
    public void setSkdStarlevel(Integer skdStarlevel) {
        this.skdStarlevel = skdStarlevel;
    }

    /**
     * 获取行程目的地
     *
     * @return skd_destination - 行程目的地
     */
    public String getSkdDestination() {
        return skdDestination;
    }

    /**
     * 设置行程目的地
     *
     * @param skdDestination 行程目的地
     */
    public void setSkdDestination(String skdDestination) {
        this.skdDestination = skdDestination;
    }

    /**
     * 获取0-未完成,1-已完成，2-草稿
     *
     * @return skd_status - 0-未完成,1-已完成，2-草稿
     */
    public Byte getSkdStatus() {
        return skdStatus;
    }

    /**
     * 设置0-未完成,1-已完成，2-草稿
     *
     * @param skdStatus 0-未完成,1-已完成，2-草稿
     */
    public void setSkdStatus(Byte skdStatus) {
        this.skdStatus = skdStatus;
    }

    /**
     * 获取行程总览是某个用户发布的
     *
     * @return uid - 行程总览是某个用户发布的
     */
    public String getUid() {
        return uid;
    }

    /**
     * 设置行程总览是某个用户发布的
     *
     * @param uid 行程总览是某个用户发布的
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
}