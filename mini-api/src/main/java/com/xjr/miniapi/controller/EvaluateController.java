package com.xjr.miniapi.controller;

import com.xjr.pojo.Comments;
import com.xjr.service.EvaluateService;
import com.xjr.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Api(value = "评价管理接口", tags = {"评价管理的controller"})
@RequestMapping("/evaluate")
public class EvaluateController extends BaseController{

    @Autowired
    private EvaluateService evaluateService;

    // 日志对象
    private static final Logger logger = Logger.getLogger(EvaluateController.class);


    // 1、提交短评接口 POST

    // 2、点赞/收藏和取消点赞/收藏行程接口 POST

    // 3、对行程评分接口 POST

    // 4、判断并获取用户点赞状态接口

    // 5、判断并获取用户评分状态接口

    /**
     *
     * 1、提交短评接口 POST
     *
     * @param comments
     * @return
     */
    @ApiOperation(value = "提交短评接口", httpMethod = "POST", notes = "提交短评接口")
    @RequestMapping(value = "/submitComments", method = RequestMethod.POST)
    public JSONResult submitComments(@ApiParam(required = true, value = "短评对象", name = "comments")
                                  @RequestBody Comments comments)
    {
        logger.info("提交短评接口：comments============================="+comments.toString());

        // toUserId是需要根据skdId查询出来的

        evaluateService.submitComments(comments);

        return JSONResult.ok();
    }

    /**
     *
     * 2、点赞/收藏和取消点赞/收藏行程接口 POST
     *
     * @param skdId
     * @param userId
     * @param isLike
     * @return
     */
    @ApiOperation(value = "操作点赞的接口", httpMethod = "POST", notes = "操作点赞的接口")
    @RequestMapping(value = "/operateLikes", method = RequestMethod.POST)
    public JSONResult operateLikes(@ApiParam(required = true, value = "点赞行程id", name = "skdId")
                                       @RequestParam String skdId,
                                   @ApiParam(required = true, value = "点赞用户id", name = "userId")
                                   @RequestParam String userId,
                                   @ApiParam(required = true, value = "点赞flag,true表示点赞，false表示取消点赞", name = "isLike")
                                   @RequestParam Boolean isLike)
    {
        logger.info("操作点赞的接口：skdId============================="+skdId);
        logger.info("操作点赞的接口：userId============================="+userId);
        logger.info("操作点赞的接口：isLike============================="+isLike);

        evaluateService.operateLikes(userId, skdId, isLike);

        return JSONResult.ok();
    }

    /**
     *
     * 3、对行程评分接口 POST
     *
     * @param skdId
     * @param userId
     * @param stars
     * @return
     */
    @ApiOperation(value = "提交行程评分接口", httpMethod = "POST", notes = "提交行程评分接口")
    @RequestMapping(value = "/submitRates", method = RequestMethod.POST)
    public JSONResult submitRates(@ApiParam(required = true, value = "受到评分行程id", name = "skdId")
                                        @RequestParam String skdId,
                                    @ApiParam(required = true, value = "评分用户id", name = "userId")
                                        @RequestParam String userId,
                                    @ApiParam(required = true, value = "评分stars数", name = "stars")
                                        @RequestParam BigDecimal stars)
    {
        logger.info("提交行程评分的接口：skdId============================="+skdId);
        logger.info("提交行程评分的接口：userId============================="+userId);
        logger.info("提交行程评分的接口：stars============================="+stars);

        // toUserId是需要根据skdId查询出来的
        evaluateService.submitRates(skdId, userId, stars);

        return JSONResult.ok();
    }

    /**
     *
     * 4、判断并获取用户点赞状态接口
     *
     * @param userId
     * @param skdId
     * @return
     * true表示当前用户没有点过赞，可以进点赞操作；
     * false表示当前用户已经点过赞，不要提交点赞请求。
     */
    @ApiOperation(value = "判断并获取用户点赞状态接口", httpMethod = "GET", notes = "判断并获取用户点赞状态接口")
    @RequestMapping(value = "/likeStatus", method = RequestMethod.GET)
    public JSONResult getLikeStatus(@ApiParam(required = true, value = "点赞用户id", name = "userId")
                                      @RequestParam String userId,
                                    @ApiParam(required = true, value = "被点赞行程id", name = "skdId")
                                    @RequestParam String skdId)
    {
        logger.info("判断并获取用户点赞状态接口：userId============================="+userId);
        logger.info("判断并获取用户点赞状态接口：skdId============================="+skdId);

        Boolean flag = evaluateService.getLikeStatus(userId, skdId);

        return JSONResult.ok(flag);
    }


    /**
     *
     * 5、判断并获取用户评分状态接口
     *
     * @param userId
     * @param skdId
     * @return
     * true表示当前用户没有评过分，可以进评分操作；
     * false表示当前用户已经评过分，不要提交评分请求。
     */
    @ApiOperation(value = "判断并获取用户评分状态接口", httpMethod = "GET", notes = "判断并获取用户评分状态接口")
    @RequestMapping(value = "/rateStatus", method = RequestMethod.GET)
    public JSONResult getRateStatus(@ApiParam(required = true, value = "评分用户id", name = "userId")
                                    @RequestParam String userId,
                                    @ApiParam(required = true, value = "被评分行程id", name = "skdId")
                                    @RequestParam String skdId)
    {
        logger.info("判断并获取用户评分状态接口：userId============================="+userId);
        logger.info("判断并获取用户评分状态接口：skdId============================="+skdId);

        BigDecimal rate = evaluateService.getRateStatus(userId, skdId);

        return JSONResult.ok(rate);
    }


}
