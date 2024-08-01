package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 医生对象 his_doctor
 *
 * @author apr_jay
 * @date 2022-08-31
 */
public class HisDoctor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 医生编号
     */
    private Long doctId;

    /**
     * 密码
     */
    private String doctPassword;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    @NotBlank(message = "姓名不能为空")
    @Size(min = 0, max = 10, message = "姓名长度不能超过10个字符")
    private String doctName;

    /**
     * 所属部门
     */
    private Long deptId;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    private String doctPhone;

    /**
     * 性别
     */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String doctSex;

    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private Long doctAge;

    /**
     * 医生职称
     */
    @Excel(name = "医生职称", readConverterExp = "0=医师,1=主治医师,2=副主任医师,3=主任医师")
    private String doctLevel;

    /**
     * 是否排班
     */

    private String isOrderwork;

    /**
     * 学历背景
     */
    private String doctEducationBg;
    /**
     * 科室
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Excel.Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "deptLeader", type = Excel.Type.EXPORT)
    })
    private HisDept dept;


    public void setDoctId(Long doctId) {
        this.doctId = doctId;
    }

    public Long getDoctId() {
        return doctId;
    }

    public void setDoctPassword(String doctPassword) {
        this.doctPassword = doctPassword;
    }

    public String getDoctPassword() {
        return doctPassword;
    }

    public void setDoctName(String doctName) {
        this.doctName = doctName;
    }

    public String getDoctName() {
        return doctName;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDoctPhone(String doctPhone) {
        this.doctPhone = doctPhone;
    }

    public String getDoctPhone() {
        return doctPhone;
    }

    public void setDoctSex(String doctSex) {
        this.doctSex = doctSex;
    }

    public String getDoctSex() {
        return doctSex;
    }

    public void setDoctAge(Long doctAge) {
        this.doctAge = doctAge;
    }

    public Long getDoctAge() {
        return doctAge;
    }

    public void setDoctLevel(String doctLevel) {
        this.doctLevel = doctLevel;
    }

    public String getDoctLevel() {
        return doctLevel;
    }

    public void setIsOrderwork(String isOrderwork) {
        this.isOrderwork = isOrderwork;
    }

    public String getIsOrderwork() {
        return isOrderwork;
    }

    public void setDoctEducationBg(String doctEducationBg) {
        this.doctEducationBg = doctEducationBg;
    }

    public String getDoctEducationBg() {
        return doctEducationBg;
    }

    public HisDept getDept() {
        return dept;
    }

    public void setDept(HisDept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("doctId", getDoctId())
                .append("doctPassword", getDoctPassword())
                .append("doctName", getDoctName())
                .append("deptId", getDeptId())
                .append("doctPhone", getDoctPhone())
                .append("doctSex", getDoctSex())
                .append("doctAge", getDoctAge())
                .append("doctLevel", getDoctLevel())
                .append("isOrderwork", getIsOrderwork())
                .append("doctEducationBg", getDoctEducationBg())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("dept", getDept())
                .toString();
    }
}
