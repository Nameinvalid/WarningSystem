package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.Role;
import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.dao.RoleDao;
import com.Ice.WarningSystem.form.role.DeleteRoleForm;
import com.Ice.WarningSystem.form.role.InsertRoleForm;
import com.Ice.WarningSystem.form.role.SelectRolePageForm;
import com.Ice.WarningSystem.form.role.UpdateRoleForm;
import com.Ice.WarningSystem.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleDao roleDao;

    @Override
    public IPage<Role> findRolePage(SelectRolePageForm selectRolePageForm) {
        Page<Role> page=new Page<>(selectRolePageForm.getPageNum(), selectRolePageForm.getPageSize());
        LambdaQueryWrapper<Role> wrapper=new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(selectRolePageForm.getRoleName())){
            wrapper.like(Role::getRoleName,selectRolePageForm.getRoleName());
        }
        if (StringUtils.isNotBlank(selectRolePageForm.getRemark())){
            wrapper.like(Role::getRemark,selectRolePageForm.getRemark());
        }
        wrapper.orderByAsc(Role::getCreateTime);
        page=roleDao.selectPage(page,wrapper);
        return page;
    }

    @Override
    public int insertRole(InsertRoleForm roleForm) {
        Role role=new Role();
        role.setRoleName(roleForm.getRoleName());
        role.setRemark(roleForm.getRemark());
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setType(1);
        int count=roleDao.insert(role);
        Long roleId=role.getId();
        if (roleId==null || count==0){
            return 0;
        }else {
            LambdaUpdateWrapper<Role> wrapper=new LambdaUpdateWrapper<>();
            wrapper.eq(Role::getId,roleId).set(Role::getCreateUserId,roleForm.getUpdateUserId())
                    .set(Role::getUpdateUserId,roleForm.getUpdateUserId());
            int countId=roleDao.update(null,wrapper);
            if (countId==1){
                return 1;
            }else {
                return 2;
            }
        }
    }

    @Override
    public int updateRole(UpdateRoleForm roleForm) {
        Role role=new Role();
        role.setRoleName(roleForm.getRoleName());
        role.setRemark(roleForm.getRemark());
        role.setType(roleForm.getType());
        role.setUpdateUserId(roleForm.getUpdateUserId());
        role.setUpdateTime(new Date());
        LambdaUpdateWrapper<Role> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Role::getId,roleForm.getRoleId());
        int count=roleDao.update(role,updateWrapper);
        if(count==0){
            //????????????????????????????????????????????????????????????id????????????sql????????????
            return 0;
        }else if(count==1){
            //????????????????????????
            return 1;
        }else if(count>1){
            //???????????????????????????????????????id?????????????????????????????????
            return 2;
        }else {
            //??????????????????
            return -1;
        }
    }

    @Override
    public int deleteRole(DeleteRoleForm roleForm) {
        LambdaUpdateWrapper<Role> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Role::getId,roleForm.getRoleId()).set(Role::getType,0)
                .set(Role::getUpdateUserId,roleForm.getDeleteUserId())
                .set(Role::getUpdateTime,new Date());
        int count=roleDao.update(null,wrapper);
        if(count==0){
            //??????????????????????????????????????????????????????id????????????sql????????????
            return 0;
        }else if(count==1){
            //????????????????????????
            return 1;
        }else if(count>1){
            //???????????????????????????????????????id?????????????????????????????????
            return 2;
        }else {
            //??????????????????
            return -1;
        }
    }
}
