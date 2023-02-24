package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.Role;
import com.Ice.WarningSystem.form.role.DeleteRoleForm;
import com.Ice.WarningSystem.form.role.InsertRoleForm;
import com.Ice.WarningSystem.form.role.SelectRolePageForm;
import com.Ice.WarningSystem.form.role.UpdateRoleForm;
import com.baomidou.mybatisplus.core.metadata.IPage;

public interface RoleService {
    IPage<Role> findRolePage(SelectRolePageForm selectRolePageForm);

    int insertRole(InsertRoleForm roleForm);

    int updateRole(UpdateRoleForm roleForm);

    int deleteRole(DeleteRoleForm roleForm);
}
