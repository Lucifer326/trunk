package org.jeecgframework.web.system.service;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSDepartImport;

/**
 *
 * 组织机构
 *
 */
public interface DepartService{
	
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	public <T> Serializable save(T entity);
	/**
	 * 编辑
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity);
	/**
	 * 根据父id查询组织列表
	 * @param parentdepartid
	 * @return
	 */
	List<TSDepart> getDepartlistByParentId(String parentdepartid);
	/**
	 * 根据区划id查询组织列表
	 * @param regionId
	 * @return
	 */
	List<TSDepart> getDepartlistByRegionId(String regionId);
	
	/**
	 * 删除组织机构
	 * @param departId
	 * @return
	 */
	public int deleteDept(String departId);
	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
			Boolean ispage);
	
	
	/**
	 * 根据组织id查找 所有的用户 个数
	 * @param departId
	 * @return
	 */
	public Long getUsersCount(String departId);
	
	/**
	 * 查询部门
	 * @param id
	 * @return
	 */
	public TSDepart getDepart(String id);
	/**
	 * 删除用户组织机构关联关系，当用户只归属于当前组织机构时，级联删除用户
	 * @param 
	 * @returnuserId
	 */
	public int deleteUserOrg(String userId,String orgId);
	
	public String generateOrgCode(String pid);
	public TSDepart getTSDepartBydepartname(String parentdepartname);
	public void saveTSDepartList(List<TSDepartImport> tsDeparts);
	public List<TSDepart> getDepartListByRegionIdAndName(String name, String regionId) throws Exception;
}
