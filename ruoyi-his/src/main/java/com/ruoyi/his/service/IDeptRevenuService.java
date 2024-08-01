package com.ruoyi.his.service;

import java.util.List;

import com.ruoyi.his.domain.DeptRevenu;
import com.ruoyi.his.domain.RevenuSummary;
import com.ruoyi.his.domain.SummarySeries;


/**
 * VIEWService接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface IDeptRevenuService 
{
    /**
     * 查询VIEW
     * 
     * @param deptId VIEW主键
     * @return VIEW
     */
    public DeptRevenu selectDeptRevenuByDeptId(Long deptId);

    /**
     * 查询VIEW列表
     * 
     * @param deptRevenu VIEW
     * @return VIEW集合
     */
    public List<DeptRevenu> selectDeptRevenuList(DeptRevenu deptRevenu);

    /**
     * 新增VIEW
     * 
     * @param deptRevenu VIEW
     * @return 结果
     */
    public int insertDeptRevenu(DeptRevenu deptRevenu);

    /**
     * 修改VIEW
     * 
     * @param deptRevenu VIEW
     * @return 结果
     */
    public int updateDeptRevenu(DeptRevenu deptRevenu);

    /**
     * 批量删除VIEW
     * 
     * @param deptIds 需要删除的VIEW主键集合
     * @return 结果
     */
    public int deleteDeptRevenuByDeptIds(Long[] deptIds);

    /**
     * 删除VIEW信息
     * 
     * @param deptId VIEW主键
     * @return 结果
     */
    public int deleteDeptRevenuByDeptId(Long deptId);
    /**
     * 查询收支汇总信息
     * */
    RevenuSummary getRevenuSummary(String startDate, String endDate);

    SummarySeries getSummarySeries(String freq);


}
