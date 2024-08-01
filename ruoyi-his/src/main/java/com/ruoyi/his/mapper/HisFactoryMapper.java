package com.ruoyi.his.mapper;


import com.ruoyi.his.domain.HisFactory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HisFactoryMapper {


    /**
     * 查询厂家管理
     *
     * @param hisFactory 厂家管理
     * @return 厂家管理集合
     */
    public List<HisFactory> selectHisFactoryList(HisFactory hisFactory);

    public HisFactory selectHisFactoryById(@Param("factoryId")Long factoryId);

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
     * 删除厂家管理
     *
     * @param factoryId 厂家管理主键
     * @return 结果
     */
    public int deleteHisFactoryByFactoryId(Long factoryId);

    /**
     * 批量删除厂家管理
     *
     * @param factoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisFactoryByFactoryIds(Long[] factoryIds);

    /**
     * 检查厂家的名称是否重复
     *
     *
     * @return 结果
     */

    public HisFactory checkFactoryNameUnique(@Param("factoryName") String factoryName);

    /**
     * 检查厂家的编码是否重复
     *
     *
     * @return 结果
     */

    public HisFactory checkFactoryNoUnique(@Param("factoryNo") String factoryNo);

    /**
     * 检查厂家的查询关键字是否重复
     *
     *
     * @return 结果
     */

    public HisFactory checkKeywordUnique(@Param("keyword") String keyword);
}
