package org.jeecgframework.tag.core.hplus;

import org.jeecgframework.core.util.MutiLangUtil;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * 类描述：数据表格控件列表字段处理类
 * 
 * 
 * @date： 日期：
 * @version 1.0
 */
public class DataListGridColumnTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	protected String title;
	protected String field;
	protected Integer width;
	protected boolean hidden=false;
	private String langArg;
	protected String rec;//记录行号从1开始
	
	
	
	public int doEndTag() throws JspTagException {
		title = MutiLangUtil.doMutiLang(title, langArg);
		
		Tag t = findAncestorWithClass(this, DataListGridTag.class);
		DataListGridTag parent = (DataListGridTag) t;
		parent.setColumn(title,field,width,hidden,rec);
		return EVAL_PAGE;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getLangArg() {
		return langArg;
	}
	public void setLangArg(String langArg) {
		this.langArg = langArg;
	}
	public String getTitle() {
		return title;
	}
	public String getField() {
		return field;
	}
	public Integer getWidth() {
		return width;
	}
	public boolean isHidden() {
		return hidden;
	}
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
	public String getRec() {
		return rec;
	}
	public void setRec(String rec) {
		this.rec = rec;
	}	
	
}
