package com.help.helpme.mapper.sys;

import com.help.helpme.base.BaseMapper;
import com.help.helpme.entity.sys.SysAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    /**
     * 根据用户名查询
     * @param sysAdmin
     * @return
     */
    SysAdmin selectByUserName(SysAdmin sysAdmin);

    /**
     * 根据用户名或手机号查询
     * @param sysAdmin
     * @return
     */
    List<SysAdmin> selectUserNameOrMobile(SysAdmin sysAdmin);
}