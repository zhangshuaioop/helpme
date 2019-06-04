package com.help.helpme.mapper.sys;

import com.help.helpme.base.BaseMapper;
import com.help.helpme.entity.sys.SysAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * 根据用户名查询
     * @param sysAdmin
     * @return
     */
    SysAdmin selectByUserName(SysAdmin sysAdmin);
}