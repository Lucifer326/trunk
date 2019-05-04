package com.jeecg.MaterialsInPut.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 附件信息
 *
 * @table REP_SUBSTANCE_ACCESSORY
 * @author 苑希阳
 */
@Entity
@Table(name = "REP_SUBSTANCE_ACCESSORY")
public class RepSubstanceAccessory {

    private String uuid;

    private String accessoryName;

    private String accessoryPath;

    @Id
    @Column(name = "UUID",length = 36, nullable = false)
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Column(name = "ACCESSORY_NAME",length = 36, nullable = false)
    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    @Column(name = "ACCESSORY_PATH", length = 200, nullable = false)
    public String getAccessoryPath() {
        return accessoryPath;
    }

    public void setAccessoryPath(String accessoryPath) {
        this.accessoryPath = accessoryPath;
    }
}
