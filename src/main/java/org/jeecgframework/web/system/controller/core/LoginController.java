package org.jeecgframework.web.system.controller.core;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.extend.datasource.DataSourceContextHolder;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.IpUtil;
import org.jeecgframework.core.util.ListtoMenu;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SysThemesUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSConfig;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.FunctionService;
import org.jeecgframework.web.system.service.MutiLangServiceI;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.jeecgframework.web.system.util.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.jeecg.bssys_deploy.service.BssysDeployServiceI;

/**
 * 登陆初始化控制器
 * 
 * @author 张代浩
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/loginController")
public class LoginController extends BaseController {
	private Logger log = Logger.getLogger(LoginController.class);
	private SystemService systemService;
	private UserService userService;
	@Autowired
	private BssysDeployServiceI bssysDeployService;
	@Autowired
	private FunctionService functionService;
	@Autowired
	private RoleUserService roleUserService;
	private String message = null;
	private static final ResourceBundle liguanqun = ResourceBundle.getBundle("jeecg/style");

	@Autowired
	private MutiLangServiceI mutiLangService;

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	@Autowired
	public void setUserService(UserService userService) {

		this.userService = userService;
	}

	@RequestMapping(params = "goPwdInit")
	public String goPwdInit() {
		return "login/pwd_init";
	}

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public AjaxJson checkuser(TSUser user, HttpServletRequest req, HttpServletResponse response) throws Exception {
		HttpSession session = ContextHolderUtils.getSession();
		session.setAttribute("transtion", "true");
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
		AjaxJson j = new AjaxJson();
		// 添加语言选择
		if (req.getParameter("langCode") != null) {
			req.getSession().setAttribute("lang", req.getParameter("langCode"));
		}

		// 验证验证码
		String randCode = req.getParameter("randCode");
		if (StringUtils.isEmpty(randCode)) {
			j.setMsg(mutiLangService.getLang("common.enter.verifycode"));
			j.setSuccess(false);
			return j;
		}
		if (!randCode.equalsIgnoreCase(String.valueOf(session.getAttribute("randCode")))) {
			j.setMsg(mutiLangService.getLang("common.verifycode.error"));
			j.setSuccess(false);
			return j;
		}

		if (user == null || "".equals(user.getUserName()) || "".equals(user.getPassword())) {
			j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
			j.setSuccess(false);
			return j;
		}

		TSUser loginUser = userService.getUserByLoginName(user.getUserName());
		if (loginUser == null) {
			j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
			j.setSuccess(false);
			return j;
		}
		//将原先的密码盐值处理代码去掉   joyray   2018-08-21
		String paramPassword = user.getPassword();
		String userPassword = loginUser.getPassword();
		if (!userPassword.equals(paramPassword)) {
			j.setMsg(mutiLangService.getLang("common.username.or.password.error"));
			j.setSuccess(false);
			return j;
		}

		// 获取用户组织机构
		List<TSDepart> departList = userService.getUserDepartList(loginUser.getId());
		if (departList == null || departList.size() == 0) {// 用户没有归属组织机构时登陆失败（admin除外）
			if (loginUser.getStatus() != Globals.User_ADMIN) {
				j.setMsg("用户没有归属组织机构");
				j.setSuccess(false);
				return j;
			}
		}

		// 获取组织机构、用户配置关系 : 0：一对多；1：多对多，默认一对多
		String departUserRelation = bssysDeployService.getValue("001001001", null);
		if (departUserRelation == null || departUserRelation.equals("")) {
			departUserRelation = "0";
		}

		// 当组织机构用户关系为一对多时，若用户存在多个组织机构，则为错误数据，拒绝登陆（admin除外）
		if (loginUser.getStatus() != Globals.User_ADMIN && "0".equals(departUserRelation) && departList.size() > 1) {
			j.setMsg("用户数据错误，存在多个归属组织机构");
			j.setSuccess(false);
			return j;
		}

		// 验证通过，保存部门数据到session、cookie
		saveLoginSuccessInfo(req, response, loginUser, departList, departUserRelation);

		return j;
	}

	/**
	 * 保存用户登录的信息
	 * 
	 * @param req
	 *            request
	 * @param user
	 *            当前登录用户
	 * @param departList
	 *            组织主键
	 */
	private void saveLoginSuccessInfo(HttpServletRequest req, HttpServletResponse response, TSUser user,
			List<TSDepart> departList, String departUserRelation) {
		HttpSession session = ContextHolderUtils.getSession();

		// 保存部门数据
		TSDepart currentDepart = null;

		if ("0".equals(departUserRelation)) {// 组织用户为一对多时，直接保存部门到session
			session.setAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART, departList.get(0));
			currentDepart = departList.get(0);

		} else if ("1".equals(departUserRelation)) {// 组织用户为多对多时，以cookie中记录的部门作为登陆部门
			session.setAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_LIST, departList);
			// ------从cookie中获取上次登陆的部门id
			String cookieDepartId = "";
			try {
				Cookie[] cookies = req.getCookies();
				for (Cookie cookie : cookies) {
					if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
						continue;
					}
					if (cookie.getName().equalsIgnoreCase(
							"JEECG_LOGIN_USER_DEPART" + user.getUserName() + "_" + req.getContextPath())) {
						cookieDepartId = cookie.getValue();
						break;
					}
				}
			} catch (Exception e) {
			}

			if ("".equals(cookieDepartId)) {// cookie中找不到，则默认取第一个
				session.setAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART, departList.get(0));
				currentDepart = departList.get(0);
			} else {// cookie能找到，则以cookie中的部门作为登陆部门
				TSDepart depart = null;
				for (int i = 0; i < departList.size(); i++) {
					depart = departList.get(i);
					if (depart.getId().equals(cookieDepartId)) {
						currentDepart = depart;
						break;// 找到上次登陆的部门则直接返回
					}
				}

				if (currentDepart == null) {
					currentDepart = departList.get(0);
				}
				session.setAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART, currentDepart);
				cookieDepartId = currentDepart.getId();
			}
		}

		// 将本次登陆部门保存到cookie中
		Cookie cookie = new Cookie("JEECG_LOGIN_USER_DEPART" + user.getUserName() + "_" + req.getContextPath(),
				currentDepart.getId());
		// 设置cookie有效期为一个月
		cookie.setMaxAge(3600 * 24 * 30);
		response.addCookie(cookie);

		message = mutiLangService.getLang("common.user") + ": " + user.getUserName() + "["
				+ currentDepart.getDepartname() + "]" + mutiLangService.getLang("common.login.success");

		// 【基础权限】切换用户，用户分拥有不同的权限，切换用户权限错误问题
		// 当前session为空 或者 当前session的用户信息与刚输入的用户信息一致时，则更新Client信息
		Client clientOld = ClientManager.getInstance().getClient(session.getId());
		if (clientOld == null || clientOld.getUser() == null
				|| user.getUserName().equals(clientOld.getUser().getUserName())) {
			Client client = new Client();
			client.setIp(IpUtil.getIpAddr(req));
			client.setLogindatetime(new Date());
			client.setUser(user);
			ClientManager.getInstance().addClinet(session.getId(), client);
		} else {// 如果不一致，则注销session并通过session=req.getSession(true)初始化session
			ClientManager.getInstance().removeClinet(session.getId());
			session.invalidate();
			session = req.getSession(true);// session初始化
			session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);
			session.setAttribute("randCode", req.getParameter("randCode"));// 保存验证码
			try {
				checkuser(user, req, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		session.setAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID, currentDepart.getRegionId());
		// 将currentDepart放到user中，解决项目组user.getCurrentDepart方法取值问题
		user.setCurrentDepart(currentDepart);
		// 保存用户数据
		session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);

		// 添加登陆日志
		systemService.addLog(message, Globals.Log_Type_LOGIN, Globals.Log_Leavel_INFO);
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "login")
	public String login(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = ContextHolderUtils.getSession();
		TSDepart dept = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
		DataSourceContextHolder.setDataSourceType(DataSourceType.dataSource_jeecg);
		TSUser user = ResourceUtil.getSessionUserName();
		String roles = "";
		String roleids = "";
		if (user != null) {
			List<TSRoleUser> rUsers = roleUserService.getRoleUserListByUserId(user.getId(), dept.getId());
			if (rUsers != null && rUsers.size() > 0) {
				for (TSRoleUser ru : rUsers) {
					TSRole role = ru.getTSRole();
					roles += role.getRoleName() + ",";
					roleids += role.getRoleCode() + ",";
				}
			}
			if (roles.length() > 0) {
				roles = roles.substring(0, roles.length() - 1);
				roleids = roleids.substring(0, roleids.length() - 1);
			}
			modelMap.put("roleName", roles);
			modelMap.put("userName", user.getRealName());
			// 获取当前登录用户的组织机构
			TSDepart currentDept = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
			if (currentDept != null) {
				modelMap.put("currentOrgName", currentDept.getDepartname());
			}

			request.getSession().setAttribute("CKFinder_UserRole", "admin");
			request.getSession().setAttribute("roleids", roleids);

			String ace = liguanqun.getString("ace_style");
			request.setAttribute("ace_style", ace);
			String hplus = liguanqun.getString("hplus");
			request.setAttribute("hplus", hplus);
			String ShortCut = liguanqun.getString("ShortCut");
			request.setAttribute("ShortCut", ShortCut);
			String jinDianStyle = liguanqun.getString("jinDianStyle");
			request.setAttribute("jinDianStyle", jinDianStyle);
			SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
			if ("ace".equals(sysTheme.getStyle()) || "diy".equals(sysTheme.getStyle())
					|| "acele".equals(sysTheme.getStyle()) || sysTheme.getStyle().contains("hplus")) {
				request.setAttribute("menuMap", getFunctionMap(user));
			}
			// update-start--Author:zhoujf Date:20150610 for:ace addOneTab无效问题
			Cookie cookie = new Cookie("JEECGINDEXSTYLE" + "_" + request.getContextPath(), sysTheme.getStyle());
			// 设置cookie有效期为一个月
			cookie.setMaxAge(3600 * 24 * 30);
			response.addCookie(cookie);
			// update-end--Author:zhoujf Date:20150610 for:ace addOneTab无效问题

			// update-start--Author: jg_huangxg Date:20160330 for: zIndex索引问题
			Cookie zIndexCookie = new Cookie("ZINDEXNUMBER", "1990");
			zIndexCookie.setMaxAge(3600 * 24);// 一天
			response.addCookie(zIndexCookie);
			// update-end--Author: jg_huangxg Date:20160330 for: zIndex索引问题
			return sysTheme.getIndexPath();
		} else {
			String login = liguanqun.getString("login_title");
			String login_english = liguanqun.getString("login_english");
			String login_public = liguanqun.getString("login_public");
			request.setAttribute("login", login);
			request.setAttribute("login_english", login_english);
			request.setAttribute("login_public", login_public);
			return "login/login";
		}

	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		TSUser user = ResourceUtil.getSessionUserName();
		systemService.addLog("用户" + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		ClientManager.getInstance().removeClinet(session.getId());
		session.invalidate();
		ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		return modelAndView;
	}

	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		ModelAndView modelAndView = new ModelAndView();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			modelAndView.setView(new RedirectView("loginController.do?login"));
		} else {
			List<TSConfig> configs = systemService.loadAll(TSConfig.class);
			for (TSConfig tsConfig : configs) {
				request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
			}
			modelAndView.setViewName("main/left");
			request.setAttribute("menuMap", getFunctionMap(user));
		}
		return modelAndView;
	}

	/**
	 * 获取权限的map
	 * 
	 * @param user
	 * @return
	 */
	private Map<Integer, List<TSFunction>> getFunctionMap(TSUser user) {
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if (client.getFunctionMap() == null || client.getFunctionMap().size() == 0) {
			Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
			Map<String, TSFunction> loginActionlist = getUserFunction(user);
			if (loginActionlist.size() > 0) {
				Collection<TSFunction> allFunctions = loginActionlist.values();
				for (TSFunction function : allFunctions) {
					// update-begin--Author:anchao Date:20140913
					// for：菜单过滤--------------------
					if (function.getFunctionType().intValue() == Globals.Function_TYPE_FROM.intValue()) {
						// 如果为表单或者弹出 不显示在系统菜单里面
						continue;
					}
					// update-end--Author:anchao Date:20140913
					// for：菜单过滤--------------------
					if (!functionMap.containsKey(function.getFunctionLevel() + 0)) {
						functionMap.put(function.getFunctionLevel() + 0, new ArrayList<TSFunction>());
					}
					functionMap.get(function.getFunctionLevel() + 0).add(function);
				}
				// 菜单栏排序
				Collection<List<TSFunction>> c = functionMap.values();
				for (List<TSFunction> list : c) {
					Collections.sort(list, new NumberComparator());
				}
			}
			client.setFunctionMap(functionMap);
			return functionMap;
		} else {
			return client.getFunctionMap();
		}
	}

	/**
	 * 获取用户菜单列表
	 * 
	 * @param user
	 * @return
	 */
	private Map<String, TSFunction> getUserFunction(TSUser user) {
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if (client.getFunctions() == null || client.getFunctions().size() == 0) {
			Map<String, TSFunction> loginActionlist = new HashMap<String, TSFunction>();
			TSDepart depart = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
			List<TSFunction> list1 = functionService.getFunctionsForUser(user.getId(), depart.getId());
			// 434行有时会返回null，虽然一定会有结果集返回。
			if (null != list1 && list1.size() > 0) {
				for (TSFunction function : list1) {
					loginActionlist.put(function.getId(), function);
				}
			}
			client.setFunctions(loginActionlist);
		}
		return client.getFunctions();
	}

	

	// update-begin--Author:zhangguoming Date:20140821 for：抽取方法，获取角色下的权限列表
	/**
	 * 根据 角色实体 组装 用户权限列表
	 * 
	 * @param loginActionlist
	 *            登录用户的权限列表
	 * @param role
	 *            角色实体
	 * @deprecated
	 */
	private void assembleFunctionsByRole(Map<String, TSFunction> loginActionlist, TSRole role) {
		List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",
				role.getId());
		for (TSRoleFunction roleFunction : roleFunctionList) {
			TSFunction function = roleFunction.getTSFunction();
			// update-begin--Author:anchao Date:20140822
			// for：[bugfree号]字段级权限（表单，列表）--------------------
			if (function.getFunctionType().intValue() == Globals.Function_TYPE_FROM.intValue()) {
				// 如果为表单或者弹出 不显示在系统菜单里面
				continue;
			}
			// update-end--Author:anchao Date:20140822
			// for：[bugfree号]字段级权限（表单，列表）--------------------
			loginActionlist.put(function.getId(), function);
		}
	}
	// update-end--Author:zhangguoming Date:20140821 for：抽取方法，获取角色下的权限列表

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request) {

		// update-start--Author:jg_renjie Date:20160315
		// for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		// ACE ACE2 DIY时需要在home.jsp头部引入依赖的js及css文件
		if ("ace".equals(sysTheme.getStyle()) || "diy".equals(sysTheme.getStyle())
				|| "acele".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		// update-end--Author:jg_renjie Date:20160315
		// for：配合首页改造，控制不同风格时是否引入js/css文件
		return new ModelAndView("main/home");
	}

	/**
	 * ACE首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "acehome")
	public ModelAndView acehome(HttpServletRequest request) {

		// update-start--Author:jg_renjie Date:20160315
		// for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		// ACE ACE2 DIY时需要在home.jsp头部引入依赖的js及css文件
		if ("ace".equals(sysTheme.getStyle()) || "diy".equals(sysTheme.getStyle())
				|| "acele".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		// update-end--Author:jg_renjie Date:20160315
		// for：配合首页改造，控制不同风格时是否引入js/css文件
		return new ModelAndView("main/acehome");
	}

	/**
	 * HPLUS_BLUE首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hplusbluehome")
	public ModelAndView hplusbluehome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("hplus_blue".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_blue_home");
	}

	/**
	 * HPLUS_DARKBLUE首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hplusdarkbluehome")
	public ModelAndView hplusdarkbluehome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("darkblue".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_darkblue_home");
	}

	/**
	 * HPLUS_CYAN首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hpluscyanhome")
	public ModelAndView hpluscyanhome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("hplus_cyan".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_cyan_home");
	}

	/**
	 * HPLUS_DARKRED首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hplusdarkredhome")
	public ModelAndView hplusdarkredhome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("hplus_darkred".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_darkred_home");
	}

	/**
	 * HPLUS_DARKGREY首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hplusdarkgreyhome")
	public ModelAndView hplusdarkgreyhome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("hplus_darkgrey".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_darkgrey_home");
	}

	/**
	 * HPLUS_ORANGE首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hplusorangehome")
	public ModelAndView hplusorangehome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("hplus_orange".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_orange_home");
	}

	/**
	 * HPLUS_GREEN首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hplusgreenhome")
	public ModelAndView hplusgreenhome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("hplus_green".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_green_home");
	}

	/**
	 * HPLUS_RED首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "hplusredhome")
	public ModelAndView hplusredhome(HttpServletRequest request) {

		// Author:gjq Date:201601220 for：配合首页改造，控制不同风格时是否引入js/css文件
		SysThemesEnum sysTheme = SysThemesUtil.getSysTheme(request);
		if ("hplus_red".equals(sysTheme.getStyle())) {
			request.setAttribute("show", "1");
		} else {// default及shortcut不需要引入依赖文件，所有需要屏蔽
			request.setAttribute("show", "0");
		}
		return new ModelAndView("main/hplus_red_home");
	}

	/**
	 * 无权限页面提示跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "noAuth")
	public ModelAndView noAuth(HttpServletRequest request) {
		return new ModelAndView("common/noAuth");
	}

	/**
	 * @Title: top @Description: bootstrap头部菜单请求 @param request @return
	 *         ModelAndView @throws
	 */
	@RequestMapping(params = "top")
	public ModelAndView top(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = systemService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/bootstrap_top");
	}

	

	/**
	 * @Title: top @author gaofeng @Description: shortcut头部菜单请求 @param
	 *         request @return ModelAndView @throws
	 */
	@RequestMapping(params = "shortcut_top")
	public ModelAndView shortcut_top(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = systemService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/shortcut_top");
	}

	/**
	 * @Title: top @author gaofeng @Description: shortcut头部菜单请求 @param
	 *         request @return ModelAndView @throws
	 */
	@RequestMapping(params = "salvation_left")
	public ModelAndView salvation_left(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = systemService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/salvation_left");
	}

	@RequestMapping(params = "leftmenu")
	@ResponseBody
	public String leftMenu(HttpServletRequest request) {

		String pid = request.getParameter("parentid");
		StringBuffer menuString = new StringBuffer();
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
		}
		Map<Integer, List<TSFunction>> map = getFunctionMap(ResourceUtil.getSessionUserName());
		List<TSFunction> primaryMenu = map.get(0);
		menuString.append("<div class='easyui-accordion' fit='true' border='false' id='sel'>");
		for (TSFunction function : primaryMenu) {
			if (function.getId().equals(pid)) {
				if (!function.hasSubFunction(map)) {
					menuString.append(ListtoMenu.getChild2(function, 1, map));
				} else {
					menuString.append(ListtoMenu.getChild2(function, 1, map));
				}
			}
		}
		menuString.append("</div>");
		return menuString.toString();
	}

	/**
	 * @Title: top @author:landychang @Description:
	 *         salvation头部菜单一级菜单列表，并将其用ajax传到页面，实现动态控制一级菜单列表 @return
	 *         String @throws
	 */
	@RequestMapping(params = "primaryMenuss")
	@ResponseBody
	public String getPrimaryMenuss() {
		List<TSFunction> primaryMenu = getFunctionMap(ResourceUtil.getSessionUserName()).get(0);
		String floor = "";
		String selMenuId = "";
		// update-start--Author:zhangguoming Date:20140923
		// for：用户没有任何权限，首页没有退出按钮的bug
		if (primaryMenu == null) {
			return floor;
		}
		// update-end--Author:zhangguoming Date:20140923
		// for：用户没有任何权限，首页没有退出按钮的bug
		int curIndex = 0;
		for (TSFunction function : primaryMenu) {
			if (function.getFunctionLevel() == 0) {
				String lang_key = function.getFunctionName();
				String menuid = function.getId();
				String lang_context = mutiLangService.getLang(lang_key);
				lang_context = lang_context.trim();

				floor += "<li ><a id='" + menuid
						+ "' href='#'  onclick='ChanageMenu(this)' ><span class='nav_left'></span>" + lang_context
						+ "<span class='nav_right'></span></a></li>";
				if (curIndex == 0) {
					selMenuId = menuid;
				}
				/*
				 * floor +=
				 * " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> "
				 * +
				 * " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
				 * + s +"</li> ";
				 */
				/* 修复shortcut下只建一级菜单，点击无法进入菜单地址 */
				/*
				 * if (!StringUtils.isEmpty(function.getFunctionUrl())) { floor
				 * +=
				 * " <li style='position: relative;' subFunction='none' subtitle='"
				 * +lang_context + "' suburl='" + function.getFunctionUrl() +
				 * "' subicon='"
				 * +TSIcon.allTSIcons.get(function.getTSIcon().getId()).
				 * getIconClas()+
				 * "'><img class='imag1' src='plug-in/login/images/default.png' /> "
				 * +
				 * " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' /></li> "
				 * ; } else { floor +=
				 * " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> "
				 * +
				 * " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
				 * </li> "; }
				 */
				/* 修复shortcut下只建一级菜单，点击无法进入菜单地址 */
			}
			curIndex++;
		}
		return floor;
	}

	/**
	 * @Title: top @author:gaofeng @Description:
	 *         shortcut头部菜单一级菜单列表，并将其用ajax传到页面，实现动态控制一级菜单列表 @return
	 *         AjaxJson @throws
	 */
	@RequestMapping(params = "primaryMenu")
	@ResponseBody
	public String getPrimaryMenu() {
		List<TSFunction> primaryMenu = getFunctionMap(ResourceUtil.getSessionUserName()).get(0);
		StringBuilder floor = new StringBuilder();
		// update-start--Author:zhangguoming Date:20140923
		// for：用户没有任何权限，首页没有退出按钮的bug
		if (primaryMenu == null) {
			return floor.toString();
		}
		// update-end--Author:zhangguoming Date:20140923
		// for：用户没有任何权限，首页没有退出按钮的bug
		for (TSFunction function : primaryMenu) {
			if (function.getFunctionLevel() == 0) {
				String lang_key = function.getFunctionName();
				String lang_context = mutiLangService.getLang(lang_key);
				lang_context = lang_context.trim();
				// update-start--Author:huangzq Date:20160113
				// for：:TASK#858::【系统功能】logo替换
				if ("业务申请".equals(lang_context)) {

//					String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
//							+ lang_context + "</span></div>";
					StringBuilder ss = new StringBuilder();
					ss.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>");
					ss.append( lang_context + "</span></div>");
//					floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/ywsq.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/ywsq-up.png' style='display: none;' />"
//							+ ss + " </li> ";
					floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/ywsq.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/ywsq-up.png' style='display: none;' />");
					floor.append(ss + " </li> ");
				} else if ("个人办公".equals(lang_context)) {

//					String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
//							+ lang_context + "</span></div>";
					StringBuilder ss = new StringBuilder();
					ss.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>");
					ss.append( lang_context + "</span></div>");
//					floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/grbg.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/grbg-up.png' style='display: none;' />"
//							+ ss + " </li> ";
					floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/grbg.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/grbg-up.png' style='display: none;' />");
					floor.append(ss + " </li> ");
				} else if ("流程管理".equals(lang_context)) {

//					String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
//							+ lang_context + "</span></div>";
					StringBuilder ss = new StringBuilder();
					ss.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>");
					ss.append( lang_context + "</span></div>");
//					floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/lcsj.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/lcsj-up.png' style='display: none;' />"
//							+ ss + " </li> ";
					floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/lcsj.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/lcsj-up.png' style='display: none;' />");
					floor.append(ss + " </li> ");
				} else if ("Online 开发".equals(lang_context)) {

//					floor += " <li><img class='imag1' src='plug-in/login/images/online.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' />"
//							+ " </li> ";
					floor.append(" <li><img class='imag1' src='plug-in/login/images/online.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' />");
					floor.append(" </li> ");
				} else if ("表单管理".equals(lang_context)) {

//					String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
//							+ lang_context + "</span></div>";
					StringBuilder ss = new StringBuilder();
					ss.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>");
					ss.append( lang_context + "</span></div>");
//					floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/zdybd.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/zdybd-up.png' style='display: none;' />"
//							+ ss + " </li> ";
					floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/zdybd.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/zdybd-up.png' style='display: none;' />");
					floor.append( ss + " </li> ");
				} else if ("系统监控".equals(lang_context)) {

//					floor += " <li><img class='imag1' src='plug-in/login/images/xtjk.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' />"
//							+ " </li> ";
					floor.append(" <li><img class='imag1' src='plug-in/login/images/xtjk.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' />");
					floor.append( " </li> ");
				} else if ("统计报表".equals(lang_context)) {

//					floor += " <li><img class='imag1' src='plug-in/login/images/tjbb.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/tjbb_up.png' style='display: none;' />"
//							+ " </li> ";
					floor.append(" <li><img class='imag1' src='plug-in/login/images/tjbb.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/tjbb_up.png' style='display: none;' />");
					floor.append( " </li> ");
				} else if ("消息中间件".equals(lang_context)) {
//					String ss = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
//							+ lang_context + "</span></div>";
					StringBuilder ss = new StringBuilder();
					ss.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>");
					ss.append( lang_context + "</span></div>");
//					floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + ss
//							+ " </li> ";
					floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + ss);
					floor.append( " </li> ");
				} else if ("系统管理".equals(lang_context)) {

//					floor += " <li><img class='imag1' src='plug-in/login/images/xtgl.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' />"
//							+ " </li> ";
					floor.append(" <li><img class='imag1' src='plug-in/login/images/xtgl.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' />");
					floor.append( " </li> ");
				} else if ("常用示例".equals(lang_context)) {

//					floor += " <li><img class='imag1' src='plug-in/login/images/cysl.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' />"
//							+ " </li> ";
					floor.append(" <li><img class='imag1' src='plug-in/login/images/cysl.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' />");
					floor.append( " </li> ");
				} else if (lang_context.contains("消息推送")) {

					String s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>消息推送</div>";
//					floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> "
//							+ " <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + s
//							+ "</li> ";
					floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> ");
					floor.append(" <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />" + s);
					floor.append( " </li> ");
				} else {
					// 其他的为默认通用的图片模式
					StringBuilder s = new StringBuilder();
					if (lang_context.length() >= 5 && lang_context.length() < 7) {
//						s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
//								+ lang_context + "</span></div>";
						s.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>");
						s.append( lang_context + "</span></div>");
					} else if (lang_context.length() < 5) {
//						s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>"
//								+ lang_context + "</div>";
						s.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>");
						s.append( lang_context + "</div>");
					} else if (lang_context.length() >= 7) {
//						s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
//								+ lang_context.substring(0, 6) + "</span></div>";
						s.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>");
						s.append( lang_context.substring(0, 6) + "</span></div>");
					}
					/*
					 * floor +=
					 * " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> "
					 * +
					 * " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
					 * + s +"</li> ";
					 */
					/* 修复shortcut下只建一级菜单，点击无法进入菜单地址 */
					if (!StringUtils.isEmpty(function.getFunctionUrl())) {
//						floor += " <li style='position: relative;' subFunction='none' subtitle='" + lang_context
//								+ "' suburl='" + function.getFunctionUrl() + "' subicon='"
//								+ TSIcon.allTSIcons.get(function.getTSIcon().getId()).getIconClas()
//								+ "'><img class='imag1' src='plug-in/login/images/default.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
//								+ s + "</li> ";
						floor.append(" <li style='position: relative;' subFunction='none' subtitle='" + lang_context );
						floor.append("' suburl='" + function.getFunctionUrl() + "' subicon='");
						floor.append(TSIcon.allTSIcons.get(function.getTSIcon().getId()).getIconClas());
						floor.append("'><img class='imag1' src='plug-in/login/images/default.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />");
						floor.append( s + "</li> " );
					} else {
//						floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
//								+ s + "</li> ";
						floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />");
						floor.append(s + "</li> ");
					}
					/* 修复shortcut下只建一级菜单，点击无法进入菜单地址 */
				}
			}
		}
		// update-end--Author:huangzq Date:20160114 for：:TASK#858::【系统功能】logo替换
		return floor.toString();
	}

	/**
	 * @Title: top @author:wangkun @Description:
	 *         shortcut头部菜单二级菜单列表，并将其用ajax传到页面，实现动态控制二级菜单列表 @return
	 *         AjaxJson @throws
	 */
	@RequestMapping(params = "primaryMenuDiy")
	@ResponseBody
	public String getPrimaryMenuDiy() {
		// 取二级菜单
		List<TSFunction> primaryMenu = getFunctionMap(ResourceUtil.getSessionUserName()).get(1);
		StringBuilder floor = new StringBuilder();
		if (primaryMenu == null) {
			return floor.toString();
		}
		String menuString = "user.manage role.manage department.manage menu.manage";
		for (TSFunction function : primaryMenu) {
			if (menuString.contains(function.getFunctionName())) {
				if (function.getFunctionLevel() == 1) {

					String lang_key = function.getFunctionName();
					String lang_context = mutiLangService.getLang(lang_key);
					if ("申请".equals(lang_key)) {
						lang_context = "申请";
						StringBuilder s = new StringBuilder();
//						s = "<div style='width:67px;position: absolute;top:47px;text-align:center;color:#000000;font-size:12px;'>"
//								+ lang_context + "</div>";
						s.append("<div style='width:67px;position: absolute;top:47px;text-align:center;color:#000000;font-size:12px;'>");
						s.append(lang_context + "</div>");
//						floor += " <li><img class='imag1' src='plug-in/login/images/head_icon1.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/head_icon1.png' style='display: none;' />"
//								+ s + " </li> ";
						floor.append(" <li><img class='imag1' src='plug-in/login/images/head_icon1.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/head_icon1.png' style='display: none;' />");
						floor.append(s + " </li> ");
					} else if ("Online 开发".equals(lang_context)) {

//						floor += " <li><img class='imag1' src='plug-in/login/images/online.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' />"
//								+ " </li> ";
						floor.append(" <li><img class='imag1' src='plug-in/login/images/online.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' />");
						floor.append(" </li> ");
					} else if ("统计查询".equals(lang_context)) {

//						floor += " <li><img class='imag1' src='plug-in/login/images/guanli.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/guanli_up.png' style='display: none;' />"
//								+ " </li> ";
						floor.append(" <li><img class='imag1' src='plug-in/login/images/guanli.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/guanli_up.png' style='display: none;' />");
						floor.append(" </li> ");
					} else if ("系统管理".equals(lang_context)) {

//						floor += " <li><img class='imag1' src='plug-in/login/images/xtgl.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' />"
//								+ " </li> ";
						floor.append(" <li><img class='imag1' src='plug-in/login/images/xtgl.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' />");
						floor.append(" </li> ");
					} else if ("常用示例".equals(lang_context)) {

//						floor += " <li><img class='imag1' src='plug-in/login/images/cysl.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' />"
//								+ " </li> ";
						floor.append(" <li><img class='imag1' src='plug-in/login/images/cysl.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' />");
						floor.append(" </li> ");
					} else if ("系统监控".equals(lang_context)) {

//						floor += " <li><img class='imag1' src='plug-in/login/images/xtjk.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' />"
//								+ " </li> ";
						floor.append(" <li><img class='imag1' src='plug-in/login/images/xtjk.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' />");
						floor.append(" </li> ");
					} else if (lang_context.contains("消息推送")) {
						String s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>消息推送</div>";
//						floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />"
//								+ s + "</li> ";
						floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/msg.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/msg_up.png' style='display: none;' />");
						floor.append(s + "</li> ");
					} else {
						// 其他的为默认通用的图片模式
						StringBuilder s = new StringBuilder();
						if (lang_context.length() >= 5 && lang_context.length() < 7) {
//							s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'><span style='letter-spacing:-1px;'>"
//									+ lang_context + "</span></div>";
							s.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'><span style='letter-spacing:-1px;'>");
							s.append(lang_context + "</span></div>");
						} else if (lang_context.length() < 5) {
//							s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'>"
//									+ lang_context + "</div>";
							s.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'>");
							s.append(lang_context + "</div>");
						} else if (lang_context.length() >= 7) {
//							s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'><span style='letter-spacing:-1px;'>"
//									+ lang_context.substring(0, 6) + "</span></div>";
							s.append("<div style='width:67px;position: absolute;top:40px;text-align:center;color:#000000;font-size:12px;'><span style='letter-spacing:-1px;'>");
							s.append(lang_context.substring(0, 6) + "</span></div>");
						}
//						floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/head_icon2.png' /> "
//								+ " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
//								+ s + "</li> ";
						floor.append(" <li style='position: relative;'><img class='imag1' src='plug-in/login/images/head_icon2.png' /> ");
						floor.append(" <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />");
						floor.append(s + "</li> ");
					}
				}
			}
		}

		return floor.toString();
	}

	/**
	 * 云桌面返回：用户权限菜单
	 */
	@RequestMapping(params = "getPrimaryMenuForWebos")
	@ResponseBody
	public AjaxJson getPrimaryMenuForWebos() {
		AjaxJson j = new AjaxJson();
		// 将菜单加载到Session，用户只在登录的时候加载一次
		Object getPrimaryMenuForWebos = ContextHolderUtils.getSession().getAttribute("getPrimaryMenuForWebos");
		if (oConvertUtils.isNotEmpty(getPrimaryMenuForWebos)) {
			j.setMsg(getPrimaryMenuForWebos.toString());
		} else {
			String PMenu = ListtoMenu.getWebosMenu(getFunctionMap(ResourceUtil.getSessionUserName()));
			ContextHolderUtils.getSession().setAttribute("getPrimaryMenuForWebos", PMenu);
			j.setMsg(PMenu);
		}
		return j;
	}

	/**
	 * 另一套登录界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "login2")
	public String login2() {
		return "login/login2";
	}

	/**
	 * ACE登录界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "login3")
	public String login3() {
		return "login/login3";
	}

	/**
	 * 初始化公钥和私钥
	 */
	@RequestMapping(params = "initRSAKey")
	@ResponseBody
	public AjaxJson initRSAKey(HttpServletRequest request) {
		HttpSession session = ContextHolderUtils.getSession();
		AjaxJson j = new AjaxJson();
		// 初始化秘钥操作
		try {
			HashMap<String, Object> map;
			map = RSAUtils.getKeys();
			// 生成公钥和私钥
			RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
			RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
			session.setAttribute("privateKey", privateKey);// 私钥保存在session中，用于解密
			// 模
			String publicKeyModulus = publicKey.getModulus().toString(16);
			// 公钥指数
			String publicKeyExponent = publicKey.getPublicExponent().toString(16);

			Map<String, Object> m = new HashMap<String, Object>();
			m.put("publicKeyExponent", publicKeyExponent);
			m.put("publicKeyModulus", publicKeyModulus);
			j.setAttributes(m);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setMsg("初始化秘钥成功");
		return j;
	}
}