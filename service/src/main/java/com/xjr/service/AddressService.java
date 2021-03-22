package com.xjr.service;

import com.xjr.pojo.UsersAddress;

import java.text.ParseException;
import java.util.List;

public interface AddressService {

    /**
     * 根据用户id查询所有收货地址
     * @param userId
     * @return
     */
    public List<UsersAddress> getAllAddressByUserId(String userId);

    /**
     * 删除指定用户的指定收货地址
     * @param uaId(String不知道为啥也可以匹配Integer？)
     * @param userId
     */
    public void deleteByUaIdAndUserId(String uaId, String userId);

    /**
     * 提交收货地址
     * @param usersAddress
     * @return
     */
    public Integer submitAddress(UsersAddress usersAddress) throws ParseException;

    /**
     * 取消默认收货地址
     */
    public void cancelDefaultAddress();

    /**
     * 设置默认地址状态
     * @param uaId
     * @param userId
     */
    public void setDefaultAddrByUaIdAndUserId(String uaId, String userId);
}
