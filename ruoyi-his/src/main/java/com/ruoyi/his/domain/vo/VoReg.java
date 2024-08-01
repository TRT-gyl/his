package com.ruoyi.his.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WuShaoYan
 * @date 2022/9/6
 */
public class VoReg extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 排班日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date scheduleDate;

    /** 排班时段（0上午 1下午 2晚上） */
    @Excel(name = "排班时段", readConverterExp = "0=上午,1=下午,2=晚上")
    private Long scheduleTime;

    /** 排班类型 */
    @Excel(name = "排班类型")
    private String scheduleType;

    /** 姓名 */
    @Excel(name = "姓名")
    private String doctName;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    /** 挂号费 */
    @Excel(name = "挂号费")
    private BigDecimal regPrice;

    /** 挂号ID */
    @Excel(name = "挂号ID")
    private Long registrationId;

    /** 状态（0待收费 1待就诊 2就诊完成 3已退号） */
    @Excel(name = "状态", readConverterExp = "0=待收费,1=待就诊,2=就诊完成,3=已退号")
    private String registrationStatus;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 患者ID */
    @Excel(name = "患者ID")
    private Long patientId;

    /** 医生ID */
    @Excel(name = "医生ID")
    private Long scheduleDocterId;

    public void setScheduleDate(Date scheduleDate)
    {
        this.scheduleDate = scheduleDate;
    }

    public Date getScheduleDate()
    {
        return scheduleDate;
    }
    public void setScheduleTime(Long scheduleTime)
    {
        this.scheduleTime = scheduleTime;
    }

    public Long getScheduleTime()
    {
        return scheduleTime;
    }
    public void setScheduleType(String scheduleType)
    {
        this.scheduleType = scheduleType;
    }

    public String getScheduleType()
    {
        return scheduleType;
    }
    public void setDoctName(String doctName)
    {
        this.doctName = doctName;
    }

    public String getDoctName()
    {
        return doctName;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getDeptName()
    {
        return deptName;
    }
    public void setRegPrice(BigDecimal regPrice)
    {
        this.regPrice = regPrice;
    }

    public BigDecimal getRegPrice()
    {
        return regPrice;
    }
    public void setRegistrationId(Long registrationId)
    {
        this.registrationId = registrationId;
    }

    public Long getRegistrationId()
    {
        return registrationId;
    }
    public void setRegistrationStatus(String registrationStatus)
    {
        this.registrationStatus = registrationStatus;
    }

    public String getRegistrationStatus()
    {
        return registrationStatus;
    }
    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public String getPatientName()
    {
        return patientName;
    }
    public void setPatientId(Long patientId)
    {
        this.patientId = patientId;
    }

    public Long getPatientId()
    {
        return patientId;
    }
    public void setScheduleDocterId(Long scheduleDocterId)
    {
        this.scheduleDocterId = scheduleDocterId;
    }

    public Long getScheduleDocterId()
    {
        return scheduleDocterId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("scheduleDate", getScheduleDate())
                .append("scheduleTime", getScheduleTime())
                .append("scheduleType", getScheduleType())
                .append("doctName", getDoctName())
                .append("deptName", getDeptName())
                .append("regPrice", getRegPrice())
                .append("registrationId", getRegistrationId())
                .append("registrationStatus", getRegistrationStatus())
                .append("patientName", getPatientName())
                .append("patientId", getPatientId())
                .append("scheduleDocterId", getScheduleDocterId())
                .toString();
    }
}
