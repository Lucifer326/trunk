package org.jeecgframework.tag.core.hplus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.engine.FreemarkerHelper;

@SuppressWarnings("serial")
public class TreeTag extends TagSupport{
	
	private String type = "jstree";
	private String id;
	private String url;
	private boolean checkBox = false;
	private String style = "margin-top:30px;";
	private String treeClass = "ztree";
	private boolean isChecked = false;
	private boolean contextmenu = false;
	private String contextmenuFunction;

	public String getContextmenuFunction() {
		return contextmenuFunction;
	}

	public void setContextmenuFunction(String contextmenuFunction) {
		this.contextmenuFunction = contextmenuFunction;
	}

	public boolean isContextmenu() {
		return contextmenu;
	}

	public void setContextmenu(boolean contextmenu) {
		this.contextmenu = contextmenu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public String getTreeClass() {
		return treeClass;
	}

	public void setTreeClass(String treeClass) {
		this.treeClass = treeClass;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public boolean isCheckBox() {
		return checkBox;
	}

	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}

	public String getUrl() {
		return url;
	}
	
	public String getId() {
		return id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(tree1());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	private StringBuffer tree(){
		StringBuffer sb = new StringBuffer();
		sb.append(" <ul id='"+id+"' class='ztree' style='margin-top: 30px;'></ul> ");
		sb.append(" <script type='text/javascript' src='plug-in/tag/treeTag/treeTag.js'></script> ");
		sb.append(" <script type='text/javascript'>$(function(){initTree('"+id+"','"+url+"');});</script> ");
		return sb;
	}
	
	private String tree1(){
		FreemarkerHelper viewEngine = new FreemarkerHelper();
		Map<String,Object> treeData = new HashMap<String,Object>();
		treeData.put("tree_type", type);
		treeData.put("tree_id", id);
		treeData.put("tree_url", url);
		treeData.put("tree_style", style);
		treeData.put("tree_class", treeClass);
		treeData.put("tree_checkBox", checkBox);
		treeData.put("tree_isChecked", isChecked);
		treeData.put("tree_contextmenu", contextmenu);
		treeData.put("tree_contextmenuFunctions", parseContextmenuFunction());
		String html = viewEngine.parseTemplate("/tag/treeTag/treeTag.ftl", treeData);
		return html;
	}
	
	private List<String[]> parseContextmenuFunction(){
		List<String[]> list = new ArrayList<String[]>();
		if(StringUtil.isNotEmpty(contextmenuFunction)){
			String[] functions = contextmenuFunction.split(";");
			for (String string : functions) {
				String[] function1 = string.split(":");
				list.add(function1);
			}
		}
		return list;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}
	
}
