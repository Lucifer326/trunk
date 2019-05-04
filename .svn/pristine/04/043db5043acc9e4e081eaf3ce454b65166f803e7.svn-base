/**  
 * @Project: jeecg
 * @Title: ProcessEndListener.java
 * @Package com.oa.manager.workFlow.listener.execution
 * @date 2013-8-16 下午2:04:12
 * @Copyright: 2013 
 */
package org.jeecgframework.workflow.listener.execution;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
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
public class ProcessListenerHorseEnd implements TaskListener{

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
		
		//修改流程状态
		TaskJeecgService taskJeecgService= ApplicationContextUtil.getContext().getBean(TaskJeecgService.class);
		
		//------------------------------------------------------------------------------
		taskJeecgService.updateEndTest(delegateTask);
	}

}
