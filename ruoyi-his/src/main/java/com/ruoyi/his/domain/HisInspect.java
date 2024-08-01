package com.ruoyi.his.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 检查费用设置对象 his_inspect
 * 
 * @author ruoyi
 * @date 2022-09-02
 */
public class HisInspect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目费用序号 */
    @Excel(name = "项目费用序号")
    private Long inspectId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String inspectName;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keyword;

    /** 项目单价 */
    @Excel(name = "项目单价")
    private BigDecimal inspectPrice;

    /** 项目成本 */
    @Excel(name = "项目成本")
    private BigDecimal inspectCost;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 类别 */
    @Excel(name = "类别")
    private String category;

    /** 状态（0是正常，1是不正常） */
    @Excel(name = "状态", readConverterExp = "0=是正常，1是不正常")
    private String status;

    public void setInspectId(Long inspectId) 
    {
        this.inspectId = inspectId;
    }

    public Long getInspectId() 
    {
        return inspectId;
    }
    public void setInspectName(String inspectName) 
    {
        this.inspectName = inspectName;
    }

    public String getInspectName() 
    {
        return inspectName;
    }
    public void setKeyword(String keyword) 
    {
        this.keyword = keyword;
    }

    public String getKeyword() 
    {
        return keyword;
    }
    public void setInspectPrice(BigDecimal inspectPrice) 
    {
        this.inspectPrice = inspectPrice;
    }

    public BigDecimal getInspectPrice() 
    {
        return inspectPrice;
    }
    public void setInspectCost(BigDecimal inspectCost) 
    {
        this.inspectCost = inspectCost;
    }

    public BigDecimal getInspectCost() 
    {
        return inspectCost;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
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
            .append("inspectId", getInspectId())
            .append("inspectName", getInspectName())
            .append("keyword", getKeyword())
            .append("inspectPrice", getInspectPrice())
            .append("inspectCost", getInspectCost())
            .append("unit", getUnit())
            .append("category", getCategory())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
