package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisSchedule;
import com.ruoyi.his.domain.vo.VoSchedule;
import com.ruoyi.his.domain.vo.VoWeek;
import org.apache.ibatis.jdbc.SQL;

import java.util.Date;
import java.util.List;

/**
 * 排班表视图Service接口
 *
 * @author 吴绍艳
 * @date 2022-09-01
 */
public interface IHisScheduleService {
    /**
     * 查询可挂号列表
     *
     * @param hisSchedule
     * @return
     */
    public List<HisSchedule> selectSchedule(HisSchedule hisSchedule);

    /**
     * 查询排班表视图列表
     *
     * @param hisSchedule 排班表视图
     * @return 排班表视图集合
     */
    public List<VoSchedule> selectVSchedDoctorDeptList(HisSchedule hisSchedule);


    /**
     * 修改排班表视图
     *
     * @param hisSchedule 排班表视图
     * @return 结果
     */
    public int updateVSchedDoctorDept(HisSchedule hisSchedule);



    /**
     * 查询排班表视图
     *
     * @param scheduleId 排班表视图主键
     * @return
     */
    public HisSchedule selectScheduleByScheduleId(Long scheduleId);
}
