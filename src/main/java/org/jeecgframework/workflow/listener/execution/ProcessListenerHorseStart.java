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
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.service.impl.TaskJeecgService;



/**
 * 
 * 类名：ProcessTestListener
 * 功能：流程实例测试监听器
 * 详细：
 * 作者：jeecg
 * 版本：1.0
 * 日期：2013-8-16 下午2:04:12
 *
 */
public class ProcessListenerHorseStart implements TaskListener{

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;


	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------------执行测试流程监听---------------------");
		String reason=(String)delegateTask.getVariable("reason");//获取流程发起人id
		
		System.out.println("==流程结束=="+reason);
		/***
		* Horse_Little Start
		*/
		//System.out.println("==流程结束=="+delegateTask.getProcessInstanceId());
		//String assignee = delegateTask.getAssignee();
		StringBuffer sbRole = new StringBuffer(100);
		Set<IdentityLink> identityLinkSet = delegateTask.getCandidates();
		for (IdentityLink identityLink:identityLinkSet){
			sbRole.append(",'").append(identityLink.getGroupId()).append("'");
		}
		if (sbRole.length()<=0)
			return;
		CommonService taskJeecgService= (CommonService) ApplicationContextUtil.getContext().getBean("commonService");
		StringBuffer sb = new StringBuffer(100);
		sb.append("select i.username from ACT_RU_TASK a,t_s_base_user b, ");
		sb.append("t_s_user_org c ,t_s_depart d,t_s_depart e, ");
		sb.append("t_s_user_org f,t_s_role_user g,t_s_role h,t_s_base_user i ");
		sb.append("where a.proc_inst_id_ = "+delegateTask.getProcessInstanceId()+" and b.username = a.assignee_ ");
		sb.append("and c.user_id = b.id and c.org_id = d.id ");
		sb.append("and d.parentdepartid = e.id ");
		sb.append("and f.org_id = e.id ");
		sb.append("and f.user_id = g.userid ");
		sb.append("and h.id = g.roleid ");
		sb.append("and f.user_id = i.id and h.rolecode in (").append(sbRole.toString().substring(1)).append(")");
	
		List<String> userList = taskJeecgService.findListbySql(sb.toString());
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
		/*ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		IdentityService identityService = processEngine.getIdentityService();
		TaskService taskService = processEngine.getTaskService();
		delegateTask.addCandidateUser("horse1");  
        delegateTask.addCandidateUser("horse2"); */ 
        
        
        
        
		//User user1 = identityService.getUserInfo("horse3", "");
		//taskService.addCandidateGroup(taskId, groupId);
		//taskService.addCandidateUser("34626",("horse2"));//delegateTask.getTaskDefinitionKey(), "horse2");
		/*taskService.addCandidateUser(delegateTask.getTaskDefinitionKey(), "horse1");
		taskService.addCandidateUser(delegateTask.getTaskDefinitionKey(), "admin");*/
		/*for (IdentityLink idLink:identityLinkSet){
			idLink.
		} 
		delegateTask.set*/
		//assignee = assignee.equals("horse1")?"horse1,horse2":"horse2,horse3";
		//delegateTask.setAssignee("horse2");
		//------------------------------------------------------------------------------
		/*End*/
		//修改流程状态
		/*TaskJeecgService taskJeecgService= ApplicationContextUtil.getContext().getBean(TaskJeecgService.class);
		taskJeecgService.updateStartTest(delegateTask);*/
	}

}
