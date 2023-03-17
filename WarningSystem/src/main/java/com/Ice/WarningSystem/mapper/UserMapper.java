package com.Ice.WarningSystem.mapper;

import com.Ice.WarningSystem.bean.User;
import com.Ice.WarningSystem.form.login.SelectLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {

    String findUsername(Long updateUserId);
}
