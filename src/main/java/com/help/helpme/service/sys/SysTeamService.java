package com.help.helpme.service.sys;

import com.github.pagehelper.PageHelper;
import com.help.helpme.base.BaseService;
import com.help.helpme.entity.sys.SysTeam;
import com.help.helpme.mapper.sys.SysTeamMapper;
import com.help.helpme.utils.PageInfo;
import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 团队
 * @author: zhangshuai
 * @create: 2019-06-10 15:01
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysTeamService extends BaseService<SysTeamMapper,SysTeam> {

    @Autowired
    private SysTeamMapper sysTeamMapper;

    /**
     * 分页公司团队列表
     * @param sysTeam
     * @return
     */
    public Result findPage(SysTeam sysTeam){
        PageHelper.startPage(sysTeam.getPageNum(), sysTeam.getPageSize());
        List<SysTeam> persons = sysTeamMapper.selectAll(sysTeam);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<SysTeam> pageInfo = new PageInfo<>(persons);
        return ResultUtil.success(pageInfo);
    }

}
