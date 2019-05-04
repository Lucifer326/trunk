package com.jeecg.bssys_deploy.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
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
import com.jeecg.bssys_deploy.entity.BssysDeploydisEntity;
import com.jeecg.bssys_deploy.service.BssysDeployServiceI;
import com.jeecg.bssys_deploy.service.BssysDeploydisServiceI;
import com.jeecg.system_region.entity.SystemRegionEntity;
import com.jeecg.system_region.service.SystemRegionServiceI;

/**
 * @Title: Controller
 * @Description: 系统配置绑定行政区划表
 * @author onlineGenerator
 * @date 2017-05-23 09:15:09
 * @version V1.0
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/bssysDeploydisController")
public class BssysDeploydisController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BssysDeploydisController.class);

	@Autowired
	private BssysDeploydisServiceI bssysDeploydisService;
	@Autowired
	private BssysDeployServiceI bssysDeployService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private SystemRegionServiceI systemRegionService;

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 系统配置绑定行政区划表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "dislist")
	public ModelAndView dislist(HttpServletRequest request) {
		request.setAttribute("deploy_id", request.getParameter("deploy_id"));
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeployBindDisList");
	}



	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "disdatagrid")
	public void disdatagrid(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		//获取页面传递过来的区划id
		String regionId = request.getParameter("dis_id");//区划id
		if (StringUtil.isEmpty(regionId)) {
			HttpSession session = ContextHolderUtils.getSession();
			regionId = (String) session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID);
		}
		//将区划id转换为区划编码，供查询使用
		String regionCode = null;
		if(StringUtil.isEmpty(regionId)) {
			regionCode = null;
		}else {
			SystemRegionEntity region = systemRegionService.getEntity(SystemRegionEntity.class,
					regionId);
			if(region != null) {
				regionCode = region.getRegionCode();
			}
		}
		
		//对datagrid中的field做处理，避免点击左侧区划树报错
		if(dataGrid.getField()==null || dataGrid.getField().equals("")) {
			dataGrid.setField("id,disCode,regionName,name,value,description,createDate,");
		}
		
		//获取页面传递过来的配置项id
		String deployid = request.getParameter("deploy_id");
	
		//查询获得 pageList
		PageList pageList = bssysDeploydisService.getList(deployid, regionCode,dataGrid);
		
		//将pageList装换为 对应实体的集合
		List<BssysDeploydisEntity> list = pageList.getResultList();
		
		dataGrid.setResults(list);
		dataGrid.setTotal(pageList.getCount());
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 获取指定区划是否有对应的配置数据
	 * 
	 * @return
	 */
	@RequestMapping(params = "getCount")
	@ResponseBody
	public String getCount(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request) {
		
		//从前台页面获取 区划id
		String regionId = request.getParameter("dis_id");
		
		//从前台页面获取配置id
		String deployid = request.getParameter("deploy_id");
		
		//在sql中拼接 查询条件
		String where = " and dedis.deploy_id = '" + deployid + "'" + " and dis.id = '" + regionId + "'";

		//查询 记录数
		Integer counts = bssysDeploydisService.getCount(where);
		
		//将结果返回页面
		return counts.toString();
	}
	/**
	 * 删除系统配置绑定行政区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		bssysDeploydis = systemService.getEntity(BssysDeploydisEntity.class, bssysDeploydis.getId());
		message = "系统配置绑定行政区划表删除成功";
		try {
			bssysDeploydisService.delete(bssysDeploydis);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置绑定行政区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 软删除系统配置绑定行政区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doFalseDel")
	@ResponseBody
	public AjaxJson doFalseDel(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		bssysDeploydis = systemService.getEntity(BssysDeploydisEntity.class, bssysDeploydis.getId());
		message = "系统配置绑定行政区划表删除成功";
		try {
			bssysDeploydis.setIsdeleted(1);
			bssysDeploydisService.doUpdateSql(bssysDeploydis);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置绑定行政区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除系统配置绑定行政区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统配置绑定行政区划表删除成功";
		try {
			for (String id : ids.split(",")) {
				BssysDeploydisEntity bssysDeploydis = systemService.getEntity(BssysDeploydisEntity.class, id);
				bssysDeploydisService.delete(bssysDeploydis);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置绑定行政区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量软删除系统配置绑定行政区划表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchFalseDel")
	@ResponseBody
	public AjaxJson doBatchFalseDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统配置绑定行政区划表删除成功";
		try {
			for (String id : ids.split(",")) {
				BssysDeploydisEntity bssysDeploydis = systemService.getEntity(BssysDeploydisEntity.class, id);
				bssysDeploydis.setIsdeleted(1);
				bssysDeploydisService.doUpdateSql(bssysDeploydis);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置绑定行政区划表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加系统配置绑定行政区划表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统配置绑定行政区划表添加成功";
		try {
			bssysDeploydisService.save(bssysDeploydis);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置绑定行政区划表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新系统配置绑定行政区划表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "系统配置绑定行政区划表更新成功";
		BssysDeploydisEntity t = bssysDeploydisService.get(BssysDeploydisEntity.class, bssysDeploydis.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(bssysDeploydis, t);
			bssysDeploydisService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "系统配置绑定行政区划表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 系统配置绑定行政区划表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BssysDeploydisEntity bssysDeploydis, HttpServletRequest req) {
		
		// 行政区划信息
		if (StringUtil.isNotEmpty(req.getParameter("dis_id"))) {
			SystemRegionEntity region = systemRegionService.getEntity(SystemRegionEntity.class,
					req.getParameter("dis_id"));
			req.setAttribute("systemRegionPage", region);
		}
		// 系统配置信息
		if (StringUtil.isNotEmpty(req.getParameter("deploy_id"))) {
			BssysDeployEntity deploy = bssysDeployService.getEntity(BssysDeployEntity.class,
					req.getParameter("deploy_id"));
			req.setAttribute("bssysDeployPage", deploy);
		}
		//系统配置绑定行政区划信息
		if (StringUtil.isNotEmpty(bssysDeploydis.getId())) {
			bssysDeploydis = bssysDeploydisService.getEntity(BssysDeploydisEntity.class, bssysDeploydis.getId());
			req.setAttribute("bssysDeploydisPage", bssysDeploydis);
		}
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeploydis-add");
	}

	/**
	 * 系统配置绑定行政区划表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BssysDeploydisEntity bssysDeploydis, HttpServletRequest req, DataGrid dataGrid) {
		
		SystemRegionEntity region = null;
		BssysDeployEntity deploy = null;
		
		if (StringUtil.isNotEmpty(bssysDeploydis.getId())) {
			
			bssysDeploydis = bssysDeploydisService.getEntity(BssysDeploydisEntity.class, bssysDeploydis.getId());		
			
			if(StringUtil.isNotEmpty(bssysDeploydis.getDisId())){
				
				region = systemRegionService.getEntity(SystemRegionEntity.class,
						bssysDeploydis.getDisId());
			}
			
			if(StringUtil.isNotEmpty(bssysDeploydis.getDeployId())){
				
				deploy = bssysDeployService.getEntity(BssysDeployEntity.class,
						bssysDeploydis.getDeployId());
			}
					
		}
		req.setAttribute("systemRegionPage", region);
		req.setAttribute("bssysDeploydisPage", bssysDeploydis);
		req.setAttribute("bssysDeployPage", deploy);
		
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeploydis-update");
	}
	
	/**
	 * 系统配置绑定行政区划表查看页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(BssysDeploydisEntity bssysDeploydis, HttpServletRequest req) {
		BssysDeploydisEntity deployDis = null;
		SystemRegionEntity region = null;
		BssysDeployEntity deploy = null;
		
		if (StringUtil.isNotEmpty(bssysDeploydis.getId())) {
			
			deployDis = bssysDeploydisService.getEntity(BssysDeploydisEntity.class, bssysDeploydis.getId());		
			
			
			region = systemRegionService.getEntity(SystemRegionEntity.class,
					deployDis.getDisId());
			
			deploy = bssysDeployService.getEntity(BssysDeployEntity.class,
					deployDis.getDeployId());
		
		}
		
		req.setAttribute("systemRegionPage", region);
		req.setAttribute("bssysDeploydisPage", deployDis);
		req.setAttribute("bssysDeployPage", deploy);
		
		return new ModelAndView("com/jeecg/bssys_deploy/bssysDeploydis-detail");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "bssysDeploydisController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BssysDeploydisEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bssysDeploydis,
				request.getParameterMap());
		List<BssysDeploydisEntity> bssysDeploydiss = this.bssysDeploydisService.getListByCriteriaQuery(cq, false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "系统配置绑定行政区划表");
		modelMap.put(NormalExcelConstants.CLASS, BssysDeploydisEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("系统配置绑定行政区划表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, bssysDeploydiss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BssysDeploydisEntity bssysDeploydis, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME, "系统配置绑定行政区划表");
		modelMap.put(NormalExcelConstants.CLASS, BssysDeploydisEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,
				new ExportParams("系统配置绑定行政区划表列表", "导出人:" + ResourceUtil.getSessionUserName().getRealName(), "导出信息"));
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
				List<BssysDeploydisEntity> listBssysDeploydisEntitys = ExcelImportUtil
						.importExcel(file.getInputStream(), BssysDeploydisEntity.class, params);
				for (BssysDeploydisEntity bssysDeploydis : listBssysDeploydisEntitys) {
					bssysDeploydisService.save(bssysDeploydis);
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
	public List<BssysDeploydisEntity> list() {
		List<BssysDeploydisEntity> listBssysDeploydiss = bssysDeploydisService.getList(BssysDeploydisEntity.class);
		return listBssysDeploydiss;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BssysDeploydisEntity task = bssysDeploydisService.get(BssysDeploydisEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BssysDeploydisEntity bssysDeploydis, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BssysDeploydisEntity>> failures = validator.validate(bssysDeploydis);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		bssysDeploydisService.save(bssysDeploydis);

		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = bssysDeploydis.getId();
		URI uri = uriBuilder.path("/rest/bssysDeploydisController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BssysDeploydisEntity bssysDeploydis) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BssysDeploydisEntity>> failures = validator.validate(bssysDeploydis);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		bssysDeploydisService.saveOrUpdate(bssysDeploydis);

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		bssysDeploydisService.deleteEntityById(BssysDeploydisEntity.class, id);
	}
}
