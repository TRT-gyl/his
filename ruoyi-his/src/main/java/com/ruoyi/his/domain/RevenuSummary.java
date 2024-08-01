package com.ruoyi.his.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RevenuSummary {
    private static final BigDecimal ZERO = new BigDecimal(0);
    private BigDecimal netGains;
    private BigDecimal totalGains;
    private BigDecimal totalSpend;
    private GainsAndCost gainsComposition;
    private List<DeptRevenu> deptRevenus;
    private List<MedicinePurchaseRecords> medicinePurchaseRecords;
    public RevenuSummary(List<DeptRevenu> deptRevenuList, List<MedicinePurchaseRecords> medicinePurchaseRecords){
        this.deptRevenus = deptRevenuList;
        this.medicinePurchaseRecords = medicinePurchaseRecords;
        gainsComposition = new GainsAndCost(this.deptRevenus, this.medicinePurchaseRecords);
        totalGains = this.gainsComposition.getRegistrationGains().add(this.gainsComposition.getExamineGains())
                        .add(this.gainsComposition.getMedicineSaleGains());
        totalSpend = this.gainsComposition.getMedicineCost();
        netGains = totalGains.subtract(totalSpend);
    }
    @Data
    private class GainsAndCost {
        private BigDecimal registrationGains = new BigDecimal(0);
        private BigDecimal medicineSaleGains = new BigDecimal(0);
        private BigDecimal examineGains = new BigDecimal(0);
        private BigDecimal medicineCost = new BigDecimal(0);
        GainsAndCost(List<DeptRevenu> deptRevenuList, List<MedicinePurchaseRecords> medicinePurchaseRecords){

            for(DeptRevenu deptRevenu: deptRevenuList){
                registrationGains = getRegistrationGains().add(deptRevenu.getRegRevenu()!=null? deptRevenu.getRegRevenu():ZERO);
                medicineSaleGains = getMedicineSaleGains().add(deptRevenu.getMedicineRevenu()!=null? deptRevenu.getMedicineRevenu():ZERO);
                examineGains = getExamineGains().add(deptRevenu.getExamineRevenu()!=null? deptRevenu.getExamineRevenu():ZERO);
            }
            for(MedicinePurchaseRecords records: medicinePurchaseRecords){
                medicineCost = getMedicineCost().add(records.getTotalMoney()!=null? records.getTotalMoney():ZERO);
            }
        }
    }


}
