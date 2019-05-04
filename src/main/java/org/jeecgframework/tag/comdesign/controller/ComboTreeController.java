package org.jeecgframework.tag.comdesign.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.entity.TreeEntity;
import org.jeecgframework.tag.comdesign.service.ControlGeneratorService;
import org.jeecgframework.tag.core.hplus.ComboTreeTag;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;



/***
 * 下拉树控制器
 * @author zkx
 * 日期：2018-1-15
 */
@Scope("prototype")
@Controller
@RequestMapping("/comboTreeController")
public class ComboTreeController {

	
	@Autowired
	private ControlGeneratorService controlGeneratorService;
	
	
	
	 /**
		 * 获取数据
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(params = "comboTreeGetData")
		@ResponseBody
		public List<ComboTree> comboTreeGetData(HttpServletRequest request,ComboTree comboTree){
			
			
			AjaxJson j = new AjaxJson();
			String parentid = request.getParameter("parentid");
			String url= request.getParameter("dataUrl");
			
			String interfaceName = null;
			String methodName = null;
			
			String []data = url.split("[.]");
			
//			if(data.length != 2){
//				
//				j.setMsg("数据格式不正确");
//				j.setSuccess(false);
//				return j;
//			}
			interfaceName =data[0];
			
			methodName =data[1];
			String ids = request.getParameter("ids");
					
			//获取上下文对象
			WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
			
			//通过反射从容器中获得 interfaceName接口类的实例
			Object o = webContext.getBean(interfaceName);
			Class clazz = o.getClass(); 
			
			Method m2 = null;
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			
			
			try {
				//获取interfaceName接口类实例的methodName方法
				m2 = clazz.getMethod(methodName,new Class[] {String.class,String.class});
				List<ComboTree> comboTreelist = new ArrayList<ComboTree>();	
				//调用该方法
				List<TreeEntity> treeEntityList =  (List<TreeEntity>)m2.invoke(o,parentid,ids);
				
				if(treeEntityList==null || treeEntityList.size()==0) {
					return null;
				}
				
				for(int i=0; i<treeEntityList.size();i++) {
					TreeEntity e = treeEntityList.get(i);
					ComboTree t = new ComboTree();
					t.setId(e.getId());
					t.setText(e.getName());
					if(e.isParent()) {
						t.setState("closed");
					}else {
						t.setState("open");
					}
					t.setChecked(e.isChecked());
					comboTreelist.add(t);
				}
				return comboTreelist;
				
				
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		
		/*********************************************************************************************/
		
		
		
		
		
		
		
	/**
	 * 通过设置的属性生成前端代码，返给前台页面
	 * @param popupTreeTag
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "comboTreeSourceCode")
	@ResponseBody
	public AjaxJson comboTreeSourceCode(ComboTreeTag comboTreeTag, HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		
		//调用标签类的方法 获取源码Code
		String sourceCode = comboTreeTag.end().toString();
		
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
	public AjaxJson save(ComboTreeTag comboTreeTag, HttpServletRequest request) {
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
		String objectToJson = JSON.toJSONString(comboTreeTag);
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
	
	
}
