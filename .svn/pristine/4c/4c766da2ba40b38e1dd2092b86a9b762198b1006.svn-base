package org.jeecgframework.tag.core.hplus;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.tag.vo.hplus.DataListGridColumn;
/**
 * 
 * 类描述：DATALISTGRID标签处理类
 * 
 * @author 
 * @version 1.0
 */
@SuppressWarnings({"serial"})
public class DataListGridTag extends TagSupport {
	
	protected String tableName;//表名称
	protected String name;// 表格标示
	protected String fields = "";// 显示字段
	protected String idField = "id";// 主键字段
	protected List<DataListGridColumn> columnList = new ArrayList<DataListGridColumn>();// 列表操作显示
	protected String title;// 表格标题
	public boolean pagination = true;// 是否显示分页
	private boolean checkbox = false;// 是否显示复选框
	private String width;
	private String height;
	private boolean autoLoadData = true; // 列表是否自动加载数据
	
	public int pageSize = 10;
	private String sortName;// 定义的列进行排序
	private String sortOrder = "asc";// 定义列的排序顺序，只能是"递增"或"降序".
	private String rowStyler;// rowStyler函数
	
	
	
	public int doStartTag() throws JspTagException {
		// 清空资源
		columnList.clear();
		fields = "";
		return EVAL_PAGE;
	}
	
	
	public int doEndTag() throws JspException {
		try {
			
			JspWriter out = this.pageContext.getOut();
				out.print(bootstrapdatatables().toString());
				out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public StringBuffer bootstrapdatatables(){
		
		String grid = "";
		StringBuffer sb = new StringBuffer();
		width = (width == null) ? "auto" : width;
		height = (height == null) ? "auto" : height;
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("$(function(){  storage=$.localStorage;if(!storage)storage=$.cookieStorage;\n");
		//sb.append(this.getNoAuthOperButton());
		grid = "bootstrapTable";
		sb.append("$(\'#" + name + "\').bootstrapTable({\n");
		sb.append("idField: '" + idField + "',\n");
		sb.append("uniqueId: '" + idField + "',\n");
		sb.append("dataType: 'json', \n");
		sb.append("method:'get',\n");
		sb.append("showColumns:true,\n");

		sb.append("showRefresh:true,\n");

		//sb.append("showToggle:"+showToggle+",\n");
		//sb.append("sortable:"+sortable+",\n");
		
		sb.append("iconSize: 'outline',\n");

		sb.append("icons: { \n");

		sb.append(" refresh: 'glyphicon-repeat',\n");

		sb.append(" toggle:'glyphicon-list-alt',\n");

		sb.append(" columns: 'glyphicon-list'\n");

		sb.append("  },\n");

		if (title != null) {
			sb.append("title: \'" + title + "\',\n");
		}
		if(autoLoadData)
			   sb.append("url:\'datalistgridController.do?datagrid&tableName=" + tableName + "&field=" + fields + "\',\n");
			else
				sb.append("url:\'',");
		if(StringUtils.isNotEmpty(rowStyler)){
			sb.append("rowStyle: function(index,row){ return "+rowStyler+"(index,row);},");
		}
		
		sb.append("sidePagination:\"server\",\n");
		sb.append("pageSize: " + pageSize + ",\n");
		sb.append("pagination:" + pagination + ",\n");
		sb.append("pageIndex:1,\n");
		sb.append("pageList:[" + pageSize * 1 + "," + pageSize * 2 + "," + pageSize * 3 + "],\n");
		if (StringUtils.isNotBlank(sortName)) {
			sb.append("sortName:'" + sortName + "',\n");
		}
		sb.append("sortOrder:'" + sortOrder + "',\n");
		sb.append("rownumbers:true,");
		sb.append("singleSelect:" + !checkbox + ",\n");

		sb.append("striped:true,showFooter:false,\n");
		sb.append("columns:[[{checkbox: true},\n");
		this.getField(sb);
		sb.append("]],\n");
		
		
		sb.append("});\n");
		sb.append("try{restoreheader();}catch(ex){}\n");
		sb.append("});\n");
		
		sb.append("</script>\n");
		
		
		// 开始表格显示区
		sb.append("<div class=\"row\">\n");
		sb.append("<div class=\"col-sm-12\">\n");
		sb.append("<div class=\"ibox float-e-margins\">\n");
		sb.append("<div class=\"ibox-content\">\n");
		sb.append("<div class=\"example-wrap\">\n");

		sb.append("<table width=\"100%\"   id=\"" + name + "\" data-mobile-responsive=\"true\" ></table>\n");
		sb.append("</div>\n");

		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");

		// 表格显示区结束
				
		return sb;
		
	}

	/**
	 * 拼接字段 普通列
	 * 
	 * @param sb
	 * 
	 */
	 protected void getField(StringBuffer sb){
		 getField(sb,1);
	 }
	 
		/**
		 * 拼接字段
		 * 
		 * @param sb
		 * @frozen 0 冰冻列 1 普通列
		 */
		protected void getField(StringBuffer sb,int frozen) {
			// 复选框
			if (checkbox&&frozen==0) {
				sb.append("{field:\'ck\',checkbox:\'true\'},");
			}
			int i = 0;
			for (DataListGridColumn column : columnList) {
				i++;
				String field;
				
				field = column.getField();
				sb.append("{field:\'" + field + "\',title:\'" + column.getTitle() + "\'");
				if(column.getWidth() != null){
					sb.append(",width:"+column.getWidth());
				}
				if (column.isHidden()) {
					sb.append(",visible:false");
				}
				sb.append("}");
					// 去除末尾,
				if (i < columnList.size()) {
					sb.append(",");
				}
			}
		}
	 

	 
	
/**
 * 
 * setColumn(设置字段)
 * 
 * @param title
 * @param field
 * @param width
 */
public void setColumn(String title, String field, Integer width,boolean hidden,String rec) {
	
	DataListGridColumn dataListGridColumn = new DataListGridColumn();
	
	dataListGridColumn.setTitle(title);
	dataListGridColumn.setField(field);
	dataListGridColumn.setWidth(width);	
	dataListGridColumn.setHidden(hidden);
	dataListGridColumn.setRec(rec);
	
	columnList.add(dataListGridColumn);
	if (field != "opt") {
		fields += field + ",";
	}
}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isPagination() {
		return pagination;
	}
	public void setPagination(boolean pagination) {
		this.pagination = pagination;
	}
	public boolean isCheckbox() {
		return checkbox;
	}
	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
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

	public boolean isAutoLoadData() {
		return autoLoadData;
	}

	public void setAutoLoadData(boolean autoLoadData) {
		this.autoLoadData = autoLoadData;
	}

	public List<DataListGridColumn> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<DataListGridColumn> columnList) {
		this.columnList = columnList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getIdField() {
		return idField;
	}

	public void setIdField(String idField) {
		this.idField = idField;
	}

	public String getRowStyler() {
		return rowStyler;
	}

	public void setRowStyler(String rowStyler) {
		this.rowStyler = rowStyler;
	}
	
}
