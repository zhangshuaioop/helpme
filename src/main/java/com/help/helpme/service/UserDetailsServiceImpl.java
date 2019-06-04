package com.help.helpme.service;

import com.help.helpme.entity.JwtUser;
import com.help.helpme.entity.sys.SysAdmin;
import com.help.helpme.entity.sys.SysAuthority;
import com.help.helpme.entity.sys.response.SysAuthorityRes;
import com.help.helpme.service.sys.SysAdminService;
import com.help.helpme.service.sys.SysAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zhangshuai on 2019/1/31
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysAdminService sysAdminService;

    @Autowired
    private SysAuthorityService sysAuthorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysAdmin newSysAdmin = new SysAdmin();
        newSysAdmin.setUsername(username);
        newSysAdmin.setFlagEnabled(true);
        SysAdmin sysAdmin = sysAdminService.findByUserName(newSysAdmin);

        if(sysAdmin == null){
            throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();

        //查询平台个人权限
        List<SysAuthorityRes> sysAuthorities = sysAuthorityService.findByAdminId(sysAdmin.getId());

        for (SysAuthorityRes sysAuthoritie: sysAuthorities) {
            collection.add(new SimpleGrantedAuthority("ROLE_"+sysAuthoritie.getName()));
        }
        sysAdmin.setAuthorities(collection);
        return new JwtUser(sysAdmin);
    }

}
