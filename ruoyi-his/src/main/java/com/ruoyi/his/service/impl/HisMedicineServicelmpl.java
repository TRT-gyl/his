package com.ruoyi.his.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisPrescription;
import com.ruoyi.his.mapper.HisMedicineMapper;
import com.ruoyi.his.service.IHisMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class HisMedicineServicelmpl implements IHisMedicineService {
    @Autowired
    private HisMedicineMapper hisMedicineMapper;

    @Override
    public List<HisMedicine> selectHisMedicineList(HisMedicine hisMedicine) {
        return hisMedicineMapper.selectHisMedicineList(hisMedicine);
    }

    @Override
    public HisMedicine selectHisMedicineById(Long medicineId) {
        return hisMedicineMapper.selectHisMedicineById(medicineId);
    }

    @Override
    public int insertHisMedicine(HisMedicine hisMedicine) {
        return hisMedicineMapper.insertHisMedicine(hisMedicine);
    }

    @Override
    public int updateHisMedicine(HisMedicine hisMedicine) {

        return hisMedicineMapper.updateHisMedicine(hisMedicine);
    }

    @Override
    public int deleteHisMedicineByMedicineIds(Long[] medicineIds) {
        return hisMedicineMapper.deleteHisMedicineByMedicineIds(medicineIds);
    }

    @Override
    public int deleteHisMedicineByMedicineId(Long medicineId) {
        return hisMedicineMapper.deleteHisMedicineByMedicineId(medicineId);
    }

    @Override
    public String checkMedicineNameUnique(HisMedicine hisMedicine) {
        Long medicineId= StringUtils.isNull(hisMedicine.getMedicineId())?-1L:hisMedicine.getMedicineId();
        HisMedicine info=hisMedicineMapper.checkMedicineNameUnique(hisMedicine.getMedicineName());

        if(StringUtils.isNotNull(info)&&info.getMedicineId().longValue()!=medicineId.longValue()){
            return UserConstants.NOT_UNIQUE;//不是独立的厂家名字
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkMedicineNoUnique(HisMedicine hisMedicine) {
        Long medicineId= StringUtils.isNull(hisMedicine.getMedicineId())?-1L:hisMedicine.getMedicineId();
        HisMedicine info=hisMedicineMapper.checkMedicineNoUnique(hisMedicine.getMedicineNo());

        if(StringUtils.isNotNull(info)&&info.getMedicineId().longValue()!=medicineId.longValue()){
            return UserConstants.NOT_UNIQUE;//厂家编号重复
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkKeywordUnique(HisMedicine hisMedicine) {
        Long medicineId= StringUtils.isNull(hisMedicine.getMedicineId())?-1L:hisMedicine.getMedicineId();
        HisMedicine info=hisMedicineMapper.checkKeywordUnique(hisMedicine.getKeyword());

        if(StringUtils.isNotNull(info)&&info.getMedicineId().longValue()!=medicineId.longValue()){
            return UserConstants.NOT_UNIQUE;//不是独立的查询
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public List<HisPrescription> selectPrescribeMsg(Long medicineId) {
        return hisMedicineMapper.selectPrescribeMsg(medicineId);
    }
}
