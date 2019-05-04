package org.jeecgframework.tag.comdesign.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 控件设计器
 * @author zkx
 * @date 2017-12-30 11:55:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cgform_control_design", schema = "")
@SuppressWarnings("serial")
public class CgformControlDesignEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**控件名称*/
	@Excel(name="控件名称")
	private java.lang.String name;
	/**控件类型id*/
	@Excel(name="控件类型id")
	private java.lang.String typeId;
	/**源码*/
	@Excel(name="源码")
	private java.lang.String sourceCode;
	/**属性*/
	@Excel(name="属性")
	private java.lang.String property;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**更新日期*/
	private java.util.Date updateDate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  控件名称
	 */
	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  控件名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  控件类型id
	 */
	@Column(name ="TYPE_ID",nullable=true,length=32)
	public java.lang.String getTypeId(){
		return this.typeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  控件类型id
	 */
	public void setTypeId(java.lang.String typeId){
		this.typeId = typeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  源码
	 */
	@Column(name ="SOURCE_CODE",nullable=true,length=1000)
	public java.lang.String getSourceCode(){
		return this.sourceCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  源码
	 */
	public void setSourceCode(java.lang.String sourceCode){
		this.sourceCode = sourceCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  属性
	 */
	@Column(name ="PROPERTY",nullable=true,length=1000)
	public java.lang.String getProperty(){
		return this.property;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  属性
	 */
	public void setProperty(java.lang.String property){
		this.property = property;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
}
