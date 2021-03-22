package com.xjr.mapper;

import com.xjr.pojo.UsersAddress;
import com.xjr.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersAddressMapper extends MyMapper<UsersAddress> {

    // 根据userId查询所有地址
    public List<UsersAddress> selectAllAddressByUserId(@Param("userId")String userId);

    // 根据u_id和ua_id删除地址
    public void deleteByUaIdAndUserId(@Param("uaId")String uaId, @Param("userId")String userId);

    // 取消default状态
    public void cancelDefault();

    // 设置default状态
    public void setDefaultAddress(@Param("uaId")String uaId, @Param("userId")String userId);


}