package com.ruoyi.his.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * 挂号费用对象 his_reg_cost
 *
 * @author apr_jay
 * @date 2022-08-30
 */
public class HisRegCost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 挂号费用ID
     */
    private Long regId;

    /**
     * 挂号费用名称
     */
    @NotBlank(message = "挂号费用名称不能为空")
    private String regName;

    /**
     * 挂号费
     */

    private BigDecimal regPrice;

    /**
     * 状态
     */

    private String status;

    public void setRegId(Long regId) {
        this.regId = regId;
    }

    public Long getRegId() {
        return regId;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegPrice(BigDecimal regPrice) {
        this.regPrice = regPrice;
    }

    public BigDecimal getRegPrice() {
        return regPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("regId", getRegId())
                .append("regName", getRegName())
                .append("regPrice", getRegPrice())
                .append("status", getStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
