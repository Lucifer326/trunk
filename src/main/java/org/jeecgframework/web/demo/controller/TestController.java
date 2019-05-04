package org.jeecgframework.web.demo.controller;
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
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.demo.entity.TestEntity;
import org.jeecgframework.web.demo.service.TestServiceI;
import org.jeecgframework.web.system.pojo.base.TSDepart;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**   
 * @Title: Controller
 * @Description: 测试表
 * @author onlineGenerator
 * @date 2017-02-06 17:22:13
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/testController")
public class TestController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestController.class);

	@Autowired
	private TestServiceI testService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@RequestMapping(params = "testTreegrid")
	public String testTreegrid(){
		return "jeecg/demo/test/testTreegrid";
	}
	
	/**
	 * 测试组织机构树表
	 * @param tSDepart
	 * @param request
	 * @param response
	 * @param treegrid
	 * @return
	 */
	@RequestMapping(params = "departgrid")
	@ResponseBody
	public Object departgrid(TSDepart tSDepart,HttpServletRequest request, HttpServletResponse response, TreeGrid treegrid) {
		//1.根据 treegrid 获取 departList
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		if("yes".equals(request.getParameter("isSearch"))){
			treegrid.setId(null);
			tSDepart.setId(null);
		} 
		if(null != tSDepart.getDepartname()){
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSDepart);
		}
		if (treegrid.getId() != null) {
			cq.eq("TSPDepart.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSPDepart");
		}
		if(tSDepart.getRegionId()!=null){
			cq.eq("regionId", tSDepart.getRegionId());
		}
		cq.add();
		List<TreeGrid> departList =null;
		departList=systemService.getListByCriteriaQuery(cq, false);
		//2.如果 departList 为空，根据 tSDepart 重新获取 departList
		if(departList.size()==0&&tSDepart.getDepartname()!=null){ 
			cq = new CriteriaQuery(TSDepart.class);
			TSDepart parDepart = new TSDepart();
			tSDepart.setTSPDepart(parDepart);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSDepart);
		    departList =systemService.getListByCriteriaQuery(cq, false);
		}
		//3.设置树形列表模型 TreeGrid
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setTextField("departname");
		treeGridModel.setParentText("TSPDepart_departname");
		treeGridModel.setParentId("TSPDepart_id");
		treeGridModel.setSrc("description");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("TSDeparts");
        Map<String,Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("orgCode", "orgCode");
        fieldMap.put("orgType", "orgType");
		fieldMap.put("mobile", "mobile");
		fieldMap.put("fax", "fax");
		fieldMap.put("address", "address");
        treeGridModel.setFieldMap(fieldMap);
        //4. 根据树形列表模型获取树形列表数据
        treeGrids = systemService.treegrid(departList, treeGridModel);
        //5.将树形列表数据转换为 json 并返回
        JSONArray jsonArray = new JSONArray();
        for (TreeGrid treeGrid : treeGrids) {
            jsonArray.add(JSON.parse(treeGrid.toJson()));
        }
        return jsonArray;
	}
	


	/**
	 * 测试表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("jeecg/demo/test/testList");
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
	public void datagrid(TestEntity test,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, test, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.testService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除测试表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TestEntity test, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		test = systemService.getEntity(TestEntity.class, test.getId());
		message = "测试表删除成功";
		try{
			testService.delete(test);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除测试表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "测试表删除成功";
		try{
			for(String id:ids.split(",")){
				TestEntity test = systemService.getEntity(TestEntity.class, 
				id
				);
				testService.delete(test);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "测试表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加测试表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TestEntity test, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "测试表添加成功";
		try{
			testService.save(test);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新测试表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TestEntity test, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "测试表更新成功";
		TestEntity t = testService.get(TestEntity.class, test.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(test, t);
			testService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "测试表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
 	/**
	 * 自定义按钮-sql增强-测试
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doTest1")
	@ResponseBody
	public AjaxJson doTest1(TestEntity test, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		message = "测试成功";
		TestEntity t = testService.get(TestEntity.class, test.getId());
		try{
			testService.doTest1Sql(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "测试失败";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 测试表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TestEntity test, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(test.getId())) {
			test = testService.getEntity(TestEntity.class, test.getId());
			req.setAttribute("testPage", test);
		}
		return new ModelAndView("jeecg/demo/test/test-add");
	}
	/**
	 * 测试表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TestEntity test, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(test.getId())) {
			test = testService.getEntity(TestEntity.class, test.getId());
			req.setAttribute("testPage", test);
		}
		return new ModelAndView("jeecg/demo/test/test-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","testController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TestEntity test,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TestEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, test, request.getParameterMap());
		List<TestEntity> tests = this.testService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"测试表");
		modelMap.put(NormalExcelConstants.CLASS,TestEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("测试表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tests);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TestEntity test,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"测试表");
    	modelMap.put(NormalExcelConstants.CLASS,TestEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("测试表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<TestEntity> listTestEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TestEntity.class,params);
				for (TestEntity test : listTestEntitys) {
					testService.save(test);
				}
				j.setMsg("文件导入成功！");
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
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TestEntity> list() {
		List<TestEntity> listTests=testService.getList(TestEntity.class);
		return listTests;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TestEntity task = testService.get(TestEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TestEntity test, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TestEntity>> failures = validator.validate(test);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		testService.save(test);

		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = test.getId();
		URI uri = uriBuilder.path("/rest/testController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TestEntity test) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TestEntity>> failures = validator.validate(test);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		testService.saveOrUpdate(test);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		testService.deleteEntityById(TestEntity.class, id);
	}
}
