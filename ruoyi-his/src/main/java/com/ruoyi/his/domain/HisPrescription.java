package com.ruoyi.his.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 处方药品对象 his_prescription
 * 
 * @author ruoyi
 * @date 2022-09-01
 */
public class HisPrescription extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 处方药品表 */
    private Long prescriptionId;

    /** 药品id */
    @Excel(name = "药品id")
    private Long medicineId;

    /** 药品数量 */
    @Excel(name = "药品数量")
    private Long quantity;

    /** 金额（单价*数量） */
    @Excel(name = "金额", readConverterExp = "单=价*数量")
    private BigDecimal amount;

    /** 就诊id */
    @Excel(name = "就诊id")
    private Long treatId;

    /** 服用备注 */
    @Excel(name = "服用备注")
    private String notes;

    /** 支付状态(已支付，未支付，已退费） */
    @Excel(name = "支付状态(已支付，未支付，已退费）")
    private String state;

    /** $column.columnComment */
    private String delFlag;

    public void setPrescriptionId(Long prescriptionId) 
    {
        this.prescriptionId = prescriptionId;
    }

    public Long getPrescriptionId() 
    {
        return prescriptionId;
    }
    public void setMedicineId(Long medicineId) 
    {
        this.medicineId = medicineId;
    }

    public Long getMedicineId() 
    {
        return medicineId;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setTreatId(Long treatId) 
    {
        this.treatId = treatId;
    }

    public Long getTreatId() 
    {
        return treatId;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
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
            .append("prescriptionId", getPrescriptionId())
            .append("medicineId", getMedicineId())
            .append("quantity", getQuantity())
            .append("amount", getAmount())
            .append("treatId", getTreatId())
            .append("notes", getNotes())
            .append("state", getState())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
