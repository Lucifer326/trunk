package org.jeecgframework.tag.comdesign.controller;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.dao.impl.CommonDao;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.service.ControlGeneratorService;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.core.hplus.DataListGridTag;
import org.jeecgframework.tag.vo.hplus.DataListGridColumn;
import org.jeecgframework.web.cgform.entity.config.CgFormFieldEntity;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/***
 * 数据表格控制器
 * @author zkx
 * 日期：2018-1-12
 */
@Scope("prototype")
@Controller
@RequestMapping("/datalistgridController")
public class DataListGridController {

	
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private ControlGeneratorService controlGeneratorService;
	
	
	
	/**
     * 表格设置页面跳转
     *
     * @return
     */
	@RequestMapping(params = "setDatagrid")
	public ModelAndView dataListGrid(HttpServletRequest request) {
		
		return new ModelAndView("comdesign/setDatagrid");
	}
	/**
	 * 通过设置的属性生成前端代码，返给前台页面
	 * @param popupTreeTag
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "datalistgridSourceCode")
	@ResponseBody
	public AjaxJson datalistgridSourceCode(DataListGridTag dataListGridTag, HttpServletRequest request){
		
		//获取前台传过来的列数组
		String list = request.getParameter("columdatalist");
		
		AjaxJson j = new AjaxJson();
		JSONArray jsonArray = new JSONArray(list);
		String str =null;
		List<DataListGridColumn> columnList = new ArrayList<DataListGridColumn>();
		for(int i =0;i<jsonArray.length();i++){//遍历列数组
			
			JSONArray js = jsonArray.getJSONObject(i).getJSONArray("value");
			
			DataListGridColumn dgc = new DataListGridColumn();
			for(int k =0;k<js.length();k++){
				
				str = js.getString(k);
				
				switch (k) {
				case 0:
					dgc.setRec(str);
					break;
				case 1:
					dgc.setField(str);
					break;
				case 2:
					dgc.setTitle(str);
					break;
				case 3:
					dgc.setWidth(Integer.valueOf(str));
					break;
				case 4:
					dgc.setHidden(Boolean.valueOf(str).booleanValue());
					break;

				default:
					break;
				}
				
			}
			columnList.add(dgc);
			
			dataListGridTag.setColumn(dgc.getTitle(), dgc.getField(), dgc.getWidth(), dgc.isHidden(),dgc.getRec());
		}
		
		dataListGridTag.setColumnList(columnList);
		
		//调用标签类的方法 获取源码Code
		String sourceCode = dataListGridTag.bootstrapdatatables().toString();
		
		j.setSuccess(true);
		j.setMsg(sourceCode);
		
		return j;
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid) {
	
	String tableName = request.getParameter("tableName");
	
	String condiArr = request.getParameter("condiArr");
	JSONArray jsonArray = null;
	if(StringUtils.isNotEmpty(condiArr)){
		
		jsonArray = new JSONArray(condiArr);
	}
	
	PageList pageList = controlGeneratorService.getdatalistgrid(tableName,dataGrid,jsonArray);
	
	 dataGrid.setResults(pageList.getResultList());
     dataGrid.setTotal(pageList.getCount());
		TagUtil.datagrid(response, dataGrid);
	
	}

	
	/**
     * 列设置页面跳转
     *
     * @return
     */
	@RequestMapping(params = "setColumns")
	public ModelAndView setColumns(HttpServletRequest request) {
		
		String tablename= request.getParameter("tablename");
		
		String hql = "from CgFormFieldEntity f where f.table in(select h.id from CgFormHeadEntity h WHERE h.tableName = ?)";
		
		List<CgFormFieldEntity> columnsList = commonDao.findHql(hql, tablename);
		
		request.setAttribute("columnsList", columnsList);
		
		return new ModelAndView("comdesign/setColumns");
	}
	
	
	@RequestMapping(params = "getColumnsByTablename")
	@ResponseBody
	public AjaxJson getColumnsByTablename(String tablename,HttpServletRequest request) {
			
			AjaxJson j= new AjaxJson();	
			
			String hql = "from CgFormFieldEntity f where f.table in(select h.id from CgFormHeadEntity h WHERE h.tableName = ?)";
			
			List<CgFormFieldEntity> columnsList = commonDao.findHql(hql, tablename);
		
			if(columnsList.size()<0){				
				
				 return j;
			}
			
			List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
			
			
			for(CgFormFieldEntity c :columnsList){
				Map<String,String> map = new HashMap<String,String>();
				
				map.put("coluname", c.getFieldName());
				map.put("content", c.getContent());
				dataList.add(map);
			}
			
			net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(dataList);
			j.setMsg(jsonArray.toString());
			return j;
		
		
	}
	/**
	 * 新增
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DataListGridTag dataListGridTag, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "控件添加成功";
		
		String historyId = request.getParameter("historyId");
		String source = request.getParameter("source");
		String controlName = request.getParameter("controlName");
		String typeId = request.getParameter("typeId");
		
		CgformControlDesignEntity controlDesign = null;
		
		if(historyId == null || historyId.equals("")) {
			
			controlDesign = new CgformControlDesignEntity();
		
			//设置控件名称
			controlDesign.setName(controlName);
		}else{
			
			controlDesign = controlGeneratorService.getControlDesign(historyId);
			
		}
		
		String list = request.getParameter("columdatalist");
		
		JSONArray jsonArray = new JSONArray(list);
		String str =null;
		List<DataListGridColumn> columnList = new ArrayList<DataListGridColumn>();
		for(int i =0;i<jsonArray.length();i++){
			
			JSONArray js = jsonArray.getJSONObject(i).getJSONArray("value");
			
			DataListGridColumn dgc = new DataListGridColumn();
			for(int k =0;k<js.length();k++){
				
				str = js.getString(k);
				
				switch (k) {
				case 0:
					dgc.setRec(str);
					break;
				case 1:
					dgc.setField(str);
					break;
				case 2:
					dgc.setTitle(str);
					break;
				case 3:
					dgc.setWidth(Integer.valueOf(str));
					break;

				default:
					break;
				}
				
			}
			columnList.add(dgc);
			
			dataListGridTag.setColumn(dgc.getTitle(), dgc.getField(), dgc.getWidth(), dgc.isHidden(),dgc.getRec());
		}
		
		dataListGridTag.setColumnList(columnList);
		
		//设置属性
		String objectToJson = JSON.toJSONString(dataListGridTag);
		controlDesign.setProperty(objectToJson);
		//设置源码
		controlDesign.setSourceCode(source);
		//设置控件类型
		controlDesign.setTypeId(typeId);
		//设置修改时间
		controlDesign.setUpdateDate(new Date());
		
		//获取当前登录用户
		HttpSession session = ContextHolderUtils.getSession();
		TSUser currentUser =  (TSUser)session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		//设置修改人
		controlDesign.setCreateBy(currentUser.getRealName());
			
		try {
			controlGeneratorService.save(controlDesign);
			message = controlDesign.getId();
		} catch (Exception e) {
			e.printStackTrace();
			message = "控件添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
}
