package com.ruoyi.his.service.impl;

import java.util.List;

import com.ruoyi.his.domain.RegistrationRecords;
import com.ruoyi.his.mapper.RegistrationRecordsMapper;
import com.ruoyi.his.service.IRegistrationRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * VIEWService业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@Service
public class RegistrationRecordsServiceImpl implements IRegistrationRecordsService
{
    @Autowired
    private RegistrationRecordsMapper registrationRecordsMapper;


    @Override
    public List<RegistrationRecords> selectRegistrationRecordsList(RegistrationRecords registrationRecords) {
        return registrationRecordsMapper.selectRegistrationRecordsList(registrationRecords);
    }

    @Override
    public List<RegistrationRecords> selectStatisticRecordsList(RegistrationRecords registrationRecords) {
        return registrationRecordsMapper.selectStatisticRecordsList(registrationRecords);
    }
}
