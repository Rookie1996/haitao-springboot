package com.xjr.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Stock implements Serializable {
    @Id
    private Integer id;

    /**
     * 商品id
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * 商品库存s数量,从商品表中分离出来
     */
    private Integer stock;

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
     * 获取商品id
     *
     * @return product_id - 商品id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置商品id
     *
     * @param productId 商品id
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取商品库存s数量,从商品表中分离出来
     *
     * @return stock - 商品库存s数量,从商品表中分离出来
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * 设置商品库存s数量,从商品表中分离出来
     *
     * @param stock 商品库存s数量,从商品表中分离出来
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }
}