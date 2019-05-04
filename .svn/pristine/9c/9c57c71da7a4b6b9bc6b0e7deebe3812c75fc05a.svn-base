package com.jeecg.system_region.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.SystemRegionContorl;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSON;
import com.jeecg.bssys_deploy.service.BssysDeployServiceI;
import com.jeecg.system_region.entity.SystemRegionEntity;
import com.jeecg.system_region.entity.SystemRegionImportEntity;
import com.jeecg.system_region.service.SystemRegionServiceI;

/**
 * @Title: Controller
 * @Description: 行政区划
 * @author onlineGenerator
 * @date 2017-03-28 14:11:45
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/systemRegionController")
public class SystemRegionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SystemRegionController.class);

	@Autowired
	private SystemRegionServiceI systemRegionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private TypeServiceI typeService;

	@Autowired
	private Validator validator;
	@Autowired
	private BssysDeployServiceI bssysDeployService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@RequestMapping(params = "SingleTree")
	public String SingleTree() {
		return "com/jeecg/system_region/regionSingleTree";
	}

	@RequestMapping(params = "SingleTree1")
	public String SingleTree1(String type, String regcode, HttpServletRequest request) {
		request.setAttribute("type", type);
		request.setAttribute("regcode", regcode);

		return "com/jeecg/system_region/regionSingleTree";
	}

	@RequestMapping(params = "SingleTree2")
	public String SingleTree2() {
		return "com/jeecg/system_region/regionSingleTree2";
	}

	@RequestMapping(params = "MultipleTree")
	public String MultipleTree() {
		return "com/jeecg/system_region/regionMultipleTree";
	}

	@RequestMapping(params = "getSystemRegionLevel")
	public void getSystemRegionLevel(String plevel, HttpServletResponse response) {
		TSTypegroup group = systemService.getTypeGroupByCode("101010");
		List<TSType> types = group.getTSTypes();
		Collections.sort(types, new Comparator<TSType>() {
			@Override
			public int compare(TSType o1, TSType o2) {
				return o1.getTypecode().compareToIgnoreCase(o2.getTypecode());
			}

		});
		List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
		for (TSType type : types) {
			if (StringUtil.isNotEmpty(plevel) && plevel.compareToIgnoreCase(type.getTypecode()) >= 0) {
				continue;
			}
			Map<String, String> data = new HashMap<String, String>();
			data.put("typecode", type.getTypecode());
			data.put("typename", type.getTypename());
			datas.add(data);
		}
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw = response.getWriter();
			pw.write(JSON.toJSONString(datas.toArray()));
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(params = "tree")
	@ResponseBody
	public List<ComboTree> tree() {
		// 从数据库中查表
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);
		cq.isNull("parent");
		cq.add();
		List<SystemRegionEntity> regionList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < regionList.size(); i++) {
			comboTrees.add(ConvertToTree(regionList.get(i)));
		}
		return comboTrees;

	}

	private ComboTree ConvertToTree(SystemRegionEntity entity) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getRegionName());
		if (entity.getList() != null && entity.getList().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getList().size(); i++) {
				comboTrees.add(ConvertToTree(entity.getList().get(i)));
			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}

	/**
	 * 条目表 关联区划 的 树 数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "tree1")
	@ResponseBody
	public List<ComboTree> tree1(HttpServletRequest request) {

		String orgIds = request.getParameter("orgIds");
		String[] orgId = orgIds.split(",");
		// 从数据库中查表
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);
		cq.isNull("parent");
		cq.add();
		List<SystemRegionEntity> regionList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < regionList.size(); i++) {
			comboTrees.add(ConvertToTree1((regionList.get(i)), orgId));
		}
		return comboTrees;

	}

	private ComboTree ConvertToTree1(SystemRegionEntity entity, String[] orgId) {

		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		// 循环给已选中的区划设置Checked
		for (int i = 0; i < orgId.length; i++) {
			if (tree.getId().equals(orgId[i])) {
				tree.setChecked(true);
			}
		}

		tree.setText(entity.getRegionName());
		if (entity.getList() != null && entity.getList().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getList().size(); i++) {
				comboTrees.add(ConvertToTree1((entity.getList().get(i)), orgId));

			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}

	@RequestMapping(params = "deptTree")
	@ResponseBody
	public List<ComboTree> deptTree(HttpServletRequest request) {

		String checkRegionId = request.getParameter("checkRegionId");

		// 从数据库中查表
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);
		cq.isNull("parent");
		cq.add();
		List<SystemRegionEntity> regionList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < regionList.size(); i++) {
			comboTrees.add(ConvertToDeptTree((regionList.get(i)), checkRegionId));
		}
		return comboTrees;

	}

	private ComboTree ConvertToDeptTree(SystemRegionEntity entity, String checkRegionId) {

		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());

		if (tree.getId().equals(checkRegionId)) {
			tree.setChecked(true);
		}

		tree.setText(entity.getRegionName());
		if (entity.getList() != null && entity.getList().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getList().size(); i++) {
				comboTrees.add(ConvertToDeptTree((entity.getList().get(i)), checkRegionId));

			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}

	/**
	 * 系统区划表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/system_region/systemRegionList");
	}

	/**
	 * 行政区划树状表格页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "systemRegion")
	public ModelAndView systemRegion(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/system_region/systemRegionList1");
	}

	/**
	 * 类型表生成树 的 数据
	 * 
	 * @param systemRegion
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "systemRegionGrid")
	@ResponseBody
	public Object systemRegionGrid(SystemRegionEntity systemRegion, HttpServletRequest request,
			HttpServletResponse response, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);
		boolean noSearch = true;
		String searchparams = request.getParameter("searchparams");
		String searchparam = request.getParameter("searchparam");
		if (StringUtil.isNotEmpty(searchparams) && StringUtil.isNotEmpty(searchparam)) {
			searchparam = searchparam.replaceAll("%", "");
			if (StringUtil.isNotEmpty(searchparam)) {
				cq.like(searchparams.replaceAll("\"", "").trim(), searchparam.replaceAll("\"", "").trim() + "%");
				noSearch = false;
			}
		}
		if (noSearch) {
			cq.isNull("parent");
		}
		cq.addOrder("regionCode", SortDirection.asc);
		cq.add();
		List<SystemRegionEntity> regionList = systemService.getListByCriteriaQuery(cq, false);
		return parseTrees(regionList);
	}

	private List<Map<String, Object>> parseTrees(List<SystemRegionEntity> list) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (SystemRegionEntity entity : list) {
			Map<String, Object> tree = new HashMap<String, Object>();
			tree.put("id", entity.getId());
			tree.put("text", entity.getRegionName());
			tree.put("code", entity.getRegionCode());
			tree.put("regionShortName", entity.getRegionShortName());
			TSTypegroup group = systemService.getTypeGroupByCode("101010");
			for (TSType type : group.getTSTypes()) {
				if (type.getTypecode().equals(entity.getRegionLevel())) {
					tree.put("regionLevel", type.getTypename());
				}
			}
			tree.put("areaCode", entity.getAreaCode());
			tree.put("regionPostcode", entity.getRegionPostcode());
			if (entity.getList() != null && entity.getList().size() > 0) {
				List<SystemRegionEntity> children = entity.getList();
				Collections.sort(children, new Comparator<SystemRegionEntity>() {
					@Override
					public int compare(SystemRegionEntity o1, SystemRegionEntity o2) {
						return o1.getRegionCode().compareToIgnoreCase(o2.getRegionCode());
					}
				});
				tree.put("children", parseTrees(children));
			}
			trees.add(tree);
		}
		return trees;
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
	public void datagrid(SystemRegionEntity systemRegion, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, systemRegion,
				request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.systemRegionService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**********************************************************************************************/

	/**
	 * 区划选择列表跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "deptSelectRegion")
	public String deptSelectRegion(String parentDepartId, String checkRegionId, HttpServletRequest request) {
		if (null != parentDepartId && !parentDepartId.equals("")) {
			request.setAttribute("parentDepartId", parentDepartId);
		}
		request.setAttribute("checkRegionId", checkRegionId);
		return "com/jeecg/system_region/deptSelectRegion";
	}
	
	/**
	 * 区划配置功能选择列表跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "deptSelectRegion1")
	public String deptSelectRegion1(String parentDepartId, String checkRegionId, HttpServletRequest request) {		
		request.setAttribute("checkRegionId", checkRegionId);
		return "com/jeecg/system_region/deptSelectRegion1";
	}

	/**
	 * 区划 选择跳转页面
	 *
	 * @return
	 */
	@RequestMapping(params = "regionSelect")
	public String regionSelect(String orgIds, HttpServletRequest request) {
		request.setAttribute("orgIds", orgIds);
		return "com/jeecg/system_region/regionSelect";
	}

	/**
	 * 验证上级区划id数据是否正确
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "deptSelectRegionGrid")
	@ResponseBody
	public AjaxJson deptSelectRegionGrid(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String parentDepartId = request.getParameter("parentDepartId");
		if (parentDepartId == null || parentDepartId.equals("")) {
			j.setSuccess(false);
			j.setMsg("参数parentDepartId为空");
			return j;
		}
		TSDepart dept = systemService.getEntity(TSDepart.class, parentDepartId);
		if (dept == null) {
			j.setSuccess(false);
			j.setMsg("未找到上级组织机构：parentDepartId：" + parentDepartId);
			return j;
		}

		if (StringUtil.isEmpty(dept.getRegionId())) {
			j.setMsg("请先完善【" + dept.getDepartname() + "】的区划信息");
			j.setSuccess(false);
			return j;
		}

		return j;
	}
	
	/**
	 * 验证上级区划id数据是否正确
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "deptSelectRegionGrid1")
	@ResponseBody
	public AjaxJson deptSelectRegionGrid1(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String parentRegionId = request.getParameter("parentRegionId");
		if (parentRegionId == null || parentRegionId.equals("")) {
			j.setSuccess(false);
			j.setMsg("参数parentRegionId为空");
			return j;
		}
		return j;
	}

	/**
	 * 组织机构 选择区划树
	 * 
	 * @param systemRegion
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "deptSelectRegionTree")
	@ResponseBody
	public Object deptSelectRegionTree(SystemRegionEntity systemRegion, HttpServletRequest request,
			HttpServletResponse response, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);
		// 父组织的id
		String parentDepartId = request.getParameter("parentDepartId");
		// 回显时选中的节点
		String checkRegionId = request.getParameter("checkRegionId");
		// 区划树点击节点时传过来的节点id
		String nodeId = request.getParameter("id");
		TSDepart dept = null;
		if (StringUtil.isEmpty(nodeId)) {// node节点为 空 说明应该是 第一次加载
			if (StringUtil.isNotEmpty(parentDepartId)) {
				dept = systemService.getEntity(TSDepart.class, parentDepartId);
				if (StringUtil.isNotEmpty(dept.getRegionId())) {
					cq.eq("id", dept.getRegionId());
				}
			} else {
				cq.isNull("parent.id");
			}

		} else {// 按 node节点id 查询

			cq.eq("parent.id", nodeId);
		}
		cq.add();
		List<SystemRegionEntity> regionList = systemService.getListByCriteriaQuery(cq, false);
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (SystemRegionEntity entity : regionList) {
			Map<String, Object> tree = new HashMap<String, Object>();
			tree.put("id", entity.getId());
			tree.put("text", entity.getRegionName());

			// 默认选中
			if (entity.getId().equals(checkRegionId)) {
				tree.put("checked", "true");
			}
			if (entity.getIsLeaf().equals("0")) {
				tree.put("state", "closed");
			}
			trees.add(tree);
		}
		return trees;
	}
	
	/**
	 * 区划配置功能组织机构 选择区划树
	 * 
	 * @param systemRegion
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "deptSelectRegionTree1")
	@ResponseBody
	public Object deptSelectRegionTree1(SystemRegionEntity systemRegion, HttpServletRequest request,
			HttpServletResponse response, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);		
		// 回显时选中的节点
		String checkRegionId = request.getParameter("checkRegionId");
		// 区划树点击节点时传过来的节点id
		String nodeId = request.getParameter("id");
		TSDepart dept = null;
		if (StringUtil.isEmpty(nodeId)) {// node节点为 空 说明应该是 第一次加载
			if (StringUtil.isNotEmpty(checkRegionId)) {
				cq.eq("id",checkRegionId);
			} else {
				cq.isNull("parent.id");
			}

		} else {// 按 node节点id 查询

			cq.eq("parent.id", nodeId);
		}
		cq.add();
		List<SystemRegionEntity> regionList = systemService.getListByCriteriaQuery(cq, false);
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (SystemRegionEntity entity : regionList) {
			Map<String, Object> tree = new HashMap<String, Object>();
			tree.put("id", entity.getId());
			tree.put("text", entity.getRegionName());

			// 默认选中
			if (entity.getId().equals(checkRegionId)) {
				tree.put("checked", "true");
			}
			if (entity.getIsLeaf().equals("0")) {
				tree.put("state", "closed");
			}
			trees.add(tree);
		}
		return trees;
	}

	/**
	 * 实现异步加载区划树
	 * 
	 * @param systemRegion
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "asynDatagrid")
	@ResponseBody
	public Object asynDatagrid(SystemRegionEntity systemRegion, HttpServletRequest request,
			HttpServletResponse response, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);
		boolean noSearch = true;
		List<SystemRegionEntity> regionList = null;
		String nodeId = request.getParameter("id");
		String searchparams = request.getParameter("searchparams");
		String searchparam = request.getParameter("searchparam");
		if (StringUtil.isNotEmpty(searchparams) && StringUtil.isNotEmpty(searchparam)) {
			searchparam = searchparam.replaceAll("%", "");
			if (StringUtil.isNotEmpty(searchparam)) {
				SystemRegionContorl entity = bssysDeployService.querySystemRegionControl();
				if(entity==null){
					cq.like(searchparams.replaceAll("\"", "").trim(), searchparam.replaceAll("\"", "").trim() + "%");
				}else{
					String regionId = entity.getRootRegionId();
					String level = entity.getLevelCode();//行政区划控制的显示级别
					SystemRegionEntity region = systemRegionService.getEntity(SystemRegionEntity.class, regionId);
					String regionCode = region.getRegionCode();
					String regionLevel = region.getRegionLevel()==null?"":region.getRegionLevel();
					switch (regionLevel) {
					case "":
						regionCode = "000000000000";
						break;
					case "sheng":
						regionCode = regionCode.substring(0, 2);
						break;
					case "shi":
						regionCode = regionCode.substring(0, 4);
						break;
					case "xian":
						regionCode = regionCode.substring(0, 6);
						break;
					case "zhen":
						regionCode = regionCode.substring(0, 9);
						break;
					default:
						break;
					}
					;
					Object[] obj={};
					switch(level){
						case "sheng":
							obj = new Object[]{"sheng"};
							break;
						case "shi":
							obj = new Object[]{"sheng","shi"};
							break;
						case "xian":
							obj = new Object[]{"sheng","shi","xian"};
							break;
						case "zhen":
							obj = new Object[]{"sheng","shi","xian","zhen"};
							break;	
						default:
							break;
					};
					if(!"000000000000".equals(regionCode)){
						cq.like("regionCode", regionCode+"%");						
					}
					cq.in("regionLevel", obj);
					cq.like(searchparams.replaceAll("\"", "").trim(), searchparam.replaceAll("\"", "").trim() + "%");
				}			
				noSearch = false;
			}
		}

		if (noSearch) {

			if (nodeId == null || nodeId == "") {
				HttpSession session = ContextHolderUtils.getSession();
				TSUser user = ResourceUtil.getSessionUserName();
				if (Globals.User_ADMIN == user.getStatus()) {// 管理员显示全部
					SystemRegionContorl entity = bssysDeployService.querySystemRegionControl();
					if(entity==null){
						cq.isNull("parent");
					}else{
						String regionId = entity.getRootRegionId();						
						String level = entity.getLevelCode();//行政区划控制的显示级别
						Object[] obj={};
						switch(level){
							case "sheng":
								obj = new Object[]{"","sheng"};
								break;
							case "shi":
								obj = new Object[]{"","sheng","shi"};
								break;
							case "xian":
								obj = new Object[]{"","sheng","shi","xian"};
								break;
							case "zhen":
								obj = new Object[]{"","sheng","shi","xian","zhen"};
								break;	
							default:
								break;
						};
						cq.eq("id", regionId);
						cq.in("regionLevel", obj);
					}
					
				} else {
					String regionId = (String) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID);
					cq.eq("id", regionId);
				}
			} else {
				SystemRegionContorl entity = bssysDeployService.querySystemRegionControl();
				if(entity==null){
					
				}else{
					String regionId = entity.getRootRegionId();						
					String level = entity.getLevelCode();//行政区划控制的显示级别
					Object[] obj={};
					switch(level){
						case "sheng":
							obj = new Object[]{"sheng"};
							break;
						case "shi":
							obj = new Object[]{"sheng","shi"};
							break;
						case "xian":
							obj = new Object[]{"sheng","shi","xian"};
							break;
						case "zhen":
							obj = new Object[]{"sheng","shi","xian","zhen"};
							break;	
						default:
							break;
					};
					cq.in("regionLevel", obj);
				}
				cq.eq("parent.id", nodeId);
			}
		}
		cq.addOrder("regionCode", SortDirection.asc);
		cq.add();

		regionList = systemService.getListByCriteriaQuery(cq, false);

		return parseAsynTrees(regionList);
	}

	private List<Map<String, Object>> parseAsynTrees(List<SystemRegionEntity> list) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		TSTypegroup group = systemService.getTypeGroupByCode("101010");
		for (SystemRegionEntity entity : list) {
			Map<String, Object> tree = new HashMap<String, Object>();
			tree.put("id", entity.getId());
			tree.put("text", entity.getRegionName());
			tree.put("code", entity.getRegionCode());
			tree.put("regionShortName", entity.getRegionShortName());
			// TSTypegroup group =
			// systemService.getTypeGroupByCode("area_level");101010
			for (TSType type : group.getTSTypes()) {
				if (type.getTypecode().equals(entity.getRegionLevel())) {
					tree.put("regionLevel", type.getTypename());
				}
			}
			tree.put("areaCode", entity.getAreaCode());
			tree.put("regionPostcode", entity.getRegionPostcode());

			if (StringUtil.isEmpty(entity.getIsLeaf())) {
				entity.setIsLeaf("1");
			}

			if (entity.getIsLeaf().equals("0")) {

				tree.put("state", "closed");

			}
			trees.add(tree);
		}
		return trees;
	}

	/**************************************************************************/

	/**
	 * 删除系统区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(SystemRegionEntity systemRegion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		systemRegion = systemService.getEntity(SystemRegionEntity.class, systemRegion.getId());
		message = "系统区划表删除成功";
		try {

			if (systemRegion.getId().equals("20171120100000")) {

				j.setMsg("系统初始化数据,不可删除");
				j.setSuccess(false);
				return j;

			}
			if (systemRegion.getIsLeaf().equals("0")) {
				// throw new RuntimeException("该行政区划存在下级行政区划，删除失败");
				message = "该行政区划存在下级行政区划，删除失败";
				j.setSuccess(false);

			} else {
				CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
				cq.eq("regionId", systemRegion.getId());
				cq.add();
				List<TSDepart> departs = systemService.getListByCriteriaQuery(cq, false);
				if (departs != null && departs.size() > 0) {
					// throw new RuntimeException("该行政区划存在组织机构，删除失败");
					message = "该行政区划存在组织机构，删除失败";
					j.setSuccess(false);
				} else {
					if (systemRegion.getParent() != null) {

						String pid = systemRegion.getParent().getId();
						SystemRegionEntity parentRegion = systemService.getEntity(SystemRegionEntity.class, pid);
						List<SystemRegionEntity> children = systemRegionService.getChildren(pid);
						if (parentRegion.getIsLeaf().equals("0")) {
							if (children.size() < 2 || children == null) {
								parentRegion.setIsLeaf("1");

								systemRegionService.save(parentRegion);
							}

						}
					}
					systemRegionService.delete(systemRegion);
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "系统区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 软删除系统区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doFalseDel")
	@ResponseBody
	public AjaxJson doFalseDel(SystemRegionEntity systemRegion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		systemRegion = systemService.getEntity(SystemRegionEntity.class, systemRegion.getId());
		message = "系统区划表删除成功";
		try {
			// systemRegion.setIsdeleted("1");
			systemRegionService.doUpdateSql(systemRegion);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除系统区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统区划表删除成功";
		try {
			for (String id : ids.split(",")) {
				SystemRegionEntity systemRegion = systemService.getEntity(SystemRegionEntity.class, id);
				systemRegionService.delete(systemRegion);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量软删除系统区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchFalseDel")
	@ResponseBody
	public AjaxJson doBatchFalseDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统区划表删除成功";
		try {
			for (String id : ids.split(",")) {
				SystemRegionEntity systemRegion = systemService.getEntity(SystemRegionEntity.class, id);
				// systemRegion.setIsdeleted("1");
				systemRegionService.doUpdateSql(systemRegion);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加系统区划表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(SystemRegionEntity systemRegion, HttpServletRequest request) {

		AjaxJson j = new AjaxJson();
		// 设置上级字典
		String pid = request.getParameter("parent.id");
		if (pid.equals("")) {
			systemRegion.setParent(null);
		} else {
			SystemRegionEntity parentRegion = systemService.getEntity(SystemRegionEntity.class, pid);
			if (parentRegion.getIsLeaf().equals("1")) {
				parentRegion.setIsLeaf("0");
				systemRegionService.save(parentRegion);
			}
		}
		// 新增区划时默认为 叶子节点
		systemRegion.setIsLeaf("1");
		message = "系统区划表添加成功";
		try {
			systemRegionService.save(systemRegion);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统区划表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新系统区划表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(SystemRegionEntity systemRegion, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统区划表更新成功";
		if ("" == systemRegion.getParent().getId() || "NULL" == systemRegion.getParent().getId()) {
			systemRegion.setParent(null);
		}
		try {
			SystemRegionEntity t = systemRegionService.get(SystemRegionEntity.class, systemRegion.getId());
			MyBeanUtils.copyBeanNotNull2Bean(systemRegion, t);
			systemRegionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统区划表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 系统区划表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(SystemRegionEntity systemRegion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(systemRegion.getId())) {
			systemRegion = systemRegionService.getEntity(SystemRegionEntity.class, systemRegion.getId());
			req.setAttribute("systemRegion", systemRegion);
			req.setAttribute("plevel", systemRegion.getRegionLevel());
		}
		return new ModelAndView("com/jeecg/system_region/systemRegion-add");
	}

	/**
	 * 系统区划表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(SystemRegionEntity systemRegion, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(systemRegion.getId())) {
			systemRegion = systemRegionService.getEntity(SystemRegionEntity.class, systemRegion.getId());
			req.setAttribute("systemRegion", systemRegion);
		}
		return new ModelAndView("com/jeecg/system_region/systemRegion-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "systemRegionController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(SystemRegionEntity systemRegion, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, systemRegion,
				request.getParameterMap());

		// 获得需要导出的数据库的信息集合
		//List<SystemRegionEntity> systemRegions = this.systemRegionService.getListByCriteriaQuery(cq, false);
		String[] str = new String[2];
		List<SystemRegionEntity> systemRegions = systemRegionService.getRegionList(str);
		System.out.println("size"+systemRegions.size());
		// 声明一个集合
		List<SystemRegionImportEntity> result = new ArrayList<SystemRegionImportEntity>();		
		if (systemRegions.size() > 0 && systemRegions != null) {
			for (SystemRegionEntity r : systemRegions) {				
				SystemRegionImportEntity region = new SystemRegionImportEntity();
				// 区划编码
				region.setRegionCode(r.getRegionCode());
				// 行政区划名称
				region.setRegionName(r.getRegionName());
				// 行政区划级别名称
//				if (StringUtils.isNotEmpty(r.getRegionLevel())) {
//					TSType tsType = typeService.getTypenameByTypegroupcodeAndTypecode("101010", r.getRegionLevel());
//					if (tsType != null) {
//						region.setRegionLevel(tsType.getTypename());
//					}
//				}
				region.setRegionLevel(r.getRegionLevel());
//				// 父级区划code
//				if (r.getParent() == null) {
//					region.setParentRegionCode(null);
//				} else {
//					region.setParentRegionCode(r.getParent().getRegionCode());
//				}
				// 父级区划code
				if ("".equals(r.getParentCode())) {
					region.setParentRegionCode(null);
				} else {
					region.setParentRegionCode(r.getParentCode());
				}
				// 行政区划简称
				region.setRegionShortName(r.getRegionShortName());
				// 行政区划区号
				region.setAreaCode(r.getAreaCode());
				// 行政区划邮政编码
				region.setRegionPostcode(r.getRegionPostcode());
				// 将信息添加到导入到excel的集合中
				result.add(region);				
			}
		}

		modelMap.put(NormalExcelConstants.FILE_NAME, "系统区划表");
		modelMap.put(NormalExcelConstants.CLASS, SystemRegionImportEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("系统区划表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
	public String exportXlsByT(SystemRegionImportEntity systemRegion, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		SystemRegionImportEntity region = new SystemRegionImportEntity();

		List<SystemRegionImportEntity> result = new ArrayList<SystemRegionImportEntity>();
		// 区划编码
		region.setRegionCode("必填");
		// 行政区划名称
		region.setRegionName("必填");
		// 行政区划区号
		region.setAreaCode("选填");
		// 父级区划code
		region.setParentRegionCode("必填");
		// 行政区划简称
		region.setRegionShortName("选填");
		// 行政区划级别
		region.setRegionLevel("必填(参照数据字典中的行政区划级别的条目名称)");
		// 行政区划邮政编码
		region.setRegionPostcode("选填");

		result.add(region);

		modelMap.put(NormalExcelConstants.FILE_NAME, "系统区划表");
		modelMap.put(NormalExcelConstants.CLASS, SystemRegionImportEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("系统区划表列表", "整理人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(false);
			try {
				List<SystemRegionImportEntity> listSystemRegionEntitys = ExcelImportUtil
						.importExcel(file.getInputStream(), SystemRegionImportEntity.class, params);
				//String str = "";
				StringBuilder str = new StringBuilder();
				List<String> regicodeList = new ArrayList<String>();
				int line = params.getHeadRows() + params.getTitleRows();
				for (int i = 0; i < listSystemRegionEntitys.size(); i++) {
					SystemRegionImportEntity r = listSystemRegionEntitys.get(i);
					// 验证行政区划编码
					String regionCode = proceString(r.getRegionCode());
					if (regionCode == null || "".equals(regionCode.trim())) {
						//str = str + "第" + (i + 1 + line) + "行行政区划编码为空<br>";
						str = str.append("第").append(i + 1 + line).append("行行政区划编码为空<br>");
						//continue;
					}
					// 验证父级行政区划编码
					String parentRegionCode = proceString(r.getParentRegionCode());
					if (parentRegionCode == null || "".equals(parentRegionCode.trim())) {
						//str = str + "第" + (i + 1 + line) + "行父级行政区划编码为空<br>";
						str = str.append("第").append(i + 1 + line).append("行父级行政区划编码为空<br>");
						//continue;
					}
					if (!regicodeList.contains(parentRegionCode)) {// 若父级区划编码不在列表中，则从数据库中查询
						SystemRegionEntity parentRegion = systemRegionService
								.getSystemRegionEntityByRegionCode(parentRegionCode);
						if (parentRegion == null) {
							//str = str + "第" + (i + 1 + line) + "行父级行政区划编码不存在，当前值：" + parentRegionCode + "<br>";
							str = str.append("第").append(i + 1 + line).append("行父级行政区划编码不存在，当前值：").append(parentRegionCode).append("<br>");
							//continue;
						}else{
							regicodeList.add(parentRegion.getRegionCode());// 将有效的区划编码放到区划编码列表中，便于后续循环使用
						}
						//regicodeList.add(parentRegion.getRegionCode());// 将有效的区划编码放到区划编码列表中，便于后续循环使用
					}
					// 验证行政区划级别字典条目名称填写是否正确
					String regionLevelName = r.getRegionLevel();
					String regionLevelcode = "";
					//通过excel填写的行政区划级别名称 获取字典表对应的区划级别条目的编码
					if (regionLevelName == null || "".equals(regionLevelName.trim())) {
						//str = str + "第" + (i + 1 + line) + "行行政区划级别为空<br>";
						str = str.append("第").append(i + 1 + line).append("行行政区划级别为空<br>");
						//continue;
					} else {
						TSType tsType = typeService.getTypecodeByTypegroupcodeAndTypename("101010", regionLevelName);
						if (tsType != null) {
							regionLevelcode = tsType.getTypecode();
						}
						// 当前用户区划id
						String curentUserRegionId = (String) request.getSession()
								.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID);
						List<TSType> typelist = typeService.getItemsByTypegroupcodeAndRegionId("101010",
								curentUserRegionId);
						List<String> typeCodeList = new ArrayList<String>();
						for (TSType t : typelist) {
							typeCodeList.add(t.getTypecode());
						}
						String s = proceString(regionLevelcode);
						if (!typeCodeList.contains(s)) {
							//str = str + "第" + (i + 1 + line) + "行行政区划级别字典条目名称错误,当前值：" + regionLevelName + "<br>";
							str = str.append("第").append(i + 1 + line).append("行行政区划级别字典条目名称错误,当前值：").append(regionLevelName).append("<br>");
							continue;
						}
						r.setRegionLevel(regionLevelcode);
					}
					// 记录此条数据的区划编码到list中，便于确认后续数据的父级区划编码是否存在
					regicodeList.add(regionCode);
				}
				if (!str.toString().equals("")) {
					String requestUrl = request.getScheme() // 当前链接使用的协议
							+ "://" + request.getServerName()// 服务器地址
							+ ":" + request.getServerPort() // 端口号
							+ request.getContextPath() + "/"; // 应用名称

					TSUser user = ResourceUtil.getSessionUserName();
					String p = "temp/" + user.getUserName() + Calendar.getInstance().getTimeInMillis() + ".xls";
					String realPath = request.getSession().getServletContext().getRealPath("/") + p;
					String downloadPath = requestUrl + p;
					try {
						File f = exportExcel(realPath, str.toString(), response);
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
				// 进行保存
				systemRegionService.saveSystemRegionList(listSystemRegionEntitys);
				j.setMsg("文件导入成功!");
			} catch (Exception e) {
				j.setMsg("文件导入失败 !");
				logger.error(ExceptionUtil.getExceptionMessage(e));
				return j;
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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<SystemRegionEntity> list() {
		List<SystemRegionEntity> listSystemRegions = systemRegionService.getList(SystemRegionEntity.class);
		return listSystemRegions;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		SystemRegionEntity task = systemRegionService.get(SystemRegionEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody SystemRegionEntity systemRegion, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SystemRegionEntity>> failures = validator.validate(systemRegion);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		systemRegionService.save(systemRegion);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = systemRegion.getId();
		URI uri = uriBuilder.path("/rest/systemRegionController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody SystemRegionEntity systemRegion) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<SystemRegionEntity>> failures = validator.validate(systemRegion);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		systemRegionService.saveOrUpdate(systemRegion);

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		systemRegionService.deleteEntityById(SystemRegionEntity.class, id);
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
