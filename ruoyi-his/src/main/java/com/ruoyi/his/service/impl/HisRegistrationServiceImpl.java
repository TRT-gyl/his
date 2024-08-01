package com.ruoyi.his.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.HisPatient;
import com.ruoyi.his.domain.HisSchedule;
import com.ruoyi.his.domain.HisTreat;
import com.ruoyi.his.domain.vo.VoReg;
import com.ruoyi.his.exception.NoRegistrationNumException;
import com.ruoyi.his.mapper.HisPatientMapper;
import com.ruoyi.his.mapper.HisScheduleMapper;
import com.ruoyi.his.service.IHisTreatService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisRegistrationMapper;
import com.ruoyi.his.domain.HisRegistration;
import com.ruoyi.his.service.IHisRegistrationService;
import org.springframework.transaction.annotation.Transactional;

import static com.ruoyi.his.service.impl.HisScheduleServiceImpl.*;

/**
 * 挂号Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@Service
@Transactional
public class HisRegistrationServiceImpl implements IHisRegistrationService
{
    @Autowired
    private HisRegistrationMapper hisRegistrationMapper;
    @Autowired
    private HisScheduleMapper hisScheduleMapper;

    @Autowired
    private IHisTreatService iHisTreatService;

    @Override
    public HisTreat selectRegAndTreatNoByRegId(Long registrationId){
        String s = hisRegistrationMapper.selectRegAndTreatNoByRegId(registrationId);
        return iHisTreatService.selectHisTreatByTreatNo(s);
    }
    /**
     * 查询挂号
     * 
     * @param registrationId 挂号主键
     * @return 挂号
     */
    @Override
    public HisRegistration selectHisRegistrationByRegistrationId(Long registrationId)
    {
        return hisRegistrationMapper.selectHisRegistrationByRegistrationId(registrationId);
    }

    /**
     * 查询挂号列表
     * 
     * @param voReg 挂号
     * @return 挂号
     */
    @Override
    public List<VoReg> selectHisRegistrationList(VoReg voReg)
    {
        return hisRegistrationMapper.selectHisRegistrationList(voReg);
    }

    /**
     * 新增挂号
     * 
     * @param hisRegistration 挂号
     * @return 返回新增成功的主键
     */
    @Override
    public String insertHisRegistration(HisRegistration hisRegistration)
    {
        hisRegistration.setRegistrationNo("HIS"+getNo());//设置挂号编号
        hisRegistration.setRegistrationStatus("0");//设置初始状态：未缴费
        hisRegistration.setCreateTime(DateUtils.getNowDate());//挂号时间
        HisSchedule hisSchedule = new HisSchedule();
        hisSchedule.setScheduleId(hisRegistration.getRegistrationSchedule());//设置ID
        List<HisSchedule> all = hisScheduleMapper.getAll(hisSchedule);
        Integer i = Integer.parseInt(all.get(0).getScheduleNums());
        if (i==0){
            throw new NoRegistrationNumException();
        }
        all.get(0).setScheduleNums(((Integer)(i - 1)).toString());//挂号就减去当前号数
        hisScheduleMapper.update(all.get(0));//修改挂号数
        hisRegistrationMapper.insertHisRegistration(hisRegistration);//增加
        return hisRegistration.getRegistrationNo();
    }

    /**
     * 修改挂号
     * 
     * @param voReg 挂号
     * @return 结果
     */
    @Override
    public int updateHisRegistration(VoReg voReg)
    {
        if (voReg.getRegistrationStatus().equals("0")){
            voReg.setRegistrationStatus("1");
        }else if (voReg.getRegistrationStatus().equals("1")){
            voReg.setRegistrationStatus("3");
        }
        return hisRegistrationMapper.updateHisRegistration(voReg);
    }

    /**
     * 批量删除挂号
     * 
     * @param registrationIds 需要删除的挂号主键
     * @return 结果
     */
    @Override
    public int deleteHisRegistrationByRegistrationIds(Long[] registrationIds)
    {
        return hisRegistrationMapper.deleteHisRegistrationByRegistrationIds(registrationIds);
    }

    /**
     * 删除挂号信息
     * 
     * @param registrationId 挂号主键
     * @return 结果
     */
    @Override
    public int deleteHisRegistrationByRegistrationId(Long registrationId)
    {
        return hisRegistrationMapper.deleteHisRegistrationByRegistrationId(registrationId);
    }

    /**
     * 拿到当前时间+随机数组成的编号
     *
     * @return
     */
    public static String getNo() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        int s = (int) ((Math.random() + 1) * 1000);
        return formatter.format(now) + s;
    }
}
