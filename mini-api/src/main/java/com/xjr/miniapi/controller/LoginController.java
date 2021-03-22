package com.xjr.miniapi.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjr.RawData;
import com.xjr.pojo.Users;
import com.xjr.service.UserService;
import com.xjr.utils.JSONResult;
import io.swagger.annotations.*;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.log4j.Logger;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "用户注册登录的接口", tags = {"注册和登录的controller"})
public class LoginController extends BaseController{

    @Autowired
    private UserService userService;

//    @Autowired
//    public RedisTemplate redisTemplate;

    @Autowired
    private Sid sid; /* 不知道为啥注入不了ioc，可能没auto-scan？ ---确实！加上@ComponentScan即可*/

    // 日志对象
    private static final Logger logger = Logger.getLogger(UserController.class);

    /**
     * 登陆接口
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiResponses({
            @ApiResponse(code = 404, message = "服务器未找到资源"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "服务器错误"),
            @ApiResponse(code = 401, message = "没有访问权限"),
            @ApiResponse(code = 403, message = "服务器拒绝访问"),
    })
    @ApiOperation(value = "小程序登录", httpMethod = "POST", notes = "小程序登录")
    public JSONResult login(
            @ApiParam(required = true, value = "临时登录凭证code", name = "code") String code,
            @ApiParam(required = true, value = "用户非敏感信息", name = "rawData")
            @RequestParam(value = "rawData", required = true) String rawData,
            @ApiParam(required = true, value = "签名", name = "signature")
            @RequestParam(value = "signature", required = true) String signature,
            @ApiParam(required = true, value = "用户敏感信息", name = "encrypteData")
            @RequestParam(value = "encrypteData", required = true) String encrypteData,
            @ApiParam(required = true, value = "解密算法的向量", name = "iv")
            @RequestParam(value = "iv", required = true) String iv
    ){
        logger.info( "Start get SessionKey" );
        logger.info("rawData============================================================="+rawData);
        logger.info("signature============================================================="+signature);
        logger.info("encrypteData=========================================================="+encrypteData);
        logger.info("iv========================================================================"+iv);

        ObjectMapper mapper = new ObjectMapper();

        // 获取微信SDK对象
        final WxMaService wxService = getWxMaService();

        RawData data = null;
        WxMaJscode2SessionResult session = null;
        WxMaUserInfo userInfo = null;
        String openid = null;
        String sessionKey = null;
        String unionid = null;

        try {
            if (rawData != null && !"".equals(rawData)) {
                //1、获取用户非敏感信息 ---> Java Model类
                data = mapper.readValue(rawData, RawData.class);
            }

            // （1）wx.login()获取的code就可从微信端获取授权的sessionkey
            session = wxService.getUserService().getSessionInfo(code);

            //（2）从session对象，获取到openid和sessionkey
            openid = session.getOpenid();
            sessionKey = session.getSessionKey();
            // unionid不需解密即可获得
            unionid = session.getUnionid();

            logger.info("sessionkey========================================================="+sessionKey);
            logger.info("openid========================================================="+openid);
            logger.info("unionid========================================================="+unionid);

            // （3）微信端session-key有时效性，用户信息校验 7200秒==2hours
            if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
                return JSONResult.errorMsg("user check failed！");
            }

            // 2、解密用户信息
            userInfo = wxService.getUserService().getUserInfo(sessionKey, encrypteData, iv);
            logger.info("userInfo========================================================="+userInfo);


        } catch (IOException e) {
            e.printStackTrace();
            logger.info("获取用户信息失败");
            return JSONResult.errorMsg("请求失败！");

        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.info("获取用户信息失败");
        }

        // 3、插入MySQL userid是主键，openid只作为支付时使用的id
        String userId = insertUser(data, openid);
//        Users users = new Users();
//        users.setuId(userId);

        // 4、登录成功之后 缓存openid, unionId, userId到redis
        String userToken = redisCache(openid, unionid, userId);

        Map<String,String> res = new HashMap<>();
        res.put("userId",userId);
        res.put("userToken",userToken);

        return JSONResult.ok(res);
    }


    /**
     * 根据wx.login()获得的code，获取新的userToken的接口
     * 专门给tokenController？---1、验证token接口（拦截器直接给屏蔽掉了？可以设置不屏蔽，主动校验一次）；2、获取token接口
     *
     * 【重要】使用code生成的userToken的key是sid，value应该是userId + 权限。这样之后验证成功后才可以通过userId做事情
     * openid在mysql中是唯一索引搜索快
     */

    /**
     * 微信SDK获取wxService对象
     * 输入appId、appSecret信息获取wxService对象
     * @return
     */
    private WxMaService getWxMaService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(APP_ID);
        config.setSecret(APP_SECRET);
        config.setMsgDataFormat("JSON");
        WxMaService wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);
        return wxMaService;
    }

    /**
     *  登陆将用户信息插入到数据库
     * @param data 用户开放信息
     * @param openid 微信唯一标识ID
     * @return
     */
    private String insertUser(RawData data, String openid){

        // 判断openid的用户是否存在，不存在入库，否则返回已存在的用户id。
        boolean isExist = userService.queryOpenidIsExist(openid);

        if(isExist){
            logger.info("用户openid已存在,不需要插入");

            String userId = userService.queryUserIdByOpenid(openid);

            return userId;

        }else{
            // 生成唯一userId
            String userId = sid.nextShort();
            Users users = new Users();
            users.setuId(userId);
            users.setOpenid(openid);
            users.setuName(data.getNickName());
            users.setuGender(data.getGender());
            users.setuType("消费者");
            users.setuFaceImage(data.getAvatarUrl());
            users.setuAddress(data.getCountry()+"-"+data.getProvince()+"-"+data.getCity());
            users.setuNickname(data.getNickName());
            users.setuRegisterTime(new Date());
            users.setuThirdPartyId(0);
            users.setuIntro("还没有简介！");

            userService.saveUser(users);

            return userId;
        }



    }

    /**
     * 缓存openid，sessionKey,userId等信息
     * @param openid 小程序用户唯一标志
     * @param unionId 小程序不同平台唯一标志
     * @param userId 后台生成的用户唯一标志
     *
     *               会话管理(interrupter跟前端验证userToken是否过期)
     *               第一次授权登录会自动生成新的token存入redis中
     */
    private String redisCache(String openid, String unionId, String userId) {

        // 2021/3/6前端不应该保存sessionKey、openid信息
        // 2021/3/13 token可以用来区分用户的权限：消费者访问的接口、代购者访问的接口（以后需要的时候再区分，先统一使用sid）

        // 思考：需要直接缓存openid和unionId，覆盖原有的信息吗？---并不需要
        // （2021/3/13 如果是使用code验证，就服务端没必要保存openid和unionId了）
        // 感觉还是需要的当登陆成功后进行支付就可以在redis中获取了！
        // key:"WEXIN_USER_OPENID"+":"+userId,  value: sid
        // key:"WEXIN_USER_UNIONID"+":"+userId,  value: sid

        String userToken = sid.nextShort();
        // 存入redis中  key:sid,  value: userId + openid + unionId + type（消费者）
        // 这么存储是为了userToken有效时，能够直接使用openid调用支付等微信接口（省的到mysql中去获取）



//        //  缓存一份新的openid和userToken
//        redisTemplate.opsForValue().set("WEXIN_USER_OPENID:"+userId,openid,432000);  // 5天登录状态
//        redisTemplate.opsForValue().set("WEXIN_USER_SESSIONKEY:"+userId,sessionKey,432000);

        return userToken;
    }


}
