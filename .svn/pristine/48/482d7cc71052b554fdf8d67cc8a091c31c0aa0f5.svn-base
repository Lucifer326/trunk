package org.jeecgframework.tag.core.hplus;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * 类描述：多功能按钮标签
 * 
 * 
 * @date： 日期：2018-3-5 时间：上午9：17
 * @version 1.0
 */
public class MultiButtonTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String field; //按钮唯一标识
	private String name;//按钮名称
	private String buttonStyle;//按钮自定义样式
	private String multibuttonType;//按钮类型：查询、弹出窗口、新增、编辑、删除（批量删除）、导出、导入、超链接
	
	private String tableId;//表id
	private String selectConditionList;
	
	
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public StringBuffer end() {
		
		if(StringUtils.isBlank(name)){
			this.name="多功能按钮";
		}
		if(StringUtils.isBlank(buttonStyle)){
			this.buttonStyle="btn btn-primary btnh";
		}
		
		StringBuffer sb = new StringBuffer();
		
		if(multibuttonType.equals("selectButton")){//查询
			//遍历数组取出 控件id 然后根据控件id在页面上 查找对应的元素对象，获取其name 和value，放进数组中。
			
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("var datagridUrl;\n");
			sb.append("var condiArr=new Array();\n");
			sb.append("var conditionList = \"\";\n");
			sb.append("var cId;\n");
			sb.append("var condiName;\n");
			sb.append("var condiVal;\n");
			
			
			sb.append("setTimeout(function(){\n");//延迟获取所对应的数据表格的url
			sb.append("datagridUrl=$('#"+tableId+"').bootstrapTable('getOptions').url;\n");
			sb.append("},1000);\n");
			sb.append("");
			
			sb.append("conditionList = eval("+selectConditionList+");\n");//把查询条件的json串转为对象。
			
			
			sb.append("function "+field+"Search(){\n");//查询函数
			sb.append("condiArr.splice(0,condiArr.length);");//清空数组
			sb.append("for(var c in conditionList){\n");
			sb.append("cId = conditionList[c].controlId;\n");
			sb.append("console.log(cId);\n");
			
			sb.append("var tcid = '#'+cId\n");
			sb.append("condiName=$(tcid).attr('name');\n");
			sb.append("condiVal=$(tcid).val();\n");
			sb.append("condiArr.push({\n");
			sb.append(" controlId:cId,\n");
			sb.append(" relation:conditionList[c].relation,\n");
			sb.append("condiName:condiName,\n");
			sb.append("operator:conditionList[c].operator,\n");
			sb.append("condiVal:condiVal,\n");
			sb.append("resultsort:conditionList[c].resultsort,\n");
			sb.append("});\n");
			sb.append("}\n");
		     
			
			sb.append("$.ajax({\n");
			sb.append("async : false,\n");
			sb.append("cache : false,\n");
			sb.append(" type : 'POST',\n");
			sb.append("url : datagridUrl,\n");// 请求的action路径
			sb.append(" dataType: \"json\",\n");
			sb.append("data:{\"condiArr\":JSON.stringify(condiArr)},\n");
			sb.append("error : function() {\n"); // 请求失败处理函数
			sb.append("},\n");
			sb.append("success : function(data) {\n");//成功函数     
			sb.append("$('#"+tableId+"').bootstrapTable('load',data);");
			sb.append("}\n");
			sb.append(" });\n");
			
			sb.append("}\n");
			sb.append("</script>\n");
			
			sb.append("<button id=\""+field+"\" class=\""+buttonStyle+"\" onClick=\""+field+"Search();\">");
			
		}else if(multibuttonType.equals("")){//新增
			
		}
		sb.append(""+name+"</button>");
		
			return sb;
		}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getButtonStyle() {
		return buttonStyle;
	}
	public void setButtonStyle(String buttonStyle) {
		this.buttonStyle = buttonStyle;
	}
	
	public String getMultibuttonType() {
		return multibuttonType;
	}
	public void setMultibuttonType(String multibuttonType) {
		this.multibuttonType = multibuttonType;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getSelectConditionList() {
		return selectConditionList;
	}
	public void setSelectConditionList(String selectConditionList) {
		this.selectConditionList = selectConditionList;
	}
	
}
