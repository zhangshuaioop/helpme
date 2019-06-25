package com.help.helpme.entity.sys;

import com.help.helpme.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "团队")
public class SysTeam extends BaseEntity {

    @ApiModelProperty(value = "id", example = "1", required = true)
    private Long id;

    @ApiModelProperty(value = "名称", example = "1", required = true)
    private String name;

    @ApiModelProperty(value = "手机号", example = "1", required = true)
    private String mobile;

    @ApiModelProperty(value = "描述", example = "1", required = true)
    private String description;

    @ApiModelProperty(value = "图片地址", example = "1", required = true)
    private String url;

    @ApiModelProperty(value = "是否启用0禁用1启用", example = "1", required = true)
    private Boolean flagEnabled;

    @ApiModelProperty(value = "是否删除0未删除1删除", example = "1", required = true)
    private Boolean flagDelete;

    @ApiModelProperty(value = "创建人", example = "1", required = true)
    private Long createBy;

    @ApiModelProperty(value = "更新人", example = "1", required = true)
    private Long updateBy;

    @ApiModelProperty(value = "创建时间", example = "1", required = true)
    private Date createDate;

    @ApiModelProperty(value = "更新时间", example = "1", required = true)
    private Date updateDate;

    @ApiModelProperty(value = "全局搜索", example = "1", required = true)
    private String search;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Boolean getFlagEnabled() {
        return flagEnabled;
    }

    public void setFlagEnabled(Boolean flagEnabled) {
        this.flagEnabled = flagEnabled;
    }

    public Boolean getFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(Boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "SysTeam{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", flagEnabled=" + flagEnabled +
                ", flagDelete=" + flagDelete +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", search='" + search + '\'' +
                '}';
    }
}