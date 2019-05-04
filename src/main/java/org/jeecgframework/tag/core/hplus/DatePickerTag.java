package org.jeecgframework.tag.core.hplus;

import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * 类描述：日期选择标签
 * 
 * 
 * @date： 日期：2018-1-8 时间：下午15:57:30
 * @version 1.0
 */
public class DatePickerTag extends TagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; 
	private String dateFmt ;
	private boolean readOnly= true;
	private boolean showClear= true;
	private boolean hasLabel = true;
	private String defaultVal; // 默认值
	private String lable;
	private String minDate;
	private String maxDate;
	protected String width;// 宽度
	protected String height;// 高度
	private boolean showWeek = true;
	
	
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
		
		
		if(StringUtils.isBlank(width)){//默认宽度
			width = "160";
		}
		
		if(StringUtils.isBlank(height)){//默认高度
			height = "30";
		}
		
		if(StringUtils.isBlank(dateFmt)){//默认日期显示格式yyyy-MM-dd HH:mm:ss
			
			this.dateFmt = "yyyy-MM-dd HH:mm:ss";
		}
		
		if (hasLabel) {
			
			sb.append("<div class=\"\">");//" + divClass + "
			
			if(StringUtils.isNotBlank(lable)){
				sb.append("<label >");
				
				sb.append(this.lable + ":");
				sb.append("</label>");
			}
		}
		sb.append("<input id=\""+name+"\" name=\""+name+"\" type=\"text\"");
			
		if(StringUtils.isNotBlank(height)){
				sb.append(" style=\"height:"+height+"px;");
			}
		
		if(StringUtils.isNotBlank(width)){
			sb.append("width:"+width+"px\"");
		}
		
		if(readOnly){//只读属性
			sb.append(" readonly=\"readonly\"");
		}
		sb.append("class=\"Wdate\" onClick=\"WdatePicker(");
		
		//日期显示的格式	
		sb.append("{dateFmt:\'"+dateFmt+"\',");
		
		if(!showClear){//是否显示清空按钮
			
			sb.append("isShowClear:"+showClear+",");
		}
		if(StringUtils.isNotBlank(minDate)){//设置最小日期
			
			sb.append("minDate:\'"+minDate+"\',");
		}
		if(StringUtils.isNotBlank(maxDate)){//设置最大日期
			
			sb.append("maxDate:\'"+maxDate+"\',");
		}
		if(showWeek){//是否显示周
			
			sb.append("isShowWeek:"+showWeek+",");
		}
								
			sb.append("})\"");
			if(StringUtils.isNotBlank(defaultVal)){//默认值不为空
				
				sb.append(" value ='"+defaultVal+"'");
			}
		sb.append("/>");
		
		if (hasLabel) {
			sb.append("</div>");
		}
		return sb;
		
	}
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDateFmt() {
		return dateFmt;
	}
	public void setDateFmt(String dateFmt) {
		this.dateFmt = dateFmt;
	}
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
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
	public String getMinDate() {
		return minDate;
	}
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	
	public boolean isShowWeek() {
		return showWeek;
	}
	public void setShowWeek(boolean showWeek) {
		this.showWeek = showWeek;
	}
	public String getDefaultVal() {
		return defaultVal;
	}
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
	public boolean isShowClear() {
		return showClear;
	}
	public void setShowClear(boolean showClear) {
		this.showClear = showClear;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	
	
	
	
}
