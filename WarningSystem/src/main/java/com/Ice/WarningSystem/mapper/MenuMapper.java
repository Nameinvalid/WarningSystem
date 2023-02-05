package com.Ice.WarningSystem.mapper;

import com.Ice.WarningSystem.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> findAll(Menu menu);
}
