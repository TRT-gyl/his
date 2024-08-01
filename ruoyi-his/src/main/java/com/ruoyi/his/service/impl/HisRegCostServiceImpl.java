package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisRegCostMapper;
import com.ruoyi.his.domain.HisRegCost;
import com.ruoyi.his.service.IHisRegCostService;

/**
 * 挂号费用Service业务层处理
 * 
 * @author apr_jay
 * @date 2022-08-30
 */
@Service
public class HisRegCostServiceImpl implements IHisRegCostService 
{
    @Autowired
    private HisRegCostMapper hisRegCostMapper;

    /**
     * 查询挂号费用
     * 
     * @param regId 挂号费用主键
     * @return 挂号费用
     */
    @Override
    public HisRegCost selectHisRegCostByRegId(Long regId)
    {
        return hisRegCostMapper.selectHisRegCostByRegId(regId);
    }

    /**
     * 查询挂号费用列表
     * 
     * @param hisRegCost 挂号费用
     * @return 挂号费用
     */
    @Override
    public List<HisRegCost> selectHisRegCostList(HisRegCost hisRegCost)
    {
        return hisRegCostMapper.selectHisRegCostList(hisRegCost);
    }

    /**
     * 新增挂号费用
     * 
     * @param hisRegCost 挂号费用
     * @return 结果
     */
    @Override
    public int insertHisRegCost(HisRegCost hisRegCost)
    {
        hisRegCost.setCreateTime(DateUtils.getNowDate());
        return hisRegCostMapper.insertHisRegCost(hisRegCost);
    }

    /**
     * 修改挂号费用
     * 
     * @param hisRegCost 挂号费用
     * @return 结果
     */
    @Override
    public int updateHisRegCost(HisRegCost hisRegCost)
    {
        hisRegCost.setUpdateTime(DateUtils.getNowDate());
        return hisRegCostMapper.updateHisRegCost(hisRegCost);
    }

    /**
     * 批量删除挂号费用
     * 
     * @param regIds 需要删除的挂号费用主键
     * @return 结果
     */
    @Override
    public int deleteHisRegCostByRegIds(Long[] regIds)
    {
        return hisRegCostMapper.deleteHisRegCostByRegIds(regIds);
    }

    /**
     * 删除挂号费用信息
     * 
     * @param regId 挂号费用主键
     * @return 结果
     */
    @Override
    public int deleteHisRegCostByRegId(Long regId)
    {
        return hisRegCostMapper.deleteHisRegCostByRegId(regId);
    }
}
