package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.MedicineSaleRecords;
import com.ruoyi.his.mapper.MedicineSaleRecordsMapper;
import com.ruoyi.his.service.IMedicineSaleRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VIEWService业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@Service
public class MedicineSaleRecordsServiceImpl implements IMedicineSaleRecordsService
{
    @Autowired
    private MedicineSaleRecordsMapper medicineSaleRecordsMapper;

    /**
     * 查询VIEW
     * 
     * @param medicineId VIEW主键
     * @return VIEW
     */
    @Override
    public MedicineSaleRecords selectMedicineSaleRecordsByMedicineId(Long medicineId)
    {
        return medicineSaleRecordsMapper.selectMedicineSaleRecordsByMedicineId(medicineId);
    }

    /**
     * 查询VIEW列表
     * 
     * @param medicineSaleRecords VIEW
     * @return VIEW
     */
    @Override
    public List<MedicineSaleRecords> selectMedicineSaleRecordsList(MedicineSaleRecords medicineSaleRecords)
    {
        return medicineSaleRecordsMapper.selectMedicineSaleRecordsList(medicineSaleRecords);
    }

    /**
     * 新增VIEW
     * 
     * @param medicineSaleRecords VIEW
     * @return 结果
     */
    @Override
    public int insertMedicineSaleRecords(MedicineSaleRecords medicineSaleRecords)
    {
        medicineSaleRecords.setCreateTime(DateUtils.getNowDate());
        return medicineSaleRecordsMapper.insertMedicineSaleRecords(medicineSaleRecords);
    }

    /**
     * 修改VIEW
     * 
     * @param medicineSaleRecords VIEW
     * @return 结果
     */
    @Override
    public int updateMedicineSaleRecords(MedicineSaleRecords medicineSaleRecords)
    {
        return medicineSaleRecordsMapper.updateMedicineSaleRecords(medicineSaleRecords);
    }

    /**
     * 批量删除VIEW
     * 
     * @param medicineIds 需要删除的VIEW主键
     * @return 结果
     */
    @Override
    public int deleteMedicineSaleRecordsByMedicineIds(Long[] medicineIds)
    {
        return medicineSaleRecordsMapper.deleteMedicineSaleRecordsByMedicineIds(medicineIds);
    }

    /**
     * 删除VIEW信息
     * 
     * @param medicineId VIEW主键
     * @return 结果
     */
    @Override
    public int deleteMedicineSaleRecordsByMedicineId(Long medicineId)
    {
        return medicineSaleRecordsMapper.deleteMedicineSaleRecordsByMedicineId(medicineId);
    }

    @Override
    public List<MedicineSaleRecords> selectMedicineStatisticRecords(MedicineSaleRecords medicineSaleRecords) {
        return medicineSaleRecordsMapper.selectMedicineStatisticRecords(medicineSaleRecords);
    }
}
