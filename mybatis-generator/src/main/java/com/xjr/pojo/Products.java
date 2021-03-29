package com.xjr.pojo;

import java.math.BigDecimal;
import javax.persistence.*;

public class Products {
    /**
     * 代购商品id
     */
    @Id
    @Column(name = "p_id")
    private String pId;

    /**
     * 代购商品名称
     */
    @Column(name = "p_name")
    private String pName;

    /**
     * 代购商品品牌
     */
    @Column(name = "p_brand")
    private String pBrand;

    /**
     * 代购商品产地
     */
    @Column(name = "p_origin")
    private String pOrigin;

    /**
     * 代购商品信息、型号
     */
    @Column(name = "p_info")
    private String pInfo;

    /**
     * 代购商品描述
     */
    @Column(name = "p_desc")
    private String pDesc;

    /**
     * 销量
     */
    @Column(name = "p_sales")
    private Integer pSales;

    /**
     * 商品单价,单位：分
     */
    @Column(name = "p_price")
    private BigDecimal pPrice;

    /**
     * 0-未上架；1-已上架
     */
    @Column(name = "p_status")
    private Integer pStatus;

    /**
     * 代购商品图片url
     */
    @Column(name = "p_main_img_url")
    private String pMainImgUrl;

    /**
     * 默认0，1表示已删除
     */
    @Column(name = "p_is_del")
    private Byte pIsDel;

    /**
     * 总行程id号，商品属于总行程
     */
    @Column(name = "skd_id")
    private String skdId;

    /**
     * 获取代购商品id
     *
     * @return p_id - 代购商品id
     */
    public String getpId() {
        return pId;
    }

    /**
     * 设置代购商品id
     *
     * @param pId 代购商品id
     */
    public void setpId(String pId) {
        this.pId = pId;
    }

    /**
     * 获取代购商品名称
     *
     * @return p_name - 代购商品名称
     */
    public String getpName() {
        return pName;
    }

    /**
     * 设置代购商品名称
     *
     * @param pName 代购商品名称
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * 获取代购商品品牌
     *
     * @return p_brand - 代购商品品牌
     */
    public String getpBrand() {
        return pBrand;
    }

    /**
     * 设置代购商品品牌
     *
     * @param pBrand 代购商品品牌
     */
    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    /**
     * 获取代购商品产地
     *
     * @return p_origin - 代购商品产地
     */
    public String getpOrigin() {
        return pOrigin;
    }

    /**
     * 设置代购商品产地
     *
     * @param pOrigin 代购商品产地
     */
    public void setpOrigin(String pOrigin) {
        this.pOrigin = pOrigin;
    }

    /**
     * 获取代购商品信息、型号
     *
     * @return p_info - 代购商品信息、型号
     */
    public String getpInfo() {
        return pInfo;
    }

    /**
     * 设置代购商品信息、型号
     *
     * @param pInfo 代购商品信息、型号
     */
    public void setpInfo(String pInfo) {
        this.pInfo = pInfo;
    }

    /**
     * 获取代购商品描述
     *
     * @return p_desc - 代购商品描述
     */
    public String getpDesc() {
        return pDesc;
    }

    /**
     * 设置代购商品描述
     *
     * @param pDesc 代购商品描述
     */
    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    /**
     * 获取销量
     *
     * @return p_sales - 销量
     */
    public Integer getpSales() {
        return pSales;
    }

    /**
     * 设置销量
     *
     * @param pSales 销量
     */
    public void setpSales(Integer pSales) {
        this.pSales = pSales;
    }

    /**
     * 获取商品单价,单位：分
     *
     * @return p_price - 商品单价,单位：分
     */
    public BigDecimal getpPrice() {
        return pPrice;
    }

    /**
     * 设置商品单价,单位：分
     *
     * @param pPrice 商品单价,单位：分
     */
    public void setpPrice(BigDecimal pPrice) {
        this.pPrice = pPrice;
    }

    /**
     * 获取0-未上架；1-已上架
     *
     * @return p_status - 0-未上架；1-已上架
     */
    public Integer getpStatus() {
        return pStatus;
    }

    /**
     * 设置0-未上架；1-已上架
     *
     * @param pStatus 0-未上架；1-已上架
     */
    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }

    /**
     * 获取代购商品图片url
     *
     * @return p_main_img_url - 代购商品图片url
     */
    public String getpMainImgUrl() {
        return pMainImgUrl;
    }

    /**
     * 设置代购商品图片url
     *
     * @param pMainImgUrl 代购商品图片url
     */
    public void setpMainImgUrl(String pMainImgUrl) {
        this.pMainImgUrl = pMainImgUrl;
    }

    /**
     * 获取默认0，1表示已删除
     *
     * @return p_is_del - 默认0，1表示已删除
     */
    public Byte getpIsDel() {
        return pIsDel;
    }

    /**
     * 设置默认0，1表示已删除
     *
     * @param pIsDel 默认0，1表示已删除
     */
    public void setpIsDel(Byte pIsDel) {
        this.pIsDel = pIsDel;
    }

    /**
     * 获取总行程id号，商品属于总行程
     *
     * @return skd_id - 总行程id号，商品属于总行程
     */
    public String getSkdId() {
        return skdId;
    }

    /**
     * 设置总行程id号，商品属于总行程
     *
     * @param skdId 总行程id号，商品属于总行程
     */
    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }
}