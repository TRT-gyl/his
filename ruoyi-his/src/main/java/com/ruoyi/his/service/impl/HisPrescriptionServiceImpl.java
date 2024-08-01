package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.HisPrescription;
import com.ruoyi.his.mapper.HisPrescriptionMapper;
import com.ruoyi.his.service.IHisPrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 处方药品Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-02
 */
@Service
public class HisPrescriptionServiceImpl implements IHisPrescriptionService
{
    @Autowired
    private HisPrescriptionMapper hisPrescriptionMapper;

    /**
     * 查询处方药品
     * 
     * @param prescriptionId 处方药品主键
     * @return 处方药品
     */
    @Override
    public HisPrescription selectHisPrescriptionByPrescriptionId(Long prescriptionId)
    {
        return hisPrescriptionMapper.selectHisPrescriptionByPrescriptionId(prescriptionId);
    }

    /**
     * 查询处方药品列表
     * 
     * @param hisPrescription 处方药品
     * @return 处方药品
     */
    @Override
    public List<HisPrescription> selectHisPrescriptionList(HisPrescription hisPrescription)
    {
        return hisPrescriptionMapper.selectHisPrescriptionList(hisPrescription);
    }

    /**
     * 新增处方药品
     * 
     * @param hisPrescription 处方药品
     * @return 结果
     */
    @Override
    public int insertHisPrescription(HisPrescription hisPrescription)
    {
        hisPrescription.setCreateTime(DateUtils.getNowDate());
        return hisPrescriptionMapper.insertHisPrescription(hisPrescription);
    }

    /**
     * 修改处方药品
     * 
     * @param hisPrescription 处方药品
     * @return 结果
     */
    @Override
    public int updateHisPrescription(HisPrescription hisPrescription)
    {
        hisPrescription.setUpdateTime(DateUtils.getNowDate());
        return hisPrescriptionMapper.updateHisPrescription(hisPrescription);
    }

    /**
     * 批量删除处方药品
     * 
     * @param prescriptionIds 需要删除的处方药品主键
     * @return 结果
     */
    @Override
    public int deleteHisPrescriptionByPrescriptionIds(Long[] prescriptionIds)
    {
        return hisPrescriptionMapper.deleteHisPrescriptionByPrescriptionIds(prescriptionIds);
    }

    /**
     * 删除处方药品信息
     * 
     * @param prescriptionId 处方药品主键
     * @return 结果
     */
    @Override
    public int deleteHisPrescriptionByPrescriptionId(Long prescriptionId)
    {
        return hisPrescriptionMapper.deleteHisPrescriptionByPrescriptionId(prescriptionId);
    }
}
