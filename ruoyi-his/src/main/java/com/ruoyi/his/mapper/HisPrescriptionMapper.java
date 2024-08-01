package com.ruoyi.his.mapper;

import java.util.List;

import com.ruoyi.his.domain.HisPrescription;


/**
 * 处方药品Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-02
 */
public interface HisPrescriptionMapper 
{
    /**
     * 查询处方药品
     * 
     * @param prescriptionId 处方药品主键
     * @return 处方药品
     */
    public HisPrescription selectHisPrescriptionByPrescriptionId(Long prescriptionId);

    /**
     * 查询处方药品列表
     * 
     * @param hisPrescription 处方药品
     * @return 处方药品集合
     */
    public List<HisPrescription> selectHisPrescriptionList(HisPrescription hisPrescription);

    /**
     * 新增处方药品
     * 
     * @param hisPrescription 处方药品
     * @return 结果
     */
    public int insertHisPrescription(HisPrescription hisPrescription);

    /**
     * 修改处方药品
     * 
     * @param hisPrescription 处方药品
     * @return 结果
     */
    public int updateHisPrescription(HisPrescription hisPrescription);

    /**
     * 删除处方药品
     * 
     * @param prescriptionId 处方药品主键
     * @return 结果
     */
    public int deleteHisPrescriptionByPrescriptionId(Long prescriptionId);

    /**
     * 批量删除处方药品
     * 
     * @param prescriptionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisPrescriptionByPrescriptionIds(Long[] prescriptionIds);
}