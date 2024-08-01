package com.ruoyi.his.mapper;

import java.util.List;

import com.ruoyi.his.domain.MedicineSaleRecords;

/**
 * VIEWMapper接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface MedicineSaleRecordsMapper 
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
     * 删除VIEW
     * 
     * @param medicineId VIEW主键
     * @return 结果
     */
    public int deleteMedicineSaleRecordsByMedicineId(Long medicineId);

    /**
     * 批量删除VIEW
     * 
     * @param medicineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMedicineSaleRecordsByMedicineIds(Long[] medicineIds);

    List<MedicineSaleRecords> selectMedicineStatisticRecords(MedicineSaleRecords medicineSaleRecords);
}
