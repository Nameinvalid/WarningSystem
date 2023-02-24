package com.Ice.WarningSystem.enums.role;

public enum InsertRoleIsSuccess {
    INSERT_ROLE_IS_SUCCESS(1,"添加角色成功"),
    INSERT_ROLE_IS_ERROR_CREATE_USER_ID(2,"添加角色失败，创建者id和修改者id未生成"),
    INSERT_ROLE_IS_ERROR(0,"添加角色失败，id未生成"),
    INSERT_ERROR_UNKNOWN("未知错误"),
    INSERT_ERROR_EXCEPTION("插入数据发生异常，插入失败");

    private int Code;
    private String msg;

    InsertRoleIsSuccess(String msg){
        this.msg=msg;
    }

    InsertRoleIsSuccess(int Code,String msg){
        this.Code=Code;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return Code;
    }

    @Override
    public String toString() {
        return "InsertRoleIsSuccess{" +
                "Code=" + Code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
