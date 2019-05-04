package org.jeecgframework.tag.comdesign.entity;

/**
 * 下拉框控件级联选择时根据typegroupcode查询时返回json问题使用的实体类
 * @author Administrator
 *
 */
public class OptionVo {

	/**
	 * select option 的value属性
	 */
	private String id;
	
	/**
	 * select option 显示内容
	 */
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	
	
}
