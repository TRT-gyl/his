package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisInspect;

import java.util.List;

public interface IHisInspectService {
    /**
     * 查询检查费用信息数据集合
     *
     * @param inspect 检查费用信息
     * @return 检查费用数据集合
     */
    public List<HisInspect> selectInspectList(HisInspect inspect);

    /**
     * 批量删除挂号费用信息
     *
     * @param inspectIds 需要删除的挂号费用ID
     * @return 结果
     */
    public int deleteInspectByIds(Long[] inspectIds);

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
     * 通过检查费用ID查询检查费用信息
     *
     * @param inspectId 检查费用ID
     * @return 角色对象信息
     */
    public HisInspect selectInspectById(Long inspectId);
}
