package com.ruoyi.his.service;

import java.util.List;

import com.ruoyi.his.domain.HisPatient;

/**
 * 患者Service接口
 *
 * @author ruoyi
 * @date 2022-08-30
 */
public interface IHisPatientService {
    /**
     * 查询患者
     *
     * @param patientId 患者主键
     * @return 患者
     */
    public HisPatient selectHisPatientByPatientId(Long patientId);

    /**
     * 查询患者列表
     *
     * @param hisPatient 患者
     * @return 患者集合
     */
    public List<HisPatient> selectHisPatientList(HisPatient hisPatient);

    /**
     * 新增患者
     *
     * @param hisPatient 患者
     * @return 新增患者的主键
     */
    public Integer insertHisPatient(HisPatient hisPatient);

    /**
     * 修改患者
     *
     * @param hisPatient 患者
     * @return 结果
     */
    public int updateHisPatient(HisPatient hisPatient);

    /**
     * 查询患者
     *
     * @param idCard 身份证
     * @return 患者
     */
    public HisPatient selectHisPatientByIdCard(String idCard);
}