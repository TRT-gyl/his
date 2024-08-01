package com.ruoyi.his.mapper;

import com.ruoyi.his.domain.HisInspect;

import java.util.List;

public interface HisInspectMapper {
    /**
     * 查询检查费用信息数据集合
     *
     * @param inspect 检查费用信息
     * @return 检查费用数据集合
     */
    public List<HisInspect> selectInspectList(HisInspect inspect);

    /**
     * 通过检查费用ID查询检查费用信息
     *
     * @param inspect_id 检查费用ID
     * @return 角色对象信息
     */
    public HisInspect selectInspectById(Long inspect_id);

    /**
     * 修改检查费用信息
     *
     * @param inspect 检查费用信息id
     * @return 结果
     */
    public int updateInspect(HisInspect inspect);

    /**
     * 新增检查费用信息
     *
     * @param inspect 检查费用信息
     * @return 结果
     */
    public int insertInspect(HisInspect inspect);

    /**
     * 批量删除挂号费用信息
     *
     * @param inspect_ids 需要删除的挂号费用ID
     * @return 结果
     */
    public int deleteInspectByIds(Long[] inspect_ids);

}
