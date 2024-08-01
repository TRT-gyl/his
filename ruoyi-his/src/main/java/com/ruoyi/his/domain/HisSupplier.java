package com.ruoyi.his.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HisSupplier extends BaseEntity {
    private Long supplierId;

    @Excel(name = "供应商名称")
    @NotBlank(message = "供应商名称不能为空")
    @Size(min = 0,max=20,message = "供应商名称不能超过20字")
    private String supplierName;


    @Excel(name = "联系人")
    @NotBlank(message = "供应商联系人不能为空")
    @Size(min=0,max = 4,message = "联系人姓名不能超过4位")
    private String linkMan;

    @Excel(name = "固定电话")
    @NotBlank(message = "固定电话不能为空")
    private String mobile;

    @Excel(name = "供应商手机")
    @NotBlank(message = "供应商手机不能为空")
    private String phone;

    @Excel(name = "银行账户")
    @NotBlank(message = "银行账号不能为空")
    @Size(min = 0,max=19,message = "银行账号不能超过19位")
    private String account;

    @Excel(name = "地址")
    @NotBlank(message = "地址不能为空")
    private String address;

    private String status;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "HisSupplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", linkMan='" + linkMan + '\'' +
                ", mobile='" + mobile + '\'' +
                ", phone='" + phone + '\'' +
                ", account='" + account + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
