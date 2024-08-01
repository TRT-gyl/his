package com.ruoyi.his.service.impl;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ruoyi.his.domain.vo.VoSchedule;
import com.ruoyi.his.domain.vo.VoWeek;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.his.mapper.HisScheduleMapper;
import com.ruoyi.his.domain.HisSchedule;
import com.ruoyi.his.service.IHisScheduleService;
import sun.util.resources.LocaleData;

/**
 * 排班表视图Service业务层处理
 *
 * @author 吴绍艳
 * @date 2022-09-01
 */

@Service
public class HisScheduleServiceImpl implements IHisScheduleService {
    @Autowired
    private HisScheduleMapper hisScheduleMapper;

    /**
     * 查询可挂号列表
     *
     * @param hisSchedule
     * @return
     */
    @Override
    public List<HisSchedule> selectSchedule(HisSchedule hisSchedule) {
        LocalDate today = LocalDate.now();
        if (hisSchedule.getScheduleDate()==null||hisSchedule.getScheduleDate().before(convertD(today))){
            hisSchedule.setScheduleDate(convertD(today));
        }
        return hisScheduleMapper.getAll(hisSchedule);
    }

    /**
     * 查询排班表
     *
     * @param hisSchedule 查询条件
     * @return 排班表视图
     */
    @Override
    public List<VoSchedule> selectVSchedDoctorDeptList(HisSchedule hisSchedule) {
        if (hisSchedule.getMonday() == null) {//第一次查询就是查询当前周的数据
            hisSchedule.setMonday(convertD(getMonday()));
        } else {
            hisSchedule.setMonday(new java.sql.Date(hisSchedule.getMonday().getTime()));
        }
        LocalDate monday = convertL(new Date(hisSchedule.getMonday().getTime()));//周一
        hisSchedule.setSunday(monday.minusDays(-6));//周日
        List<HisSchedule> hisSchedules = hisScheduleMapper.getByCondition(hisSchedule);//查周一到周日的数据
        List<VoSchedule> voSchedulelist = new ArrayList<>();//封装传递到前端的数据
        for (HisSchedule schedule : hisSchedules) {
            VoSchedule voSchedule = new VoSchedule();
            voSchedule.setMonday(convertD(monday));
            voSchedule.setName(schedule.getDoctName());//排班医生
            voSchedule.setDept(schedule.getDeptName());//所属科室
            voSchedule.setScheduleTime(schedule.getScheduleTime());//排班时段
            //判断日期属于周一-周日的哪天，然后封装
            String scheduleDate = trans(schedule.getScheduleDate());
            if (scheduleDate.equals(monday.toString())) {
                VoWeek voWeek = new VoWeek();
                voWeek.setDate(convertL(schedule.getScheduleDate()));
                voWeek.setType(schedule.getScheduleType());
                voSchedule.setWeek1(voWeek);
            } else if (scheduleDate.equals(monday.minusDays(-1).toString())) {
                VoWeek voWeek = new VoWeek();
                voWeek.setDate(convertL(schedule.getScheduleDate()));
                voWeek.setType(schedule.getScheduleType());
                voSchedule.setWeek2(voWeek);
            } else if (scheduleDate.equals(monday.minusDays(-2).toString())) {
                VoWeek voWeek = new VoWeek();
                voWeek.setDate(convertL(schedule.getScheduleDate()));
                voWeek.setType(schedule.getScheduleType());
                voSchedule.setWeek3(voWeek);
            } else if (scheduleDate.equals(monday.minusDays(-3).toString())) {
                VoWeek voWeek = new VoWeek();
                voWeek.setDate(convertL(schedule.getScheduleDate()));
                voWeek.setType(schedule.getScheduleType());
                voSchedule.setWeek4(voWeek);
            } else if (scheduleDate.equals(monday.minusDays(-4).toString())) {
                VoWeek voWeek = new VoWeek();
                voWeek.setDate(convertL(schedule.getScheduleDate()));
                voWeek.setType(schedule.getScheduleType());
                voSchedule.setWeek5(voWeek);
            } else if (scheduleDate.equals(monday.minusDays(-5).toString())) {
                VoWeek voWeek = new VoWeek();
                voWeek.setDate(convertL(schedule.getScheduleDate()));
                voWeek.setType(schedule.getScheduleType());
                voSchedule.setWeek6(voWeek);
            } else if (scheduleDate.equals(monday.minusDays(-6).toString())) {
                VoWeek voWeek = new VoWeek();
                voWeek.setDate(convertL(schedule.getScheduleDate()));
                voWeek.setType(schedule.getScheduleType());
                voSchedule.setWeek7(voWeek);
            }
            voSchedulelist.add(voSchedule);
        }
        return voSchedulelist;
    }


    /**
     * 修改排班表视图
     *
     * @param hisSchedule 排班表视图
     * @return 结果
     */
    @Override
    public int updateVSchedDoctorDept(HisSchedule hisSchedule) {
        return hisScheduleMapper.update(hisSchedule);
    }

    //根据排版id查详情
    @Override
    public HisSchedule selectScheduleByScheduleId(Long scheduleId) {
       return hisScheduleMapper.selectScheduleByScheduleId(scheduleId);
    }

    /**
     * 查询本周周一日期
     */
    public static LocalDate getMonday() {
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return monday;
    }

    /**
     * LocalDate转Date
     *
     * @param localdate
     * @return
     */
    public static Date convertD(LocalDate localdate) {
        return Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转LocalDate
     *
     * @param d
     * @return
     */
    public static LocalDate convertL(Date d) {
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date格式化为’yyyy-MM-dd‘
     *
     * @param date
     * @return
     */
    private static String trans(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//定义新的日期格式
        return formatter.format(date);
    }


}
