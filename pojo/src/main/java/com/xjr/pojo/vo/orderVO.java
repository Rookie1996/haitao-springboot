package com.xjr.pojo.vo;

import com.xjr.pojo.Order;
import com.xjr.pojo.OrderProduct;

import java.io.Serializable;
import java.util.List;

public class orderVO implements Serializable {

    public Order order;

    public List<OrderProduct> orderProductList;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    @Override
    public String toString() {
        return "orderVO{" +
                "order=" + order +
                ", orderProductList=" + orderProductList +
                '}';
    }
}
