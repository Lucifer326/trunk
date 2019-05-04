package org.jeecgframework.web.system.pojo.base;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * @Title: Entity
 * @Description: 分类管理
 * @author JueYue
 * @date 2014-09-16 21:50:55
 * @version V1.0
 * 
 */
@Entity()
@Table(name = "t_s_release", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
public class TSReleaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/** id */
	private java.lang.String id;
	/** 版本组件 */
	private java.lang.String groupname;
	/** 版本名称 */
	private java.lang.String name;
	/** 版本编号 */
	private java.lang.String version;
	/** 更新日期 */
	private java.util.Date updateDate;
	/** 是否启用 */
	private java.lang.String isUse;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String id
	 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 32)
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String id
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}
	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新日期
	 */
	@Column(name = "UPDATE_DATE", nullable = true)
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date 更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 版本名称
	 */
	@Column(name = "NAME", nullable = true, length = 32)
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 版本名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	
	

	
	

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 版本编号
	 */
	@Column(name = "VERSION", nullable = true, length = 32)
	public java.lang.String getVersion() {
		return this.version;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 版本组件
	 */
	@Column(name = "GROUPNAME", nullable = true, length = 32)
	public java.lang.String getGroupname() {
		return groupname;
	}
	
	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 版本组件
	 */
	public void setGroupname(java.lang.String groupname) {
		this.groupname = groupname;
	}
	

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String 版本编号
	 */
	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	

	@Column(name = "ISUSE", nullable = true, length = 10)
	public java.lang.String getIsUse() {
		return isUse;
	}

	public void setIsUse(java.lang.String isUse) {
		this.isUse = isUse;
	}

	

	
}
