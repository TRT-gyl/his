package com.ruoyi.his.service;

import java.util.List;

import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.domain.HisSupplier;
import com.ruoyi.his.domain.vo.VoPurchaseOrder;

/**
 * 采购入库Service接口
 *
 * @author zhangbing
 * @date 2022-08-31
 */
public interface IHisPurchasWarehousingService
{
    /**
     * 查询采购入库
     *
     * @param id 采购入库主键
     * @return 采购入库
     */
    public HisPurchasWarehousing selectHisPurchasWarehousingById(Long id);

    /**
     * 查询采购入库列表
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 采购入库集合
     */
    public List<HisPurchasWarehousing> selectHisPurchasWarehousingList(HisPurchasWarehousing hisPurchasWarehousing);

    /**
     * 查询药品供应商列表
     *
     * @param hisSupplier 药品供应商
     * @return 药品供应商集合
     */
    public List<HisSupplier> selectHisSupplierList(HisSupplier hisSupplier);

    /**
     * 查询药品列表
     *
     * @param hisMedicine 药品信息
     * @return 药品集合(包含生产厂家)
     */
    public List<HisMedicine> selectHisMedicineList(HisMedicine hisMedicine);

    /**
     * 新增采购入库
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 结果
     */
    public int insertHisPurchasWarehousing(HisPurchasWarehousing hisPurchasWarehousing);

    /**
     * 修改采购入库
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 结果
     */
    public int updateHisPurchasWarehousing(HisPurchasWarehousing hisPurchasWarehousing);


    /**
     * 修改采购入库
     *
     * @param voPurchaseOrder 采购入库及订单集合
     * @return 结果
     */
    public int updateHisPurchasWarehousingAndHisPurchase(VoPurchaseOrder voPurchaseOrder);

    /**
     * 批量删除采购入库
     *
     * @param ids 需要删除的采购入库主键集合
     * @return 结果
     */
    public int deleteHisPurchasWarehousingByIds(Long[] ids);

    /**
     * 删除采购入库信息
     *
     * @param id 采购入库主键
     * @return 结果
     */
    public int deleteHisPurchasWarehousingById(Long id);
}
