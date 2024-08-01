package com.ruoyi.his.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.domain.HisSupplier;
import com.ruoyi.his.mapper.HisSupplierMapper;
import com.ruoyi.his.service.IHisSupplierService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
/*
供应商业务实现
 */
@Service
public class HisSupplierServiceImpl implements IHisSupplierService {

    @Autowired
    private HisSupplierMapper hisSupplierMapper;

    @Override
    public List<HisSupplier> selectHisSupplierList(HisSupplier hisSupplier) {
        return hisSupplierMapper.selectHisSupplierList(hisSupplier);
    }

    @Override
    public HisSupplier selectHisSupplierById(Long supplierId) {
        return hisSupplierMapper.selectHisSupplierById(supplierId);
    }

    @Override
    public int insertHisSupplier(HisSupplier hisSupplier) {
        return hisSupplierMapper.insertHisSupplier(hisSupplier);
    }

    @Override
    public int updateHisSupplier(HisSupplier hisSupplier) {

        return hisSupplierMapper.updateHisSupplier(hisSupplier);
    }

    @Override
    public int deleteHisSupplierBySupplierIds(Long[] supplierIds) {
        return hisSupplierMapper.deleteHisSupplierBySupplierIds(supplierIds);
    }

    @Override
    public int deleteHisSupplierBySupplierId(Long supplierId) {
        return hisSupplierMapper.deleteHisSupplierBySupplierId(supplierId);
    }

    @Override
    public String checkSupplierNameUnique(HisSupplier hisSupplier) {
        Long supplierId= StringUtils.isNull(hisSupplier.getSupplierId())?-1L:hisSupplier.getSupplierId();
        HisSupplier info=hisSupplierMapper.checkSupplierNameUnique(hisSupplier.getSupplierName());

        if(StringUtils.isNotNull(info)&&info.getSupplierId().longValue()!=supplierId.longValue()){
            return UserConstants.NOT_UNIQUE;//不是独立的厂家名字
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<HisPurchasWarehousing> selectPwMsg(Long suppliersId) {
        return hisSupplierMapper.selectPwMsg(suppliersId);
    }


}
