package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.form.menu.InsertMenuForm;

public interface MenuService {
    int insertMenu(InsertMenuForm insertMenuForm);

    int maxMenuIndex(InsertMenuForm insertMenuForm);
}
