package org.jeecgframework.web.system.controller.core;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboBox;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.ValidForm;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.enums.SysThemesEnum;
import org.jeecgframework.core.util.*;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.datatable.DataTables;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.Client;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleFunction;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSRoleUserBase;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserAgentEntity;
import org.jeecgframework.web.system.pojo.base.TSUserCardInfoEntity;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.DepartService;
import org.jeecgframework.web.system.service.RoleService;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserOrgService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jeecg.bssys_deploy.service.BssysDeployServiceI;


/**
 * @ClassName: UserController
 * @Description: TODO(用户管理处理类)
 * @author 张代浩
 */
@Scope("prototype")
@Controller
@RequestMapping("/userController")
public class UserController extends BaseController {
	/**
	 * Logger for this class
	 */
	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private RoleUserService roleUserService;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private BssysDeployServiceI bssysDeployService;
	@Autowired
	private DepartService departService;
	@Autowired
	private RoleService roleService;
	
	private String message = null;


	/**
	 * 菜单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "menu")
	public void menu(HttpServletRequest request, HttpServletResponse response) {
		SetListSort sort = new SetListSort();
		HttpSession session = ContextHolderUtils.getSession();
		TSUser u = ResourceUtil.getSessionUserName();
		TSDepart dept = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
		// 登陆者的权限
		Set<TSFunction> loginActionlist = new HashSet();// 已有权限菜单
		List<TSRoleUser> rUsers = roleUserService.getRoleUserListByUserId(u.getId(), dept.getId());
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id", role.getId());
			if (roleFunctionList.size() > 0) {
				for (TSRoleFunction roleFunction : roleFunctionList) {
					TSFunction function = (TSFunction) roleFunction.getTSFunction();
					loginActionlist.add(function);
				}
			}
		}
		List<TSFunction> bigActionlist = new ArrayList();// 一级权限菜单
		List<TSFunction> smailActionlist = new ArrayList();// 二级权限菜单
		if (loginActionlist.size() > 0) {
			for (TSFunction function : loginActionlist) {
				if (function.getFunctionLevel() == 0) {
					bigActionlist.add(function);
				} else if (function.getFunctionLevel() == 1) {
					smailActionlist.add(function);
				}
			}
		}
		// 菜单栏排序
		Collections.sort(bigActionlist, sort);
		Collections.sort(smailActionlist, sort);
		String logString = ListtoMenu.getMenu(bigActionlist, smailActionlist);
		// request.setAttribute("loginMenu",logString);
		try {
			response.getWriter().write(logString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户列表页面跳转[跳转到标签和手工结合的html页面]
	 * 
	 * @return
	 */
	@RequestMapping(params = "userDemo")
	public String userDemo(HttpServletRequest request) {
		// 给部门查询条件中的下拉框准备数据
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
		return "system/user/userList2";
	}
	
	
	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "user")
	public String user(HttpServletRequest request) {
		return "system/user/userList";
	}

	/**
	 * 用户信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "userinfo")
	public String userinfo(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/userinfo";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "changepassword")
	public String changepassword(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		request.setAttribute("user", user);
		return "system/user/changepassword";
	}
	
	

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping(params = "savenewpwd")
	@ResponseBody
	public AjaxJson savenewpwd(HttpServletRequest request) throws Exception {
		AjaxJson j = new AjaxJson();
		TSUser user = ResourceUtil.getSessionUserName();
		String password = oConvertUtils.getString(request.getParameter("password"));
		String newpassword = oConvertUtils.getString(request.getParameter("newpassword"));
		//String pString = PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt());
		String pString = Md5CryptUtils.GetMD5Code(password);
		if (!pString.equals(user.getPassword())) {
			j.setMsg("原密码不正确");
			j.setSuccess(false);
		} else {
			try {
				//user.setPassword(PasswordUtil.encrypt(user.getUserName(), newpassword, PasswordUtil.getStaticSalt()));
				user.setPassword(Md5CryptUtils.GetMD5Code(newpassword));
			} catch (Exception e) {
				e.printStackTrace();
			}
			systemService.updateEntitie(user);
			j.setMsg("修改成功");

		}
		return j;
	}
	

	/**
	 * 
	 * 修改用户密码
	 * @author Chj
	 */
	
	@RequestMapping(params = "changepasswordforuser")
	public ModelAndView changepasswordforuser(TSUser user, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(user.getId())) {
			user = systemService.getEntity(TSUser.class, user.getId());
			req.setAttribute("user", user);
			idandname(req, user);
			System.out.println(user.getPassword()+"-----"+user.getRealName());
		}
		return new ModelAndView("system/user/adminchangepwd");
	}
	
	
	
	@RequestMapping(params = "savenewpwdforuser")
	@ResponseBody
	public AjaxJson savenewpwdforuser(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		String id = oConvertUtils.getString(req.getParameter("id"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		if (StringUtil.isNotEmpty(id)) {
			TSUser users = systemService.getEntity(TSUser.class,id);
			System.out.println(users.getUserName());
			//users.setPassword(PasswordUtil.encrypt(users.getUserName(), password, PasswordUtil.getStaticSalt()));
			try {
				users.setPassword(Md5CryptUtils.GetMD5Code(password));
			} catch (Exception e) {
				e.printStackTrace();
			}
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(users.getActivitiSync());
			systemService.updateEntitie(users);	
			message = "用户: " + users.getUserName() + "密码重置成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} 
		
		j.setMsg(message);

		return j;
	}
	/**
	 * 锁定账户
	
	 * 
	 * @author pu.chen
	 */
	@RequestMapping(params = "lock")
	@ResponseBody
	public AjaxJson lock(String id, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if(id==null || id.equals("")) {
			message = "参数id为空";
			j.setMsg(message);
			return j;
		}
		
		String lockValue=req.getParameter("lockvalue");
		if(!"0".equals(lockValue) && !"1".equals(lockValue)) {
			message = "参数无效，lockvalue：" + lockValue;
			j.setMsg(message);
			return j;
		}
		
		TSUser user = userService.getUser(id);
		if(user==null) {
			message = "未找到要锁定或激活的用户，id：" + id;
			j.setMsg(message);
			return j;
		}
		
		if(user.getStatus().equals(Globals.User_ADMIN)){
			message = "超级管理员不可操作";
			j.setMsg(message);
			return j;
		}
		
		

		user.setStatus(new Short(lockValue));
		try{
			userService.saveUser(user);
			if("0".equals(lockValue)){
				message = "用户：" + user.getUserName() + "锁定成功!";
			}else if("1".equals(lockValue)){
				message = "用户：" + user.getUserName() + "激活成功!";
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			message = "操作失败!";
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 得到角色列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	@ResponseBody
	public List<ComboBox> role(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
		String id = request.getParameter("id");
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		List<TSRole> roles = new ArrayList();
		if (StringUtil.isNotEmpty(id)) {
			List<TSRoleUser> roleUser = systemService.findByProperty(TSRoleUser.class, "TSUser.id", id);
			if (roleUser.size() > 0) {
				for (TSRoleUser ru : roleUser) {
					roles.add(ru.getTSRole());
				}
			}
		}
		List<TSRole> roleList = systemService.getList(TSRole.class);
		comboBoxs = TagUtil.getComboBox(roleList, roles, comboBox);
		return comboBoxs;
	}

	/**
	 * 得到部门列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "depart")
	@ResponseBody
	public List<ComboBox> depart(HttpServletResponse response, HttpServletRequest request, ComboBox comboBox) {
		String id = request.getParameter("id");
		List<ComboBox> comboBoxs = new ArrayList<ComboBox>();
		List<TSDepart> departs = new ArrayList();
		if (StringUtil.isNotEmpty(id)) {
			TSUser user = systemService.get(TSUser.class, id);
            List<TSDepart[]> resultList = systemService.findHql("from TSDepart d,TSUserOrg uo where d.id=uo.orgId and uo.id=?", id);
            for (TSDepart[] departArr : resultList) {
                departs.add(departArr[0]);
            }
        }
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		comboBoxs = TagUtil.getComboBox(departList, departs, comboBox);
		return comboBoxs;
	}

	/**
	 * easyuiAJAX用户列表请求数据 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(TSUser user,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String orgIds = request.getParameter("orgIds");
        String queryScope = request.getParameter("queryScope");
        if(queryScope!=null && "all".equals(queryScope)) {//查询全部时，设置orgIds参水为空
        	orgIds = null;
        }
      //第一次加载用户列表时，如果  非超级管理员则 按照当前登录用户所属的部门id 加载所有的用户
        HttpSession session = ContextHolderUtils.getSession();
		TSUser currentUser = (TSUser) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);

		if(!currentUser.getStatus().equals(Globals.User_ADMIN)) {//非管理员
			if(StringUtil.isEmpty(orgIds)){
				TSDepart dept = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
				
				orgIds = dept.getId();
	        }
		}
        PageList list = userService.queryUser(dataGrid.getPage(), dataGrid.getRows(), user, orgIds);
        
        dataGrid.setResults(list.getResultList());
        dataGrid.setTotal(list.getCount());
        TagUtil.datagrid(response, dataGrid);
    }

	/**
	 * 用户信息录入和更新
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSUser user, HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		if(user.getId()==null || user.getId().equals("")) {
			message = "参数id为空";
			j.setMsg(message);
			return j;
		}
		
		TSUser u = userService.getUser(user.getId());
		if(u==null) {
			message = "未找到要删除的用户，id：" + user.getId();
			j.setMsg(message);
			return j;
		}
		
		if(u.getStatus().equals(Globals.User_ADMIN)){
			message = "超级管理员不可删除";
			j.setMsg(message);
			return j;
		}
		
		int i = userService.deleteUserById(user.getId());
		
		if(i==0){
			message = "删除失败，请联系管理员！";
			j.setMsg(message);
			return j;
		}
		
		message = "用户：" + u.getUserName() + "删除成功";
		j.setMsg(message);
		return j;
	}

	/**
	 * 检查用户名
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkUser")
	@ResponseBody
	public ValidForm checkUser(HttpServletRequest request) {
		ValidForm v = new ValidForm();
		String userName=oConvertUtils.getString(request.getParameter("param"));
		String code=oConvertUtils.getString(request.getParameter("code"));
		List<TSUser> roles=systemService.findByProperty(TSUser.class,"userName",userName);
		if(roles.size()>0&&!code.equals(userName))
		{
			v.setInfo("用户名已存在");
			v.setStatus("n");
		}
		return v;
	}

	/**
	 * 用户录入
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "saveUser")
	@ResponseBody
	public AjaxJson saveUser(HttpServletRequest req, TSUser user,TSUserCardInfoEntity userCardInfo) {
		AjaxJson j = new AjaxJson();
		// 得到用户的部门和角色
		String orgIds = oConvertUtils.getString(req.getParameter("orgIds"));
		String roleIds = oConvertUtils.getString(req.getParameter("roleId"));
		String roleNames = oConvertUtils.getString(req.getParameter("roleName"));
		String password = oConvertUtils.getString(req.getParameter("password"));
		if (StringUtil.isNotEmpty(user.getId())) {
			TSUser users = userService.getUser(user.getId());
			users.setEmail(user.getEmail());
			users.setOfficePhone(user.getOfficePhone());
			users.setMobilePhone(user.getMobilePhone());
			users.setRealName(user.getRealName());
			users.setStatus(Globals.User_Normal);
			users.setActivitiSync(user.getActivitiSync());
			users.setUserKey(roleNames);
			users.setCardId(user.getCardId());
			userService.saveUser(users, orgIds, roleIds);
			List<TSUserCardInfoEntity> userCardInfoList=userService.getUserCardInfoList(users.getUserName());
			if(userCardInfoList.size()>0){
				if(!users.getCardId().equals(userCardInfoList.get(0).getUserCardId())){
					userCardInfo.setId(null);
					userCardInfo.setUserName(users.getUserName());
					userCardInfo.setUserCardId(users.getCardId());
					try {
						String currDateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
						Date currDate = DateUtils.parseDate(currDateStr, "yyyy-MM-dd HH:mm:ss");
						userCardInfo.setUpdateDate(currDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					userService.saveUserCardInfo(userCardInfo);
				}
			}else if(userCardInfoList.size()==0){
				userCardInfo.setId(null);
				userCardInfo.setUserName(users.getUserName());
				userCardInfo.setUserCardId(users.getCardId());
				try {
					String currDateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
					Date currDate = DateUtils.parseDate(currDateStr, "yyyy-MM-dd HH:mm:ss");
					userCardInfo.setUpdateDate(currDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				userService.saveUserCardInfo(userCardInfo);
			}
			message = "用户: " + users.getUserName() + "更新成功";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			TSUser users = userService.getUserByLoginName(user.getUserName());
			if (users != null) {
				message = "用户: " + users.getUserName() + "已经存在";
			} else {
				try {
				//user.setPassword(PasswordUtil.encrypt(user.getUserName(), password, PasswordUtil.getStaticSalt()));
				user.setPassword(Md5CryptUtils.GetMD5Code(password));
				user.setStatus(Globals.User_Normal);
				user.setUserKey(roleNames);
				userService.saveUser(user, orgIds, roleIds);
				userCardInfo.setUserName(user.getUserName());
				userCardInfo.setUserCardId(user.getCardId());
				String currDateStr = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
				Date currDate = DateUtils.parseDate(currDateStr, "yyyy-MM-dd HH:mm:ss");
				userCardInfo.setUpdateDate(currDate);
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				userService.saveUserCardInfo(userCardInfo);
				message = "用户: " + user.getUserName() + "添加成功";
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
		}
		j.setMsg(message);

		return j;
	}


	/**
	 * 用户选择角色跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "roles")
	public ModelAndView roles(HttpServletRequest request) {
		//--author：zhoujf-----start----date:20150531--------for: 编辑用户，选择角色,弹出的角色列表页面，默认没选中
		ModelAndView mv = new ModelAndView("system/user/users");
		String ids = oConvertUtils.getString(request.getParameter("ids"));
		mv.addObject("ids", ids);
		return mv;
		//--author：zhoujf-----end------date:20150531--------for: 编辑用户，选择角色,弹出的角色列表页面，默认没选中
	}

	/**
	 * 角色显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridRole")
	public void datagridRole(TSRole tsRole, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSRole.class, dataGrid);
		cq.add(Restrictions.eq("status","1"));
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tsRole);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
     * 用户选择角色列表跳转页面
     *
     * @return
     */
    @RequestMapping(params = "roleSelect")
    public String roleSelect(HttpServletRequest request) {
    	String orgIds = request.getParameter("orgIds");
    	String ids = request.getParameter("ids");
    	request.setAttribute("ids", ids);
    	request.setAttribute("orgIds", orgIds);
    	//组织机构与用户关系， 0：一对多；1：多对多，默认一对多
		String orgUserRelation = bssysDeployService.getValue("001001001", null);
		if(orgUserRelation==null || orgUserRelation.equals("")){
			orgUserRelation = "0";
		}
		
		//角色与用户关系， 0：角色用户一对多；1：角色用户多对多，默认多对多
		String roleUserRelation = bssysDeployService.getValue("001003001", null);
		if(roleUserRelation==null || roleUserRelation.equals("")){
			roleUserRelation = "1";
		}
		
		if("0".equals(orgUserRelation) && "0".equals(roleUserRelation)) {//组织用户一对多 && 用户角色多对一
			return "system/user/userRoleRadioSelectForSingleDept";
			
		}else if("0".equals(orgUserRelation) && "1".equals(roleUserRelation)) {//组织用户一对多 && 用户角色多对多
			return "system/user/userRoleCheckSelectForSingleDept";
			
		}else if("1".equals(orgUserRelation) && "0".equals(roleUserRelation)) {//组织用户多对多 && 用户角色多对一
			return "system/user/userRoleRadioSelectForMutiDept";
			
		}else if("1".equals(orgUserRelation) && "1".equals(roleUserRelation)) {//组织用户多对多 && 用户角色多对多
			return "system/user/userRoleCheckSelectForMutiDept";
		}
        return null;
    }
    
    /**
	 * 用户管理菜单页面：展示角色列表树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "getRoleTree")
	@ResponseBody
	public AjaxJson getRoleTree(HttpServletRequest request, HttpServletResponse response){
		
		AjaxJson j = new AjaxJson();
		String orgIds = request.getParameter("orgIds");
		if(orgIds==null || "".equals(orgIds)) {
			j.setMsg("orgIds参数为空");
			j.setSuccess(false);
			return j;
		}
		
		//根据部门ids查询部门列表
		String[] orgIdArray = orgIds.split(",");
		List<TSDepart> departList = new ArrayList<TSDepart>();
		for(int i=0;i<orgIdArray.length;i++) {
			String id = orgIdArray[i];
			if(id==null || "".equals(id)) {
				continue;
			}
			TSDepart d = departService.getDepart(id);
			if(d != null) {
				departList.add(d);
			}
		}
		if(departList.size() == 0) {
			j.setMsg("orgIds参数无效,orgIds:" + orgIds);
			j.setSuccess(false);
			return j;
		}
		
		//查询角色列表
		List<TSRole> roleList = roleService.getAllRoles();
		if(roleList==null || roleList.size()==0) {
			j.setMsg("未找到角色！");
			j.setSuccess(false);
			return j;
		}
		
		String ids = request.getParameter("ids");
		ids = "," + ids + ",";
		
		//拼接json
		List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
		for(int i=0; i<departList.size(); i++ ) {
			TSDepart dept = departList.get(i);
			//添加部门节点
			Map<String,Object> departMap = new HashMap<String,Object>();
			departMap.put("id", dept.getId());
			departMap.put("name", dept.getDepartname());
			departMap.put("nocheck", true);
			departMap.put("pId", "1");
			departMap.put("isParent",true);
			dateList.add(departMap);
			
			//为部门添加角色节点
			for(int m=0; m<roleList.size(); m++) {
				TSRole role = roleList.get(m);
				String nodeId = dept.getId() + "_" + role.getId();
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", nodeId);
				map.put("name", role.getRoleName());
				if(ids.indexOf("," + nodeId + ",")!=-1) {
					map.put("checked", true);
				}
				map.put("pId", dept.getId());
				dateList.add(map);
			}
		}
		
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(dateList);
		j.setMsg(jsonArray.toString());
		return j;
	}

	/**
	 * easyuiAJAX请求数据： 用户选择角色列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSUser user, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(user.getId())) {
			user = userService.getUser(user.getId());
			
			req.setAttribute("user", user);
			idandname(req, user);
			getOrgInfos(req, user);
		}else {
			String departId = req.getParameter("departId");

	        if(StringUtil.isNotEmpty(departId)){
	        	TSDepart tsDepart = systemService.getEntity(TSDepart.class, departId);
	        	if(tsDepart != null) {
	        		req.setAttribute("orgIds", tsDepart.getId());
	        		req.setAttribute("departname", tsDepart.getDepartname());
	        	}
	        }
		}

		//组织机构与用户关系， 0：一对多；1：多对多，默认一对多
		String orgUserRelation = bssysDeployService.getValue("001001001", null);
		if(orgUserRelation==null || orgUserRelation.equals("")){
			orgUserRelation = "0";
		}
		//角色与用户关系， 0：角色用户一对多；1：角色用户多对多，默认多对多
		String roleUserRelation = bssysDeployService.getValue("001003001", null);
		if(roleUserRelation==null || roleUserRelation.equals("")){
			roleUserRelation = "1";
		}
		req.setAttribute("orgUserRelation", orgUserRelation);
		req.setAttribute("roleUserRelation", roleUserRelation);
				
        return new ModelAndView("system/user/user");
	}
	
	/**
	 * 处理角色管理-用户列表页面的“用户编辑”请求
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "editUserFromRole")
	public ModelAndView editUserFromRole(HttpServletRequest req) {
		//组织机构与用户关系， 0：一对多；1：多对多，默认一对多
		String orgUserRelation = bssysDeployService.getValue("001001001", null);
		if(orgUserRelation==null || orgUserRelation.equals("")){
			orgUserRelation = "0";
		}
		//角色与用户关系， 0：角色用户一对多；1：角色用户多对多，默认多对多
		String roleUserRelation = bssysDeployService.getValue("001003001", null);
		if(roleUserRelation==null || roleUserRelation.equals("")){
			roleUserRelation = "1";
		}
		req.setAttribute("orgUserRelation", orgUserRelation);
		req.setAttribute("roleUserRelation", roleUserRelation);
		
		String roleUserId = req.getParameter("id");
		if(roleUserId==null || "".equals(roleUserId)) {
			return new ModelAndView("system/user/user");
		}
		
		TSRoleUser roleUser = roleUserService.getRoleUser(roleUserId);
		if(roleUser == null) {
			return new ModelAndView("system/user/user");
		}
		
		TSUser user = roleUser.getTSUser();
		req.setAttribute("user", user);
		idandname(req, user);
		getOrgInfos(req, user);

        return new ModelAndView("system/user/user");
	}

//    update-start--Author:zhangguoming  Date:20140825 for：添加新的业务逻辑方法
    /**
     * 用户的登录后的组织机构选择页面
     * @param request request
     * @return 用户选择组织机构页面
     */
	@RequestMapping(params = "userOrgSelect")
	public ModelAndView userOrgSelect(HttpServletRequest request) {
		List<TSDepart> orgList = new ArrayList<TSDepart>();
		String userId = oConvertUtils.getString(request.getParameter("userId"));

        List<Object[]> orgArrList = systemService.findHql("from TSDepart d,TSUserOrg uo where d.id=uo.tsDepart.id and uo.tsUser.id=?", new String[]{userId});
        for (Object[] departs : orgArrList) {
            orgList.add((TSDepart) departs[0]);
        }
        request.setAttribute("orgList", orgList);

        TSUser user = systemService.getEntity(TSUser.class, userId);
        request.setAttribute("user", user);

		return new ModelAndView("system/user/userOrgSelect");
    }

	private void idandname(HttpServletRequest req, TSUser user) {
		List<TSRoleUser> roleUsers = roleUserService.getRoleUserListByUserId(user.getId());
		String roleId = "";
		String roleName = "";
		if (roleUsers!=null && roleUsers.size() > 0) {
			for (TSRoleUser tRoleUser : roleUsers) {
				roleId = roleId + tRoleUser.gettSDepart().getId() + "_" + tRoleUser.getTSRole().getId() + ",";
				roleName = roleName + tRoleUser.gettSDepart().getDepartname() + "_" + tRoleUser.getTSRole().getRoleName() + ",";
			}
		}
		//去除角色后面的逗号
		if(roleId.length()>1){
			roleId = roleId.substring(0, roleId.length()-1);
			roleName = roleName.substring(0, roleName.length()-1);
		}
		req.setAttribute("roleId", roleId);
		req.setAttribute("roleName", roleName);
	}
	
	public void getOrgInfos(HttpServletRequest req, TSUser user) {
		List<TSUserOrg> tSUserOrgs = userOrgService.getUserOrgListByUserId(user.getId());
		String orgIds = "";
		String departname = "";
		if (tSUserOrgs!=null && tSUserOrgs.size() > 0) {
			for (TSUserOrg tSUserOrg : tSUserOrgs) {
				orgIds += tSUserOrg.getTsDepart().getId() + ",";
				departname += tSUserOrg.getTsDepart().getDepartname() + ",";
			}
		}
		//去除组织机构后面的逗号
		if(orgIds.length()>1){
			orgIds = orgIds.substring(0, orgIds.length()-1);
			departname = departname.substring(0, departname.length()-1);
		}
		req.setAttribute("orgIds", orgIds);
		req.setAttribute("departname", departname);

	}
	
	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "choose")
	public String choose(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/checkuser";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseUser")
	public String chooseUser(HttpServletRequest request) {
		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");
		request.setAttribute("roleid", roleid);
		request.setAttribute("departid", departid);
		return "system/membership/userlist";
	}

	/**
	 * 部门和角色选择用户的用户显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridUser")
	public void datagridUser(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String departid = request.getParameter("departid");
		String roleid = request.getParameter("roleid");
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if (departid.length() > 0) {
			cq.eq("TDepart.departid", oConvertUtils.getInt(departid, 0));
			cq.add();
		}
		String userid = "";
		if (roleid.length() > 0) {
			List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TRole.roleid", oConvertUtils.getInt(roleid, 0));
			if (roleUsers.size() > 0) {
				for (TSRoleUser tRoleUser : roleUsers) {
					userid += tRoleUser.getTSUser().getId() + ",";
				}
			}
			cq.in("userid", oConvertUtils.getInts(userid.split(",")));
			cq.add();
		}
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 根据部门和角色选择用户跳转页面
	 */
	@RequestMapping(params = "roleDepart")
	public String roleDepart(HttpServletRequest request) {
		List<TSRole> roles = systemService.loadAll(TSRole.class);
		request.setAttribute("roleList", roles);
		return "system/membership/roledepart";
	}

	/**
	 * 部门和角色选择用户的panel跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "chooseDepart")
	public ModelAndView chooseDepart(HttpServletRequest request) {
		String nodeid = request.getParameter("nodeid");
		ModelAndView modelAndView = null;
		if (nodeid.equals("role")) {
			modelAndView = new ModelAndView("system/membership/users");
		} else {
			modelAndView = new ModelAndView("system/membership/departList");
		}
		return modelAndView;
	}

	/**
	 * 部门和角色选择用户的用户显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridDepart")
	public void datagridDepart(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 测试
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "test")
	public void test(HttpServletRequest request, HttpServletResponse response) {
		String jString = request.getParameter("_dt_json");
		DataTables dataTables = new DataTables(request);
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataTables);
		String username = request.getParameter("userName");
		if (username != null) {
			cq.like("userName", username);
			cq.add();
		}
		DataTableReturn dataTableReturn = systemService.getDataTableReturn(cq, true);
		TagUtil.datatable(response, dataTableReturn, "id,userName,mobilePhone,TSDepart_departname");
	}

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "index")
	public String index() {
		return "bootstrap/main";
	}

	/**
	 * 用户列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "main")
	public String main() {
		return "bootstrap/test";
	}

	/**
	 * 测试
	 * 
	 * @return
	 */
	@RequestMapping(params = "testpage")
	public String testpage(HttpServletRequest request) {
		return "test/test";
	}

	/**
	 * 设置签名跳转页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "addsign")
	public ModelAndView addsign(HttpServletRequest request) {
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return new ModelAndView("system/user/usersign");
	}

	/**
	 * 用户录入
	 * 
	 * @param user
	 * @param req
	 * @return
	 */

	@RequestMapping(params = "savesign", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson savesign(HttpServletRequest req) {
		UploadFile uploadFile = new UploadFile(req);
		String id = uploadFile.get("id");
		TSUser user = systemService.getEntity(TSUser.class, id);
		uploadFile.setRealPath("signatureFile");
		uploadFile.setCusPath("signature");
		uploadFile.setByteField("signature");
		uploadFile.setBasePath("resources");
		uploadFile.setRename(false);
		uploadFile.setObject(user);
		AjaxJson j = new AjaxJson();
		message = user.getUserName() + "设置签名成功";
		systemService.uploadFile(uploadFile);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);

		return j;
	}
	/**
	 * 测试组合查询功能
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "testSearch")
	public void testSearch(TSUser user, HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		if(user.getUserName()!=null){
			cq.like("userName", user.getUserName());
		}
		if(user.getRealName()!=null){
			cq.like("realName", user.getRealName());
		}
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "changestyle")
	public String changeStyle(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		if(user==null){
			return "login/login";
		}
		SysThemesEnum sysThemesEnum = SysThemesUtil.getSysTheme(request);
		request.setAttribute("indexStyle", sysThemesEnum.getStyle());
		return "system/user/changestyle";
	}
	/**
	* @Title: saveStyle
	* @Description: 修改首页样式
	* @param request
	* @return AjaxJson    
	* @throws
	 */
	@RequestMapping(params = "savestyle")
	@ResponseBody
	public AjaxJson saveStyle(HttpServletRequest request,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		j.setSuccess(Boolean.FALSE);
		TSUser user = ResourceUtil.getSessionUserName();
		if(user!=null){
			String indexStyle = request.getParameter("indexStyle");
			
			if(StringUtils.isNotEmpty(indexStyle)){
				Cookie cookie = new Cookie("JEECGINDEXSTYLE"+ "_" + request.getContextPath(), indexStyle);
				//设置cookie有效期为一个月
				cookie.setMaxAge(3600*24*30);
				response.addCookie(cookie);
				logger.info("indexStyle:"+indexStyle);
				j.setSuccess(Boolean.TRUE);
				j.setMsg("样式修改成功，请刷新页面");
			}
            //update-start--Author:JueYue  Date:2014-5-28 for:风格切换,菜单懒加载失效的问题
            ClientManager.getInstance().getClient().getFunctions().clear();
            //update-end--Author:JueYue  Date:2014-5-28 for:风格切换,菜单懒加载失效的问题
		}else{
			j.setMsg("请登录后再操作");
		}
		return j;
	}
	
	@RequestMapping(params = "changeDepart")
	public String changeDepart(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		if(user==null){
			return "login/login";
		}
		HttpSession session = ContextHolderUtils.getSession();
		TSDepart dep = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
		request.setAttribute("currentDepartId", dep.getId());
		
		return "system/user/changeDepart";
	}
	
	@RequestMapping(params = "saveLoginDepart")
	@ResponseBody
	public AjaxJson saveLoginDepart(HttpServletRequest request,HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		j.setSuccess(Boolean.FALSE);
		TSUser user = ResourceUtil.getSessionUserName();
		if(user == null) {
			j.setMsg("请登录后再操作");
			return j;
		}
		
		String departId = request.getParameter("departId");
		if(StringUtils.isEmpty(departId)) {
			j.setMsg("参数获取失败");
			return j;
		}
		
		HttpSession session = ContextHolderUtils.getSession();
		List<TSDepart> list = (List<TSDepart>) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_LIST);
		if(list==null || list.size()==0) {
			j.setMsg("用户归属组织列表为空");
			return j;
		}
		
		TSDepart targetDepart = null;
		for(int i=0;i<list.size();i++) {
			targetDepart = list.get(i);
			if(targetDepart.getId().equals(departId)){
				break;
			}
		}
		
		if(targetDepart == null) {
			j.setMsg("未找到要切换的目标组织机构：" + departId);
			return j;
		}
		
		//切换组织机构
		session.setAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART, targetDepart);
		session.setAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID, targetDepart.getRegionId());
		
		//将currentDepart放到user中，解决项目组user.getCurrentDepart方法取值问题
		user.setCurrentDepart(targetDepart);
		session.setAttribute(ResourceUtil.LOCAL_CLINET_USER, user);
		
		request.setAttribute("currentOrgName", targetDepart.getDepartname());
		
		Cookie cookie = new Cookie("JEECG_LOGIN_USER_DEPART" + user.getUserName() + "_" + request.getContextPath(), departId);
		
		//清空菜单，以免用户多个部门间的权限紊乱
		Client client = ClientManager.getInstance().getClient(session.getId());
		client.setFunctionMap(null);
		client.setFunctions(null);
		Enumeration<String> attributes = session.getAttributeNames();
		while(attributes.hasMoreElements()){
            String attributeName = attributes.nextElement();//调用nextElement方法获得元素
            if(attributeName.startsWith("leftMenuCache")) {
            	session.setAttribute(attributeName, null);
            }
        }
		
		//设置cookie有效期为一个月
		cookie.setMaxAge(3600*24*30);
		response.addCookie(cookie);
		j.setSuccess(Boolean.TRUE);
		j.setMsg("组织机构切换成功，请刷新页面");
		
		return j;
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","userController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TSUser tsUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tsUser, request.getParameterMap());
		List<TSUser> tsUsers = this.systemService.getListByCriteriaQuery(cq,false);
		//导出的时候处理一下组织机构编码和角色编码
		for(int i=0;i<tsUsers.size();i++){
			TSUser user = tsUsers.get(i);
			//托管
			systemService.getSession().evict(user);
			List<TSRoleUser> roleUsers = roleUserService.getRoleUserListByUserId(user.getId());
			String roleName = "";
			if (roleUsers!=null && roleUsers.size() > 0) {
				for (TSRoleUser tRoleUser : roleUsers) {
					roleName = roleName + tRoleUser.gettSDepart().getDepartname() + "_" + tRoleUser.getTSRole().getRoleName() + ",";
				}
			}			
			if(!roleName.equals("")){
				user.setUserKey(roleName.substring(0,roleName.length()-1));
			}
			
			List<TSUserOrg> tSUserOrgs = userOrgService.getUserOrgListByUserId(user.getId());
			String departname = "";
			if (tSUserOrgs!=null && tSUserOrgs.size() > 0) {
				for (TSUserOrg tSUserOrg : tSUserOrgs) {
					departname += tSUserOrg.getTsDepart().getDepartname() + ",";
				}
			}
			if(!departname.equals("")) {
				user.setDepartid(departname.substring(0,departname.length()-1));
			}
		}
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户表");
		modelMap.put(NormalExcelConstants.CLASS,TSUser.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tsUsers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TSUser tsUser,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME,"用户表");
		modelMap.put(NormalExcelConstants.CLASS,TSUser.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("用户表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
				"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TSUser> tsUsers = ExcelImportUtil.importExcel(file.getInputStream(),TSUser.class,params);
				for (TSUser tsUser : tsUsers) {
					tsUser.setStatus(new Short("1"));
					String username = tsUser.getUserName();
					String roleCodes = tsUser.getUserKey();
					String deptCodes = tsUser.getDepartid();

					if(username==null||username.equals("")){
						j.setMsg("用户名为必填字段，导入失败");
					}else if((roleCodes==null||roleCodes.equals(""))||(deptCodes==null||deptCodes.equals(""))){
						List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",username);
						if(users.size()!=0){
							//用户存在更新
							TSUser user = users.get(0);
							MyBeanUtils.copyBeanNotNull2Bean(tsUser,user);
							user.setDepartid(null);
							systemService.saveOrUpdate(user);
						}else{
							tsUser.setDepartid(null);
							systemService.save(tsUser);
						}
					}else{
						String[] roles = roleCodes.split(",");
						String[] depts = deptCodes.split(",");
						boolean flag = true;
						//判断组织机构编码和角色编码是否存在，如果不存在，也不能导入
						for(String roleCode:roles){
							List<TSRole> roleList = systemService.findByProperty(TSRole.class,"roleCode",roleCode);
							if(roleList.size()==0){
								flag = false;
							}
						}

						for(String deptCode:depts){
							List<TSDepart> departList = systemService.findByProperty(TSDepart.class,"orgCode",deptCode);
							if(departList.size()==0){
								flag = false;
							}
						}

						if(flag){
							//判断用户是否存在
							List<TSUser> users = systemService.findByProperty(TSUser.class,"userName",username);
							if(users.size()!=0){
								//用户存在更新
								TSUser user = users.get(0);
								MyBeanUtils.copyBeanNotNull2Bean(tsUser,user);
								user.setDepartid(null);
								systemService.saveOrUpdate(user);

								String id = user.getId();
								systemService.executeSql("delete from t_s_role_user_base where userid='"+id+"'");
								for(String roleCode:roles){
									//根据角色编码得到roleid
									List<TSRole> roleList = systemService.findByProperty(TSRole.class,"roleCode",roleCode);
									TSRoleUserBase tsRoleUser = new TSRoleUserBase();
									tsRoleUser.setTSUser(user);
									tsRoleUser.setTSRole(roleList.get(0));
									systemService.save(tsRoleUser);
								}

								systemService.executeSql("delete from t_s_user_org where user_id='"+id+"'");
								for(String orgCode:depts){
									//根据角色编码得到roleid
									List<TSDepart> departList = systemService.findByProperty(TSDepart.class,"orgCode",orgCode);
									TSUserOrg tsUserOrg = new TSUserOrg();
									tsUserOrg.setTsDepart(departList.get(0));
									tsUserOrg.setTsUser(user);
									systemService.save(tsUserOrg);
								}
							}else{
								//不存在则保存
								//TSUser user = users.get(0);
								tsUser.setDepartid(null);
								systemService.save(tsUser);
								for(String roleCode:roles){
									//根据角色编码得到roleid
									List<TSRole> roleList = systemService.findByProperty(TSRole.class,"roleCode",roleCode);
									TSRoleUserBase tsRoleUser = new TSRoleUserBase();
									tsRoleUser.setTSUser(tsUser);
									tsRoleUser.setTSRole(roleList.get(0));
									systemService.save(tsRoleUser);
								}

								for(String orgCode:depts){
									//根据角色编码得到roleid
									List<TSDepart> departList = systemService.findByProperty(TSDepart.class,"orgCode",orgCode);
									TSUserOrg tsUserOrg = new TSUserOrg();
									tsUserOrg.setTsDepart(departList.get(0));
									tsUserOrg.setTsUser(tsUser);
									systemService.save(tsUserOrg);
								}
							}
							j.setMsg("文件导入成功！");
						}else {
							j.setMsg("组织机构编码和角色编码不能匹配");
						}
					}
				}
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	/**
	 * 选择用户跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "userSelect")
	public String userSelect() {
		return "system/user/userSelect";
	}
	
	@RequestMapping(params = "userCardInfo")
	public ModelAndView userCardInfo(TSUserCardInfoEntity tSUserCardInfo) {
		HttpSession session = ContextHolderUtils.getSession();
		session.setAttribute("tSUserCardInfoPage", tSUserCardInfo);
		return new ModelAndView("system/user/tSUserCardInfoList");
	}
	@RequestMapping(params = "userCardInfoDatagrid")
	public void userCardInfoDatagrid(TSUserCardInfoEntity tSUserCardInfo, HttpServletResponse response, DataGrid dataGrid) {
		HttpSession session = ContextHolderUtils.getSession();
		CriteriaQuery cq = new CriteriaQuery(TSUserCardInfoEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,tSUserCardInfo);
		tSUserCardInfo=(TSUserCardInfoEntity) session.getAttribute("tSUserCardInfoPage");
		String userName=tSUserCardInfo.getUserName();
		cq.eq("userName", userName);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
}