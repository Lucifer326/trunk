package com.jeecg.MaterialsInPut.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 物资入库
 */
@Entity
@Table(name = "REP_SUBSTANCE_STORAGE", schema = "")
public class RepSubstanceStorageEntity implements Serializable {
	/**
	 * 主键ID
	 */
	private String uuid;

	/**
	 * 入库单号
	 */
	private String storageNumber;

	/**
	 * 是否定向
	 */
	private String isorienteering;

	/**
	 * 采购批次
	 */
	private String purchaseBatch;

	/**
	 * 捐赠用途
	 */
	private String donationPurposes;

	/**
	 * 入库仓库id
	 */
	private String storageWarehouse;

	/**
	 * 物资来源
	 */
	private String substanceSource;

	/**
	 * 入库人
	 */
	private String storagePerson;

	/**
	 * 入库日期
	 */
	private Date storageDate;

	/**
	 * 捐赠区域
	 */
	private String donateArea;

	/**
	 * 附件
	 */
	private String accessory;

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
	 * 所属公司
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
	 * 来源对象 (采购人, 捐赠人, 调拨单)
	 */
	private String sourceObject;

	/**
	 * 流程状态
	 */
	private String bpmStatus;

	@Override
	public String toString() {
		return "RepSubstanceStorageEntity{" +
				"uuid='" + uuid + '\'' +
				", storageNumber='" + storageNumber + '\'' +
				", isorienteering='" + isorienteering + '\'' +
				", purchaseBatch='" + purchaseBatch + '\'' +
				", donationPurposes='" + donationPurposes + '\'' +
				", storageWarehouse='" + storageWarehouse + '\'' +
				", substanceSource='" + substanceSource + '\'' +
				", storagePerson='" + storagePerson + '\'' +
				", storageDate=" + storageDate +
				", donateArea='" + donateArea + '\'' +
				", accessory='" + accessory + '\'' +
				", remark='" + remark + '\'' +
				", createName='" + createName + '\'' +
				", createBy='" + createBy + '\'' +
				", createDate=" + createDate +
				", updateName='" + updateName + '\'' +
				", updateBy='" + updateBy + '\'' +
				", updateDate=" + updateDate +
				", sysCompanyCode='" + sysCompanyCode + '\'' +
				", sysOrgCode='" + sysOrgCode + '\'' +
				", isdeleted=" + isdeleted +
				", sourceObject='" + sourceObject + '\'' +
				", bpmStatus='" + bpmStatus + '\'' +
				'}';
	}

	@Id
	//@GeneratedValue(generator = "paymentableGenerator")
	//@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "UUID", length = 36, nullable = false)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "STORAGE_NUMBER", length = 36, nullable = false)
	public String getStorageNumber() {
		return storageNumber;
	}

	public void setStorageNumber(String storageNumber) {
		this.storageNumber = storageNumber;
	}

	@Column(name = "ISORIENTEERING", length = 36)
	public String getIsorienteering() {
		return isorienteering;
	}

	public void setIsorienteering(String isorienteering) {
		this.isorienteering = isorienteering;
	}

	@Column(name = "PURCHASE_BATCH", length = 36)
	public String getPurchaseBatch() {
		return purchaseBatch;
	}

	public void setPurchaseBatch(String purchaseBatch) {
		this.purchaseBatch = purchaseBatch;
	}

	@Column(name = "DONATION_PURPOSES", length = 36)
	public String getDonationPurposes() {
		return donationPurposes;
	}

	public void setDonationPurposes(String donationPurposes) {
		this.donationPurposes = donationPurposes;
	}

	@Column(name = "STORAGE_WAREHOUSE", length = 36, nullable = false)
	public String getStorageWarehouse() {
		return storageWarehouse;
	}

	public void setStorageWarehouse(String storageWarehouse) {
		this.storageWarehouse = storageWarehouse;
	}

	@Column(name = "SUBSTANCE_SOURCE", length = 36, nullable = false)
	public String getSubstanceSource() {
		return substanceSource;
	}

	public void setSubstanceSource(String substanceSource) {
		this.substanceSource = substanceSource;
	}

	@Column(name = "STORAGE_PERSON", length = 36, nullable = false)
	public String getStoragePerson() {
		return storagePerson;
	}

	public void setStoragePerson(String storagePerson) {
		this.storagePerson = storagePerson;
	}

	@Column(name = "STORAGE_DATE", nullable = false)
	public Date getStorageDate() {
		return storageDate;
	}

	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}

	@Column(name = "DONATE_AREA", length = 36, nullable = false)
	public String getDonateArea() {
		return donateArea;
	}

	public void setDonateArea(String donateArea) {
		this.donateArea = donateArea == null ? null : donateArea.trim();
	}

	@Column(name = "ACCESSORY", length = 36)
	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "CREATE_NAME", length = 36)
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	@Column(name = "CREATE_BY", length = 36)
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "CREATE_DATE", length = 36)
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
		this.updateName = updateName;
	}

	@Column(name = "UPDATE_BY", length = 36)
	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Column(name = "UPDATE_DATE", length = 36)
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
		this.sysCompanyCode = sysCompanyCode;
	}

	@Column(name = "SYS_ORG_CODE", length = 36)
	public String getSysOrgCode() {
		return sysOrgCode;
	}

	public void setSysOrgCode(String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	@Column(name = "ISDELETED", length = 1, nullable = false)
	public Short getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Short isdeleted) {
		this.isdeleted = isdeleted;
	}

	@Column(name = "SOURCE_OBJECT", length = 36, nullable = false)
	public String getSourceObject() {
		return sourceObject;
	}

	public void setSourceObject(String sourceObject) {
		this.sourceObject = sourceObject;
	}

	@Column(name = "BPM_STATUS", length = 36)
	public String getBpmStatus() {
		return bpmStatus;
	}

	public void setBpmStatus(String bpmStatus) {
		this.bpmStatus = bpmStatus;
	}
}