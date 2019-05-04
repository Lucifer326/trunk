package com.jeecg.allocation_notice.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: dengfuhai
 */
@Entity
@Table(name = "REP_ALLOT_INFORM", schema = "")
public class RepAllotInform {
    /**
     *主键id
     */
    private String uuid;
    /**
     * 调拨单据号
     */
    private String allotNumber;
    /**
     * 救灾项目
     */
    private String reloefProject;
    /**
     *  调出部门
     */
    private String calloutDepartment;
    /**
     * 调入部门
     */
    private String callinDepartment;
    /**
     *调拨日期
     */
    private Date allotDate;
    /**
     * 物资用途
     */
    private String storageUse;
    /**
     * 备注
     */
    private String remark;
    /**
     * 	创建人姓名
     */
    private String createName;
    /**
     * 	创建人登录名称
     */
    private String createBy;
    /**
     * 	创建时间
     */
    private Date createDate;
    /**
     * 更新人姓名
     */
    private String updateName;
    /**
     * 	更新人登录名称
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 所属公司
     */
    private String sysCompanyCode;
    /**
     * 所属部门
     */
    private String sysOrgCode;
    /**
     * 流程状态
     */
    private String bpmStatus;
    /**
     * 	是否删除(null or 0未删除，1删除)
     */
    private Short isdeleted;
    /**
     * 仓储机构编号
     */
    private String repositoryid;
    /**
     * 	是否已使用（0：使用，1：未使用）
     */
    private Short isuse;
    /**
     *  	调拨单状态  1:未出库  2:已出库
     */
    private String allotStatus;
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "UUID", nullable = false, length = 36)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }
    @Column(name = "ALLOT_NUMBER", nullable = true, length = 36)
    public String getAllotNumber() {
        return allotNumber;
    }

    public void setAllotNumber(String allotNumber) {
        this.allotNumber = allotNumber == null ? null : allotNumber.trim();
    }
    @Column(name = "RELOEF_PROJECT", nullable = true, length = 36)
    public String getReloefProject() {
        return reloefProject;
    }

    public void setReloefProject(String reloefProject) {
        this.reloefProject = reloefProject == null ? null : reloefProject.trim();
    }
    @Column(name = "CALLOUT_DEPARTMENT", nullable = true, length = 36)
    public String getCalloutDepartment() {
        return calloutDepartment;
    }

    public void setCalloutDepartment(String calloutDepartment) {
        this.calloutDepartment = calloutDepartment == null ? null : calloutDepartment.trim();
    }
    @Column(name = "CALLIN_DEPARTMENT", nullable = true, length = 36)
    public String getCallinDepartment() {
        return callinDepartment;
    }

    public void setCallinDepartment(String callinDepartment) {
        this.callinDepartment = callinDepartment == null ? null : callinDepartment.trim();
    }
    @Column(name = "ALLOT_DATE", nullable = true, length = 7)
    public Date getAllotDate() {
        return allotDate;
    }

    public void setAllotDate(Date allotDate) {
        this.allotDate = allotDate;
    }
    @Column(name = "STORAGE_USE", nullable = true, length = 36)
    public String getStorageUse() {
        return storageUse;
    }

    public void setStorageUse(String storageUse) {
        this.storageUse = storageUse == null ? null : storageUse.trim();
    }
    @Column(name = "REMARK", nullable = true, length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    @Column(name = "CREATE_NAME", nullable = true, length = 36)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName == null ? null : createName.trim();
    }
    @Column(name = "CREATE_BY", nullable = true, length = 36)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }
    @Column(name = "CREATE_DATE", nullable = true, length = 7)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    @Column(name = "UPDATE_NAME", nullable = true, length = 36)
    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }
    @Column(name = "UPDATE_BY", nullable = true, length = 36)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
    @Column(name = "UPDATE_DATE", nullable = true, length = 7)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    @Column(name = "SYS_COMPANY_CODE", nullable = true, length = 36)
    public String getSysCompanyCode() {
        return sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode == null ? null : sysCompanyCode.trim();
    }
    @Column(name = "SYS_ORG_CODE", nullable = true, length = 36)
    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode == null ? null : sysOrgCode.trim();
    }
    @Column(name = "BPM_STATUS", nullable = true, length = 36)
    public String getBpmStatus() {
        return bpmStatus;
    }

    public void setBpmStatus(String bpmStatus) {
        this.bpmStatus = bpmStatus == null ? null : bpmStatus.trim();
    }
    @Column(name = "ISDELETED", nullable = true, length = 1)
    public Short getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Short isdeleted) {
        this.isdeleted = isdeleted;
    }
    @Column(name = "REPOSITORYID", nullable = true, length = 36)
    public String getRepositoryid() {
        return repositoryid;
    }

    public void setRepositoryid(String repositoryid) {
        this.repositoryid = repositoryid == null ? null : repositoryid.trim();
    }
    @Column(name = "ISUSE", nullable = true, length = 1)
    public Short getIsuse() {
        return isuse;
    }

    public void setIsuse(Short isuse) {
        this.isuse = isuse;
    }
    @Column(name = "ALLOT_STATUS", nullable = true, length = 1)
    public String getAllotStatus() {
        return allotStatus;
    }

    public void setAllotStatus(String allotStatus) {
        this.allotStatus = allotStatus == null ? null : allotStatus.trim();
    }
}