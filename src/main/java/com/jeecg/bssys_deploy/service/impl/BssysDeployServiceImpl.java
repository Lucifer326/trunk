package com.jeecg.bssys_deploy.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.web.system.pojo.base.SystemRegionContorl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.bssys_deploy.entity.BssysDeployEntity;
import com.jeecg.bssys_deploy.service.BssysDeployServiceI;

/**
 * @author Administrator
 *
 */
@Service("bssysDeployService")
@Transactional
public class BssysDeployServiceImpl extends CommonServiceImpl implements BssysDeployServiceI {
	@Autowired
	private CommonDao commonDao;

	public <T> void delete(T entity) {
		super.delete(entity);
		// 执行删除操作配置的sql增强
		this.doDelSql((BssysDeployEntity) entity);
	}

	public <T> Serializable save(T entity) {
		Serializable t = super.save(entity);
		// 执行新增操作配置的sql增强
		this.doAddSql((BssysDeployEntity) entity);
		return t;
	}

	public <T> void saveOrUpdate(T entity) {
		super.saveOrUpdate(entity);
		// 执行更新操作配置的sql增强
		this.doUpdateSql((BssysDeployEntity) entity);
	}

	/**
	 * 默认按钮-sql增强-新增操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doAddSql(BssysDeployEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-更新操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doUpdateSql(BssysDeployEntity t) {
		return true;
	}

	/**
	 * 默认按钮-sql增强-删除操作
	 * 
	 * @param id
	 * @return
	 */
	public boolean doDelSql(BssysDeployEntity t) {
		return true;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @return
	 */
	public String replaceVal(String sql, BssysDeployEntity t) {
		sql = sql.replace("#{id}", String.valueOf(t.getId()));
		sql = sql.replace("#{name}", String.valueOf(t.getName()));
		sql = sql.replace("#{code}", String.valueOf(t.getCode()));
		sql = sql.replace("#{date_expr}", String.valueOf(t.getDateExpr()));
		// sql = sql.replace("#{value_type}",String.valueOf(t.getValueType()));
		sql = sql.replace("#{description}", String.valueOf(t.getDescription()));
		// sql =
		// sql.replace("#{parent_code}",String.valueOf(t.getParentCode()));
		sql = sql.replace("#{control_type}", String.valueOf(t.getControlType()));
		sql = sql.replace("#{value}", String.valueOf(t.getValue()));
		// sql = sql.replace("#{sort}",String.valueOf(t.getSort()));
		sql = sql.replace("#{is_binddis}", String.valueOf(t.getIsBinddis()));
		sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
		sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
		sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
		sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
		sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
		sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
		sql = sql.replace("#{isdeleted}", String.valueOf(t.getIsdeleted()));
		sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
		sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
		return sql;
	}

	/**
	 * 获取指定编码对应系统配置项的Value
	 * 
	 * @param deployId
	 *            系统配置ID
	 * @param disCode
	 *            行政区划编码
	 * @return
	 */
	@Override
	public String getValue(String deployId, String disCode) {
		BssysDeployEntity deploy = getEntity(BssysDeployEntity.class, deployId);

		if (deploy == null) {
			return null;
		}
		String value = null;
		Integer isBinddis = deploy.getIsBinddis();
		if (isBinddis == 0) {
			return deploy.getValue();
		} else if (isBinddis == 1) {
			if (disCode == null || "".equals(disCode)) {
				return deploy.getValue();
			}
			String sql = "select dedis.value,REGION.LEVELDESC "
					+ "from bssys_deploydis dedis , (select s.id,s.REGION_CODE,SYS_CONNECT_BY_PATH(id, '/'), "
					+ "length(SYS_CONNECT_BY_PATH(id, '/')) - length(replace(SYS_CONNECT_BY_PATH(id, '/'),'/','')) "
					+ "leveldesc from SYSTEM_REGION s start with REGION_CODE = '" + disCode
					+ "' connect by prior PARENT_ID = id) region "
					+ "where DEDIS.DIS_CODE = REGION.REGION_CODE and dedis.isdeleted = 0 and dedis.deploy_id = '" + deployId
					+ "' order by REGION.LEVELDESC";

			List<Object[]> list = commonDao.findListbySql(sql);
			if (null != list && list.size() > 0) {

				Object[] o = list.get(0);
				return o[0].toString();
			}
		}
		return value;
	}

	@Override
	public SystemRegionContorl saveSytemRegionControl(SystemRegionContorl entity) {
		commonDao.saveOrUpdate(entity);
		return entity;
	}

	@Override
	public SystemRegionContorl querySystemRegionControl() {
		SystemRegionContorl entity = null;
		String hql = " from SystemRegionContorl";
		List<SystemRegionContorl> list = commonDao.findHql(hql);
		if(list.size()>0){
			entity = list.get(0);
		}
		return entity;
	}
}