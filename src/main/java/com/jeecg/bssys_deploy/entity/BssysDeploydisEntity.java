package com.jeecg.bssys_deploy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 系统配置绑定行政区划表
 * @author onlineGenerator
 * @date 2017-05-23 09:15:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bssys_deploydis", schema = "")
@SuppressWarnings("serial")
public class BssysDeploydisEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**配置值*/
	@Excel(name="配置值")
	private java.lang.String value;
	/**行政区划ID*/
	@Excel(name="行政区划ID")
	private java.lang.String disId;
	/**排序*/
	private java.lang.Integer sort;
	/**创建人帐户*/
	private java.lang.String createBy;
	/**创建人姓名*/
	private java.lang.String createName;
	/**创建时间*/
	private java.util.Date createDate;
	/**更新人ID*/
	private java.lang.String updateBy;
	/**更新人姓名*/
	private java.lang.String updateName;
	/**末次修改时间*/
	private java.util.Date updateDate;
	/**是否已删除*/
	private java.lang.Integer isdeleted;
	/**所属部门ID*/
	private java.lang.String sysOrgCode;
	/**行政区划编码*/
	@Excel(name="行政区划编码")
	private java.lang.String disCode;
	/**系统配置ID*/
	@Excel(name="系统配置ID")
	private java.lang.String deployId;
	/**区划名称*/
	private java.lang.String regionName;
	/**配置名称*/
	private java.lang.String name;
	/** 数据表达式 */
	private java.lang.String dateExpr;
	/** 说明 */
	private java.lang.String description;
	/** 控件类型 */
	private java.lang.Integer controlType;

	@Transient
	public java.lang.String getRegionName() {
		return regionName;
	}
	
	public void setRegionName(java.lang.String regionName) {
		this.regionName = regionName;
	}
	@Transient
	public java.lang.String getName() {
		return name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	@Transient
	public java.lang.String getDateExpr() {
		return dateExpr;
	}
	
	public void setDateExpr(java.lang.String dateExpr) {
		this.dateExpr = dateExpr;
	}
	@Transient
	public java.lang.String getDescription() {
		return description;
	}
	
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	@Transient
	public java.lang.Integer getControlType() {
		return controlType;
	}
	
	public void setControlType(java.lang.Integer controlType) {
		this.controlType = controlType;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
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
	 *@return: java.lang.String  配置值
	 */
	@Column(name ="VALUE",nullable=true,length=50)
	public java.lang.String getValue(){
		return this.value;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  配置值
	 */
	public void setValue(java.lang.String value){
		this.value = value;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行政区划ID
	 */
	@Column(name ="DIS_ID",nullable=true,length=50)
	public java.lang.String getDisId(){
		return this.disId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政区划ID
	 */
	public void setDisId(java.lang.String disId){
		this.disId = disId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  排序
	 */
	@Column(name ="SORT",nullable=true,length=10)
	public java.lang.Integer getSort(){
		return this.sort;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  排序
	 */
	public void setSort(java.lang.Integer sort){
		this.sort = sort;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人帐户
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人帐户
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人姓名
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=64)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人姓名
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=7)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人ID
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人ID
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人姓名
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=64)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人姓名
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  末次修改时间
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=7)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  末次修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否已删除
	 */
	@Column(name ="ISDELETED",insertable=false,nullable=true,length=2)
	public java.lang.Integer getIsdeleted(){
		return this.isdeleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否已删除
	 */
	public void setIsdeleted(java.lang.Integer isdeleted){
		this.isdeleted = isdeleted;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门ID
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门ID
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行政区划编码
	 */
	@Column(name ="DIS_CODE",nullable=true,length=50)
	public java.lang.String getDisCode(){
		return this.disCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政区划编码
	 */
	public void setDisCode(java.lang.String disCode){
		this.disCode = disCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  系统配置ID
	 */
	@Column(name ="DEPLOY_ID",nullable=true,length=50)
	public java.lang.String getDeployId(){
		return this.deployId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  系统配置ID
	 */
	public void setDeployId(java.lang.String deployId){
		this.deployId = deployId;
	}
}
