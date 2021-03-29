package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "search_records")
public class SearchRecords implements Serializable {
    @Id
    @Column(name = "sr_id")
    private Integer srId;

    /**
     * 搜索的内容
     */
    @Column(name = "sr_content")
    private String srContent;

    /**
     * 目的地
     */
    @Column(name = "sr_destination")
    private String srDestination;

    /**
     * 抽取的实体，可作为热搜词
     */
    @Column(name = "sr_entity")
    private String srEntity;

    @Column(name = "sr_uid")
    private String srUid;

    /**
     * @return sr_id
     */
    public Integer getSrId() {
        return srId;
    }

    /**
     * @param srId
     */
    public void setSrId(Integer srId) {
        this.srId = srId;
    }

    /**
     * 获取搜索的内容
     *
     * @return sr_content - 搜索的内容
     */
    public String getSrContent() {
        return srContent;
    }

    /**
     * 设置搜索的内容
     *
     * @param srContent 搜索的内容
     */
    public void setSrContent(String srContent) {
        this.srContent = srContent;
    }

    /**
     * 获取目的地
     *
     * @return sr_destination - 目的地
     */
    public String getSrDestination() {
        return srDestination;
    }

    /**
     * 设置目的地
     *
     * @param srDestination 目的地
     */
    public void setSrDestination(String srDestination) {
        this.srDestination = srDestination;
    }

    /**
     * 获取抽取的实体，可作为热搜词
     *
     * @return sr_entity - 抽取的实体，可作为热搜词
     */
    public String getSrEntity() {
        return srEntity;
    }

    /**
     * 设置抽取的实体，可作为热搜词
     *
     * @param srEntity 抽取的实体，可作为热搜词
     */
    public void setSrEntity(String srEntity) {
        this.srEntity = srEntity;
    }

    /**
     * @return sr_uid
     */
    public String getSrUid() {
        return srUid;
    }

    /**
     * @param srUid
     */
    public void setSrUid(String srUid) {
        this.srUid = srUid;
    }
}