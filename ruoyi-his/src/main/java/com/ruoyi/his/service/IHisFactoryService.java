package com.ruoyi.his.service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisFactory;
import com.ruoyi.his.domain.HisFactory;

import java.util.List;

public interface IHisFactoryService {
    

    /**
     * 查询厂家管理列表
     *
     * @param hisFactory 厂家
     * @return 厂家管理集合
     */
    public List<HisFactory> selectHisFactoryList(HisFactory hisFactory);

    /**
     * 根据id查询厂家(用于回显)
     *
     *
     */
    public HisFactory selectHisFactoryById(Long factoryId);

    /**
     * 新增厂家管理
     *
     * @param hisFactory 厂家管理
     * @return 结果
     */
    public int insertHisFactory(HisFactory hisFactory);

    /**
     * 修改厂家管理
     *
     * @param hisFactory 厂家管理
     * @return 结果
     */
    public int updateHisFactory(HisFactory hisFactory);

    /**
     * 批量删除厂家管理
     *
     * @param factoryIds 需要删除的厂家管理主键集合
     * @return 结果
     */
    public int deleteHisFactoryByFactoryIds(Long[] factoryIds);

    /**
     * 删除厂家管理信息
     *
     * @param factoryId 厂家管理主键
     * @return 结果
     */
    public int deleteHisFactoryByFactoryId(Long factoryId);

    /**
     * 检查厂家名称是否重复
     */

    public String checkFactoryNameUnique(HisFactory hisFactory);

    /**
     * 检查厂家编号是否重复
     */

    public String checkFactoryNoUnique(HisFactory hisFactory);

    /**
     * 检查厂家查询关键字是否重复
     */

    public String checkKeywordUnique(HisFactory hisFactory);

}
