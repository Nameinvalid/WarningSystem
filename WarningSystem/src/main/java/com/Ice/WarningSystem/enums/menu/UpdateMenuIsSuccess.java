package com.Ice.WarningSystem.enums.menu;

public enum UpdateMenuIsSuccess {
    UPDATE_MENU_IS_SUCCESS(1,"修改菜单成功"),
    UPDATE_MENU_IS_ERROR_ID_REPETITION(2,"修改菜单过多，证明被修改人id重复，数据库中有脏数据"),
    UPDATE_MENU_IS_ERROR(0,"修改失败，也就是没有被修改人的id，或者是sql发生错误"),
    UPDATE_ERROR_UNKNOWN(-1,"未知错误"),
    UPDATE_ERROR_EXCEPTION("修改数据发生异常，修改失败");

    private int Code;
    private String msg;

    UpdateMenuIsSuccess(String msg){
        this.msg=msg;
    }

    UpdateMenuIsSuccess(int Code,String msg){
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
