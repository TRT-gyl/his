package com.ruoyi.his.domain.vo;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author WuShaoYan
 * @date 2022/9/1
 */
public class VoWeek {
    private String type;
    private LocalDate schedDate;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return schedDate;
    }

    public void setDate(LocalDate date) {
        this.schedDate = date;
    }


    @Override
    public String toString() {
        return "VoWeek{" +
                "type=" + type +
                ", schedDate=" + schedDate +
                '}';
    }
}
