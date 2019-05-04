package org.jeecgframework.workflow.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 同步用户到工作流，拦截器
 * @author zhangdaihao
 *
 */
public class SynUserInterceptor  implements HandlerInterceptor {
	private List<String> includeUrls;
	
	private static final Logger logger = Logger.getLogger(SynUserInterceptor.class);
	@Autowired
	private ActivitiService activitiService;
	@Autowired
	private SystemService systemService;
	private String message = null;
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		String requestPath = ResourceUtil.getRequestPath(arg0);// 用户访问的资源地址
		if(!includeUrls.contains(requestPath)){
			return;
		}
		if(requestPath.equals("userController.do?saveUser") || requestPath.indexOf("userController.do?saveUser&")!=-1){
			//获取表单ID
			String userId = arg0.getParameter("id");
			String roleId = arg0.getParameter("roleId");
			String activitiSync = arg0.getParameter("activitiSync");
			if (StringUtil.isEmpty(activitiSync)) {
				return;
			}
			if (StringUtil.isNotEmpty(roleId)) {
				if (StringUtil.isEmpty(userId)) {
					//添加时同步
					TSUser user = new TSUser();
					user.setUserName(arg0.getParameter("userName"));
					user.setEmail(arg0.getParameter("email"));
					user.setOfficePhone(arg0.getParameter("officePhone"));
					user.setMobilePhone(arg0.getParameter("mobilePhone"));
					TSDepart depart = new TSDepart();
					depart.setId(arg0.getParameter("TSDepart.id"));
					//TODO  待修改组织机构修改
					//user.setTSDepart(depart);
					user.setRealName(arg0.getParameter("realName"));
					user.setStatus(WorkFlowGlobals.User_Normal);
					user.setActivitiSync(Short.valueOf(activitiSync));
					try{
						activitiService.save(user, roleId, user.getActivitiSync());//同步用户到引擎
						message = "添加用户: " + user.getUserName() + "时同步到工作流成功";
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					}catch (Exception e) {
						e.printStackTrace();
						logger.debug(e.getMessage());
					}
					return;
				}else{
					//更新时同步
					try{
						TSUser users = systemService.getEntity(TSUser.class, userId);
						activitiService.save(users, roleId, users.getActivitiSync());//同步用户到引擎
						message = "更新用户: " + users.getUserName() + "时同步到工作流成功";
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					}catch (Exception e) {
						e.printStackTrace();
						logger.debug(e.getMessage());
					}
					return;
				}
			}
		}else if(requestPath.equals("userController.do?lock") || requestPath.indexOf("userController.do?lock&")!=-1){//删除用户
			String userId = arg0.getParameter("id");
			String lockValue = arg0.getParameter("lockvalue");
			if("0".equals(lockValue)){
				activitiService.synUserDel(userId);
			}else if("1".equals(lockValue)){
				activitiService.synUserActive(userId);
			}
		}else if(requestPath.equals("roleController.do?lock") || requestPath.indexOf("roleController.do?lock&")!=-1){//禁用、启用角色
			String roleId = arg0.getParameter("id");
			String lockValue=arg0.getParameter("lockvalue");
			if ("0".equals(lockValue)) {//禁用角色
				activitiService.synRoleLock(roleId);
			} else if ("1".equals(lockValue)) {//启用角色
				activitiService.synRoleUnLock(roleId);
			}
		}else if(requestPath.equals("roleController.do?doAddUserToRole") || requestPath.indexOf("roleController.do?doAddUserToRole&")!=-1){//角色管理中直接添加用户
			String roleId = arg0.getParameter("roleId");
			String userOrgIds = arg0.getParameter("userOrgIds");
			activitiService.synRoleUser(roleId, userOrgIds);
		}
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		String requestPath = ResourceUtil.getRequestPath(arg0);// 用户访问的资源地址
		if(!includeUrls.contains(requestPath)){
			return true;
		}
		//获取表单ID
		if(requestPath.equals("userController.do?del") || requestPath.indexOf("userController.do?del&")!=-1){
			String userId = arg0.getParameter("id");
			activitiService.synUserDel(userId);
		}else if(requestPath.equals("roleController.do?delRoleUser") || requestPath.indexOf("roleController.do?delRoleUser&")!=-1){//角色管理中删除用户
			String roleUserId = arg0.getParameter("id");
			activitiService.synRoleUserDel(roleUserId);
		}else if(requestPath.equals("roleController.do?delRole") || requestPath.indexOf("roleController.do?delRole&")!=-1){//删除角色
			String roleId = arg0.getParameter("id");
			activitiService.synRoleDel(roleId);
		}
		return true;
	}

	public List<String> getIncludeUrls() {
		return includeUrls;
	}

	public void setIncludeUrls(List<String> includeUrls) {
		this.includeUrls = includeUrls;
	}

}
