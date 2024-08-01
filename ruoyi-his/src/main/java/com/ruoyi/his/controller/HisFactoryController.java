package com.ruoyi.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.HisFactory;
import com.ruoyi.his.domain.HisFactory;
import com.ruoyi.his.service.IHisFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/his/factory")
public class HisFactoryController extends BaseController {
    @Autowired
    private IHisFactoryService hisFactoryService;

    /**
     * 查询厂家管理列表
     */
    @PreAuthorize("@ss.hasPermi('his:factory:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisFactory hisFactory)
    {
        startPage();
        List<HisFactory> list = hisFactoryService.selectHisFactoryList(hisFactory);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('his:factory:query')")
    @GetMapping("/{factoryId}")
    public AjaxResult getInfo(@PathVariable("factoryId")Long factoryId)
    {

        return AjaxResult.success(hisFactoryService.selectHisFactoryById(factoryId));
    }

    /**
     * 导出厂家管理列表
     */
    @PreAuthorize("@ss.hasPermi('his:factory:export')")
    @Log(title = "厂家管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisFactory hisFactory)
    {
        List<HisFactory> list = hisFactoryService.selectHisFactoryList(hisFactory);
        ExcelUtil<HisFactory> util = new ExcelUtil<HisFactory>(HisFactory.class);
        util.exportExcel(response, list, "厂家管理数据");
    }



    /**
     * 新增厂家管理
     */
    @PreAuthorize("@ss.hasPermi('his:factory:add')")
    @Log(title = "厂家管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated  @RequestBody HisFactory hisFactory)
    {//前端传给后端接收以后还是得校验一下
        String mobileReg="(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}";


       if(UserConstants.NOT_UNIQUE.equals(hisFactoryService.checkFactoryNameUnique(hisFactory))){
           return AjaxResult.error("添加厂家失败:厂家名称重复","厂家名称重复");
       }else if(UserConstants.NOT_UNIQUE.equals(hisFactoryService.checkFactoryNoUnique(hisFactory))){
           return AjaxResult.error("添加厂家失败:厂家编号重复","厂家编号重复");
       }else if(UserConstants.NOT_UNIQUE.equals(hisFactoryService.checkKeywordUnique(hisFactory))){
           return AjaxResult.error("添加厂家失败:查询关键字重复","查询关键字重复");
       } else if(!Pattern.matches(mobileReg,hisFactory.getMobile())){
           return AjaxResult.error("修改厂家失败:请输入正确的电话号码","请输入正确的电话号码");
       }

       //添加创建人
        hisFactory.setCreateBy(SecurityUtils.getUsername());
       //添加创建时间
        hisFactory.setCreateTime(DateUtils.getNowDate());
        return toAjax(hisFactoryService.insertHisFactory(hisFactory));
    }

    /**
     * 修改厂家管理
     */
    @PreAuthorize("@ss.hasPermi('his:factory:edit')")
    @Log(title = "厂家管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisFactory hisFactory)
    {
        String mobileReg="(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}";


        if(UserConstants.NOT_UNIQUE.equals(hisFactoryService.checkFactoryNameUnique(hisFactory))){
            return AjaxResult.error("修改厂家失败:厂家名称重复","厂家名称重复");
        }else if(UserConstants.NOT_UNIQUE.equals(hisFactoryService.checkFactoryNoUnique(hisFactory))){
            return AjaxResult.error("修改厂家失败:厂家编号重复","厂家编号重复");
        }else if(UserConstants.NOT_UNIQUE.equals(hisFactoryService.checkKeywordUnique(hisFactory))){
            return AjaxResult.error("修改厂家失败:查询关键字重复","查询关键字重复");
        }
        else if(!Pattern.matches(mobileReg,hisFactory.getMobile())){
            return AjaxResult.error("修改厂家失败:请输入正确的电话号码","请输入正确的电话号码");
        }

        hisFactory.setUpdateBy(SecurityUtils.getUsername());
        hisFactory.setUpdateTime(DateUtils.getNowDate());

        return toAjax(hisFactoryService.updateHisFactory(hisFactory));
    }

    /**
     * 删除厂家
     */
    @PreAuthorize("@ss.hasPermi('his:factory:remove')")
    @Log(title = "厂家管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{factoryIds}")
    public AjaxResult remove(@PathVariable Long[] factoryIds)
    {
        return toAjax(hisFactoryService.deleteHisFactoryByFactoryIds(factoryIds));
    }
    
}
