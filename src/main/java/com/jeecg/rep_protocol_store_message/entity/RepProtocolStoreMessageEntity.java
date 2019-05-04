package com.jeecg.rep_protocol_store_message.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 协议储备
 * @author 刘雨泽
 * @table REP_PROTOCOL_STORE_MESSAGE
 */
@Entity
@Table(name = "REP_PROTOCOL_STORE_MESSAGE", schema = "")
@SuppressWarnings("serial")
public class RepProtocolStoreMessageEntity {
    private String uuid;

    /**
     *协议编号
     */
    private String protocolNumber;

    /**
     *协议签订日期
     */
    private Date protocolDate;

    /**
     *协议储备物资
     */
    private String protocolStorage;

    /**
     *物资规格
     */
    private String storageSize;

    /**
     *协议储备数量
     */
    private Long protocolAmount;

    /**
     *协议年限
     */
    private String protocolYear;

    /**
     *协议单价
     */
    private BigDecimal protocolPrice;

    /**
     *协议公司
     */
    private String protocolCompany;

    /**
     *
     */
    private String disId;

    /**
     *
     */
    private String disName;

    /**
     *备注
     */
    private String remark;

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
     *
     */
    private String bpmStatus;

    /**
     *是否删除
     */
    private String isdeleted = "0";

    /**
     *
     */
    private String protocolStorageId;

    /**
     *
     */
    private String protocolCompanyId;

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

    @Column(name ="PROTOCOL_NUMBER",nullable=true,length=36)
    public String getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(String protocolNumber) {
        this.protocolNumber = protocolNumber == null ? null : protocolNumber.trim();
    }

    @Column(name ="PROTOCOL_DATE",nullable=true)
    public Date getProtocolDate() {
        return protocolDate;
    }

    public void setProtocolDate(Date protocolDate) {
        this.protocolDate = protocolDate;
    }

    @Column(name ="PROTOCOL_STORAGE",nullable=true,length=100)
    public String getProtocolStorage() {
        return protocolStorage;
    }

    public void setProtocolStorage(String protocolStorage) {
        this.protocolStorage = protocolStorage == null ? null : protocolStorage.trim();
    }

    @Column(name ="STORAGE_SIZE",nullable=true,length=100)
    public String getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(String storageSize) {
        this.storageSize = storageSize == null ? null : storageSize.trim();
    }

    @Column(name ="PROTOCOL_AMOUNT",nullable=true,length=36)
    public Long getProtocolAmount() {
        return protocolAmount;
    }

    public void setProtocolAmount(Long protocolAmount) {
        this.protocolAmount = protocolAmount;
    }

    @Column(name ="PROTOCOL_YEAR",nullable=true,length=36)
    public String getProtocolYear() {
        return protocolYear;
    }

    public void setProtocolYear(String protocolYear) {
        this.protocolYear = protocolYear == null ? null : protocolYear.trim();
    }

    @Column(name ="PROTOCOL_PRICE",nullable=true)
    public BigDecimal getProtocolPrice() {
        return protocolPrice;
    }

    public void setProtocolPrice(BigDecimal protocolPrice) {
        this.protocolPrice = protocolPrice;
    }

    @Column(name ="PROTOCOL_COMPANY",nullable=true,length=100)
    public String getProtocolCompany() {
        return protocolCompany;
    }

    public void setProtocolCompany(String protocolCompany) {
        this.protocolCompany = protocolCompany == null ? null : protocolCompany.trim();
    }

    @Column(name ="DIS_ID",nullable=true,length=36)
    public String getDisId() {
        return disId;
    }

    public void setDisId(String disId) {
        this.disId = disId == null ? null : disId.trim();
    }

    @Column(name ="DIS_NAME",nullable=true,length=36)
    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName == null ? null : disName.trim();
    }

    @Column(name ="REMARK",nullable=true,length=500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    @Column(name ="BPM_STATUS",nullable=true,length=36)
    public String getBpmStatus() {
        return bpmStatus;
    }

    public void setBpmStatus(String bpmStatus) {
        this.bpmStatus = bpmStatus == null ? null : bpmStatus.trim();
    }

    @Column(name ="ISDELETED",nullable=true,length=10)
    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    @Column(name ="PROTOCOL_STORAGE_ID",nullable=true,length=36)
    public String getProtocolStorageId() {
        return protocolStorageId;
    }

    public void setProtocolStorageId(String protocolStorageId) {
        this.protocolStorageId = protocolStorageId == null ? null : protocolStorageId.trim();
    }

    @Column(name ="PROTOCOL_COMPANY_ID",nullable=true,length=36)
    public String getProtocolCompanyId() {
        return protocolCompanyId;
    }

    public void setProtocolCompanyId(String protocolCompanyId) {
        this.protocolCompanyId = protocolCompanyId == null ? null : protocolCompanyId.trim();
    }
}