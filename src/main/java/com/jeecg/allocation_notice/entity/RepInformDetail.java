package com.jeecg.allocation_notice.entity;

import com.jeecg.system_management.pojo.RepSubstanceMessage;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: dengfuhai
 */
@Entity
@Table(name = "REP_INFORM_DETAIL", schema = "")
public class RepInformDetail {
    /**
     *调拨详情的主键id
     */
    private String uuid;
    /**
     *  调拨编号
     */
    private String informid;
    /**
     *   物资编号
     */
    private String messageid;
     /**
      *   物资折价合计
      */
    private Long priceTotal;
    /**
     *调拨数量
     */
    private Long allotAmount;

    /**
     *	备注
     */
    private String remark;
    /**
     *	创建人姓名
     */
    private String createName;
    /**
     *创建人登录名称
     */
    private String createBy;
    /**
     *	创建时间
     */
    private Date createDate;
    /**
     *	更新人姓名
     */
    private String updateName;
    /**
     *	更新人登录名称
     */
    private String updateBy;
    /**
     *	更新时间
     */
    private Date updateDate;
    /**
     *	所属公司
     */
    private String sysComyanyCode;
    /**
     *	所属部门
     */
    private String sysOrgCode;
    /**
     *  流程状态
     */
    private String bpmStatus;
    /**
     *	是否删除(null or 0未删除 1 已删除)
     */
    private Short isdeleted;


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

    @Column(name = "INFORMID", nullable = true, length = 36)
    public String getInformid() {
        return informid;
    }

    public void setInformid(String informid) {
        this.informid = informid == null ? null : informid.trim();
    }

    @Column(name = "MESSAGEID", nullable = true, length = 36)
    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }
    @Column(name = "PRICE_TOTAL", nullable = true, length = 18)
    public Long getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Long priceTotal) {
        this.priceTotal = priceTotal;
    }
    @Column(name = "ALLOT_AMOUNT", nullable = true, length = 18)
    public Long getAllotAmount() {
        return allotAmount;
    }

    public void setAllotAmount(Long allotAmount) {
        this.allotAmount = allotAmount;
    }
    @Column(name = "REMARK", nullable = true, length = 36)
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
    @Column(name = "SYS_COMYANY_CODE", nullable = true, length = 36)
    public String getSysComyanyCode() {
        return sysComyanyCode;
    }

    public void setSysComyanyCode(String sysComyanyCode) {
        this.sysComyanyCode = sysComyanyCode == null ? null : sysComyanyCode.trim();
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

    @Override
    public String toString() {
        return "RepInformDetail{" +
                "uuid='" + uuid + '\'' +
                ", informid='" + informid + '\'' +
                ", messageid='" + messageid + '\'' +
                ", priceTotal=" + priceTotal +
                ", allotAmount=" + allotAmount +
                ", remark='" + remark + '\'' +
                ", createName='" + createName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateName='" + updateName + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", sysComyanyCode='" + sysComyanyCode + '\'' +
                ", sysOrgCode='" + sysOrgCode + '\'' +
                ", bpmStatus='" + bpmStatus + '\'' +
                ", isdeleted=" + isdeleted +
                '}';
    }
}