package org.jeecgframework.core.enums;

import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;

/**
 * 系统样式类型
 *
 * @author zhoujf
 */
public enum SysThemesEnum {
	
	DEFAULT_STYLE("default","main/main","default", "经典风格"),
	SHORTCUT_STYLE("shortcut","main/shortcut_main","default", "ShortCut风格"),
	SAVATION_STYLE("salvation","main/salvation_main","salvation", "Salvation风格"),	
	SLIDING_STYLE("sliding","main/sliding_main","default", "Sliding云桌面"),
	ACE_STYLE("ace","main/ace_main","metro", "ACE平面风格"),
	ACE_LE_STYLE("acele","main/ace_main","metrole", "ACE2风格"),
	DIY("diy","main/diy","default","diy风格"),
	HPLUS_BULE("hplus_blue","main/hplus_blue_main","metro","HPLUS蓝色风格"),
	HPLUS_DARKBLUE("hplus_darkblue","main/hplus_darkblue_main","metro","HPLUS藏青风格"),
	HPLUS_CYAN("hplus_cyan","main/hplus_cyan_main","metro","HPLUS青色风格"),
	HPLUS_DARKRED("hplus_darkred","main/hplus_darkred_main","metro","HPLUS暗红风格"),
	HPLUS_DARKGREY("hplus_darkgrey","main/hplus_darkgrey_main","metro","HPLUS深灰风格"),
	HPLUS_ORANGE("hplus_orange","main/hplus_orange_main","metro","HPLUS橘黄风格"),
	HPLUS_GREEN("hplus_green","main/hplus_green_main","metro","HPLUS绿色风格"),
	HPLUS_RED("hplus_red","main/hplus_red_main","metro","HPLUS红色风格");



    /**
     * 风格
     */
    private String style;
    
    /**
     * 首页路径
     */
    private String indexPath;
    
    /**
     * 样式
     */
    private String themes;
    /**
     * 描述
     */
    private String desc;
    /**
     * 默认配置文件的key值
     */
    private final static String DEF_STYLE_KEY="sysThemesStyle";

    private SysThemesEnum(String style, String indexPath, String themes, String desc) {
        this.style = style;
        this.indexPath = indexPath;
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

	public String getIndexPath() {
		return indexPath;
	}

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	public static SysThemesEnum toEnum(String style) {
		if (StringUtil.isEmpty(style)) {
			//默认风格
			String configStyle = ResourceUtil.getConfigByName(DEF_STYLE_KEY);
			if(StringUtil.isEmpty(configStyle)){
				return HPLUS_BULE;
			}else{
				for(SysThemesEnum item : SysThemesEnum.values()) {
					if(item.getStyle().equals(configStyle)) {
						return item;
					}
				}
				
			}
        }
		for(SysThemesEnum item : SysThemesEnum.values()) {
			if(item.getStyle().equals(style)) {
				return item;
			}
		}
		//默认风格
		return HPLUS_BULE;
	}

    public String toString() {
        return "{style: " + style + ", indexPath: " + indexPath + ", themes: " + themes + ", desc: " + desc +"}";
    }
}
