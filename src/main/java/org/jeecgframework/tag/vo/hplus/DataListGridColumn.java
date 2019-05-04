package org.jeecgframework.tag.vo.hplus;
/**
 * 
 * 类描述：列表字段模型
 * 
 * @date： 
 * @version 1.0
 */
public class DataListGridColumn {
	protected String title;//表格列名
	protected String field;//数据库对应字段
	protected Integer width;//宽度
	protected boolean hidden;//是否隐藏
	protected String rec;//记录列从1开始
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
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
