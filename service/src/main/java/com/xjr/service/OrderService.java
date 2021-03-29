package com.xjr.service;

import com.xjr.pojo.Order;
import com.xjr.pojo.vo.historyOrderVO;
import com.xjr.pojo.vo.orderVO;

import java.util.List;

public interface OrderService {

    /**
     * 根据传递的orderVO创建订单的接口。
     *
     * 该接口包含创建订单order、扣减库存stock、增加销量product、操作order_product表
     *
     * @param orderVO
     */
    public String createOrder(orderVO orderVO);

    /**
     * 根据userId查询该用户的所有历史订单
     *
     * @param userId
     * @return
     */
    public List<historyOrderVO> selectAllHistoryOrder(String userId);


    /**
     *
     * @param userId
     * @param status
     * @return
     */
    public List<historyOrderVO> selectOrderByUserIdAndStatus(String userId, Byte status);


    /**
     * 根据订单状态搜索全部的订单
     * @param status
     * @return
     */
    public List<Order> selectOrderByStatus(Byte status);


    /**
     * 修改订单状态函数
     * @param orderNo
     * @param status
     */
    void operateOrderStatus(String orderNo, Byte status);


}
