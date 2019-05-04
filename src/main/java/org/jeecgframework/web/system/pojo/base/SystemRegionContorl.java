package org.jeecgframework.web.system.pojo.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;

/**
 * 行政区划显示控制表
 * 
 * @author 程沼龙
 */
@Entity
@Table(name = "system_region_control")
public class SystemRegionContorl implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**根行政区划id*/
	private java.lang.String rootRegionId;
	/**末级显示级别*/
	private java.lang.String levelCode;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	@Column(name ="root_region_id",nullable=true,length=32)
	public java.lang.String getRootRegionId() {
		return rootRegionId;
	}
	public void setRootRegionId(java.lang.String rootRegionId) {
		this.rootRegionId = rootRegionId;
	}
	@Column(name ="level_code",nullable=true,length=20)
	public java.lang.String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(java.lang.String levelCode) {
		this.levelCode = levelCode;
	}
	


}