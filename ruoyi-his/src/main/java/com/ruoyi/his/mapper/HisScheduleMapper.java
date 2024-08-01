package com.ruoyi.his.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.his.domain.HisDoctor;
import com.ruoyi.his.domain.HisSchedule;
import com.ruoyi.his.domain.vo.VoSchedule;
import com.ruoyi.his.domain.vo.VoWeek;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

/**
 * 排班表Mapper接口
 *
 * @author 吴绍艳
 * @date 2022-09-01
 */
public interface HisScheduleMapper   {

    /**
     * 按周和条件查询排班表列表
     *
     * @param hisSchedule 查询条件
     * @return 排班表集合
     */
    @SelectProvider(type = SelectSql.class, method = "getByCondition")
    @Results({@Result(property = "scheduleId", column = "schedule_id"),
            @Result(property = "doctId", column = "schedule_docter_id"),
            @Result(property = "scheduleDate", column = "schedule_date"),
            @Result(property = "scheduleTime", column = "schedule_time"),
            @Result(property = "scheduleNums", column = "schedule_nums"),
            @Result(property = "scheduleType", column = "schedule_type"),
            @Result(property = "doctName", column = "doct_name"),
            @Result(property = "deptName", column = "dept_name"),
    })
    public List<HisSchedule> getByCondition(HisSchedule hisSchedule);

    //条件查询
    public static class SelectSql {
        public String getByCondition(HisSchedule hisSchedule) {
            return new SQL() {{
                SELECT("*");
                FROM("v_sched_doctor_dept");
                WHERE("schedule_date BETWEEN #{monday} AND #{sunday}");
                if (hisSchedule.getDeptName() != null) {
                    WHERE("dept_name  like '%" + hisSchedule.getDeptName() + "%' ");
                }
                if (hisSchedule.getDoctName() != null) {
                    WHERE("doct_name  like '%" + hisSchedule.getDoctName() + "%' ");
                }
                if (hisSchedule.getDoctId()!=null){
                    WHERE("schedule_docter_id=#{doctId}");
                }
            }}.toString();
        }
    }
    @SelectProvider(type = SelectAllSql.class, method = "getByCondition")
    @Results({@Result(property = "scheduleId", column = "schedule_id"),
            @Result(property = "doctId", column = "schedule_docter_id"),
            @Result(property = "scheduleDate", column = "schedule_date"),
            @Result(property = "scheduleTime", column = "schedule_time"),
            @Result(property = "scheduleNums", column = "schedule_nums"),
            @Result(property = "scheduleType", column = "schedule_type"),
            @Result(property = "doctName", column = "doct_name"),
            @Result(property = "deptName", column = "dept_name"),
    })
    public List<HisSchedule> getAll(HisSchedule hisSchedule);

    public static class SelectAllSql {
        public String getByCondition(HisSchedule hisSchedule) {
            return new SQL() {{
                SELECT("*");
                FROM("v_sched_doctor_dept");
                WHERE("schedule_type != '休'");
                if (hisSchedule.getDoctId()!=null){
                    WHERE("schedule_docter_id=#{doctId}");
                }
                if (hisSchedule.getScheduleType()!=null){
                    WHERE("schedule_type=#{scheduleType}");
                }
                if (hisSchedule.getScheduleTime()!=null){
                    WHERE("schedule_time=#{scheduleTime}");
                }
                if (hisSchedule.getScheduleDate()!=null){
                    WHERE("schedule_date=#{scheduleDate}");
                }
                if (hisSchedule.getScheduleId()!=null){
                    WHERE("schedule_Id=#{scheduleId}");
                }
            }}.toString();
        }
    }
    /**
     * 修改排班表
     *
     * @param hisSchedule 排班表
     * @return 结果
     */
    @UpdateProvider(type = UpdateSql.class, method = "update")
    public int update(HisSchedule hisSchedule);

    public static class UpdateSql {
        public String update(HisSchedule hisSchedule) {
            return new SQL() {{
                UPDATE("v_sched_doctor_dept");
                if (hisSchedule.getScheduleTime()!= null) {
                    SET("schedule_time = #{scheduleTime}");
                }
                if (hisSchedule.getScheduleNums() != null) {
                    SET("schedule_nums = #{scheduleNums}");
                }
                if (hisSchedule.getScheduleType() != null) {
                    SET("schedule_type = #{scheduleType}");
                }
                WHERE("schedule_id = #{scheduleId}");
            }}.toString();
        }
    }


    /**
     * 查询排班表视图
     *
     * @param scheduleId 排班表视图主键
     * @return 排班表视图
     */
    public HisSchedule selectScheduleByScheduleId(Long scheduleId);

}
