package com.Ice.WarningSystem.controller;

import com.Ice.WarningSystem.bean.Picture;
import com.Ice.WarningSystem.form.picture.InsertPicture;
import com.Ice.WarningSystem.form.picture.SelectPicturePage;
import com.Ice.WarningSystem.http.HttpResult;
import com.Ice.WarningSystem.http.HttpStatus;
import com.Ice.WarningSystem.service.PictureService;
import com.Ice.WarningSystem.vo.PictureVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员",e);
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
            return HttpResult.error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"添加异常",e);
        }
    }
}
