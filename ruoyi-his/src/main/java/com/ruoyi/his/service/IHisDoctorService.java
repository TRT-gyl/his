package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisDoctor;

import java.util.List;

/**
 * 医生Service接口
 *
 * @author apr_jay
 * @date 2022-08-31
 */
public interface IHisDoctorService {
    /**
     * 查询医生
     *
     * @param doctId 医生主键
     * @return 医生
     */
    public HisDoctor selectHisDoctorByDoctId(Long doctId);

    /**
     * 查询医生列表
     *
     * @param hisDoctor 医生
     * @return 医生集合
     */
    public List<HisDoctor> selectHisDoctorList(HisDoctor hisDoctor);

    /**
     * 新增医生
     *
     * @param hisDoctor 医生
     * @return 结果
     */
    public int insertHisDoctor(HisDoctor hisDoctor);

    /**
     * 修改医生
     *
     * @param hisDoctor 医生
     * @return 结果
     */
    public int updateHisDoctor(HisDoctor hisDoctor);

    /**
     * 批量删除医生
     *
     * @param doctIds 需要删除的医生主键集合
     * @return 结果
     */
    public int deleteHisDoctorByDoctIds(Long[] doctIds);

    /**
     * 删除医生信息
     *
     * @param doctId 医生主键
     * @return 结果
     */
    public int deleteHisDoctorByDoctId(Long doctId);

    /**
     * 校验手机号码是否唯一
     *
     * @param doctor 医生信息
     * @return 结果
     */
    public String checkPhoneUnique(HisDoctor doctor);
}
