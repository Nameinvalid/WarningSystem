package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.Picture;
import com.Ice.WarningSystem.form.picture.DeletePicture;
import com.Ice.WarningSystem.form.picture.InsertPicture;
import com.Ice.WarningSystem.form.picture.SelectPicturePage;
import com.Ice.WarningSystem.form.picture.UpdatePicture;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpStatus;
import com.Ice.WarningSystem.service.PictureService;
import com.Ice.WarningSystem.vo.PictureVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "PictureController",tags = "照片管理控制层")
@RestController
@Slf4j
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    PictureService pictureService;

    @PostMapping("/selectPicturePage")
    @ApiOperation(value = "照片的分页查询")
    public HttpResult selectPicturePage(@RequestBody SelectPicturePage picturePage){
        try {
            IPage<PictureVO> page=pictureService.selectPicturePage(picturePage);
            if (page.getSize()==0){
                return HttpResult.ok("无结果",page);
            }else {
                return HttpResult.ok("查询成功",page);
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_EXPECTATION_FAILED,"未知异常，请联系管理员",e);
        }
    }

    @PostMapping("/insertPicture")
    @ApiOperation(value = "新增添照片")
    public HttpResult insertPicture(@RequestBody InsertPicture picture){
        try {
            boolean isSuccess=pictureService.insertPicture(picture);
            if (isSuccess){
                return HttpResult.ok("新增成功");
            }else{
                return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"添加失败");
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_EXPECTATION_FAILED,"添加异常",e);
        }
    }

    @PutMapping("/updatePicture")
    @ApiOperation(value = "修改照片信息")
    public HttpResult updatePicture(@RequestBody UpdatePicture updatePicture){
        try{
            boolean isSuccess=pictureService.updatePicture(updatePicture);
            if (isSuccess){
                return HttpResult.ok("修改成功");
            }else {
                return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"修改失败");
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_EXPECTATION_FAILED,"修改异常",e);
        }
    }

    @DeleteMapping("/deletePicture")
    @ApiOperation(value = "删除图片")
    public HttpResult deletePicture(@RequestBody DeletePicture deletePicture){
        try {
            boolean isSuccess=pictureService.deletePicture(deletePicture);
            if (isSuccess){
                return HttpResult.ok("删除成功");
            }else {
                return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"修改失败");
            }
        }catch (Exception e){
            return HttpResult.error(HttpStatus.SC_EXPECTATION_FAILED,"删除异常",e);
        }
    }
}
