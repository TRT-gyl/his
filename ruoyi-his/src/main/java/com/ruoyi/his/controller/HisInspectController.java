package com.ruoyi.his.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.HisInspect;
import com.ruoyi.his.service.IHisInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("his/inspect")
public class HisInspectController extends BaseController {
    @Autowired
    private IHisInspectService hisInspectService;

    /**
     * 新增检查费用信息
     */
    @PreAuthorize("@ss.hasPermi('his:inspect:add')")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HisInspect inspect) {
        int i = hisInspectService.insertInspect(inspect);
        return toAjax(i);
    }

    /**
     * 查询
     */
    @PreAuthorize("@ss.hasPermi('his:inspect:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisInspect sysInspect) {
        startPage();
        List<HisInspect> list = hisInspectService.selectInspectList(sysInspect);
        return getDataTable(list);
    }

    /**
     * 根据编号查询
     */
    @PreAuthorize("@ss.hasPermi('his:inspect:query')")
    @GetMapping(value = "/{inspect_id}")
    public AjaxResult getInfo(@PathVariable Long inspect_id) {
        return AjaxResult.success(hisInspectService.selectInspectById(inspect_id));
    }

    /**
     * 修改
     */
    @PreAuthorize("@ss.hasPermi('his:inspect:edit')")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody HisInspect inspect) {
        return toAjax(hisInspectService.updateInspect(inspect));
    }

    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('his:inspect:remove')")
    @DeleteMapping("/{inspect_ids}")
    public AjaxResult remove(@PathVariable Long[] inspect_ids) {
        int i = hisInspectService.deleteInspectByIds(inspect_ids);
        System.out.println(i);
        return toAjax(i);
    }


    /**
     * 导出
     *
     * @param inspect
     * @return
     */
    @PreAuthorize("@ss.hasPermi('his:register:export')")
    @GetMapping("/export")
    public AjaxResult export(HisInspect inspect) {
        List<HisInspect> list = hisInspectService.selectInspectList(inspect);
        ExcelUtil<HisInspect> util = new ExcelUtil<HisInspect>(HisInspect.class);
        return util.exportExcel(list, "挂号费信息");
    }
}
