package org.jeecgframework.tag.comdesign.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.comdesign.entity.CgformControlDesignEntity;
import org.jeecgframework.tag.comdesign.entity.TreeEntity;
import org.jeecgframework.tag.comdesign.service.ControlGeneratorService;
import org.jeecgframework.tag.core.hplus.PopupTreeTag;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;


/***
 * 树形弹窗控制器
 * @author zkx
 * 日期：2017-12-27
 */
@Scope("prototype")
@Controller
@RequestMapping("/popupTreeController")
public class PopupTreeController {

	
	@Autowired
	private ControlGeneratorService controlGeneratorService;
	
	
	/**
     * 弹窗树列表页面跳转
     *
     * @return
     */
    @RequestMapping(params = "popupTree")
    public ModelAndView popupTree(HttpServletRequest request) {
    		
    	String Ids = request.getParameter("Ids");
    	request.setAttribute("ids", Ids);
    	
    	String selectType = request.getParameter("selectType");
    	request.setAttribute("selectType", selectType);
    	
    	String conditon = request.getParameter("conditon");
    	request.setAttribute("conditon", conditon);
    	
    	String checkBranch = request.getParameter("checkBranch");
    	request.setAttribute("checkBranch", checkBranch);
    	
    	String cascadeCheck = request.getParameter("cascadeCheck");
    	request.setAttribute("cascadeCheck", cascadeCheck);
    	
    	String dataProvider = request.getParameter("dataProvider");
    	request.setAttribute("dataProvider", dataProvider);
    	
    	String async = request.getParameter("async");
    	request.setAttribute("async", async);
    	
    	
    	return new ModelAndView("comdesign/popupTree");
    }
    
    
    
    
    /**
	 * 获取数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "popupTreeGetData")
	@ResponseBody
	public AjaxJson popupTreeGetData(HttpServletRequest request, HttpServletResponse response){
		
		AjaxJson j = new AjaxJson();
		String parentid = request.getParameter("parentid");
		String dataProvider= request.getParameter("dataProvider");
		
		String interfaceName = null;
		String methodName = null;
		
		String []data = dataProvider.split("[.]");
		
		if(data.length != 2){
			
			j.setMsg("数据格式不正确");
			j.setSuccess(false);
			return j;
		}
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
			List<TreeEntity> treelist = new ArrayList<TreeEntity>();	
			//调用该方法
			treelist =  (List<TreeEntity>)m2.invoke(o,parentid,ids);
			
			if(treelist.size()>0){
				
				Map<String,Object> map = null;				
				 
				for(TreeEntity tree:treelist){
					
					map = new HashMap<String,Object>();
					map.put("id", tree.getId());
					map.put("name", tree.getName());
					map.put("checked", tree.isChecked());					
					map.put("pId", tree.getpId());
					map.put("isParent", tree.isParent());	
					
					dataList.add(map);
				}
			}
			
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(dataList);
		j.setMsg(jsonArray.toString());
		return j;
	}
	
	/**
	 * 通过设置的属性生成前端代码，返给前台页面
	 * @param popupTreeTag
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "popupTreeSourceCode")
	@ResponseBody
	public AjaxJson popupTreeSourceCode(PopupTreeTag popupTreeTag, HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		
		//调用标签类的方法 获取源码Code
		String sourceCode = popupTreeTag.end().toString();
		
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
	public AjaxJson save(PopupTreeTag popupTreeTag, HttpServletRequest request) {
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
		String objectToJson = JSON.toJSONString(popupTreeTag);
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
