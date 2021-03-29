package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "product_spec")
public class ProductSpec implements Serializable {
    @Id
    private Integer id;

    private String color;

    private String size;

    /**
     * 商品id号，商品规格信息属于商品
     */
    @Column(name = "p_id")
    private String pId;

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
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * 获取商品id号，商品规格信息属于商品
     *
     * @return p_id - 商品id号，商品规格信息属于商品
     */
    public String getpId() {
        return pId;
    }

    /**
     * 设置商品id号，商品规格信息属于商品
     *
     * @param pId 商品id号，商品规格信息属于商品
     */
    public void setpId(String pId) {
        this.pId = pId;
    }
}