package com.xjr.service;

import com.xjr.pojo.Users;

public interface UserService {

    /**
     * 判断openid是否存在
     * @param openid
     * @return
     */
    public boolean queryOpenidIsExist(String openid);


    /**
     * 根据openid查询用户id
     * @param openid
     * @return
     */
    public String queryUserIdByOpenid(String openid);

    /**
     * 保存注册用户
     * @param users
     */
    public void saveUser(Users users);

    /**
     * 用户修改信息
     *
     * @param users
     */
    public void updateUserInfo(Users users);

    /**
     * 根据userId查询用户信息
     * @param userId
     * @return
     */
    public Users queryUserInfo(String userId);

}
