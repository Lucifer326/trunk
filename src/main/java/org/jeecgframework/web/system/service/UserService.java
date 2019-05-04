package org.jeecgframework.web.system.service;

import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserAgentEntity;
import org.jeecgframework.web.system.pojo.base.TSUserCardInfoEntity;
/**
 * 
 * @author  张代浩
 *
 */
public interface UserService{

	public TSUser checkUserExits(TSUser user);
	public String getUserRole(TSUser user);
	public void pwdInit(TSUser user, String newPwd);
	
	/**
	 * 根据用户登录名查询用户
	 * @param loginName
	 * @return
	 */
	public TSUser getUserByLoginName(String loginName);
	
	/**
	 * 查询用户关联的部门列表
	 * @param userId
	 * @return
	 */
	public List<TSDepart> getUserDepartList(String userId);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int deleteUserById(String id);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public TSUser getUser(String id);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public TSUser saveUser(TSUser user);
	
	/**
	 * 保存用户，并同时更新组织机构和角色信息
	 * @param user
	 * @param orgIds 部门id，多个用“,”隔开
	 * @param roleIds 角色id，格式：部门1id_角色1id,部门2id_角色2id,部门2id_角色2id,
	 * @return
	 */
	public TSUser saveUser(TSUser user,String orgIds,String roleIds);
	
	/**
	 * 查询所有有效用户
	 * @return
	 */
	public List<TSUser> getAllUser();
	
	/**
	 * 用户分页查询
	 * @param pageNo
	 * @param pageSize
	 * @param user   
	 * @param orgId  部门ID
	 * @return
	 */
	public PageList queryUser(int pageNo,int pageSize,TSUser user,String orgId);
	/**
	 * 查询登陆用户及下级关联的部门列表
	 * @param userId
	 * @return
	 */
	public List<TSDepart> getUserNextDepartList(String userId);
	/**
	 * 查询登陆用户和代理人及下级关联的部门列表
	 * @param userId
	 * @return
	 */
	public List<TSDepart> getUserAndAgentNextDepartList(String userId);
	
 	public List<TSUserCardInfoEntity> getUserCardInfoList(String userName ) ;
 	/**
	 * 保存用户身份证信息
	 * @param user
	 * @return
	 */
	public TSUserCardInfoEntity saveUserCardInfo(TSUserCardInfoEntity userCardInfo);
	
}
