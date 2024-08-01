package com.ruoyi.his.mapper;

import java.util.List;

import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.domain.HisSupplier;

/**
 * 采购入库Mapper接口
 *
 * @author zhangbing
 * @date 2022-08-31
 */
public interface HisPurchasWarehousingMapper
{
    /**
     * 查询采购入库
     *
     * @param id 采购入库主键
     * @return 采购入库
     */
    public HisPurchasWarehousing selectHisPurchasWarehousingById(Long id);

    /**
     * 查询药品供应商列表
     *
     * @param hisSupplier 药品供应商
     * @return 药品供应商集合
     */
    public List<HisSupplier> selectHisSupplierList(HisSupplier hisSupplier);

    /**
     * 查询药品列表(包含生产厂家)
     *
     * @param hisMedicine 药品
     * @return 药品列表
     */
    public List<HisMedicine> selectHisMedicineList(HisMedicine hisMedicine);

    /**
     * 查询药品-根据药品id
     *
     * @param id 药品
     * @return 药品
     */
    public HisMedicine selectHisMedicineByMedicineId(Long id);

    /**
     * 查询采购入库列表
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 采购入库集合
     */
    public List<HisPurchasWarehousing> selectHisPurchasWarehousingList(HisPurchasWarehousing hisPurchasWarehousing);

    /**
     * 新增入库及子订单信息
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 结果
     */
    public int insertHisPurchasWarehousing(HisPurchasWarehousing hisPurchasWarehousing);

    /**
     * 新增入库及子订单信息同时增加药品的库存
     *
     * @param hisMedicine 药品
     * @return 结果
     */

     public int updateHisMedicine(HisMedicine hisMedicine);

    /**
     * 修改采购入库
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 结果
     */
    public int updateHisPurchasWarehousing(HisPurchasWarehousing hisPurchasWarehousing);

    /**
     * 删除采购入库
     *
     * @param id 采购入库主键
     * @return 结果
     */
    public int deleteHisPurchasWarehousingById(Long id);

    /**
     * 批量删除采购入库
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisPurchasWarehousingByIds(Long[] ids);
}
