package com.ruoyi.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.his.domain.HisRegCost;
import com.ruoyi.his.service.IHisRegCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 挂号费用Controller
 *
 * @author apr_jay
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/his/resCost")
public class HisRegCostController extends BaseController {
    @Autowired
    private IHisRegCostService hisRegCostService;

    /**
     * 查询挂号费用列表
     */
    @PreAuthorize("@ss.hasPermi('his:resCost:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisRegCost hisRegCost) {
        startPage();
        List<HisRegCost> list = hisRegCostService.selectHisRegCostList(hisRegCost);
        return getDataTable(list);
    }

    /**
     * 获取挂号费用详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:resCost:query')")
    @GetMapping(value = "/{regId}")
    public AjaxResult getInfo(@PathVariable("regId") Long regId) {
        return AjaxResult.success(hisRegCostService.selectHisRegCostByRegId(regId));
    }

    /**
     * 新增挂号费用
     */
    @PreAuthorize("@ss.hasPermi('his:resCost:add')")
    @Log(title = "挂号费用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HisRegCost hisRegCost) {
        return toAjax(hisRegCostService.insertHisRegCost(hisRegCost));
    }

    /**
     * 修改挂号费用
     */
    @PreAuthorize("@ss.hasPermi('his:resCost:edit')")
    @Log(title = "挂号费用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisRegCost hisRegCost) {
        return toAjax(hisRegCostService.updateHisRegCost(hisRegCost));
    }

    /**
     * 删除挂号费用
     */
    @PreAuthorize("@ss.hasPermi('his:resCost:remove')")
    @Log(title = "挂号费用", businessType = BusinessType.DELETE)
    @DeleteMapping("/{regIds}")
    public AjaxResult remove(@PathVariable Long[] regIds) {
        return toAjax(hisRegCostService.deleteHisRegCostByRegIds(regIds));
    }
}
