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
import com.ruoyi.his.domain.HisMedicine;
import com.ruoyi.his.domain.HisPrescription;
import com.ruoyi.his.service.IHisMedicineService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
@RestController
@RequestMapping("/his/medicine")
public class HisMedicineController extends BaseController {
    @Autowired
    private IHisMedicineService hisMedicineService;

    /**
     * 查询药品管理
     */
    @PreAuthorize("@ss.hasPermi('his:medicine:list')")
    @GetMapping("/list")
    public TableDataInfo list(HisMedicine hisMedicine)
    {
        startPage();
        List<HisMedicine> list = hisMedicineService.selectHisMedicineList(hisMedicine);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('his:medicine:query')")
    @GetMapping("/{medicineId}")
    public AjaxResult getInfo(@PathVariable("medicineId")Long medicineId)
    {

        return AjaxResult.success(hisMedicineService.selectHisMedicineById(medicineId));
    }

    /**
     * 导出药品管理列表
     */
   @PreAuthorize("@ss.hasPermi('his:medicine:export')")
    @Log(title = "药品管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HisMedicine hisMedicine)
    {
        List<HisMedicine> list = hisMedicineService.selectHisMedicineList(hisMedicine);
        ExcelUtil<HisMedicine> util = new ExcelUtil<HisMedicine>(HisMedicine.class);
        util.exportExcel(response, list, "药品管理数据");
    }



    /**
     * 新增药品管理
     */
    @PreAuthorize("@ss.hasPermi('his:medicine:add')")
    @Log(title = "药品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody HisMedicine hisMedicine)
    {//前端传给后端接收以后还是得校验一下
        if(UserConstants.NOT_UNIQUE.equals(hisMedicineService.checkMedicineNameUnique(hisMedicine))){
            return AjaxResult.error("添加药品失败","药品名称重复");
        }else if(UserConstants.NOT_UNIQUE.equals(hisMedicineService.checkMedicineNoUnique(hisMedicine))){
            return AjaxResult.error("添加药品失败","药品编号重复");
        }else if(UserConstants.NOT_UNIQUE.equals(hisMedicineService.checkKeywordUnique(hisMedicine))){
            return AjaxResult.error("添加药品失败","查询关键字重复");
        }
        //添加创建人
        hisMedicine.setCreateBy(SecurityUtils.getUsername());
        //添加创建时间
        hisMedicine.setCreateTime(DateUtils.getNowDate());
        return toAjax(hisMedicineService.insertHisMedicine(hisMedicine));
    }

    /**
     * 修改药品管理
     */
    @PreAuthorize("@ss.hasPermi('his:medicine:edit')")
    @Log(title = "药品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HisMedicine hisMedicine)
    {

        if(UserConstants.NOT_UNIQUE.equals(hisMedicineService.checkMedicineNameUnique(hisMedicine))){
            return AjaxResult.error("修改药品失败:药品名称重复","药品名称重复");
        }else if(UserConstants.NOT_UNIQUE.equals(hisMedicineService.checkMedicineNoUnique(hisMedicine))){
            return AjaxResult.error("修改药品失败:药品编号重复","药品编号重复");
        }else if(UserConstants.NOT_UNIQUE.equals(hisMedicineService.checkKeywordUnique(hisMedicine))){
            return AjaxResult.error("修改药品失败:查询关键字重复","查询关键字重复");
        }

        hisMedicine.setUpdateBy(SecurityUtils.getUsername());
        hisMedicine.setUpdateTime(DateUtils.getNowDate());

        return toAjax(hisMedicineService.updateHisMedicine(hisMedicine));
    }

    /**
     * 删除药品管理
     */
    @PreAuthorize("@ss.hasPermi('his:medicine:remove')")
    @Log(title = "药品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{medicineIds}")
    public AjaxResult remove(@PathVariable Long[] medicineIds)
    {
        String msg="";
        for(Long medicineId:medicineIds){
            boolean flag=false;//每次循环判断该不该删除
            List<HisPrescription> prescriptions = hisMedicineService.selectPrescribeMsg(medicineId);
            if(prescriptions.size()!=0&&prescriptions!=null){
                for(HisPrescription hisPrescription:prescriptions)
                {
                    if(hisPrescription.getDelFlag().equals("0")){//有没有删除的处方使用了该药品
                        if(!msg.equals("")){
                            msg+=",";

                        }

                        msg+="id为"+medicineId+"的药品仍然有未删除的处方在使用";
                        flag=true;
                        break;

                    }
                }
            }


            if(flag==false){//没有未删除的处方使用该药品
                hisMedicineService.deleteHisMedicineByMedicineId(medicineId);
            }


        }


        if(!msg.equals("")){
            return AjaxResult.error(msg);
        }

        else{
//            return toAjax(hisMedicineService.deleteHisMedicineByMedicineIds(medicineIds));

            return AjaxResult.success();
        }

    }
}
