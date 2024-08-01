package com.ruoyi.his.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 药品采购对象 his_purchase
 *
 * @author zhangbing
 * @date 2022-08-31
 */
public class HisPurchase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 药品表外键 */
    @Excel(name = "药品表外键")
    private Long drugId;

    /** 采购入库表外键 */
    @Excel(name = "采购入库表外键")
    private Long pwId;

    /** 数量 */
    @Excel(name = "数量")
    private Long amount;

    /** 批发单价 */
    @Excel(name = "批发单价")
    private BigDecimal wholesale;

    /** 批发总金额 */
    @Excel(name = "批发总金额")
    private BigDecimal totalMoney;

    /** 批次号 */
    @Excel(name = "批次号")
    private String batch;

    private HisMedicine hisMedicine;
    public void setHisMedicine(HisMedicine hisMedicine){
        this.hisMedicine=hisMedicine;
    }

    public HisMedicine getHisMedicine(){
        return hisMedicine;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDrugId(Long drugId)
    {
        this.drugId = drugId;
    }

    public Long getDrugId()
    {
        return drugId;
    }
    public void setPwId(Long pwId)
    {
        this.pwId = pwId;
    }

    public Long getPwId()
    {
        return pwId;
    }
    public void setAmount(Long amount)
    {
        this.amount = amount;
    }

    public Long getAmount()
    {
        return amount;
    }
    public void setWholesale(BigDecimal wholesale)
    {
        this.wholesale = wholesale;
    }

    public BigDecimal getWholesale()
    {
        return wholesale;
    }
    public void setTotalMoney(BigDecimal totalMoney)
    {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalMoney()
    {
        return totalMoney;
    }
    public void setBatch(String batch)
    {
        this.batch = batch;
    }

    public String getBatch()
    {
        return batch;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("drugId", getDrugId())
                .append("pwId", getPwId())
                .append("amount", getAmount())
                .append("wholesale", getWholesale())
                .append("totalMoney", getTotalMoney())
                .append("batch", getBatch())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
