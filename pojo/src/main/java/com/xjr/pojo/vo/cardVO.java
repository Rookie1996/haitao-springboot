package com.xjr.pojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class cardVO implements Serializable {

    // 拥有skd的部分属性即可，将一些属性拆分成前端可以使用的cardVO对象

    public String skdId;

    public String title;

    public String desc;

    public String url;

    // 出发和回归的年、月、日拆分
    public Integer start_year;

    public Integer start_month;

    public Integer start_day;

    public String start_detail;

    // 具体周几
    public String start_week_day;

    public Integer end_year;

    public Integer end_month;

    public Integer end_day;

    // 具体周几
    public String end_week_day;

    public String end_detail;

    public String location;

    public Byte status;

    public BigDecimal stars;

    public Integer likes;

    // 行程所属用户id写也无关紧要
    public String uid;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getSkdId() {
        return skdId;
    }

    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStart_year() {
        return start_year;
    }

    public void setStart_year(Integer start_year) {
        this.start_year = start_year;
    }

    public Integer getStart_month() {
        return start_month;
    }

    public void setStart_month(Integer start_month) {
        this.start_month = start_month;
    }

    public Integer getStart_day() {
        return start_day;
    }

    public void setStart_day(Integer start_day) {
        this.start_day = start_day;
    }

    public String getStart_detail() {
        return start_detail;
    }

    public void setStart_detail(String start_detail) {
        this.start_detail = start_detail;
    }

    public String getStart_week_day() {
        return start_week_day;
    }

    public void setStart_week_day(String start_week_day) {
        this.start_week_day = start_week_day;
    }

    public Integer getEnd_year() {
        return end_year;
    }

    public void setEnd_year(Integer end_year) {
        this.end_year = end_year;
    }

    public Integer getEnd_month() {
        return end_month;
    }

    public void setEnd_month(Integer end_month) {
        this.end_month = end_month;
    }

    public Integer getEnd_day() {
        return end_day;
    }

    public void setEnd_day(Integer end_day) {
        this.end_day = end_day;
    }

    public String getEnd_week_day() {
        return end_week_day;
    }

    public void setEnd_week_day(String end_week_day) {
        this.end_week_day = end_week_day;
    }

    public String getEnd_detail() {
        return end_detail;
    }

    public void setEnd_detail(String end_detail) {
        this.end_detail = end_detail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getStars() {
        return stars;
    }

    public void setStars(BigDecimal stars) {
        this.stars = stars;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "cardVO{" +
                "skdId='" + skdId + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", start_year=" + start_year +
                ", start_month=" + start_month +
                ", start_day=" + start_day +
                ", start_detail='" + start_detail + '\'' +
                ", start_week_day='" + start_week_day + '\'' +
                ", end_year=" + end_year +
                ", end_month=" + end_month +
                ", end_day=" + end_day +
                ", end_week_day='" + end_week_day + '\'' +
                ", end_detail='" + end_detail + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                ", stars=" + stars +
                ", likes=" + likes +
                ", uid='" + uid + '\'' +
                '}';
    }
}
