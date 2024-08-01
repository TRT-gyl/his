package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;

/**
 * 科室管理对象 his_dept
 *
 * @author apr_jlr
 * @date 2022-08-31
 */
public class HisDept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 科室ID
     */
    private Long deptId;

    /**
     * 科室名称
     */
    @Excel(name = "科室名称")
    @NotBlank(message = "科室名称不能为空")
    private String deptName;

    /**
     * 科室编码
     */
    @Excel(name = "科室编码")
    private String deptCode;

    /**
     * 科室挂号量
     */
    @Excel(name = "科室挂号量")
    private Long deptNum;

    /**
     * 科室负责人
     */
    @Excel(name = "科室负责人")
    private String deptLeader;

    /**
     * 科室电话
     */
    @Excel(name = "科室电话")
    private String deptPhone;

    /**
     * 状态
     */
    @Excel(name = "状态")
    private String status;

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptNum(Long deptNum) {
        this.deptNum = deptNum;
    }

    public Long getDeptNum() {
        return deptNum;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptPhone(String deptPhone) {
        this.deptPhone = deptPhone;
    }

    public String getDeptPhone() {
        return deptPhone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("deptName", getDeptName())
                .append("deptCode", getDeptCode())
                .append("deptNum", getDeptNum())
                .append("deptLeader", getDeptLeader())
                .append("deptPhone", getDeptPhone())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
