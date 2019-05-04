package com.jeecg.bssys_deploy.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.SystemRegionContorl;

import com.jeecg.bssys_deploy.entity.BssysDeployEntity;

public interface BssysDeployServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BssysDeployEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BssysDeployEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BssysDeployEntity t);
 	/**
 	 * 获取指定编码对应系统配置项的Value
 	 * @param deployId 系统配置ID
 	 * @param disCode 行政区划编码
 	 * @return
 	 */
 	public String getValue(String deployId,String disCode);
 	
 	public SystemRegionContorl saveSytemRegionControl(SystemRegionContorl entity);

	public SystemRegionContorl querySystemRegionControl();
}
