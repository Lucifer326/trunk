package org.jeecgframework.web.system.service;

import java.util.List;

import org.jeecgframework.web.system.pojo.base.TSRole;
/**
 * 
 * @author  张代浩
 *
 */
import org.jeecgframework.web.system.pojo.base.TSUser;
public interface RoleService{

	/**
	 * 根据id查询角色
	 * @param id
	 * @return
	 */
	public TSRole getRole(String id);
	
	/**
	 * 查询角色
	 * @param role
	 * @return
	 */
	public List<TSRole> queryRoleList(TSRole role);
	
	/**
	 * 禁用角色
	 * @param id
	 */
	public boolean lockRole(String id);
	
	/**
	 * 启用角色
	 * @param id
	 */
	public boolean unLockRole(String id);
	
	/**
	 * 查询指定角色的用户列表
	 * @param roleId
	 * @return
	 */
	public List<TSUser> getUserListByRoleId(String roleId);
	
	/**
	 * 查询已禁用角色的用户列表
	 * @param roleId
	 * @return
	 */
	public List<TSUser> getUserListForLockRole(String roleId);
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public <T> List<T> getAllRoles();
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return 1:删除成功;0:删除失败；-1：删除失败，禁止删除
	 */
	public int deleteRole(String roleId);
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	public TSRole saveRole(TSRole role);
	
}
