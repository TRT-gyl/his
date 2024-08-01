package com.ruoyi.his.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.his.domain.HisPrescription;
import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.domain.HisSupplier;
import com.ruoyi.his.service.IHisSupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/his/supplier")
public class HisSupplierController extends BaseController {
    @Autowired
    private IHisSupplierService hisSupplierService;

    /**
     * 查询供应商管理列表
     */
    @PreAuthorize("@ss.hasPermi('his:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisSupplier hisSupplier)
    {
        startPage();
        List<HisSupplier> list = hisSupplierService.selectHisSupplierList(hisSupplier);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('his:supplier:query')")
    @GetMapping("/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId")Long supplierId)
    {

        return AjaxResult.success(hisSupplierService.selectHisSupplierById(supplierId));
    }

    /**
     * 导出供应商管理列表
     */
    @PreAuthorize("@ss.hasPermi('his:supplier:export')")
    @Log(title = "供应商管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisSupplier hisSupplier)
    {
        List<HisSupplier> list = hisSupplierService.selectHisSupplierList(hisSupplier);
        ExcelUtil<HisSupplier> util = new ExcelUtil<HisSupplier>(HisSupplier.class);
        util.exportExcel(response, list, "供应商管理数据");
    }



    /**
     * 新增供应商管理
     */
    @PreAuthorize("@ss.hasPermi('his:supplier:add')")
    @Log(title = "供应商管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HisSupplier hisSupplier)
    {//前端传给后端接收以后还是得校验一下

        String mobileReg="(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}";
        String phoneReg="^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

        if(UserConstants.NOT_UNIQUE.equals(hisSupplierService.checkSupplierNameUnique(hisSupplier))){
            return AjaxResult.error("添加供应商失败:供应商名称重复","供应商名称重复");
        }else if(!Pattern.matches(phoneReg,hisSupplier.getPhone())){
            return AjaxResult.error("添加供应商失败:请输入正确的手机号码","请输入正确的手机号码");
        }else if(!Pattern.matches(mobileReg,hisSupplier.getMobile())){
            return AjaxResult.error("添加供应商失败:请输入正确的电话号码","请输入正确的电话号码");
        }

        //添加创建人
        hisSupplier.setCreateBy(SecurityUtils.getUsername());
        //添加创建时间
        hisSupplier.setCreateTime(DateUtils.getNowDate());
        return toAjax(hisSupplierService.insertHisSupplier(hisSupplier));
    }

    /**
     * 修改供应商管理
     */
    @PreAuthorize("@ss.hasPermi('his:supplier:edit')")
    @Log(title = "供应商管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisSupplier hisSupplier)
    {

        String mobileReg="(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}";
        String phoneReg="^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

        if(UserConstants.NOT_UNIQUE.equals(hisSupplierService.checkSupplierNameUnique(hisSupplier))){
            return AjaxResult.error("修改供应商失败:供应商名称重复","供应商名称重复");
        }else if(!Pattern.matches(phoneReg,hisSupplier.getPhone())){
            return AjaxResult.error("修改供应商失败:请输入正确的手机号码","请输入正确的手机号码");
        }else if(!Pattern.matches(mobileReg,hisSupplier.getMobile())){
            return AjaxResult.error("修改供应商失败:请输入正确的电话号码","请输入正确的电话号码");
        }

        hisSupplier.setUpdateBy(SecurityUtils.getUsername());
        hisSupplier.setUpdateTime(DateUtils.getNowDate());

        return toAjax(hisSupplierService.updateHisSupplier(hisSupplier));
    }

    /**
     * 删除供应商管理
     */
    @PreAuthorize("@ss.hasPermi('his:supplier:remove')")
    @Log(title = "供应商管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {

        String msg="";
        for(Long supplierId:supplierIds){
            boolean flag=false;//每次循环判断该不该删除
            List<HisPurchasWarehousing> purchasWarehousings = hisSupplierService.selectPwMsg(supplierId);
            if(purchasWarehousings.size()!=0&&purchasWarehousings!=null){
                for(HisPurchasWarehousing hisPurchasWarehousing:purchasWarehousings)
                {
                    if(hisPurchasWarehousing.getStatus().equals("2")){//有入库成功的不能删除
                        if(!msg.equals("")){
                            msg+=",";

                        }

                        msg+="id为"+supplierId+"的药品仍然有未删除的处方在使用";
                        flag=true;
                        break;

                    }
                }
            }


            if(flag==false){//没有未删除的处方使用该药品就可以删除
                hisSupplierService.deleteHisSupplierBySupplierId(supplierId);
            }


        }


        if(!msg.equals("")){
            return AjaxResult.error(msg);
        }

        else{
//            return toAjax(hisMedicineService.deleteHisMedicineByMedicineIds(medicineIds));

            return AjaxResult.success();
        }
//        return toAjax(hisSupplierService.deleteHisSupplierBySupplierIds(supplierIds));
    }
}
