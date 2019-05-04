package org.jeecgframework.web.system.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;

public interface TypegroupServiceI extends CommonService{
	
 	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TSTypegroup t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TSTypegroup t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TSTypegroup t);

	List<TSTypegroup> getAll();

	List<TSTypegroup> getChildren(String parentId);

	public TSTypegroup getDictionaryType(String typegroupcode);

	public int getCountNum(String typeCode);
	
	public int getCountNumUpdate(String typeCode, String id);
	
}
