package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.HisRegistration;
import com.ruoyi.his.domain.vo.VoReg;
import com.ruoyi.his.mapper.HisRegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisTreatMapper;
import com.ruoyi.his.domain.HisTreat;
import com.ruoyi.his.service.IHisTreatService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 就诊Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Service
@Transactional
public class HisTreatServiceImpl implements IHisTreatService 
{
    @Autowired
    private HisTreatMapper hisTreatMapper;
    @Autowired
    private HisRegistrationMapper hisRegistrationMapper;

    /**
     * 查询就诊
     * 
     * @param treatNo 就诊编号
     * @return 就诊
     */
    @Override
    public HisTreat selectHisTreatByTreatNo(String treatNo)
    {
        return hisTreatMapper.selectHisTreatByTreatNo(treatNo);
    }

    /**
     * 查询就诊列表
     * 
     * @param hisTreat 就诊
     * @return 就诊
     */
    @Override
    public List<HisTreat> selectHisTreatList(HisTreat hisTreat)
    {
        return hisTreatMapper.selectHisTreatList(hisTreat);
    }

    /**
     * 新增就诊
     * 
     * @param hisTreat 就诊
     * @return 结果
     */
    @Override
    public String insertHisTreat(HisTreat hisTreat)
    {
        hisTreat.setTreatNo("T"+HisRegistrationServiceImpl.getNo());
        hisTreat.setCreateTime(DateUtils.getNowDate());
        hisTreatMapper.insertHisTreat(hisTreat);

        //保存病例后需要将就诊状态改为就诊中
        VoReg voReg = new VoReg();
        voReg.setRegistrationStatus("4");
        voReg.setRegistrationId(hisTreat.getTreatRegistrationId());
        hisRegistrationMapper.updateHisRegistration(voReg);
        //返回病例编号
        return hisTreat.getTreatNo();
    }

    /**
     * 修改就诊
     * 
     * @param hisTreat 就诊
     * @return 结果
     */
    @Override
    public int updateHisTreat(HisTreat hisTreat)
    {
        hisTreat.setUpdateTime(DateUtils.getNowDate());
        return hisTreatMapper.updateHisTreat(hisTreat);
    }

    /**
     * 批量删除就诊
     * 
     * @param treatIds 需要删除的就诊主键
     * @return 结果
     */
    @Override
    public int deleteHisTreatByTreatIds(Long[] treatIds)
    {
        return hisTreatMapper.deleteHisTreatByTreatIds(treatIds);
    }

    /**
     * 删除就诊信息
     * 
     * @param treatId 就诊主键
     * @return 结果
     */
    @Override
    public int deleteHisTreatByTreatId(Long treatId)
    {
        return hisTreatMapper.deleteHisTreatByTreatId(treatId);
    }


    @Override
    public HisTreat selectHisRegistrationId(Long registrationId){
        return hisTreatMapper.selectHisTreatByRegistrationId(registrationId);
    }
}
