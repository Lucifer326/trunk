package com.jeecg.system_region.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.jeecg.system_region.entity.SystemRegionEntity;
import com.jeecg.system_region.entity.SystemRegionImportEntity;

public interface SystemRegionServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(SystemRegionEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(SystemRegionEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(SystemRegionEntity t);
 	
 	List<SystemRegionEntity> getAll();
 	
 	List<SystemRegionEntity> getChildren(String pid);
 	/**
 	 * 验证区划code唯一
 	 * @param regionCode
 	 * @return
 	 */
 	public SystemRegionEntity getSystemRegionEntityByRegionCode(String regionCode);
 	public SystemRegionEntity getSystemRegionEntityById(String id);
 	
 	/**
 	 * 用来导入时循环保存区划
 	 * @param list
 	 */
 	public void saveSystemRegionList(List<SystemRegionImportEntity> list);

	public List<SystemRegionEntity> getRegionList(String[] str);
 	
}
