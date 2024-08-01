package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.MedicinePurchaseRecords;
import com.ruoyi.his.mapper.MedicinePurchaseRecordsMapper;
import com.ruoyi.his.service.IMedicinePurchaseRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * VIEWService业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@Service
public class MedicinePurchaseRecordsServiceImpl implements IMedicinePurchaseRecordsService
{
    @Autowired
    private MedicinePurchaseRecordsMapper medicinePurchaseRecordsMapper;

    /**
     * 查询VIEW
     * 
     * @param medicineId VIEW主键
     * @return VIEW
     */
    @Override
    public MedicinePurchaseRecords selectMedicinePurchaseRecordsByMedicineId(Long medicineId)
    {
        return medicinePurchaseRecordsMapper.selectMedicinePurchaseRecordsByMedicineId(medicineId);
    }

    /**
     * 查询VIEW列表
     * 
     * @param medicinePurchaseRecords VIEW
     * @return VIEW
     */
    @Override
    public List<MedicinePurchaseRecords> selectMedicinePurchaseRecordsList(MedicinePurchaseRecords medicinePurchaseRecords)
    {
        return medicinePurchaseRecordsMapper.selectMedicinePurchaseRecordsList(medicinePurchaseRecords);
    }

    /**
     * 新增VIEW
     * 
     * @param medicinePurchaseRecords VIEW
     * @return 结果
     */
    @Override
    public int insertMedicinePurchaseRecords(MedicinePurchaseRecords medicinePurchaseRecords)
    {
        medicinePurchaseRecords.setCreateTime(DateUtils.getNowDate());
        return medicinePurchaseRecordsMapper.insertMedicinePurchaseRecords(medicinePurchaseRecords);
    }

    /**
     * 修改VIEW
     * 
     * @param medicinePurchaseRecords VIEW
     * @return 结果
     */
    @Override
    public int updateMedicinePurchaseRecords(MedicinePurchaseRecords medicinePurchaseRecords)
    {
        return medicinePurchaseRecordsMapper.updateMedicinePurchaseRecords(medicinePurchaseRecords);
    }

    /**
     * 批量删除VIEW
     * 
     * @param medicineIds 需要删除的VIEW主键
     * @return 结果
     */
    @Override
    public int deleteMedicinePurchaseRecordsByMedicineIds(Long[] medicineIds)
    {
        return medicinePurchaseRecordsMapper.deleteMedicinePurchaseRecordsByMedicineIds(medicineIds);
    }

    /**
     * 删除VIEW信息
     * 
     * @param medicineId VIEW主键
     * @return 结果
     */
    @Override
    public int deleteMedicinePurchaseRecordsByMedicineId(Long medicineId)
    {
        return medicinePurchaseRecordsMapper.deleteMedicinePurchaseRecordsByMedicineId(medicineId);
    }
}
