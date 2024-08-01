package com.ruoyi.his.domain.vo;

import com.ruoyi.his.domain.HisPurchasWarehousing;
import com.ruoyi.his.domain.HisPurchase;
import lombok.Data;

import java.util.List;

@Data
public class VoPurchaseOrder {
    private HisPurchasWarehousing hisPurchasWarehousing;
    private List<HisPurchase> hisPurchaseList;
}
