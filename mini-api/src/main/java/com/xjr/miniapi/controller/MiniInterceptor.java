package com.xjr.miniapi.controller;

import com.xjr.utils.JSONResult;
import com.xjr.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class MiniInterceptor implements HandlerInterceptor {

//    @Autowired
//    public RedisTemplate redisTemplate;
    public static final String WEIXIN_USER_SESSIONKEY = "WEIXIN_USER_SESSIONKEY";
    public static final String WEXIN_USER_OPENID = "WEIXIN_USER_OPENID";

    /**
     * 拦截请求，Controller调用之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 返回false：请求被拦截
        // 返回true：请求放行

//        // ***前端header发送的userId和userToken都是用来跟拦截器进行验证的。
//        String userId = request.getHeader("userId");
//        String userToken = request.getHeader("userToken");
//
//        // userId和userToken均不为空，为空则需要登录
//        if (StringUtils.isNoneBlank(userId) && StringUtils.isNoneBlank(userToken)) {
//
//            String uniqueToken = (String) redisTemplate.opsForValue().get(WEIXIN_USER + ":" + userId);
//            // redis失效重新登录
//            if (StringUtils.isEmpty(uniqueToken) && StringUtils.isBlank(uniqueToken)) {
//                System.out.println("redis失效，登录已过期，请重新登录...");
                  // 502的错误回去前端，就会重新发送新的获取userToken请求
//                returnErrorResponse(response, new JSONResult().errorTokenMsg("登录已过期，请重新登录"));
//                return false;
//            } else {// 用户重新登录,生成了新的userToken
//                if (!uniqueToken.equals(userToken)) {
//                    System.out.println("账号在别的手机端登录...");
//                    returnErrorResponse(response, new JSONResult().errorTokenMsg("账号被挤出"));
//                    return false;
//                }
//            }
//        } else {
//            System.out.println("请登录...");
//            returnErrorResponse(response, new JSONResult().errorTokenMsg("请登录..."));
//            return false;
//        }

        return true;
    }

    public void returnErrorResponse(HttpServletResponse response, JSONResult result)
            throws IOException, UnsupportedEncodingException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
//            out.write(JSONUtils.toJSONString(result).getBytes("utf-8"));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 请求Controller之后，渲染视图之前
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 请求Controller之后，视图渲染之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
