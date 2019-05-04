package org.jeecgframework.web.formula.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.web.formula.analysis.FormulaConstant;
import org.jeecgframework.web.formula.analysis.FormulaException;
import org.jeecgframework.web.formula.analysis.StringUtil;
import org.jeecgframework.web.formula.dto.DictColAndItem;
import org.jeecgframework.web.formula.dto.FormulaFactorTree;
import org.jeecgframework.web.formula.dto.RefedCol;
import org.jeecgframework.web.formula.dto.SQLCalc;
import org.jeecgframework.web.formula.service.FormulaServiceI;
import org.jeecgframework.web.formula.validate.ValidateI;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jeecg.system_region.controller.SystemRegionController;
import com.jeecg.system_region.entity.SystemRegionEntity;
/**
 * 公式计算接口类
 * 
 * @author Horse_little
 * @date 2017年7月31日 下午10:32:17
 */
@Controller
@RequestMapping("/defController")
public class DefController {
	private static final Logger logger = Logger.getLogger(SystemRegionController.class);

	/*@Autowired
	private SystemRegionServiceI systemRegionService;*/
	@Autowired
	private FormulaServiceI formulaServiceImpl;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ValidateI validate;
	@RequestMapping(params = "define")
	public ModelAndView list(Model model, HttpServletRequest request) {	
		request.setAttribute("tablename",request.getParameter("tablename"));
		String formulaname = request.getParameter("formulaname");
		String type = request.getParameter("type");
		if (type!=null&&type.equals("add")){
			//新增公式
			formulaname=StringUtil.getUUID();
			try{
				boolean saveOk = formulaServiceImpl.saveFormulaName(request.getParameter("tablename"),"formulacolv",formulaname);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		request.setAttribute("formulaname",formulaname);
		return new ModelAndView("formula/define");
	}
	@RequestMapping(params = "test")
	public String test(Model model, HttpServletRequest request) {		
		return "formula/test";
	}
	/**
	 *
	 * @param request
	 * @param comboTree
	 * @param code
	 * @return
	 */
	@RequestMapping(params = "formTree")
	@ResponseBody
	public List<ComboTree> formTree(HttpServletRequest request,final ComboTree rootCombotree) {
		String tablename = request.getParameter("tablename");
		Map<String,String> tableMap = formulaServiceImpl.getFormulaTable();
		Map<String,Map> formulaColMap = formulaServiceImpl.getFormulaColumn();
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,String> key = it.next();
			ComboTree combotree = new ComboTree();
			combotree.setId(key.getKey());
			combotree.setText(key.getValue());
			Map<String,String> colMap = formulaColMap.get(key.getKey());
			Iterator<Entry<String,String>> it2 = formulaColMap.get(key.getKey()).entrySet().iterator();
			List<ComboTree> comboTreesCol = new ArrayList<ComboTree>();
			while(it2.hasNext()){
				Entry<String,String> key2 = it2.next();
				ComboTree combotreeCol = new ComboTree();
				combotreeCol.setId(key2.getKey());
				combotreeCol.setText(key2.getValue());
				comboTreesCol.add(combotreeCol);
			}
			combotree.setChildren(comboTreesCol);
			comboTrees.add(combotree);
		}
		rootCombotree.setId("#");
		rootCombotree.setText("表单列表");
		rootCombotree.setChecked(false);
		rootCombotree.setChildren(comboTrees);

		return new ArrayList<ComboTree>(){{add(rootCombotree);}};
	}

	/*
	 * 公式新增页面打开
	 * @param request
	 * @param tablename
	 * */
	@RequestMapping(params = "addFormulaName")
	public ModelAndView addFormulaName(HttpServletRequest request) {
		request.setAttribute("tablename",request.getParameter("tablename"));
		request.setAttribute("columnname",request.getParameter("columnname"));
		return new ModelAndView("formula/addformula");
	}

	/*
	 * SQL计算器新增页面打开
	 * @param request
	 * @param tablename
	 * */
	@RequestMapping(params = "addSQLCalcName")
	public ModelAndView addSQLCalcName(HttpServletRequest request) {
		request.setAttribute("tablename",request.getParameter("tablename"));
		request.setAttribute("columnname",request.getParameter("columnname"));
		return new ModelAndView("formula/addsqlcalcname");
	}

	/*
	 * SQL计算器新增页面打开
	 * @param request
	 * @param tablename
	 * */
	@RequestMapping(params = "addSQLCalc")
	public ModelAndView addSQLCalc(HttpServletRequest request) {
		request.setAttribute("tablename",request.getParameter("tablename"));
		request.setAttribute("columnname",request.getParameter("columnname"));
		return new ModelAndView("formula/addsqlcalc");
	}

	/*行条件范围设置页面打开
	 * @param request
	 * @param tablename
	 * */
	@RequestMapping(params = "rowCondition")
	public ModelAndView rowCondition(HttpServletRequest request) {
		request.setAttribute("tablename",request.getParameter("tablename"));
		request.setAttribute("formulaname",request.getParameter("formulaname"));
		return new ModelAndView("formula/rowCondition");
	}
	/*
	 * 区划范围页面打开
	 * @param request
	 * @param tablename
	 * */
	@RequestMapping(params = "regionCondition")
	public ModelAndView regionCondition(HttpServletRequest request) {
		request.setAttribute("tablename",request.getParameter("tablename"));
		request.setAttribute("formulaname",request.getParameter("formulaname"));
		return new ModelAndView("formula/regionCondition");
	}

	/**
	 * 获取被引用列信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getFormulaName")
	@ResponseBody
	public void getFormulaName(HttpServletRequest request,HttpServletResponse response) {
		String tablename = request.getParameter("tablename");
		String columnname = request.getParameter("columnname");
		Map<String,String> tableMap = formulaServiceImpl.getFormulaName(tablename,columnname);
		StringBuffer sb = new StringBuffer(100);
		Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();
		JSONArray arr2 = new JSONArray();		
		while(it.hasNext()){
			Entry<String,String> key = it.next();
			JSONObject refedColJSON =new JSONObject();
			refedColJSON.put("id",key.getKey());
			refedColJSON.put("text",key.getValue());
			if (arr2.size()<1)
				refedColJSON.put("selected", "true");
			arr2.add(refedColJSON);
		}
		this.writeJson(response, ContentType.JSON, 0, arr2);
	}

	/**
	 * 获取被引用列信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getRefedCol")
	@ResponseBody
	public void getRefedCol(HttpServletRequest request,HttpServletResponse response) {
		String tablename = request.getParameter("tablename");
		@SuppressWarnings("unchecked")
		List<RefedCol> refcolList = formulaServiceImpl.getRefedCol(tablename);
		StringBuffer sb = new StringBuffer(100);
		/*Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();*/	
		JSONArray arr2 = new JSONArray();	
		for(RefedCol refcol:refcolList){
			//Entry<String,String> key = it.next();
			JSONObject refedColJSON =new JSONObject();
			/*refedColJSON.put("id",key.getValue());
			refedColJSON.put("text",key.getKey());*/
			refedColJSON.put("id",refcol.getColName());
			refedColJSON.put("text",refcol.getColCName());
			arr2.add(refedColJSON);
		}
		this.writeJson(response, ContentType.JSON, 0, arr2);
	}

	/**
	 * 公式合法性验证
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "checkFormula")
	@ResponseBody
	public void checkFormula(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String express = request.getParameter("express");
		if (FormulaConstant.typeItemMap == null)
			FormulaConstant.typeItemMap = formulaServiceImpl.getTypeItemMap();
		//boolean checked = true;
		String checkErro="";
		if (express==null||express.trim().equals("")){
			checkErro = "计算表达式不允许为空，请核查！";
			this.returnJson(checkErro, response);
			return;
		}
		List<RefedCol> refedList = formulaServiceImpl.getRefedCol(tablename);	
		try{
			Map tableMap = new HashMap();
			for (RefedCol col:refedList){
				tableMap.put(col.getColCName(),col.getColName());
			}
			validate.validate(express,tablename, tableMap);
		}catch(FormulaException fx){
			checkErro = fx.getMessage();
		}catch(Exception ex){
			checkErro = ex.getCause().getMessage();
		}
		/*JSONArray arr2 = new JSONArray();

		if (!checked){
			checkJSON.put("erro", checkErro);
			checkJSON.put("success", "false");
		} else
		{
			checkJSON.put("erro","");
			checkJSON.put("success", "true");
		}
		arr2.add(checkJSON);		
		this.writeJson(response, ContentType.JSON, 0, arr2);*/
		this.returnJson(checkErro, response);
	}
	/**
	 * 公式保存
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "saveFormula")
	@ResponseBody
	public void saveFormula(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String express = request.getParameter("express");
		String formulaName = request.getParameter("formulaname");
		String columnname = request.getParameter("columnname");
		String datarange = request.getParameter("datarange");
		if (FormulaConstant.typeItemMap == null)
			FormulaConstant.typeItemMap = formulaServiceImpl.getTypeItemMap();
		String formulaAnalysis="";
		String checkErro="";
		if (formulaName==null||formulaName.trim().equals("")){
			checkErro = "公式名称不允许为空，请核查！";
			this.returnJson(checkErro, response);
			return;
		}
		if (express==null||express.trim().equals("")){
			checkErro = "计算表达式不允许为空，请核查！";
			this.returnJson(checkErro, response);
			return;
		}
		//Map<String,String> colMap = formulaServiceImpl.getRefedCol(tablename);
		List<RefedCol> refedList = formulaServiceImpl.getRefedCol(tablename);	
		Map colMap = new HashMap();
		for (RefedCol col:refedList){
			colMap.put(col.getColCName(),col.getColName());
		}
		
		String checkLabel = "0";
		String usedCol ="";
		String datarangeResult = "";
		try{
			formulaAnalysis = validate.validate(express,tablename, colMap);
			usedCol = StringUtil.getFactorByMap(express, "[", "]", colMap);
			usedCol = StringUtil.getSelfusedColFromWhere(formulaAnalysis, tablename, "!",usedCol);
			datarangeResult =  StringUtil.replaceAllValueByMap(datarange, "[", "]", colMap);
			checkLabel = "1";
		}catch(FormulaException fx){
			checkLabel = "0";
			checkErro = fx.getMessage();
		}catch(Exception ex){
			checkLabel = "0";
			checkErro = ex.getMessage();
		}
		try{
			datarange=datarange.replace("'","''");
			datarangeResult=datarangeResult.replace("'","''");
			formulaAnalysis=formulaAnalysis.replace("'", "''");
			express=express.replace("'","''");
			boolean saveOk = formulaServiceImpl.saveFormula(tablename,columnname,formulaName,express,formulaAnalysis,checkLabel,usedCol,datarange,datarangeResult);
		} catch(SQLException ex){
			checkErro = checkErro.equals("")?"":checkErro+"公式保存失败，错误原因："+ex.getCause().getMessage();
		}catch(Exception ex){
			checkErro = checkErro.equals("")?"":checkErro+"公式保存失败，错误原因："+ex.getCause().getMessage();
		}
		this.returnJson(checkErro, response);
	}

	/**
	 * 公式名称保存
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "saveFormulaName")
	@ResponseBody
	public AjaxJson saveFormulaName(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String columnname = request.getParameter("columnname");
		String formulaName = request.getParameter("formulaname");
		AjaxJson j = new AjaxJson();
		String checkErro="公式名称添加成功！";
		if (formulaName==null||formulaName.trim().equals("")){
			checkErro = "公式名称不允许为空，请核查！";
			j.setMsg(checkErro);
			return j;
		}
		if (columnname==null||columnname.trim().equals("")){
			checkErro = "公式列不允许为空，请核查！";
			j.setMsg(checkErro);
			return j;
		}
		if (tablename==null||tablename.trim().equals("")){
			checkErro = "表名不允许为空，请核查！";
			j.setMsg(checkErro);
			return j;
		}
		try{
			boolean saveOk = formulaServiceImpl.saveFormulaName(tablename,columnname,formulaName);
		} catch(SQLException ex){
			checkErro = "公式名称保存失败，错误原因："+ex.getCause().getMessage();
		}catch(Exception ex){
			checkErro = "公式名称保存失败，错误原因："+ex.getCause().getMessage();
		}
		j.setMsg(checkErro);
		return j;
	}/**
	 * SQL计算器名称保存
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "saveSQLCalcName")
	@ResponseBody
	public AjaxJson saveSQLCalcName(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String sqlcalcName = request.getParameter("sqlcalcname");
		AjaxJson j = new AjaxJson();
		String checkErro="SQL计算器名称保存成功！";
		if (sqlcalcName==null||sqlcalcName.trim().equals("")){
			checkErro = "SQL计算器名称不允许为空，请核查！";
			j.setMsg(checkErro);
			return j;
		}
		if (tablename==null||tablename.trim().equals("")){
			checkErro = "表名不允许为空，请核查！";
			j.setMsg(checkErro);
			return j;
		}
		try{
			boolean saveOk = formulaServiceImpl.saveSQLCalcName(tablename,sqlcalcName);
		} catch(SQLException ex){
			checkErro = "SQL计算器名称保存失败，错误原因："+ex.getCause().getMessage();
		}catch(Exception ex){
			checkErro = "SQL计算器名称保存失败，错误原因："+ex.getCause().getMessage();
		}
		j.setMsg(checkErro);
		return j;
	}

	/**
	 * 获取SQL计算器列表信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getSQLCalcName2")
	@ResponseBody
	public void getSQLCalcName2(HttpServletRequest request,HttpServletResponse response) {
		String tablename = request.getParameter("tablename");
		Map<String,String> tableMap = formulaServiceImpl.getSQLCalcName(tablename);
		StringBuffer sb = new StringBuffer(100);
		Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();
		JSONArray arr2 = new JSONArray();		
		while(it.hasNext()){
			Entry<String,String> key = it.next();
			JSONObject refedColJSON =new JSONObject();
			refedColJSON.put("id",key.getKey());
			refedColJSON.put("text",key.getValue());
			arr2.add(refedColJSON);
		}
		this.writeJson(response, ContentType.JSON, 0, arr2);
	}


	/**
	 * 获取SQL计算器列表信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getSQLCalcName")
	@ResponseBody
	public List<ComboTree> getSQLCalcName(HttpServletRequest request,final ComboTree rootCombotree) {
		String tablename = request.getParameter("tablename");

		/*if (tablename==null||tablename.equals("")){
			rootCombotree.setId("#");
			rootCombotree.setText("请在公式选择树中选择公式名称！");
			return new ArrayList<ComboTree>(){{add(rootCombotree);}};
		}*/
		Map<String,String> tableMap = formulaServiceImpl.getSQLCalcName(tablename);
		List<ComboTree> comboTreesCol = new ArrayList<ComboTree>();
		Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,String> key = it.next();
			ComboTree combotreeformuname = new ComboTree();
			combotreeformuname.setId(key.getKey());
			combotreeformuname.setText(key.getValue());
			comboTreesCol.add(combotreeformuname);				
		}
		rootCombotree.setId("#");
		rootCombotree.setText("SQL计算器列表");
		rootCombotree.setChecked(false);
		rootCombotree.setChildren(comboTreesCol);

		return new ArrayList<ComboTree>(){{add(rootCombotree);}};
	}
	/**
	 * 获取SQL计算表达式内容信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getSQLCalcExpress")
	@ResponseBody
	public AjaxJson getSQLCalcExpress(HttpServletRequest request,HttpServletResponse response) {
		String sqlcalcname = request.getParameter("sqlcalcname");
		AjaxJson j = new AjaxJson();
		Map calcMap =formulaServiceImpl.getSQLCalcExpress(sqlcalcname);
		StringBuffer sb = new StringBuffer(100);
		sb.append(calcMap.get("calccontent")).append("^").append(calcMap.get("fromitem"));
		Map tableMap = (Map)calcMap.get("tablesmap");
		if (tableMap!=null){
			Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();
			while(it.hasNext()){
				Entry<String,String> key = it.next();
				sb.append(",").append(key.getValue());
			}
		}
		j.setMsg(sb.toString());
		return j;
	}

	/**
	 * 公式合法性验证
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "checkCalc")
	@ResponseBody
	public void checkCalc(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String sqlexpress = request.getParameter("sqlexpress");
		String checkErro="";
		if (sqlexpress==null||sqlexpress.trim().equals("")){
			checkErro = "计算表达式不允许为空，请核查！";
			this.returnJson(checkErro, response);
			return;
		}
		try{
			validate.validate(sqlexpress,null, null);
		}catch(FormulaException fx){
			checkErro = fx.getMessage();
		}catch(Exception ex){
			/*checkErro = ex.getCause().getMessage();
			if (checkErro.equals(""))*/
			checkErro = ex.getMessage();
		}finally{
			this.returnJson(checkErro, response);
		}
	}
	/**
	 * SQL计算器保存
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "saveCalc")
	@ResponseBody
	public void saveCalc(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String sqlexpress = request.getParameter("sqlexpress");
		String sqlcalcname = request.getParameter("sqlcalcname");
		String ifcheck = request.getParameter("ifcheck");
		String selecttableids = request.getParameter("selecttableids");
		String selecttablenames = request.getParameter("selecttablenames");
		String typeitemid = request.getParameter("typeitemid");
		String formulaAnalysis="";
		if (FormulaConstant.typeItemMap == null)
			FormulaConstant.typeItemMap = formulaServiceImpl.getTypeItemMap();
		String checkErro="";
		if (sqlcalcname==null||sqlcalcname.trim().equals("")){
			checkErro = "SQL计算器名称不允许为空，请核查！";
			this.returnJson(checkErro, response);
			return;
		}
		if (sqlexpress==null||sqlexpress.trim().equals("")){
			checkErro = "SQL计算表达式不允许为空，请核查！";
			this.returnJson(checkErro, response);
			return;
		}
		String checkLabel = "0";
		String expressResult ="";
		try{
			expressResult = validate.validate(sqlexpress,null, null);
			checkLabel = "1";
		}catch(FormulaException fx){
			checkLabel = "0";
			checkErro = fx.getMessage();
		}catch(Exception ex){
			checkLabel = "0";
			checkErro = ex.getCause().getMessage();
		}
		try{
			Map argMap = new HashMap();
			argMap.put("tablename", tablename);
			argMap.put("sqlcalcname", sqlcalcname);
			argMap.put("sqlexpressori", sqlexpress.replace("'", "''"));
			argMap.put("sqlexpressresult", expressResult.replace("'", "''"));
			argMap.put("ifcheck", ifcheck);
			argMap.put("selecttableids", selecttableids);
			argMap.put("selecttablenames", selecttablenames);
			argMap.put("typeitemid", typeitemid);
			boolean saveOk = formulaServiceImpl.saveCalc(argMap);
		} catch(SQLException ex){
			checkErro = checkErro.equals("")?"":checkErro+"保存失败，错误原因："+ex.getCause().getMessage();
		}catch(Exception ex){
			checkErro = checkErro.equals("")?"":checkErro+"公式保存失败，错误原因："+ex.getCause().getMessage();
		}
		this.returnJson(checkErro, response);
	}
	/**
	 * 公式区划信息保存
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "saveRegion")
	@ResponseBody
	public AjaxJson saveRegion(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String formulaname = request.getParameter("formulaname");
		String nodes = request.getParameter("nodes");
		String nodestext = request.getParameter("nodestext");
		AjaxJson j= new AjaxJson();
		String checkErro="公式的区划范围设置成功!";
		/*if (nodes==null||nodes.trim().equals("")){			
			//this.returnJson(checkErro, response);
			j.setMsg(checkErro);
			return j;
		}*/
		try{
			boolean saveOk = formulaServiceImpl.saveRegion(tablename,formulaname,nodes);
		} catch(SQLException ex){
			checkErro = "公式区划信息保存，错误原因："+ex.getCause().getMessage();
			j.setMsg(checkErro);
			return j;
		}catch(Exception ex){
			checkErro = "公式区划信息保存，错误原因："+ex.getCause().getMessage();
			j.setMsg(checkErro);
			return j;
		}
		j.setObj(nodestext);
		j.setMsg(checkErro);
		return j;
	}
	/**
	 * 公式区划信息保存
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "saveRowCondition")
	@ResponseBody
	public AjaxJson saveRowCondition(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String formulaname = request.getParameter("formulaname");
		String dictexpress = request.getParameter("dictexpress");
		String datarange = dictexpress;
		String datarangeResult = dictexpress;
		String checkErro = "行条件信息保存成功！";
		try{
			datarange=datarange.replace("'","''");
			datarangeResult=datarangeResult.replace("'","''");
			boolean saveOk = formulaServiceImpl.saveFormulaDataRange(tablename,formulaname,datarange,datarangeResult);
		} catch(Exception ex){
			checkErro = checkErro.equals("")?"":checkErro+"行条件保存失败，错误原因："+ex.getCause().getMessage();
		}
		AjaxJson j = new AjaxJson();
		j.setObj(dictexpress);
		j.setMsg(checkErro);
		return j;
	}

	/**
	 * 获取公式区划信息
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "getFormulaRegion")
	@ResponseBody
	private String getFormulaRegion(HttpServletRequest request) {
		String tablename = request.getParameter("tablename");
		String formulaname = request.getParameter("formulaname");
		return formulaServiceImpl.getFormulaRegion(tablename,formulaname);
	}
	/**
	 * 公式删除
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "delFormulaName")
	@ResponseBody
	public void delFormulaName(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String formulaname = request.getParameter("formulaname");
		String checkErro="";
		try{
			boolean saveOk = formulaServiceImpl.delFormulaName(tablename,formulaname);
		} catch(SQLException ex){
			checkErro = checkErro.equals("")?"":checkErro+"公式删除失败，错误原因："+ex.getCause().getMessage();
		}catch(Exception ex){
			checkErro = checkErro.equals("")?"":checkErro+"公式删除失败，错误原因："+ex.getCause().getMessage();
		}
		this.returnJson(checkErro, response);
	}
	/**
	 * SQL计算器删除
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "delCalcName")
	@ResponseBody
	public void delCalcName(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String sqlcalcname = request.getParameter("sqlcalcname");
		String checkErro="";
		if (sqlcalcname==null||sqlcalcname.trim().equals("")){
			checkErro = "SQL计算器名称未选中，请核查！";
			this.returnJson(checkErro, response);
			return;
		}
		try{
			boolean saveOk = formulaServiceImpl.delCalcName(tablename,sqlcalcname);
		} catch(SQLException ex){
			checkErro = checkErro.equals("")?"":checkErro+"SQL计算器删除失败，错误原因："+ex.getCause().getMessage();
		}catch(Exception ex){
			checkErro = checkErro.equals("")?"":checkErro+"SQL计算器删除失败，错误原因："+ex.getCause().getMessage();
		}
		this.returnJson(checkErro, response);
	}
	/**
	 * 得到公式定义的原始内容进行回显
	 * @param request
	 * @param tablename
	 * @return
	 * @throws Exception 
	 * @throws FormulaException 
	 */
	@RequestMapping(params = "getformulaOriginal")
	@ResponseBody
	public AjaxJson getformulaOriginal(HttpServletRequest request,HttpServletResponse response) throws FormulaException, Exception {
		String tablename = request.getParameter("tablename");
		String formulaName = request.getParameter("formulaname");
		if (formulaName==null||formulaName.trim().equals("")){
			return null;
		}
		if (tablename==null||tablename.trim().equals("")){
			return null;
		}
		AjaxJson j = new AjaxJson();
		String originalContent = formulaServiceImpl.getformulaOriginal(tablename,formulaName);
		if (originalContent!=null)
			j.setMsg(originalContent);
		return j;
	}


	@RequestMapping(params = "tree")
	@ResponseBody
	public List<ComboTree> tree(HttpServletRequest request) {
		//从数据库中查表
		String tablename = request.getParameter("tablename");
		String formulaName = request.getParameter("formulaname");
		String regionids = this.getFormulaRegion(request);
		CriteriaQuery cq = new CriteriaQuery(SystemRegionEntity.class);
		cq.isNull("parent");
		cq.add();
		List<SystemRegionEntity> regionList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < regionList.size(); i++) {
			comboTrees.add(ConvertToTree(regionList.get(i),new StringBuffer(regionids)));
		}
		return comboTrees;

	}
	private ComboTree ConvertToTree(SystemRegionEntity entity,StringBuffer regionids) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getRegionName());
		if (regionids!=null&&!regionids.equals("")){
			if (regionids.toString().indexOf(entity.getId())>0){
				regionids.toString().replace(entity.getId()+",","");
				tree.setChecked(true);
			} else
				tree.setChecked(false);

		}else
			tree.setChecked(false);
		if (entity.getList() != null && entity.getList().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getList().size(); i++) {
				comboTrees.add(ConvertToTree(entity.getList().get(i),regionids));
			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}

	/**
	 * 获取公式选择要素信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getFormulaFactorTree")
	@ResponseBody
	public List<ComboTree> getFormulaFactorTree(HttpServletRequest request,final ComboTree rootCombotree) {
		String tablename = request.getParameter("tablename");
		/*Map<String,String> tableMap = formulaServiceImpl.getFormulaTable();
			Map<String,Map> formulaColMap = formulaServiceImpl.getFormulaColumn();*/
		List<FormulaFactorTree> formulaFactorList = formulaServiceImpl.getFormulaFactorTree(tablename.toUpperCase());
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		List<ComboTree> comboTreesCol = null;
		List<ComboTree> comboTreesFormula = null;
		ComboTree combotree = null;
		for (int i=0;i<formulaFactorList.size();i++){
			FormulaFactorTree factorTree = formulaFactorList.get(i);
			if (comboTreesFormula==null){
				comboTreesFormula = new ArrayList<ComboTree>();
			}
			ComboTree combotreeformuname = new ComboTree();
			combotreeformuname.setId(factorTree.getFormulaName());
			combotreeformuname.setText(factorTree.getFormulaName());
			comboTreesFormula.add(combotreeformuname);
			if (i==formulaFactorList.size() -1||!factorTree.getColumnName().equals(formulaFactorList.get(i + 1).getColumnName())){
				if (comboTreesCol==null)
					comboTreesCol = new ArrayList<ComboTree>();
				ComboTree combotreeCol = new ComboTree();
				combotreeCol.setId(factorTree.getColumnName());
				combotreeCol.setText(factorTree.getColumnNamec());
				combotreeCol.setChildren(comboTreesFormula);
				comboTreesFormula = null;
				comboTreesCol.add(combotreeCol);
			}
			if (i==formulaFactorList.size() -1||!factorTree.getTableName().equals(formulaFactorList.get(i + 1).getTableName())){
				combotree = new ComboTree();
				combotree.setId(factorTree.getTableName());
				combotree.setText(factorTree.getTableNamec());
				comboTrees.add(combotree);
				combotree.setChildren(comboTreesCol);
				comboTreesCol = null;
			}
		}
		rootCombotree.setId("#");
		rootCombotree.setText("表单列表");
		rootCombotree.setChecked(false);
		rootCombotree.setChildren(comboTrees);

		return new ArrayList<ComboTree>(){{add(rootCombotree);}};
	}

	/**
	 * 获取公式选择要素信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getExpressFactorTree")
	@ResponseBody
	public List<ComboTree> getExpressFactorTree(HttpServletRequest request,final ComboTree rootCombotree) {
		String tablename = request.getParameter("tablename");
		String id = request.getParameter("id");
		if(id!=null&&!id.equals("")){
			Map itemMap = formulaServiceImpl.getItemByTableAndCol(tablename,id);
			List<ComboTree> comboTreeItemList = new ArrayList<ComboTree>();
			Iterator<Entry<String,String>> itemIT = itemMap.entrySet().iterator();
			while(itemIT.hasNext()){
				Entry<String,String> keym = itemIT.next();
				ComboTree combotreeCol = new ComboTree();
				combotreeCol.setId(keym.getKey());
				combotreeCol.setText(keym.getValue());
				comboTreeItemList.add(combotreeCol);
			}
			rootCombotree.setId("#");
			rootCombotree.setText("请在公式选择树中选择公式名称！");
			return comboTreeItemList;
		}
		if (tablename==null||tablename.equals("")){
			rootCombotree.setId("#");
			rootCombotree.setText("请在公式选择树中选择公式名称！");
			return new ArrayList<ComboTree>(){{add(rootCombotree);}};
		}
		//Map<String,String> refColMap = formulaServiceImpl.getRefedCol(tablename);
		List<RefedCol> refedList = formulaServiceImpl.getRefedCol(tablename);	
		List<ComboTree> comboTreeColList = new ArrayList<ComboTree>();
		List<ComboTree> comboTreeFactorList = new ArrayList<ComboTree>();
		ComboTree combotreeFactor1 = new ComboTree();
		combotreeFactor1.setId(tablename);
		combotreeFactor1.setText("本表字段");
		//Iterator<Entry<String,String>> it = refColMap.entrySet().iterator();
		//while(it.hasNext()){
		for(RefedCol col:refedList){
			//Entry<String,String> key = it.next();
			ComboTree combotreeCol = new ComboTree();
			/*combotreeCol.setId(key.getValue());
			combotreeCol.setText(key.getKey());*/
			combotreeCol.setId(col.getColName());
			combotreeCol.setText(col.getColCName());
			if (!col.getDictField().equals(""))
				combotreeCol.setState("closed");
			comboTreeColList.add(combotreeCol);
		}
		combotreeFactor1.setChildren(comboTreeColList);
		comboTreeFactorList.add(combotreeFactor1);

		ComboTree combotreeFactor2 = new ComboTree();
		combotreeFactor2.setId("funlist");
		combotreeFactor2.setText("函数列表");
		Iterator<Entry<String,String>> it2 = FormulaConstant.funMap.entrySet().iterator();
		List<ComboTree> combotreeFunList = new ArrayList<ComboTree>();
		while(it2.hasNext()){
			Entry<String,String> key2 = it2.next();
			ComboTree combotreeCalc = new ComboTree();
			combotreeCalc.setId(key2.getValue());
			combotreeCalc.setText(key2.getKey());
			combotreeFunList.add(combotreeCalc);
		}
		combotreeFactor2.setChildren(combotreeFunList);
		comboTreeFactorList.add(combotreeFactor2);

		ComboTree combotreeFactor3 = new ComboTree();
		combotreeFactor3.setId("sqlcalclist");
		combotreeFactor3.setText("SQL计算器");
		List<SQLCalc> sqlCalcList = formulaServiceImpl.getSQLCalc(tablename);
		/*Iterator<Entry<String,String>> it3 = sqlCalcMap.entrySet().iterator();*/
		List<ComboTree> combotreeCalcList = new ArrayList<ComboTree>();
		String calcID="";
		for(int i=0;i<sqlCalcList.size();i++){
			//Entry<String,String> key3 = it3.next();
			ComboTree combotreeCalc = new ComboTree();
			combotreeCalc.setId(sqlCalcList.get(i).getID());
			combotreeCalc.setText(sqlCalcList.get(i).getName());
			if (sqlCalcList.get(i).getFromItem().equals("1")){
				Map<String,String> itemMap = formulaServiceImpl.getAllItems(sqlCalcList.get(i).getTypeID());
				if (itemMap !=null){
					Iterator<Entry<String,String>> it3 = itemMap.entrySet().iterator();
					List<ComboTree> combotreeCalc3 = new ArrayList<ComboTree>();
					while(it3.hasNext()){
						Entry<String,String> key3 = it3.next();
						ComboTree combotree3 = new ComboTree();
						combotree3.setId(key3.getKey());
						combotree3.setText(key3.getValue());
						combotreeCalc3.add(combotree3);
					}
					combotreeCalc.setChildren(combotreeCalc3);
				}
			}
			combotreeCalcList.add(combotreeCalc);
		}
		combotreeFactor3.setChildren(combotreeCalcList);
		comboTreeFactorList.add(combotreeFactor3);

		rootCombotree.setId("#");
		rootCombotree.setText("表达式要素列表");
		rootCombotree.setChildren(comboTreeFactorList);

		return new ArrayList<ComboTree>(){{add(rootCombotree);}};
	}
	/**
	 * 获取公式选择要素信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getDictColAndItem")
	@ResponseBody
	public List<ComboTree> getDictColAndItem(HttpServletRequest request,final ComboTree rootCombotree) {
		String tablename = request.getParameter("tablename");
		String formulaname = request.getParameter("formulaname");
		if (tablename==null||tablename.equals("")){
			rootCombotree.setId("#");
			rootCombotree.setText("请在公式选择树中选择公式名称！");
			return new ArrayList<ComboTree>(){{add(rootCombotree);}};
		}
		List<DictColAndItem> dictAndItemList = formulaServiceImpl.getDictColAndItem(tablename);
		List<ComboTree> comboTreesCol = null;
		List<ComboTree> comboTreesFormula = null;
		for (int i=0;i<dictAndItemList.size();i++){
			DictColAndItem factorTree = dictAndItemList.get(i);
			if (comboTreesFormula==null){
				comboTreesFormula = new ArrayList<ComboTree>();
			}
			ComboTree combotreeformuname = new ComboTree();
			combotreeformuname.setId(factorTree.getTypeCode());
			combotreeformuname.setText(factorTree.getTypeName());
			comboTreesFormula.add(combotreeformuname);
			if (i==dictAndItemList.size() -1||!factorTree.getFieldName().equals(dictAndItemList.get(i + 1).getFieldName())){
				if (comboTreesCol==null)
					comboTreesCol = new ArrayList<ComboTree>();
				ComboTree combotreeCol = new ComboTree();
				combotreeCol.setId(factorTree.getFieldName());
				combotreeCol.setText(factorTree.getFieldCName());
				combotreeCol.setChildren(comboTreesFormula);
				comboTreesFormula = null;
				comboTreesCol.add(combotreeCol);
			}
		}
		rootCombotree.setId("#");
		rootCombotree.setText("字典信息选择");
		rootCombotree.setChecked(false);
		rootCombotree.setChildren(comboTreesCol);

		return new ArrayList<ComboTree>(){{add(rootCombotree);}};
	}

	/**
	 * 获取公式选择要素信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getAllDictType")
	@ResponseBody
	public List<ComboTree> getAllDictColAndItem(HttpServletRequest request,final ComboTree rootCombotree) {
		boolean leaf = false;
		String nodeid = request.getParameter("id");
		Map tableMap = formulaServiceImpl.getAllDictType(nodeid);
		if (tableMap.size()<=0){
			tableMap = formulaServiceImpl.getAllDictItem(nodeid);
			leaf = true;
		}
		Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();
		List<ComboTree> comboTreesCol = new ArrayList<ComboTree>();
		while (it.hasNext()){
			Entry<String,String> key = it.next();
			ComboTree combotree = new ComboTree();
			combotree.setId(key.getKey());
			combotree.setText(key.getValue());
			if (!leaf)
			combotree.setState("closed");
			comboTreesCol.add(combotree);
		}
		if(nodeid!=null&&!nodeid.equals(""))
			return comboTreesCol;
		rootCombotree.setId("#");
		rootCombotree.setText("字典信息");
		rootCombotree.setChecked(false);
		rootCombotree.setChildren(comboTreesCol);

		return new ArrayList<ComboTree>(){{add(rootCombotree);}};
	}
	/**
	 * 获取公式选择要素信息
	 * @param request
	 * @param tablename
	 * @return
	 */
	@RequestMapping(params = "getTableList")
	@ResponseBody
	public List<ComboTree> getTableList(HttpServletRequest request,final ComboTree rootCombotree) {
		String calcname = request.getParameter("calcname");
		Map tableMap = formulaServiceImpl.getTableList(calcname);
		Iterator<Entry<String,String>> it = tableMap.entrySet().iterator();
		List<ComboTree> comboTreesCol = new ArrayList<ComboTree>();
		while (it.hasNext()){
			Entry<String,String> key = it.next();
			ComboTree combotree = new ComboTree();
			combotree.setId(key.getKey());
			combotree.setText(key.getValue().substring(0, key.getValue().indexOf("^")));
			//if (tablename.indexOf(key.getValue()+",")>=0||tablename.endsWith(key.getValue()))
			if (key.getValue().indexOf("^1")>0)
				combotree.setChecked(true);
			comboTreesCol.add(combotree);
		}
		rootCombotree.setId("#");
		rootCombotree.setText("计算器适用的表单范围");
		rootCombotree.setChecked(false);
		rootCombotree.setChildren(comboTreesCol);

		return new ArrayList<ComboTree>(){{add(rootCombotree);}};
	}
	
	private void returnJson(String erro,HttpServletResponse response){
		JSONArray arr2 = new JSONArray();
		JSONObject checkJSON =new JSONObject();
		if (!erro.equals("")){
			checkJSON.put("erro", erro);
			checkJSON.put("success", "false");
		} else
		{
			checkJSON.put("erro","");
			checkJSON.put("success", "true");
		}
		arr2.add(checkJSON);		
		this.writeJson(response, ContentType.JSON, 0, arr2);
	}
	private void writeJson(HttpServletResponse response,ContentType ct,int resultCode,JSONArray jsonArray){
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType(ct.getName());
			response.setHeader("Result-Code", String.valueOf(resultCode));
			StringBuffer sb = new StringBuffer(100);
			for (int i=0;i<jsonArray.size();i++){
				sb.append(JSON.toJSONString(jsonArray.get(i), SerializerFeature.DisableCircularReferenceDetect));//URLEncoder.encode(JSON.toJSONString(json, SerializerFeature.DisableCircularReferenceDetect),"UTF-8");
				if(i<jsonArray.size()-1)
					sb.append(",");
			}
			String jsonstr = sb.toString();
			response.setContentLength(jsonstr.getBytes().length);
			if(resultCode != 0){
				response.setHeader("errorMessage", URLEncoder.encode(((JSONObject)jsonArray.get(0)).getString("msg"),"UTF-8"));// 对响应头信息中文编码
			}else {
				response.getWriter().write("["+jsonstr+"]");
			}
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public enum ContentType{
		HTML {public String getName(){return "text/html;charset=UTF-8";}},
		JSON {public String getName(){return "application/json;charset=UTF-8";}},
		PLAIN {public String getName(){return "text/plain;charset=UTF-8";}},
		XML {public String getName(){return "text/xml;charset=UTF-8";}},
		FILE{public String getName(){return "application/x-msdownload";}},
		IMAGE{public String getName(){return "image/jpeg";}};
		public abstract String getName();
	}
}
