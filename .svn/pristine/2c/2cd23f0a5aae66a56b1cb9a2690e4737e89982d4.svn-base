package com.jeecg.bssys_deploy.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.DepartService;
import org.jeecgframework.web.system.service.SystemService;
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

import com.jeecg.bssys_deploy.entity.BssysDeployEntity;
import com.jeecg.bssys_deploy.service.BssysDeployServiceI;
import com.jeecg.system_region.entity.SystemRegionEntity;
import com.jeecg.system_region.service.SystemRegionServiceI;
import org.jeecgframework.web.system.pojo.base.SystemRegionContorl;

/**
 * @Title: Controller
 * @Description: 系统配置表
 * @author onlineGenerator
 * @date 2017-05-17 14:43:22
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/bssysDeployController")
public class BssysDeployController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BssysDeployController.class);

	@Autowired
	private BssysDeployServiceI bssysDeployService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private SystemRegionServiceI systemRegionService;
	@Autowired
	private DepartService departService;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 系统配置表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeployList");
	}

	/**
	 * easyui AJAX请求数据 报错，不可使用
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(BssysDeployEntity bssysDeploy, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BssysDeployEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bssysDeploy, request.getParameterMap());
		try {
			// 自定义追加查询条件
			cq.eq("isdeleted", 0);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bssysDeployService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 行政区划树状表格页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listtree")
	public ModelAndView listTree() {
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeployListTree");
	}

	
	/**
	 * 异步加载树形表格
	 * @param systemRegion
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "treedatagrid")
	@ResponseBody
	public Object treedatagrid(BssysDeployEntity systemRegion, HttpServletRequest request, HttpServletResponse response,
			TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(BssysDeployEntity.class);
		boolean noSearch = true;
		String nodeId  = request.getParameter("id");
		String name  = request.getParameter("name");		
		if (StringUtil.isNotEmpty(name)) {
			cq.like("name", "%" + name + "%");
			noSearch = false;
		}
		if(noSearch){
			if(nodeId == null || nodeId == ""){
				
				cq.isNull("parent");
			}else{
				cq.eq("parent.id", nodeId);
			}						
		}
		cq.eq("isdeleted", 0);
		cq.add();
		List<BssysDeployEntity> deployList = bssysDeployService.getListByCriteriaQuery(cq, false);
	
		
		return parseTrees(deployList);
	}

	private List<Map<String, Object>> parseTrees(List<BssysDeployEntity> list) {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (BssysDeployEntity entity : list) {
			Map<String, Object> tree = new HashMap<String, Object>();
			tree.put("id", entity.getId());
			// tree.put("code", entity.getCode());
			tree.put("code_name", "[" + entity.getCode() + "]" + entity.getName());
			tree.put("description", entity.getDescription());
			tree.put("value", entity.getValue());
			tree.put("isBinddis", entity.getIsBinddis());
			Integer IsBinddis = entity.getIsBinddis();
			String strIsBinddisName = "";
			if (IsBinddis == null) {
				strIsBinddisName = "";
			} else if (IsBinddis == 1) {
				strIsBinddisName = "是";
			} else if (IsBinddis == 0) {
				strIsBinddisName = "否";
			}
			tree.put("IsBinddisName", strIsBinddisName);
				if (entity.getList() != null && entity.getList().size() > 0) {
				
				//对子节点 进行 未删除状态的 过滤
				List<BssysDeployEntity> list1 = entity.getList();
			
					for(int i = 0; i<list1.size();i++){
						if(list1.get(i).getIsdeleted() == 0){
							tree.put("state", "closed");
						}
					}
				
				
			}
			trees.add(tree);
		}
		return trees;
	}
	
	
	
	

	/**
	 * 删除系统配置表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BssysDeployEntity bssysDeploy, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		bssysDeploy = systemService.getEntity(BssysDeployEntity.class, bssysDeploy.getId());
		message = "系统配置表删除成功";
		try {
			bssysDeployService.delete(bssysDeploy);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 软删除系统配置表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doFalseDel")
	@ResponseBody
	public AjaxJson doFalseDel(BssysDeployEntity bssysDeploy, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		message = "系统配置表删除成功";
		bssysDeploy = systemService.getEntity(BssysDeployEntity.class, bssysDeploy.getId());

		
		try {
			
			if(bssysDeploy.getList().size()>0){
				message = "该记录存在下级,请先删除下级";
				j.setSuccess(false);
			}else{
				
				bssysDeploy.setIsdeleted(1);
				bssysDeployService.doUpdateSql(bssysDeploy);
				
			}
		
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除系统配置表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统配置表删除成功";
		try {
			for (String id : ids.split(",")) {
				BssysDeployEntity bssysDeploy = systemService.getEntity(BssysDeployEntity.class, id);
				bssysDeployService.delete(bssysDeploy);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量软删除系统配置表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchFalseDel")
	@ResponseBody
	public AjaxJson doBatchFalseDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统配置表删除成功";
		try {
			for (String id : ids.split(",")) {
				BssysDeployEntity bssysDeploy = systemService.getEntity(BssysDeployEntity.class, id);
				bssysDeploy.setIsdeleted(1);
				bssysDeployService.doUpdateSql(bssysDeploy);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加系统配置表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BssysDeployEntity bssysDeploy, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		String pid = request.getParameter("parent.id");
		if(pid == null || pid == ""){
			bssysDeploy.setParent(null);
		}		
			
		message = "系统配置表添加成功";
		
		bssysDeploy.setIsdeleted(0);
		
		try {
			bssysDeployService.save(bssysDeploy);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新系统配置表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BssysDeployEntity bssysDeploy, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统配置表更新成功";
		BssysDeployEntity t = bssysDeployService.get(BssysDeployEntity.class, bssysDeploy.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bssysDeploy, t);
			bssysDeployService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 系统配置表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BssysDeployEntity bssysDeploy, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bssysDeploy.getId())) {
			bssysDeploy = bssysDeployService.getEntity(BssysDeployEntity.class, bssysDeploy.getId());
			req.setAttribute("bssysDeployPage", bssysDeploy);
			
			String code = bssysDeploy.getCode();
			String maxCode = "";
			String newCode = "";
			List<BssysDeployEntity> children = bssysDeploy.getList();
			
			
			if(children != null && children.size()>0){
				
				for(int i = 0;i<children.size(); i++){
					if(children.get(i).getCode().compareTo(maxCode)>0){
						
						maxCode = children.get(i).getCode();
	
					}
							
				}
				
				//从 获得 的 最大code中  截取   后三位
				String subAfterCode =  maxCode.substring(maxCode.length()-3, maxCode.length());
				
				String subbeforeCode =  maxCode.substring(0, maxCode.length()-3);
				
				if(StringUtil.isNumeric(subAfterCode)){
					
					//获取  没有增加前的长度
					int beforeLen = StringUtil.getStringLen(subAfterCode);
					
					//字符串转为 数值  然后 加1
					int num = Integer.parseInt(subAfterCode)+1;
					
					String addAfterCode = StringUtil.numberToString(num);
					
					//获取  增加后的长度
					int afterLen = StringUtil.getStringLen(addAfterCode);
					
					if(beforeLen >afterLen){
						
						int count = beforeLen - afterLen;
						for(int i = 0 ; i <count; i++){
							
							newCode += "0";
						}
					}
					maxCode = subbeforeCode + newCode + addAfterCode;
					
				}
	
			}else{
				maxCode = code+"001";
	
			}
			
			
			req.setAttribute("maxCode", maxCode);
		}
		

		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeploy-add");
	}

	/**
	 * 系统配置表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BssysDeployEntity bssysDeploy, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bssysDeploy.getId())) {
			bssysDeploy = bssysDeployService.getEntity(BssysDeployEntity.class, bssysDeploy.getId());
			req.setAttribute("bssysDeployPage", bssysDeploy);
		}
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeploy-update");
	}
	
	/**
	 * 系统配置表查看页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(BssysDeployEntity bssysDeploy, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bssysDeploy.getId())) {
			bssysDeploy = bssysDeployService.getEntity(BssysDeployEntity.class, bssysDeploy.getId());
			req.setAttribute("bssysDeployPage", bssysDeploy);
		}
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeploy-detail");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "bssysDeployController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BssysDeployEntity bssysDeploy, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BssysDeployEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bssysDeploy, request.getParameterMap());
		List<BssysDeployEntity> bssysDeploys = this.bssysDeployService.getListByCriteriaQuery(cq, false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "系统配置表");
		modelMap.put(NormalExcelConstants.CLASS, BssysDeployEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("系统配置表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, bssysDeploys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BssysDeployEntity bssysDeploy, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME, "系统配置表");
		modelMap.put(NormalExcelConstants.CLASS, BssysDeployEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("系统配置表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
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
				List<BssysDeployEntity> listBssysDeployEntitys = ExcelImportUtil.importExcel(file.getInputStream(),
						BssysDeployEntity.class, params);
				for (BssysDeployEntity bssysDeploy : listBssysDeployEntitys) {
					bssysDeployService.save(bssysDeploy);
				}
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

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<BssysDeployEntity> list() {
		List<BssysDeployEntity> listBssysDeploys = bssysDeployService.getList(BssysDeployEntity.class);
		return listBssysDeploys;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BssysDeployEntity task = bssysDeployService.get(BssysDeployEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BssysDeployEntity bssysDeploy, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BssysDeployEntity>> failures = validator.validate(bssysDeploy);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		bssysDeployService.save(bssysDeploy);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = bssysDeploy.getId();
		URI uri = uriBuilder.path("/rest/bssysDeployController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BssysDeployEntity bssysDeploy) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BssysDeployEntity>> failures = validator.validate(bssysDeploy);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		bssysDeployService.saveOrUpdate(bssysDeploy);

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		bssysDeployService.deleteEntityById(BssysDeployEntity.class, id);
	}

	/*
	 * 获取系统配置树
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<ComboTree> tree() {
		// 从数据库中查表
		CriteriaQuery cq = new CriteriaQuery(BssysDeployEntity.class);
		cq.isNull("parent");
		cq.add();
		List<BssysDeployEntity> regionList = bssysDeployService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < regionList.size(); i++) {
			comboTrees.add(ConvertToTree(regionList.get(i)));
		}
		return comboTrees;

	}

	private ComboTree ConvertToTree(BssysDeployEntity entity) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getName());
		if (entity.getList() != null && entity.getList().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getList().size(); i++) {
				comboTrees.add(ConvertToTree(entity.getList().get(i)));
			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}
	
	@RequestMapping(params="add")
	public ModelAndView addOrg(HttpServletRequest req){
		SystemRegionContorl entity = bssysDeployService.querySystemRegionControl();
		String rcode = "";
		if(entity==null){
			rcode = "000000000000";
			SystemRegionEntity region = systemRegionService.getSystemRegionEntityByRegionCode(rcode);//默认选择中国为根节点			
			SystemRegionEntity region1 = new SystemRegionEntity();
			region1.setId(region.getId());
			region1.setRegionName(region.getRegionName());
			region1.setSysOrgCode("");
			req.setAttribute("region", region1);
		}else{
			String regionId = entity.getRootRegionId();
			String id = entity.getId();
			SystemRegionEntity regionEntity = systemRegionService.getSystemRegionEntityById(regionId);
			rcode = regionEntity.getRegionCode();
			String level = entity.getLevelCode();
			SystemRegionEntity region = systemRegionService.getSystemRegionEntityByRegionCode(rcode);
			SystemRegionEntity region1 = new SystemRegionEntity();
			region1.setRegionLevel(level);
			region1.setSysOrgCode(id);
			region1.setRegionName(region.getRegionName());
			region1.setId(region.getId());
			req.setAttribute("region", region1);
		}
		
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeploy-editOrg");
	}
	@RequestMapping(params="saveRootRegion")
	@ResponseBody
	public AjaxJson saveRootRegion(SystemRegionEntity entity,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "保存成功";
		String regionId = entity.getId();
		String regionLevel = entity.getRegionLevel();
		String id = entity.getSysOrgCode();
		SystemRegionContorl control = new SystemRegionContorl();
		if(!"".equals(id)){
			control.setId(id);			
		}
		control.setRootRegionId(regionId);
		control.setLevelCode(regionLevel);
		try {
			bssysDeployService.saveSytemRegionControl(control);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
}