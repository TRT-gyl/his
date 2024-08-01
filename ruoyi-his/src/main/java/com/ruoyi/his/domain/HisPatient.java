package com.ruoyi.his.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 患者对象 his_patient
 * 
 * @author ruoyi
 * @date 2022-08-30
 */
public class HisPatient extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 患者ID */
    private Long patientId;

    /** 患者姓名 */
    @Excel(name = "患者姓名")
    private String patientName;

    /** 患者电话 */
    @Excel(name = "患者电话")
    private String tel;

    /** 患者身份证 */
    @Excel(name = "患者身份证")
    private String idCard;

    /** 患者年龄 */
    @Excel(name = "患者年龄")
    private String age;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 地址信息 */
    @Excel(name = "地址信息")
    private String address;

    /** 患者出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "患者出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date patientDate;

    /** 删除标志（0正常 1删除） */
    private String delFlag;

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    @Excel(name = "过敏史")
    private String allergy;

    public void setPatientId(Long patientId) 
    {
        this.patientId = patientId;
    }

    public Long getPatientId() 
    {
        return patientId;
    }
    public void setPatientName(String patientName) 
    {
        this.patientName = patientName;
    }

    public String getPatientName() 
    {
        return patientName;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setAge(String age) 
    {
        this.age = age;
    }

    public String getAge() 
    {
        return age;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPatientDate(Date patientDate) 
    {
        this.patientDate = patientDate;
    }

    public Date getPatientDate() 
    {
        return patientDate;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    /** 过敏史 */

}
