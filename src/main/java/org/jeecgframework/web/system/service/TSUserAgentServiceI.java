package org.jeecgframework.web.system.service;

import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserAgentEntity;

public interface TSUserAgentServiceI extends CommonService {

	/**
	 * @Title: save @Description: TODO(保存代理人记录) @param @param
	 *         entity @param @return @param @throws Exception 参数 @return
	 *         Serializable 返回类型 @throws
	 */
	public Serializable save(TSUserAgentEntity entity) throws Exception;

	/**
	 * @Title: saveOrUpdate @Description: TODO(保存和编辑代理人记录) @param @param
	 *         entity @param @throws Exception 参数 @return void 返回类型 @throws
	 */
	public void saveOrUpdate(TSUserAgentEntity entity) throws Exception;

	/**
	 * @Title: getTsUserAgent @Description: TODO(查询代理人记录对象) @param @param
	 *         agentUserName @param @return 参数 @return TSUserAgentEntity
	 *         返回类型 @throws
	 */
	public TSUserAgentEntity getTsUserAgent(String agentUserName);

	/**
	 * @Title: getTsUserList @Description: TODO(查询被代理人记录对象) @param @param
	 *         userName @param @return 参数 @return TSUserAgentEntity 返回类型 @throws
	 */
	public List<TSUserAgentEntity> getTsUserList(String userName);

	/**
	 * @Title: getTsUserAgentList @Description: TODO(查询代理人记录对象) @param @param
	 *         userName @param @return 参数 @return List
	 *         <TSUserAgentEntity> 返回类型 @throws
	 */
	public List<TSUserAgentEntity> getTsUserAgentList(String userName);

	/**
	 * 用户分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param user
	 * @param orgId
	 *            部门ID
	 * @return
	 */
	public PageList queryUser(int pageNo, int pageSize, TSUser user, String orgId);
}
