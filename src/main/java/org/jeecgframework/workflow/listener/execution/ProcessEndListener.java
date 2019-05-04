/**  
 * @Project: jeecg
 * @Title: ProcessEndListener.java
 * @Package com.oa.manager.workFlow.listener.execution
 * @date 2013-8-16 下午2:04:12
 * @Copyright: 2013 
 */
package org.jeecgframework.workflow.listener.execution;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.service.impl.TaskJeecgService;



/**
 * 
 * 类名：ProcessEndListener
 * 功能：流程实例结束监听器
 * 详细：
 * 作者：jeecg
 * 版本：1.0
 * 日期：2013-8-16 下午2:04:12
 *
 */
public class ProcessEndListener implements ExecutionListener{

	/**
	 * @Fields serialVersionUID : 
	 */
	
	private static final long serialVersionUID = 1L;

	public void notify(DelegateExecution execution) throws Exception {
		
		String applyUserId=(String)execution.getVariable("applyUserId");//获取流程发起人id
		String bpmStatus=(String)execution.getVariable(WorkFlowGlobals.BPM_STATUS);//获取流程状态
		System.out.println("==流程结束=="+applyUserId);
		
		//修改流程状态
		TaskJeecgService taskJeecgService= ApplicationContextUtil.getContext().getBean(TaskJeecgService.class);
		
		//------------------------------------------------------------------------------
		//流程追回逻辑    20160217
		if("callBackProcess".equals(bpmStatus)){
			taskJeecgService.updateFormDataStatusStart(execution);
		}else{
			taskJeecgService.updateFormDataStatus(execution);
		}
		//------------------------------------------------------------------------------
		
		//清空流程实例所有历史流程变量,任务变量
		HistoryService historyService= ApplicationContextUtil.getContext().getBean(HistoryService.class);
		
		List<HistoricVariableInstance> hvis=historyService.createHistoricVariableInstanceQuery().processInstanceId(execution.getProcessInstanceId()).list();
		
		for(HistoricVariableInstance h:hvis){
			//System.out.println(h.getVariableName());
			//update-begin--Author:zhoujf  Date:20151208 for：业务标题不清空
			//流程对应的表单业务标题，不清空
			if(WorkFlowGlobals.BPM_BIZ_TITLE.equals(h.getVariableName())){
				continue;
			}
			//流程办理风格，不清空
			if(WorkFlowGlobals.BPM_PROC_DEAL_STYLE.equals(h.getVariableName())){
				continue;
			}
			//update-end--Author:zhoujf  Date:20151208 for：业务标题不清空
			//流程对应的表单页面，不清空
			if(!WorkFlowGlobals.BPM_FORM_CONTENT_URL.equals(h.getVariableName()) && !WorkFlowGlobals.BPM_FORM_CONTENT_URL_MOBILE.equals(h.getVariableName())){
				((HistoricVariableInstanceEntity)h).delete();
			}
		}
	}

}
