package com.xjr.miniapi.controller;

import com.xjr.pojo.Users;
import com.xjr.service.UserService;
import com.xjr.utils.JSONResult;
import io.swagger.annotations.*;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.HashMap;


@RestController
@Api(value = "用户相关业务接口", tags = {"用户相关业务的controller"})
@RequestMapping("/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

//    @Autowired
//    public RedisTemplate redisTemplate;

    @Autowired
    private Sid sid; /* 不知道为啥注入不了ioc，可能没auto-scan？ ---确实！加上@ComponentScan即可*/

    // 日志对象
    private static final Logger logger = Logger.getLogger(UserController.class);


    /**
     * 更新用户个人信息接口
     */
    @ApiOperation(value = "更新用户个人信息", httpMethod = "POST", notes = "更新用户个人信息")
    @RequestMapping(value = "/personInfo", method = RequestMethod.POST)
    public JSONResult personInfo(@ApiParam(required = true, value = "用户对象", name = "users")
            @RequestBody Users users)
    {
        logger.info("users============================="+users.toString());
        if(users == null){
            return JSONResult.errorMsg("请检查传入对象！");
        }
        userService.updateUserInfo(users);

        return JSONResult.ok();
    }


    /**
     * 更新个人定位接口
     */
    @ApiOperation(value = "更新个人定位", httpMethod = "POST", notes = "更新个人定位")
    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public JSONResult location(
            @ApiParam(required = true, value = "用户id", name = "userId")
            @RequestParam(value = "userId", required = true)String userId,
            @ApiParam(required = true, value = "定位字符串", name = "location")
            @RequestParam(value = "location", required = true)String location)
    {
        logger.info("userId============"+userId);
        logger.info("location============"+location);
        if(userId.equals("") || userId == null){
            return JSONResult.errorMsg("请登录！");
        }
        if(location.equals("") || location == null){
            return JSONResult.errorMsg("请检查定位地址！");
        }

        Users users = new Users();
        users.setuId(userId);
        users.setuLocation(location);
        userService.updateUserInfo(users);

        return JSONResult.ok();
    }


    // 2021/3/29获取个人信息接口
    @ApiOperation(value = "获取完整个人信息接口", httpMethod = "GET", notes = "获取完整个人信息接口")
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public JSONResult getUserInfo(@ApiParam(required = true, value = "用户id", name = "userId")
                                  @RequestParam(value = "userId", required = true)String userId){


        logger.info("获取完整个人信息接口:userId========================================"+userId);

        Users users = userService.queryUserInfo(userId);

        return JSONResult.ok(users);
    }


}
