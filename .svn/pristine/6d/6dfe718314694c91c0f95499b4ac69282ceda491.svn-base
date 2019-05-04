/**  
 * @Project: jeecg
 * @Title: ProcessEndListener.java
 * @Package com.oa.manager.workFlow.listener.execution
 * @date 2013-8-16 下午2:04:12
 * @Copyright: 2013 
 */
package org.jeecgframework.workflow.listener.execution;

import java.util.List;
import java.util.Set;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.IdentityLink;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.service.impl.TaskJeecgService;



/**
 * 
 * 类名：ProcessStartListenerUpDeptAssignee
 * 功能：获取上级部门的指定角色的人员
 * 详细：
 * 作者：Horse_Little
 * 版本：1.1
 * 日期：2017-9-06 下午4:04:12
 *
 */
public class ProcessStartListenerUpDeptAssignee implements TaskListener{

	/**
	 * @Fields serialVersionUID : 
	 */

	private static final long serialVersionUID = 1L;


	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		/***
		 * Horse_Little Start
		 */
		StringBuffer sbRole = new StringBuffer(100);
		Set<IdentityLink> identityLinkSet = delegateTask.getCandidates();
		for (IdentityLink identityLink:identityLinkSet){
			sbRole.append(",'").append(identityLink.getGroupId()).append("'");
		}
		if (sbRole.length()<=0)
			return;
		CommonService taskJeecgService= (CommonService) ApplicationContextUtil.getContext().getBean("commonService");
		StringBuffer sb1 = new StringBuffer(100);
		sb1.append("select distinct b.username from t_s_base_user b, ");
		sb1.append("t_s_role_user g,t_s_role h ");
		sb1.append("where h.id = g.roleid and g.userid = b.id ");
		sb1.append("and h.rolecode in (").append(sbRole.toString().substring(1)).append(")");
		List<String> userList = taskJeecgService.findListbySql(sb1.toString());
		if (userList.size()>0)
		{
			for (String userid:userList){
				delegateTask.deleteCandidateUser(userid);
			}	
		}   
		StringBuffer sb = new StringBuffer(100);
		sb.append("select i.username from (select c.assignee_ as username from ACT_RU_TASK c where c.proc_inst_id_ = "+delegateTask.getProcessInstanceId()+"");
		sb.append("  ) aa,t_s_base_user b,  ");
		sb.append("t_s_user_org c ,t_s_depart d,t_s_depart e,  ");
		sb.append("t_s_user_org f,t_s_role_user g,t_s_role h,t_s_base_user i  ");
		sb.append("where  b.username = aa.username  ");
		sb.append("and f.user_id = i.id and h.rolecode in (").append(sbRole.toString().substring(1)).append(") ");
		sb.append("and c.user_id = b.id and c.org_id = d.id  ");
		sb.append("and d.parentdepartid = e.id  ");
		sb.append("and f.org_id = e.id  ");
		sb.append("and f.user_id = g.userid  ");
		sb.append("   and h.id = g.roleid  ");
		

		userList = taskJeecgService.findListbySql(sb.toString());

		if (userList.size()>0)
		{
			if(userList.size()>1){
				for (String userid:userList){
					delegateTask.addCandidateUser(userid);  
				}
			}else{
				delegateTask.setAssignee(userList.get(0));
			}			
		} else {
			TSUser user = ResourceUtil.getSessionUserName();
			
			sb.delete(0, sb.length());
			sb.append("select i.username ");
			sb.append("   from t_s_base_user b, ");
			sb.append("   t_s_user_org c, ");
			sb.append("   t_s_depart d, ");
			sb.append("   t_s_depart e, ");
			sb.append("   t_s_user_org f, ");
			sb.append("   t_s_role_user g, ");
			sb.append("   t_s_role h, ");
			sb.append("   t_s_base_user i ");
			sb.append("   where  b.username = '"+user.getUserName()+"' ");
			sb.append("   and c.user_id = b.id ");
			sb.append("   and c.org_id = d.id ");
			sb.append("   and d.parentdepartid = e.id ");
			sb.append("   and f.org_id = e.id ");
			sb.append("   and f.user_id = g.userid ");
			sb.append("   and h.id = g.roleid ");
			sb.append("   and f.user_id = i.id and h.rolecode in (").append(sbRole.toString().substring(1)).append(") ");

			userList = taskJeecgService.findListbySql(sb.toString());
			if (userList.size()>0)
			{
				if(userList.size()>1){
					for (String userid:userList){
						delegateTask.addCandidateUser(userid);  
					}
				}else{
					delegateTask.setAssignee(userList.get(0));
				}			
			}
		}  
	}

}
