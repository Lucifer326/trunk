package org.jeecgframework.web.formula.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.EhcacheUtil;
import org.jeecgframework.web.formula.analysis.FormulaException;
import org.jeecgframework.web.formula.compute.Calculate;
import org.jeecgframework.web.formula.compute.CalculateI;
import org.jeecgframework.web.formula.dto.RefedCol;
import org.jeecgframework.web.formula.service.FormulaServiceI;
import org.jeecgframework.web.system.pojo.base.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 公式计算接口类
 * 
 * @author Horse_little
 * @date 2017年7月31日 下午10:32:17
 */
@Controller
@RequestMapping("/calcInterface")
public class CalcInterface {
	@Autowired
	private Calculate colaculate; 
	@Autowired
	private FormulaServiceI formulaServiceImpl;
	
	@RequestMapping(params = "calc")
	public String list(Model model, HttpServletRequest request) {
		System.out.println("formulacalc/calc");
		
		List<RefedCol> refcolList = new ArrayList<RefedCol>();
		if(EhcacheUtil.get("dictCache", "test")==null){
			refcolList = formulaServiceImpl.getRefedCol("TMP");
		}
		else{
			refcolList =(List<RefedCol>) EhcacheUtil.get("dictCache","test");
		}
		EhcacheUtil.put("dictCache","test", refcolList);
		return "";
		
		/*Map arg = new HashMap();
		arg.put("c", "'女'");
		arg.put("d", "1.01");
		arg.put("e", "12");
		arg.put("f", "123");
		Map args = new HashMap();
		args.put("测试公式1", arg);
		args.put("测试公式2", arg);*/
		
		/*try {
			//colaculate.getFormulaVal("select !d!+!e!+!f! ", arg);//
			//String val = this.SFormulaComputeAllArg("TMP", "G", arg);
			//Map val = this.MFormulaComputeAllArg("TMP", args);
			//String[] vals=this.GetFromulaComputeCol("TMP");
			Map val = this.GetFromulaComputeAllArg("TMP");
			System.out.println("公式：select !d!+!e!+!f! 的计算值是："+val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "formula/define";*/
	}
	/*
	作者：Horse_Little
	日期：2017-08-01
	SFormulaComputeAllArg
	功能描述：实现对单一公式的计算功能，参与公式计算的所有涉及参数（系统保留字段除外）均通过接口函数进行输入。
	返回值：String
	参数列表：
	String	TableName //表单物理名称
	String	FormulaName//公式名称
	Map   MapArg//参与公式计算的参数键值对；Key是参与计算列的名称；Object是参与计算的数值，类型String
	 */
	@RequestMapping(params = "SFormulaComputeAllArg")
	public String SFormulaComputeAllArg(String	TableName,
			String	FormulaName,
			Map   MapArg){
		String val = null;
		try {
			String formulaExpress = formulaServiceImpl.getFormulaExp(TableName,FormulaName);
			val = colaculate.getFormulaVal(formulaExpress, MapArg,TableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}
	
	@RequestMapping(params = "GetFromulaComputeAllArg")
	public Map GetFromulaComputeArg0(String	TableName,String formulaname){		
		try {
			Map valMap = this.formulaServiceImpl.GetFromulaComputeArg(TableName,formulaname);
			return valMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	@RequestMapping(params = "SFormulaComputeAllArg2")
	@ResponseBody
	public AjaxJson SFormulaComputeAllArg2( HttpServletRequest request){
		String val = "";
		String tableName = request.getParameter("tablename");
		String formulaName = request.getParameter("formulaname");
		Map MapArg = new HashMap();
		Enumeration pNames=request.getParameterNames();
		while(pNames.hasMoreElements()){
		    String name=(String)pNames.nextElement();
		    if (name.equals("tablename")||name.equals("formulaname")||name.equals("SFormulaComputeAllArg2"))
		    	continue;
		    String value=request.getParameter(name);
		    //System.out.println(name + "=" + value);
		    MapArg.put(name, value);
		}
		
		AjaxJson j = new AjaxJson();
		
		try {
			val = this.SFormulaComputeAllArg(tableName, formulaName, MapArg);
			j.setMsg(val);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return j;
	}
	/*
	作者：Horse_Little
	日期：2017-08-01
	MFormulaComputeAllArg 
	功能描述：实现对多个公式的计算功能，参与公式计算的所有参数值作为函数参数进行输入。
	返回值：Map//Key值为公式列名称，Object为对应的计算结果，类型为String。
	参数列表：
	String	TableName //表单物理名称
	Map	MapFormulaInfo//公式列信息，Key为公式名称，Object为Map类型，其对应的Key为参与公式计算的字段名称，Object为其对应的数值。
   */
	@RequestMapping(params = "MFormulaComputeAllArg")
	public Map MFormulaComputeAllArg(String	TableName,
			Map   MapArg){
		Map valMap = new HashMap();
		try {
			Iterator<Entry<String,Map>> it = MapArg.entrySet().iterator();
			String formulaCol = null;
			String val=null;
			while (it.hasNext()){
				Entry<String,Map> key = it.next();
				formulaCol = key.getKey();
				Map argMa= key.getValue();
				val = this.SFormulaComputeAllArg(TableName,formulaCol, argMa);
				valMap.put(formulaCol, val);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valMap;
	}
	
	/*作者：Horse_Little
	日期：2017-08-01
	GetFromulaComputeCol 
	功能描述：获取当前窗口的所有公式列信息。
	返回值：String[]//当前窗口的公式列名称
	参数列表：
	String	TableName //表单名称
	*/
	@RequestMapping(params = "GetFromulaComputeCol")
	public String[] GetFromulaComputeCol(String	TableName){
		String[] formulaName = null;
		try {
			formulaName = this.formulaServiceImpl.GetFromulaComputeCol(TableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formulaName;
	}
	
	/*作者：Horse_Little
	日期：2017-08-01
	GetFromulaComputeAllArg 
	功能描述：获取当前窗口的所有公式列的所有参数列名称。
	返回值：Map//Key为公式名称，Value为其对应的所有参数列列表，类型为Map,Key值为引用列名称，Value为列类型
	参数列表：
	String	TableName//表单名称
	*/
	@RequestMapping(params = "GetFromulaComputeAllArg")
	public Map GetFromulaComputeAllArg(String	TableName){		
		try {
			Map valMap = this.formulaServiceImpl.GetFromulaComputeAllArg(TableName);
			return valMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping(params = "GetFromulaComputeArg")
	@ResponseBody
	public AjaxJson GetFromulaComputeArg( HttpServletRequest request){
		String tableName = request.getParameter("tablename");
		String formulaname = request.getParameter("formulaname");
		AjaxJson j = new AjaxJson();
		Map valMap = this.formulaServiceImpl.GetFromulaComputeArg(tableName,formulaname);
		Iterator<Entry<String,String>> it = valMap.entrySet().iterator();
		StringBuffer sb = new StringBuffer(100);
		while (it.hasNext()){
			Entry<String,String> key = it.next();
			sb.append(key.getKey()).append(",");
		}
		j.setMsg(sb.toString());
		return j;
	}
	
	/*作者：Horse_Little
	日期：2017-08-01
	GetFromulaComputeCol 
	功能描述：获取当前窗口的所有公式列信息。
	返回值：String[]//当前窗口的公式列名称
	参数列表：
	String	TableName //表单名称
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
		if (originalContent.indexOf(",")>=0)
			originalContent = originalContent.substring(0, originalContent.indexOf(","));
		if (originalContent!=null)
			j.setMsg(originalContent);
		return j;
	}
}
