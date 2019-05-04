package com.jeecg.MaterialProducer.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName MaterialPurchaseRecordEntity
 * @Description TODO
 * @Author God
 * @Date 2019/4/4、19:30
 * @Version 1.0
 **/
@Entity
@Table(name = "MATERIAL_PURCHASE_RECORD",schema ="")
@SuppressWarnings("serial")
public class MaterialPurchaseRecordEntity {

    /**主键*/
    private java.lang.String uuid;
    /**物品种类*/
    private java.lang.String productCategory;
    /**数量*/
    private java.lang.String productNumber;
    /**单价*/
    private java.lang.String unitPrice;
    /**购买时间*/
    private java.util.Date buyTime;
    /**物资质量*/
    private java.lang.String materialQuality;
    /**备注*/
    private java.lang.String remark;
    /**创建人姓名*/
    private java.lang.String createName;
    /**创建人登录名称*/
    private java.lang.String createBy;
    /**创建时间*/
    private java.lang.String createDate;
    /**所属公司*/
    private java.lang.String sysCompanyCode;
    /**所属部门*/
    private java.lang.String sysOrgCode;
    /**sc_product表的主键*/
    private java.lang.String scProductId;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "UUID",nullable=false)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "PRODUCT_CATEGORY")
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    @Column(name = "PRODUCT_NUMBER")
    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }
    @Column(name = "UNIT_PRICE")
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Column(name = "BUY_TIME")
    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }
    @Column(name = "MATERIAL_QUALITY")
    public String getMaterialQuality() {
        return materialQuality;
    }

    public void setMaterialQuality(String materialQuality) {
        this.materialQuality = materialQuality;
    }
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Column(name = "CREATE_NAME")
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
    @Column(name = "CREATE_BY")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Column(name = "CREATE_DATE")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    @Column(name = "SYS_COMPANY_CODE")
    public String getSysCompanyCode() {
        return sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
    }
    @Column(name = "SYS_ORG_CODE")
    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }
    @Column(name = "SC_PRODUCT_ID")
    public String getScProductId() {
        return scProductId;
    }

    public void setScProductId(String scProductId) {
        this.scProductId = scProductId;
    }
}
