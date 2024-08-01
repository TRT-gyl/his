package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.vo.VoRepertory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisPurchaseMapper;
import com.ruoyi.his.domain.HisPurchase;
import com.ruoyi.his.service.IHisPurchaseService;

/**
 * 药品采购Service业务层处理
 *
 * @author zhangbing
 * @date 2022-08-31
 */
@Service
public class HisPurchaseServiceImpl implements IHisPurchaseService
{
    @Autowired
    private HisPurchaseMapper hisPurchaseMapper;

    //查询批次库存及价格
    @Override
    public List<HisPurchase> selectHisPurchases(VoRepertory voRepertory) {
        return hisPurchaseMapper.selectHisPurchases(voRepertory);
    }

    /**
     * 查询药品采购
     *
     * @param id 药品采购主键
     * @return 药品采购
     */
    @Override
    public HisPurchase selectHisPurchaseById(Long id)
    {
        return hisPurchaseMapper.selectHisPurchaseById(id);
    }

    /**
     * 查询药品采购列表
     *
     * @param hisPurchase 药品采购
     * @return 药品采购
     */
    @Override
    public List<HisPurchase> selectHisPurchaseList(HisPurchase hisPurchase)
    {
        return hisPurchaseMapper.selectHisPurchaseList(hisPurchase);
    }

    /**
     * 新增药品采购
     *
     * @param hisPurchase 药品采购
     * @return 结果
     */
    @Override
    public int insertHisPurchase(HisPurchase hisPurchase)
    {
        hisPurchase.setCreateTime(DateUtils.getNowDate());
        return hisPurchaseMapper.insertHisPurchase(hisPurchase);
    }

    /**
     * 修改药品采购
     *
     * @param hisPurchase 药品采购
     * @return 结果
     */
    @Override
    public int updateHisPurchase(HisPurchase hisPurchase)
    {
        hisPurchase.setUpdateTime(DateUtils.getNowDate());
        return hisPurchaseMapper.updateHisPurchase(hisPurchase);
    }

    /**
     * 批量删除药品采购
     *
     * @param ids 需要删除的药品采购主键
     * @return 结果
     */
    @Override
    public int deleteHisPurchaseByIds(Long[] ids)
    {
        return hisPurchaseMapper.deleteHisPurchaseByIds(ids);
    }

    /**
     * 删除药品采购信息
     *
     * @param id 药品采购主键
     * @return 结果
     */
    @Override
    public int deleteHisPurchaseById(Long id)
    {
        return hisPurchaseMapper.deleteHisPurchaseById(id);
    }
}
