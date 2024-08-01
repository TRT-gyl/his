package com.ruoyi.his.service;

import java.util.List;

import com.ruoyi.his.domain.HisPatient;
import com.ruoyi.his.domain.HisRegistration;
import com.ruoyi.his.domain.HisTreat;
import com.ruoyi.his.domain.vo.VoReg;

/**
 * 挂号Service接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface IHisRegistrationService 
{
    /**
     * 通过挂号ID查订单编号
     * @param registrationId
     * @return
     */
    public HisTreat selectRegAndTreatNoByRegId(Long registrationId);
    /**
     * 查询挂号
     * 
     * @param registrationId 挂号主键
     * @return 挂号
     */
    public HisRegistration selectHisRegistrationByRegistrationId(Long registrationId);

    /**
     * 查询挂号列表
     * 
     * @param voReg 挂号
     * @return 挂号集合
     */
    public List<VoReg> selectHisRegistrationList(VoReg voReg);

    /**
     * 新增挂号
     * 
     * @param hisRegistration 挂号
     * @return 结果
     */
    public String insertHisRegistration(HisRegistration hisRegistration);

    /**
     * 修改挂号
     * 
     * @param voReg 挂号
     * @return 结果
     */
    public int updateHisRegistration(VoReg voReg);

    /**
     * 批量删除挂号
     * 
     * @param registrationIds 需要删除的挂号主键集合
     * @return 结果
     */
    public int deleteHisRegistrationByRegistrationIds(Long[] registrationIds);

    /**
     * 删除挂号信息
     * 
     * @param registrationId 挂号主键
     * @return 结果
     */
    public int deleteHisRegistrationByRegistrationId(Long registrationId);
}
