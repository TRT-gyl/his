package com.ruoyi.his.mapper;

import com.ruoyi.his.domain.MedicinePurchaseRecords;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * VIEWMapper接口
 *
 * @author ruoyi
 * @date 2022-08-31
 */
public interface MedicinePurchaseRecordsMapper {
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
     * 删除VIEW
     *
     * @param medicineId VIEW主键
     * @return 结果
     */
    public int deleteMedicinePurchaseRecordsByMedicineId(Long medicineId);

    /**
     * 批量删除VIEW
     *
     * @param medicineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMedicinePurchaseRecordsByMedicineIds(Long[] medicineIds);

    public List<MedicinePurchaseRecords> selectMedicinePurchaseRecordsInPeriod(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
