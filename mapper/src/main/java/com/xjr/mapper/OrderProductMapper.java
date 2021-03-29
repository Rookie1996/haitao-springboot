package com.xjr.mapper;

import com.xjr.pojo.OrderProduct;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductMapper extends MyMapper<OrderProduct> {

    // 根据订单编号查询所有商品
    public List<OrderProduct> selectAllProductByOrderNo(String orderNo);
}