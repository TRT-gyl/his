package com.ruoyi.his.mapper;

import com.ruoyi.his.domain.HisDoctor;

import java.util.List;

/**
 * 医生Mapper接口
 *
 * @author apr_jay
 * @date 2022-08-31
 */
public interface HisDoctorMapper {
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
     * 删除医生
     *
     * @param doctId 医生主键
     * @return 结果
     */
    public int deleteHisDoctorByDoctId(Long doctId);

    /**
     * 批量删除医生
     *
     * @param doctIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisDoctorByDoctIds(Long[] doctIds);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public HisDoctor checkPhoneUnique(String phonenumber);
}
