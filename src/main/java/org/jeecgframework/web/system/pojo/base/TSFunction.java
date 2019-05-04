package org.jeecgframework.web.system.pojo.base;
import java.util.ArrayList;
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

import org.jeecgframework.core.common.entity.IdEntity;

/**
 *菜单权限表
 * @author  张代浩
 */
@Entity
@Table(name = "t_s_function")
//update-begin--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
@org.hibernate.annotations.Proxy(lazy = false)
//update-end--Author:jp_renjie  Date:20150604 for：TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误

public class TSFunction extends IdEntity implements java.io.Serializable {
	private TSFunction TSFunction;//父菜单
	private String functionName;//菜单名称
	private Short functionLevel;//菜单等级
	private String functionUrl;//菜单地址
	private Short functionIframe;//菜单地址打开方式
	private String functionOrder;//菜单排序
	private Short functionType;//菜单类型
	private TSIcon TSIcon = new TSIcon();//菜单图标
    //        update-begin--Author:zhangguoming  Date:20140509 for：添加云桌面图标实体
	private TSIcon TSIconDesk;// 云桌面菜单图标
	
//  update-begin--Author:jg_longjb龙金波  Date:20150313 for：增加子菜单个数虚拟属性，避免使用getTSFunctions().size()导致过多sql
	/*private int subFunctionSize;
	@Formula(value = "(SELECT count(t_s_function.id) FROM t_s_function where t_s_function.parentfunctionid = id)")
	public int getSubFunctionSize() {
		return subFunctionSize;
	}*/
//  update-begin--Author:jg_gudongli辜栋利  Date:20150516 for：判断是否有子节点，利用已有数据不需要查询数据库	

	/**创建时间*/
	private java.util.Date createDate;
	/**创建人ID*/
	private java.lang.String createBy;
	/**创建人名称*/
	private java.lang.String createName;
	/**修改时间*/
	private java.util.Date updateDate;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改人名称*/
	private java.lang.String updateName;
	/**备注*/
	private java.lang.String functionremark;
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="create_date",nullable=true)
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
	 *@return: java.lang.String  创建人ID
	 */
	@Column(name ="create_by",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人ID
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	@Column(name ="create_name",nullable=true,length=32)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="update_date",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人ID
	 */
	@Column(name ="update_by",nullable=true,length=32)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人ID
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人名称
	 */
	@Column(name ="update_name",nullable=true,length=32)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	public boolean hasSubFunction(Map<Integer, List<TSFunction>> map) {
		if(map.containsKey(this.getFunctionLevel()+1)){
			return hasSubFunction(map.get(this.getFunctionLevel()+1));
		}
		return false;
	}
//  update-begin--Author:jg_gudongli辜栋利  Date:20150516 for：判断是否有子节点，利用已有数据不需要查询数据库	
	public boolean hasSubFunction(List<TSFunction> functions) {
		for (TSFunction f : functions) {
			if(f.getTSFunction().getId().equals(this.getId())){
				return true;
			}
		}
		return false;
	}
	/*public void setSubFunctionSize(int subFunctionSize) {
		this.subFunctionSize = subFunctionSize;
	}*/
//  update-end--Author:jg_longjb龙金波  Date:20150313 for：增加子菜单个数虚拟属性，避免使用getTSFunctions().size()导致过多sql
	
	
	//update-begin--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
    @ManyToOne(fetch = FetchType.EAGER)
    //update-end--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
    @JoinColumn(name = "desk_iconid")
    public TSIcon getTSIconDesk() {
        return TSIconDesk;
    }
    public void setTSIconDesk(TSIcon TSIconDesk) {
        this.TSIconDesk = TSIconDesk;
    }
    //        update-end--Author:zhangguoming  Date:20140509 for：添加云桌面图标实体
    
	private List<TSFunction> TSFunctions = new ArrayList<TSFunction>();
	//update-begin--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
	@ManyToOne(fetch = FetchType.EAGER)
	//update-end--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
	@JoinColumn(name = "iconid")
	public TSIcon getTSIcon() {
		return TSIcon;
	}
	public void setTSIcon(TSIcon tSIcon) {
		TSIcon = tSIcon;
	}
	
	//update-begin--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
    @ManyToOne(fetch = FetchType.EAGER)
    //update-end--Author:jp_renjie  Date:20150604 for： for:TSFunction关闭lazy，使得TSFunction在session关闭之后能够继续取得相关数据从而解决切换到云桌面时不能正常加载的错误
	@JoinColumn(name = "parentfunctionid")
	public TSFunction getTSFunction() {
		return this.TSFunction;
	}

	public void setTSFunction(TSFunction TSFunction) {
		this.TSFunction = TSFunction;
	}

	@Column(name = "functionname", nullable = false, length = 50)
	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Column(name = "functionlevel")
	public Short getFunctionLevel() {
		return this.functionLevel;
	}

	public void setFunctionLevel(Short functionLevel) {
		this.functionLevel = functionLevel;
	}
	
	@Column(name = "functiontype")
	public Short getFunctionType() {
		return this.functionType;
	}

	public void setFunctionType(Short functionType) {
		this.functionType = functionType;
	}
	
	@Column(name = "functionurl", length = 100)
	public String getFunctionUrl() {
		return this.functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	@Column(name = "functionorder")
	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSFunction")
	public List<TSFunction> getTSFunctions() {
		return TSFunctions;
	}
	public void setTSFunctions(List<TSFunction> TSFunctions) {
		this.TSFunctions = TSFunctions;
	}
	@Column(name = "functioniframe")
	public Short getFunctionIframe() {
		return functionIframe;
	}
	public void setFunctionIframe(Short functionIframe) {
		this.functionIframe = functionIframe;
	}
	@Column(name ="functionremark",nullable=true,length=1000)
	public java.lang.String getFunctionremark() {
		return functionremark;
	}

	public void setFunctionremark(java.lang.String functionremark) {
		this.functionremark = functionremark;
	}
	
	
}