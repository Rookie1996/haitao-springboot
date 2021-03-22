package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "skd_image")
public class SkdImage {
    @Id
    private Integer id;

    private String url;

    /**
     * 0-正常，1-已删除
     */
    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "skd_id")
    private String skdId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取0-正常，1-已删除
     *
     * @return is_del - 0-正常，1-已删除
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置0-正常，1-已删除
     *
     * @param isDel 0-正常，1-已删除
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

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
}