package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.Menu;
import com.Ice.WarningSystem.form.menu.InsertMenuForm;
import com.Ice.WarningSystem.form.menu.SelectMenuPageForm;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface MenuService {
    int insertMenu(InsertMenuForm insertMenuForm);

    int maxMenuIndex(InsertMenuForm insertMenuForm);

    IPage<Menu> findMenuPage(SelectMenuPageForm pageForm);

    List<Menu> findMenuList(Integer menuIndex);

}
