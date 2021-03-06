package com.help.helpme.utils;

import com.help.helpme.entity.sys.SysAdmin;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * @description: 系统用户信息
 * @author: zhangshuai
 * @create: 2018-12-12 21:58
 */
@Component
public class CurrentUtil {



    /**
     * 登陆用户信息
     * @return
     */
    public static SysAdmin getCurrent() {
        SysAdmin sysAdmin = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            sysAdmin = (SysAdmin) authentication.getCredentials();
        }
        return sysAdmin;
}

    /**
     * 权限
     * @return
     */
    public static Collection<? extends GrantedAuthority> getCurrentRole() {

        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            collection = (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
        }
        return collection;
    }
}
