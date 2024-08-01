package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.HisRegCost;

/**
 * 挂号费用Mapper接口
 * 
 * @author apr_jay
 * @date 2022-08-30
 */
public interface HisRegCostMapper 
{
    /**
     * 查询挂号费用
     * 
     * @param regId 挂号费用主键
     * @return 挂号费用
     */
    public HisRegCost selectHisRegCostByRegId(Long regId);

    /**
     * 查询挂号费用列表
     * 
     * @param hisRegCost 挂号费用
     * @return 挂号费用集合
     */
    public List<HisRegCost> selectHisRegCostList(HisRegCost hisRegCost);

    /**
     * 新增挂号费用
     * 
     * @param hisRegCost 挂号费用
     * @return 结果
     */
    public int insertHisRegCost(HisRegCost hisRegCost);

    /**
     * 修改挂号费用
     * 
     * @param hisRegCost 挂号费用
     * @return 结果
     */
    public int updateHisRegCost(HisRegCost hisRegCost);

    /**
     * 删除挂号费用
     * 
     * @param regId 挂号费用主键
     * @return 结果
     */
    public int deleteHisRegCostByRegId(Long regId);

    /**
     * 批量删除挂号费用
     * 
     * @param regIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisRegCostByRegIds(Long[] regIds);
}
