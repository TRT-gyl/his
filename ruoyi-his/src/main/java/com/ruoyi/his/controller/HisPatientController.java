package com.ruoyi.his.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.his.domain.HisPatient;
import com.ruoyi.his.service.IHisPatientService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 患者Controller
 *
 * @author 吴绍艳
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/his/patient")
public class HisPatientController extends BaseController
{
    @Autowired
    private IHisPatientService hisPatientService;

    /**
     * 查询患者列表
     */
    @PreAuthorize("@ss.hasPermi('his:patient:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisPatient hisPatient)
    {
        startPage();
        List<HisPatient> list = hisPatientService.selectHisPatientList(hisPatient);
        return getDataTable(list);
    }

    /**
     * 导出患者列表
     */
    @PreAuthorize("@ss.hasPermi('his:patient:export')")
    @Log(title = "患者", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisPatient hisPatient)
    {
        List<HisPatient> list = hisPatientService.selectHisPatientList(hisPatient);
        ExcelUtil<HisPatient> util = new ExcelUtil<HisPatient>(HisPatient.class);
        util.exportExcel(response, list, "患者数据");
    }

    /**
     * 根据主键获取患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:patient:query')")
    @GetMapping(value = "/{patientId}")
    public AjaxResult getInfo(@PathVariable("patientId") Long patientId)
    {
        return AjaxResult.success(hisPatientService.selectHisPatientByPatientId(patientId));
    }
    /**
     * 获取身份证患者详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:patient:query')")
    @PostMapping(value = "/{idCard}")
    public AjaxResult getInfoByIDcard(@PathVariable("idCard") String idCard)
    {
        return AjaxResult.success(hisPatientService.selectHisPatientByIdCard(idCard));
    }
    /**
     * 新增患者
     */
    @PreAuthorize("@ss.hasPermi('his:patient:add')")
    @Log(title = "患者", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HisPatient hisPatient)
    {
        HisPatient hisPatient1 = hisPatientService.selectHisPatientByIdCard(hisPatient.getIdCard());
        if (hisPatient1!=null){
            return  AjaxResult.success("当前患者已存在，不能重复添加");
        }else {//返回新增成功的主键用于挂号
            int i = hisPatientService.insertHisPatient(hisPatient);
            logger.debug("i:",i);
            return AjaxResult.success(i);
        }

    }

    /**
     * 修改患者
     */
    @PreAuthorize("@ss.hasPermi('his:patient:edit')")
    @Log(title = "患者", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisPatient hisPatient)
    {
        return toAjax(hisPatientService.updateHisPatient(hisPatient));
    }


}
