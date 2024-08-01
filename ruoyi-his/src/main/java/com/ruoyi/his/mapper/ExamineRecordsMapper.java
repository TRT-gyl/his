package com.ruoyi.his.mapper;

import java.util.List;

import com.ruoyi.his.domain.ExamineRecords;


/**
 * VIEWMapper接口
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
public interface ExamineRecordsMapper 
{
    /**
     * 查询VIEW列表
     * 
     * @param examineRecords VIEW
     * @return VIEW集合
     */
    List<ExamineRecords> selectExamineRecordsList(ExamineRecords examineRecords);


    List<ExamineRecords> selectStatisticRecordsList(ExamineRecords examineRecords);
}
