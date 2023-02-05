package com.Ice.WarningSystem.service.impl;

import com.Ice.WarningSystem.bean.Menu;
import com.Ice.WarningSystem.dao.MenuDao;
import com.Ice.WarningSystem.form.menu.InsertMenuForm;
import com.Ice.WarningSystem.mapper.MenuMapper;
import com.Ice.WarningSystem.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
        System.out.println(insertMenuForm);
        Menu menu=new Menu();
        menu.setMenuName(insertMenuForm.getMenuName());
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menu.setCreateUserId(insertMenuForm.getCreateUserId());
        menu.setUpdateUserId(insertMenuForm.getCreateUserId());
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
        System.out.println(menu);
        //菜单参数添加完成后，进行添加
        menuDao.insert(menu);
        return 0;
    }
}
