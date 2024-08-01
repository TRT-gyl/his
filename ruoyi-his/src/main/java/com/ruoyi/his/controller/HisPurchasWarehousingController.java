package com.ruoyi.his.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisSupplier;
import com.ruoyi.his.domain.vo.VoPurchaseOrder;
import com.ruoyi.his.domain.vo.VoRepertory;
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
import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.service.IHisPurchasWarehousingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 采购入库Controller
 *
 * @author zhangbing
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/his/warehousing")
public class HisPurchasWarehousingController extends BaseController
{
    @Autowired
    private IHisPurchasWarehousingService hisPurchasWarehousingService;



    /**
     * 查询采购入库列表
     */
    @PreAuthorize("@ss.hasPermi('his:warehousing:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisPurchasWarehousing hisPurchasWarehousing)
    {
        startPage();
        List<HisPurchasWarehousing> list = hisPurchasWarehousingService.selectHisPurchasWarehousingList(hisPurchasWarehousing);
        return getDataTable(list);
    }

    /**
     * 查询药品供应商列表
     */
    //@PreAuthorize("@ss.hasPermi('his:supplier:list')")
    @GetMapping("/hisSupplierList")
    public AjaxResult list(HisSupplier hisSupplier)
    {
        List<HisSupplier> list = hisPurchasWarehousingService.selectHisSupplierList(hisSupplier);
        return AjaxResult.success(list);
    }

    /**
     * 查询药品列表
     */
    //@PreAuthorize("@ss.hasPermi('his:supplier:list')")
    @GetMapping("/hisMedicineList")
    public TableDataInfo hisMedicineList(HisMedicine hisMedicine)
    {
        startPage();
        List<HisMedicine> list = hisPurchasWarehousingService.selectHisMedicineList(hisMedicine);
        return getDataTable(list);
    }

    /**
     * 导出采购入库列表
     */
    @PreAuthorize("@ss.hasPermi('his:warehousing:export')")
    @Log(title = "采购入库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisPurchasWarehousing hisPurchasWarehousing)
    {
        List<HisPurchasWarehousing> list = hisPurchasWarehousingService.selectHisPurchasWarehousingList(hisPurchasWarehousing);
        ExcelUtil<HisPurchasWarehousing> util = new ExcelUtil<HisPurchasWarehousing>(HisPurchasWarehousing.class);
        util.exportExcel(response, list, "采购入库数据");
    }

    /**
     * 获取采购入库详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:warehousing:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(hisPurchasWarehousingService.selectHisPurchasWarehousingById(id));
    }

    /**
     * 新增采购入库
     */
    @PreAuthorize("@ss.hasPermi('his:warehousing:add')")
    @Log(title = "采购入库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HisPurchasWarehousing hisPurchasWarehousing)
    {
        return toAjax(hisPurchasWarehousingService.insertHisPurchasWarehousing(hisPurchasWarehousing));
    }

    /**
     * 修改采购入库
     */
    @PreAuthorize("@ss.hasPermi('his:warehousing:edit')")
    @Log(title = "采购入库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisPurchasWarehousing hisPurchasWarehousing)
    {
        return toAjax(hisPurchasWarehousingService.updateHisPurchasWarehousing(hisPurchasWarehousing));
    }

    /**
     * 修改采购入库的同时修改下属的订单
     */
    @PutMapping("/updateOrder")
    public AjaxResult update(@RequestBody VoPurchaseOrder voPurchaseOrder)
    {
        return toAjax(hisPurchasWarehousingService.updateHisPurchasWarehousingAndHisPurchase(voPurchaseOrder));
    }

    /**
     * 删除采购入库
     */
    @PreAuthorize("@ss.hasPermi('his:warehousing:remove')")
    @Log(title = "采购入库", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(hisPurchasWarehousingService.deleteHisPurchasWarehousingByIds(ids));
    }
}
