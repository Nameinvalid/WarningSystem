package com.Ice.WarningSystem.enums.role;

public enum DeleteRoleIsSuccess {
    DELETE_USER_IS_SUCCESS(1,"删除角色成功"),
    DELETE_USER_IS_ERROR_ID_REPETITION(2,"删除角色过多，证明被修改人id重复，数据库中有脏数据"),
    DELETE_USER_IS_ERROR(0,"删除失败，也就是没有角色的id，或者是sql发生错误"),
    DELETE_ERROR_UNKNOWN(-1,"未知错误"),
    DELETE_ERROR_EXCEPTION("修改数据发生异常，修改失败");

    private int Code;
    private String msg;

    DeleteRoleIsSuccess(String msg){
        this.msg=msg;
    }
    DeleteRoleIsSuccess(int Code,String msg){
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
