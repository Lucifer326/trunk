package com.jeecg.material_scrap.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REP_SUBSTANCE_SCRAP", schema = "")
/**物资报废管理 Entity
 * @author 任小昆
 */
public class Scrap implements java.io.Serializable {

    /**
     * 主键id
     */
    private java.lang.String uuid;
    /**
     * 报废单据号
     */
    private java.lang.String scrapNumber;
    /**
     * 仓库
     */
    private java.lang.String wareHouse;
    /**
     * 关联单据
     */
    private java.lang.String associatedDocuments;
    /**
     * 备注
     */
    private java.lang.String remark;
    /**
     * 创建人姓名
     */
    private java.lang.String createName;
    /**
     * 创建人登录名称
     */
    private java.lang.String createBy;
    /**
     * 创建时间
     */
    private java.util.Date createDate;
    /**
     * 更新人姓名
     */
    private java.lang.String updateName;
    /**
     * 更新人登录名称
     */
    private java.lang.String updateBy;
    /**
     * 更新时间
     */
    private java.util.Date updateDate;
    /**
     * 所属公司
     */
    private java.lang.String companyCode;
    /**
     * 所属部门
     */
    private java.lang.String organizationCode;
    /**
     * 流程状态
     */
    private java.lang.String status;
    /**
     * 是否删除
     */
    private java.lang.String isDeleted;
    /**
     * 报废时间
     */
    private java.util.Date scrapDate;

    /**
     * 审批状态
     *
     * @return
     */
    private java.lang.String reviewStatus;
    /**
     * 审批人
     *
     * @return
     */
    private java.lang.String reviewer;
    /**
     * 审批意见
     *
     * @return
     */
    private java.lang.String reviewReason;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "uuid", nullable = false, length = 36)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    @Column(name = "scrapNumber", nullable = true, length = 36)
    public String getScrapNumber() {
        return scrapNumber;
    }

    public void setScrapNumber(String scrapNumber) {
        this.scrapNumber = scrapNumber == null ? null : scrapNumber.trim();
    }


    @Column(name = "wareHouse", nullable = true, length = 36)
    public String getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(String wareHouse) {
        this.wareHouse = wareHouse;
    }


    @Column(name = "associatedDocuments", nullable = true, length = 36)
    public String getAssociatedDocuments() {
        return associatedDocuments;
    }

    public void setAssociatedDocuments(String associatedDocuments) {
        this.associatedDocuments = associatedDocuments;
    }


    @Column(name = "remark", nullable = true, length = 250)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    @Column(name = "createName", nullable = true, length = 36)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }


    @Column(name = "createBy", nullable = true, length = 36)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "createDate", nullable = true, length = 36)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Column(name = "updateName", nullable = true)

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    @Column(name = "updateBy", nullable = true, length = 36)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    @Column(name = "updateDate", nullable = true)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    @Column(name = "companyCode", nullable = true, length = 36)

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Column(name = "organizationCode", nullable = true, length = 36)

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }


    @Column(name = "status", nullable = true, length = 36)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Column(name = "isDeleted", nullable = true, length = 36)

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Column(name = "scrapDate", nullable = true)
    public Date getScrapDate() {
        return scrapDate;
    }

    public void setScrapDate(Date scrapDate) {
        this.scrapDate = scrapDate;
    }

    @Column(name = "reviewStatus", nullable = true, length = 36)
    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    @Column(name = "reviewer", nullable = true, length = 36)
    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    @Column(name = "reviewReason", nullable = true, length = 36)
    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

}
