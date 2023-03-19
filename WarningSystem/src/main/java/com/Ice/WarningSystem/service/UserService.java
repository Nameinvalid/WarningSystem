package com.Ice.WarningSystem.service;

import com.Ice.WarningSystem.bean.Role;
import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.form.user.DeleteUserForm;
import com.Ice.WarningSystem.form.user.InsertUserContent;
import com.Ice.WarningSystem.form.user.SelectUserPageForm;
import com.Ice.WarningSystem.form.user.UpdateUserForm;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface UserService {

    boolean findUsername(String username);

    int insertUser(InsertUserContent insertUserContent);

    int updateUser(UpdateUserForm updateUserForm);

    int deleteUser(DeleteUserForm deleteUserForm);

    IPage<User> findIPageUser(SelectUserPageForm selectUserPageForm);

    List<Role> findAllRole();
}
