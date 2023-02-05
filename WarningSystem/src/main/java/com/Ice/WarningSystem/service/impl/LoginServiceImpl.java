package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.dao.UserDao;
import com.Ice.WarningSystem.form.login.SelectLogin;
import com.Ice.WarningSystem.mapper.UserMapper;
import com.Ice.WarningSystem.service.LoginService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    UserMapper userMapper;

    @Resource
    UserDao userDao;

    @Override
    public User findPassWord(SelectLogin selectLogin) {
        //由于username不能相同，所以username是惟一的
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,selectLogin.getUsername());
        return userDao.selectOne(wrapper);
    }
}
