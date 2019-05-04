package org.jeecgframework.tag.core.hplus;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 类描述：下拉树形菜单
 * 
 * @author: 张代浩 @date： 日期：2012-12-7 时间：上午10:17:45
 * @version 1.0
 */
public class ComboTreeTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String id;// ID
	protected String name;// 控件名称
	protected String url;// 远程数据
	protected String width;// 宽度
	private boolean hasLabel = false;// 是否显示label
	private String lable;// label值
	protected String value;// 控件值
	private String onSelect;// 选择事件
	private boolean multiple = false;// 是否多选

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

		StringBuffer sb = new StringBuffer();

		width = (width == null) ? "140" : width;

		sb.append("<script type=\"text/javascript\">" + "$(function() { " + "$(\'#" + id + "\').combotree({"
				+ "url :\'comboTreeController.do?comboTreeGetData&dataUrl=" + url + "\'," 
				+ "width :\'" + width +"\',"
				+ "onBeforeExpand:function(node){ " 
			    + "$(this).tree('options').url='comboTreeController.do?comboTreeGetData&dataUrl="+url+"&parentid='+node.id;"	
				+ "},");
			    
				if (onSelect != null) {
					sb.append("onSelect:function(node){");
					sb.append("return " + onSelect);
					
					if (onSelect.indexOf("(") < 0) {
						sb.append("(node);");
					}
					
					sb.append("},\n");
				}
				
				/*sb.append("onLoadSuccess:function(row,data){ " 
				+"for(var i=0;i<data.length;i++){"
				+"var node=$(\'#"+id+"\').combotree('tree').tree('find',data[i].id);"
				+ "var t = $(\'#"+id+"\').combotree('tree');"	
				+"t.tree('expand',node.id)"
				+ "}"   
				+ "},");*/
		
		sb.append("multiple:" + multiple + "");
		sb.append("});");
		sb.append("});");
		
		sb.append("</script>");
		if (hasLabel) {

			sb.append("<label class=\"labelClass\" >");

			if (StringUtils.isNotBlank(lable)) {

				sb.append(this.lable + ":");
			}
			sb.append("</label>");
		}
		sb.append("<input type=\"text\" name=\""+name +"\" id=\""+id +"\"");

		if (value !=null && value !="") {
			sb.append(" value= '"+value+"' ");
		}
		sb.append(">");
		return sb;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public boolean isHasLabel() {
		return hasLabel;
	}

	public void setHasLabel(boolean hasLabel) {
		this.hasLabel = hasLabel;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

	public String getOnSelect() {
		return onSelect;
	}

	public void setOnSelect(String onSelect) {
		this.onSelect = onSelect;
	}

}
