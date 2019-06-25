package com.help.helpme.service.sys;

import com.github.pagehelper.PageHelper;
import com.help.helpme.base.BaseService;
import com.help.helpme.entity.sys.SysCompany;
import com.help.helpme.mapper.sys.SysCompanyMapper;
import com.help.helpme.utils.PageInfo;
import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 业务公司
 * @author: zhangshuai
 * @create: 2019-06-10 15:01
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysCompanyService extends BaseService<SysCompanyMapper,SysCompany> {

    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    /**
     * 分页业务公司列表
     * @param sysCompany
     * @return
     */
    public Result findPage(SysCompany sysCompany){
        PageHelper.startPage(sysCompany.getPageNum(), sysCompany.getPageSize());
        List<SysCompany> persons = sysCompanyMapper.selectAll(sysCompany);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<SysCompany> pageInfo = new PageInfo<>(persons);
        return ResultUtil.success(pageInfo);
    }

}
