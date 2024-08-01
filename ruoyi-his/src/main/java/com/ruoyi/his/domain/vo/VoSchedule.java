package com.ruoyi.his.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * 封装班表每周的信息
 *
 * @author WuShaoYan
 * @date 2022/9/1
 */

public class VoSchedule {
    private String name;
    private String dept;
    private Long scheduleTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date monday;
    private VoWeek voWeek1;
    private VoWeek voWeek2;
    private VoWeek voWeek3;
    private VoWeek voWeek4;
    private VoWeek voWeek5;
    private VoWeek voWeek6;
    private VoWeek voWeek7;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Long getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }


    public VoWeek getWeek1() {
        return voWeek1;
    }

    public void setWeek1(VoWeek voWeek1) {
        this.voWeek1 = voWeek1;
    }

    public VoWeek getWeek2() {
        return voWeek2;
    }

    public void setWeek2(VoWeek voWeek2) {
        this.voWeek2 = voWeek2;
    }

    public VoWeek getWeek3() {
        return voWeek3;
    }

    public void setWeek3(VoWeek voWeek3) {
        this.voWeek3 = voWeek3;
    }

    public VoWeek getWeek4() {
        return voWeek4;
    }

    public void setWeek4(VoWeek voWeek4) {
        this.voWeek4 = voWeek4;
    }

    public VoWeek getWeek5() {
        return voWeek5;
    }

    public void setWeek5(VoWeek voWeek5) {
        this.voWeek5 = voWeek5;
    }

    public VoWeek getWeek6() {
        return voWeek6;
    }

    public void setWeek6(VoWeek voWeek6) {
        this.voWeek6 = voWeek6;
    }

    public VoWeek getWeek7() {
        return voWeek7;
    }

    public void setWeek7(VoWeek voWeek7) {
        this.voWeek7 = voWeek7;
    }

    public Date getMonday() {
        return monday;
    }

    public void setMonday(Date monday) {
        this.monday = monday;
    }

    @Override
    public String toString() {
        return "VoSchedule{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", scheduleTime=" + scheduleTime +
                ", monday=" + monday +
                ", voWeek1=" + voWeek1 +
                ", voWeek2=" + voWeek2 +
                ", voWeek3=" + voWeek3 +
                ", voWeek4=" + voWeek4 +
                ", voWeek5=" + voWeek5 +
                ", voWeek6=" + voWeek6 +
                ", voWeek7=" + voWeek7 +
                '}';
    }
}
