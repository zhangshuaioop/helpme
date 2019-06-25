package com.help.helpme.controller.sys;

import com.help.helpme.entity.sys.SysRole;
import com.help.helpme.entity.sys.SysRoleAuthority;
import com.help.helpme.mapper.sys.SysRoleAuthorityMapper;
import com.help.helpme.service.sys.SysRoleService;
import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @description: 系统角色
 * @author: zhangshuai
 * @create: 2019-05-31 14:37
 */
@RestController
@RequestMapping("/sysRole")
@Api(description = "系统角色")
public class SysRoleController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleAuthorityMapper sysRoleAuthorityMapper;

    @ApiOperation(
            value = "分页系统角色列表",
            notes = "分页系统角色列表",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/findPage",method=RequestMethod.POST)
    @PreAuthorize("hasRole('角色列表')")
    public Result findPage(@RequestBody SysRole sysRole) {
        logger.info("分页系统角色列表开始,入参:sysRole="+sysRole.toString());
        Result result = sysRoleService.findPage(sysRole);
        logger.info("分页系统角色列表结束,出参:result="+result.getData().toString());
        return result;
    }


    @ApiOperation(
            value = "保存/更新系统角色",
            notes = "保存/更新系统角色",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/saveOrUpdate",method=RequestMethod.POST)
    @PreAuthorize("hasRole('角色列表')")
    public Result saveOrUpdate(@RequestBody SysRole sysRole) {
        logger.info("保存/更新系统角色开始,入参:sysRole="+sysRole.toString());

        if(sysRole==null || sysRole.getName()==null || sysRole.getName().length()<1){
            return ResultUtil.validateError("请填写名称");
        }

        Date date = new Date();
        sysRole.setUpdateDate(date);
        if(sysRole.getId() != null){
            sysRoleService.update(sysRole);  //更新角色

            sysRoleAuthorityMapper.deleteByRoleId(sysRole.getId()); //删除权限

        }else {
            sysRole.setCreateDate(date);
            sysRole.setFlagDelete(false);
            sysRoleService.save(sysRole);
        }
        if(sysRole.getAuthorityIds() != null && sysRole.getAuthorityIds().size()>0) {
            //权限保存
            SysRoleAuthority sysRoleAuthority = new SysRoleAuthority();
            sysRoleAuthority.setRoleId(sysRole.getId());
            sysRoleAuthority.setAuthorityArr(sysRole.getAuthorityIds());
            sysRoleAuthorityMapper.saves(sysRoleAuthority);
        }

        logger.info("保存/更新系统角色成功");
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
    @PreAuthorize("hasRole('角色列表')")
    public Result edit(@RequestParam Long id) {
        logger.info("编辑查询角色开始,入参:id="+id);
        Result result = sysRoleService.edit(id);
        logger.info("编辑查询角色成功,出参:result="+result.getData().toString());
        return result;
    }


    @ApiOperation(
            value = "查询系统角色列表",
            notes = "查询系统角色列表",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getList",method=RequestMethod.GET)
    @PreAuthorize("hasRole('角色列表')")
    public Result getList() {
        logger.info("查询系统角色列表开始,入参:无");
        SysRole sysRole = new SysRole();
        sysRole.setFlagEnabled(true);
        List<SysRole> result = sysRoleService.findAll(sysRole);
        logger.info("查询系统角色列表成功,出参:result="+result.toString());
        return ResultUtil.success(result);
    }



    @ApiOperation(
            value = "查询单条",
            notes = "查询单条",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/get",method=RequestMethod.GET)
    @PreAuthorize("hasRole('角色列表')")
    public Result get(@RequestParam Long id) {
        logger.info("查询单条系统角色开始,入参:id="+id);
        SysRole result = sysRoleService.get(id);
        logger.info("查询单条系统角色成功,出参:result="+result.toString());
        return ResultUtil.success(result);
    }


    @ApiOperation(
            value = "批量删除",
            notes = "批量删除",
            consumes = "application/json",
            responseReference = "com.help.helpme.utils.Result"
    )
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/deletes",method=RequestMethod.GET)
    @PreAuthorize("hasRole('角色列表')")
    public Result deletes(@RequestParam String ids) {
        logger.info("批量删除系统角色开始,入参:ids="+ids);
        sysRoleService.deletes(ids);
        logger.info("批量删除系统角色成功");
        return ResultUtil.success();
    }

}
