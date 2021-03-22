package com.xjr.miniapi.controller;

import com.xjr.pojo.Users;
import com.xjr.pojo.UsersAddress;
import com.xjr.service.AddressService;
import com.xjr.utils.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@Api(value = "货运地址业务接口", tags = {"货运地址管理业务的controller"})
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    // 日志对象
    private static final Logger logger = Logger.getLogger(UserController.class);

    /**
     * 获取用户所有收货地址
     */
    @ApiOperation(value = "获取用户所有收货地址", httpMethod = "GET", notes = "获取用户所有收货地址")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public JSONResult getAllAddress(@ApiParam(required = true, value = "用户id", name = "userId")
                                 @RequestParam(value = "userId", required = true) String userId)
    {
        logger.info("userId============================="+userId);
        if(userId == null){
            return JSONResult.errorMsg("请检查传入对象！");
        }

        List<UsersAddress> addrList = addressService.getAllAddressByUserId(userId);

        logger.info("addrList============================="+addrList);

        return JSONResult.ok(addrList);
    }


    /**
     * 提交用户收货地址
     */
    @ApiOperation(value = "提交用户收货地址", httpMethod = "POST", notes = "提交用户收货地址")
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public JSONResult submitAddress(@ApiParam(required = true, value = "用户地址对象", name = "usersAddress")
                                 @RequestBody UsersAddress usersAddress) throws ParseException {
        logger.info("usersAddress============================="+usersAddress.toString());
//        System.out.println(usersAddress.toString());

        if(usersAddress == null){
            return JSONResult.errorMsg("请检查传入对象！");
        }

        Integer uaId = addressService.submitAddress(usersAddress);

        logger.info("uaId============================="+uaId);


        return JSONResult.ok(uaId);
    }


    /**
     * 删除指定用户指定收货地址
     */
    @ApiOperation(value = "删除指定用户指定收货地址", httpMethod = "POST", notes = "删除指定用户指定收货地址")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JSONResult deleteUserAddress(@ApiParam(required = true, value = "地址id", name = "uaId")
                                        @RequestParam(value = "uaId", required = true) String uaId,
                                        @ApiParam(required = true, value = "用户id", name = "userId")
                                        @RequestParam(value = "userId", required = true) String userId)
    {
        logger.info("uaId============================="+uaId);
        logger.info("userId============================="+userId);

        if(userId == null || uaId == null){
            return JSONResult.errorMsg("请检查传入对象！");
        }

        addressService.deleteByUaIdAndUserId(uaId, userId);

        return JSONResult.ok();
    }

    /**
     * 设置默认收货地址
     */
    @Transactional
    @ApiOperation(value = "设置默认收货地址", httpMethod = "POST", notes = "设置默认收货地址")
    @RequestMapping(value = "/default", method = RequestMethod.POST)
    public JSONResult setDefaultAddress(@ApiParam(required = true, value = "地址id", name = "uaId")
                                        @RequestParam(value = "uaId", required = true) String uaId,
                                        @ApiParam(required = true, value = "用户id", name = "userId")
                                        @RequestParam(value = "userId", required = true) String userId)
    {
        logger.info("uaId============================="+uaId);
        logger.info("userId============================="+userId);

        if(userId == null || uaId == null){
            return JSONResult.errorMsg("请检查传入对象！");
        }

        // 1、先取消默认地址
        addressService.cancelDefaultAddress();

        // 2、设置默认地址
        addressService.setDefaultAddrByUaIdAndUserId(uaId, userId);

        return JSONResult.ok();
    }


}
