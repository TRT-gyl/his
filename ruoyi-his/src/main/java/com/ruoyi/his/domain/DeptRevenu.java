package com.ruoyi.his.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 dept_revenu
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public class DeptRevenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private Long deptId;

    /** 科室名称 */
    @Excel(name = "科室名称")
    private String deptName;

    /** 挂号费 */
    @Excel(name = "挂号费")
    private BigDecimal regRevenu;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal medicineRevenu;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal examineRevenu;

    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setRegRevenu(BigDecimal regRevenu) 
    {
        this.regRevenu = regRevenu;
    }

    public BigDecimal getRegRevenu() 
    {
        return regRevenu;
    }
    public void setMedicineRevenu(BigDecimal medicineRevenu) 
    {
        this.medicineRevenu = medicineRevenu;
    }

    public BigDecimal getMedicineRevenu() 
    {
        return medicineRevenu;
    }
    public void setExamineRevenu(BigDecimal examineRevenu) 
    {
        this.examineRevenu = examineRevenu;
    }

    public BigDecimal getExamineRevenu() 
    {
        return examineRevenu;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("regRevenu", getRegRevenu())
            .append("medicineRevenu", getMedicineRevenu())
            .append("examineRevenu", getExamineRevenu())
            .append("createTime", getCreateTime())
            .toString();
    }
}
