package org.jeecgframework.web.system.pojo.base;
// default package

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * TTypegroup entity.
 */
@Entity
@Table(name = "t_s_typegroup")
public class TSTypegroup extends IdEntity implements java.io.Serializable {
	public static Map<String, TSTypegroup> allTypeGroups = new HashMap<String,TSTypegroup>();
	public static Map<String, List<TSType>> allTypes = new HashMap<String,List<TSType>>();
	
	
		//字典的上级
		private TSTypegroup TSTypegroup;
		
		//字典的下级
		private List<TSType> TSTypes = new ArrayList<TSType>();
	
		
		
		
		
		private List<TSTypegroup> list = new ArrayList<TSTypegroup>();
		
		
		
		
		
	
		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "TSTypegroup")
		@Where(clause = "IS_DELETED = 0")
		public List<TSTypegroup> getList() {
			return list;
		}

		public void setList(List<TSTypegroup> list) {
			this.list = list;
		}

	private String typegroupname;
	
	private String typegroupcode;
	
	/**版本*/
	@Excel(name="版本")
	private java.lang.String version;
	/**已删除*/
	@Excel(name="已删除")
	private java.lang.Integer isDeleted;
	/**类型层次*/
	@Excel(name="类型层次")
	private java.lang.String levelId;
	/**在同层兄*/
	@Excel(name="在同层兄")
	private java.lang.Integer orderInLevel;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarks;
	/**创建人名*/
	@Excel(name="创建人名")
	private java.lang.String createName;
	/**创建人登*/
	@Excel(name="创建人登")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人*/
	@Excel(name="更新人")
	private java.lang.String updateName;
	/**更新人*/
	@Excel(name="更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name="更新日期",format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**所属部门*/
	@Excel(name="所属部门")
	private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name="所属公司")
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	@Excel(name="流程状态")
	private java.lang.String bpmStatus;
	
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id",referencedColumnName = "ID")
	public TSTypegroup getTSTypegroup() {
		return TSTypegroup;
	}

	public void setTSTypegroup(TSTypegroup TSTypegroup) {
		this.TSTypegroup = TSTypegroup;
	}
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSTypegroup")
	public List<TSType> getTSTypes() {
		return this.TSTypes;
	}

	public void setTSTypes(List<TSType> TSTypes) {
		this.TSTypes = TSTypes;
	}

	
	
/*********************************************************/
	
	
	@Column(name = "typegroupname", length = 50)
	public String getTypegroupname() {
		return this.typegroupname;
	}

	public void setTypegroupname(String typegroupname) {
		this.typegroupname = typegroupname;
	}

	@Column(name = "typegroupcode", length = 50)
	public String getTypegroupcode() {
		return this.typegroupcode;
	}

	public void setTypegroupcode(String typegroupcode) {
		this.typegroupcode = typegroupcode;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本
	 */
	@Column(name ="VERSION",insertable=true,nullable=true,length=50)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  已删除
	 */
	@Column(name ="IS_DELETED",insertable=true,nullable=true,length=10)
	public java.lang.Integer getIsDeleted(){
		return this.isDeleted;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  已删除
	 */
	public void setIsDeleted(java.lang.Integer isDeleted){
		this.isDeleted = isDeleted;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  类型层次
	 */
	@Column(name ="LEVEL_ID",insertable=true,nullable=true,length=10)
	public java.lang.String getLevelId(){
		return this.levelId;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  类型层次
	 */
	public void setLevelId(java.lang.String levelId){
		this.levelId = levelId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  在同层兄
	 */
	@Column(name ="ORDER_IN_LEVEL",insertable=true,nullable=true,length=10)
	public java.lang.Integer getOrderInLevel(){
		return this.orderInLevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  在同层兄
	 */
	public void setOrderInLevel(java.lang.Integer orderInLevel){
		this.orderInLevel = orderInLevel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKS",insertable=true,nullable=true,length=50)
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
	 *@return: java.lang.String  创建人名
	 */
	@Column(name ="CREATE_NAME",insertable=true,nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登
	 */
	@Column(name ="CREATE_BY",insertable=true,nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",insertable=true,nullable=true,length=10)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */
	@Column(name ="UPDATE_NAME",insertable=true,nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */
	@Column(name ="UPDATE_BY",insertable=true,nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",insertable=true,nullable=true,length=10)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	@Column(name ="SYS_ORG_CODE",insertable=true,nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	@Column(name ="SYS_COMPANY_CODE",insertable=true,nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */
	@Column(name ="BPM_STATUS",insertable=true,nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}

	
}