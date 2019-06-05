package com.help.helpme.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 参数检验工具
 * @author: zhangshuai
 * @create: 2018-11-26 13:32
 */
public class ValiDateUtil {

    //校验手机号
    public static boolean failPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }


    //校验邮箱
    public static boolean failEmail(String email) {
        Pattern p = Pattern.compile("/^(\\w)+(.\\w+)*@(\\w)+((.\\w+)+)$/");
        Matcher m = p.matcher(email);
        return m.matches();
    }


    /**
     * 2.   论坛注册用户名验证
     必须以字母开头
     只能包括 字母 , 下划线 , 数字
     长度必须在6 到 10 之间

     */
    public static boolean failUsername(String username)
    {
        //String regex="[a-zA-Z][0-9a-zA-Z_]{5,9}";
        //String regex="[a-zA-Z][\\da-zA-Z_]{5,9}";// \d  要转成 \\d
        String regex="[a-zA-Z]\\w{5,9}";
        return username.matches(regex);
    }


    /**
     * 身份证验证
     身份证号码必须为15位或18位数字
     */
    public static boolean failIdentity(String identity)
    {
        String regex="\\d{15}|\\d{18}|\\d{17}X";
        return identity.matches(regex);
    }


}
