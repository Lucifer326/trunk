package org.jeecgframework.tag.comdesign.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.entity.OptionVo;
import org.jeecgframework.tag.comdesign.service.ControlGeneratorService;
import org.jeecgframework.tag.core.hplus.DictSelectTag;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.TypeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;



/***
 * 树形弹窗控制器
 * @author zkx
 * 日期：2017-12-27
 */
@Scope("prototype")
@Controller
@RequestMapping("/dictSelectController")
public class DictSelectController {

	
	
	@Autowired
	private ControlGeneratorService controlGeneratorService;
	
	
	@Autowired
	private TypeServiceI typeService;
	
	/**
	 * 通过设置的属性生成前端代码，返给前台页面
	 * @param popupTreeTag
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "dictSelectSourceCode")
	@ResponseBody
	public AjaxJson dictSelectSourceCode(DictSelectTag dictSelectTag, HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		
		//调用标签类的方法 获取源码Code
		String sourceCode = dictSelectTag.end().toString();
		
		j.setSuccess(true);
		j.setMsg(sourceCode);
		
		return j;
	}
	
	/**
	 * 新增
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DictSelectTag dictSelectTag, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "控件添加成功";
		
		String historyId = request.getParameter("historyId");
		String source = request.getParameter("source");
		String controlName = request.getParameter("controlName");
		String typeId = request.getParameter("typeId");
		
		CgformControlDesignEntity controlDesign = null;
		
		if(historyId == null || historyId.equals("")) {
			
			controlDesign = new CgformControlDesignEntity();
		
			//设置控件名称
			controlDesign.setName(controlName);
		}else{
			
			controlDesign = controlGeneratorService.getControlDesign(historyId);
			
		}
		
		//设置属性
		String objectToJson = JSON.toJSONString(dictSelectTag);
		controlDesign.setProperty(objectToJson);
		//设置源码
		controlDesign.setSourceCode(source);
		//设置控件类型
		controlDesign.setTypeId(typeId);
		//设置修改时间
		controlDesign.setUpdateDate(new Date());
		
		//获取当前登录用户
		HttpSession session = ContextHolderUtils.getSession();
		TSUser currentUser =  (TSUser)session.getAttribute(ResourceUtil.LOCAL_CLINET_USER);
		//设置修改人
		controlDesign.setCreateBy(currentUser.getRealName());
			
		try {
			controlGeneratorService.save(controlDesign);
			message = controlDesign.getId();
		} catch (Exception e) {
			e.printStackTrace();
			message = "控件添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 下拉框控件级联时
	 * 根据typegroupcode或者自定义表单的表名和id，text 属性
	 * 查询获取相应的集合
	 * @param 
	 * @return
	 */
	@RequestMapping(params = "getListByCodeOrTable")
	@ResponseBody
	public String getListByCodeOrTable(HttpSession session, HttpServletRequest request) {
		
	
		String dict = request.getParameter("dict");
		String condition = request.getParameter("condition");
		String dynamicCondition = request.getParameter("dynamicCondition");
		
		
		if(dict.indexOf(",") != -1){//自定义表单查询
			String dicts[] = null;
			
			String dictTable = null;
			String dictField = null;
			String dictText = null;
			
			dicts = dict.split(",");
			if(dicts.length >0){
				dictTable = dicts[0];
				dictField = dicts[1];
				dictText = dicts[2];
			}
			
			List<Map<String, Object>> tableList = controlGeneratorService.getListByTable(dictTable,dictField,dictText,condition,dynamicCondition);
			
			return JSONHelper.toJSONString(tableList);
		}else{//根据typegroupcode查询
			String parentTypecode = parseParentid(dynamicCondition);
			
			String regionId = (String)session.getAttribute(ResourceUtil.LOCAL_CLINET_USER_DEPART_REGIONID);
			
			List<TSType> typeList = typeService.getItemsByTypegroupcodeAndRegionId(dict, regionId, parentTypecode);
			List<OptionVo> optionVoList = new ArrayList<OptionVo>();
			if(typeList.size()>0){
				
				for(TSType t : typeList){
					OptionVo ov = new OptionVo();
					ov.setId(t.getTypecode());
					ov.setName(t.getTypename());
					optionVoList.add(ov);
				}
				
			}
			
			return JSONHelper.toJSONString(optionVoList);
		}
	}
	
	public String parseParentid(String dynamicCondition){
		if(dynamicCondition==null || "".equals(dynamicCondition)) {
			return "";
		}
		
		if(dynamicCondition.indexOf("=") != -1){
			int indexNum = dynamicCondition.lastIndexOf("=");
			return dynamicCondition.substring(indexNum+1);
		}
		
		return dynamicCondition;
		
	}
	
	
	
}
