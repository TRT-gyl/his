package com.ruoyi.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.HisDept;
import com.ruoyi.his.service.IHisDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 科室管理Controller
 *
 * @author apr_jay
 * @date 2022-08-28
 */
@RestController
@RequestMapping("/his/dept")
public class HisDeptController extends BaseController {
    @Autowired
    private IHisDeptService hisDeptService;

    /**
     * 查询科室管理列表
     */
    @PreAuthorize("@ss.hasPermi('his:dept:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisDept hisDept) {
        startPage();
        List<HisDept> list = hisDeptService.selectHisDeptList(hisDept);
        return getDataTable(list);
    }

    /**
     * 导出科室管理列表
     */
    @PreAuthorize("@ss.hasPermi('his:dept:export')")
    @Log(title = "科室管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisDept hisDept) {
        List<HisDept> list = hisDeptService.selectHisDeptList(hisDept);
        ExcelUtil<HisDept> util = new ExcelUtil<HisDept>(HisDept.class);
        util.exportExcel(response, list, "科室管理数据");
    }

    /**
     * 获取科室管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId) {
        return AjaxResult.success(hisDeptService.selectHisDeptByDeptId(deptId));
    }

    /**
     * 新增科室管理
     */
    @PreAuthorize("@ss.hasPermi('his:dept:add')")
    @Log(title = "科室管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HisDept hisDept) {
        return toAjax(hisDeptService.insertHisDept(hisDept));
    }

    /**
     * 修改科室管理
     */
    @PreAuthorize("@ss.hasPermi('his:dept:edit')")
    @Log(title = "科室管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisDept hisDept) {
        return toAjax(hisDeptService.updateHisDept(hisDept));
    }

    /**
     * 删除科室管理
     */
    @PreAuthorize("@ss.hasPermi('his:dept:remove')")
    @Log(title = "科室管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds) {
        return toAjax(hisDeptService.deleteHisDeptByDeptIds(deptIds));
    }
}
