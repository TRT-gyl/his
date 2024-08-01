package com.ruoyi.his.mapper;

import java.util.List;

import com.ruoyi.his.domain.RegistrationRecords;


/**
 * VIEWMapper接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface RegistrationRecordsMapper 
{

    List<RegistrationRecords> selectRegistrationRecordsList(RegistrationRecords registrationRecords);

    List<RegistrationRecords> selectStatisticRecordsList(RegistrationRecords registrationRecords);
}
