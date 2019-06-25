package com.help.helpme.service.sys;

import com.github.pagehelper.PageHelper;
import com.help.helpme.base.BaseService;
import com.help.helpme.entity.sys.SysBanner;
import com.help.helpme.mapper.sys.SysBannerMapper;
import com.help.helpme.utils.PageInfo;
import com.help.helpme.utils.Result;
import com.help.helpme.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: Banner
 * @author: zhangshuai
 * @create: 2019-06-10 14:53
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysBannerService extends BaseService<SysBannerMapper,SysBanner> {

    @Autowired
    private SysBannerMapper sysBannerMapper;

    /**
     * 分页Banner列表
     * @param sysBanner
     * @return
     */
    public Result findPage(SysBanner sysBanner){
        PageHelper.startPage(sysBanner.getPageNum(), sysBanner.getPageSize());
        List<SysBanner> persons = sysBannerMapper.selectAll(sysBanner);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<SysBanner> pageInfo = new PageInfo<>(persons);
        return ResultUtil.success(pageInfo);
    }
}
