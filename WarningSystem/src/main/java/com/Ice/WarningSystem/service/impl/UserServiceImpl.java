package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.Role;
import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.dao.RoleDao;
import com.Ice.WarningSystem.dao.UserDao;
import com.Ice.WarningSystem.form.user.DeleteUserForm;
import com.Ice.WarningSystem.form.user.InsertUserContent;
import com.Ice.WarningSystem.form.user.SelectUserPageForm;
import com.Ice.WarningSystem.form.user.UpdateUserForm;
import com.Ice.WarningSystem.mapper.UserMapper;
import com.Ice.WarningSystem.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Resource
    RoleDao roleDao;


    @Override
    public boolean findUsername(String username) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        //检测这个用户名是否存在，然后判断账户是否弃用，弃用的话可以以这个用户名的形式存在
        int count=userDao.selectCount(wrapper);
        return count == 0;
    }

    @Override
    public int insertUser(InsertUserContent insertUserContent) {
        boolean insertUsername=findUsername(insertUserContent.getUsername());
        if(insertUsername){
            User user=new User();
            user.setUsername(insertUserContent.getUsername());
            user.setPassword(insertUserContent.getPassword());
            user.setPhone(insertUserContent.getPhone());
            user.setName(insertUserContent.getName());
            user.setSex(insertUserContent.getSex());
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setType(1);
            int count=userDao.insert(user);
            Long userId=user.getId();
            if(userId==null || count==0){
                //如果id是空的或者插入的行数是0的话，说明没插入进去，直接返回0：插入失败
                return 0;
            }else {
                LambdaUpdateWrapper<User> wrapper=new LambdaUpdateWrapper<>();
                if (insertUserContent.getUpdateUserId()==0){
                    wrapper.eq(User::getId,userId).set(User::getCreateUserId,userId)
                            .set(User::getUpdateUserId,userId);
                }else {
                    wrapper.eq(User::getId,userId).set(User::getCreateUserId,insertUserContent.getUpdateUserId())
                            .set(User::getUpdateUserId,insertUserContent.getUpdateUserId());
                }
                int countId= userDao.update(null,wrapper);
                if(countId==1){
                    //等于1说明插入成功，并且修改成功后面的创建者id和修改者id都已经同步
                    //返回1：插入成功，且创建者id修改者id同步成功
                    return 1;
                }else {
                    //如果插入成功之后，但是countId不是1，说明插入的多了或者干脆创建者id或者修改者id没有同步成功
                    //返回字节码2，说明返回修改的多了
                    return 2;
                }
            }
        }else {
            return 0;
        }

    }

    @Override
    public int updateUser(UpdateUserForm updateUserForm) {
        User user=new User();
        user.setUpdateUserId(updateUserForm.getUpdateUserId());
        user.setUpdateTime(new Date());
        user.setUsername(updateUserForm.getUsername());
        user.setType(updateUserForm.getType());
        user.setName(updateUserForm.getName());
        user.setPhone(updateUserForm.getPhone());
        user.setSex(updateUserForm.getSex());
        user.setPassword(updateUserForm.getPassword());
        LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,updateUserForm.getUserId());
        int count=userDao.update(user,updateWrapper);
        if(count==0){
            //相当于没有修改成功，也就是没有被修改人的id，或者是sql发生错误
            return 0;
        }else if(count==1){
            //相当于修改成功了
            return 1;
        }else if(count>1){
            //修改行数多了，证明被修改人id重复，数据库中有脏数据
            return 2;
        }else {
            //发生未知错误
            return -1;
        }
    }

    @Override
    public int deleteUser(DeleteUserForm userForm) {
        //所有的账号并不是真正的删除，只是将账号弃用而已，如果账号弃用1年以上才会被删除
        LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,userForm.getUserId()).set(User::getType,0)
                .set(User::getUpdateUserId,userForm.getDeleteUserId())
                .set(User::getUpdateTime,new Date());
        int count=userDao.update(null,updateWrapper);
        if(count==0){
            //相当于没有修改成功，也就是没有用户的id，或者是sql发生错误
            return 0;
        }else if(count==1){
            //相当于修改成功了
            return 1;
        }else if(count>1){
            //修改行数多了，证明被修改人id重复，数据库中有脏数据
            return 2;
        }else {
            //发生未知错误
            return -1;
        }
    }

    @Override
    public IPage<User> findIPageUser(SelectUserPageForm selectUserPageForm) {
        Page<User> page=new Page<>(selectUserPageForm.getPageNum(),selectUserPageForm.getPageSize());
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(selectUserPageForm.getName())){
            wrapper.like(User::getName,selectUserPageForm.getName());
        }
        if(selectUserPageForm.getPhone()!=null){
            wrapper.like(User::getPhone,selectUserPageForm.getPhone());
        }
        wrapper.orderByAsc(User::getCreateTime);
        page=userDao.selectPage(page,wrapper);
        return page;
    }

    @Override
    public List<Role> findAllRole() {
        LambdaQueryWrapper<Role> wrapper=new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Role::getCreateTime);
        return roleDao.selectList(wrapper);
    }
}
