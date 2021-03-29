package com.xjr.miniapi;


import com.xjr.mapper.OrderProductMapper;
import com.xjr.mapper.ProductsMapper;
import com.xjr.mapper.StockMapper;
import com.xjr.miniapi.controller.OrderController;
import com.xjr.pojo.Order;
import com.xjr.pojo.OrderProduct;
import com.xjr.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 订单超时取消并回补库存、恢复销量的定时器
 */
@Component
public class OrderTimeOutCancelTask {

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private OrderService orderService;

    // 日志对象
    private static final Logger logger = Logger.getLogger(OrderTimeOutCancelTask.class);


    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     *
     * 每隔3秒进行测试
     */
    @Transactional
    @Scheduled(cron = "0 0/10 * * * ?")
    public void cancelTimeOutOrder() {

        logger.info("每隔10min检查数据库中是否存在未付款订单，有则取消该订单并根据product编号释放锁定库存和恢复销量。");

        // 订单过期时间为30min

        // 查询所有未付款订单，如果有超时订单则取消订单
        List<Order> orderList = orderService.selectOrderByStatus((byte)1);

        for(Order order: orderList){
            String orderNo = order.getOrderNo();
            Date createTime = order.getCreateTime();
            Date now = new Date();
            long diff = now.getTime() - createTime.getTime();

            logger.info(orderNo+"订单已创建时长为:"+diff/60000);

            if(diff >= 1800000){ // 订单已经超时了

                // 根据orderNo查询订单商品
                List<OrderProduct> orderProductList = orderProductMapper.selectAllProductByOrderNo(orderNo);
                // 1、取消订单
                orderService.operateOrderStatus(orderNo, (byte)3);

                // 2、 回补库存和销量
                for(OrderProduct orderProduct: orderProductList){
                    String pid = orderProduct.getProductId();
                    Integer number = orderProduct.getProductQuantity();

                    // 增加库存
                    stockMapper.increaseStockByPid(pid, number);

                    // 减少销量
                    productsMapper.increaseSalesByPid(pid, -number);

                }


            }

        }

    }

}
