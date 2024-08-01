package com.ruoyi.his.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.DeptRevenu;
import com.ruoyi.his.domain.RevenuSummary;
import com.ruoyi.his.domain.SummarySeries;
import com.ruoyi.his.service.IDeptRevenuService;
import lombok.extern.slf4j.Slf4j;
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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * VIEWController
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/his/statistic/revenu")
@Slf4j
public class DeptRevenuController extends BaseController
{
    @Autowired
    private IDeptRevenuService deptRevenuService;

    /**
     * 查询日期内的收支汇总
     */
    @PreAuthorize("@ss.hasPermi('his:statistic:revenu')")
    @GetMapping("/summary")
    public AjaxResult getSummary(String startDate, String endDate){
        log.debug("startDate:"+startDate);
        log.debug("endDate:"+endDate);
        RevenuSummary revenuSummary = deptRevenuService.getRevenuSummary(startDate, endDate);
        return AjaxResult.success(revenuSummary);
    }


    /**
     * 查询医院的收入趋势
     */
    @PreAuthorize("@ss.hasPermi('his:statistic:revenu')")
    @GetMapping("/trend")
    public AjaxResult getTrend(String freq)
    {
        log.debug("freq:"+freq);
        SummarySeries summarySeries = deptRevenuService.getSummarySeries(freq);
        return AjaxResult.success(summarySeries);
    }


}
