package com.Ice.WarningSystem.enums.user;

public enum InsertUserIsSuccess {
    INSERT_USER_IS_SUCCESS(1,"添加用户成功"),
    INSERT_USER_IS_ERROR_CREATE_USER_ID(2,"添加用户失败，创建者id和修改者id未生成"),
    INSERT_USER_IS_ERROR(0,"添加用户失败，id未生成"),
    INSERT_ERROR_UNKNOWN("未知错误"),
    INSERT_ERROR_EXCEPTION("插入数据发生异常，插入失败");

    private int Code;
    private String msg;

    InsertUserIsSuccess(String msg){
        this.msg=msg;
    }

    InsertUserIsSuccess(int Code,String msg){
        this.Code=Code;
        this.msg=msg;
    }

    public int getCode(){
        return this.Code;
    }

    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
