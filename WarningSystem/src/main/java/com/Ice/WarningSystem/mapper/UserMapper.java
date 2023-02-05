package com.Ice.WarningSystem.mapper;

import com.Ice.WarningSystem.form.login.SelectLogin;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    SelectLogin findAll(String username);
}
