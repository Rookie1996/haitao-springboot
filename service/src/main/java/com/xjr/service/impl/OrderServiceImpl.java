package com.xjr.service.impl;

import com.xjr.mapper.*;
import com.xjr.pojo.Order;
import com.xjr.pojo.OrderProduct;
import com.xjr.pojo.vo.historyOrderVO;
import com.xjr.pojo.vo.orderVO;
import com.xjr.service.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired(required = false)
    private Sid sid;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private ProductsMapper productsMapper;

    @Transactional
    @Override
    public String createOrder(orderVO orderVO) {

        // 1、创建order并赋值
        Order order = new Order();

        String orderno = sid.nextShort();

        order.setOrderNo(orderno);
        order.setCreateTime(new Date());

        order.setuId(orderVO.getOrder().getuId());
        order.setReceiverName(orderVO.getOrder().getReceiverName());
        order.setReceiverPhone(orderVO.getOrder().getReceiverPhone());
        order.setReceiverPostCode(orderVO.getOrder().getReceiverPostCode());
        order.setReceiverProvince(orderVO.getOrder().getReceiverProvince());
        order.setReceiverCity(orderVO.getOrder().getReceiverCity());
        order.setReceiverRegion(orderVO.getOrder().getReceiverRegion());
        order.setReceiverDetailAddr(orderVO.getOrder().getReceiverDetailAddr());
        order.setTotalPrice(orderVO.getOrder().getTotalPrice());

        // 设置为未支付状态
        order.setStatus((byte)1);
        order.setTotalCount(orderVO.getOrder().getTotalCount());

        System.out.println("order:"+order.toString());

        // 插入到order表中，order需要加反引号...导致insertSelective报错！
//        orderMapper.insertSelective(order);
        orderMapper.insertOrder(orderno, order.getuId(), order.getCreateTime(), order.getReceiverName(),
                order.getReceiverPhone(), order.getReceiverPostCode(), order.getReceiverProvince(),
                order.getReceiverCity(), order.getReceiverRegion(), order.getReceiverDetailAddr(),
                order.getTotalPrice(), order.getStatus(), order.getTotalCount());


        // 2、创建order_product
        List<OrderProduct> list = orderVO.getOrderProductList();
        for(OrderProduct orderProduct: list){
            // 先赋值订单编号和订单主键
            orderProduct.setOrderNo(orderno);
            Long orderId = orderMapper.selectOrderIdByOrderNo(orderno);
            orderProduct.setOrderId(orderId);

            orderProductMapper.insertSelective(orderProduct);

            String pid = orderProduct.getProductId();
            Integer number = orderProduct.getProductQuantity();

            // 3、扣减库存stock ，20min分钟后自动取消订单
            Integer stockFlag = stockMapper.decreaseStockByPid(pid, number);
            if(stockFlag == 0){
                return "库存已售罄";
            }

            // 4、增加商品销量
            productsMapper.increaseSalesByPid(pid, number);
        }
        return orderno;
    }

    // 查询历史订单
    @Override
    public List<historyOrderVO> selectAllHistoryOrder(String userId) {

        List<Order> orderList = orderMapper.selectAllOrderByUserId(userId);
        List<historyOrderVO> list = new ArrayList<>();

        for(Order order: orderList){
            String orderNo = order.getOrderNo();
            // 根据orderNo查询订单商品
            List<OrderProduct> orderProductList = orderProductMapper.selectAllProductByOrderNo(orderNo);

            // 每一单有多个商品
            historyOrderVO historyOrderVO = new historyOrderVO();
            historyOrderVO.setOrderProductList(orderProductList);

            historyOrderVO.setOrderNo(orderNo);
            historyOrderVO.setuId(order.getuId());
            historyOrderVO.setCreateTime(order.getCreateTime());

            historyOrderVO.setReceiverProvince(order.getReceiverProvince());
            historyOrderVO.setReceiverCity(order.getReceiverCity());
            historyOrderVO.setReceiverRegion(order.getReceiverRegion());
            historyOrderVO.setReceiverDetailAddr(order.getReceiverDetailAddr());
            historyOrderVO.setReceiverName(order.getReceiverName());
            historyOrderVO.setReceiverPhone(order.getReceiverPhone());
            historyOrderVO.setReceiverPostCode(order.getReceiverPostCode());
            historyOrderVO.setStatus(order.getStatus());
            historyOrderVO.setTotal_count(order.getTotalCount());
            historyOrderVO.setTotal_price(order.getTotalPrice());
            historyOrderVO.setNote(order.getNote());

            List<String> historyUrls = new ArrayList<>();

            for(OrderProduct orderProduct: orderProductList){
                String pid = orderProduct.getProductId();
                List<String> urls = productImageMapper.selectUrlsByPid(pid);
                // 每个商品取第一个图片
                historyUrls.add(urls.get(0));
            }
            historyOrderVO.setUrls(historyUrls);

            list.add(historyOrderVO);

        }

        return list;
    }

    @Transactional
    @Override
    public List<historyOrderVO> selectOrderByUserIdAndStatus(String userId, Byte status) {

        List<Order> orderList = orderMapper.selectOrderByUserIdAndStatus(userId, status);
        List<historyOrderVO> list = new ArrayList<>();

        for(Order order: orderList){
            String orderNo = order.getOrderNo();
            // 根据orderNo查询订单商品
            List<OrderProduct> orderProductList = orderProductMapper.selectAllProductByOrderNo(orderNo);

            // 每一单有多个商品
            historyOrderVO historyOrderVO = new historyOrderVO();
            historyOrderVO.setOrderProductList(orderProductList);

            historyOrderVO.setOrderNo(orderNo);
            historyOrderVO.setuId(order.getuId());
            historyOrderVO.setCreateTime(order.getCreateTime());

            historyOrderVO.setReceiverProvince(order.getReceiverProvince());
            historyOrderVO.setReceiverCity(order.getReceiverCity());
            historyOrderVO.setReceiverRegion(order.getReceiverRegion());
            historyOrderVO.setReceiverDetailAddr(order.getReceiverDetailAddr());
            historyOrderVO.setReceiverName(order.getReceiverName());
            historyOrderVO.setReceiverPhone(order.getReceiverPhone());
            historyOrderVO.setReceiverPostCode(order.getReceiverPostCode());
            historyOrderVO.setStatus(order.getStatus());
            historyOrderVO.setTotal_count(order.getTotalCount());
            historyOrderVO.setTotal_price(order.getTotalPrice());
            historyOrderVO.setNote(order.getNote());

            List<String> historyUrls = new ArrayList<>();

            for(OrderProduct orderProduct: orderProductList){
                String pid = orderProduct.getProductId();
                List<String> urls = productImageMapper.selectUrlsByPid(pid);
                // 每个商品取第一个图片
                historyUrls.add(urls.get(0));
            }
            historyOrderVO.setUrls(historyUrls);

            list.add(historyOrderVO);

        }

        return list;
    }

    @Override
    public List<Order> selectOrderByStatus(Byte status) {

        List<Order> res = orderMapper.selectOrderByStatus(status);

        return res;
    }

    @Override
    public void operateOrderStatus(String orderNo, Byte status) {

        orderMapper.updateOrderStatusByOrderNo(orderNo, status);
    }


}
