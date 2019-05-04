package com.jeecg.MaterialsOutPut.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 甄磊超
 * 物资出库详细信息
 */
@Entity
@Table(name = "REP_OUTHOUSE_DETAIL")
public class RepOuthouseDetail implements Serializable {
    /**
     * 主键编号
     */
    private String uuid;

    /**
     * 物资出库id
     */
    private String outhouseid;

    /**
     * 物资信息id
     */
    private String messageid;

    /**
     * 物资出库详细主键
     */
    private String storageDetail;


    /**
     * 出库数量
     */
    private Long outhouseAmount;

    /**
     * 是否可回收
     */
    private Short isrecycle;

    /**
     * 出库仓库
     */
    private String outhouseWarehouse;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人姓名
     */
    private String createName;

    /**
     * 创建人登录名称
     */
    private String createBy;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新人姓名
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
     * 所属公司编号
     */
    private String sysCompanyCode;

    /**
     * 所属部门编号
     */
    private String sysOrgCode;

    /**
     * 是否被删除
     */
    private Short isdeleted;

    /**
     * 流程状态
     */
    private String bpmStatus;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "UUID")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    @Column(name = "OUTHOUSEID")
    public String getOuthouseid() {
        return outhouseid;
    }

    public void setOuthouseid(String outhouseid) {
        this.outhouseid = outhouseid == null ? null : outhouseid.trim();
    }

    @Column(name = "MESSAGEID")
    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    @Column(name = "OUTHOUSE_AMOUNT")
    public Long getOuthouseAmount() {
        return outhouseAmount;
    }

    public void setOuthouseAmount(Long outhouseAmount) {
        this.outhouseAmount = outhouseAmount;
    }

    @Column(name = "ISRECYCLE")
    public Short getIsrecycle() {
        return isrecycle;
    }

    public void setIsrecycle(Short isrecycle) {
        this.isrecycle = isrecycle;
    }

    @Column(name = "OUTHOUSE_WAREHOUSE")
    public String getOuthouseWarehouse() {
        return outhouseWarehouse;
    }

    public void setOuthouseWarehouse(String outhouseWarehouse) {
        this.outhouseWarehouse = outhouseWarehouse == null ? null : outhouseWarehouse.trim();
    }

    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Column(name = "CREATE_NAME")
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    @Column(name = "CREATE_BY")
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

    @Column(name = "UPDATE_NAME")
    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    @Column(name = "UPDATE_BY")
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

    @Column(name = "SYS_COMPANY_CODE")
    public String getSysCompanyCode() {
        return sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode == null ? null : sysCompanyCode.trim();
    }

    @Column(name = "SYS_ORG_CODE")
    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode == null ? null : sysOrgCode.trim();
    }

    @Column(name = "ISDELETED")
    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Column(name = "BPM_STATUS")
    public String getBpmStatus() {
        return bpmStatus;
    }

    public void setBpmStatus(String bpmStatus) {
        this.bpmStatus = bpmStatus == null ? null : bpmStatus.trim();
    }

    @Column(name = "STORAGEDETAIL")
    public String getStorageDetail() {
        return storageDetail;
    }

    public void setStorageDetail(String storageDetail) {
        this.storageDetail = storageDetail;
    }
}