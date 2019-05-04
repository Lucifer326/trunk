package org.jeecgframework.web.system.controller.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Property;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserAgentEntity;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TSUserAgentServiceI;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: Controller
 * @Description: 用户代理人设置
 * @author onlineGenerator
 * @date 2017-03-06 11:21:34
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/userAgentController")
public class UserAgentController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UserAgentController.class);

	@Autowired
	private TSUserAgentServiceI tSUserAgentService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private UserService userService;

	/**
	 * 用户代理人设置列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "agent")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("system/user/userAgentList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TSUserAgentEntity tSUserAgent, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		CriteriaQuery cq = new CriteriaQuery(TSUserAgentEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSUserAgent);
		String userName = user.getUserName();
		try {
			cq.eq("status", "1");
			cq.ge("endTime", Calendar.getInstance().getTime());
			if (!(userName.equals("admin"))) {
				cq.eq("userName", userName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * @Title: addorupdate @Description: TODO(代理人录入和编辑页面跳转) @param @param
	 * tSUserAgent @param @param req @param @return 参数 @return ModelAndView
	 * 返回类型 @throws
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSUserAgentEntity tSUserAgent, HttpServletRequest req) {
		if (tSUserAgent.getId() != null) {
			tSUserAgent = systemService.getEntity(TSUserAgentEntity.class, tSUserAgent.getId());
			req.setAttribute("tSUserAgent", tSUserAgent);
		}
		TSUser user = ResourceUtil.getSessionUserName();
		req.setAttribute("userName", user.getUserName());
		req.setAttribute("realName", user.getRealName());
		return new ModelAndView("system/user/userAgent");
	}

	/**
	 * 选择用户跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "userSelect")
	public String userSelect() {
		return "system/user/userAgentSelect";
	}

	/**
	 * easyuiAJAX用户列表请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "agentGrid")
	public void agentGrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String orgIds = request.getParameter("orgIds");
		String queryScope = request.getParameter("queryScope");
		if (queryScope != null && "all".equals(queryScope)) {// 查询全部时，设置orgIds参水为空
			orgIds = null;
		}
		// 第一次加载用户列表时，如果 非超级管理员则 按照当前登录用户所属的部门id 加载所有的用户
		HttpSession session = ContextHolderUtils.getSession();
		TSUser currentUser = (TSUser) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		if (!currentUser.getStatus().equals(Globals.User_ADMIN)) {// 非管理员
			if (StringUtil.isEmpty(orgIds)) {
				TSDepart dept = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);

				orgIds = dept.getId();
			}
		}
		PageList list = tSUserAgentService.queryUser(dataGrid.getPage(), dataGrid.getRows(), user, orgIds);
		dataGrid.setResults(list.getResultList());
		dataGrid.setTotal(list.getCount());
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 取消用户代理人设置
	 * 
	 * @return
	 */
	@RequestMapping(params = "cancel")
	@ResponseBody
	public AjaxJson cancel(TSUserAgentEntity tSUserAgent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSUserAgent = systemService.getEntity(TSUserAgentEntity.class, tSUserAgent.getId());
		tSUserAgent.setStatus("0");
		message = "用户代理人设置取消成功";
		try {
			String currDateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			Date currDate = DateUtils.parseDate(currDateStr, "yyyy-MM-dd HH:mm:ss");
			tSUserAgent.setStopTime(currDate);
			tSUserAgentService.save(tSUserAgent);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "用户代理人设置取消失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新用户代理人设置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddOrUpdate")
	@ResponseBody
	public AjaxJson doAddOrUpdate(TSUserAgentEntity tSUserAgent, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "用户代理人设置成功";
		try {
			if (StringUtils.isEmpty(tSUserAgent.getId())) {
				tSUserAgentService.save(tSUserAgent);
			} else {
				TSUserAgentEntity t = tSUserAgentService.get(TSUserAgentEntity.class, tSUserAgent.getId());
				MyBeanUtils.copyBeanNotNull2Bean(tSUserAgent, t);
				tSUserAgentService.saveOrUpdate(t);
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "用户代理人设置失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TSUserAgentEntity> list() {
		List<TSUserAgentEntity> listTSUserAgents = tSUserAgentService.getList(TSUserAgentEntity.class);
		return listTSUserAgents;
	}

	/**
	 * easyuiAJAX用户列表请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "userdatagrid")
	public void userdatagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		Short[] userstate = new Short[] { Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden };
		cq.in("status", userstate);
		cq.eq("deleteFlag", Globals.Delete_Normal);
		cq.eq("activitiSync", new Short("1"));

		String orgIds = request.getParameter("orgIds");
		List<String> orgIdList = extractIdListByComma(orgIds);
		// 获取 当前组织机构的用户信息
		if (!CollectionUtils.isEmpty(orgIdList)) {
			CriteriaQuery subCq = new CriteriaQuery(TSUserOrg.class);
			subCq.setProjection(Property.forName("tsUser.id"));
			subCq.in("tsDepart.id", orgIdList.toArray());
			subCq.add();

			cq.add(Property.forName("id").in(subCq.getDetachedCriteria()));
		}

		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		List<TSUser> cfeList = new ArrayList<TSUser>();
		for (Object o : dataGrid.getResults()) {
			if (o instanceof TSUser) {
				TSUser cfe = (TSUser) o;
				if (cfe.getId() != null && !"".equals(cfe.getId())) {
					List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id",
							cfe.getId());
					if (roleUser.size() > 0) {
						String roleName = "";
						for (TSRoleUser ru : roleUser) {
							roleName += ru.getTSRole().getRoleName() + ",";
						}
						roleName = roleName.substring(0, roleName.length() - 1);
						cfe.setUserKey(roleName);
					}
				}
				cfeList.add(cfe);
			}
		}
		TagUtil.datagrid(response, dataGrid);
	}
}
