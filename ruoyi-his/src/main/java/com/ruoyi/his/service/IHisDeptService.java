package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisDept;

import java.util.List;

/**
 * 科室管理Service接口
 *
 * @author apr_jlr
 * @date 2022-08-31
 */
public interface IHisDeptService {
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
     * 批量删除科室管理
     *
     * @param deptIds 需要删除的科室管理主键集合
     * @return 结果
     */
    public int deleteHisDeptByDeptIds(Long[] deptIds);

    /**
     * 删除科室管理信息
     *
     * @param deptId 科室管理主键
     * @return 结果
     */
    public int deleteHisDeptByDeptId(Long deptId);
}
