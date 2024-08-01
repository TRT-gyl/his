package com.ruoyi.his.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.his.domain.vo.VoSchedule;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.HisSchedule;
import com.ruoyi.his.service.IHisScheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * schedule视图Controller
 *
 * @author 吴绍艳
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/his/schedule")
public class HisScheduleController extends BaseController {
    @Autowired
    private IHisScheduleService iHisScheduleService;


    /**
     * 查询schedule视图列表
     */
    @PreAuthorize("@ss.hasPermi('his:schedule:list')")
    @GetMapping("/list")
    public AjaxResult list(HisSchedule hisSchedule) {
        List<VoSchedule> list = iHisScheduleService.selectVSchedDoctorDeptList(hisSchedule);
        return AjaxResult.success(list);
    }

    /**
     * 查询个人的排班表
     */
    @PreAuthorize("@ss.hasPermi('his:schedule:selflist')")
    @GetMapping("/selflist")
    public AjaxResult selfList(HisSchedule hisSchedule){
        hisSchedule.setDoctId(SecurityUtils.getUserId());
        List<VoSchedule> list = iHisScheduleService.selectVSchedDoctorDeptList(hisSchedule);
        return AjaxResult.success(list);
    }


    /**
     * 修改schedule视图
     */
    @PreAuthorize("@ss.hasPermi('his:schedule:edit')")
    @Log(title = "schedule视图", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisSchedule hisSchedule) {
        return toAjax(iHisScheduleService.updateVSchedDoctorDept(hisSchedule));
    }

    /**
     * 获取排班表视图详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:schedule:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return AjaxResult.success(iHisScheduleService.selectScheduleByScheduleId(scheduleId));
    }

}
