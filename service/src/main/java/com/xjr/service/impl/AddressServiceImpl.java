package com.xjr.service.impl;

import com.xjr.mapper.UsersAddressMapper;
import com.xjr.pojo.UsersAddress;
import com.xjr.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private UsersAddressMapper usersAddressMapper;

    @Override
    public List<UsersAddress> getAllAddressByUserId(String userId) {

        List<UsersAddress> list = new ArrayList<>();

        if(userId != null && userId != ""){
            list = usersAddressMapper.selectAllAddressByUserId(userId);
        }

        return list;
    }

    @Override
    public void deleteByUaIdAndUserId(String uaId, String userId) {

        if(!StringUtils.isBlank(uaId) && !StringUtils.isBlank(userId)){
            usersAddressMapper.deleteByUaIdAndUserId(uaId, userId);
        }
    }

    @Transactional
    @Override
    public Integer submitAddress(UsersAddress usersAddress) throws ParseException {

        Integer ua_id = -1;
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sqlDate = sp.parse(sp.format(new Date()));

        // 1、新增是没有ua_id；2、修改有ua_id
        if(usersAddress.getUaId() != null){
            ua_id = usersAddress.getUaId();
            usersAddress.setCreateTime(sqlDate);
            usersAddressMapper.updateByPrimaryKeySelective(usersAddress);

        }else {
            usersAddress.setCreateTime(sqlDate);
//            System.out.println("插入创建时间:"+usersAddress.toString());

            usersAddressMapper.insertSelective(usersAddress);
            usersAddress = usersAddressMapper.selectOne(usersAddress);
            ua_id = usersAddress.getUaId();
        }

        return ua_id;
    }

    @Override
    public void cancelDefaultAddress() {
        usersAddressMapper.cancelDefault();
    }

    @Override
    public void setDefaultAddrByUaIdAndUserId(String uaId, String userId) {
        usersAddressMapper.setDefaultAddress(uaId, userId);
    }

}
