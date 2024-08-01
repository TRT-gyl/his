package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HisFactory extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /** 厂家序号 */
//    @Excel(name = "厂家序号", cellType = Excel.ColumnType.NUMERIC)
    private Long factoryId;

    /** 厂家名称 */
    @Excel(name = "厂家名称")
    @NotBlank(message = "厂家名称不能为空")
    @Size(min = 0,max=20,message = "厂家名称不能超过20字")
    private String factoryName;

    /** 厂家编号 */
    @Excel(name = "厂家编号")
    @NotBlank(message = "厂家编号不能为空")
    @Size(min=0,max = 7,message = "厂家编号不能超过7位")
    private String factoryNo;

    /** 厂家联系人 */
    @Excel(name = "厂家联系人")
    @NotBlank(message = "联系人不能为空")
    @Size(min=0,max = 4,message = "联系人姓名不能超过4位")
    private String linkMan;

    /** 厂家电话 */
    @Excel(name = "电话")
    @NotBlank(message = "联系人电话不能为空")
    private String mobile;

    /** 厂家地址 */
    @Excel(name = "厂家地址")
    @NotBlank(message = "地址不能为空")
    private String address;

    /** 查询关键字 */
    @Excel(name = "查询关键字")
    @NotBlank(message = "关键字不能为空")
    @Size(min=0,max=4,message = "查询关键字不超过4位")
    private String keyword;


    /** 厂家状态:0正常,1停用 */
    private String status;


    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryNo() {
        return factoryNo;
    }

    public void setFactoryNo(String factoryNo) {
        this.factoryNo = factoryNo;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "HisFactory{" +
                "factoryId=" + factoryId +
                ", factoryName='" + factoryName + '\'' +
                ", factoryNo='" + factoryNo + '\'' +
                ", linkMan='" + linkMan + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", keyword='" + keyword + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
