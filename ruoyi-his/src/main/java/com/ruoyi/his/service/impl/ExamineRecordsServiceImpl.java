package com.ruoyi.his.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.ExamineRecords;
import com.ruoyi.his.mapper.ExamineRecordsMapper;
import com.ruoyi.his.service.IExamineRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * VIEWService业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@Service
public class ExamineRecordsServiceImpl implements IExamineRecordsService
{
    @Autowired
    private ExamineRecordsMapper examineRecordsMapper;


    /**
     * 查询VIEW列表
     * 
     * @param examineRecords VIEW
     * @return VIEW
     */
    @Override
    public List<ExamineRecords> selectExamineRecordsList(ExamineRecords examineRecords)
    {
        return examineRecordsMapper.selectExamineRecordsList(examineRecords);
    }


    @Override
    public List<ExamineRecords> selectStatisticRecords(ExamineRecords examineRecords) {
        return examineRecordsMapper.selectStatisticRecordsList(examineRecords);
    }
}
