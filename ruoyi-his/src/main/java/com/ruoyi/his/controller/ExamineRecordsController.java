package com.ruoyi.his.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.ExamineRecords;
import com.ruoyi.his.domain.MedicineSaleRecords;
import com.ruoyi.his.domain.RevenuSummary;
import com.ruoyi.his.domain.SummarySeries;
import com.ruoyi.his.service.IExamineRecordsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * VIEWController
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/his/statistic/examine")
public class ExamineRecordsController extends BaseController
{
    @Autowired
    private IExamineRecordsService examineRecordsService;



    @PreAuthorize("@ss.hasPermi('his:statistic:examine')")
    @GetMapping("/examineRecords")
    public TableDataInfo listExamineRecords(ExamineRecords examineRecords)
    {
        logger.debug("examineRecords:"+examineRecords);
        startPage();
        List<ExamineRecords> list = examineRecordsService.selectExamineRecordsList(examineRecords);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('his:statistic:examine')")
    @GetMapping("/statisticRecords")
    public TableDataInfo listStatisticRecords(ExamineRecords examineRecords)
    {
        startPage();
        List<ExamineRecords> list = examineRecordsService.selectStatisticRecords(examineRecords);
        return getDataTable(list);
    }

}
