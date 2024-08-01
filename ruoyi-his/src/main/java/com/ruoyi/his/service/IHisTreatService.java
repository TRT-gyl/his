package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.HisTreat;

/**
 * 就诊Service接口
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public interface IHisTreatService 
{
    /**
     * 查询就诊
     * 
     * @param treatNo 就诊编号
     * @return 就诊
     */
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
    public String insertHisTreat(HisTreat hisTreat);

    /**
     * 修改就诊
     * 
     * @param hisTreat 就诊
     * @return 结果
     */
    public int updateHisTreat(HisTreat hisTreat);

    /**
     * 批量删除就诊
     * 
     * @param treatIds 需要删除的就诊主键集合
     * @return 结果
     */
    public int deleteHisTreatByTreatIds(Long[] treatIds);

    /**
     * 删除就诊信息
     * 
     * @param treatId 就诊主键
     * @return 结果
     */
    public int deleteHisTreatByTreatId(Long treatId);

    /**
     * 查询就诊详情
     *
     * @param registrationId 挂号ID
     * @return 结果
     */
    public HisTreat selectHisRegistrationId(Long registrationId);
}
