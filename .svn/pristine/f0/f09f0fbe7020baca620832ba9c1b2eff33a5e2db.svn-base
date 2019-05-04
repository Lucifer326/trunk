package org.jeecgframework.web.system.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypeUnlessRegionEntity;

import com.jeecg.system_region.entity.SystemRegionEntity;

public interface TypeServiceI extends CommonService{
	
	public <T> void delete(T entity);
 	
 	public <T> Serializable save(T entity);
 	
 	public <T> void saveOrUpdate(T entity);
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TSType t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TSType t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TSType t);

	List<SystemRegionEntity> getAll();
	
	
	List<TSType> getAllTypes(String typegroupId);
	List<TSType> getChildren(String typePid);
	
	List<TSTypeUnlessRegionEntity> getRegionId(String typegroupeId,String typeId);
	
	int deletedRegionId(String typeId,String dictionaryId);
	
	List<TSType> getItems(String typegroupId);
	
	List<TSType> getItemsByTypeIdAndRegionId(String typegroupid,String regionId);
	
	List<TSType> getItemsByTypegroupcodeAndRegionId(String typegroupcode,String regionId);
	List<TSType> getItemsByTypeIdAndRegionIdAndItemKey(String typecode,String typegroupId,String regionId);
	
	public List<TSType> getItemsByTypegroupcodeAndRegionId(String typegroupcode,String regionId,String parentid);

	public TSType getTypenameByTypegroupcodeAndTypecode(String typegroupcode, String typecode);

	public TSType getTypecodeByTypegroupcodeAndTypename(String typegroupcode, String orgTypename);
}
