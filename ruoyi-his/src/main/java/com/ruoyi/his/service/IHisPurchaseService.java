package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.HisPurchase;
import com.ruoyi.his.domain.vo.VoRepertory;

/**
 * 药品采购Service接口
 *
 * @author zhangbing
 * @date 2022-08-31
 */
public interface IHisPurchaseService
{

    /**
     * 查询批次库存及价格
     *
     * @param VoRepertory
     * @return List<HisPurchase>
     */
    public List<HisPurchase> selectHisPurchases(VoRepertory voRepertory);

    /**
     * 查询药品采购
     *
     * @param id 药品采购主键
     * @return 药品采购
     */
    public HisPurchase selectHisPurchaseById(Long id);

    /**
     * 查询药品采购列表
     *
     * @param hisPurchase 药品采购
     * @return 药品采购集合
     */
    public List<HisPurchase> selectHisPurchaseList(HisPurchase hisPurchase);

    /**
     * 新增药品采购
     *
     * @param hisPurchase 药品采购
     * @return 结果
     */
    public int insertHisPurchase(HisPurchase hisPurchase);

    /**
     * 修改药品采购
     *
     * @param hisPurchase 药品采购
     * @return 结果
     */
    public int updateHisPurchase(HisPurchase hisPurchase);

    /**
     * 批量删除药品采购
     *
     * @param ids 需要删除的药品采购主键集合
     * @return 结果
     */
    public int deleteHisPurchaseByIds(Long[] ids);

    /**
     * 删除药品采购信息
     *
     * @param id 药品采购主键
     * @return 结果
     */
    public int deleteHisPurchaseById(Long id);
}
