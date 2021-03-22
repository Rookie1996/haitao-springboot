package com.xjr.pojo;

import javax.persistence.*;

@Table(name = "skd_detail_image")
public class SkdDetailImage {
    @Id
    private Integer id;

    private String url;

    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "skd_detail_id")
    private String skdDetailId;

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
     * @return is_del
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

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
}