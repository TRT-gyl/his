package com.ruoyi.his.mapper;

import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisPrescription;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HisMedicineMapper {

    /**
     * 查询药品管理列表
     *
     * @param hisMedicine 药品管理
     * @return 药品管理集合
     */
    public List<HisMedicine> selectHisMedicineList(HisMedicine hisMedicine);

    public HisMedicine selectHisMedicineById(@Param("medicineId")Long medicineId);

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
     * 删除药品管理
     *
     * @param medicineId 药品管理主键
     * @return 结果
     */
    public int deleteHisMedicineByMedicineId(Long medicineId);

    /**
     * 批量删除药品管理
     *
     * @param medicineIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHisMedicineByMedicineIds(Long[] medicineIds);

    /**
     * 检查药品的名称是否重复
     *
     *
     * @return 结果
     */

    public HisMedicine checkMedicineNameUnique(@Param("medicineName") String medicineName);

    /**
     * 检查药品的编码重复
     *
     *
     * @return 结果
     */

    public HisMedicine checkMedicineNoUnique(@Param("medicineNo") String medicineNo);

    /**
     * 检查药品的查询关键字是否重复
     *
     *
     * @return 结果
     */

    public HisMedicine checkKeywordUnique(@Param("keyword") String keyword);


    public List<HisPrescription> selectPrescribeMsg(@Param("medicineId")Long medicineId);

}
