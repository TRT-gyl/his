package com.ruoyi.his.mapper;

import java.util.List;
import com.ruoyi.his.domain.HisDept;

/**
 * 科室管理Mapper接口
 *
 * @author apr_jay
 * @date 2022-08-28
 */
public interface HisDeptMapper
{
    /**
     * 查询科室管理
     *
     * @param deptId 科室管理主键
     * @return 科室管理
     */
    public HisDept selectHisDeptByDeptId(Long deptId);

    /**
     * 查询科室管理列表
     *
     * @param hisDept 科室管理
     * @return 科室管理集合
     */
    public List<HisDept> selectHisDeptList(HisDept hisDept);

    /**
     * 新增科室管理
     *
     * @param hisDept 科室管理
     * @return 结果
     */
    public int insertHisDept(HisDept hisDept);

    /**
     * 修改科室管理
     *
     * @param hisDept 科室管理
     * @return 结果
     */
    public int updateHisDept(HisDept hisDept);

    /**
     * 删除科室管理
     *
     * @param deptId 科室管理主键
     * @return 结果
     */
    public int deleteHisDeptByDeptId(Long deptId);

    /**
     * 批量删除科室管理
     *
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisDeptByDeptIds(Long[] deptIds);
}
