package com.xjr.pojo.vo;

import java.io.Serializable;

public class skdDetailVO implements Serializable {

    public String skdDetailId;

    public String article;

    public String loc;

    public String timepoint;

    public Integer status;

    public String skdId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSkdDetailId() {
        return skdDetailId;
    }

    public void setSkdDetailId(String skdDetailId) {
        this.skdDetailId = skdDetailId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getTimepoint() {
        return timepoint;
    }

    public void setTimepoint(String timepoint) {
        this.timepoint = timepoint;
    }

    public String getSkdId() {
        return skdId;
    }

    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }

    @Override
    public String toString() {
        return "skdDetailVO{" +
                "skdDetailId='" + skdDetailId + '\'' +
                ", article='" + article + '\'' +
                ", loc='" + loc + '\'' +
                ", timepoint='" + timepoint + '\'' +
                ", status=" + status +
                ", skdId='" + skdId + '\'' +
                '}';
    }
}
