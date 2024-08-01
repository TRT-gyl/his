package com.ruoyi.his.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.DeptRevenu;
import com.ruoyi.his.domain.MedicinePurchaseRecords;
import com.ruoyi.his.domain.RevenuSummary;
import com.ruoyi.his.domain.SummarySeries;
import com.ruoyi.his.mapper.DeptRevenuMapper;
import com.ruoyi.his.mapper.MedicinePurchaseRecordsMapper;
import com.ruoyi.his.service.IDeptRevenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * VIEWService业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@Service
public class DeptRevenuServiceImpl implements IDeptRevenuService
{
    @Autowired
    private DeptRevenuMapper deptRevenuMapper;
    @Autowired
    private MedicinePurchaseRecordsMapper medicinePurchaseRecordsMapper;
    private static final int WEEK_DAYS = 7;
    private static final int MONTH_DAYS = 30;
    private static final int DAYS = 3;
    /**
     * 时间序列
     *
     * */


    /**
     * 查询VIEW
     * 
     * @param deptId VIEW主键
     * @return VIEW
     */
    @Override
    public DeptRevenu selectDeptRevenuByDeptId(Long deptId)
    {
        return deptRevenuMapper.selectDeptRevenuByDeptId(deptId);
    }
    /**
     * 查询收支汇总信息
     *
     * */
    /**
     * 查询VIEW列表
     * 
     * @param deptRevenu VIEW
     * @return VIEW
     */
    @Override
    public List<DeptRevenu> selectDeptRevenuList(DeptRevenu deptRevenu)
    {
        return deptRevenuMapper.selectDeptRevenuList(deptRevenu);
    }

    /**
     * 新增VIEW
     * 
     * @param deptRevenu VIEW
     * @return 结果
     */
    @Override
    public int insertDeptRevenu(DeptRevenu deptRevenu)
    {
        deptRevenu.setCreateTime(DateUtils.getNowDate());
        return deptRevenuMapper.insertDeptRevenu(deptRevenu);
    }

    /**
     * 修改VIEW
     * 
     * @param deptRevenu VIEW
     * @return 结果
     */
    @Override
    public int updateDeptRevenu(DeptRevenu deptRevenu)
    {
        return deptRevenuMapper.updateDeptRevenu(deptRevenu);
    }

    /**
     * 批量删除VIEW
     * 
     * @param deptIds 需要删除的VIEW主键
     * @return 结果
     */
    @Override
    public int deleteDeptRevenuByDeptIds(Long[] deptIds)
    {
        return deptRevenuMapper.deleteDeptRevenuByDeptIds(deptIds);
    }

    /**
     * 删除VIEW信息
     * 
     * @param deptId VIEW主键
     * @return 结果
     */
    @Override
    public int deleteDeptRevenuByDeptId(Long deptId)
    {
        return deptRevenuMapper.deleteDeptRevenuByDeptId(deptId);
    }

    @Override
    public RevenuSummary getRevenuSummary(String startDate, String endDate) {
        List<MedicinePurchaseRecords> medicinePurchaseRecords = medicinePurchaseRecordsMapper.selectMedicinePurchaseRecordsInPeriod(startDate, endDate);
        List<DeptRevenu> deptRevenus = deptRevenuMapper.selectDeptRevenuInPeriod(startDate, endDate);
        RevenuSummary summary = new RevenuSummary(deptRevenus, medicinePurchaseRecords);
        return summary;
    }

    @Override
    public SummarySeries getSummarySeries(String freq) {
        return null;
    }

//    @Override
//    public SummarySeries getSummarySeries(String freq) {
//        List<String> dateRange = null;
//        List<RevenuSummary> revenuSummaries = new ArrayList<>();
//        String currentDate = DateUtils.getDate();
//        if("days".equals(freq)){
//            dateRange = DateUtils.getDateRange(currentDate, DAYS);
//        }else if("week".equals(freq)){
//            dateRange = DateUtils.getDateRange(currentDate, WEEK_DAYS);
//        }else if("month".equals(freq)){
//            dateRange = DateUtils.getDateRange(currentDate, MONTH_DAYS);
//        }
//        for (String date: dateRange){
//            RevenuSummary revenuSummary = getRevenuSummary(date, date);
//            revenuSummaries.add(revenuSummary);
//        }
//        SummarySeries summarySeries = new SummarySeries();
//        summarySeries.setDateSeries(dateRange);
//        summarySeries.setRevenuSeries(revenuSummaries);
//        return summarySeries;
//    }
}
