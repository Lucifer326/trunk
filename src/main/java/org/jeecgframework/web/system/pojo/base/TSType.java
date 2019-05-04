package org.jeecgframework.web.system.pojo.base;
// default package

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 通用类型字典表
 *  @author  张代浩
 */
@Entity
@Table(name = "t_s_type")
public class TSType extends IdEntity implements java.io.Serializable {

	private TSTypegroup TSTypegroup;//类型分组 
	
	private TSType TSType;//父类型 
	
	
	//分层的子集
	private List<TSType> list = new ArrayList<TSType>();
	
	
	private String typename;//类型名称
	
	private String typecode;//类型编码
	
	
	
	/**简称*/
	@Excel(name="简称")
	private java.lang.String simpleName;
	/**版本（备用字段）*/
	@Excel(name="版本（备用字段）")
	private java.lang.String version;
	/**是否系统内置*/
	@Excel(name="是否系统内置")
	private java.lang.Integer isSys;
	/**是否停用*/
	@Excel(name="是否停用")
	private java.lang.Integer isStop;
	/**已删除*/
	@Excel(name="已删除")
	private java.lang.Integer isDeleted;
	/**本类型下的次序*/
	@Excel(name="本类型下的次序")
	private java.lang.Integer orderInLevel;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarks;
	/**创建人名称*/
	@Excel(name="创建人名称")
	private java.lang.String createName;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称")
	private java.lang.String createBy;
	/**创建日期（平台默认创建，暂不使用）*/
	@Excel(name="创建日期",format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人名称*/
	@Excel(name="更新人名称")
	private java.lang.String updateName;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称")
	private java.lang.String updateBy;
	/**更新日期（平台默认创建，暂不使用）*/
	@Excel(name="更新日期",format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**所属部门*/
	@Excel(name="所属部门")
	private java.lang.String sysOrgCode;
	/**所属公司（平台默认创建，暂不使用）*/
	@Excel(name="所属公司")
	private java.lang.String sysCompanyCode;
	/**流程状态（平台默认创建，暂不使用）*/
	@Excel(name="流程状态")
	private java.lang.String bpmStatus;
	
	
	
//	private List<TPProcess> TSProcesses = new ArrayList();
	private List<TSType> TSTypes =new ArrayList<TSType>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typegroupid")
	public TSTypegroup getTSTypegroup() {
		return this.TSTypegroup;
	}

	public void setTSTypegroup(TSTypegroup TSTypegroup) {
		this.TSTypegroup = TSTypegroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typepid")
	public TSType getTSType() {
		return this.TSType;
	}

	public void setTSType(TSType TSType) {
		this.TSType = TSType;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "TSType")
	@Where(clause = "IS_DELETED = 0")
	public List<TSType> getList() {
		return list;
	}

	public void setList(List<TSType> list) {
		this.list = list;
	}

	@Column(name = "typename", length = 50)
	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	@Column(name = "typecode", length = 50)
	public String getTypecode() {
		return this.typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSType")
//	public List<TPProcess> getTSProcesses() {
//		return this.TSProcesses;
//	}
//
//	public void setTSProcesses(List<TPProcess> TSProcesses) {
//		this.TSProcesses = TSProcesses;
//	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSType")
	public List<TSType> getTSTypes() {
		return this.TSTypes;
	}

	public void setTSTypes(List<TSType> TSTypes) {
		this.TSTypes = TSTypes;
	}
/*************************更新*******************************************/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简称
	 */
	@Column(name ="SIMPLE_NAME",nullable=true,length=50)
	public java.lang.String getSimpleName(){
		return this.simpleName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简称
	 */
	public void setSimpleName(java.lang.String simpleName){
		this.simpleName = simpleName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本（备用字段）
	 */
	@Column(name ="VERSION",nullable=true,length=50)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本（备用字段）
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否系统内置。1：内置（不允许修改），0：非内置。默认为0
	 */
	@Column(name ="IS_SYS",insertable=true,nullable=true,length=10)
	public java.lang.Integer getIsSys(){
		return this.isSys;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否系统内置。1：内置（不允许修改），0：非内置。默认为0
	 */
	public void setIsSys(java.lang.Integer isSys){
		this.isSys = isSys;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否停用。1：已停用（不允许引用），0：正常使用。默认为0
	 */
	@Column(name ="IS_STOP",insertable=true,nullable=true,length=10)
	public java.lang.Integer getIsStop(){
		return this.isStop;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否停用。1：已停用（不允许引用），0：正常使用。默认为0
	 */
	public void setIsStop(java.lang.Integer isStop){
		this.isStop = isStop;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  已删除。0：未删除，1：已删除
	 */
	@Column(name ="IS_DELETED",insertable=true,nullable=true,length=10)
	public java.lang.Integer getIsDeleted(){
		return this.isDeleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  已删除。0：未删除，1：已删除
	 */
	public void setIsDeleted(java.lang.Integer isDeleted){
		this.isDeleted = isDeleted;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  本类型下的次序，从0开始，由小到大排列
	 */
	@Column(name ="ORDER_IN_LEVEL",nullable=true,length=10)
	public java.lang.Integer getOrderInLevel(){
		return this.orderInLevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  本类型下的次序，从0开始，由小到大排列
	 */
	public void setOrderInLevel(java.lang.Integer orderInLevel){
		this.orderInLevel = orderInLevel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",nullable=true,length=50)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称（平台默认创建，暂不使用）
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称（平台默认创建，暂不使用）
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称（平台默认创建，暂不使用）
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称（平台默认创建，暂不使用）
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期（平台默认创建，暂不使用）
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=10)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期（平台默认创建，暂不使用）
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称（平台默认创建，暂不使用）
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称（平台默认创建，暂不使用）
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称（平台默认创建，暂不使用）
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称（平台默认创建，暂不使用）
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期（平台默认创建，暂不使用）
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=10)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期（平台默认创建，暂不使用）
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门（平台默认创建，暂不使用）
	 */
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门（平台默认创建，暂不使用）
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司（平台默认创建，暂不使用）
	 */
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司（平台默认创建，暂不使用）
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态（平台默认创建，暂不使用）
	 */
	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态（平台默认创建，暂不使用）
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	
}