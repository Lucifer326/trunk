package org.jeecgframework.web.system.service.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSTypeUnlessRegionEntity;
import org.jeecgframework.web.system.service.TypeUnlessRegionServiceI;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("typeUnlessRegionService")
@Transactional
public class TypeUnlessRegionServiceImpl extends CommonServiceImpl implements TypeUnlessRegionServiceI {

	@CacheEvict(value="dictCache",allEntries=true,beforeInvocation=true)
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TSTypeUnlessRegionEntity)entity);
 	}
 	 @CacheEvict(value="dictCache",allEntries=true,beforeInvocation=true)
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((TSTypeUnlessRegionEntity)entity);
 		return t;
 	}
 	@CacheEvict(value="dictCache",allEntries=true,beforeInvocation=true)
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((TSTypeUnlessRegionEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TSTypeUnlessRegionEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TSTypeUnlessRegionEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TSTypeUnlessRegionEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TSTypeUnlessRegionEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{dictionaryid}",String.valueOf(t.getDictionaryId()));
 		sql  = sql.replace("#{typeid}",String.valueOf(t.getTypeId()));
 		sql  = sql.replace("#{regionid}",String.valueOf(t.getRegionId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	
}