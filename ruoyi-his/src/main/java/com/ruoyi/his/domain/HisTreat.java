package com.ruoyi.his.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 就诊对象 his_treat
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public class HisTreat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 病例ID */
    private Long treatId;

    /** 挂号ID */
    @Excel(name = "挂号ID")
    private Long treatRegistrationId;

    /** 病例编号 */
    @Excel(name = "病例编号")
    private String treatNo;

    /** 发病日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发病日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date treatDate;

    /** 是否传染（0否 1是） */
    @Excel(name = "是否传染", readConverterExp = "0=否,1=是")
    private String treatContagion;

    /** 主诉 */
    @Excel(name = "主诉")
    private String treatComplaint;

    /** 诊断信息 */
    @Excel(name = "诊断信息")
    private String treatInfo;

    /** 医生建议 */
    @Excel(name = "医生建议")
    private String treatAdvice;

    /** 删除标志（0正常 1删除） */
    private String delFlag;

    public void setTreatId(Long treatId) 
    {
        this.treatId = treatId;
    }

    public Long getTreatId() 
    {
        return treatId;
    }
    public void setTreatRegistrationId(Long treatRegistrationId) 
    {
        this.treatRegistrationId = treatRegistrationId;
    }

    public Long getTreatRegistrationId() 
    {
        return treatRegistrationId;
    }
    public void setTreatNo(String treatNo) 
    {
        this.treatNo = treatNo;
    }

    public String getTreatNo() 
    {
        return treatNo;
    }
    public void setTreatDate(Date treatDate) 
    {
        this.treatDate = treatDate;
    }

    public Date getTreatDate() 
    {
        return treatDate;
    }
    public void setTreatContagion(String treatContagion) 
    {
        this.treatContagion = treatContagion;
    }

    public String getTreatContagion() 
    {
        return treatContagion;
    }
    public void setTreatComplaint(String treatComplaint) 
    {
        this.treatComplaint = treatComplaint;
    }

    public String getTreatComplaint() 
    {
        return treatComplaint;
    }
    public void setTreatInfo(String treatInfo) 
    {
        this.treatInfo = treatInfo;
    }

    public String getTreatInfo() 
    {
        return treatInfo;
    }
    public void setTreatAdvice(String treatAdvice) 
    {
        this.treatAdvice = treatAdvice;
    }

    public String getTreatAdvice() 
    {
        return treatAdvice;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("treatId", getTreatId())
            .append("treatRegistrationId", getTreatRegistrationId())
            .append("treatNo", getTreatNo())
            .append("treatDate", getTreatDate())
            .append("treatContagion", getTreatContagion())
            .append("treatComplaint", getTreatComplaint())
            .append("treatInfo", getTreatInfo())
            .append("treatAdvice", getTreatAdvice())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
