package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisPrescription;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHisMedicineService {
    /**
     * 查询药品管理列表
     *
     * @param hisMedicine 药品管理
     * @return 药品管理集合
     */
    public List<HisMedicine> selectHisMedicineList(HisMedicine hisMedicine);

    /**
     * 根据id查询药品(用于回显)
     *
     *
     */
    public HisMedicine selectHisMedicineById(Long medicineId);

    /**
     * 新增药品管理
     *
     * @param hisMedicine 药品管理
     * @return 结果
     */
    public int insertHisMedicine(HisMedicine hisMedicine);

    /**
     * 修改药品管理
     *
     * @param hisMedicine 药品管理
     * @return 结果
     */
    public int updateHisMedicine(HisMedicine hisMedicine);

    /**
     * 批量删除药品管理
     *
     * @param medicineIds 需要删除的药品管理主键集合
     * @return 结果
     */
    public int deleteHisMedicineByMedicineIds(Long[] medicineIds);

    /**
     * 删除药品管理信息
     *
     * @param medicineId 药品管理主键
     * @return 结果
     */
    public int deleteHisMedicineByMedicineId(Long medicineId);

    /**
     * 检查药品名称重复
     */

    public String checkMedicineNameUnique(HisMedicine hisMedicine);

    /**
     * 检查药品编号是否重复
     */

    public String checkMedicineNoUnique(HisMedicine hisMedicine);

    /**
     * 检查药品查询关键字是否重复
     */

    public String checkKeywordUnique(HisMedicine hisMedicine);

    public List<HisPrescription> selectPrescribeMsg(Long medicineId);
}
