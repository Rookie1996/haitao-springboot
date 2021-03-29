package com.xjr.service.impl;

import com.xjr.mapper.UsersMapper;
import com.xjr.pojo.Users;
import com.xjr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required=false)
    private UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryOpenidIsExist(String openid) {

        //有事务就使用事务，没有就不使用
        Users user = new Users();
        user.setOpenid(openid);

        Users res = usersMapper.selectOne(user);

        return res != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public String queryUserIdByOpenid(String openid) {

        Users user = new Users();
        user.setOpenid(openid);

        Users res = usersMapper.selectOne(user);

        return res.getuId();
    }

    @Transactional(propagation = Propagation.REQUIRED) //默认开启事务
    @Override
    public void saveUser(Users users) {

        // 直接插入用户
        usersMapper.insertSelective(users);
    }

    @Override
    public void updateUserInfo(Users users) {

        // 部分更新mysql
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override
    public Users queryUserInfo(String userId) {

        Users users = usersMapper.selectByPrimaryKey(userId);

        return users;
    }
}
