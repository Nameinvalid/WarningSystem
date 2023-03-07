package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.Menu;
import com.Ice.WarningSystem.dao.MenuDao;
import com.Ice.WarningSystem.form.menu.DeleteMenuForm;
import com.Ice.WarningSystem.form.menu.InsertMenuForm;
import com.Ice.WarningSystem.form.menu.SelectMenuPageForm;
import com.Ice.WarningSystem.form.menu.UpdateMenuForm;
import com.Ice.WarningSystem.mapper.MenuMapper;
import com.Ice.WarningSystem.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    MenuDao menuDao;
    @Resource
    MenuMapper menuMapper;

    @Override
    public int maxMenuIndex(InsertMenuForm insertMenuForm) {
        LambdaQueryWrapper<Menu> wrapper=new LambdaQueryWrapper<>();
        if(insertMenuForm.getMenuGrade()==1){
            //where 相应的菜单等级
            //如果是1级菜单就这样就行了
            wrapper.eq(Menu::getMenuGrade,insertMenuForm.getMenuGrade());
            return menuDao.selectCount(wrapper);
        }else {
            //如果是2级菜单的话，需要父菜单的id条件,筛选出相同父级菜单的二级菜单，方便统计
            wrapper.eq(Menu::getMenuGrade,insertMenuForm.getMenuGrade());
            wrapper.eq(Menu::getbId,insertMenuForm.getParentMenuId());
            return menuDao.selectCount(wrapper);
        }
    }

    @Override
    public int insertMenu(InsertMenuForm insertMenuForm) {
        Menu menu=new Menu();
        menu.setMenuName(insertMenuForm.getMenuName());
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menu.setCreateUserId(insertMenuForm.getUpdateUserId());
        menu.setUpdateUserId(insertMenuForm.getUpdateUserId());
        if (insertMenuForm.getMenuGrade()==1){
            //根据菜单等级，说明是一级菜单
            //查询相应的级别菜单最大值，然后+1
            int menuIndex=maxMenuIndex(insertMenuForm)+1;
            menu.setMenuIndex(menuIndex);
            menu.setMenuGrade(insertMenuForm.getMenuGrade());
        }else {
            //如果是二级菜单
            int menuIndex=maxMenuIndex(insertMenuForm)+1;
            menu.setMenuIndex(menuIndex);
            menu.setbId(insertMenuForm.getParentMenuId());
            menu.setParentMenuName(insertMenuForm.getParentMenuName());
            menu.setMenuGrade(insertMenuForm.getMenuGrade());
        }
        //菜单参数添加完成后，进行添加
        return menuDao.insert(menu);
    }

    @Override
    public IPage<Menu> findMenuPage(SelectMenuPageForm pageForm) {
        Page<Menu> page=new Page<>(pageForm.getPageNum(),pageForm.getPageSize());
        LambdaQueryWrapper<Menu> wrapper=new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageForm.getMenuName())){
            wrapper.like(Menu::getMenuName,pageForm.getMenuName());
        }
        if (pageForm.getMenuGrade()!=null){
            wrapper.eq(Menu::getMenuGrade,pageForm.getMenuGrade());
        }
        wrapper.orderByAsc(Menu::getMenuGrade);
        page=menuDao.selectPage(page,wrapper);
        return page;
    }

    @Override
    public List<Menu> findMenuList(Integer menuIndex) {
        LambdaQueryWrapper<Menu> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getMenuGrade,menuIndex);
        wrapper.orderByAsc(Menu::getbId);
        return menuDao.selectList(wrapper);
    }

    @Override
    public int updateMenu(UpdateMenuForm menuForm) {
        Menu menu=new Menu();
        menu.setMenuName(menuForm.getMenuName());
        menu.setMenuGrade(menuForm.getMenuGrade());
        menu.setParentMenuName(menuForm.getParentMenuName());
        menu.setbId(menuForm.getParentMenuId());
        menu.setUpdateUserId(menuForm.getUpdateUserId());
        menu.setUpdateTime(new Date());
        LambdaUpdateWrapper<Menu> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Menu::getId,menuForm.getMenuId());
        int count=menuDao.update(menu,wrapper);
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
    public int deleteMenu(DeleteMenuForm menuForm) {
        LambdaUpdateWrapper<Menu> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Menu::getId,menuForm.getMenuId()).set(Menu::getType,0)
                .set(Menu::getUpdateUserId,menuForm.getDeleteUserId())
                .set(Menu::getUpdateTime,new Date());
        int count=menuDao.update(null, wrapper);
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
}
