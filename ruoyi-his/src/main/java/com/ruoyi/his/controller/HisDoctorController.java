package com.ruoyi.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.service.IHisDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 医生Controller
 *
 * @author apr_jay
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/his/doctor")
public class HisDoctorController extends BaseController {
    @Autowired
    private IHisDoctorService hisDoctorService;

    /**
     * 查询医生列表
     */
    @PreAuthorize("@ss.hasPermi('his:doctor:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisDoctor hisDoctor) {
        startPage();
        List<HisDoctor> list = hisDoctorService.selectHisDoctorList(hisDoctor);
        return getDataTable(list);
    }

    /**
     * 导出医生列表
     */
    @PreAuthorize("@ss.hasPermi('his:doctor:export')")
    @Log(title = "医生", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisDoctor hisDoctor) {
        List<HisDoctor> list = hisDoctorService.selectHisDoctorList(hisDoctor);
        ExcelUtil<HisDoctor> util = new ExcelUtil<HisDoctor>(HisDoctor.class);
        util.exportExcel(response, list, "医生数据");
    }

    /**
     * 获取医生详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:doctor:query')")
    @GetMapping(value = "/{doctId}")
    public AjaxResult getInfo(@PathVariable("doctId") Long doctId) {
        return AjaxResult.success(hisDoctorService.selectHisDoctorByDoctId(doctId));
    }

    /**
     * 新增医生
     */
    @PreAuthorize("@ss.hasPermi('his:doctor:add')")
    @Log(title = "医生", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HisDoctor hisDoctor) {
        if (StringUtils.isNotEmpty(hisDoctor.getDoctPhone())
                && UserConstants.NOT_UNIQUE.equals(hisDoctorService.checkPhoneUnique(hisDoctor))) {
            return AjaxResult.error("新增用户'" + hisDoctor.getDoctName() + "'失败，手机号码已存在");
        }
        return toAjax(hisDoctorService.insertHisDoctor(hisDoctor));
    }

    /**
     * 修改医生
     */
    @PreAuthorize("@ss.hasPermi('his:doctor:edit')")
    @Log(title = "医生", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisDoctor hisDoctor) {
        return toAjax(hisDoctorService.updateHisDoctor(hisDoctor));
    }

    /**
     * 删除医生
     */
    @PreAuthorize("@ss.hasPermi('his:doctor:remove')")
    @Log(title = "医生", businessType = BusinessType.DELETE)
    @DeleteMapping("/{doctIds}")
    public AjaxResult remove(@PathVariable Long[] doctIds) {
        return toAjax(hisDoctorService.deleteHisDoctorByDoctIds(doctIds));
    }
}
