package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.domain.HisSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHisSupplierService {
    /**
     * 查询供应商管理列表
     *
     * @param hisSupplier 供应商管理
     * @return 供应商管理集合
     */
    public List<HisSupplier> selectHisSupplierList(HisSupplier hisSupplier);

    /**
     * 根据id查询供应商(用于回显)
     *
     *
     */
    public HisSupplier selectHisSupplierById(Long supplierId);

    /**
     * 新增供应商管理
     *
     * @param hisSupplier 供应商管理
     * @return 结果
     */
    public int insertHisSupplier(HisSupplier hisSupplier);

    /**
     * 修改供应商管理
     *
     * @param hisSupplier 供应商管理
     * @return 结果
     */
    public int updateHisSupplier(HisSupplier hisSupplier);

    /**
     * 批量删除供应商
     *
     * @param supplierIds 需要删除的供应商管理主键集合
     * @return 结果
     */
    public int deleteHisSupplierBySupplierIds(Long[] supplierIds);

    /**
     * 删除供应商管理信息
     *
     * @param supplierId 供应商管理主键
     * @return 结果
     */
    public int deleteHisSupplierBySupplierId(Long supplierId);

    /**
     * 检查供应商名称是否重复
     */

    public String checkSupplierNameUnique(HisSupplier hisSupplier);

    List<HisPurchasWarehousing> selectPwMsg(@Param("suppliersId")Long suppliersId);


}
