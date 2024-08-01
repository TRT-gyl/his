package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisPurchase;
import com.ruoyi.his.domain.HisSupplier;
import com.ruoyi.his.domain.vo.VoPurchaseOrder;
import com.ruoyi.his.mapper.HisPurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisPurchasWarehousingMapper;
import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.service.IHisPurchasWarehousingService;

/**
 * 采购入库Service业务层处理
 *
 * @author zhangbing
 * @date 2022-08-31
 */
@Service
public class HisPurchasWarehousingServiceImpl implements IHisPurchasWarehousingService
{
    @Autowired
    private HisPurchasWarehousingMapper hisPurchasWarehousingMapper;
    @Autowired
    private HisPurchaseMapper hisPurchaseMapper;

    /**
     * 查询采购入库
     *
     * @param id 采购入库主键
     * @return 采购入库
     */
    @Override
    public HisPurchasWarehousing selectHisPurchasWarehousingById(Long id)
    {
        return hisPurchasWarehousingMapper.selectHisPurchasWarehousingById(id);
    }

    /**
     * 查询采购入库列表
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 采购入库
     */
    @Override
    public List<HisPurchasWarehousing> selectHisPurchasWarehousingList(HisPurchasWarehousing hisPurchasWarehousing)
    {
        return hisPurchasWarehousingMapper.selectHisPurchasWarehousingList(hisPurchasWarehousing);
    }

    /**
     * 查询药品供应商列表
     *
     * @param hisSupplier 药品供应商
     * @return 药品供应商
     */
    @Override
    public List<HisSupplier> selectHisSupplierList(HisSupplier hisSupplier)
    {
        return hisPurchasWarehousingMapper.selectHisSupplierList(hisSupplier);
    }

    @Override
    public List<HisMedicine> selectHisMedicineList(HisMedicine hisMedicine) {
        return hisPurchasWarehousingMapper.selectHisMedicineList(hisMedicine);
    }

    /**
     * 新增采购入库
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 结果
     */
    @Override
    public int insertHisPurchasWarehousing(HisPurchasWarehousing hisPurchasWarehousing)
    {
        hisPurchasWarehousing.setCreateTime(DateUtils.getNowDate());
        int re = hisPurchasWarehousingMapper.insertHisPurchasWarehousing(hisPurchasWarehousing);
        for (int i=0;i<hisPurchasWarehousing.getPurchases().size();i++){
            //增加订单
            HisPurchase hisPurchase = hisPurchasWarehousing.getPurchases().get(i);
            hisPurchase.setCreateTime(DateUtils.getNowDate());
            hisPurchase.setPwId(hisPurchasWarehousing.getId());
            re+=hisPurchaseMapper.insertHisPurchase(hisPurchase);
        }
        return re;
    }
    //入库时增加药品库存 param:id是入库id
    public int updateMedicinePurse(Long id){
        HisPurchase hisPurchase1=new HisPurchase();
        hisPurchase1.setPwId(id);
        List<HisPurchase> hisPurchases = hisPurchaseMapper.selectHisPurchaseList(hisPurchase1);
        int re=0;
        for (HisPurchase hisPurchase:hisPurchases){
            //根据药品id查询药品的库存信息
            HisMedicine hisMedicine = hisPurchasWarehousingMapper.selectHisMedicineByMedicineId(hisPurchase.getDrugId());
            //当入库时修改药品的库存信息
            long l = hisMedicine.getStock().longValue();
            Long amount = hisPurchase.getAmount();
            amount=l+amount;
            hisMedicine.setStock(amount.intValue());
            re+=hisPurchasWarehousingMapper.updateHisMedicine(hisMedicine);
        }
        return re;

    }

    /**
     * 修改采购入库
     *
     * @param hisPurchasWarehousing 采购入库
     * @return 结果
     */
    @Override
    public int updateHisPurchasWarehousing(HisPurchasWarehousing hisPurchasWarehousing)
    {
        hisPurchasWarehousing.setUpdateTime(DateUtils.getNowDate());
        if("2".equals(hisPurchasWarehousing.getStatus())){
            updateMedicinePurse(hisPurchasWarehousing.getId());
        }
        return hisPurchasWarehousingMapper.updateHisPurchasWarehousing(hisPurchasWarehousing);
    }

    /**
     * 修改采购入库及订单集合
     *
     * @param voPurchaseOrder 采购入库及订单集合
     * @return 结果
     */
    @Override
    public int updateHisPurchasWarehousingAndHisPurchase(VoPurchaseOrder voPurchaseOrder)
    {
        int index=0;
        index+=hisPurchasWarehousingMapper.updateHisPurchasWarehousing(voPurchaseOrder.getHisPurchasWarehousing());
        for (HisPurchase hisPurchase: voPurchaseOrder.getHisPurchaseList()) {
            index+=hisPurchaseMapper.updateHisPurchase(hisPurchase);
        }
        return index;
    }

    /**
     * 批量删除采购入库
     *
     * @param ids 需要删除的采购入库主键
     * @return 结果
     */
    @Override
    public int deleteHisPurchasWarehousingByIds(Long[] ids)
    {
        return hisPurchasWarehousingMapper.deleteHisPurchasWarehousingByIds(ids);
    }

    /**
     * 删除采购入库信息
     *
     * @param id 采购入库主键
     * @return 结果
     */
    @Override
    public int deleteHisPurchasWarehousingById(Long id)
    {
        return hisPurchasWarehousingMapper.deleteHisPurchasWarehousingById(id);
    }
}
