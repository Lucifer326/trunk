package com.jeecg.material_scrap.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
/**物资信息回显 Controller
 * @author 任小昆
 */
@Table(name = "REP_SUBSTANCE_SCRAPDETA", schema = "")
public class ScrapLookTwoInformation {
    /**
     * id
     */
    private java.lang.String uuid;

    /**
     * 物资品名
     */
    private java.lang.String subName;

    /**
     * 计量单位
     */
    private java.lang.String unit;

    /**
     * 报废数量
     */
    private java.lang.String scrapTotal;

    /**
     * 单位
     */
    private java.lang.String scrapCause;

    /**
     * 是否删除
     */
    private java.lang.String isdeleted = "0";


    /**
     * 入库单Id     ( 入库单Id)
     */
    private java.lang.String storageId;

    /**
     * 物资信息表id
     */
    private java.lang.String repositoryid;
    private String messageId;

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

    @Column(name = "subName", nullable = true, length = 36)
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Column(name = "unit", nullable = true, length = 36)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    @Column(name = "scrapTotal", nullable = true, length = 36)
    public String getScrapTotal() {
        return scrapTotal;
    }

    public void setScrapTotal(String scrapTotal) {
        this.scrapTotal = scrapTotal;
    }

    @Column(name = "scrapCause", nullable = true, length = 36)
    public String getScrapCause() {
        return scrapCause;
    }

    public void setScrapCause(String scrapCause) {
        this.scrapCause = scrapCause;
    }

    @Column(name = "isdeleted", nullable = true, length = 36)
    public String getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(String isdeleted) {
        this.isdeleted = isdeleted;
    }

    @Column(name = "repositoryid", nullable = true, length = 36)
    public String getRepositoryid() {
        return repositoryid;
    }

    public void setRepositoryid(String repositoryid) {
        this.repositoryid = repositoryid;
    }

    @Column(name = "STORAGEID", nullable = true, length = 36)
    public String getStorageId() { return storageId; }

    public void setStorageId(String storageId) { this.storageId = storageId; }

    @Column(name = "MESSAGEID", nullable = true, length = 36)
    public String getMessageId() {
        return messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
