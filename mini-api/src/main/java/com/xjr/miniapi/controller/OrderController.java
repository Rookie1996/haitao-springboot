package com.xjr.miniapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.google.gson.JsonObject;
import com.xjr.pojo.vo.historyOrderVO;
import com.xjr.pojo.vo.orderVO;
import com.xjr.service.OrderService;
import com.xjr.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Api(value = "商品购买业务接口", tags = {"行程商品购买业务的controller"})
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    private RateLimiter orderCreateRateLimiter;

    private ExecutorService executorService;

    @PostConstruct
    public void init(){
        executorService = Executors.newFixedThreadPool(20); //LinkedBlockQueue排队

        orderCreateRateLimiter = RateLimiter.create(300);//300的qps每秒访问
    }

    // 日志对象
    private static final Logger logger = Logger.getLogger(OrderController.class);


    /**
     * 商品购买核心模块的非常核心的方法（迫于时间限制，直接分为两步骤先创建订单，再扣减库存，再使用SpringTask对订单进行管理）
     *
     * @param orderVO
     * @return
     */
    @ApiOperation(value = "创建订单接口", httpMethod = "POST", notes = "创建订单接口")
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public JSONResult createOrder(@ApiParam(required = true, value = "订单对象VO", name = "orderVO")
                                      @RequestBody orderVO orderVO){

        if(orderVO == null){
            return JSONResult.errorMsg("请检查订单对象VO！");
        }

        logger.info("创建订单接口：orderVO============================="+orderVO.toString());
        logger.info("创建订单接口：order============================="+orderVO.getOrder().toString());
        logger.info("创建订单接口：orderProduct============================="+orderVO.getOrderProductList());

        //令牌桶限流
        if(orderCreateRateLimiter.acquire() > 0){//默认从令牌桶中获取1个令牌，没有就需要被阻塞
            return JSONResult.errorMsg("获取令牌失败！");
        }

        // 创建订单、扣减库存等的主方法
        String orderNo = orderService.createOrder(orderVO);

        return JSONResult.ok(orderNo);
    }


    /**
     * 根据用户id查询所有的历史订单接口。
     * 排序按未付款订单、已付款、已完成订单
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询历史订单接口", httpMethod = "GET", notes = "查询历史订单接口")
    @RequestMapping(value = "/historyOrder", method = RequestMethod.GET)
    public JSONResult selectHistoryOrder(@ApiParam(required = true, value = "用户id", name = "userId")
                                  @RequestParam String userId){

        logger.info("查询历史订单接口：userId============================="+userId);

        List<historyOrderVO> historyList = orderService.selectAllHistoryOrder(userId);

        return JSONResult.ok(historyList);
    }

    // 查看未付款订单 （需要返回包含图片信息的pojo类）
    @ApiOperation(value = "查询未付款订单接口", httpMethod = "GET", notes = "查询未付款订单接口")
    @RequestMapping(value = "/unpayOrder", method = RequestMethod.GET)
    public JSONResult selectUnpayOrder(@ApiParam(required = true, value = "用户id", name = "userId")
                                         @RequestParam String userId){

        logger.info("查询未付款订单接口：userId============================="+userId);

        // 订单状态为1
        List<historyOrderVO> historyList = orderService.selectOrderByUserIdAndStatus(userId, (byte)1);

        return JSONResult.ok(historyList);
    }

    // 查看已付款订单
    @ApiOperation(value = "查询已付款订单接口", httpMethod = "GET", notes = "查询已付款订单接口")
    @RequestMapping(value = "/payedOrder", method = RequestMethod.GET)
    public JSONResult selectPayedOrder(@ApiParam(required = true, value = "用户id", name = "userId")
                                         @RequestParam String userId){

        logger.info("查询已付款订单接口：userId============================="+userId);

        // 订单状态为2
        List<historyOrderVO> historyList = orderService.selectOrderByUserIdAndStatus(userId, (byte)2);

        return JSONResult.ok(historyList);
    }

    // 查看已完成订单
    @ApiOperation(value = "查询已完成订单接口", httpMethod = "GET", notes = "查询已完成订单接口")
    @RequestMapping(value = "/doneOrder", method = RequestMethod.GET)
    public JSONResult selectDoneOrder(@ApiParam(required = true, value = "用户id", name = "userId")
                                       @RequestParam String userId){

        logger.info("查询已完成订单接口：userId============================="+userId);

        // 订单状态为4
        List<historyOrderVO> historyList = orderService.selectOrderByUserIdAndStatus(userId, (byte)4);

        return JSONResult.ok(historyList);
    }



    // 微信支付接口
    @ApiOperation(value = "微信创建订单接口", httpMethod = "POST", notes = "微信创建订单接口")
    @RequestMapping(value = "/execPay", method = RequestMethod.POST)
    public JSONResult execPay(@ApiParam(required = true, value = "订单号", name = "orderNo")
                                  @RequestParam String orderNo) throws Exception{

        //请求URL
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/jsapi");

        // 请求body参数,前端传递过来的json对象的string化
        String reqdata = "{"
                + "\"amount\": {"
                + "\"total\": 100,"
                + "\"currency\": \"CNY\""
                + "},"
                + "\"mchid\": \"1900006891\","
                + "\"description\": \"Image形象店-深圳腾大-QQ公仔\","
                + "\"notify_url\": \"https://www.weixin.qq.com/wxpay/pay.php\","
                + "\"payer\": {"
                + "\"openid\": \"o4GgauE1lgaPsLabrYvqhVg7O8yA\"" + "},"
                + "\"out_trade_no\": \"1217752501201407033233388881\","
                + "\"goods_tag\": \"WXG\","
                + "\"appid\": \"wxdace645e0bc2c424\"" + "}";

        StringEntity entity = new StringEntity(reqdata);
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");

        // 创建默认的httpClient实例.  add
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //完成签名并执行请求
        CloseableHttpResponse response = httpclient.execute(httpPost);

        String prepay_id = "";

        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                System.out.println("success,return body = " + EntityUtils.toString(response.getEntity()));

                JSONObject jsonObject = JSON.parseObject(EntityUtils.toString(response.getEntity()));
                prepay_id = jsonObject.getString("prepay_id");


            } else if (statusCode == 204) {
                System.out.println("success");
            } else {
                System.out.println("failed,resp code = " + statusCode+ ",return body = " + EntityUtils.toString(response.getEntity()));
                throw new IOException("request failed");
            }
        } finally {
            response.close();
        }

        return JSONResult.ok(prepay_id);
    }


}
