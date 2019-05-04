package com.jeecg.rep_substance_recycle.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 物资回收详细
 * @author 刘雨泽
 * @table REP_DETAILED_MATERIAL_RECOVERY
 */
@Entity
@Table(name = "REP_DETAILED_MATERIAL_RECOVERY", schema = "")
@SuppressWarnings("serial")
public class RepDetailedMaterialRecoveryEntity {

    /**
     *id
     */
    private String uuid;

    /**
     *物资名称
     */
    private String subName;

    /**
     *规格类型
     */
    private String specificationType;

    /**
     *物资类别
     */
    private String subCategory;

    /**
     *单位
     */
    private String unit;

    /**
     *回收数量
     */
    private BigDecimal backNum;

    /**
     *是否删除
     */
    private String isdeleted = "0";

    /**
     * 物资详细表id
     */
    private String repositoryid;

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

    @Column(name ="SUB_NAME",nullable=true,length=36)
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    @Column(name ="SPECIFICATION_TYPE",nullable=true,length=36)
    public String getSpecificationType() {
        return specificationType;
    }

    public void setSpecificationType(String specificationType) {
        this.specificationType = specificationType == null ? null : specificationType.trim();
    }

    @Column(name ="SUB_CATEGORY",nullable=true,length=36)
    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory == null ? null : subCategory.trim();
    }

    @Column(name ="UNIT",nullable=true,length=36)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    @Column(name ="BACK_NUM",nullable=true,length=36)
    public BigDecimal getBackNum() {
        return backNum;
    }

    public void setBackNum(BigDecimal backNum) {
        this.backNum = backNum;
    }

    @Column(name ="ISDELETED",nullable=true,length=36)
    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted == null ? null : isdeleted.trim();
    }

    @Column(name ="REPOSITORYID",nullable=true,length=36)
    public String getRepositoryid() {
        return repositoryid;
    }

    public void setRepositoryid(String repositoryid) {
        this.repositoryid = repositoryid == null ? null : repositoryid.trim();
    }

}
