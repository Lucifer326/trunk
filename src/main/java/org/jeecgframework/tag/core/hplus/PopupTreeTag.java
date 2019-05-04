package org.jeecgframework.tag.core.hplus;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.oConvertUtils;

/**
 * 
 * 树形控件选择弹出框
 * 
 * @author: zhouKuangZue
 * @date	2017-12-21
 * @version 1.0
 */
public class PopupTreeTag extends TagSupport {

	private static final long serialVersionUID = 1;
	private String name;    		// <input type=”hidden”/>元素name属性值
	private String nameVal;			// <input type=”text”/>元素value属性值
	private String hiddenNameVal; 	// <input type=”hidden”/>元素value属性值
	private String inputClass;     			//输入框css class
	private String inputStyle;				//输入框自定义style
	private String dialogWidth; 			//弹出窗口宽度
	private String dialogHeight; 			//弹出窗口高度
	private boolean readonly= true;	// 只读属性
	private boolean hasLabel = false;       //是否显示lable,默认不显示
	private String lable;			   		// 标签
	private String dialogTitle;			   	// 标题
	private String selectType = "checkbox";	//选择弹窗树的类型：radio 单选，checkbox 多选 ，默认为 checkbox 多选
	private String conditon;	//树形显示数据      为空时 从根节点加载所有，有参数 根据条件加载数据
	private String dataProvider;    //数据源	项目组自己定义
	private boolean cascadeCheck = true;  //是否支持级联选择	true 级联选中,false 不级联续中, 默认为true
	private boolean checkBranch = true;  //是否允许选择非叶子节点   true 允许,false 不允许, 默认为true
	private boolean async = true;	//数据加载方式
	
	
	public String getNameVal() {
		return nameVal;
	}
	public void setNameVal(String nameVal) {
		this.nameVal = nameVal;
	}
	public String getHiddenNameVal() {
		return hiddenNameVal;
	}
	public void setHiddenNameVal(String hiddenNameVal) {
		this.hiddenNameVal = hiddenNameVal;
	}
	public String getConditon() {
		return conditon;
	}
	public void setConditon(String conditon) {
		this.conditon = conditon;
	}
	public String getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}
	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	public String getDialogTitle() {
		return dialogTitle;
	}
	public void setDialogTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public boolean isHasLabel() {
		return hasLabel;
	}
	public void setHasLabel(boolean hasLabel) {
		this.hasLabel = hasLabel;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInputClass() {
		return inputClass;
	}
	public void setInputClass(String inputClass) {
		this.inputClass = inputClass;
	}
	public String getInputStyle() {
		return inputStyle;
	}
	public void setInputStyle(String inputStyle) {
		this.inputStyle = inputStyle;
	}
	public String getDialogWidth() {
		return dialogWidth;
	}
	public void setDialogWidth(String dialogWidth) {
		this.dialogWidth = dialogWidth;
	}
	public String getDialogHeight() {
		return dialogHeight;
	}
	public void setDialogHeight(String dialogHeight) {
		this.dialogHeight = dialogHeight;
	}
	public boolean isCascadeCheck() {
		return cascadeCheck;
	}
	public void setCascadeCheck(boolean cascadeCheck) {
		this.cascadeCheck = cascadeCheck;
	}
	public boolean isCheckBranch() {
		return checkBranch;
	}
	public void setCheckBranch(boolean checkBranch) {
		this.checkBranch = checkBranch;
	}
	public boolean isAsync() {
		return async;
	}
	public void setAsync(boolean async) {
		this.async = async;
	}
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		JspWriter out = null;
		try {
			out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				out.clear();
				out.close();
			} catch (Exception e2) {
			}
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {
		
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isBlank(name)) {
			name = "treeNames"; // 默认<input type=”text” id = “treeNames” name = “treeNames”/>
		}
		if(StringUtils.isBlank(lable)){
			lable = "数据选择";
		}
		if(StringUtils.isBlank(dialogTitle)){
			dialogTitle = "数据选择";
		}
		if(StringUtils.isBlank(inputClass)){
			inputClass = "Validform_label";   //默认input Class样式
		}
		if(StringUtils.isBlank(inputStyle)){
			inputStyle = "height:30px;";   //默认input css样式
		}
		
		if(StringUtils.isBlank(dialogWidth)){
			dialogWidth = "400";
		}
		
		if(StringUtils.isBlank(dialogHeight)){
			dialogHeight = "500";
		}
		if(hasLabel && oConvertUtils.isNotEmpty(lable)){
			sb.append(lable + "：");
		}
		sb.append("<input ");
		if(readonly){
			sb.append("readonly=\"readonly\" ");
		}
		sb.append("type=\"text\" id=\"" + name +"Show\" name=\"" + name +"Show\" class="+inputClass+" style=\""+inputStyle+"\";");//onclick=\""+name+"OpenTreeSelect()\"
		if(StringUtils.isNotBlank(nameVal)){
			sb.append(" value=\""+nameVal+"\"");
		}
		sb.append(" />");
		String Ids = "";		
		sb.append("<input id=\"" + name + "\" name=\"" + name + "\" type=\"hidden\" ");
		if(StringUtils.isNotBlank(hiddenNameVal)){
			sb.append(" value=\""+hiddenNameVal+"\"");
			Ids = "&Ids=" + hiddenNameVal;
		}
		
		sb.append("/>");
		sb.append("	<button class=\"btn btn-primary btnh\" id=\"treeSearch\" type=\"button\" onclick=\""+name+"OpenTreeSelect()\">");
		sb.append("<i class=\"fa fa-search\"></i>&nbsp;选择</button>");
		
		String commonConfirm = MutiLangUtil.getMutiLangInstance().getLang("common.confirm");
		String commonCancel = MutiLangUtil.getMutiLangInstance().getLang("common.cancel");
				
		sb.append("<script type=\"text/javascript\">");
		sb.append("function "+name+"OpenTreeSelect() {");
		sb.append("    $.dialog({content: 'url:popupTreeController.do?popupTree" + Ids 
				+ "&selectType="+selectType+"&dataProvider="+dataProvider+"&checkBranch="+checkBranch+"&cascadeCheck="+cascadeCheck
				+"&async="+async+"',"
				+ "zIndex: 99999, title: '" + dialogTitle + "', lock: true, width: '" +dialogWidth+"px', "
				+ "height: '" + dialogHeight + "px', opacity: 0.4, button: [");
		sb.append("       {name: '" + commonConfirm + "', callback: "+name+"CallbackTreeSelect, focus: true},");
		sb.append("       {name: '" + commonCancel + "', callback: function (){}}");
		sb.append("   ]}).zindex();");
		sb.append("}");
		sb.append("function "+name+"CallbackTreeSelect() {");
		sb.append(" var iframe = this.iframe.contentWindow;");
		sb.append(" var treeObj = iframe.$.fn.zTree.getZTreeObj(\"treeSelect\");");
		sb.append(" var nodes = treeObj.getCheckedNodes(true);");
		sb.append(" if(nodes.length>0){");
		sb.append(" var ids='',names='';");
		sb.append("for(i=0;i<nodes.length;i++){");
		sb.append(" var node = nodes[i];");
		sb.append(" ids += node.id+',';");
		sb.append(" names += node.name+',';");
		sb.append("}");
		sb.append(" $('#" + name + "Show').val(names);");
		sb.append(" $('#" + name + "Show').blur();");		
		sb.append(" $('#" + name + "').val(ids);");
		sb.append("}");
		sb.append("}");
		sb.append("</script>");
		return sb;
	}
}
