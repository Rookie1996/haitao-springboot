package com.xjr.pojo.vo;

import java.io.Serializable;
import java.util.Arrays;

public class skdVO implements Serializable {

    private String id;

    private String title;

    private String desc;

    private String[] imgList;

    private String starttime;

    private String endtime;

    private String destination;

    private int status;

    private String uid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String[] getImgList() {
        return imgList;
    }

    public void setImgList(String[] imgList) {
        this.imgList = imgList;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "skdVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", imgList=" + Arrays.toString(imgList) +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", destination='" + destination + '\'' +
                ", status=" + status +
                ", uid='" + uid + '\'' +
                '}';
    }
}
