package org.jeecgframework.web.system.controller.core;

import org.apache.commons.lang.StringUtils;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSModelview;
import org.jeecgframework.web.system.pojo.base.TSIcon;
import org.jeecgframework.web.system.pojo.base.TSOperation;
import org.jeecgframework.web.system.service.MvServiceI;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 菜单权限处理类
 * 
 * @author 郑熙
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/ModelViewController")
public class ModelViewController extends BaseController {
	/**
	 * Logger for this class
	 */
	//private static final Logger logger = Logger
	//		.getLogger(ModelViewController.class);
	
	private UserService userService;
	private SystemService systemService;
	private String message = null;
	@Autowired
	private MvServiceI MvService;
	
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
	 * 视图选择列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "function")
	public ModelAndView function() {
		return new ModelAndView("system/modelview/modelviewlist");
	}

	

	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSModelview.class, dataGrid);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	

	/**
	 * 删除记录
	 * 
	 * @param function
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSModelview function, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		function = systemService.getEntity(TSModelview.class, function.getId());
		message = MutiLangUtil.paramDelSuccess("common.mv");
		try{
			systemService.delete(function);
		}catch (Exception e){
			e.printStackTrace();
			message=MutiLangUtil.getMutiLangInstance().getLang("common.mv.del.fail");
		}
		
		
		j.setMsg(message);
		return j;
	}

	

	/**
	 * 递归更新子菜单的FunctionLevel
	 * @param subFunction
	 * @param parent
	 */
	private void updateSubFunction(List<TSModelview> subFunction,TSModelview  parent) {
		if(subFunction.size() ==0){//没有子菜单是结束
              return;
       }else{
    	   for (TSModelview TSModelview : subFunction) {
				TSModelview.setFunctionLevel(Short.valueOf(parent.getFunctionLevel()
						+ 1 + ""));
				systemService.saveOrUpdate(TSModelview);
				List<TSModelview> subFunction1 = systemService.findByProperty(TSModelview.class, "tsModelview.id", TSModelview.getId());
				updateSubFunction(subFunction1,TSModelview);
		   }
       }
	}
	
	
	/**
	 * 权限录入
	 * @param <T>
	 * 
	 * @param function
	 * @return
	 */
	@RequestMapping(params = "saveTsmv")
	@ResponseBody
	public <T> AjaxJson saveTsmv(TSModelview TSModelview, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		TSModelview.setFunctionUrl(TSModelview.getFunctionUrl().trim());
		String functionOrder = TSModelview.getFunctionOrder();
		if (StringUtils.isEmpty(functionOrder)) {
			TSModelview.setFunctionOrder("0");
		}
	
		if (TSModelview.getTsModelview().getId().equals("")) {
			TSModelview.setTsModelview(null);
		} else {
			TSModelview parent = systemService.getEntity(TSModelview.class,
					TSModelview.getTsModelview().getId());
			TSModelview.setFunctionLevel(Short.valueOf(parent.getFunctionLevel()
					+ 1 + ""));
		}
		if (StringUtil.isNotEmpty(TSModelview.getId())) {
			message = MutiLangUtil.paramUpdSuccess("common.mv");
			//systemService.saveOrUpdate(TSModelview);
			systemService.saveOrUpdate(TSModelview);
			
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
			
			List<TSModelview> subFunction = systemService.findByProperty(TSModelview.class, "tsModelview.id", TSModelview.getId());
			updateSubFunction(subFunction,TSModelview);
		

		} else {
			if (TSModelview.getFunctionLevel().equals(Globals.Function_Leave_ONE)) {
				List<TSModelview> functionList = systemService.findByProperty(
						TSModelview.class, "functionLevel",
						Globals.Function_Leave_ONE);
				
				TSModelview.setFunctionOrder(TSModelview.getFunctionOrder());
			} else {
				List<TSModelview> functionList = systemService.findByProperty(
						TSModelview.class, "functionLevel",
						Globals.Function_Leave_TWO);
				
				TSModelview.setFunctionOrder(TSModelview.getFunctionOrder());
			}
			message = MutiLangUtil.paramAddSuccess("common.mv");
			systemService.save(TSModelview);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}

		j.setMsg(message);
		return j;
	}

	////////////
	
	/**
	 * 系统区划表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSModelview mv, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mv.getId())) {
			mv = systemService.getEntity(TSModelview.class, mv.getId());
			req.setAttribute("pid", mv.getId());
		}
		return new ModelAndView("system/modelview/modelviewaddedit");
	}
	/**
	 * 系统区划表编辑页面跳转
	 * 
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSModelview mv, HttpServletRequest req) throws InstantiationException, IllegalAccessException {
		if (StringUtil.isNotEmpty(mv.getId())) {
			mv = systemService.getEntity(TSModelview.class, mv.getId());
			String oid=mv.getFunctionOrder();
			req.setAttribute("mv", mv);
			req.setAttribute("oid", oid);
			
		}
		return new ModelAndView("system/modelview/modelviewaddedit");
	}
	/////////////

	/**
	 * 权限列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSModelview mv, HttpServletRequest req) {
		String mvid = req.getParameter("id");
		
		int oid;
		List<TSModelview> mvlist = systemService
				.getList(TSModelview.class);
		oid=MvService.getchid("0");
		req.setAttribute("mvlist", mvlist);
		
	
		if (mvid != null) {
			mv = systemService.getEntity(TSModelview.class, mv.getId());
			req.setAttribute("mv", mv);
		}
		if (mv.getTsModelview() != null
				&& mv.getTsModelview().getId() != null) {
			String pid =mv.getTsModelview().getId();
			 oid=MvService.getchid(pid);
			
			req.setAttribute("mv", mv);
		}
		req.setAttribute("oid",oid+1);
		return new ModelAndView("system/modelview/modelviewaddedit");
	}

	/**
	 * 操作列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdateop")
	public ModelAndView addorupdateop(TSOperation operation,
			HttpServletRequest req) {
		List<TSIcon> iconlist = systemService.getList(TSIcon.class);
		req.setAttribute("iconlist", iconlist);
		if (operation.getId() != null) {
			operation = systemService.getEntity(TSOperation.class,
					operation.getId());
			req.setAttribute("operation", operation);
		}
		String functionId = oConvertUtils.getString(req
				.getParameter("functionId"));
		req.setAttribute("functionId", functionId);
		return new ModelAndView("system/operation/operation");
	}

	/**
	 * 权限列表
	 */
	@RequestMapping(params = "mvGrid")
	@ResponseBody
	public List<TreeGrid> mvGrid(HttpServletRequest request,
			TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(TSModelview.class);
		String selfId = request.getParameter("selfId");
		if (selfId != null) {
			cq.notEq("id", selfId);
		}
		if (treegrid.getId() != null) {
			cq.eq("tsModelview.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("tsModelview");
		}
		cq.addOrder("functionOrder", SortDirection.asc);
		cq.add();
		cq = HqlGenerateUtil.getDataAuthorConditionHql(cq, new TSModelview());
		cq.add();
		List<TSModelview> functionList = systemService.getListByCriteriaQuery(cq, false);
        List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIcon("functionUrlt");
		treeGridModel.setTextField("functionName");
		treeGridModel.setParentText("tsModelview_functionName");
		treeGridModel.setParentId("tsModelview_id");
		treeGridModel.setSrc("functionUrl");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("tsModelviews");
		treeGridModel.setOrder("functionOrder");

		treeGrids = systemService.treegrid(functionList, treeGridModel);
		MutiLangUtil.setMutiTree(treeGrids);
		return treeGrids;
	}

	/**
	 * 权限列表
	 */
	@RequestMapping(params = "functionList")
	@ResponseBody
	public void functionList(HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSModelview.class, dataGrid);
		String id = oConvertUtils.getString(request.getParameter("id"));
		cq.isNull("tsModelview");
		if (id != null) {
			cq.eq("tsModelview.id", id);
		}
		cq.add();
		List<TSModelview> functionList = systemService.getListByCriteriaQuery(
				cq, false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 父级权限下拉菜单
	 */
	@RequestMapping(params = "setPFunction")
	@ResponseBody
	public List<ComboTree> setPFunction(HttpServletRequest request,
			ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSModelview.class);
		if (null != request.getParameter("selfId")) {
			cq.notEq("id", request.getParameter("selfId"));
		}
		if (comboTree.getId() != null) {
			cq.eq("TSModelview.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSModelview");
		}
		cq.add();
		List<TSModelview> functionList = systemService.getListByCriteriaQuery(
				cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id",
				"functionName", "TSMvs");
		comboTrees = systemService.ComboTree(functionList, comboTreeModel,
				null, false);
		MutiLangUtil.setMutiTree(comboTrees);
		return comboTrees;
	}

	// update-end--Author:gaofeng Date:20140619 for：修改云桌面的搜索功能中的系统中应用内搜索
	/**
	 * 菜单模糊检索功能
	 * 
	 * @return
	 */
	@RequestMapping(params = "searchApp")
	public ModelAndView searchApp(TSModelview function, HttpServletRequest req) {

		String name = req.getParameter("name");
		//String menuListMap = "";
		StringBuilder menuListMap = new StringBuilder();
		// String menuListMap =
		// "<div class='appListContainer ui-sortable' customacceptdrop='0' index='0' _olddisplay='block' style='width: auto; height: auto; margin-left: 10px; margin-top: 10px; display: block;'>";
		CriteriaQuery cq = new CriteriaQuery(TSModelview.class);

		cq.notEq("functionLevel", Short.valueOf("0"));
		if (name == null || "".equals(name)) {
			cq.isNull("TSModelview");
		} else {
			String name1 = "%" + name + "%";
			cq.like("functionName", name1);
		}
		cq.add();
		List<TSModelview> functionList = systemService.getListByCriteriaQuery(
				cq, false);
		if (functionList.size() > 0 && functionList != null) {
			for (int i = 0; i < functionList.size(); i++) {
				// menuListMap = menuListMap +
				// "<div id='menuList_area'  class='menuList_area'>";
			String icon = "";
//				if (!"".equals(functionList.get(i).getTSIconDesk())
//						&& functionList.get(i).getTSIconDesk() != null) {
//					icon = functionList.get(i).getTSIconDesk().getIconPath();
//				} else {
					icon = "plug-in/sliding/icon/default.png";
//				}
//				menuListMap = menuListMap
//						+ "<div type='"
//						+ i
//						+ 1
//						+ "' class='menuSearch_Info' id='"
//						+ functionList.get(i).getId()
//						+ "' title='"
//						+ functionList.get(i).getFunctionName()
//						+ "' url='"
//						+ functionList.get(i).getFunctionUrl()
//						+ "' icon='"
//						+ icon
//						+ "' style='float:left;left: 0px; top: 0px;margin-left: 30px;margin-top: 20px;'>"
//						+ "<div ><img alt='"
//						+ functionList.get(i).getFunctionName()
//						+ "' src='"
//						+ icon
//						+ "'></div>"
//						+ "<div class='appButton_appName_inner1' style='color:#333333;'>"
//						+ functionList.get(i).getFunctionName() + "</div>"
//						+ "<div class='appButton_appName_inner_right1'></div>" +
//						// "</div>" +
//						"</div>";
				menuListMap.append("<div type='");
				menuListMap.append(i+1);
				menuListMap.append("' class='menuSearch_Info' id='");
				menuListMap.append(functionList.get(i).getId());
				menuListMap.append("' title='");
				menuListMap.append(functionList.get(i).getFunctionName());
				menuListMap.append("' url='");
				menuListMap.append(functionList.get(i).getFunctionUrl());
				menuListMap.append("' icon='");
				menuListMap.append(icon);
				menuListMap.append("' style='float:left;left: 0px; top: 0px;margin-left: 30px;margin-top: 20px;'>");
				menuListMap.append("<div ><img alt='");
				menuListMap.append(functionList.get(i).getFunctionName());
				menuListMap.append("' src='");
				menuListMap.append(icon);
				menuListMap.append("'></div>");
				menuListMap.append("<div class='appButton_appName_inner1' style='color:#333333;'>");
				menuListMap.append(functionList.get(i).getFunctionName() + "</div>");
				menuListMap.append("<div class='appButton_appName_inner_right1'></div>");
				menuListMap.append("</div>");
				
			}
		} else {
			//menuListMap = menuListMap + "很遗憾，在系统中没有检索到与“" + name + "”相关的信息！";
			menuListMap = menuListMap.append("很遗憾，在系统中没有检索到与“" + name + "”相关的信息！");
		}
		// menuListMap = menuListMap + "</div>";
		System.out.println("-------------------------------" + menuListMap);
		req.setAttribute("menuListMap", menuListMap.toString());
		return new ModelAndView("system/mv/menuAppList");
	}

	
	
	
	/**
	 * 视图扩展获取tsfunction 视图路径及pid，排序号，是否叶子，中文名
	 * 
	 * @return
	 * 
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@RequestMapping(params = "getTsfunctionMenudata")
	@ResponseBody
	public Object getTsfunctionMenudata(HttpServletRequest request) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String id = request.getParameter("clickFunctionId");
		String url = request.getParameter("id");
		String viewurl= "";
	try {
		 viewurl =MvService.gettsfunctionview(url);
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

//		List<TSFunction> res =MvService.gettsfunctionMenudata(id);
//		Iterator<TSFunction> it = res.iterator();
		JSONObject obj = new JSONObject();
		obj.put("viewurl", viewurl);

//		while (it.hasNext()) {
//			TSFunction t = it.next();
//			orderid = t.getFunctionOrder();
//			String parentid = t.getTSFunction().getId();
//			
//		}
			
		
		return obj;
	}

	/**
	 * 视图扩展获取tsfunction树
	 * 
	 * @return
	 */

	@RequestMapping(params = "GetTsfunctionMenu")
	@ResponseBody
	public List<ComboTree> GetTsfunctionMenu(HttpServletRequest request, final ComboTree rootCombotree) {
		String id = request.getParameter("id");
		if (id != null && id.indexOf("clickFunctionId=") > 0) {
			id = id.substring(id.indexOf("clickFunctionId=") + "clickFunctionId=".length());
		}
		Map<String, String> nodeMenu =MvService.gettsfunctionMenu(id);
		Iterator<Entry<String, String>> it = nodeMenu.entrySet().iterator();
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		while (it.hasNext()) {
			Entry<String, String> key = it.next();
			ComboTree combotree = new ComboTree();
			combotree.setId(key.getKey());
			combotree.setText(key.getValue());
		
			if (key.getKey().indexOf("?") < 0)
				combotree.setState("closed");
			comboTrees.add(combotree);
		}
		MutiLangUtil.setMutiTree(comboTrees);
		if (id != null && !id.equals(""))
		
			return comboTrees;
		rootCombotree.setId("#");
		rootCombotree.setText("选择视图");
		rootCombotree.setChecked(false);
		rootCombotree.setChildren(comboTrees);
		rootCombotree.setState("closed");
		return new ArrayList<ComboTree>() {
			{
				add(rootCombotree);
			}
		};
	}
	
	@RequestMapping(params = "mvtreepage")
	public ModelAndView mvtreepage(HttpServletRequest request) {
		
		return new ModelAndView("system/modelview/modelviewdialog");
	}

	

	
}
