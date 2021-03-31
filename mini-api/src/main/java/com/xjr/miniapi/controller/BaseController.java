package com.xjr.miniapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

//    @Autowired
//    public RedisTemplate redisTemplate;

//    public static final String USER_REDIS_SESSION = "user-redis-session";

    // 文件保存的命名空间
//    public static final String FILE_SPACE = "D:\\haitao_dev";
    public static final String FILE_SPACE = "/haitao_dev";

    public static final String APP_ID = "wxcb5adcf07fc3a00d";

    public static final String APP_SECRET = "564162dfd19ebcd23a3eabfbbb9c44a5";

    // 每页分页的记录数
    public static final Integer PAGE_SIZE = 6;

}
