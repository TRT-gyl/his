package com.ruoyi.his.service;


import com.ruoyi.his.domain.MedicineSaleRecords;

import java.util.List;

/**
 * VIEWService接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface IMedicineSaleRecordsService 
{
    /**
     * 查询VIEW
     * 
     * @param medicineId VIEW主键
     * @return VIEW
     */
    public MedicineSaleRecords selectMedicineSaleRecordsByMedicineId(Long medicineId);

    /**
     * 查询VIEW列表
     * 
     * @param medicineSaleRecords VIEW
     * @return VIEW集合
     */
    public List<MedicineSaleRecords> selectMedicineSaleRecordsList(MedicineSaleRecords medicineSaleRecords);

    /**
     * 新增VIEW
     * 
     * @param medicineSaleRecords VIEW
     * @return 结果
     */
    public int insertMedicineSaleRecords(MedicineSaleRecords medicineSaleRecords);

    /**
     * 修改VIEW
     * 
     * @param medicineSaleRecords VIEW
     * @return 结果
     */
    public int updateMedicineSaleRecords(MedicineSaleRecords medicineSaleRecords);

    /**
     * 批量删除VIEW
     * 
     * @param medicineIds 需要删除的VIEW主键集合
     * @return 结果
     */
    public int deleteMedicineSaleRecordsByMedicineIds(Long[] medicineIds);

    /**
     * 删除VIEW信息
     * 
     * @param medicineId VIEW主键
     * @return 结果
     */
    public int deleteMedicineSaleRecordsByMedicineId(Long medicineId);
    List<MedicineSaleRecords> selectMedicineStatisticRecords(MedicineSaleRecords medicineSaleRecords);
}
