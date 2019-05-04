package com.jeecg.system_management.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物资信息管理Pojo
 * @author 王国强
 * @table REP_SUBSTANCE_MESSAGE
 */
@Entity
@Table(name = "REP_SUBSTANCE_MESSAGE")
public class RepSubstanceMessage {
    /**
    *  主键id
    */

    private String uuid;
    /**
    *  物资编号
    */
    private String subNumber;
    /**
    *  物资名称
    */
    private String subName;
    /**
    *  物资类别
    */
    private String subCategory;
    /**
    *规格型号
    */
    private String specificationType;
    /**
    *  是否可回收
    */
    private Short isrecycle;
    /**
    *  供应商
    */
    private String supplier;
    /**
    * 价格
    */
    private BigDecimal price;
    /**
    * 备注
    */
    private String remark;
    /**
     * 创建人姓名
     */
    private String creartName;
    /**
    * 创建人登录名称
    */
    private String createBy;
    /**
    * 创建日期
    */
    private Date createDate;
    /**
    * 更新人名称
    */
    private String updateName;
    /**
    * 更新人登录名称
    */
    private String updateBy;
    /**
    * 更新日期
    */
    private Date updateDate;
    /**
    *  所属公司
    */
    private String sysCompanyCode;
    /**
    * 所属部门
    */
    private String sysOrgCode;
    /**
    * 是否删除
    */
    private Short isdeleted;
    /**
    * 单位
    */
    private String unit;
    /**
    * 流程状态
    */
    private String bpmStatus;
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "UUID", length = 36)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }
    @Column(name = "SUB_NUMBER", length = 36, nullable = false)
    public String getSubNumber() {
        return subNumber;
    }

    public void setSubNumber(String subNumber) {
        this.subNumber = subNumber == null ? null : subNumber.trim();
    }
    @Column(name = "SUB_NAME", length = 36, nullable = false)
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }
    @Column(name = "SUB_CATEGORY", length = 36, nullable = false)
    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory == null ? null : subCategory.trim();
    }
    @Column(name = "SPECIFICATION_TYPE", length = 36)
    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType == null ? null : specificationType.trim();
    }
    @Column(name = "ISRECYCLE", length = 1)
    public Short getIsrecycle() {
        return isrecycle;
    }

    public void setIsrecycle(Short isrecycle) {
        this.isrecycle = isrecycle;
    }
    @Column(name = "SUPPLIER", length = 36)
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }
    @Column(name = "PRICE", length = 18)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Column(name = "REMARK", length = 36)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    @Column(name = "CREART_NAME", length = 36)
    public String getCreartName() {
        return creartName;
    }

    public void setCreartName(String creartName) {
        this.creartName = creartName == null ? null : creartName.trim();
    }
    @Column(name = "CREATE_BY", length = 36)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name = "UPDATE_NAME", length = 36)
    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }
    @Column(name = "UPDATE_BY", length = 36)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
    @Column(name = "UPDATE_DATE")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    @Column(name = "SYS_COMPANY_CODE", length = 36)
    public String getSysCompanyCode() {
        return sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode == null ? null : sysCompanyCode.trim();
    }
    @Column(name = "SYS_ORG_CODE", length = 36)
    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode == null ? null : sysOrgCode.trim();
    }
    @Column(name = "ISDELETED", length = 1)
    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
    }
    @Column(name = "UNIT", length = 36)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
    @Column(name = "BPM_STATUS", length = 36)
    public String getBpmStatus() {
        return bpmStatus;
    }

    public void setBpmStatus(String bpmStatus) {
        this.bpmStatus = bpmStatus == null ? null : bpmStatus.trim();
    }
}