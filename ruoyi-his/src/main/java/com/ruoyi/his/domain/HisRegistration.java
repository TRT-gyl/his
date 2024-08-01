package com.ruoyi.his.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 挂号对象 his_registration
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public class HisRegistration extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 挂号ID */
    private Long registrationId;

    /** 患者ID */
    @Excel(name = "患者ID")
    private Long registrationPatientId;

    /** 就诊日期和时段（排班ID） */
    @Excel(name = "就诊日期和时段", readConverterExp = "排=班ID")
    private Long registrationSchedule;

    /** 挂号编号 */
    @Excel(name = "挂号编号")
    private String registrationNo;

    /** 挂号类型ID */
    @Excel(name = "挂号类型ID")
    private Long registrationType;

    /** 状态（0待收费 1待就诊 2就诊完成 3已退号） */
    @Excel(name = "状态", readConverterExp = "0=待收费,1=待就诊,2=就诊完成,3=已退号")
    private String registrationStatus;

    public HisTreat getHisTreat() {
        return hisTreat;
    }

    public void setHisTreat(HisTreat hisTreat) {
        this.hisTreat = hisTreat;
    }

    private HisTreat hisTreat;
    public void setRegistrationId(Long registrationId) 
    {
        this.registrationId = registrationId;
    }

    public Long getRegistrationId() 
    {
        return registrationId;
    }
    public void setRegistrationPatientId(Long registrationPatientId) 
    {
        this.registrationPatientId = registrationPatientId;
    }

    public Long getRegistrationPatientId() 
    {
        return registrationPatientId;
    }
    public void setRegistrationSchedule(Long registrationSchedule) 
    {
        this.registrationSchedule = registrationSchedule;
    }

    public Long getRegistrationSchedule() 
    {
        return registrationSchedule;
    }
    public void setRegistrationNo(String registrationNo) 
    {
        this.registrationNo = registrationNo;
    }

    public String getRegistrationNo() 
    {
        return registrationNo;
    }
    public void setRegistrationType(Long registrationType) 
    {
        this.registrationType = registrationType;
    }

    public Long getRegistrationType() 
    {
        return registrationType;
    }
    public void setRegistrationStatus(String registrationStatus) 
    {
        this.registrationStatus = registrationStatus;
    }

    public String getRegistrationStatus() 
    {
        return registrationStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("registrationId", getRegistrationId())
            .append("registrationPatientId", getRegistrationPatientId())
            .append("registrationSchedule", getRegistrationSchedule())
            .append("registrationNo", getRegistrationNo())
            .append("registrationType", getRegistrationType())
            .append("registrationStatus", getRegistrationStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
