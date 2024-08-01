package com.ruoyi.his.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.his.domain.HisPurchase;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class VoRepertory   {
    private String billsid;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeStart;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTimeEnd;
    private String medicineType;
    private String prescribe;
    private String medicineName;
    private String factoryName;
    private Integer pwId;

}
