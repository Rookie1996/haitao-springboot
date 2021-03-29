package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Table(name = "order_product")
public class OrderProduct implements Serializable {
    @Id
    private Long id;

    /**
     * 订单id号（订单的自增主键）
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 商品id号
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品品牌
     */
    @Column(name = "product_brand")
    private String productBrand;

    /**
     * 销售价格
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    @Column(name = "product_quantity")
    private Integer productQuantity;

    /**
     * 商品销售属性json，:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    @Column(name = "product_attribute")
    private String productAttribute;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订单id号（订单的自增主键）
     *
     * @return order_id - 订单id号（订单的自增主键）
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id号（订单的自增主键）
     *
     * @param orderId 订单id号（订单的自增主键）
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取商品id号
     *
     * @return product_id - 商品id号
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置商品id号
     *
     * @param productId 商品id号
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取商品名称
     *
     * @return product_name - 商品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置商品名称
     *
     * @param productName 商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取商品品牌
     *
     * @return product_brand - 商品品牌
     */
    public String getProductBrand() {
        return productBrand;
    }

    /**
     * 设置商品品牌
     *
     * @param productBrand 商品品牌
     */
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    /**
     * 获取销售价格
     *
     * @return product_price - 销售价格
     */
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    /**
     * 设置销售价格
     *
     * @param productPrice 销售价格
     */
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * 获取购买数量
     *
     * @return product_quantity - 购买数量
     */
    public Integer getProductQuantity() {
        return productQuantity;
    }

    /**
     * 设置购买数量
     *
     * @param productQuantity 购买数量
     */
    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * 获取商品销售属性json，:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     *
     * @return product_attribute - 商品销售属性json，:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    public String getProductAttribute() {
        return productAttribute;
    }

    /**
     * 设置商品销售属性json，:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     *
     * @param productAttribute 商品销售属性json，:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", orderNo='" + orderNo + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productPrice=" + productPrice +
                ", productQuantity=" + productQuantity +
                ", productAttribute='" + productAttribute + '\'' +
                '}';
    }
}