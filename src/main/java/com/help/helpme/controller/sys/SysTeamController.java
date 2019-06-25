package com.help.helpme.controller.sys;

import com.help.helpme.entity.sys.SysAdmin;
import com.help.helpme.entity.sys.SysTeam;
import com.help.helpme.service.sys.SysTeamService;
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
 * @description: 公司团队
 * @author: zhangshuai
 * @create: 2019-06-10 15:38
 */
@RestController
@RequestMapping("/sysTeam")
@Api(description = "公司团队")
public class SysTeamController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SysTeamService sysTeamService;

    @Value("${upload}")
    private String upload;

    @ApiOperation(
            value = "分页公司团队列表",
            notes = "分页公司团队列表",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/findPage",method=RequestMethod.POST)
    @PreAuthorize("hasRole('公司团队')")
    public Result findPage(@RequestBody SysTeam sysTeam) {
        logger.info("分页公司团队列表开始,入参:sysTeam="+sysTeam.toString());
        Result result = sysTeamService.findPage(sysTeam);
        logger.info("分页公司团队列表结束,出参:result="+result.getData().toString());
        return result;
    }


    @ApiOperation(
            value = "详情",
            notes = "详情",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/get",method=RequestMethod.GET)
    @PreAuthorize("hasRole('公司团队')")
    public Result get(@RequestParam Long id) {
        logger.info("公司团队详情开始,入参:id="+id.toString());
        SysTeam result = sysTeamService.get(id);
        logger.info("公司团队详情结束,出参:result="+result.toString());
        return ResultUtil.success(result);
    }


    @ApiOperation(
            value = "保存/更新",
            notes = "保存/更新",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
    @PreAuthorize("hasRole('公司团队')")
    public Result saveOrUpdate(@RequestBody SysTeam sysTeam) {
        logger.info("公司团队保存/更新开始,入参:sysTeam="+sysTeam.toString());
        SysAdmin sysAdmin = CurrentUtil.getCurrent();
        Date date = new Date();
        sysTeam.setUpdateDate(date);
        sysTeam.setUpdateBy(sysAdmin.getId());
        if(sysTeam.getId() != null){
            sysTeamService.update(sysTeam);
        }else {
            sysTeam.setCreateBy(sysAdmin.getId());
            sysTeam.setCreateDate(date);
            sysTeam.setFlagDelete(false);
            sysTeamService.save(sysTeam);
        }
        logger.info("公司团队保存/更新成功");
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
    @PreAuthorize("hasRole('公司团队')")
    public Result deletes(@RequestParam String ids) {
        logger.info("批量删除公司团队开始,入参:ids="+ids);
        sysTeamService.deletes(ids);
        logger.info("批量删除公司团队成功");
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
            returnPath = "/upload/team/"+name + "." + extension;

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
