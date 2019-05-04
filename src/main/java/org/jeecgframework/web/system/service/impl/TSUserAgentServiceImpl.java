package org.jeecgframework.web.system.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.type.DateType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserAgentEntity;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.TSUserAgentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tSUserAgentService")
@Transactional
public class TSUserAgentServiceImpl extends CommonServiceImpl implements TSUserAgentServiceI {

	@Autowired
	private RoleUserService roleUserService;

	public Serializable save(TSUserAgentEntity entity) throws Exception {
		Serializable t = super.save(entity);
		// 执行新增操作增强业务
		this.doAddBus(entity);
		return t;
	}

	public void saveOrUpdate(TSUserAgentEntity entity) throws Exception {
		super.saveOrUpdate(entity);
		// 执行更新操作增强业务
		this.doUpdateBus(entity);
	}

	/**
	 * 新增操作增强业务
	 * 
	 * @param t
	 * @return
	 */
	private void doAddBus(TSUserAgentEntity t) throws Exception {
		// -----------------sql增强 start----------------------------
		// -----------------sql增强 end------------------------------

		// -----------------java增强 start---------------------------
		// -----------------java增强 end-----------------------------
	}

	/**
	 * 更新操作增强业务
	 * 
	 * @param t
	 * @return
	 */
	private void doUpdateBus(TSUserAgentEntity t) throws Exception {
		// -----------------sql增强 start----------------------------
		// -----------------sql增强 end------------------------------

		// -----------------java增强 start---------------------------
		// -----------------java增强 end-----------------------------
	}

	private Map<String, Object> populationMap(TSUserAgentEntity t) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", t.getId());
		map.put("user_name", t.getUserName());
		map.put("agent_user_name", t.getAgentUserName());
		map.put("start_time", t.getStartTime());
		map.put("end_time", t.getEndTime());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		return map;
	}

	/**
	 * 替换sql中的变量
	 * 
	 * @param sql
	 * @param t
	 * @return
	 */
	public String replaceVal(String sql, TSUserAgentEntity t) {
		sql = sql.replace("#{id}", String.valueOf(t.getId()));
		sql = sql.replace("#{user_name}", String.valueOf(t.getUserName()));
		sql = sql.replace("#{agent_user_name}", String.valueOf(t.getAgentUserName()));
		sql = sql.replace("#{start_time}", String.valueOf(t.getStartTime()));
		sql = sql.replace("#{end_time}", String.valueOf(t.getEndTime()));
		sql = sql.replace("#{create_name}", String.valueOf(t.getCreateName()));
		sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
		sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
		sql = sql.replace("#{update_name}", String.valueOf(t.getUpdateName()));
		sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
		sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
		sql = sql.replace("#{sys_org_code}", String.valueOf(t.getSysOrgCode()));
		sql = sql.replace("#{sys_company_code}", String.valueOf(t.getSysCompanyCode()));
		sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
		return sql;
	}

	/**
	 * 执行JAVA增强
	 */
	private void executeJavaExtend(String cgJavaType, String cgJavaValue, Map<String, Object> data) throws Exception {
		if (StringUtil.isNotEmpty(cgJavaValue)) {
			Object obj = null;
			try {
				if ("class".equals(cgJavaType)) {
					// 因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				} else if ("spring".equals(cgJavaType)) {
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if (obj instanceof CgformEnhanceJavaInter) {
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("t_p_mobile_biz_form", data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			}
		}
	}

	@Override
	public TSUserAgentEntity getTsUserAgent(String agentUserName) {
		TSUserAgentEntity tsUserAgent = null;
		try {
			// 查询有代理设置的用户
			String currDateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			Date currDate = DateUtils.parseDate(currDateStr, "yyyy-MM-dd HH:mm:ss");
			String hql = "from TSUserAgentEntity t where t.status = '1' and startTime <= ? and endTime >= ?";
			List<TSUserAgentEntity> agentUserList = commonDao.findHql(hql, currDate, currDate);
			if (agentUserList.size() > 0) {
				for (int i = 0; i < agentUserList.size(); i++) {
					if (agentUserName.equals(agentUserList.get(i).getAgentUserName())) {
						tsUserAgent = agentUserList.get(i);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsUserAgent;
	}

	/**
	 * @Title: getTsUserList @Description: TODO(查询被代理人记录对象) @param @param
	 *         userName @param @return 参数 @return TSUserAgentEntity 返回类型 @throws
	 */
	@Override
	public List<TSUserAgentEntity> getTsUserList(String userName) {
		List<TSUserAgentEntity> agentUserList = null;
		try {
			// 查询有代理设置的用户
			String currDateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			Date currDate = DateUtils.parseDate(currDateStr, "yyyy-MM-dd HH:mm:ss");
			String hql = "from TSUserAgentEntity t where t.status = '1' and startTime <= ? and endTime >= ? and userName=?";
			agentUserList = commonDao.findHql(hql, currDate, currDate, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentUserList;
	}

	/**
	 * @Title: getTsUserAgentList @Description: TODO(查询代理人记录对象) @param @param
	 *         userName @param @return 参数 @return List
	 *         <TSUserAgentEntity> 返回类型 @throws
	 */
	@Override
	public List<TSUserAgentEntity> getTsUserAgentList(String userName) {
		List<TSUserAgentEntity> agentUserList = null;
		try {
			// 查询有代理设置的用户
			String currDateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			Date currDate = DateUtils.parseDate(currDateStr, "yyyy-MM-dd HH:mm:ss");
			String hql = "from TSUserAgentEntity t where t.status = '1' and startTime <= ? and endTime >= ? and agentUserName=?";
			agentUserList = commonDao.findHql(hql, currDate, currDate, userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agentUserList;
	}

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
	@Override
	public PageList queryUser(int pageNo, int pageSize, TSUser user, String orgId) {
		HqlQuery hqlQuery = new HqlQuery("");
		hqlQuery.setCurPage(pageNo);
		hqlQuery.setPageSize(pageSize);

		ArrayList<Object> params = new ArrayList<Object>();
		ArrayList<Type> types = new ArrayList<Type>();

		// 获取当前登录用户
		TSUser currentUser = ResourceUtil.getSessionUserName();
		String currentUserId = currentUser.getId();
		String hql = "select u From TSUser u where u.status in (1,0) and u.id <> ?";
		params.add(currentUserId);
		types.add(StringType.INSTANCE);
		hql = hql
				+ " and not exists(select t from TSUserAgentEntity t where u.userName =t.userName and t.status = '1' and endTime >= ?)";
		params.add(Calendar.getInstance().getTime());
		types.add(DateType.INSTANCE);
		if (user != null) {
			if (user.getUserName() != null && !"".equals(user.getUserName())) {
				hql = hql + " and u.userName like ? ";
				params.add("%" + user.getUserName() + "%");
				types.add(StringType.INSTANCE);
			}
		}

		if (orgId != null && !orgId.equals("")) {
			hql = hql + " and exists(select 1 from TSUserOrg uo where u.id = uo.tsUser.id and uo.tsDepart.id = ?)";
			params.add(orgId);
			types.add(StringType.INSTANCE);
		}

		hql = hql + " order by userName";

		hqlQuery.setQueryString(hql);
		boolean needParameter = false;
		if (params.size() > 0) {
			hqlQuery.setParam(params.toArray());
			Type[] tt = new Type[types.size()];
			for (int i = 0; i < types.size(); i++) {
				tt[i] = types.get(i);
			}
			hqlQuery.setTypes(tt);
			needParameter = true;
		}
		PageList pageList = commonDao.getPageList(hqlQuery, needParameter);
		List<TSUser> list = pageList.getResultList();
		if (list == null || list.size() == 0) {
			return pageList;
		}

		// 设置用户角色显示
		List<TSUser> result = new ArrayList<TSUser>();

		for (int i = 0; i < list.size(); i++) {
			TSUser tempUser = list.get(i);
			result.add(tempUser);
			List<TSRoleUser> ruList = roleUserService.getRoleUserListByUserId(tempUser.getId());
			if (ruList == null || ruList.size() == 0) {
				tempUser.setUserKey(null);
				continue;
			}
			String userKey = "";
			for (int m = 0; m < ruList.size(); m++) {
				TSRoleUser tempRu = ruList.get(m);
				if (tempRu.gettSDepart() != null && tempRu.getTSRole() != null) {
					userKey = userKey + tempRu.gettSDepart().getDepartname() + "-" + tempRu.getTSRole().getRoleName()
							+ ",";
				}
			}
			if (userKey.length() > 1) {
				userKey = userKey.substring(0, userKey.length() - 1);
			}
			tempUser.setUserKey(userKey);
		}
		pageList.setResultList(result);

		return pageList;
	}

}