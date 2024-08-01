package com.ruoyi.his.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 medicine_purchase_records
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public class MedicinePurchaseRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long medicineId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String medicineName;

    /** 批发总金额 */
    @Excel(name = "批发总金额")
    private BigDecimal totalMoney;

    public void setMedicineId(Long medicineId) 
    {
        this.medicineId = medicineId;
    }

    public Long getMedicineId() 
    {
        return medicineId;
    }
    public void setMedicineName(String medicineName) 
    {
        this.medicineName = medicineName;
    }

    public String getMedicineName() 
    {
        return medicineName;
    }
    public void setTotalMoney(BigDecimal totalMoney) 
    {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getTotalMoney() 
    {
        return totalMoney;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("medicineId", getMedicineId())
            .append("medicineName", getMedicineName())
            .append("totalMoney", getTotalMoney())
            .append("createTime", getCreateTime())
            .toString();
    }
}
