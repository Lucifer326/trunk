package org.jeecgframework.web.system.service;

import java.util.List;

import org.jeecgframework.web.system.pojo.base.TSFunction;

public interface FunctionService {

	/**
	 * 查询菜单
	 * @param id 菜单id
	 * @return
	 */
	public TSFunction getFunction(String id);
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	public List<TSFunction> getAllFunctions();
	
	/**
	 * 删除菜单
	 * @param id 菜单id
	 * @return
	 */
	public int deleteFunction(String id);

	
	/**
	 * 保存菜单
	 * @param function
	 * @return
	 */
	public TSFunction saveFunction(TSFunction function);
	
	/**
	 * 查询用户可见菜单
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<TSFunction> getFunctionsForUser(String userId,String orgId);
	
	/**
	 * 验证用户是否有查看指定菜单的权限
	 * @param userId 用户id
	 * @param orgId 用户当前登陆的组织机构
	 * @param url 菜单url
	 * @return
	 */
	public boolean menuAuthValidateByUrl(String userId,String orgId,String url);

	
	
}
