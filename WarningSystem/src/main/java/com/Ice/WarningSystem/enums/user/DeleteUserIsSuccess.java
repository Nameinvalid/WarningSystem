package com.Ice.WarningSystem.enums.user;

public enum DeleteUserIsSuccess {
    DELETE_USER_IS_SUCCESS(1,"删除用户成功"),
    DELETE_USER_IS_ERROR_ID_REPETITION(2,"删除用户过多，证明被修改人id重复，数据库中有脏数据"),
    DELETE_USER_IS_ERROR(0,"删除失败，也就是没有用户的id，或者是sql发生错误"),
    DELETE_ERROR_UNKNOWN(-1,"未知错误"),
    DELETE_ERROR_EXCEPTION("修改数据发生异常，修改失败");

    private int Code;
    private String msg;

    DeleteUserIsSuccess(String msg){
        this.msg=msg;
    }
    DeleteUserIsSuccess(int Code,String msg){
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
