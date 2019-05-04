package org.jeecgframework.web.system.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypeUnlessRegionEntity;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.system_region.entity.SystemRegionEntity;

@Service("typeService")
@Transactional
public class TypeServiceImpl extends CommonServiceImpl implements TypeServiceI {

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((TSType) entity);
	}

	@CacheEvict(value = "dictCacheType", allEntries = true, beforeInvocation = true)
	public <T> Serializable save(T entity) {

		// System.out.println("删除，保存时进行缓存清除");
		Serializable t = super.save(entity);

		// 执行新增操作配置的sql增强

		this.doAddSql((TSType) entity);
		return t;
	}

	@CacheEvict(value = "dictCacheType", allEntries = true, beforeInvocation = true)
	public <T> void saveOrUpdate(T entity) {

		// System.out.println("修改时进行缓存清除");
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((TSType) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(TSType t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(TSType t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(TSType t) {
		return true;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @return
	 */
	public String replaceVal(String sql, TSType t) {
		sql = sql.replace("#{id}", String.valueOf(t.getId()));
		// sql = sql.replace("#{type_id}",String.valueOf(t.getTypeId()));
		sql = sql.replace("#{typecode}", String.valueOf(t.getTypecode()));
		sql = sql.replace("#{typename}", String.valueOf(t.getTypename()));
		sql = sql.replace("#{simple_name}", String.valueOf(t.getSimpleName()));
		sql = sql.replace("#{version}", String.valueOf(t.getVersion()));
		sql = sql.replace("#{is_sys}", String.valueOf(t.getIsSys()));
		sql = sql.replace("#{is_stop}", String.valueOf(t.getIsStop()));
		sql = sql.replace("#{is_deleted}", String.valueOf(t.getIsDeleted()));
		sql = sql.replace("#{order_in_type}", String.valueOf(t.getOrderInLevel()));
		sql = sql.replace("#{remarks}", String.valueOf(t.getRemarks()));
		sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
		sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
		sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
		sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
		sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
		sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
		sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
		sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
		sql = sql.replace("#{bpm_status}", String.valueOf(t.getBpmStatus()));
		sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
		return sql;
	}

	public List<SystemRegionEntity> getAll() {

		String hql = "from SystemRegionEntity";
		return commonDao.findHql(hql);
	}

	/**
	 * 根据字典表(t_s_type)id 和 类型表(t_s_typegroup)id 从 关联表(t_s_type_unless_region)中获得
	 * 对应的区划id
	 */
	public List<TSTypeUnlessRegionEntity> getRegionId(String typegroupeId, String typeId) {

		String hql = "from TSTypeUnlessRegionEntity where typeId = ? and dictionary_id = ?";
		return commonDao.findHql(hql, typegroupeId, typeId);
	}

	/**
	 * 删除对应的 区划
	 */
	public int deletedRegionId(String typeId, String dictionaryId) {
		String hql = "delete from TSTypeUnlessRegionEntity tug where tug.typeId = ? and tug.dictionaryId = ?";
		return commonDao.executeHql(hql, typeId, dictionaryId);
	}

	/**
	 * 根据字典类型id typegroupId 获得所有的条目数
	 */
	@Cacheable(value = "dictCacheType")
	public List<TSType> getItems(String typegroupId) {
		String hql = "from TSType t where t.isDeleted = 0 and t.isStop = 0 and t.TSTypegroup.id = ?";
		return commonDao.findHql(hql, typegroupId);
	}

	/**
	 * 根据类型id 和 区划id 获得对应的字典条目
	 * 
	 */
	@Cacheable(value = "dictCacheType")
	public List<TSType> getItemsByTypeIdAndRegionId(String typegroupid, String regionId) {

		String hql = "from TSType where isDeleted = 0 " + "and typegroupid = ? and isStop = 0 "
				+ "and id not in (select dictionaryId from TSTypeUnlessRegionEntity where regionId = ?)";

		return commonDao.findHql(hql, typegroupid, regionId);
	}

	/**
	 * 根据类型id 和 区划 id 和 条目键 获得 值
	 */
	@Cacheable(value = "dictCacheType")
	public List<TSType> getItemsByTypeIdAndRegionIdAndItemKey(String typecode, String typegroupid, String regionId) {

		String hql = "from TSType where isDeleted = 0 and isStop = 0" 
		+ " and typecode = ? and typegroupid = ?"
				+ " and id not in (select dictionaryId from TSTypeUnlessRegionEntity where regionId = ?)";
		return commonDao.findHql(hql, typecode, typegroupid, regionId);
	}

	/**
	 * 接口 根据typegroupcode (字典类型编码) 和 regionId(区划id) 查询符合条件的全部字典条目 如果 区划为空 则返回 该
	 * 字典类型编码 下的 所有可用 条目 如果 字典类型编码为空，则返回空
	 */
	@Cacheable(value = "dictCacheType")
	public List<TSType> getItemsByTypegroupcodeAndRegionId(String typegroupcode, String regionId) {

		String hql = " from TSType where isDeleted = 0 and isStop = 0 and typegroupid = "
				+ "(select id from TSTypegroup where typegroupcode = ? and isDeleted = 0) and id "
				+ "not in (select dictionaryId from TSTypeUnlessRegionEntity where regionId = ?) order by orderInLevel";
		// System.out.println("第一次进getItemsByTypegroupcodeAndRegionId查询");
		return commonDao.findHql(hql, typegroupcode, regionId);
	}

	/**
	 * 根据字典条目表的父id typePid 查找直接后代
	 */
	public List<TSType> getChildren(String typePid) {

		String hql = "FROM TSType t WHERE t.isDeleted = 0 and t.isStop = 0 and t.TSType.id = ?";
		return commonDao.findHql(hql, typePid);
	}

	/**
	 * 根据typegroupId 获得所有第一层的条目
	 */
	public List<TSType> getAllTypes(String typegroupId) {

		String hql = "from TSType where TSTypegroup.id = ? and isDeleted = 0 and isStop = 0 and TSType is null";
		List<TSType> list = commonDao.findHql(hql, typegroupId);
		if (list.size() > 0) {
			return list;
		}
		return null;
	}

	@Cacheable(value = "dictCacheType")
	public List<TSType> getItemsByTypegroupcodeAndRegionId(String typegroupcode, String regionId,
			String parentTypecode) {
		StringBuffer hql = new StringBuffer();

		hql.append("from TSType t where t.isDeleted = 0 and t.isStop = 0 and t.TSTypegroup.id ="
				+ "(select tg.id from TSTypegroup tg where tg.typegroupcode = ? and tg.isDeleted = 0) and t.id "
				+ "not in (select r.dictionaryId from TSTypeUnlessRegionEntity r where r.regionId = ?)");

		if (parentTypecode != null && parentTypecode != "") {

			hql.append(" and t.TSType.typecode = ?");
			hql.append(" order by t.orderInLevel");
		} else {

			hql.append(" and t.TSType IS NULL");
			hql.append(" order by t.orderInLevel");

			return commonDao.findHql(hql.toString(), typegroupcode, regionId);
		}
		return commonDao.findHql(hql.toString(), typegroupcode, regionId, parentTypecode);
	}

	@Override
	public TSType getTypenameByTypegroupcodeAndTypecode(String typegroupcode, String typecode) {
		String hql = "from TSType t WHERE t.TSTypegroup.id = (SELECT id FROM TSTypegroup tg where tg.typegroupcode = ?"
				+ "AND tg.isDeleted = 0) and t.typecode = ? and t.isDeleted = 0 and t.isStop = 0";
		List<TSType> list = commonDao.findHql(hql, typegroupcode, typecode);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public TSType getTypecodeByTypegroupcodeAndTypename(String typegroupcode, String orgTypename) {
		String hql = "from TSType t WHERE t.TSTypegroup.id = (SELECT id FROM TSTypegroup tg where tg.typegroupcode = ?"
				+ "AND tg.isDeleted = 0) and t.typename= ? and t.isDeleted = 0 and t.isStop = 0";
		List<TSType> list = commonDao.findHql(hql, typegroupcode, orgTypename);
		if (list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
}