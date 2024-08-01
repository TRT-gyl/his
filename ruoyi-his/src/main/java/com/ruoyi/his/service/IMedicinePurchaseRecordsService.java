package com.ruoyi.his.service;

import java.util.List;

import com.ruoyi.his.domain.MedicinePurchaseRecords;


/**
 * VIEWService接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface IMedicinePurchaseRecordsService 
{
    /**
     * 查询VIEW
     * 
     * @param medicineId VIEW主键
     * @return VIEW
     */
    public MedicinePurchaseRecords selectMedicinePurchaseRecordsByMedicineId(Long medicineId);

    /**
     * 查询VIEW列表
     * 
     * @param medicinePurchaseRecords VIEW
     * @return VIEW集合
     */
    public List<MedicinePurchaseRecords> selectMedicinePurchaseRecordsList(MedicinePurchaseRecords medicinePurchaseRecords);

    /**
     * 新增VIEW
     * 
     * @param medicinePurchaseRecords VIEW
     * @return 结果
     */
    public int insertMedicinePurchaseRecords(MedicinePurchaseRecords medicinePurchaseRecords);

    /**
     * 修改VIEW
     * 
     * @param medicinePurchaseRecords VIEW
     * @return 结果
     */
    public int updateMedicinePurchaseRecords(MedicinePurchaseRecords medicinePurchaseRecords);

    /**
     * 批量删除VIEW
     * 
     * @param medicineIds 需要删除的VIEW主键集合
     * @return 结果
     */
    public int deleteMedicinePurchaseRecordsByMedicineIds(Long[] medicineIds);

    /**
     * 删除VIEW信息
     * 
     * @param medicineId VIEW主键
     * @return 结果
     */
    public int deleteMedicinePurchaseRecordsByMedicineId(Long medicineId);
}
