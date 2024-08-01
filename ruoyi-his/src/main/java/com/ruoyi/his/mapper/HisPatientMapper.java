package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.HisPatient;

/**
 * 患者Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface HisPatientMapper

{
    /**
     * 查询患者
     * 
     * @param patientId 患者主键
     * @return 患者
     */
    public HisPatient selectHisPatientByPatientId(Long patientId);
    /**
     * 查询患者
     *
     * @param idCard 患者主键
     * @return 患者
     */
    public HisPatient selectHisPatientByIdCard(String idCard);
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
     * @return 结果
     */
    public int insertHisPatient(HisPatient hisPatient);

    /**
     * 修改患者
     * 
     * @param hisPatient 患者
     * @return 结果
     */
    public int updateHisPatient(HisPatient hisPatient);

    /**
     * 删除患者
     * 
     * @param patientId 患者主键
     * @return 结果
     */
    public int deleteHisPatientByPatientId(Long patientId);

    /**
     * 批量删除患者
     * 
     * @param patientIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisPatientByPatientIds(Long[] patientIds);
}
