package com.ruoyi.his.domain;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ruoyi.common.core.domain.BaseEntity;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 排班表
 *
 * @author 吴绍艳
 * @date 2022-09-01
 */
public class HisSchedule extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 排班ID
     */
    private Long scheduleId;
    /**
     * 医生名称
     */
    private String doctName;

    /**
     * 医生ID
     */
    private Long doctId;
    /**
     * 所属科室
     */

    private String deptName;

    /**
     * 排班日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date scheduleDate;
    /**
     * 排班时段（0上午 1下午 2晚上）
     */
    private Long scheduleTime;
    /**
     * 号源（新增初始化为50）
     */
    private String scheduleNums;
    /**
     * 挂号类型
     */
    private String scheduleType;
    /**
     * 周日
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private LocalDate sunday;
    /**
     * 周一
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date monday;

    @Override
    public String toString() {
        return "HisSchedule{" +
                "scheduleId=" + scheduleId +
                ", doctName='" + doctName + '\'' +
                ", doctId=" + doctId +
                ", deptName='" + deptName + '\'' +
                ", scheduleDate=" + scheduleDate +
                ", scheduleTime=" + scheduleTime +
                ", scheduleNums=" + scheduleNums +
                ", scheduleType=" + scheduleType +
                ", sunday=" + sunday +
                ", monday=" + monday +
                '}';
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDoctName() {
        return doctName;
    }

    public void setDoctName(String doctName) {
        this.doctName = doctName;
    }

    public Long getDoctId() {
        return doctId;
    }

    public void setDoctId(Long doctId) {
        this.doctId = doctId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public Long getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleNums() {
        return scheduleNums;
    }

    public void setScheduleNums(String scheduleNums) {
        this.scheduleNums = scheduleNums;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public LocalDate getSunday() {
        return sunday;
    }

    public void setSunday(LocalDate sunday) {
        this.sunday = sunday;
    }

    public Date getMonday() {
        return monday;
    }

    public void setMonday(Date monday) {
        this.monday = monday;
    }


}
