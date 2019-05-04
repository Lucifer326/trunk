package com.jeecg.system_region.entity;

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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

/**
 * @Title: Entity
 * @Description: 系统区划表
 * @author onlineGenerator
 * @date 2017-03-28 14:11:45
 * @version V1.0
 *
 */
@Entity
@Table(name = "system_region", schema = "")
@SuppressWarnings("serial")
public class SystemRegionEntity implements java.io.Serializable {
	/** 主键 */
	@Excel(name = "主键")
	private java.lang.String id;
	/** 创建人名称 */
	private java.lang.String createName;
	/** 创建人登录名称 */
	private java.lang.String createBy;
	/** 创建日期 */
	private java.util.Date createDate;
	/** 更新人名称 */
	private java.lang.String updateName;
	/** 更新人登录名称 */
	private java.lang.String updateBy;
	/** 更新日期 */
	private java.util.Date updateDate;
	/** 所属部门 */
	private java.lang.String sysOrgCode;
	/** 所属公司 */
	private java.lang.String sysCompanyCode;
	/** 流程状态 */
	private java.lang.String bpmStatus;
	/** 行政区划代码 */
	@Excel(name = "行政区划编码")
	private java.lang.String regionCode;
	/** 行政区划名称 */
	@Excel(name = "行政区划名称")
	private java.lang.String regionName;
	/** 行政区划简称 */
	@Excel(name = "行政区划简称")
	private java.lang.String regionShortName;
	/** 行政区划级别名称 */
	@Excel(name = "行政区划级别名称")
	private java.lang.String regionLevel;
	/** 行政区划区号 */
	@Excel(name = "行政区划区号")
	private java.lang.String areaCode;
	/** 行政区划邮政编码 */
	@Excel(name = "行政区划邮政编码")
	private java.lang.String regionPostcode;
	/** 行政区划面积 */
	@Excel(name = "行政区划面积")
	private java.lang.Double regionArea;
	/** 行政区划设立日期 */
	@Excel(name = "行政区划设立日期", format = "yyyy-MM-dd")
	private java.util.Date regionCreateDate;
	/** 行政区划撤销日期 */
	@Excel(name = "行政区划撤销日期", format = "yyyy-MM-dd")
	private java.util.Date revokeDate;
	/** 行政区划政府驻地名称 */
	@Excel(name = "行政区划政府驻地名称")
	private java.lang.String regionEncampment;
	/** 行政区划人口数量 */
	@Excel(name = "行政区划人口数量")
	private java.lang.Double regionPopulation;
	/** 行政区划城镇人口数量 */
	@Excel(name = "行政区划城镇人口数量")
	private java.lang.Double regionCountyPopulation;
	
	/**判断是否是叶子节点 */
	@Excel(name = "是否是叶子节点")
	private java.lang.String isLeaf;
	private java.lang.String parentCode;
	
	private SystemRegionEntity parent;
	private List<SystemRegionEntity> list;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 主键
	 */

	public void setParent(SystemRegionEntity parent) {
		this.parent = parent;
	}

	/*
	 * @ManyToOne //一对多双向
	 * 
	 * @JoinColumn(name=" ") //需要指定外键与一的一端给的外键名称一致，
	 * 也可以不指定，如果在多的一端不指定，则一的一端也不能指定,否则为生成两个外键
	 * 该参数定义了所关联目标实体中的联接列.注意,当referencedColumnName关联到非主键列的时候,
	 * 关联的目标类必须实现Serializable,还要注意的是所映射的属性对应单个列(否则映射无效).
	 * 注意：在关系被维护端需要通过@JoinColumn建立外键列指向关系维护端的主键
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	public SystemRegionEntity getParent() {
		return parent;
	}

	public void setList(List<SystemRegionEntity> list) {
		this.list = list;
	}

	// 一对多关联映射（双向），需要添加mappedBy属性，在一的一端中设置mappedBy,说明多的一端为主导
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent")
	public List<SystemRegionEntity> getList() {
		return list;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 36)
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             主键
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 创建人名称
	 */
	@Column(name = "CREATE_NAME", nullable = true, length = 50)
	public java.lang.String getCreateName() {
		return this.createName;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             创建人名称
	 */
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 创建人登录名称
	 */
	@Column(name = "CREATE_BY", nullable = true, length = 50)
	public java.lang.String getCreateBy() {
		return this.createBy;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建日期
	 */
	@Column(name = "CREATE_DATE", nullable = true, length = 20)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date
	 *             创建日期
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 更新人名称
	 */
	@Column(name = "UPDATE_NAME", nullable = true, length = 50)
	public java.lang.String getUpdateName() {
		return this.updateName;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             更新人名称
	 */
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 更新人登录名称
	 */
	@Column(name = "UPDATE_BY", nullable = true, length = 50)
	public java.lang.String getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 更新日期
	 */
	@Column(name = "UPDATE_DATE", nullable = true, length = 20)
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date
	 *             更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 所属部门
	 */
	@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
	public java.lang.String getSysOrgCode() {
		return this.sysOrgCode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 所属公司
	 */
	@Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
	public java.lang.String getSysCompanyCode() {
		return this.sysCompanyCode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode) {
		this.sysCompanyCode = sysCompanyCode;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 流程状态
	 */
	@Column(name = "BPM_STATUS", insertable = false, nullable = true, length = 32)
	public java.lang.String getBpmStatus() {
		return this.bpmStatus;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus) {
		this.bpmStatus = bpmStatus;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 行政区划代码
	 */
	@Column(name = "REGION_CODE", nullable = true, length = 12)
	public java.lang.String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             行政区划代码
	 */
	public void setRegionCode(java.lang.String regionCode) {
		this.regionCode = regionCode;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 行政区划名称
	 */
	@Column(name = "REGION_NAME", nullable = true, length = 50)
	public java.lang.String getRegionName() {
		return this.regionName;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             行政区划名称
	 */
	public void setRegionName(java.lang.String regionName) {
		this.regionName = regionName;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 行政区划简称
	 */
	@Column(name = "REGION_SHORT_NAME", nullable = true, length = 50)
	public java.lang.String getRegionShortName() {
		return this.regionShortName;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             行政区划简称
	 */
	public void setRegionShortName(java.lang.String regionShortName) {
		this.regionShortName = regionShortName;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 行政区划级别名称
	 */
	@Column(name = "REGION_LEVEL", nullable = true, length = 20)
	public java.lang.String getRegionLevel() {
		return this.regionLevel;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             行政区划级别名称
	 */
	public void setRegionLevel(java.lang.String regionLevel) {
		this.regionLevel = regionLevel;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 行政区划长途电话区号
	 */
	@Column(name = "AREA_CODE", nullable = true, length = 10)
	public java.lang.String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             行政区划长途电话区号
	 */
	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 行政区划邮政编码
	 */
	@Column(name = "REGION_POSTCODE", nullable = true, length = 10)
	public java.lang.String getRegionPostcode() {
		return this.regionPostcode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             行政区划邮政编码
	 */
	public void setRegionPostcode(java.lang.String regionPostcode) {
		this.regionPostcode = regionPostcode;
	}

	/**
	 * 方法: 取得java.lang.Double
	 * 
	 * @return: java.lang.Double 行政区划面积
	 */
	@Column(name = "REGION_AREA", nullable = true, length = 32)
	public java.lang.Double getRegionArea() {
		return this.regionArea;
	}

	/**
	 * 方法: 设置java.lang.Double
	 * 
	 * @param: java.lang.Double
	 *             行政区划面积
	 */
	public void setRegionArea(java.lang.Double regionArea) {
		this.regionArea = regionArea;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 行政区划设立日期
	 */
	@Column(name = "REGION_CREATE_DATE", nullable = true, length = 32)
	public java.util.Date getRegionCreateDate() {
		return this.regionCreateDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date
	 *             行政区划设立日期
	 */
	public void setRegionCreateDate(java.util.Date regionCreateDate) {
		this.regionCreateDate = regionCreateDate;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 行政区划撤销日期
	 */
	@Column(name = "REVOKE_DATE", nullable = true, length = 32)
	public java.util.Date getRevokeDate() {
		return this.revokeDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date
	 *             行政区划撤销日期
	 */
	public void setRevokeDate(java.util.Date revokeDate) {
		this.revokeDate = revokeDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 行政区划政府驻地名称
	 */
	@Column(name = "REGION_ENCAMPMENT", nullable = true, length = 50)
	public java.lang.String getRegionEncampment() {
		return this.regionEncampment;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             行政区划政府驻地名称
	 */
	public void setRegionEncampment(java.lang.String regionEncampment) {
		this.regionEncampment = regionEncampment;
	}

	/**
	 * 方法: 取得java.lang.Double
	 * 
	 * @return: java.lang.Double 行政区划人口数量
	 */
	@Column(name = "REGION_POPULATION", nullable = true, length = 32)
	public java.lang.Double getRegionPopulation() {
		return this.regionPopulation;
	}

	/**
	 * 方法: 设置java.lang.Double
	 * 
	 * @param: java.lang.Double
	 *             行政区划人口数量
	 */
	public void setRegionPopulation(java.lang.Double regionPopulation) {
		this.regionPopulation = regionPopulation;
	}

	/**
	 * 方法: 取得java.lang.Double
	 * 
	 * @return: java.lang.Double 行政区划城镇人口数量
	 */
	@Column(name = "REGION_COUNTY_POPULATION", nullable = true, length = 32)
	public java.lang.Double getRegionCountyPopulation() {
		return this.regionCountyPopulation;
	}

	/**
	 * 方法: 设置java.lang.Double
	 * 
	 * @param: java.lang.Double
	 *             行政区划城镇人口数量
	 */
	public void setRegionCountyPopulation(java.lang.Double regionCountyPopulation) {
		this.regionCountyPopulation = regionCountyPopulation;
	}
	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             是否是叶子节点
	 */
	
	@Column(name = "IS_LEAF", nullable = true, length = 1)
	public java.lang.String getIsLeaf() {
		return isLeaf;
	}
	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             是否是叶子节点
	 */
	public void setIsLeaf(java.lang.String isLeaf) {
		this.isLeaf = isLeaf;
	}
	@Transient
	public java.lang.String getParentCode() {
		return parentCode;
	}

	public void setParentCode(java.lang.String parentCode) {
		this.parentCode = parentCode;
	}
	
	
	
	
	
}
