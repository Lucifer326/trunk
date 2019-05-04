package org.jeecgframework.web.system.controller.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSDepartImport;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.DepartService;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.jeecgframework.web.system.service.TypegroupServiceI;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jeecg.bssys_deploy.service.BssysDeployServiceI;
import com.jeecg.system_region.entity.SystemRegionEntity;
import com.jeecg.system_region.service.SystemRegionServiceI;

/**
 * 部门信息处理类
 * 
 * @author 张代浩
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/departController")
public class DepartController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DepartController.class);
	private UserService userService;
	private SystemService systemService;

	@Autowired
	private DepartService departService;
	@Autowired
	private UserOrgService userOrgService;
	@Autowired
	private TypeServiceI typeService;
	@Autowired
	private BssysDeployServiceI bssysDeployService;
	@Autowired
	private SystemRegionServiceI systemRegionService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 部门列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "depart")
	public ModelAndView depart() {
		return new ModelAndView("system/depart/departList2");
	}

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	// update-start--Author:zhangguoming Date:20140825 for：添加业务逻辑；添加类注释；

	/**
	 * 判断组织是否可删除
	 * 
	 * @param role
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "judgeDelDept")
	@ResponseBody
	public AjaxJson judgeDelDept(TSDepart depart, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();

		depart = systemService.getEntity(TSDepart.class, depart.getId());

		TSUser user = ResourceUtil.getSessionUserName();

		if (depart.getId().equals("8a8ab0b246dc81120146dc8180a20016")) {
			j.setSuccess(false);
			j.setMsg("系统初始化数据，不可删除");
			return j;
		}
		// 存在下级组织机构
		if (depart.getTSDeparts().size() > 0) {// 判断 组织是否存在下级

			if (Globals.User_ADMIN != user.getStatus()) {// 如果是超级用户 则继续执行 否则 返回
															// 提示信息
				j.setSuccess(false);
				j.setMsg("此组织存在下级,请先删除下级组织机构");
				return j;
			}

			// 若是管理员用户，给出确认提示框
			j.setSuccess(true);
			j.setMsg("此组织中存在下级，删除会同步删除下级组织机构以及关联用户，是否继续？");
			return j;
		}

		// 不存在下级组织
		// 查询 该组织下是否存在用户
		Long userCount = departService.getUsersCount(depart.getId());

		if (userCount > 0) {
			// 存在关联用户
			if (Globals.User_ADMIN == user.getStatus()) {// 如果是超级用户 则继续执行 否则 返回
															// 提示信息
				j.setSuccess(true);
				j.setMsg("组织中存在用户，继续删除会同步删除相关用户，是否继续？");
				return j;
			}

			// 存在关联用户，且不是关联用户
			// 是否允许删除关联用户的组织机构
			// ，1：是；0：否，默认为否。当配置“是”时，验证是否有级联用户，若有，给出提示，用户确认后级联删除用户；当配置“否”时，验证是否有级联用户，有则不允许删除。
			String rr = bssysDeployService.getValue("001001002", null);
			if (rr == null || rr.equals("")) {
				rr = "0";
			}

			// 配置为“0”，表示不允许删除存在关联用户的组织机构
			if ("0".equals(rr)) {
				j.setSuccess(false);
				j.setMsg("删除失败，组织存在关联用户，请先删除关联用户");
				return j;
			}

			// 否则，给出用户提示信息
			j.setSuccess(true);
			j.setMsg("组织中存在用户，继续删除会同步删除相关用户，是否继续？");
			return j;
		}

		// 不存在下级且不存在关联用户
		j.setSuccess(true);
		j.setMsg("");
		return j;
	}

	/**
	 * 删除组织
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "delDept")
	@ResponseBody
	public AjaxJson delDept(TSDepart depart, HttpServletRequest request) {

		AjaxJson j = new AjaxJson();

		if (depart == null) {
			j.setMsg("删除失败，参数为空");
			return j;
		}
		if (depart.getId() == "8a8ab0b246dc81120146dc8180a20016") {

			j.setMsg("系统初始化数据，不可删除");
			return j;
		}

		if (depart.getId() == null || depart.getId().equals("")) {
			j.setMsg("删除失败，参数为空");
			return j;
		}
		int i = departService.deleteDept(depart.getId());
		if (i == -1) {
			j.setMsg("删除失败，组织仍存在用户，请先删除关联关系");
			return j;
		}

		j.setMsg("组织删除成功");
		return j;
	}

	/**
	 * 判断用户组织关系删除是否需要级联删除用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "judgeDelUserRole")
	@ResponseBody
	public AjaxJson judgeDelUserRole(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String userId = request.getParameter("userId");
		if (userId == null || userId.equals("")) {
			j.setSuccess(false);
			j.setMsg("参数userId为空");
			return j;
		}

		// 取得用户归属组织机构列表
		List<TSUserOrg> list = userOrgService.getUserOrgListByUserId(userId);
		if (list == null || list.size() == 0) {
			j.setSuccess(false);
			j.setMsg("未找到用户组织机构关联数据，userId：" + userId);
			return j;
		}

		if (list.size() == 1) {
			j.setMsg("用户只归属于当前组织机构，此删除操作会同步删除用户，是否继续？");
			return j;
		}

		// 验证通过，可以删除，返回空消息
		j.setMsg("");
		return j;
	}

	/**
	 * 删除用户组织关系
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "deleteUserOrg")
	@ResponseBody
	public AjaxJson deleteUserOrg(TSUser user, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String userId = request.getParameter("userId");
		String orgId = request.getParameter("orgId");

		if (userId == null || userId.equals("")) {
			j.setSuccess(false);
			j.setMsg("参数userId为空");
			return j;
		}

		if (orgId == null || orgId.equals("")) {
			j.setSuccess(false);
			j.setMsg("参数orgId为空");
			return j;
		}

		int i = departService.deleteUserOrg(userId, orgId);
		if (i == -1) {
			j.setMsg("删除失败，用户" + user.getRealName() + "只归属于当前组织机构，禁止删除！");
			return j;
		}

		j.setMsg("删除成功");
		return j;
	}

	public void upEntity(TSDepart depart) {
		List<TSUser> users = systemService.findByProperty(TSUser.class, "TSDepart.id", depart.getId());
		if (users.size() > 0) {
			for (TSUser tsUser : users) {
				// tsUser.setTSDepart(null);
				// systemService.saveOrUpdate(tsUser);
				systemService.delete(tsUser);
			}
		}
	}

	/**
	 * 上级组织机构选择跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "selectDepart")
	public ModelAndView selectDepart(String regionId, HttpServletRequest request) {
		// --author：zhoujf-----start----date:20150531--------for:
		// 编辑用户，选择角色,弹出的角色列表页面，默认没选中

		request.setAttribute("regionId", regionId);
		ModelAndView mv = new ModelAndView("system/depart/selectDepart");
		String id = oConvertUtils.getString(request.getParameter("id"));
		mv.addObject("id", id);
		return mv;
		// --author：zhoujf-----end------date:20150531--------for:
		// 编辑用户，选择角色,弹出的角色列表页面，默认没选中
	}

	/**
	 * 上级组织机构显示列表
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridDepart")
	public void datagridDepart(TSDepart depart, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		String regionId = request.getParameter("regionId");
		cq.eq("regionId", regionId);
		// cq.add(Restrictions.eq("status","1"));
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 判断是否选择了上级组织机构
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "judgeParentDept")
	@ResponseBody
	public AjaxJson judgeParentDept(TSDepart depart, HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		if (StringUtil.isEmpty(depart.getId())) {
			j.setSuccess(false);
			j.setMsg("请先选择父组织机构");
			return j;
		}
		// 验证通过
		return j;
	}

	/**
	 * 添加部门
	 *
	 * @param depart
	 * @return
	 */
	@RequestMapping(params = "saveDepart")
	@ResponseBody
	public AjaxJson saveDepart(TSDepart depart, HttpServletRequest request) {
		// 设置上级部门
		String pid = request.getParameter("TSPDepart.id");
		if (pid.equals("")) {
			depart.setTSPDepart(null);
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(depart.getId())) {// 编辑
			departService.saveOrUpdate(depart);

			message = MutiLangUtil.paramUpdSuccess("common.department");
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {// 新增
			String maxOrgCode = departService.generateOrgCode(pid);
			depart.setOrgCode(maxOrgCode);

			departService.save(depart);
			message = MutiLangUtil.paramAddSuccess("common.department");
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加部门
	 * 
	 * @param depart
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TSDepart depart, HttpServletRequest request) {
		// 设置上级部门
		String pid = request.getParameter("TSPDepart.id");
		if (pid.equals("")) {
			depart.setTSPDepart(null);
		}
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(depart.getId())) {
			message = MutiLangUtil.paramUpdSuccess("common.department");
			systemService.saveOrUpdate(depart);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = MutiLangUtil.paramAddSuccess("common.department");
			systemService.save(depart);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}

		j.setMsg(message);
		return j;
	}

	/**
	 * 新增页面跳转
	 * 
	 * @param depart
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "add")
	public ModelAndView add(TSDepart depart, HttpServletRequest req) {

		TSDepart dept = new TSDepart();
		if (StringUtil.isNotEmpty(depart.getId())) {// 有父级组织机构
			TSDepart parentDept = systemService.getEntity(TSDepart.class, depart.getId());
			dept.setTSPDepart(parentDept);
			dept.setRegionId(parentDept.getRegionId());
		}
		// 用于页面显示区划名称
		if (StringUtil.isNotEmpty(dept.getRegionId())) {
			SystemRegionEntity region = systemService.getEntity(SystemRegionEntity.class, dept.getRegionId());
			req.setAttribute("region", region);
		}
		req.setAttribute("depart", dept);
		return new ModelAndView("system/depart/depart2");
	}

	@RequestMapping(params = "loadOrgType")
	@ResponseBody
	public String loadOrgType(HttpServletRequest req) {
		List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
		CriteriaQuery cq = new CriteriaQuery(TSTypegroup.class);
		cq.eq("typegroupcode", "orgtype");
		cq.add();
		List<TSTypegroup> types = systemService.getListByCriteriaQuery(cq, false);
		List<TSType> list = types.get(0).getTSTypes();
		for (TSType t : list) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("typecode", t.getTypecode());
			data.put("typename", t.getTypename());
			datas.add(data);
		}
		return JSON.toJSONString(datas);
	}

	/**
	 * 部门列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "update")
	public ModelAndView update(TSDepart depart, HttpServletRequest req) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		req.setAttribute("departList", departList);
		if (StringUtil.isNotEmpty(depart.getId())) {
			depart = systemService.getEntity(TSDepart.class, depart.getId());
			req.setAttribute("depart", depart);

			if (StringUtil.isNotEmpty(depart.getRegionId())) {
				SystemRegionEntity region = systemService.getEntity(SystemRegionEntity.class, depart.getRegionId());
				req.setAttribute("region", region);
			}
			if (depart.getId().equals("8a8ab0b246dc81120146dc8180a20016")) {
				req.setAttribute("hiddenSelectRegionBtn", "1");
			}
		}
		return new ModelAndView("system/depart/depart2");
	}

	/**
	 * 父级权限列表
	 * 
	 * @param request
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "setPFunction")
	@ResponseBody
	public List<ComboTree> setPFunction(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		if (null != request.getParameter("selfId")) {
			cq.notEq("id", request.getParameter("selfId"));
		}
		if (comboTree.getId() != null) {
			cq.eq("TSPDepart.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSPDepart");
		}
		cq.add();
		List<TSDepart> departsList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "departname", "TSDeparts");
		comboTrees = systemService.ComboTree(departsList, comboTreeModel, null, true);
		return comboTrees;

	}

	/**
	 * 部门列表，树形展示
	 * 
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "departgrid")
	@ResponseBody
	public Object departgrid(TSDepart tSDepart, HttpServletRequest request, HttpServletResponse response,
			TreeGrid treegrid) throws Exception{
		List<TSDepart> departList = new ArrayList<TSDepart>();
		String regionId = request.getParameter("regionId");
		String name = request.getParameter("name");
		if (treegrid.getId() != null) {
			departList = departService.getDepartlistByParentId(treegrid.getId());
		} else {
			if (StringUtil.isEmpty(regionId)) {
				HttpSession session = ContextHolderUtils.getSession();
				regionId = (String) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID);
			}
			if (StringUtil.isNotEmpty(name)){
				departList = departService.getDepartListByRegionIdAndName(name,regionId);
			}else{
				//departList = departService.getDepartListByRegionIdAndName(name,regionId);
				departList = departService.getDepartlistByRegionId(regionId);				
			}
		}
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("departname");
		treeGridModel.setParentText("TSPDepart_departname");
		treeGridModel.setParentId("TSPDepart_id");
		treeGridModel.setSrc("description");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("TSDeparts");
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("orgCode", "orgCode");
		fieldMap.put("orgType", "orgType");
		fieldMap.put("mobile", "mobile");
		fieldMap.put("fax", "fax");
		fieldMap.put("address", "address");
		treeGridModel.setFieldMap(fieldMap);
		treeGrids = systemService.treegrid(departList, treeGridModel);

		JSONArray jsonArray = new JSONArray();
		for (TreeGrid treeGrid : treeGrids) {
			jsonArray.add(JSON.parse(treeGrid.toJson()));
		}
		return jsonArray;
	}

	/**
	 * 方法描述: 查看成员列表 作 者： yiming.zhang 日 期： Dec 4, 2013-8:53:39 PM
	 * 
	 * @param request
	 * @param departid
	 * @return 返回类型： ModelAndView
	 */
	@RequestMapping(params = "userList")
	public ModelAndView userList(HttpServletRequest request, String departid) {

		// 组织机构管理-查看成员页面，是否显示 用户 新增 按钮 【1 是 0 否 默认 为0】
		String addValue = bssysDeployService.getValue("001001003", null);
		if (StringUtil.isEmpty(addValue)) {
			addValue = "0";
		}

		request.setAttribute("isShowAdd", addValue);

		// 组织机构管理-查看成员页面，是否显示 用户 编辑 按钮 【1 是 0 否 默认 为0】
		String updateValue = bssysDeployService.getValue("001001004", null);
		if (StringUtil.isEmpty(updateValue)) {
			updateValue = "0";
		}
		request.setAttribute("isShowUpdate", updateValue);
		// 组织机构与用户关系 0：一对多；1：多对多，默认一对多
		String relationValue = bssysDeployService.getValue("001001001", null);
		if (StringUtil.isEmpty(relationValue)) {
			relationValue = "0";
		}
		request.setAttribute("isShowAddOrDelUser", relationValue);
		request.setAttribute("departid", departid);
		return new ModelAndView("system/depart/departUserList");
	}

	/**
	 * 方法描述: 成员列表dataGrid 作 者： yiming.zhang 日 期： Dec 4, 2013-10:40:17 PM
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 *            返回类型： void
	 */
	@RequestMapping(params = "userDatagrid")
	public void userDatagrid(TSUser user, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		// 查询条件组装器
		user.setDepartid(null);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
		String departid = oConvertUtils.getString(request.getParameter("departid"));
		if (!StringUtil.isEmpty(departid)) {
			// update-start--Author:zhangguoming Date:20140825
			// for：用户表字段变更后的查询字段修改
			DetachedCriteria dc = cq.getDetachedCriteria();
			DetachedCriteria dcDepart = dc.createCriteria("userOrgList");
			dcDepart.add(Restrictions.eq("tsDepart.id", departid));
			// 这种方式也是可以的
			// DetachedCriteria dcDepart = dc.createAlias("userOrgList",
			// "userOrg");
			// dcDepart.add(Restrictions.eq("userOrg.tsDepart.id", departid));
			// update-end--Author:zhangguoming Date:20140825 for：用户表字段变更后的查询字段修改
		}
		Short[] userstate = new Short[] { Globals.User_Normal, Globals.User_ADMIN };
		cq.in("status", userstate);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	// ----

	// update-start--Author:zhangguoming Date:20140826 for：获取机构树；
	/**
	 * 获取机构树-combotree
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getOrgTree")
	@ResponseBody
	public List<ComboTree> getOrgTree(HttpServletRequest request) {
		// findHql不能处理is null条件
		// List<TSDepart> departsList = systemService.findHql("from TSPDepart
		// where TSPDepart.id is null");
		List<TSDepart> departsList = systemService.findByQueryString("from TSDepart where TSPDepart.id is null");
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "departname", "TSDeparts");
		comboTrees = systemService.ComboTree(departsList, comboTreeModel, null, true);
		return comboTrees;
	}
	// update-end--Author:zhangguoming Date:20140826 for：获取机构树；

	// update-start--Author:zhangguoming Date:20140826 for：添加已有用户到组织机构；
	/**
	 * 添加 用户到组织机构 的页面 跳转
	 * 
	 * @param req
	 *            request
	 * @return 处理结果信息
	 */
	@RequestMapping(params = "goAddUserToOrg")
	public ModelAndView goAddUserToOrg(HttpServletRequest req) {
		req.setAttribute("orgId", req.getParameter("orgId"));
		return new ModelAndView("system/depart/noCurDepartUserList");
	}

	/**
	 * 获取 除当前 组织之外的用户信息列表
	 * 
	 * @param request
	 *            request
	 * @return 处理结果信息
	 */
	@RequestMapping(params = "addUserToOrgList")
	public void addUserToOrgList(TSUser user, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		String orgId = request.getParameter("orgId");

		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);

		// 获取 当前组织机构的用户信息
		CriteriaQuery subCq = new CriteriaQuery(TSUserOrg.class);
		subCq.setProjection(Property.forName("tsUser.id"));
		subCq.eq("tsDepart.id", orgId);
		subCq.add();

		cq.add(Property.forName("id").notIn(subCq.getDetachedCriteria()));
		cq.add();

		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 添加 用户到组织机构
	 * 
	 * @param req
	 *            request
	 * @return 处理结果信息
	 */
	@RequestMapping(params = "doAddUserToOrg")
	@ResponseBody
	public AjaxJson doAddUserToOrg(HttpServletRequest req) {
		AjaxJson j = new AjaxJson();
		TSDepart depart = systemService.getEntity(TSDepart.class, req.getParameter("orgId"));
		saveOrgUserList(req, depart);
		message = MutiLangUtil.paramAddSuccess("common.user");
		// systemService.addLog(message, Globals.Log_Type_UPDATE,
		// Globals.Log_Leavel_INFO);
		j.setMsg(message);

		return j;
	}

	/**
	 * 保存 组织机构-用户 关系信息
	 * 
	 * @param request
	 *            request
	 * @param depart
	 *            depart
	 */
	private void saveOrgUserList(HttpServletRequest request, TSDepart depart) {
		String orgIds = oConvertUtils.getString(request.getParameter("userIds"));

		List<TSUserOrg> userOrgList = new ArrayList<TSUserOrg>();
		List<String> userIdList = extractIdListByComma(orgIds);
		for (String userId : userIdList) {
			TSUser user = new TSUser();
			user.setId(userId);

			TSUserOrg userOrg = new TSUserOrg();
			userOrg.setTsUser(user);
			userOrg.setTsDepart(depart);

			userOrgList.add(userOrg);
		}
		if (!userOrgList.isEmpty()) {
			systemService.batchSave(userOrgList);
		}
	}

	/**
	 * 用户选择机构列表跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "departSelect")
	public String departSelect(HttpServletRequest request) {
		// 组织机构与用户关系， 0：一对多；1：多对多，默认一对多
		String ids = request.getParameter("ids");
		request.setAttribute("ids", ids);
		String orgUserRelation = bssysDeployService.getValue("001001001", null);
		if (orgUserRelation == null || orgUserRelation.equals("")) {
			orgUserRelation = "0";
		}
		if ("0".equals(orgUserRelation)) {// 一对多
			return "system/depart/departSelectRadio";
		}
		return "system/depart/departSelect";
	}

	/**
	 * 角色显示列表
	 *
	 * @param response
	 *            response
	 * @param dataGrid
	 *            dataGrid
	 */
	@RequestMapping(params = "departSelectDataGrid")
	public void datagridRole(HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	// update-end--Author:zhangguoming Date:20140827 for：用户列表页面
	// 组织机构查询条件：选择组织机构列表 相关操作
	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "departController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TSDepart tsDepart, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tsDepart, request.getParameterMap());
		cq.addOrder("orgCode", SortDirection.asc);
		// 获得需要导出的数据库的信息集合
		List<TSDepart> departs = this.departService.getListByCriteriaQuery(cq, false);
		// 声明一个集合
		List<TSDepartImport> result = new ArrayList<TSDepartImport>();
		if (departs.size() > 0 && departs != null) {
			for (TSDepart d : departs) {

				TSDepartImport depart = new TSDepartImport();
				// 组织机构名称
				depart.setDepartname(d.getDepartname());
				// 机构编码
				depart.setOrgCode(d.getOrgCode());
				// 机构类型
				if (StringUtils.isNotEmpty(d.getOrgType())) {
					TSType tsType = typeService.getTypenameByTypegroupcodeAndTypecode("orgtype", d.getOrgType());
					if (tsType != null) {
						depart.setOrgType(tsType.getTypename());
					}
				}
				// 组织机构描述
				depart.setDescription(d.getDescription());
				// 电话
				depart.setMobile(d.getMobile());
				// 传真
				depart.setFax(d.getFax());
				// 地址
				depart.setAddress(d.getAddress());
				// 区划编码
				if (StringUtils.isNotEmpty(d.getRegionId())) {
					SystemRegionEntity region = systemRegionService.getEntity(SystemRegionEntity.class,
							d.getRegionId());
					if (region != null) {
						depart.setRegionCode(region.getRegionCode());
					}
				}
				// 区划id
				depart.setRegionId(d.getRegionId());
				// 父级组织机构名称
				if (d.getTSPDepart() == null) {
					depart.setParentdepartname(null);
				} else {
					depart.setParentdepartname(d.getTSPDepart().getDepartname());
				}
				// 将信息添加到导入到excel的集合中
				result.add(depart);
			}
		}

		modelMap.put(NormalExcelConstants.FILE_NAME, "组织机构表");
		modelMap.put(NormalExcelConstants.CLASS, TSDepartImport.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("组织机构表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, result);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TSDepart tsDepart, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {

		TSDepartImport depart = new TSDepartImport();
		List<TSDepartImport> result = new ArrayList<TSDepartImport>();
		// 组织机构名称
		depart.setDepartname("必填,且机构名称唯一");
		// 机构编码
		depart.setOrgCode("必填");
		// 机构类型
		depart.setOrgType("必填(参考字典条目名称)");
		// 组织机构描述
		depart.setDescription("选填");
		// 电话
		depart.setMobile("选填，格式应为11位手机号码");
		// 传真
		depart.setFax("选填，格式应为3-4位区号紧跟7-8位号码");
		// 地址
		depart.setAddress("选填，格式应为字符或数字不含有特殊字符");
		// 区划编码
		depart.setRegionCode("必填");
		// 区划id
		depart.setRegionId("不用填写该字段");
		// 父级区划code
		depart.setParentdepartname("必填");

		result.add(depart);

		modelMap.put(NormalExcelConstants.FILE_NAME, "组织机构表");
		modelMap.put(NormalExcelConstants.CLASS, TSDepartImport.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("组织机构列表", "整理人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, result);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导入功能
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
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
				List<TSDepartImport> tsDeparts = ExcelImportUtil.importExcel(file.getInputStream(),
						TSDepartImport.class, params);
				//String str = "";
				StringBuilder sb = new StringBuilder();
				SystemRegionEntity region = null;
				List<String> deptNameList = new ArrayList<>();
				int line = params.getHeadRows() + params.getTitleRows();
				for (int i = 0; i < tsDeparts.size(); i++) {
					TSDepartImport d = tsDeparts.get(i);//d指传入的每一行数据
					// 验证组织机构名称
					String departname = StringUtil.replaceBlank(d.getDepartname());
					if (departname == null || "".equals(departname)) {
						//str = str + "第" + (i + 1 + line) + "行组织机构名称为空<br>";
						sb.append("第").append((i + 1 + line)).append("行组织机构名称为空<br>");
						//continue;
					}
					
					// 验证父级组织机构名称
					String parentDepartName = StringUtil.replaceBlank(d.getParentdepartname());
					if (parentDepartName == null || "".equals(parentDepartName.trim())) {
						//str = str + "第" + (i + 1 + line) + "行父级组织机构名称为空<br>";
						sb.append("第").append((i + 1 + line)).append("行父级组织机构名称为空<br>");
						//continue;
					}
					if (!deptNameList.contains(parentDepartName)) {// 若父级机构名称不在列表中，则从数据库中查询
						TSDepart parentdepart = departService.getTSDepartBydepartname(parentDepartName);
						if (parentdepart == null) {
							//str = str + "第" + (i + 1 + line) + "行父级组织机构名称不存在，当前值：" + parentDepartName + "<br>";
							sb.append("第").append((i + 1 + line)).append("行父级组织机构名称不存在，当前值：").append(parentDepartName).append("<br>");
							//continue;
						}else{
							deptNameList.add(parentdepart.getDepartname());// 将有效的组织机构名称放到机构名称列表中，便于后续循环使用
						}
					}
					deptNameList.add(d.getDepartname());
					// 验证区划编码是否存在
					String regionCode = StringUtil.replaceBlank(proceString(d.getRegionCode()));
					// 判断区划编码是否有值
					if (StringUtil.isNotEmpty(regionCode)) {
						// 判断输入的区划编码是否正确
						region = systemRegionService.getSystemRegionEntityByRegionCode(regionCode);
						if (region == null) {
							//str = str + "第" + (i + 1 + line) + "输入的区划编码不存在<br>";
							sb.append("第").append((i + 1 + line)).append("行输入的区划编码不存在<br>");
							//continue;
						}else{
							d.setRegionId(region.getId());
						}
					} else {
						//str = str + "第" + (i + 1 + line) + "区划编码不能为空<br>";
						sb.append("第").append((i + 1 + line)).append("区划编码不能为空<br>");
						//continue;
					}
					// 验证手机号
					if (StringUtil.isNotEmpty(d.getMobile())) {

						if (!checkMobileNO(d.getMobile())) {
							//str = str + "第" + (i + 1 + line) + "行手机号码格式不正确<br>";
							sb.append("第").append((i + 1 + line)).append("行手机号码格式不正确<br>");
							//continue;
						}
					}
					// 验证地址by zhengxi
					if (StringUtil.isNotEmpty(d.getAddress())) {
						if (!checkaddr(d.getAddress())) {
							//str = str + "第" + (i + 1 + line) + "行地址格式不正确<br>";
							sb.append("第").append((i + 1 + line)).append("行地址格式不正确<br>");
							//continue;
						}
					}
					// 验证传真 by zhengxi
					if (StringUtil.isNotEmpty(d.getFax())) {
						if (!checkfax(d.getFax())) {
							//str = str + "第" + (i + 1 + line) + "行传真号码格式不正确<br>";
							sb.append("第").append((i + 1 + line)).append("行传真号码格式不正确<br>");
							//continue;
						}
					}
					// 验证机构类型字典code值填写是否正确
					String orgTypename = d.getOrgType();
					String orgTypecode = "";
					//通过excel填写的机构类型名称 获取字典表对应的机构code
					if (orgTypename == null || "".equals(orgTypename.trim())) {
						//str = str + "第" + (i + 1 + line) + "行组织机构类型名称为空<br>";
						sb.append("第").append((i + 1 + line)).append("行组织机构类型名称为空<br>");
						//continue;
					} else {
						TSType tsType = typeService.getTypecodeByTypegroupcodeAndTypename("orgtype", orgTypename);
						if (tsType != null) {
							orgTypecode = tsType.getTypecode();
						}
						// 当前用户区划id
						String curentUserRegionId = (String) request.getSession()
								.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID);
						List<TSType> typelist = typeService.getItemsByTypegroupcodeAndRegionId("orgtype",
								curentUserRegionId);
						List<String> typeCodeList = new ArrayList<String>();
						for (TSType t : typelist) {
							typeCodeList.add(t.getTypecode());
						}
						String s = proceString(orgTypecode);
						if (!typeCodeList.contains(s)) {
							//str = str + "第" + (i + 1 + line) + "行机构类型字典类型名称错误,当前值：" + orgTypename + "<br>";
							sb.append("第").append((i + 1 + line)).append("行机构类型字典类型名称错误,当前值：").append(orgTypename).append("<br>");
							//continue;
						}else{
							d.setOrgType(orgTypecode);
						}
					}
				}
				if (!sb.toString().equals("")) {//sb是错误记录
					String requestUrl = request.getScheme() // 当前链接使用的协议
							+ "://" + request.getServerName()// 服务器地址
							+ ":" + request.getServerPort() // 端口号
							+ request.getContextPath() + "/"; // 应用名称

					TSUser user = ResourceUtil.getSessionUserName();
					String p = "temp/" + user.getUserName() + Calendar.getInstance().getTimeInMillis() + ".xls";
					String realPath = request.getSession().getServletContext().getRealPath("/") + p;
					String downloadPath = requestUrl + p;
					try {
						File f = exportExcel(realPath, sb.toString(), response);
						if (f == null) {
							j.setMsg("生成文件出错");
							return j;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					j.setMsg("文件数据存在错误!<br><a href=" + downloadPath + " >点击此处下载详细信息</a>");
					return j;
				}
				// 进行 保存
				departService.saveTSDepartList(tsDeparts);
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));

			} finally {
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
	 * 处理字符串
	 * 
	 * @param src
	 * @return
	 */
	private String proceString(String src) {

		if (src != null && src.length() > 0) {
			if (StringUtil.isFloatNumeric(src)) {
				String[] s = src.split("[.]");
				return s[0];
			}
		}
		return src;
	}

	/**
	 * 验证手机号
	 * 
	 * @param mobile
	 * @return
	 */
	private boolean checkMobileNO(String mobile) {

		String regExp = "^[1]([3][0-9]{1}|[5][0-9]{1}|[8][0-9]{1})[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobile);
		return m.find();// boolean
	}

	/**
	 * 验证地址
	 * 
	 * @param addr
	 * @return by zhengxi
	 */
	private boolean checkaddr(String addr) {
		// 0~50位字符
		String regExp = "^[\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w\\.\\s]{0,50}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(addr);
		return m.find();// boolean
	}

	/**
	 * 验证传真号
	 * 
	 * @param fax
	 * @return by zhengxi
	 */
	private boolean checkfax(String fax) {

		String regExp = "^[0-9]{3,4}[0-9]{7,8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(fax);
		return m.find();// boolean
	}

	/**
	 * 用户管理菜单页面：展示组织机构树（只展示当前登陆用户相关的组织机构树，管理员除外）
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "getDepartInfo")
	@ResponseBody
	public AjaxJson getDepartInfo(HttpServletRequest request, HttpServletResponse response) {

		AjaxJson j = new AjaxJson();
		String parentid = request.getParameter("parentid");
		String ids = request.getParameter("ids");
		ids = "," + ids + ",";

		List<TSDepart> tSDeparts = new ArrayList<TSDepart>();

		StringBuffer hql = new StringBuffer(" from TSDepart t ");
		if (StringUtils.isNotBlank(parentid)) {
			hql.append(" where TSPDepart.id = ?");
			tSDeparts = this.systemService.findHql(hql.toString(), parentid);
		} else {
			/* 只展示当前登陆用户相关的组织机构树，管理员除外 */
			HttpSession session = ContextHolderUtils.getSession();
			TSUser currentUser = (TSUser) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			if (!currentUser.getStatus().equals(Globals.User_ADMIN)) {// 非管理员
				TSDepart dept = (TSDepart) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART);
				hql.append(" where id = ?");
				tSDeparts = this.systemService.findHql(hql.toString(), dept.getId());
			} else {
				hql.append(" where TSPDepart is null");
				tSDeparts = this.systemService.findHql(hql.toString());
			}

		}
		List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();
		if (tSDeparts.size() > 0) {
			Map<String, Object> map = null;
			String sql = null;
			Object[] params = null;
			for (TSDepart depart : tSDeparts) {
				map = new HashMap<String, Object>();
				map.put("id", depart.getId());
				map.put("name", depart.getDepartname());
				if (ids.indexOf("," + depart.getId() + ",") != -1) {
					map.put("checked", true);
				}
				if (StringUtils.isNotBlank(parentid)) {
					map.put("pId", parentid);
				} else {
					map.put("pId", "1");
				}
				// 根据id判断是否有子节点
				sql = "select count(1) from t_s_depart t where t.parentdepartid = ?";
				params = new Object[] { depart.getId() };
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if (count > 0) {
					map.put("isParent", true);
				}
				dateList.add(map);
			}
		}
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(dateList);
		j.setMsg(jsonArray.toString());
		return j;
	}

	// update--end--by:jg_renjie--at:20160318 for:#942
	// 【组件封装】组织机构弹出模式，目前是列表，得改造成树方式
	@RequestMapping(params = "orgtree")
	@ResponseBody
	public List<ComboTree> orgtree() {
		// 从数据库中查表
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		// cq.isNull("parent");
		// 根据用户加载行政区划
		TSBaseUser user = ResourceUtil.getSessionUserName();
		String orgid = user.getCurrentDepart().getId();
		cq.eq("id", orgid);
		cq.add();
		List<TSDepart> orgList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < orgList.size(); i++) {
			comboTrees.add(ConvertToTree1(orgList.get(i)));
		}
		return comboTrees;

	}

	private ComboTree ConvertToTree1(TSDepart entity) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getDepartname());
		if (entity.getTSDeparts() != null && entity.getTSDeparts().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getTSDeparts().size(); i++) {
				comboTrees.add(ConvertToTree1(entity.getTSDeparts().get(i)));
			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}

	private File exportExcel(String path, String str, HttpServletResponse response) throws Exception {
		FileOutputStream out = null;
		// 创建文件
		File file = new File(path);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()) {
			fileParent.mkdirs();
		}
		file.createNewFile();

		out = new FileOutputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		String[] arr = str.split("<br>");
		CellStyle style = getStyle(wb);
		// 设置列的宽度
		sheet.setColumnWidth(0, 60 * 256);
		for (int i = 0; i < arr.length; i++) {
			HSSFRow row = sheet.createRow(i);
			HSSFCell cell = row.createCell(0);
			cell.setCellValue(arr[i]);
			cell.setCellStyle(style);
			// 设置列的高度
			row.setHeight((short) (15.625 * 30));
		}
		wb.write(out);
		out.close();

		return file;
	}

	// 设置单元格样式
	public static CellStyle getStyle(HSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		// 边框
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		// 居中
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// 设置字体
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font.setFontHeightInPoints((short) 11);
		style.setFont(font);// 选择需要用到的字体格式
		style.setWrapText(true);
		return style;
	}

}
