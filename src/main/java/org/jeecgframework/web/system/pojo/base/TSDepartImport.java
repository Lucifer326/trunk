package org.jeecgframework.web.system.pojo.base;

import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 导入导出部门机构表
 * 
 * @author 张代浩
 */

public class TSDepartImport extends IdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Excel(name = "组织机构名称")
	private String departname;// 组织机构名称
	// @Excel(name = "机构编码")
	private String orgCode;// 机构编码
	@Excel(name = "机构类型")
	private String orgType;// 机构类型
	@Excel(name = "组织机构描述")
	private String description;// 组织机构描述
	@Excel(name = "电话")
	private String mobile;// 电话
	@Excel(name = "传真")
	private String fax;// 传真
	@Excel(name = "地址")
	private String address;// 地址
	@Excel(name = "区划编码")
	private String regionCode;
	@Excel(name = "区划id")
	private String regionId;// 区划id
	@Excel(name = "上级部门名称")
	private String parentdepartname;// 上级机构名称

	public String getParentdepartname() {
		return parentdepartname;
	}

	public void setParentdepartname(String parentdepartname) {
		this.parentdepartname = parentdepartname;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getDepartname() {
		return this.departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

}