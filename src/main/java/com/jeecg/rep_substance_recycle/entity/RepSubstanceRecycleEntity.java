package com.jeecg.rep_substance_recycle.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 物资回收
 * @author 刘雨泽
 * @table REP_SUBSTANCE_RECYCLE
 */
@Entity
@Table(name = "REP_SUBSTANCE_RECYCLE", schema = "")
@SuppressWarnings("serial")
public class RepSubstanceRecycleEntity {

    /**
     *主键ID
     */
    private String uuid;

    /**
     *回收单据号
     */
    private String recycleNumber;

    /**
     *入库仓库
     */
    private String storageWarehouse;

    /**
     *入库人
     */
    private String storagePerson;

    /**
     *入库时间
     */
    private Date storageDate;

    /**
     *出库单id
     */
    private String outhouseId;

    /**
     *创建人姓名
     */
    private String createName;

    /**
     *创建人登录名称
     */
    private String createBy;

    /**
     *创建时间
     */
    private Date createDate;

    /**
     *更新人姓名
     */
    private String updateName;

    /**
     *更新人登录名称
     */
    private String updateBy;

    /**
     *更新时间
     */
    private Date updateDate;

    /**
     *所属公司
     */
    private String sysCompanyCode;

    /**
     *所属部门
     */
    private String sysOrgCode;

    /**
     *是否删除
     */
    private String isdeleted = "0";

    /**
     * 仓库id
     */
    private String 	repositoryId;

    private String approvalStatus;

    private String approver;

    private String approvalOpinion;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="UUID",nullable=false,length=36)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    @Column(name = "APPROVAL_STATUS")
    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Column(name = "APPROVER")
    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    @Column(name = "APPROVAL_OPINION")
    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    @Column(name ="RECYCLE_NUMBER",nullable=true,length=36)
    public String getRecycleNumber() {
        return recycleNumber;
    }

    public void setRecycleNumber(String recycleNumber) {
        this.recycleNumber = recycleNumber == null ? null : recycleNumber.trim();
    }

    @Column(name ="STORAGE_WAREHOUSE",nullable=true,length=36)
    public String getStorageWarehouse() {
        return storageWarehouse;
    }

    public void setStorageWarehouse(String storageWarehouse) {
        this.storageWarehouse = storageWarehouse == null ? null : storageWarehouse.trim();
    }

    @Column(name ="STORAGE_PERSON",nullable=true,length=36)
    public String getStoragePerson() {
        return storagePerson;
    }

    public void setStoragePerson(String storagePerson) {
        this.storagePerson = storagePerson == null ? null : storagePerson.trim();
    }

    @Column(name ="STORAGE_DATE",nullable=true)
    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    @Column(name ="OUTHOUSEID",nullable=true, length = 36)
    public String getOuthouseId() {
        return outhouseId;
    }

    public void setOuthouseId(String outhouseId) {
        this.outhouseId = outhouseId;
    }

    @Column(name ="CREATE_NAME",nullable=true,length=36)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }

    @Column(name ="CREATE_BY",nullable=true,length=36)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    @Column(name ="CREATE_DATE",nullable=true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name ="UPDATE_NAME",nullable=true,length=36)
    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    @Column(name ="UPDATE_BY",nullable=true,length=36)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    @Column(name ="UPDATE_DATE",nullable=true)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name ="SYS_COMPANY_CODE",nullable=true,length=36)
    public String getSysCompanyCode() {
        return sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode == null ? null : sysCompanyCode.trim();
    }

    @Column(name ="SYS_ORG_CODE",nullable=true,length=36)
    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode == null ? null : sysOrgCode.trim();
    }

    @Column(name ="ISDELETED",nullable=true,length=36)
    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    @Column(name ="REPOSITORYID",nullable=true,length=36)
    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }
}