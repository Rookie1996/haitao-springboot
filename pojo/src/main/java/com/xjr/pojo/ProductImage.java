package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "product_image")
public class ProductImage {
    @Id
    private Integer id;

    /**
     * 图片url
     */
    private String url;

    /**
     * 状态，主要表示是否删除，也可以扩展其他状态
     */
    @Column(name = "is_del")
    private Integer isDel;

    @Column(name = "product_id")
    private String productId;

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
     * 获取图片url
     *
     * @return url - 图片url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置图片url
     *
     * @param url 图片url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取状态，主要表示是否删除，也可以扩展其他状态
     *
     * @return is_del - 状态，主要表示是否删除，也可以扩展其他状态
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置状态，主要表示是否删除，也可以扩展其他状态
     *
     * @param isDel 状态，主要表示是否删除，也可以扩展其他状态
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * @return product_id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
}