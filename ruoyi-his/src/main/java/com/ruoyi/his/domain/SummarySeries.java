package com.ruoyi.his.domain;

import lombok.Data;

import java.util.List;

@Data
public class SummarySeries {
    private List<String> dateSeries;
    private List<RevenuSummary> revenuSeries;
}
