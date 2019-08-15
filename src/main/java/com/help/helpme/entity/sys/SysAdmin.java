package com.help.helpme.entity.sys;

import com.help.helpme.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@ApiModel(value = "系统用户")
public class SysAdmin extends BaseEntity {

    @ApiModelProperty(value = "id", example = "1", required = true)
    private Long id;

    @ApiModelProperty(value = "用户名", example = "1", required = true)
    private String username;

    @ApiModelProperty(value = "密码", example = "1", required = true)
    private String password;


    @ApiModelProperty(value = "昵称", example = "1", required = true)
//    @Pattern(regexp="^[-\\+]?[\\d]*$",message = "昵称请填写数字")
    @Pattern(regexp="[^\\u4e00-\\u9fa5]",message = "不包含中文")
    @Size(min=6, max=30,message = "昵称长度必须在6~30之间")
    private String nickName;

    @ApiModelProperty(value = "部门", example = "1", required = true)
    private String dept;

    @ApiModelProperty(value = "描述", example = "1", required = true)
    private String description;

    @ApiModelProperty(value = "是否启用0禁用1启用", example = "1", required = true)
    private Boolean flagEnabled;

    @ApiModelProperty(value = "头像", example = "1", required = true)
    private String headUrl;

    @ApiModelProperty(value = "邮箱", example = "1", required = true)
    private String email;

    @ApiModelProperty(value = "手机号", example = "1", required = true)
    private String mobile;

    @ApiModelProperty(value = "登陆时间", example = "1", required = true)
    private Date loginDate;

    @ApiModelProperty(value = "登陆ip", example = "1", required = true)
    private String loginIp;

    @ApiModelProperty(value = "是否删除0未删除1删除", example = "1", required = true)
    private Boolean flagDelete;

    @ApiModelProperty(value = "创建时间", example = "1", required = true)
    private Date createDate;

    @ApiModelProperty(value = "更新时间", example = "1", required = true)
    private Date updateDate;

    @ApiModelProperty(value = "全局搜索", example = "1", required = true)
    private String search;

    @ApiModelProperty(value = "角色ids", example = "1", required = true)
    private List<Long> roleIds;

    private Collection<? extends GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Boolean getFlagEnabled() {
        return flagEnabled;
    }

    public void setFlagEnabled(Boolean flagEnabled) {
        this.flagEnabled = flagEnabled;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Boolean getFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(Boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }


    @Override
    public String toString() {
        return "SysAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", dept='" + dept + '\'' +
                ", description='" + description + '\'' +
                ", flagEnabled=" + flagEnabled +
                ", headUrl='" + headUrl + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", loginDate=" + loginDate +
                ", loginIp='" + loginIp + '\'' +
                ", flagDelete=" + flagDelete +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", search='" + search + '\'' +
                ", roleIds=" + roleIds +
                ", authorities=" + authorities +
                '}';
    }
}