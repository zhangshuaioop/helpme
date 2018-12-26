//package com.platform.springboot.service.syscompany;
//
//import com.github.pagehelper.PageHelper;
//import com.platform.springboot.base.BaseService;
//import com.platform.springboot.entity.syscompany.SysCompanyPermission;
//import com.platform.springboot.entity.syscompany.response.SysCompanyPermissionRes;
//import com.platform.springboot.mapper.syscompany.SysCompanyPermissionMapper;
//import com.platform.springboot.utils.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @description: 系统权限资源
// * @author: zhangshuai
// * @create: 2018-12-12 09:26
// */
//@Service
//public class SysCompanyPermissionService extends BaseService<SysCompanyPermissionMapper,SysCompanyPermission> {
//
//    @Autowired
//    private SysCompanyPermissionMapper sysCompanyPermissionMapper;
//
//    /**
//     * 查询公司个人权限
//     * @return
//     */
//    public List<SysCompanyPermissionRes> findPermission(SysCompanyPermission sysCompanyPermission){
//        return sysCompanyPermissionMapper.selectPermission(sysCompanyPermission);
//    }
//
//
//    /**
//     * 分页查询公司权限
//     * @param sysCompanyPermission
//     * @return
//     */
//    public Result findPage(SysCompanyPermission sysCompanyPermission){
//        sysCompanyPermission.setCompanyId(CurrentUtil.getCurrent().getCompanyId());
//        PageHelper.startPage(sysCompanyPermission.getPageNum(), sysCompanyPermission.getPageSize());
//        List<SysCompanyPermissionRes> persons = sysCompanyPermissionMapper.selectCompanyPermission(sysCompanyPermission);
//        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
//        PageInfo<SysCompanyPermissionRes> pageInfo = new PageInfo<>(persons);
//        return ResultUtil.success(pageInfo);
//    }
//
//    /**
//     * 公司权限列表(树)
//     * @param sysCompanyPermission
//     * @return
//     */
//    public Result findByRoleIdList(SysCompanyPermission sysCompanyPermission){
//        sysCompanyPermission.setCompanyId(CurrentUtil.getCurrent().getCompanyId());
//        List<SysCompanyPermissionRes> permissionTree = TreeUtils.formatTree(sysCompanyPermissionMapper.selectCompanyPermission(sysCompanyPermission),false);
//        return ResultUtil.success(permissionTree);
//    }
//
//}