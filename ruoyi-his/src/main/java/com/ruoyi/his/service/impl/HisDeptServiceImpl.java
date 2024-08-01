package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.HisDept;
import com.ruoyi.his.mapper.HisDeptMapper;
import com.ruoyi.his.service.IHisDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 科室管理Service业务层处理
 *
 * @author apr_jay
 * @date 2022-08-28
 */
@Service
public class HisDeptServiceImpl implements IHisDeptService
{
    @Autowired
    private HisDeptMapper hisDeptMapper;

    /**
     * 查询科室管理
     *
     * @param deptId 科室管理主键
     * @return 科室管理
     */
    @Override
    public HisDept selectHisDeptByDeptId(Long deptId)
    {
        return hisDeptMapper.selectHisDeptByDeptId(deptId);
    }

    /**
     * 查询科室管理列表
     *
     * @param hisDept 科室管理
     * @return 科室管理
     */
    @Override
    public List<HisDept> selectHisDeptList(HisDept hisDept)
    {
        return hisDeptMapper.selectHisDeptList(hisDept);
    }

    /**
     * 新增科室管理
     *
     * @param hisDept 科室管理
     * @return 结果
     */
    @Override
    public int insertHisDept(HisDept hisDept)
    {
        hisDept.setCreateTime(DateUtils.getNowDate());
        return hisDeptMapper.insertHisDept(hisDept);
    }

    /**
     * 修改科室管理
     *
     * @param hisDept 科室管理
     * @return 结果
     */
    @Override
    public int updateHisDept(HisDept hisDept)
    {
        hisDept.setUpdateTime(DateUtils.getNowDate());
        return hisDeptMapper.updateHisDept(hisDept);
    }

    /**
     * 批量删除科室管理
     *
     * @param deptIds 需要删除的科室管理主键
     * @return 结果
     */
    @Override
    public int deleteHisDeptByDeptIds(Long[] deptIds)
    {
        return hisDeptMapper.deleteHisDeptByDeptIds(deptIds);
    }

    /**
     * 删除科室管理信息
     *
     * @param deptId 科室管理主键
     * @return 结果
     */
    @Override
    public int deleteHisDeptByDeptId(Long deptId)
    {
        return hisDeptMapper.deleteHisDeptByDeptId(deptId);
    }
}
