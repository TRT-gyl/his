package com.ruoyi.his.service;

import java.util.List;

import com.ruoyi.his.domain.ExamineRecords;


/**
 * VIEWService接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface IExamineRecordsService 
{


    List<ExamineRecords> selectExamineRecordsList(ExamineRecords examineRecords);


    List<ExamineRecords> selectStatisticRecords(ExamineRecords examineRecords);
}
