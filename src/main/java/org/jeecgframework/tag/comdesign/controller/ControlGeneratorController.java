package org.jeecgframework.tag.comdesign.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.service.ControlGeneratorService;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/***
 * 控件生成器
 * @author zkx
 * 日期：2017-12-28
 */
@Scope("prototype")
@Controller
@RequestMapping("/controlGeneratorController")
public class ControlGeneratorController {

	
	
	@Autowired
	private ControlGeneratorService controlGeneratorService;
	
	
	@Autowired
	private SystemService systemService;
	
	/**
     * 控件生成器列表页面跳转
     *
     * @return
     */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		
		return new ModelAndView("comdesign/controlGeneratorList");
	}
	/**
     * 控件生成器属性列表页面跳转
     *
     * @return
     */
	@RequestMapping(params = "controlPropertyTarget")
	public ModelAndView controlPropertyTarget(HttpServletRequest request) {
		
		
		String typeId = request.getParameter("typeId");
    	request.setAttribute("typeId", typeId);
		
    	if(typeId.equals("imgUploadTag")){//图片上传
    		return new ModelAndView("comdesign/imgUploadTarget");
    	}else if(typeId.equals("dictSelectTag")){//下拉框
    		return new ModelAndView("comdesign/dictSelectTarget");
    	}else if(typeId.equals("webUploaderTag")){//附件上传
    		return new ModelAndView("comdesign/webUploaderTarget");
    	}else if(typeId.equals("dictRadioTag")){//单选按钮
    		return new ModelAndView("comdesign/dictRadioTarget");
    	}else if(typeId.equals("dictCheckboxTag")){//多选按钮
    		return new ModelAndView("comdesign/dictCheckboxTarget");
    	}else if(typeId.equals("datePickerTag")){//日期选择
    		return new ModelAndView("comdesign/datePickerTarget");
    	}else if(typeId.equals("popTreeTag")){//弹窗树
    		return new ModelAndView("comdesign/popupTreeTarget");
    	}else if(typeId.equals("comboTreeTag")){//下拉树
    		return new ModelAndView("comdesign/comboTreeTarget");
    	}else if(typeId.equals("datalistgridTag")){//数据表格
    		return new ModelAndView("comdesign/datalistgridTarget");
    	}else if(typeId.equals("multiButtonTarget")){//多功能按钮
    		return new ModelAndView("comdesign/multiButtonTarget");
    	}
    	return new ModelAndView("");
		
	}
	
	/**
     * 控件生成器新增页面跳转
     *
     * @return
     */
	@RequestMapping(params = "addControlDesign")
	public ModelAndView addControlDesign(HttpServletRequest request) {
		String typeId = request.getParameter("typeId");
    	request.setAttribute("typeId", typeId);
		return new ModelAndView("comdesign/addControlDesign");
	}
	
	 /**
		 * easyui AJAX请求数据
		 * 
		 * @param request
		 * @param response
		 * @param 
		 * 
		 */

		@RequestMapping(params = "datagrid")
		@ResponseBody
		public void datagrid(CgformControlDesignEntity controlDesign,HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid) {
			
			PageList list = controlGeneratorService.queryControlDesignList(dataGrid.getPage(), dataGrid.getRows(),controlDesign.getTypeId());
			 
				dataGrid.setField("id,name,updateDate");
				dataGrid.setResults(list.getResultList());
				dataGrid.setTotal(list.getCount());
	        TagUtil.datagrid(response, dataGrid);
		}
		/**
		 * 点击预览获取相应的数据回显
		 * @param id 控件id
		 * @param request
		 * @return
		 */
		@RequestMapping(params = "historyData")
		@ResponseBody
		public AjaxJson historyData(CgformControlDesignEntity controlDesign, HttpServletRequest request){
			AjaxJson j = new AjaxJson();
			
			if(StringUtil.isEmpty(controlDesign.getId())){
				j.setSuccess(false);
				j.setMsg("查询失败");
				return j;
			}
			CgformControlDesignEntity controlDesignEntity = controlGeneratorService.getControlDesign(controlDesign.getId());	
			j.setSuccess(true);
			j.setObj(controlDesignEntity);
			return j;
		}
		/**
		 * 删除
		 * 
		 * @return
		 */
		@RequestMapping(params = "del")
		@ResponseBody
		public AjaxJson del(CgformControlDesignEntity controlDesign, HttpServletRequest request) {
			AjaxJson j = new AjaxJson();
			String message = "";
			
			controlDesign = controlGeneratorService.getControlDesign(controlDesign.getId());
			message = "删除成功";
			try{
				controlGeneratorService.delete(controlDesign.getId());
			}catch(Exception e){
				e.printStackTrace();
				message = "删除失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
		
}
