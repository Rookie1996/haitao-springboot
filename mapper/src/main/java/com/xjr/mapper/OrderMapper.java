package com.xjr.mapper;

import com.xjr.pojo.Order;
import com.xjr.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderMapper extends MyMapper<Order> {

    // 根据orderNo查询订单主键
    public Long selectOrderIdByOrderNo(String orderNo);

    void insertOrder(String orderNo, String userId, Date createTime, String receiverName,
                     String receiverPhone, String receiverPostCode, String receiverProvince,
                     String receiverCity, String receiverRegion, String receiverDetailAddr,
                     BigDecimal totalPrice, Byte status, Integer totalCount);

    // 根据userId查询所有order
    public List<Order> selectAllOrderByUserId(String userId);

    // 根据userId和订单状态搜索order
    public List<Order> selectOrderByUserIdAndStatus(String userId, Byte status);

    // 根据status查询order
    public List<Order> selectOrderByStatus(Byte status);

    // 根据订单编号修改订单状态
    void updateOrderStatusByOrderNo(String orderNo, Byte status);
}