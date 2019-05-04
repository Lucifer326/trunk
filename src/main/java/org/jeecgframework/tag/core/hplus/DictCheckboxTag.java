package org.jeecgframework.tag.core.hplus;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.jeecgframework.web.system.service.TypegroupServiceI;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * 类描述：多选标签
 * 
 * 
 * @date： 日期：2018-1-15 时间：下午15:57:30
 * @version 1.0
 */
public class DictCheckboxTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String field; //选择表单的Name EAMPLE:<select name="selectName" id = ""
	private String defaultVal; //默认值
	private String divClass; // DIV样式
	private String labelClass; // Label样式
	private boolean hasLabel = true; //是否显示lable
	private String lable; //lable值
	private String displayFormat; //显示格式   横排显示：，竖排显示，默认竖排显示
	private String radioPosition; //单选框位置
	private String typeGroupCode; //数据字典类型
	private String dictTable; //自定义字典表
	private String dictField; //字典的编码值
	private String dictText;  //字典的显示值
	
	@Autowired
	private static SystemService systemService;	
	
	@Autowired
	private static TypeServiceI typeService;
	
	@Autowired
	private static TypegroupServiceI typeGroupService;
	
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
		
		if (StringUtils.isBlank(divClass)) {
			divClass = "form"; // 默认form样式
		}
		if (StringUtils.isBlank(labelClass)) {
			labelClass = "Validform_label"; // 默认label样式
		}
		if (StringUtils.isBlank(displayFormat)) {
			displayFormat = "displayFormat_h"; // 默认显示格式
		}
		if (StringUtils.isBlank(radioPosition)) {
			radioPosition = "radioPosition_q"; // 默认单选框位置
		}
			sb.append("<div class=\"" + divClass + "\">");
		
		
		if (hasLabel) {
			
			sb.append("<label class=\"" + labelClass + "\" >");
			
			if(StringUtils.isNotBlank(lable)){
				
				sb.append(this.lable + ":");
				sb.append("</label>");
			}
		}
		if (dictTable != null) {
			
			List<Map<String, Object>> list = queryDic();	
			for (Map<String, Object> map : list) {
				checkbox(map.get("text").toString(), map.get("field")
						.toString(), sb);
			}
			
		}else{
			typeGroupService = ApplicationContextUtil.getContext().getBean(
					TypegroupServiceI.class);
			TSTypegroup typeGroup = typeGroupService.getDictionaryType(this.typeGroupCode);
			
			//从session中获取用户区划id
			HttpSession session = ContextHolderUtils.getSession();
			String regionId = (String)session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID);
						
			
			typeService = ApplicationContextUtil.getContext().getBean(
					TypeServiceI.class);
		
			
			//字典条目关联区划	根据给出的typegroupcode 和 区划id 查出相应的字典条目					
			List<TSType> types = typeService.getItemsByTypegroupcodeAndRegionId(this.typeGroupCode,regionId);
			
			
			if (typeGroup != null) {
				
				for (TSType type : types) {
					checkbox(type.getTypename(), type.getTypecode(), sb);
				}
			}
		}
		sb.append("</div>");
		return sb;
		
	}
	
	/**
	 * 复选框方法
	 * 
	 * @作者：Alexander
	 * 
	 * @param name
	 * @param code
	 * @param sb
	 */
	private void checkbox(String name, String code, StringBuffer sb) {
		
		String[] values = null;
		
		if(StringUtils.isNotBlank(defaultVal)){
			
			values = this.defaultVal.split(",");
		}
		Boolean checked = false;
		if(values != null){
			
			for (int i = 0; i < values.length; i++) {
				String value = values[i];
				if (code.equals(value)) {
					checked = true;
					break;
				}
				checked = false;
			}
		}
		
		if(displayFormat.equals("displayFormat_s")){//竖排显示
			
			sb.append("<div class=\"" + divClass + "\">");
		}
		
		if(radioPosition.equals("radioPosition_h")){//单选框位置 在前
			sb.append("<label>");
			sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
			sb.append("</label>");
		}
		if(checked){
			sb.append("<input type=\"checkbox\" name=\"" + field
					+ "\" checked=\"checked\" value=\"" + code + "\"");
			if (!StringUtils.isBlank(this.id)) {
				sb.append(" id=\"" + id + "\"");
			}
			sb.append(" />");
		} else {
			sb.append("<input type=\"checkbox\" name=\"" + field
					+ "\" value=\"" + code + "\"");
			if (!StringUtils.isBlank(this.id)) {
				sb.append(" id=\"" + id + "\"");
			}
			sb.append(" />");
		}
		
		if(radioPosition.equals("radioPosition_q")){//单选框位置 在后
			sb.append("<label>");
			sb.append(MutiLangUtil.getMutiLangInstance().getLang(name));
			sb.append("</label>");
		}
		if(displayFormat.equals("displayFormat_s")){//竖排显示
			
			sb.append("</div>");
		}
	}
	

	
	/**
	 * 查询自定义数据字典
	 * 
	 * @作者：zkx
	 */
	private List<Map<String, Object>> queryDic() {
		String sql = "select " + dictField + " as field," + dictText
				+ " as text from " + dictTable;
	       
		systemService = ApplicationContextUtil.getContext().getBean(
				SystemService.class);
		List<Map<String, Object>> list = systemService.findForJdbc(sql);
		return list;
	}
	
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getDefaultVal() {
		return defaultVal;
	}
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
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
	public String getTypeGroupCode() {
		return typeGroupCode;
	}
	public void setTypeGroupCode(String typeGroupCode) {
		this.typeGroupCode = typeGroupCode;
	}
	public String getDictTable() {
		return dictTable;
	}
	public void setDictTable(String dictTable) {
		this.dictTable = dictTable;
	}
	public String getDictField() {
		return dictField;
	}
	public void setDictField(String dictField) {
		this.dictField = dictField;
	}
	public String getDictText() {
		return dictText;
	}
	public void setDictText(String dictText) {
		this.dictText = dictText;
	}
	public String getDivClass() {
		return divClass;
	}
	public void setDivClass(String divClass) {
		this.divClass = divClass;
	}
	public String getLabelClass() {
		return labelClass;
	}
	
	public void setLabelClass(String labelClass) {
		this.labelClass = labelClass;
	}
	public String getDisplayFormat() {
		return displayFormat;
	}
	public void setDisplayFormat(String displayFormat) {
		this.displayFormat = displayFormat;
	}
	public String getRadioPosition() {
		return radioPosition;
	}
	public void setRadioPosition(String radioPosition) {
		this.radioPosition = radioPosition;
	}
	
	
}
