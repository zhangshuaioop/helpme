package com.help.helpme.service.sys;

import com.github.pagehelper.PageHelper;
import com.help.helpme.base.BaseService;
import com.help.helpme.entity.sys.SysAdmin;
import com.help.helpme.entity.sys.SysRole;
import com.help.helpme.entity.sys.response.SysAuthorityRes;
import com.help.helpme.mapper.sys.SysAdminMapper;
import com.help.helpme.mapper.sys.SysAuthorityMapper;
import com.help.helpme.mapper.sys.SysRoleMapper;
import com.help.helpme.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 系统用户
 * @author: zhangshuai
 * @create: 2019-01-31 13:20
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysAdminService extends BaseService<SysAdminMapper,SysAdmin> {

    @Autowired
    private SysAdminMapper sysAdminMapper;

    @Autowired
    private SysAuthorityMapper sysAuthorityMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据用户名查询
     * @param sysAdmin
     * @return
     */
    public SysAdmin findByUserName(SysAdmin sysAdmin){
        return sysAdminMapper.selectByUserName(sysAdmin);
    }


    /**
     * 分页用户列表
     * @param sysAdmin
     * @return
     */
    public Result findPage(SysAdmin sysAdmin){
        PageHelper.startPage(sysAdmin.getPageNum(), sysAdmin.getPageSize());
        List<SysAdmin> persons = sysAdminMapper.selectAll(sysAdmin);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<SysAdmin> pageInfo = new PageInfo<>(persons);
        return ResultUtil.success(pageInfo);
    }

    /**
     * 获取系统用户当前权限及相关信息
     * @return
     */
    public Result findAdminAuthority(){
        SysAdmin sysAdmin = CurrentUtil.getCurrent();
        List<SysAuthorityRes> sysAuthoritiesTree = TreeUtils.formatTree(sysAuthorityMapper.selectByAdminId(sysAdmin.getId()),false);
        Map<String,Object> map = new HashMap<>();
        map.put("sysAdmin",sysAdmin);
        map.put("sysAuthoritiesTree",sysAuthoritiesTree);
        return ResultUtil.success(map);
    }


    /**
     * 编辑查询
     * @param id
     * @return
     */
    public Result edit(Long id){
        SysAdmin sysAdmin = sysAdminMapper.selectByPrimaryKey(id);
        SysRole sysRole = new SysRole();
        sysRole.setFlagEnabled(true);
        sysRole.setAdminId(id);
        List<SysRole> sysRoles = sysRoleMapper.selectByAdminId(sysRole);
        Map<String,Object> map = new HashMap<>();
        map.put("sysAdmin",sysAdmin);
        map.put("sysRoles",sysRoles);
        return ResultUtil.success(map);
    }

}
