package com.jeecg.bssys_deploy.entity;

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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Title: Entity
 * @Description: 系统配置表
 * @author onlineGenerator
 * @date 2017-05-17 14:43:22
 * @version V1.0
 *
 */
@Entity
@Table(name = "bssys_deploy", schema = "")
@SuppressWarnings("serial")
public class BssysDeployEntity implements java.io.Serializable {
	/** 主键 */
	private java.lang.String id;
	/** 配置名称 */
	@Excel(name = "配置名称")
	private java.lang.String name;
	/** 配置编号 */
	@Excel(name = "配置编号")
	private java.lang.String code;
	/** 数据表达式 */
	@Excel(name = "数据表达式")
	private java.lang.String dateExpr;
	/** 说明 */
	@Excel(name = "说明")
	private java.lang.String description;
	/** 父节点编码 */
	//@Excel(name = "父节点编码")
	//private java.lang.String parentCode;
	/** 控件类型 */
	@Excel(name = "控件类型")
	private java.lang.Integer controlType;
	/** 配置值 */
	@Excel(name = "配置值")
	private java.lang.String value;
	/** 是否绑定区划 */
	@Excel(name = "是否绑定区划")
	private java.lang.Integer isBinddis;
	/** 创建人ID */
	private java.lang.String createBy;
	/** 创建人姓名 */
	private java.lang.String createName;
	/** 创建时间 */
	private java.util.Date createDate;
	/** 更新人ID */
	private java.lang.String updateBy;
	/** 更新人姓名 */
	private java.lang.String updateName;
	/** 末次修改时间 */
	private java.util.Date updateDate;
	/** 是否已删除 */
	private java.lang.Integer isdeleted;
	/** 所属部门ID */
	private java.lang.String sysOrgCode;

	private BssysDeployEntity parent;
	private List<BssysDeployEntity> list;

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "ID", nullable = false, length = 50)
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
	 * @return: java.lang.String 配置名称
	 */
	@Column(name = "NAME", nullable = true, length = 64)
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             配置名称
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 配置编号
	 */
	@Column(name = "CODE", nullable = true, length = 50)
	public java.lang.String getCode() {
		return this.code;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             配置编号
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 数据表达式
	 */
	@Column(name = "DATE_EXPR", nullable = true, length = 4000)
	public java.lang.String getDateExpr() {
		return this.dateExpr;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             数据表达式
	 */
	public void setDateExpr(java.lang.String dateExpr) {
		this.dateExpr = dateExpr;
	}


	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 说明
	 */
	@Column(name = "DESCRIPTION", nullable = true, length = 4000)
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             说明
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 父节点编码
	 */
//	@Column(name = "PARENT_CODE", nullable = true, length = 50)
//	public java.lang.String getParentCode() {
//		return this.parentCode;
//	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             父节点编码
	 */
//	public void setParentCode(java.lang.String parentCode) {
//		this.parentCode = parentCode;
//	}

	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 控件类型
	 */
	@Column(name = "CONTROL_TYPE", nullable = true, length = 22)
	public java.lang.Integer getControlType() {
		return this.controlType;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer
	 *             控件类型
	 */
	public void setControlType(java.lang.Integer controlType) {
		this.controlType = controlType;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 配置值
	 */
	@Column(name = "VALUE", nullable = true, length = 50)
	public java.lang.String getValue() {
		return this.value;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             配置值
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
	}
	/**
	 * 方法: 取得java.lang.Integer
	 * 
	 * @return: java.lang.Integer 是否绑定区划
	 */
	@Column(name = "IS_BINDDIS", nullable = true, length = 22)
	public java.lang.Integer getIsBinddis() {
		return this.isBinddis;
	}

	/**
	 * 方法: 设置java.lang.Integer
	 * 
	 * @param: java.lang.Integer
	 *             是否绑定区划
	 */
	public void setIsBinddis(java.lang.Integer isBinddis) {
		this.isBinddis = isBinddis;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 创建人ID
	 */
	@Column(name = "CREATE_BY", nullable = true, length = 50)
	public java.lang.String getCreateBy() {
		return this.createBy;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             创建人ID
	 */
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 创建人姓名
	 */
	@Column(name = "CREATE_NAME", nullable = true, length = 64)
	public java.lang.String getCreateName() {
		return this.createName;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             创建人姓名
	 */
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 创建时间
	 */
	@Column(name = "CREATE_DATE", nullable = true, length = 7)
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date
	 *             创建时间
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 更新人ID
	 */
	@Column(name = "UPDATE_BY", nullable = true, length = 50)
	public java.lang.String getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             更新人ID
	 */
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 更新人姓名
	 */
	@Column(name = "UPDATE_NAME", nullable = true, length = 64)
	public java.lang.String getUpdateName() {
		return this.updateName;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             更新人姓名
	 */
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}

	/**
	 * 方法: 取得java.util.Date
	 * 
	 * @return: java.util.Date 末次修改时间
	 */
	@Column(name = "UPDATE_DATE", nullable = true, length = 7)
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 方法: 设置java.util.Date
	 * 
	 * @param: java.util.Date
	 *             末次修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 是否已删除
	 */
	@Column(name = "ISDELETED", nullable = true, length = 22)
	
	public java.lang.Integer getIsdeleted() {
		return this.isdeleted;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             是否已删除
	 */
	public void setIsdeleted(java.lang.Integer isdeleted) {
		this.isdeleted = isdeleted;
	}

	/**
	 * 方法: 取得java.lang.String
	 * 
	 * @return: java.lang.String 所属部门ID
	 */
	@Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
	public java.lang.String getSysOrgCode() {
		return this.sysOrgCode;
	}

	/**
	 * 方法: 设置java.lang.String
	 * 
	 * @param: java.lang.String
	 *             所属部门ID
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}

	/**
	 * @ManyToOne //一对多双向
	 * 
	 * @JoinColumn(name=" ") //需要指定外键与一的一端给的外键名称一致，
	 * 也可以不指定，如果在多的一端不指定，则一的一端也不能指定,否则为生成两个外键
	 * 该参数定义了所关联目标实体中的联接列.注意,当referencedColumnName关联到非主键列的时候,
	 * 关联的目标类必须实现Serializable,还要注意的是所映射的属性对应单个列(否则映射无效).
	 * 注意：在关系被维护端需要通过@JoinColumn建立外键列指向关系维护端的主键
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_code", referencedColumnName = "id")
	public BssysDeployEntity getParent() {
		return parent;
	}


	public void setParent(BssysDeployEntity parent) {
		this.parent = parent;
	}
	 
	 /**
	  * 一对多关联映射（双向），需要添加mappedBy属性，在一的一端中设置mappedBy,说明多的一端为主导 
	  * @return
	  */

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent")
	@Where(clause = "ISDELETED = 0")
	public List<BssysDeployEntity> getList() {
		return list;
	}
	

	public void setList(List<BssysDeployEntity> list) {
		this.list = list;
	}
}
