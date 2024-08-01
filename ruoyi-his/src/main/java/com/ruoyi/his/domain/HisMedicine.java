package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class HisMedicine extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Long medicineId;

    @Excel(name = "药品名称")
    @NotBlank(message = "药品名称不能为空")
    @Size(min = 0,max=20,message = "药品名称不能超过20字")
    private String medicineName;


    @Excel(name = "药品编号")
    @NotBlank(message = "编号不能为空")
    @Size(min=0,max = 7,message = "药品编号不能超过7位")
    private String medicineNo;

    @Excel(name = "药品类型")
    @NotBlank(message = "药品类型不能为空")
    private String medicineType;

    @Excel(name = "处方类型")
    @NotBlank(message = "处方类型不能为空")
    private String prescribe;

    @Excel(name = "单位")
    @NotBlank(message = "单位不能为空")
    private String unit;

    @Excel(name = "处方价格")
    @Min(1)

    private Integer price;

    @Excel(name = "库存量")
    @Min(1)
    private Integer stock;

    @Excel(name = "预警值")
    @Min(1)
    private Integer warn;

    @Excel(name = "换算量")
    @Min(1)
    private Integer conversion;

    @Excel(name = "关键字")
    @NotBlank(message = "关键字不能为空")
    private String keyword;



    @Excel(name="关联厂家id")
    private Long factoryId;

    private String status;

    private HisFactory factory;//关联的厂家

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineNo() {
        return medicineNo;
    }

    public void setMedicineNo(String medicineNo) {
        this.medicineNo = medicineNo;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getPrescribe() {
        return prescribe;
    }

    public void setPrescribe(String prescribe) {
        this.prescribe = prescribe;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getWarn() {
        return warn;
    }

    public void setWarn(Integer warn) {
        this.warn = warn;
    }

    public Integer getConversion() {
        return conversion;
    }

    public void setConversion(Integer conversion) {
        this.conversion = conversion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HisFactory getFactory() {
        return factory;
    }

    public void setFactory(HisFactory factory) {
        this.factory = factory;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "HisMedicine{" +
                "medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", medicineNo='" + medicineNo + '\'' +
                ", medicineType='" + medicineType + '\'' +
                ", prescribe='" + prescribe + '\'' +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", warn=" + warn +
                ", conversion=" + conversion +
                ", keyword='" + keyword + '\'' +
                ", factoryId=" + factoryId +
                ", status='" + status + '\'' +
                ", factory=" + factory +
                '}';
    }
}
