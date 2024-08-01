package com.ruoyi.his.controller;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.his.domain.vo.VoReg;
import com.ruoyi.his.service.IHisRegistrationService;
import com.ruoyi.his.service.impl.HisScheduleServiceImpl;
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
import com.ruoyi.his.domain.HisTreat;
import com.ruoyi.his.service.IHisTreatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 就诊Controller
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/his/treat")
public class HisTreatController extends BaseController
{
    @Autowired
    private IHisRegistrationService hisRegistrationService;
    @Autowired
    private IHisTreatService hisTreatService;

    /**
     * 查询就诊列表
     */
    @PreAuthorize("@ss.hasPermi('his:treat:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoReg voReg)
    {
        voReg.setScheduleDate(HisScheduleServiceImpl.convertD(LocalDate.now()));
        voReg.setScheduleDocterId(SecurityUtils.getUserId());//仅查询当前登陆医生
        startPage();
        List<VoReg> list = hisRegistrationService.selectHisRegistrationList(voReg);
        return getDataTable(list);
    }

    /**
     * 导出就诊列表
     */
    @PreAuthorize("@ss.hasPermi('his:treat:export')")
    @Log(title = "就诊", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisTreat hisTreat)
    {
        List<HisTreat> list = hisTreatService.selectHisTreatList(hisTreat);
        ExcelUtil<HisTreat> util = new ExcelUtil<HisTreat>(HisTreat.class);
        util.exportExcel(response, list, "就诊数据");
    }

    /**
     * 获取就诊详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:treat:query')")
    @GetMapping(value = "/{treatNo}")
    public AjaxResult getInfo(@PathVariable("treatNo") String treatNo)
    {
        return AjaxResult.success(hisTreatService.selectHisTreatByTreatNo(treatNo));
    }

    /**
     * 新增就诊
     */
    @PreAuthorize("@ss.hasPermi('his:treat:add')")
    @Log(title = "就诊", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HisTreat hisTreat)
    {
        return AjaxResult.success("增加成功",hisTreatService.insertHisTreat(hisTreat));
    }

    /**
     * 修改就诊
     */
    @PreAuthorize("@ss.hasPermi('his:treat:edit')")
    @Log(title = "就诊", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisTreat hisTreat)
    {
        return toAjax(hisTreatService.updateHisTreat(hisTreat));
    }

    /**
     * 删除就诊
     */
    @PreAuthorize("@ss.hasPermi('his:treat:remove')")
    @Log(title = "就诊", businessType = BusinessType.DELETE)
	@DeleteMapping("/{treatIds}")
    public AjaxResult remove(@PathVariable Long[] treatIds)
    {
        return toAjax(hisTreatService.deleteHisTreatByTreatIds(treatIds));
    }

    /**
     * 根据挂号Id获取就诊详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:treat:query')")
    @GetMapping(value = "/re/{registrationId}")
    public AjaxResult getInfoByre(@PathVariable("registrationId") Long registrationId)
    {
        return AjaxResult.success(hisTreatService.selectHisRegistrationId(registrationId));
    }
}
