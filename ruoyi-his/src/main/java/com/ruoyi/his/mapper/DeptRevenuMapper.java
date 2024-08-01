package com.ruoyi.his.mapper;

import com.ruoyi.his.domain.DeptRevenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * VIEWMapper接口
 *
 * @author ruoyi
 * @date 2022-08-31
 */
public interface DeptRevenuMapper {
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
     * 删除VIEW
     *
     * @param deptId VIEW主键
     * @return 结果
     */
    public int deleteDeptRevenuByDeptId(Long deptId);

    /**
     * 批量删除VIEW
     *
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDeptRevenuByDeptIds(Long[] deptIds);

    public List<DeptRevenu> selectDeptRevenuInPeriod(@Param("startDate") String startDate, @Param("endDate") String endDate);

}
