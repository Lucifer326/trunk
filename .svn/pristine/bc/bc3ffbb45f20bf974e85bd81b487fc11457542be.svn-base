/**  
 * @Title: VersionUpdateController.java
 * @Package org.jeecgframework.web.system.controller.core
 * @Description: TODO(版本更新管理)
 * @author 王松
 * @date 2017年12月26日
 * @version V1.0  
 */
package org.jeecgframework.web.system.controller.core;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.VersionUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ClassName: VersionUpdateController
 * @Description: TODO(版本更新管理)
 * @author 王松
 * @date 2017年12月26日
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/versionUpdateController")
public class VersionUpdateController extends BaseController {
	/**
	 * @Fields logger : TODO(logger)
	 */
	private static final Logger logger = Logger.getLogger(VersionUpdateController.class);

	@Autowired
	private VersionUpService versionUpService;

	@Autowired
	private SystemService systemService;

	private String message = null;

	/**
	 * @Title: updateVersion
	 * @Description: TODO(更新文件)
	 * @param @param pomPath
	 * @param @param request
	 * @param @return    参数
	 * @return String    返回类型
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(params = "updateVersion")
	public String updateVersion(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String src = request.getSession().getServletContext().getRealPath("/"); 
		String pomPath=src.substring(0,src.indexOf("src")).replace("\\", "/")+"pom.xml";
		j.setMsg("更新成功，请重启服务!");
		try {
			versionUpService.updatePom(pomPath);
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("更新失败，请重新操作！");
		}
		systemService.addLog(j.getMsg(), Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		return j.getMsg();
	}
	@ResponseBody
	@RequestMapping(params = "checkVersion")
	public String checkVersion(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String src = request.getSession().getServletContext().getRealPath("/");
		String filename=src.substring(0,src.indexOf("src")).replace("\\", "/")+"pom.xml";
		int result=0;
		try {
			result=versionUpService.checkVersion(filename);
			if (result==1) {
				j.setMsg("已是最新版本，无需更新！");
			}
			if (result==2){
				j.setMsg("有新版本，需要更新！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			j.setMsg("出错了哦！");
		}
		systemService.addLog(j.getMsg(), Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		return j.getMsg();
	}
}
