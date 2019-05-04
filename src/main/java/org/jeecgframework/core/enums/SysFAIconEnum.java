package org.jeecgframework.core.enums;

import org.jeecgframework.core.util.StringUtil;

/**
 * Font Awesome样式类型
 *
 * @author zhoujf
 */
public enum SysFAIconEnum {
	
	/*	default_icon("default","icon-list-alt", "默认"),
	back_icon("back","icon-briefcase", "返回"),
	pie_icon("pie","icon-bar-chart", "小饼状图"),
	pictures_icon("pictures","icon-picture", "图片"),
	pencil_icon("pencil","icon-edit", "笔"),
	map_icon("map","icon-globe", "小地图"),
	group_add_icon("group_add","icon-group", "组"),
	calculator_icon("calculator","icon-desktop", "计算器"),
	folder_icon("folder","icon-list","文件夹");*/

	default_icon("default","fa fa-archive", "默认"),
	back_icon("back","fa fa-reply", "返回"),
	pie_icon("pie","fa fa-bar-chart-o", "小饼状图"),
	pictures_icon("pictures","fa fa-picture-o", "图片"),
	pencil_icon("pencil","fa fa-edit", "笔"),
	map_icon("map","fa fa-globe", "小地图"),
	group_add_icon("group_add","fa fa-group", "组"),
	calculator_icon("calculator","fa fa-desktop", "计算器"),
	folder_icon("folder","fa fa-folder-open","文件夹");
    /**
     * 风格
     */
    private String style;
    
    
    /**
     * 样式
     */
    private String themes;
    /**
     * 描述
     */
    private String desc;

    private SysFAIconEnum(String style, String themes, String desc) {
        this.style = style;
        this.themes = themes;
        this.desc = desc;
    }
    
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getThemes() {
		return themes;
	}

	public void setThemes(String themes) {
		this.themes = themes;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


	public static SysFAIconEnum toEnum(String style) {
		if (StringUtil.isEmpty(style)) {
			//默认风格
			return default_icon;
        }
		for(SysFAIconEnum item : SysFAIconEnum.values()) {
			if(item.getStyle().equals(style)) {
				return item;
			}
		}
		//默认风格
		return default_icon;
	}

    public String toString() {
        return "{style: " + style + ", themes: " + themes + ", desc: " + desc +"}";
    }
}
