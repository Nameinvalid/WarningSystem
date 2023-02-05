package com.Ice.WarningSystem.service;


import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.form.login.SelectLogin;

public interface LoginService {
    User findPassWord(SelectLogin selectLogin);
}
