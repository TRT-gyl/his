package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisPatientMapper;
import com.ruoyi.his.domain.HisPatient;
import com.ruoyi.his.service.IHisPatientService;

/**
 * 患者Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Service
public class HisPatientServiceImpl implements IHisPatientService 
{
    @Autowired
    private HisPatientMapper hisPatientMapper;

    /**
     * 查询患者
     *
     * @param patientId 患者主键
     * @return 患者
     */
    @Override
    public HisPatient selectHisPatientByPatientId(Long patientId)
    {
        return hisPatientMapper.selectHisPatientByPatientId(patientId);
    }

    /**
     * 查询患者列表
     *
     * @param hisPatient 患者
     * @return 患者
     */
    @Override
    public List<HisPatient> selectHisPatientList(HisPatient hisPatient)
    {
        return hisPatientMapper.selectHisPatientList(hisPatient);
    }

    /**
     * 新增患者
     *
     * @param hisPatient 患者
     * @return 结果
     */
    @Override
    public Integer insertHisPatient(HisPatient hisPatient)
    {
        hisPatient.setCreateTime(DateUtils.getNowDate());
        hisPatientMapper.insertHisPatient(hisPatient);
        return hisPatient.getPatientId().intValue();
    }

    /**
     * 修改患者
     *
     * @param hisPatient 患者
     * @return 结果
     */
    @Override
    public int updateHisPatient(HisPatient hisPatient)
    {
        hisPatient.setUpdateTime(DateUtils.getNowDate());
        return hisPatientMapper.updateHisPatient(hisPatient);
    }
    /**
     * 查询患者
     *
     * @param idCard 身份证
     * @return 患者
     */
    @Override
    public HisPatient selectHisPatientByIdCard(String idCard){
       return hisPatientMapper.selectHisPatientByIdCard(idCard);
    }
}
