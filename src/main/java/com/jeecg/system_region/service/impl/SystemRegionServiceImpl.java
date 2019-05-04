package com.jeecg.system_region.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecg.system_region.dao.SystemRegionMiniDao;
import com.jeecg.system_region.entity.SystemRegionEntity;
import com.jeecg.system_region.entity.SystemRegionImportEntity;
import com.jeecg.system_region.service.SystemRegionServiceI;

@Service("systemRegionService")
@Transactional
public class SystemRegionServiceImpl extends CommonServiceImpl implements SystemRegionServiceI {
	
	@Autowired
	private SystemRegionMiniDao systemRegionMiniDao;
	@Autowired
	private CommonDao commonDao;
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((SystemRegionEntity)entity);
 	}
 	
 	public <T> Serializable save(T entity) {
 		Serializable t = super.save(entity);
 		//执行新增操作配置的sql增强
 		this.doAddSql((SystemRegionEntity)entity);
 		return t;
 	}
 	
 	public <T> void saveOrUpdate(T entity) {
 		super.saveOrUpdate(entity);
 		//执行更新操作配置的sql增强
 		this.doUpdateSql((SystemRegionEntity)entity);
 	}
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(SystemRegionEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(SystemRegionEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(SystemRegionEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,SystemRegionEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{region_code}",String.valueOf(t.getRegionCode()));
 		sql  = sql.replace("#{region_name}",String.valueOf(t.getRegionName()));
 		sql  = sql.replace("#{region_short_name}",String.valueOf(t.getRegionShortName()));
 		sql  = sql.replace("#{region_level}",String.valueOf(t.getRegionLevel()));
 		sql  = sql.replace("#{area_code}",String.valueOf(t.getAreaCode()));
 		sql  = sql.replace("#{region_postcode}",String.valueOf(t.getRegionPostcode()));
 		sql  = sql.replace("#{region_area}",String.valueOf(t.getRegionArea()));
 		sql  = sql.replace("#{region_create_date}",String.valueOf(t.getRegionCreateDate()));
 		sql  = sql.replace("#{revoke_date}",String.valueOf(t.getRevokeDate()));
 		sql  = sql.replace("#{region_encampment}",String.valueOf(t.getRegionEncampment()));
 		sql  = sql.replace("#{region_population}",String.valueOf(t.getRegionPopulation()));
 		sql  = sql.replace("#{region_county_population}",String.valueOf(t.getRegionCountyPopulation()));
// 		sql  = sql.replace("#{parent_id}",String.valueOf(t.getParentId()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	public List<SystemRegionEntity> getAll() {
		return systemRegionMiniDao.getAll();
	}

	@Override
	public List<SystemRegionEntity> getChildren(String pid) {
		// TODO Auto-generated method stub
		return systemRegionMiniDao.getChildren(pid);
	}
	
	
 	/**
 	 * 
 	 * @param regionCode
 	 * @return
 	 */
 	public SystemRegionEntity getSystemRegionEntityByRegionCode(String regionCode){
 		
 		
 		String hql = "from SystemRegionEntity where regionCode = ?";
 		
 		List<SystemRegionEntity> regionList = this.commonDao.findHql(hql, regionCode);
 		
 		if(regionList.size()>0){
 			return regionList.get(0) ;
 		}
 		return null;
 	}
 	
 	/**
 	 * 
 	 * @param regionCode
 	 * @return
 	 */
 	public SystemRegionEntity getSystemRegionEntityById(String id){
 		
 		
 		String hql = "from SystemRegionEntity where id = ?";
 		
 		List<SystemRegionEntity> regionList = this.commonDao.findHql(hql, id);
 		
 		if(regionList.size()>0){
 			return regionList.get(0) ;
 		}
 		return null;
 	}

 	/**
 	 * 
 	 * @param list
 	 */
	public void saveSystemRegionList(List<SystemRegionImportEntity> list) {
		
		if(null == list || list.size() ==0){
			return ;
		}
		
		//记录区划编码与区划id对应关系
		HashMap<String, String> map = new HashMap<>();
		
		for(int i = 0; i < list.size(); i++){
			
			SystemRegionImportEntity r = list.get(i);
			
			// 转换为SystemRegionEntity
			SystemRegionEntity region =  this.getSystemRegionEntityByRegionCode(proceString(r.getRegionCode()));
			if(region == null) {
				region = new SystemRegionEntity();
			}
			
			// 区划编码
			region.setRegionCode(proceString(r.getRegionCode()));
			// 行政区划名称
			region.setRegionName(r.getRegionName());
			// 行政区划级别
			region.setRegionLevel(proceString(r.getRegionLevel()));
			//行政区划简称
			region.setRegionShortName(r.getRegionShortName());
			//行政区划区号
			region.setAreaCode(proceString(r.getAreaCode()));
			//行政区划邮政编码
			region.setRegionPostcode(proceString(r.getRegionPostcode()));
			//行政区划面积
			//region.setRegionArea(StringUtil.toDouble(r.getRegionArea()));
			//行政区划设立日期
			//region.setRegionCreateDate(r.getRegionCreateDate());
			//行政区划撤销日期
			//region.setRevokeDate(r.getRevokeDate());
			//行政区划政府驻地名称
			//region.setRegionEncampment(r.getRegionEncampment());
			//行政区划人口数量
			//region.setRegionPopulation(StringUtil.toDouble(r.getRegionPopulation()));
			//行政区划城镇人口数量
			//region.setRegionCountyPopulation(StringUtil.toDouble(r.getRegionCountyPopulation()));
			//默认设置为叶子节点，然后再update
			region.setIsLeaf("1");
			
			// 父级id
			String parentRegionCode = proceString(r.getParentRegionCode());
			if(map.containsKey(parentRegionCode)) {
				SystemRegionEntity parentRegion = new SystemRegionEntity();
				parentRegion.setId(map.get(parentRegionCode));
				region.setParent(parentRegion);
			}else {
				SystemRegionEntity parentRegion = this.getSystemRegionEntityByRegionCode(parentRegionCode);
				region.setParent(parentRegion);
				map.put(r.getParentRegionCode(), parentRegion.getId());
			}
			save(region);
		}
	
		//更新叶子节点状态
		String hql = "update SystemRegionEntity e set isLeaf = '0' where exists(select 1 from SystemRegionEntity child where e.id = child.parent.id)";
		this.executeHql(hql, null);
	}
	
	/**
	 * 处理字符串
	 * @param src
	 * @return
	 */
	private String proceString(String src) {
		
		if (src != null && src.length() > 0) {
			
			if(StringUtil.isFloatNumeric(src)){
				String[] s  = src.split("[.]");
				
				return s[0];
			}

		}
		return src;
	}

	@Override
	public List<SystemRegionEntity> getRegionList(String[] str) {
		List<SystemRegionEntity> regionList = new ArrayList<SystemRegionEntity>();
		String sql = " select s.region_code,s.region_name,t.typename,(select region_code from system_region where id = s.parent_id ) as parentcode,s.region_short_name,s.area_code,s.region_postcode from system_region s" 
				   + " left join ( select t1.typecode,t1.typename from t_s_type t1 "
				   + " inner join( select id from t_s_typegroup where typegroupcode = '101010' ) t2 on "
				   + " t1.typegroupid=t2.id )t "
				   + " on  s.region_level=t.typecode ";
		List<Object[]> list = commonDao.findListbySql(sql);
		for(int i=0;i<list.size();i++){
			SystemRegionEntity entity = new SystemRegionEntity();
			entity.setRegionCode(list.get(i)[0]==null?"":list.get(i)[0].toString());
			entity.setRegionName(list.get(i)[1]==null?"":list.get(i)[1].toString());
			entity.setRegionLevel(list.get(i)[2]==null?"":list.get(i)[2].toString());
			entity.setParentCode((list.get(i)[3]==null?"":list.get(i)[3].toString()));
			entity.setRegionShortName(list.get(i)[4]==null?"":list.get(i)[4].toString());
			entity.setAreaCode(list.get(i)[5]==null?"":list.get(i)[5].toString());
			entity.setRegionPostcode(list.get(i)[6]==null?"":list.get(i)[6].toString());
			regionList.add(entity);
		}
		return regionList;
	}
		
	
}