package org.jeecgframework.web.system.service.impl;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.dao.TypegroupMiniDao;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.TypegroupServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("typegroupService")
@Transactional
public class TypegroupServiceImpl extends CommonServiceImpl implements TypegroupServiceI {

	@Autowired
	private TypegroupMiniDao typegroupMiniDao;
	
	@CacheEvict(value="dictCacheTypegroup",allEntries=true,beforeInvocation=true)
 	public <T> void delete(T entity) {
		super.delete(entity);
 		
 		//执行删除操作配置的sql增强
		this.doDelSql((TSTypegroup)entity);
 	}
	
	
    @CacheEvict(value="dictCacheTypegroup",allEntries=true,beforeInvocation=true)
 	public <T> Serializable save(T entity) {
 		
		Serializable t = super.save(entity);
 		
 		//执行新增操作配置的sql增强
 		this.doAddSql((TSTypegroup)entity);
 		return t;
 	}
    @CacheEvict(value="dictCacheTypegroup",allEntries=true,beforeInvocation=true)
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((TSTypegroup)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TSTypegroup t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TSTypegroup t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TSTypegroup t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TSTypegroup t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
// 		sql  = sql.replace("#{parentid}",String.valueOf(t.getParentid()));
 		sql  = sql.replace("#{typegroupcode}",String.valueOf(t.getTypegroupcode()));
 		sql  = sql.replace("#{typegroupname}",String.valueOf(t.getTypegroupname()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{is_deleted}",String.valueOf(t.getIsDeleted()));
 		sql  = sql.replace("#{level_id}",String.valueOf(t.getLevelId()));
 		sql  = sql.replace("#{order_in_level}",String.valueOf(t.getOrderInLevel()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	/**
 	 * 获取所有第一级父类
 	 */
	public List<TSTypegroup> getAll() {
		return typegroupMiniDao.getAll();
	}
	
	/**
	 * 根据父ID获取直接后代
	 */
	@Cacheable(value = "dictCacheTypegroup", key = "#parentId")
	public List<TSTypegroup> getChildren(String parentId) {
		return typegroupMiniDao.getChildren(parentId);
	}
	
	
	public int getCountNum(String typeCode) {
		return typegroupMiniDao.getCountNum(typeCode);
	}

	public int getCountNumUpdate(String typeCode, String id) {
		return typegroupMiniDao.getCountNumUpdate(typeCode, id);
	}

	/**
	 * 根据字典类型编码 获得 类型对象
	 */
	@Cacheable(value = "dictCacheTypegroup", key="#typegroupcode")
	public TSTypegroup getDictionaryType(String typegroupcode) {
		return typegroupMiniDao.getDictionaryType(typegroupcode);
	}



}