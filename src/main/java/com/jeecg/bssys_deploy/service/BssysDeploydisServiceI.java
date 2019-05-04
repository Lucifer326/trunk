package com.jeecg.bssys_deploy.service;
import java.io.Serializable;

import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;

import com.jeecg.bssys_deploy.entity.BssysDeploydisEntity;

public interface BssysDeploydisServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BssysDeploydisEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BssysDeploydisEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BssysDeploydisEntity t);
 	/**
	 * 分页条件查询
	 * 
	 * @param page
	 * @param rows
	 * @param where
	 * @param order
	 * @return
	 */
	public PageList getList(String deployId,String regionCode, DataGrid dataGrid);

	/**
	 * 条件查询总数
	 * 
	 * @param where
	 * @return
	 */
	public int getCount(String where);


}
