package com.Ice.WarningSystem.form.picture;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "前端分页查询照片实体类")
public class SelectPicturePage {
    @ApiModelProperty(value = "照片名称")
    private String photoName;
    @ApiModelProperty(value = "页数",example = "1")
    private Long pageNum;
    @ApiModelProperty(value = "大小",example = "20")
    private Long pageSize;

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "SelectPicturePage{" +
                "photoName='" + photoName + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
