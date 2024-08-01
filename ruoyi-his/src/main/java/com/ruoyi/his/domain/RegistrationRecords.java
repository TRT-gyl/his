package com.ruoyi.his.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 registration_records
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public class RegistrationRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 挂号ID */
    @Excel(name = "挂号ID")
    private Long registrationId;

    /** 医生编号 */
    @Excel(name = "医生编号")
    private Long doctId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String doctName;

    /** 挂号费 */
    @Excel(name = "挂号费")
    private BigDecimal regPrice;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 排班日期 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "排班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduleDate;
    private Integer totalTimes;

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }

    public void setRegistrationId(Long registrationId)
    {
        this.registrationId = registrationId;
    }

    public Long getRegistrationId() 
    {
        return registrationId;
    }
    public void setDoctId(Long doctId) 
    {
        this.doctId = doctId;
    }

    public Long getDoctId() 
    {
        return doctId;
    }
    public void setDoctName(String doctName) 
    {
        this.doctName = doctName;
    }

    public String getDoctName() 
    {
        return doctName;
    }
    public void setRegPrice(BigDecimal regPrice) 
    {
        this.regPrice = regPrice;
    }

    public BigDecimal getRegPrice() 
    {
        return regPrice;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setScheduleDate(Date scheduleDate) 
    {
        this.scheduleDate = scheduleDate;
    }

    public Date getScheduleDate() 
    {
        return scheduleDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("registrationId", getRegistrationId())
            .append("doctId", getDoctId())
            .append("doctName", getDoctName())
            .append("regPrice", getRegPrice())
            .append("patientName", getPatientName())
            .append("scheduleDate", getScheduleDate())
            .toString();
    }
}
