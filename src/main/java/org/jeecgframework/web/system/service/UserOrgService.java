package org.jeecgframework.web.system.service;

import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;

public interface UserOrgService {

	/**
	 * 查询用户部门信息
	 * @param id
	 * @return
	 */
	public TSUserOrg getUserOrg(String id);
	
	/**
	 * 查询用户部门信息
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public TSUserOrg getUserOrg(String userId,String orgId);
	
	/**
	 * 查询指定用户的用户部门信息
	 * @param userId
	 * @return
	 */
	public List<TSUserOrg> getUserOrgListByUserId(String userId);
	
	/**
	 * 查询用户部门信息
	 * @param ids 用户部门表id，多个用“,”隔开 
	 * @return
	 */
	public List<TSUserOrg> getUserOrgListByIds(String ids);
	
	/**
	 * 查询指定部门的用户部门信息
	 * @param orgId
	 * @return
	 */
	public List<TSUserOrg> getUserOrgListByOrgId(String orgId);
	
	/**
	 * 保存用户部门信息
	 * @param userOrg
	 * @return
	 */
	public TSUserOrg saveUserOrg(TSUserOrg userOrg);
	
	/**
	 * 删除指定的用户部门信息
	 * @param id
	 * @return
	 */
	public int deleteUserOrg(String id);
	
	/**
	 * 删除指定用户的用户部门信息
	 * @param userId
	 * @return
	 */
	public int deleteUserOrgByUserId(String userId);
	
	/**
	 * 删除指定部门的用户部门信息
	 * @param orgId
	 * @return
	 */
	public int deleteUserOrgByOrgId(String orgId);
	
	/**
	 * 删除指定部门的指定用户
	 * @param orgId 
	 * @param userId
	 * @return
	 */
	public int deleteUserOrgByOrgIdAndUserId(String userId, String orgId);
	
	/**
	 * 查询未包含指定角色的用户部门信息列表
	 * @param pageNo
	 * @param pageSize
	 * @param user
	 * @param depart
	 * @param roleId 指定的角色id
	 * @return
	 */
	public PageList queryUserOrgListNotContainRole(int pageNo,int pageSize,TSUser user,TSDepart depart,String roleId);
	
}
