/**  
 * @Title: ReleaseController.java
 * @Package org.jeecgframework.web.system.controller.core
 * @Description: TODO(版本发布管理)
 * @author 王松
 * @date 2017年12月25日
 * @version V1.0  
 */
package org.jeecgframework.web.system.controller.core;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSReleaseEntity;
import org.jeecgframework.web.system.service.ReleaseService;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: ReleaseController
 * @Description: TODO(版本发布管理)
 * @author 王松
 * @date 2017年12月25日
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/releaseController")
public class ReleaseController extends BaseController{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReleaseController.class);
	
	@Autowired
	private ReleaseService releaseService;
	
	@Autowired
	private SystemService systemService;
	
	private String message = null;
	
	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "release")
	public ModelAndView release(HttpServletRequest request) {
		return new ModelAndView("system/release/releaseList");
	}
	
	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "releaseGrid")
	public void roleGrid(TSReleaseEntity release, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSReleaseEntity.class, dataGrid);
		String name = request.getParameter("name");
		release.setName(null);
		if (StringUtil.isNotEmpty(name)) {
			cq.like("name", "%" + name + "%");
		}
		cq.add();
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,release);
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 版本发布和编辑
	 * 
	 * @param release
	 * @return
	 */
	@RequestMapping(params = "saveRelease")
	@ResponseBody
	public AjaxJson saveRelease(TSReleaseEntity release, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(release.getId())) {
			message = "版本: " + release.getName() + "被更新成功";
			releaseService.saveRelease(release);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} else {
			message = "版本: " + release.getName() + "被添加成功";
			release.setIsUse("1");
			release.setUpdateDate(new Date());
			releaseService.saveRelease(release);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}

		return j;
	}
	/**
	 * 版本发布和编辑页面跳转
	 * 
	 * @param release
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TSReleaseEntity release, HttpServletRequest req) {
		if (release.getId() != null) {
			release = systemService.getEntity(TSReleaseEntity.class, release.getId());
			req.setAttribute("release", release);
		}
		return new ModelAndView("system/release/release");
	}

	/**
	 * 删除版本
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSReleaseEntity release, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		release = systemService.getEntity(TSReleaseEntity.class, release.getId());
		message = "版本: " + release.getName() + "被删除成功";
		releaseService.delete(release);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
}
