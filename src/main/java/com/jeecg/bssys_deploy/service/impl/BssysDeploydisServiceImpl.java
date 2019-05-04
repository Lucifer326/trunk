package com.jeecg.bssys_deploy.service.impl;
import java.io.Serializable;
import java.util.UUID;

import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.bssys_deploy.entity.BssysDeploydisEntity;
import com.jeecg.bssys_deploy.service.BssysDeploydisServiceI;

@Service("bssysDeploydisService")
@Transactional
public class BssysDeploydisServiceImpl extends CommonServiceImpl implements BssysDeploydisServiceI {

	
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((BssysDeploydisEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((BssysDeploydisEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((BssysDeploydisEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(BssysDeploydisEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(BssysDeploydisEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(BssysDeploydisEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,com.jeecg.bssys_deploy.entity.BssysDeploydisEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{value}",String.valueOf(t.getValue()));
 		sql  = sql.replace("#{dis_id}",String.valueOf(t.getDisId()));
 		sql  = sql.replace("#{sort}",String.valueOf(t.getSort()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{isdeleted}",String.valueOf(t.getIsdeleted()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{dis_code}",String.valueOf(t.getDisCode()));
 		sql  = sql.replace("#{deploy_id}",String.valueOf(t.getDeployId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	} 	
 	/**
	 * 分页条件查询
	 */
	@Override
	public PageList getList(String deployId, String regionCode, DataGrid dataGrid) {
		//拼接SQL
				StringBuffer sb = new StringBuffer();
				
				sb.append("select dedis.id,dis.region_code as disCode,dis.region_name as regionName, de.name as name, dedis.value as value, de.description as description, dedis.deploy_id, ");
				sb.append("dedis.dis_id,dedis.create_date,");
				sb.append("de.date_expr,de.control_type from bssys_deploydis dedis ");
				sb.append("join bssys_deploy de on dedis.deploy_id = de.id ");
				sb.append("join system_region dis on dedis.dis_id = dis.id where dedis.isdeleted = 0 ");
				sb.append("and de.isdeleted = 0" );
				
				
				//如果配置id 不为空则 拼接 条件
				if(deployId != "" && deployId != null){
					
					sb.append("and dedis.DEPLOY_ID ='"+ deployId +"'");
				}
				//如果区划id 不为空则 拼接 条件
				if(regionCode != "" && regionCode != null){
					
					sb.append("and dedis.DIS_CODE in(SELECT REGION_CODE FROM SYSTEM_REGION start with region_code = '"+ regionCode +"' connect by prior id = PARENT_ID) ");
				}
				
				sb.append("order by dedis.create_date desc");
				
				HqlQuery hqlQuery = new HqlQuery("");
				
				hqlQuery.setQueryString(sb.toString());
				hqlQuery.setDataGrid(dataGrid);
				hqlQuery.setClass1(BssysDeploydisEntity.class);
				hqlQuery.setCurPage(dataGrid.getPage());
				hqlQuery.setPageSize(dataGrid.getRows());
				
				PageList pageList = commonDao.getPageListBySql(hqlQuery,true);
				
				return pageList;
	}

	/**
	 * 条件查询总数
	 */
	@Override
	public int getCount(String where) {
		//sql
				String sql = "select count(dedis.id) from bssys_deploydis dedis "
						+ "left join bssys_deploy de on dedis.deploy_id = de.id "
						+ "left join system_region dis on dedis.dis_id = dis.id where dedis.isdeleted = 0 "+ where +" "
								+ "order by dedis.create_date desc";
				
				Long num = commonDao.getCountForJdbc(sql);
				
				//有结果直接返回，没有结果返回0
				if(num != 0){
					
					return num.intValue();
				}
				return 0;
	}
}