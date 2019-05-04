package org.jeecgframework.web.system.service;

import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSRoleUserBase;
import org.jeecgframework.web.system.pojo.base.TSUser;

public interface RoleUserService {

	/**
	 * 保存角色用户信息
	 * @param roleUser
	 * @return
	 */
	public TSRoleUserBase saveRoleUser(TSRoleUserBase roleUser);
	
	/**
	 * 批量保存角色用户信息
	 * @param roleId 角色id
	 * @param userOrgIds 用户部门表id，多个用","隔开
	 * @return
	 */
	public int saveRoleUser(String roleId,String userOrgIds);
	
	/**
	 * 删除角色用户信息
	 * @param id
	 * @return
	 */
	public int deleteRoleUser(String id);
	
	/**
	 * 删除指定用户的用户角色信息
	 * @param userId
	 * @return
	 */

	public int deleteRoleUserByUserId(String userId);
	
	/**
	 * 删除指定部门指定用户的用户角色信息
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public int deleteRoleUserByUserIdAndOrgId(String userId,String orgId);
	
	/**
	 * 删除指定角色的用户角色信息
	 * @param roleId
	 * @return
	 */
	public int deleteRoleUserByRoleId(String roleId);
	
	/**
	 * 查询用户角色信息
	 * @param id
	 * @return
	 */
	public TSRoleUser getRoleUser(String id);
	
	/**
	 * 根据角色id、用户id、部门id查询用户角色信息
	 * @param roleId
	 * @param userId
	 * @param departId
	 * @return
	 */
	public TSRoleUser getRoleUser(String roleId,String userId,String departId);
	
	/**
	 * 查询指定用户的用户角色信息
	 * @param userId
	 * @return
	 */
	public List<TSRoleUser> getRoleUserListByUserId(String userId);
	
	/**
	 * 查询指定部门指定用户的用户角色信息
	 * @param userId
	 * @param departId
	 * @return
	 */
	public List<TSRoleUser> getRoleUserListByUserId(String userId,String departId);
	
	/**
	 * 查询指定角色的用户角色信息
	 * @param roleId
	 * @return
	 */
	public List<TSRoleUser> getRoleUserListByRoleId(String roleId);
	
	/**
	 * 分页查询指定角色的角色用户信息
	 * @param pageNo
	 * @param pageSize
	 * @param user
	 * @param roleId 角色id，不允许为空
	 * @return
	 */
	
	public PageList getRoleUserListByRoleId(int pageNo,int pageSize,TSUser user,String roleId);
	
	/**
	 * 根据权限id查询按钮权限信息
	 * @param id
	 * @return
	 */
	public TSOperation getOperationById(String id);
	/**
	 * 根据权限code查询按钮权限信息
	 * @param id
	 * @return
	 */
	public List<TSOperation> getOperationByCode(String code);
	/**
	 * 查询指定用户的用户角色信息
	 * @param userId
	 * @return
	 */
	public List<TSRoleUser> getRoleUserListByUserName(String userName);
}
