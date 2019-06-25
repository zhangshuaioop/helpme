package com.help.helpme.controller.sys;

import com.help.helpme.entity.sys.SysAdmin;
import com.help.helpme.entity.sys.SysBanner;
import com.help.helpme.service.sys.SysBannerService;
import com.help.helpme.utils.CurrentUtil;
import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 * @description: Banner
 * @author: zhangshuai
 * @create: 2019-06-10 15:04
 */
@RestController
@RequestMapping("/sysBanner")
@Api(description = "Banner")
public class SysBannerController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SysBannerService sysBannerService;

    @Value("${upload}")
    private String upload;

    @ApiOperation(
            value = "分页Banner列表",
            notes = "分页Banner列表",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/findPage",method=RequestMethod.POST)
    @PreAuthorize("hasRole('banner')")
    public Result findPage(@RequestBody SysBanner sysBanner) {
        sysBanner.setFlagDelete(false);
        logger.info("分页Banner列表开始,入参:sysBanner="+sysBanner.toString());
        Result result = sysBannerService.findPage(sysBanner);
        logger.info("分页Banner列表结束,出参:result="+result.getData().toString());
        return result;
    }


    @ApiOperation(
            value = "Banner详情",
            notes = "Banner详情",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/get",method=RequestMethod.GET)
    @PreAuthorize("hasRole('banner')")
    public Result get(@RequestParam Long id) {
        logger.info("Banner详情开始,入参:id="+id.toString());
        SysBanner result = sysBannerService.get(id);
        logger.info("Banner详情结束,出参:result="+result.toString());
        return ResultUtil.success(result);
    }


    @ApiOperation(
            value = "Banner保存/更新",
            notes = "Banner保存/更新",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
    @PreAuthorize("hasRole('banner')")
    public Result saveOrUpdate(@RequestBody SysBanner sysBanner) {
        logger.info("Banner保存/更新开始,入参:sysBanner="+sysBanner.toString());

        if(sysBanner==null || sysBanner.getTitle()==null || sysBanner.getTitle().length()<1){
            return ResultUtil.validateError("请填写标题");
        }

        SysAdmin sysAdmin = CurrentUtil.getCurrent();
        Date date = new Date();
        sysBanner.setUpdateDate(date);
        sysBanner.setUpdateBy(sysAdmin.getId());
        if(sysBanner.getId() != null){
            sysBannerService.update(sysBanner);
        }else {
            sysBanner.setCreateBy(sysAdmin.getId());
            sysBanner.setCreateDate(date);
            sysBanner.setFlagDelete(false);
            sysBannerService.save(sysBanner);
        }
        logger.info("Banner保存/更新成功");
        return ResultUtil.success();
    }


    @ApiOperation(
            value = "批量删除",
            notes = "批量删除",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/deletes",method=RequestMethod.GET)
    @PreAuthorize("hasRole('banner')")
    public Result deletes(@RequestParam String ids) {
        logger.info("批量删除Banner开始,入参:ids="+ids);
        sysBannerService.deletes(ids);
        logger.info("批量删除Banner成功");
        return ResultUtil.success();
    }


    /**
     * 图片上传
     * @param urlFile
     * @return
     * @throws IllegalStateException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result save(@RequestParam MultipartFile urlFile) throws IllegalStateException {

        String returnPath = "";
        try {
            // 文件名使用当前时间
            String name = UUID.randomUUID().toString();

            // 获取上传图片的扩展名(jpg/png/...)
            String fileName = urlFile.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length());

            // 图片上传的相对路径（因为相对路径放到页面上就可以显示图片）
            returnPath = "/upload/banner/"+name + "." + extension;

            //绝对路径
            String path = upload + returnPath;

            File dir = new File(path);
            if(!dir.exists()) {
                dir.getParentFile().getParentFile().mkdir();
                dir.getParentFile().mkdir();
            }

            // 上传图片
            urlFile.transferTo(dir);

        } catch (Exception e) {
            throw new RuntimeException("服务器繁忙，上传图片失败");
        }
        return ResultUtil.success(returnPath);
    }


}
