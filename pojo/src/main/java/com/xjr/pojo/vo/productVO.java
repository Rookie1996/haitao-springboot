package com.xjr.pojo.vo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class productVO implements Serializable {

    /**
     * 1、product所有； 主图片不使用了修改太烦了
     *
     * 2、product_spec List<String> color、size(xl、xxl、xxxl);
     *
     * 3、stock库存；
     *
     * 4、商品图片urls List<String> urls
     *
      */

    // 1、product
    private String pid;

    private String name;

    private String brand;

    private String unit;

    private String origin;

    private String info;

    private String desc;

    private Integer sales;

    private BigDecimal price;

    private Integer status;

    private String mainImgUrl;

    private Byte isDel;

    private String skdId;

    // 2、product_spec
    private List<String> color;

    private List<String> size;

    // 3、stock
    private Integer stock;

    // 4、商品图片
    private List<String> urls;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMainImgUrl() {
        return mainImgUrl;
    }

    public void setMainImgUrl(String mainImgUrl) {
        this.mainImgUrl = mainImgUrl;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public String getSkdId() {
        return skdId;
    }

    public void setSkdId(String skdId) {
        this.skdId = skdId;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "productVO{" +
                "pid='" + pid + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", unit='" + unit + '\'' +
                ", origin='" + origin + '\'' +
                ", info='" + info + '\'' +
                ", desc='" + desc + '\'' +
                ", sales=" + sales +
                ", price=" + price +
                ", status=" + status +
                ", mainImgUrl='" + mainImgUrl + '\'' +
                ", isDel=" + isDel +
                ", skdId='" + skdId + '\'' +
                ", color=" + color +
                ", size=" + size +
                ", stock=" + stock +
                ", urls=" + urls +
                '}';
    }
}
