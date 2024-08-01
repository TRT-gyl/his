package com.ruoyi.his.mapper;

import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.domain.HisSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HisSupplierMapper {

    /**
     * 查询供应商管理
     *
     * @param hisSupplier 供应商管理
     * @return 供应商管理集合
     */
    public List<HisSupplier> selectHisSupplierList(HisSupplier hisSupplier);

    public HisSupplier selectHisSupplierById(@Param("supplierId")Long supplierId);

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
     * 删除供应商管理
     *
     * @param supplierId 供应商管理主键
     * @return 结果
     */
    public int deleteHisSupplierBySupplierId(Long supplierId);

    /**
     * 批量删除供应商管理
     *
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisSupplierBySupplierIds(Long[] supplierIds);

    /**
     * 检查供应商的名称是否重复
     *
     *
     * @return 结果
     */

    public HisSupplier checkSupplierNameUnique(@Param("supplierName") String supplierName);

    public List<HisPurchasWarehousing> selectPwMsg(@Param("suppliersId")Long suppliersId);


}
