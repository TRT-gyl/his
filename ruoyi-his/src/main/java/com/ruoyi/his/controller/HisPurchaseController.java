package com.ruoyi.his.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.vo.VoRepertory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.HisPurchase;
import com.ruoyi.his.service.IHisPurchaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 药品采购Controller
 *
 * @author zhangbing
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/his/purchase")
public class HisPurchaseController extends BaseController
{
    @Autowired
    private IHisPurchaseService hisPurchaseService;

    /**
     * 查询批次库存及价格
     */
    @GetMapping("/hisPurchaseList")
    public TableDataInfo hisPurchaseList(VoRepertory voRepertory)
    {
        System.out.println(voRepertory);
        startPage();
        List<HisPurchase> list = hisPurchaseService.selectHisPurchases(voRepertory);
        return getDataTable(list);
    }


    /**
     * 查询药品采购列表
     */
    @PreAuthorize("@ss.hasPermi('his:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisPurchase hisPurchase)
    {
        startPage();
        List<HisPurchase> list = hisPurchaseService.selectHisPurchaseList(hisPurchase);
        return getDataTable(list);
    }

    /**
     * 导出药品采购列表
     */
    @PreAuthorize("@ss.hasPermi('his:purchase:export')")
    @Log(title = "药品采购", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisPurchase hisPurchase)
    {
        List<HisPurchase> list = hisPurchaseService.selectHisPurchaseList(hisPurchase);
        ExcelUtil<HisPurchase> util = new ExcelUtil<HisPurchase>(HisPurchase.class);
        util.exportExcel(response, list, "药品采购数据");
    }

    /**
     * 获取药品采购详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:purchase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(hisPurchaseService.selectHisPurchaseById(id));
    }

    /**
     * 新增药品采购
     */
    @PreAuthorize("@ss.hasPermi('his:purchase:add')")
    @Log(title = "药品采购", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HisPurchase hisPurchase)
    {
        return toAjax(hisPurchaseService.insertHisPurchase(hisPurchase));
    }

    /**
     * 修改药品采购
     */
    @PreAuthorize("@ss.hasPermi('his:purchase:edit')")
    @Log(title = "药品采购", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisPurchase hisPurchase)
    {
        return toAjax(hisPurchaseService.updateHisPurchase(hisPurchase));
    }

    /**
     * 删除药品采购
     */
    @PreAuthorize("@ss.hasPermi('his:purchase:remove')")
    @Log(title = "药品采购", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hisPurchaseService.deleteHisPurchaseByIds(ids));
    }
}
