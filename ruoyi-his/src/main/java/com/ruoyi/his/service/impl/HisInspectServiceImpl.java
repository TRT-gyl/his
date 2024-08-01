package com.ruoyi.his.service.impl;

import com.ruoyi.his.domain.HisInspect;
import com.ruoyi.his.mapper.HisInspectMapper;
import com.ruoyi.his.service.IHisInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HisInspectServiceImpl implements IHisInspectService {
    @Autowired
    private HisInspectMapper inspectMapper;

    /**
     * 查询检查费用信息数据集合
     *
     * @param inspect 检查费用信息
     * @return 检查费用数据集合
     */
    @Override
    public List<HisInspect> selectInspectList(HisInspect inspect) {
        return inspectMapper.selectInspectList(inspect);
    }

    /**
     * 批量删除挂号费用信息
     *
     * @param inspect_ids 需要删除的挂号费用ID
     * @return 结果
     */
    @Override
    public int deleteInspectByIds(Long[] inspect_ids) {
        return inspectMapper.deleteInspectByIds(inspect_ids);
    }

    /**
     * 修改检查费用信息
     *
     * @param inspect 检查费用信息id
     * @return 结果
     */
    @Override
    public int updateInspect(HisInspect inspect) {
        System.out.println("inspect = " + inspect);
        return inspectMapper.updateInspect(inspect);
    }

    /**
     * 新增检查费用信息
     *
     * @param inspect 检查费用信息
     * @return 结果
     */
    @Override
    public int insertInspect(HisInspect inspect) {
        return inspectMapper.insertInspect(inspect);
    }

    /**
     * 通过检查费用ID查询检查费用信息
     *
     * @param inspect_id 检查费用ID
     * @return 角色对象信息
     */
    @Override
    public HisInspect selectInspectById(Long inspect_id) {
        return inspectMapper.selectInspectById(inspect_id);
    }
}
