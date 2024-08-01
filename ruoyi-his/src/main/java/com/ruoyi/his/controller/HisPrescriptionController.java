package com.ruoyi.his.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.his.domain.HisPrescription;
import com.ruoyi.his.service.IHisPrescriptionService;
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
 * 处方药品Controller
 * 
 * @author ruoyi
 * @date 2022-09-02
 */
@RestController
@RequestMapping("/his/prescription")
public class HisPrescriptionController extends BaseController
{
    @Autowired
    private IHisPrescriptionService hisPrescriptionService;

    /**
     * 查询处方药品列表
     */
    @PreAuthorize("@ss.hasPermi('his:prescription:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisPrescription hisPrescription)
    {
        startPage();
        List<HisPrescription> list = hisPrescriptionService.selectHisPrescriptionList(hisPrescription);
        return getDataTable(list);
    }

    /**
     * 导出处方药品列表
     */
    @PreAuthorize("@ss.hasPermi('his:prescription:export')")
    @Log(title = "处方药品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisPrescription hisPrescription)
    {
        List<HisPrescription> list = hisPrescriptionService.selectHisPrescriptionList(hisPrescription);
        ExcelUtil<HisPrescription> util = new ExcelUtil<HisPrescription>(HisPrescription.class);
        util.exportExcel(response, list, "处方药品数据");
    }

    /**
     * 获取处方药品详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:prescription:query')")
    @GetMapping(value = "/{prescriptionId}")
    public AjaxResult getInfo(@PathVariable("prescriptionId") Long prescriptionId)
    {
        return AjaxResult.success(hisPrescriptionService.selectHisPrescriptionByPrescriptionId(prescriptionId));
    }

    /**
     * 新增处方药品
     */
    @PreAuthorize("@ss.hasPermi('his:prescription:add')")
    @Log(title = "处方药品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HisPrescription hisPrescription)
    {
        return toAjax(hisPrescriptionService.insertHisPrescription(hisPrescription));
    }

    /**
     * 修改处方药品
     */
    @PreAuthorize("@ss.hasPermi('his:prescription:edit')")
    @Log(title = "处方药品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisPrescription hisPrescription)
    {
        return toAjax(hisPrescriptionService.updateHisPrescription(hisPrescription));
    }

    /**
     * 删除处方药品
     */
    @PreAuthorize("@ss.hasPermi('his:prescription:remove')")
    @Log(title = "处方药品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{prescriptionIds}")
    public AjaxResult remove(@PathVariable Long[] prescriptionIds)
    {
        return toAjax(hisPrescriptionService.deleteHisPrescriptionByPrescriptionIds(prescriptionIds));
    }
}
