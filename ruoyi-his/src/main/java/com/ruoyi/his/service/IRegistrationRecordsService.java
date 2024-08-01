package com.ruoyi.his.service;


import com.ruoyi.his.domain.RegistrationRecords;

import java.util.List;

/**
 * VIEWService接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface IRegistrationRecordsService 
{


    List<RegistrationRecords> selectRegistrationRecordsList(RegistrationRecords registrationRecords);


    List<RegistrationRecords> selectStatisticRecordsList(RegistrationRecords registrationRecords);
}
