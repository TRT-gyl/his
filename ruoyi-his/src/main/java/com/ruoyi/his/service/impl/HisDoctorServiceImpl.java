package com.ruoyi.his.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.mapper.HisDoctorMapper;
import com.ruoyi.his.service.IHisDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 医生Service业务层处理
 *
 * @author apr_jay
 * @date 2022-08-31
 */
@Service
public class HisDoctorServiceImpl implements IHisDoctorService {
    @Autowired
    private HisDoctorMapper hisDoctorMapper;

    /**
     * 查询医生
     *
     * @param doctId 医生主键
     * @return 医生
     */
    @Override
    public HisDoctor selectHisDoctorByDoctId(Long doctId) {
        return hisDoctorMapper.selectHisDoctorByDoctId(doctId);
    }

    /**
     * 查询医生列表
     *
     * @param hisDoctor 医生
     * @return 医生
     */
    @Override
    public List<HisDoctor> selectHisDoctorList(HisDoctor hisDoctor) {
        return hisDoctorMapper.selectHisDoctorList(hisDoctor);
    }

    /**
     * 新增医生
     *
     * @param hisDoctor 医生
     * @return 结果
     */
    @Override
    public int insertHisDoctor(HisDoctor hisDoctor) {
        hisDoctor.setCreateTime(DateUtils.getNowDate());
        return hisDoctorMapper.insertHisDoctor(hisDoctor);
    }

    /**
     * 修改医生
     *
     * @param hisDoctor 医生
     * @return 结果
     */
    @Override
    public int updateHisDoctor(HisDoctor hisDoctor) {
        hisDoctor.setUpdateTime(DateUtils.getNowDate());
        return hisDoctorMapper.updateHisDoctor(hisDoctor);
    }

    /**
     * 批量删除医生
     *
     * @param doctIds 需要删除的医生主键
     * @return 结果
     */
    @Override
    public int deleteHisDoctorByDoctIds(Long[] doctIds) {
        return hisDoctorMapper.deleteHisDoctorByDoctIds(doctIds);
    }

    /**
     * 删除医生信息
     *
     * @param doctId 医生主键
     * @return 结果
     */
    @Override
    public int deleteHisDoctorByDoctId(Long doctId) {
        return hisDoctorMapper.deleteHisDoctorByDoctId(doctId);
    }

    @Override
    public String checkPhoneUnique(HisDoctor doctor) {
        HisDoctor info = hisDoctorMapper.checkPhoneUnique(doctor.getDoctPhone());
        if (StringUtils.isNotNull(info)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
