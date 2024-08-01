package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.HisTreat;
import org.apache.ibatis.annotations.Select;

/**
 * 就诊Mapper接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface HisTreatMapper 
{
    /**
     * 查询就诊
     * 
     * @param treatNo 就诊编号
     * @return 就诊
     */
//    @Select("select * from his_treat where treat_no = #{treatNo}")
    public HisTreat selectHisTreatByTreatNo(String treatNo);

    /**
     * 查询就诊列表
     * 
     * @param hisTreat 就诊
     * @return 就诊集合
     */
    public List<HisTreat> selectHisTreatList(HisTreat hisTreat);

    /**
     * 新增就诊
     * 
     * @param hisTreat 就诊
     * @return 结果
     */
    public int insertHisTreat(HisTreat hisTreat);

    /**
     * 修改就诊
     * 
     * @param hisTreat 就诊
     * @return 结果
     */
    public int updateHisTreat(HisTreat hisTreat);

    /**
     * 删除就诊
     * 
     * @param treatId 就诊主键
     * @return 结果
     */
    public int deleteHisTreatByTreatId(Long treatId);

    /**
     * 批量删除就诊
     * 
     * @param treatIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisTreatByTreatIds(Long[] treatIds);

    /**
     * 查询就诊
     *
     * @param registrationId 挂号ID
     * @return 就诊
     */
    public HisTreat selectHisTreatByRegistrationId(Long registrationId);

}
