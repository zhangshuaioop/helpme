package com.help.helpme.controller.sys;

import com.help.helpme.entity.sys.SysAdmin;
import com.help.helpme.entity.sys.SysAdminRole;
import com.help.helpme.mapper.sys.SysAdminRoleMapper;
import com.help.helpme.service.sys.SysAdminService;
import com.help.helpme.utils.MD5Utils;
import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import com.help.helpme.utils.ValiDateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @description: 系统用户
 * @author: zhangshuai
 * @create: 2019-01-31 14:38
 */
@RestController
@RequestMapping("/sysAdmin")
@Api(description = "系统用户")
public class SysAdminController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private SysAdminRoleMapper sysAdminRoleMapper;

    @ApiOperation(
            value = "分页系统用户列表",
            notes = "分页系统用户列表",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/findPage",method=RequestMethod.POST)
    @PreAuthorize("hasRole('用户列表')")
    public Result findPage(@RequestBody SysAdmin sysAdmin) {
        logger.info("分页系统用户列表开始,入参:sysAdmin="+sysAdmin.toString());
        Result result = sysAdminService.findPage(sysAdmin);
        logger.info("分页系统用户列表结束,出参:result="+result.getData().toString());
        return result;
    }


    @ApiOperation(
            value = "获取系统用户当前权限及相关信息",
            notes = "获取系统用户当前权限及相关信息",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getAdminAuthority",method=RequestMethod.GET)
//    @PreAuthorize("hasRole('用户管理')")
    public Result getAdminAuthority() {
        logger.info("获取系统用户当前权限及相关信息开始,入参:无");
        Result result = sysAdminService.findAdminAuthority();
        logger.info("获取系统用户当前权限及相关信息结束,出参:result="+result.getData().toString());
        return result;
    }



    @ApiOperation(
            value = "保存/更新用户",
            notes = "保存/更新用户",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
    @PreAuthorize("hasRole('用户列表')")
    public Result saveOrUpdate(@RequestBody SysAdmin sysAdmin) {
        logger.info("保存/更新用户开始,入参:sysAdmin="+sysAdmin.toString());


        if(!ValiDateUtil.failUsername(sysAdmin.getUsername())){
            return ResultUtil.errorBusinessMsg("用户名以字母开头只能包括字母,下划线,数字,长度必须在6到10之间");
        }

        if(!ValiDateUtil.failPhone(sysAdmin.getMobile())){
            return ResultUtil.errorBusinessMsg("手机号格式错误");
        }

        Date date = new Date();
        sysAdmin.setUpdateDate(date);

        //根据账号或手机号用来校验是否存在
        SysAdmin newSysAdmin = new SysAdmin();
        newSysAdmin.setUsername(sysAdmin.getUsername());
        newSysAdmin.setMobile(sysAdmin.getMobile());
        List<SysAdmin> sysAdmins = sysAdminService.findUserNameOrMobile(newSysAdmin);

        if(sysAdmin.getId() != null){
            if(sysAdmins != null && sysAdmins.size() > 1){
                return ResultUtil.errorBusinessMsg("用户名或手机号已存在");
            }

            if(sysAdmin.getPassword() != null && sysAdmin.getPassword().length()>0){
                sysAdmin.setPassword(MD5Utils.MD5Encode(sysAdmin.getPassword(),"utf-8"));
            }
            sysAdminService.update(sysAdmin);  //更新用户

            sysAdminRoleMapper.deleteByAdminId(sysAdmin.getId()); //删除用户角色

        }else {

            if(sysAdmins != null && sysAdmins.size() > 0){
                return ResultUtil.errorBusinessMsg("用户名或手机号已存在");
            }

            sysAdmin.setCreateDate(date);
            sysAdmin.setFlagDelete(false);
            sysAdmin.setPassword(MD5Utils.MD5Encode("123456","utf-8"));
            sysAdminService.save(sysAdmin);

        }

        if(sysAdmin.getRoleIds() != null && sysAdmin.getRoleIds().size()>0) {
            //角色保存
            SysAdminRole sysAdminRole = new SysAdminRole();
            sysAdminRole.setAdminId(sysAdmin.getId());
            sysAdminRole.setRoleIds(sysAdmin.getRoleIds());
            sysAdminRoleMapper.saves(sysAdminRole);
        }

        logger.info("保存/更新用户成功");
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
    @PreAuthorize("hasRole('用户列表')")
    public Result deletes(@RequestParam String ids) {
        logger.info("批量删除用户开始,入参:ids="+ids);
        sysAdminService.deletes(ids);
        logger.info("批量删除用户成功");
        return ResultUtil.success();
    }


    @ApiOperation(
            value = "编辑查询",
            notes = "编辑查询",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/edit",method=RequestMethod.GET)
    @PreAuthorize("hasRole('用户列表')")
    public Result edit(@RequestParam Long id) {
        logger.info("编辑查询用户开始,入参:id="+id);
        Result result = sysAdminService.edit(id);
        logger.info("编辑查询用户成功,出参:result="+result.getData().toString());
        return result;
    }

    @ApiOperation(
            value = "查询单条",
            notes = "查询单条",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/get",method=RequestMethod.GET)
    @PreAuthorize("hasRole('用户列表')")
    public Result get(@RequestParam Long id) {
        logger.info("查询单条用户开始,入参:id="+id);
        SysAdmin result = sysAdminService.get(id);
        logger.info("查询单条用户成功,出参:result="+result.toString());
        return ResultUtil.success(result);
    }

}
