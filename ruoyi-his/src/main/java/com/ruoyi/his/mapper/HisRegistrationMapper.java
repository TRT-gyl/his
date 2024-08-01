package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.HisRegistration;
import com.ruoyi.his.domain.vo.VoReg;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * 挂号Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface HisRegistrationMapper 
{
    /**
     * 查询挂号
     * 
     * @param registrationId 挂号主键
     * @return 挂号
     */
    @Select("SELECT t.treat_no FROM his_registration r,his_treat t WHERE r.registration_id=t.treat_registration_id and r.registration_id=#{registrationId}")
    @Results({
            @Result(column = "treat_no", property = "hisTreat.treatNo"),
    })
    public String selectRegAndTreatNoByRegId(Long registrationId);

    public HisRegistration selectHisRegistrationByRegistrationId(Long registrationId);

    /**
     * 通过医生Id查挂号列表
     * @param docterId
     * @return
     */

    public HisRegistration selectRegListPatientByDocterId(Long docterId);

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
    public int insertHisRegistration(HisRegistration hisRegistration);

    /**
     * 修改挂号
     * 
     * @param voReg 挂号
     * @return 结果
     */
    public int updateHisRegistration(VoReg voReg);

    /**
     * 删除挂号
     * 
     * @param registrationId 挂号主键
     * @return 结果
     */
    public int deleteHisRegistrationByRegistrationId(Long registrationId);

    /**
     * 批量删除挂号
     * 
     * @param registrationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisRegistrationByRegistrationIds(Long[] registrationIds);
}
