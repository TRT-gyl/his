package com.ruoyi.his.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 examine_records
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public class ExamineRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 检查项ID */
    @Excel(name = "检查项ID")
    private Long itemId;

    /** 体检项目名称 */
    @Excel(name = "体检项目名称")
    private String itemName;

    /** 金额（元） */
    @Excel(name = "金额", readConverterExp = "元=")
    private BigDecimal totalPrice;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 支付状态（0未支付 1支付） */
    @Excel(name = "支付状态", readConverterExp = "0=未支付,1=支付")
    private String status;
    private Integer totalTimes;

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }

    public void setItemId(Long itemId)
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setItemName(String itemName) 
    {
        this.itemName = itemName;
    }

    public String getItemName() 
    {
        return itemName;
    }
    public void setTotalPrice(BigDecimal totalPrice) 
    {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() 
    {
        return totalPrice;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId", getItemId())
            .append("itemName", getItemName())
            .append("totalPrice", getTotalPrice())
            .append("patientName", getPatientName())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
