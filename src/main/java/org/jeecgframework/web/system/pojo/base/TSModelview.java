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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Formula;
import org.jeecgframework.core.common.entity.IdEntity;
/**
* @author hyzx 
* @date : 2017年11月28日 上午9:11:55
*/
@Entity
@Table(name = "t_s_modelview")
@org.hibernate.annotations.Proxy(lazy = false)

public class TSModelview extends IdEntity implements java.io.Serializable {
	
	
	private TSModelview tsModelview;
	private String functionName;
	private Short functionLevel;
	private String functionUrl;
	private String functionUrlt;
	private String functionOrder;
	

public boolean hasSubFunction(Map<Integer, List<TSModelview>> map) {
		if(map.containsKey(this.getFunctionLevel()+1)){
			return hasSubFunction(map.get(this.getFunctionLevel()+1));
		}
		return false;
	}

	public boolean hasSubFunction(List<TSModelview> functions) {
		for (TSModelview f : functions) {
			if(f.getTsModelview().getId().equals(this.getId())){
				return true;
			}
		}
		return false;
	}
	
  @ManyToOne(fetch = FetchType.EAGER)
  private List<TSModelview> tsModelviews = new ArrayList<TSModelview>();

  

	@Column(name = "cnname", nullable = false, length = 50)
	public String getFunctionName() {
		return this.functionName;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pid")
	public TSModelview getTsModelview() {
		return tsModelview;
	}
	public void setTsModelview(TSModelview tsModelview) {
		this.tsModelview = tsModelview;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Column(name = "ifleaf")
	public Short getFunctionLevel() {
		return this.functionLevel;
	}

	public void setFunctionLevel(Short functionLevel) {
		this.functionLevel = functionLevel;
	}

	@Column(name = "viewnameori", length = 100)
	public String getFunctionUrl() {
		return this.functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}
	
	@Column(name = "viewname", length = 100)
	public String getFunctionUrlt() {
		return this.functionUrlt;
	}

	public void setFunctionUrlt(String functionUrlt) {
		this.functionUrlt = functionUrlt;
	}
	
	@Column(name = "oid")
	public String getFunctionOrder() {
		return functionOrder;
	}

	public void setFunctionOrder(String functionOrder) {
		this.functionOrder = functionOrder;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tsModelview")
	public List<TSModelview> getTSModelviews() {
		return tsModelviews;
	}
	public void setTSModelviews(List<TSModelview> TSModelviews) {
		this.tsModelviews = TSModelviews;
	}
	

}