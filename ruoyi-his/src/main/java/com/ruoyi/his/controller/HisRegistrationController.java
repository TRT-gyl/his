package com.ruoyi.his.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.his.domain.*;
import com.ruoyi.his.domain.vo.VoReg;
import com.ruoyi.his.domain.vo.VoTree;
import com.ruoyi.his.exception.NoRegistrationNumException;
import com.ruoyi.his.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 挂号Controller
 *
 * @author ruoyi
 * @date 2022-08-30
 */
@RestController
@RequestMapping("/his/registration")
public class HisRegistrationController extends BaseController {
    @Autowired
    private IHisRegistrationService hisRegistrationService;
    @Autowired
    private IHisDeptService hisDeptService;
    @Autowired
    private IHisDoctorService iHisDoctorService;
    @Autowired
    private IHisScheduleService iHisScheduleService;



    /**
     * 查询可以挂号的医生列表
     */
    @PreAuthorize("@ss.hasPermi('his:registration:list')")
    @GetMapping("/listRegable")
    public TableDataInfo list(HisSchedule hisSchedule) {
        startPage();
        List<HisSchedule> list = iHisScheduleService.selectSchedule(hisSchedule);
        return getDataTable(list);
    }


    /**
     * 查询挂号列表
     */
    @PreAuthorize("@ss.hasPermi('his:registration:list')")
    @GetMapping("/list")
    public TableDataInfo list(VoReg voReg) {
        startPage();
        List<VoReg> list = hisRegistrationService.selectHisRegistrationList(voReg);
        return getDataTable(list);
    }

    /**
     * 获取挂号详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:registration:query')")
    @GetMapping(value = "/{registrationId}")
    public AjaxResult getInfo(@PathVariable("registrationId") Long registrationId) {
        return AjaxResult.success(hisRegistrationService.selectHisRegistrationByRegistrationId(registrationId));
    }
    /**
     * 获取挂号详细信息
     */
    @PreAuthorize("@ss.hasPermi('his:registration:query')")
    @GetMapping(value = "/andtreatno/{registrationId}")
    public AjaxResult get(@PathVariable("registrationId") Long registrationId) {
        HisTreat hisTreat = hisRegistrationService.selectRegAndTreatNoByRegId(registrationId);
        return AjaxResult.success(hisTreat);
    }

    /**
     * 封装科室，医生的级联下拉框数据
     */
    @PreAuthorize("@ss.hasPermi('his:registration:list')")
    @GetMapping("/listdept")
    public AjaxResult listIncludeDoct() {
        List<HisDept> deptList = hisDeptService.selectHisDeptList(null);
        List<HisDoctor> doctorList = iHisDoctorService.selectHisDoctorList(null);

        List<VoTree> trees = new ArrayList<>();
        for (HisDept hisDept : deptList) {
            VoTree voTree = new VoTree();
            voTree.setId(hisDept.getDeptId());
            voTree.setName(hisDept.getDeptName());
            List<VoTree> children = new ArrayList<>();
            for (HisDoctor doctor : doctorList) {
                if (doctor.getDeptId() == hisDept.getDeptId()) {
                    VoTree child = new VoTree();
                    child.setId(doctor.getDoctId());
                    child.setName(doctor.getDoctName());
                    children.add(child);
                }
            }
            voTree.setChildren(children);
            trees.add(voTree);
        }
        return AjaxResult.success(trees);
    }

    /**
     * 新增挂号
     */
    @PreAuthorize("@ss.hasPermi('his:registration:add')")
    @Log(title = "挂号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HisRegistration hisRegistration) {
        String no = hisRegistrationService.insertHisRegistration(hisRegistration);
        return AjaxResult.success("挂号成功", no);
    }

    /**
     * 修改挂号
     */
    @PreAuthorize("@ss.hasPermi('his:registration:edit')")
    @Log(title = "挂号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VoReg voReg) {
        return toAjax(hisRegistrationService.updateHisRegistration(voReg));
    }

    /**
     * 删除挂号
     */
    @PreAuthorize("@ss.hasPermi('his:registration:remove')")
    @Log(title = "挂号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{registrationIds}")
    public AjaxResult remove(@PathVariable Long[] registrationIds) {
        return toAjax(hisRegistrationService.deleteHisRegistrationByRegistrationIds(registrationIds));
    }


    /**
     * 无号源的异常出来
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoRegistrationNumException.class)
    public AjaxResult handlerExeption(Exception e) {
        e.printStackTrace();
        return AjaxResult.success("挂号失败", 0);
    }

}
