package com.ruoyi.his.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 采购入库对象 his_purchas_warehousing
 *
 * @author zhangbing
 * @date 2022-08-31
 */
public class HisPurchasWarehousing extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 单据ID */
    @Excel(name = "单据ID")
    private String billsid;

    /** 供应商表外键 */
    @Excel(name = "供应商表外键")
    private Long suppliersId;

    private HisSupplier hisSupplier;
    private String sName;
    private List<HisPurchase> purchases;
    public void setPurchases(List<HisPurchase> purchases){
        this.purchases=purchases;
    }
    public List<HisPurchase> getPurchases(){
        return purchases;
    }
    public void setsName(String sName){
        this.sName=sName;
    }
    public String getsName(){
        return sName;
    }

    /** 采购批发总金额 */
    @Excel(name = "采购批发总金额")
    private BigDecimal totalMoney;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 申请人 */
    @Excel(name = "申请人")
    private String proposer;

    /** 入库操作人 */
    @Excel(name = "入库操作人")
    private String operator;

    /** 入库时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date storageTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBillsid(String billsid)
    {
        this.billsid = billsid;
    }

    public String getBillsid()
    {
        return billsid;
    }
    public void setSuppliersId(Long suppliersId)
    {
        this.suppliersId = suppliersId;
    }

    public Long getSuppliersId()
    {
        return suppliersId;
    }
    public void setTotalMoney(BigDecimal totalMoney)
    {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalMoney()
    {
        return totalMoney;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setProposer(String proposer)
    {
        this.proposer = proposer;
    }

    public String getProposer()
    {
        return proposer;
    }
    public void setOperator(String operator)
    {
        this.operator = operator;
    }

    public String getOperator()
    {
        return operator;
    }
    public void setStorageTime(Date storageTime)
    {
        this.storageTime = storageTime;
    }

    public Date getStorageTime()
    {
        return storageTime;
    }

    public void setHisSupplier(HisSupplier hisSupplier){
        this.hisSupplier=hisSupplier;
    }
    public HisSupplier getHisSupplier(){
        return hisSupplier;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("billsid", getBillsid())
                .append("suppliersId", getSuppliersId())
                .append("totalMoney", getTotalMoney())
                .append("status", getStatus())
                .append("proposer", getProposer())
                .append("operator", getOperator())
                .append("storageTime", getStorageTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("hisSupplier",getHisSupplier())
                .toString();
    }
}
