package com.ruoyi.his.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.MedicineSaleRecords;
import com.ruoyi.his.service.IMedicineSaleRecordsService;
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
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * VIEWController
 * 
 * @author ruoyi
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/his/statistic/medicine")
public class MedicineSaleRecordsController extends BaseController
{
    @Autowired
    private IMedicineSaleRecordsService medicineSaleRecordsService;

    /**
     * 查询药品销售记录
     */
    @PreAuthorize("@ss.hasPermi('his:statistic:medicine')")
    @GetMapping("/saleRecords")
    public TableDataInfo listSaleRecords(MedicineSaleRecords medicineSaleRecords)
    {
        logger.debug("medicineSaleRecords:"+medicineSaleRecords);
        startPage();
        List<MedicineSaleRecords> list = medicineSaleRecordsService.selectMedicineSaleRecordsList(medicineSaleRecords);
        return getDataTable(list);
    }
    /**
     * 查询药品统计记录
     */
    @PreAuthorize("@ss.hasPermi('his:statistic:medicine')")
    @GetMapping("/statisticRecords")
    public TableDataInfo listStatisticRecords(MedicineSaleRecords medicineSaleRecords)
    {
        startPage();
        List<MedicineSaleRecords> list = medicineSaleRecordsService.selectMedicineStatisticRecords(medicineSaleRecords);
        return getDataTable(list);
    }


}
