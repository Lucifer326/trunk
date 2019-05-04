package org.jeecgframework.workflow.controller.process;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.ParallelGatewayActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.ParallelMultiInstanceBehavior;
import org.activiti.engine.impl.bpmn.behavior.SequentialMultiInstanceBehavior;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.task.Task;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserAgentEntity;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.TSUserAgentServiceI;
import org.jeecgframework.web.system.service.UserService;
import org.jeecgframework.workflow.common.ProcDealStyleEnum;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.dao.ActivitiDao;
import org.jeecgframework.workflow.model.activiti.ProcessHandle;
import org.jeecgframework.workflow.pojo.base.TPBpmLog;
import org.jeecgframework.workflow.pojo.base.TPProcessnode;
import org.jeecgframework.workflow.service.ActivitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: TaskController
 * @Description: 我的任务
 * @author scott
 * @date 2012-8-19 下午04:20:06
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/taskController")
public class TaskController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	private ActivitiService activitiService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected IdentityService identityService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ActivitiDao activitiDao;
	@Autowired
	private UserService userService;
	@Autowired
	private TSUserAgentServiceI tSUserAgentService;
	@Autowired
	private RoleUserService roleUserService;
	/**
	 * 跳转到我的任务列表(总任务列表)
	 */
	@RequestMapping(params = "goTaskListTab")
	public ModelAndView goTaskListTab(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-tab");
	}

	/**
	 * 跳转到我的任务列表- 我的任务（个人）
	 */
	@RequestMapping(params = "goMyTaskList")
	public ModelAndView goMyTaskList(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-person");
	}

	/**
	 * 跳转到我的任务列表 - 我的任务（角色组）需要签收
	 */
	@RequestMapping(params = "goGroupTaskList")
	public ModelAndView goGroupTaskList(HttpServletRequest request) {
		// TaskQuery userRoleTask =
		// taskService.createTaskQuery().processDefinitionKey(getProcessDefKey()).taskCandidateUser(userId);
		// request.setAttribute("userRoleTask", userRoleTask);
		return new ModelAndView("workflow/task/taskList-group");
	}

	/**
	 * 跳转到我的任务列表- 我的任务（历史任务）
	 */
	@RequestMapping(params = "goHistoryTaskList")
	public ModelAndView goHistoryTaskList(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-history");
	}

	/**
	 * 跳转到历史任务（历史任务）
	 */
	@RequestMapping(params = "goAllHistoryTaskList")
	public ModelAndView goAllHistoryTaskList(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-history-all");
	}

	/**
	 * 跳转到我的任务处理页面
	 */
	@RequestMapping(params = "goTaskTab")
	public ModelAndView goTaskTab(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		request.setAttribute("taskId", taskId);
		// 通过任务ID获取流程实例ID
		// Horse_Little
		// 2017-09-06
		// 获取任务信息
		// Start
		Task task = activitiService.getTask(taskId);
		String processInstanceId = task.getProcessInstanceId();
		// proc_inst_id,name,task_def_key
		String taskName = task.getName();
		String taskDefKey = task.getTaskDefinitionKey();
		request.setAttribute("taskName", taskName);
		request.setAttribute("taskDefKey", taskDefKey);
		// End
		request.setAttribute("processInstanceId", processInstanceId);
		// -update-begin-----------author:zhoujf date:20151210 for:任务处理页面
		String bpmProcDealStyle = (String) taskService.getVariable(taskId, WorkFlowGlobals.BPM_PROC_DEAL_STYLE);
		ProcDealStyleEnum style = ProcDealStyleEnum.toEnum(bpmProcDealStyle);
		return new ModelAndView("workflow/task/" + style.getViewName() + "task-tab");
		// -update-end-----------author:zhoujf date:20151210 for:任务处理页面
	}

	/**
	 * 签收和办理任务合二为一 Horse_Little 2017-09-18
	 */
	@RequestMapping(params = "claimAndgoTaskTab")
	public ModelAndView claimAndgoTaskTab(HttpServletRequest request) {
		String userId = ResourceUtil.getSessionUserName().getUserName().toString();
		String taskId = oConvertUtils.getString(request.getParameter("taskId"));
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task.getAssignee() == null || task.getAssignee().equals(""))
			taskService.claim(taskId, userId);
		return this.goTaskTab(request);
	}

	/**
	 * 跳转到我的任务处理页面
	 */
	@RequestMapping(params = "goProcessHisTab")
	public ModelAndView goProcessHisTab(HttpServletRequest request) {
		String processInstanceId = request.getParameter("processInstanceId");
		request.setAttribute("processInstanceId", processInstanceId);
		// -update-begin-----------author:zhoujf date:20151210 for:任务处理页面
		String bpmProcDealStyle = activitiDao.getHisVarinst(WorkFlowGlobals.BPM_PROC_DEAL_STYLE, processInstanceId);
		ProcDealStyleEnum style = ProcDealStyleEnum.toEnum(bpmProcDealStyle);
		return new ModelAndView("workflow/task/" + style.getViewName() + "process-his-tab");
		// -update-end-----------author:zhoujf date:20151210 for:任务处理页面
	}

	/**
	 * 跳转到我的任务-附加页面
	 */
	@RequestMapping(params = "goTaskForm")
	public ModelAndView goTaskForm(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		Task task = activitiService.getTask(taskId);
		// 通过任务ID获取流程实例ID
		// Horse_Little
		// 2017-09-06
		// 获取任务信息
		// 流程实例ID、流程任务ID、任务名称、流程节点ID（任务定义ID）
		// Start
		String processInstanceId = task.getProcessInstanceId();
		String processDefinitionId = task.getProcessDefinitionId();// 定义流程id
		// proc_inst_id,name,task_def_key
		String taskName = URLEncoder.encode(task.getName());//解决乱码问题
		String taskDefKey = task.getTaskDefinitionKey();
		// End

		String CONTENT_URL = null;// 任务节点对应的单据页面
		// 获取流程定义ID
		String insId = task.getProcessInstanceId();
		ProcessHandle processHandle = activitiService.getProcessHandle(taskId);

		// update-begin--Author:zhoujf Date:20150804 for：流程节点权限控制修改
		// 首先判断节点里面，是否配置了对应的表单页面
		CONTENT_URL = oConvertUtils.getString(processHandle.getTpProcessnode().getModelandview());
		if (oConvertUtils.isNotEmpty(CONTENT_URL)) {
			// 拼接参数
			CONTENT_URL = CONTENT_URL + "&id=" + runtimeService.getVariable(insId, "ID");
			// 传递到页面
			// request.setAttribute(WorkFlowGlobals.ProcNode_Start,
			// CONTENT_URL);
		} else {
			// 如果流程节点没配置表单，则获取流程变量中的CONTENT_URL
			CONTENT_URL = oConvertUtils
					.getString(runtimeService.getVariable(insId, WorkFlowGlobals.BPM_FORM_CONTENT_URL));
			if (oConvertUtils.isEmpty(CONTENT_URL)) {
				// step.1 获取流程中的start节点配置的表单
				TPProcessnode startNode = activitiService.getProcessStartNode(taskId);
				if (startNode != null) {
					CONTENT_URL = startNode.getModelandview();
					// ID取值，优先从BPM_DATA_ID获取，为空则从ID获取
					Object id = runtimeService.getVariable(insId, WorkFlowGlobals.BPM_DATA_ID) != null
							? runtimeService.getVariable(insId, WorkFlowGlobals.BPM_DATA_ID)
							: runtimeService.getVariable(insId, "id");
					// 拼接参数
					CONTENT_URL = CONTENT_URL + "&id=" + id;
					runtimeService.setVariable(insId, WorkFlowGlobals.BPM_FORM_CONTENT_URL, CONTENT_URL);
				}
			}/*else{
			    CONTENT_URL = CONTENT_URL +"&id="+runtimeService.getVariable(insId, "ID");
			}*/
			// request.setAttribute(WorkFlowGlobals.ProcNode_Start,
			// CONTENT_URL);
		}

		String processnodeId = "";
		if (processHandle.getTpProcessnode() != null) {
			processnodeId = processHandle.getTpProcessnode().getId();
		}
		// update--begin-----author:scott----date:21060309-----for:传递给子流程追加参数taskId----------------
		// old:CONTENT_URL = CONTENT_URL+
		// "&processnodeId="+processnodeId+"&taskId="+taskId
		// 2017-09-25
		// Horse_Little
		CONTENT_URL = CONTENT_URL + "&processDefinitionId=" + processDefinitionId + "&processnodeId=" + processnodeId
				+ "&taskId=" + taskId + "&processInstanceId=" + processInstanceId + "&taskName=" + taskName
				+ "&taskDefKey=" + taskDefKey;
		// End
		// update--begin-----author:scott----date:21060309-----for:传递给子流程追加参数taskId----------------
		request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL);
		// update-end--Author:zhoujf Date:20150804 for：流程节点权限控制修改

		// -update-begin-----------author:zhoujf date:20151210 for:任务处理页面
		String bpmProcDealStyle = (String) taskService.getVariable(taskId, WorkFlowGlobals.BPM_PROC_DEAL_STYLE);
		ProcDealStyleEnum style = ProcDealStyleEnum.toEnum(bpmProcDealStyle);
		return new ModelAndView("workflow/task/" + style.getViewName() + "task-form");
		// -update-end-----------author:zhoujf date:20151210 for:任务处理页面
	}

	// update-begin---author:scott-----------date:20160301-----for:移动端页面特定访问入口----
	/**
	 * 跳转到我的任务-附加页面【跳转移动端表单】
	 */
	@RequestMapping(params = "goTaskFormMobile")
	public ModelAndView goTaskFormMobile(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		Task task = activitiService.getTask(taskId);
		String CONTENT_URL = null;// 任务节点对应的单据页面
		// 获取流程定义ID
		String insId = task.getProcessInstanceId();
		ProcessHandle processHandle = activitiService.getProcessHandle(taskId);

		// update-begin--Author:zhoujf Date:20150804 for：流程节点权限控制修改
		// 首先判断节点里面，是否配置了对应的表单页面
		CONTENT_URL = oConvertUtils.getString(processHandle.getTpProcessnode().getModelandviewMobile());
		if (oConvertUtils.isNotEmpty(CONTENT_URL)) {
			// 拼接参数
			CONTENT_URL = CONTENT_URL + "&id=" + runtimeService.getVariable(insId, "id");
			// 传递到页面
			// request.setAttribute(WorkFlowGlobals.ProcNode_Start,
			// CONTENT_URL);
		} else {
			// 如果流程节点没配置表单，则获取流程变量中的CONTENT_URL
			CONTENT_URL = oConvertUtils
					.getString(runtimeService.getVariable(insId, WorkFlowGlobals.BPM_FORM_CONTENT_URL_MOBILE));
			if (oConvertUtils.isEmpty(CONTENT_URL)) {
				// step.1 获取流程中的start节点配置的表单
				TPProcessnode startNode = activitiService.getProcessStartNode(taskId);
				if (startNode != null) {
					CONTENT_URL = startNode.getModelandviewMobile();
					// 拼接参数
					CONTENT_URL = CONTENT_URL + "&id=" + runtimeService.getVariable(insId, WorkFlowGlobals.BPM_DATA_ID);
					runtimeService.setVariable(insId, WorkFlowGlobals.BPM_FORM_CONTENT_URL_MOBILE, CONTENT_URL);
				}
			}
			// request.setAttribute(WorkFlowGlobals.ProcNode_Start,
			// CONTENT_URL);
		}

		String processnodeId = "";
		if (processHandle.getTpProcessnode() != null) {
			processnodeId = processHandle.getTpProcessnode().getId();
		}
		CONTENT_URL = CONTENT_URL + "&processnodeId=" + processnodeId;
		request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL);
		// update-end--Author:zhoujf Date:20150804 for：流程节点权限控制修改

		// -update-begin-----------author:zhoujf date:20151210 for:任务处理页面
		String bpmProcDealStyle = (String) taskService.getVariable(taskId, WorkFlowGlobals.BPM_PROC_DEAL_STYLE);
		ProcDealStyleEnum style = ProcDealStyleEnum.toEnum(bpmProcDealStyle);
		return new ModelAndView("workflow/task/" + style.getViewName() + "task-form");
		// -update-end-----------author:zhoujf date:20151210 for:任务处理页面
	}
	// update-end---author:scott-----------date:20160301-----for:移动端页面特定访问入口----

	/**
	 * 跳转到我的任务-附加页面
	 */
	@RequestMapping(params = "goProcessHisForm")
	public ModelAndView goProcessHisForm(HttpServletRequest request) {
		// 获取流程定义ID
		String insId = request.getParameter("processInstanceId");
		// 如果流程节点没配置表单，则获取流程变量中的CONTENT_URL
		String CONTENT_URL = activitiDao.getHisVarinst(WorkFlowGlobals.BPM_FORM_CONTENT_URL, insId);
		// request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL);
		// Horse_Little
		// 2017-09-27
		// Start 历史详情在业务页面中获取id参数报错
		Object o = null;
		try {
			o = runtimeService.getVariable(insId, "ID");
		} catch (ActivitiObjectNotFoundException ex) {
			// Horse_Little
			// 2017-10-11
			// Start 当流程完成后，在流程变量中数据被清空，此情况通过在历史实例表中再次获取后解决。
			o = activitiService.getActHiProcinst(insId);
			// End
		}
		// Horse_Little
		// 2017-10-25
		// Start增加了readonly=1
		request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL + "&id=" + o.toString() + "&readonly=1");
		// End
		// -update-begin-----------author:zhoujf date:20151210 for:任务处理页面
		String bpmProcDealStyle = activitiDao.getHisVarinst(WorkFlowGlobals.BPM_PROC_DEAL_STYLE, insId);
		ProcDealStyleEnum style = ProcDealStyleEnum.toEnum(bpmProcDealStyle);
		return new ModelAndView("workflow/task/" + style.getViewName() + "task-form-his");
		// -update-end-----------author:zhoujf date:20151210 for:任务处理页面
	}

	/**
	 * 跳转到我的任务-任务处理页面
	 */
	@RequestMapping(params = "goTaskOperate")
	public ModelAndView goTaskOperate(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		Task task = activitiService.getTask(taskId);
		int fromIndex = 0;
		int toIndex = 0;
		// 流程下一步节点操作
		List<Map> transList = activitiService.getOutTransitions(taskId);

		// 判断一下任务节点后续的分支，是否只有一个，如果是且分支的路线中文名字没有的话，默认为提交
		if (transList.size() == 1) {
			for (Map t : transList) {
				t.put("Transition", "确认提交");
			}
		}
		// 流程审批日志
		List bpmLogList = activitiService.findBpmLogsByBpmID(task.getProcessInstanceId());
		if (bpmLogList.size() - 3 > 0) {
			fromIndex = bpmLogList.size() - 3;
			toIndex = bpmLogList.size();
		} else {
			fromIndex = 0;
			toIndex = bpmLogList.size();
		}
		List bpmLogNewList = bpmLogList.subList(fromIndex, toIndex);
		// 处理意见信息
		request.setAttribute("bpmLogList", bpmLogList);
		request.setAttribute("taskId", taskId);
		request.setAttribute("taskName", task.getName());
		// 当前任务
		request.setAttribute("task", task);
		if (task.getAssignee() != null)
			request.setAttribute("taskopname", userService.getUserByLoginName(task.getAssignee()).getRealName());
		// 流程分支
		request.setAttribute("transitionList", transList);
		// 下一步节点数目
		request.setAttribute("nextCodeCount", transList == null ? 0 : transList.size());
		request.setAttribute("bpmLogListCount", bpmLogList.size());
		request.setAttribute("bpmLogNewList", bpmLogNewList);
		request.setAttribute("bpmLogNewListCount", bpmLogNewList.size());
		// 历史任务节点
		List<Map<String, Object>> histListNode = activitiService.getHistTaskNodeListByReject(task.getProcessInstanceId());
		String hh=task.getProcessDefinitionId();
		if(null!=histListNode&&histListNode.size()>0)
		{
			for (int i = 0; i < histListNode.size(); i++) {
				//如果有与本节点相同的历史节点，进行删除，
				if(histListNode.get(i).get("name_").equals(task.getName()))
				{
					histListNode.remove(i);
				}
			}
			/*request.setAttribute("histListNode", histListNode);//当前节点之前的历史节点
			request.setAttribute("histListSize", histListNode.size());//控制是否有驳回
*/		}
		// -update-begin----------- date:20180117 for:TASK #2124
		// 【我的任务】审批页面，只有一个通过按钮，没有不通过按钮
		// -update-begin-----------date:20180117 for:TASK #2124
		// 【我的任务】审批页面，只有一个通过按钮，没有不通过按钮
		// 获取上一步的节点
		String turnbackTaskId = getTurnbackTaskId(task, histListNode);
		logger.info("turnbackTaskId======>" + turnbackTaskId);
		request.setAttribute("turnbackTaskId", turnbackTaskId);
		if(null!=histListNode&&histListNode.size()>0)
		{
			for (int i = 0; i < histListNode.size(); i++) {
				//如果有与本节点相同的历史节点，进行删除，
				if(histListNode.get(i).get("task_def_key_").equals(turnbackTaskId))
				{
					continue;
				}
				histListNode.remove(i);
			}
			request.setAttribute("histListNode", histListNode);//当前节点之前的历史节点
			request.setAttribute("histListSize", histListNode.size());//控制是否有驳回
		}
		// -update-end-----------date:20180117 for:TASK #2124
		// 【我的任务】审批页面，只有一个通过按钮，没有不通过按钮
		// -update-end-----------date:20180117 for:TASK #2124
		// 【我的任务】审批页面，只有一个通过按钮，没有不通过按钮
		// -update-begin-----------author:zhoujf date:20151210 for:任务处理页面
		String bpmProcDealStyle = (String) taskService.getVariable(taskId, WorkFlowGlobals.BPM_PROC_DEAL_STYLE);
		ProcDealStyleEnum style = ProcDealStyleEnum.toEnum(bpmProcDealStyle);
		return new ModelAndView("workflow/task/" + style.getViewName() + "task-operate");
		// -update-end-----------author:zhoujf date:20151210 for:任务处理页面
	}
	
	//-update-begin-----------date:20180117 for:TASK #2124 【我的任务】审批页面，只有一个通过按钮，没有不通过按钮
		//获取退回的上个节点
		private String getTurnbackTaskId(Task task,List<Map<String,Object>> histListNode){
			String turnbackTaskId = "";
			List<String> taskIdList = new ArrayList<String>();
			List<ActivityImpl> actList = getInTask(task);
			//找出走过的上个节点
			if(histListNode!=null&&histListNode.size()>0){
				for(int i=histListNode.size();i>0;i--){
					Map<String,Object> map = histListNode.get(i-1);
					if(actList!=null&&actList.size()>0){
						for(ActivityImpl act :actList){
							if(act.getId().equals((String)map.get("task_def_key_"))){
								taskIdList.add(act.getId());
								break;
							}
						}
					}
				}
			}
			if(taskIdList.size()>0){
				turnbackTaskId = taskIdList.get(0);
			}
			return turnbackTaskId;
		}
		
		//获取来源节点
		private List<ActivityImpl> getInTask(Task task){
			List<ActivityImpl> list = new ArrayList<ActivityImpl>();
			 // 取得流程定义
	        ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
	                .getDeployedProcessDefinition(task
	                        .getProcessDefinitionId());
			// 取得上一步活动
	        ActivityImpl currActivity = ((ProcessDefinitionImpl) definition)
	                .findActivity(task.getTaskDefinitionKey());
	        if(currActivity.getActivityBehavior() instanceof SequentialMultiInstanceBehavior){
	        	//顺序会签节点不能回退
	        }else if(currActivity.getActivityBehavior() instanceof ParallelMultiInstanceBehavior){
	        	//并行会签节点不能回退
	        }else{
	        	list.addAll(getInTaskByAct(currActivity));
	        }
	        return list;
		}
		
		
		private List<ActivityImpl> getInTaskByAct(ActivityImpl act){
			List<ActivityImpl> list = new ArrayList<ActivityImpl>();
	        //也就是节点间的连线
	        List<PvmTransition> inTransitionList = act.getIncomingTransitions();
	        for(PvmTransition pvm:inTransitionList){
	        	ActivityImpl t = (ActivityImpl)pvm.getSource();
	        	if(t.getActivityBehavior() instanceof UserTaskActivityBehavior){
	        		list.add(t);
	        	}else if(t.getActivityBehavior() instanceof ParallelGatewayActivityBehavior){
	        		//上个节点是并行网关 不回退
	        		break;
	        	}else{
	        		list.addAll(getInTaskByAct(t));
	        	}
	        }
	        return list;
		}
		//-update-end-----------date:20180117 for:TASK #2124 【我的任务】审批页面，只有一个通过按钮，没有不通过按钮
	
	/**
	 * 跳转到我的任务-任务处理页面
	 */
	@RequestMapping(params = "goProcessHisOperate")
	public ModelAndView goProcessHisOperate(HttpServletRequest request) {
		String insId = request.getParameter("processInstanceId");
		request.setAttribute("processInstanceId", insId);
		int fromIndex = 0;
		int toIndex = 0;
		// 流程审批日志
		List bpmLogList = activitiService.findBpmLogsByBpmID(insId);
		if (bpmLogList.size() - 3 > 0) {
			fromIndex = bpmLogList.size() - 3;
			toIndex = bpmLogList.size();
		} else {
			fromIndex = 0;
			toIndex = bpmLogList.size();
		}
		List bpmLogNewList = bpmLogList.subList(fromIndex, toIndex);
		request.setAttribute("bpmLogList", bpmLogList);
		request.setAttribute("bpmLogListCount", bpmLogList.size());
		request.setAttribute("bpmLogNewList", bpmLogNewList);
		request.setAttribute("bpmLogNewListCount", bpmLogNewList.size());
		// -update-begin-----------author:zhoujf date:20151210 for:任务处理页面
		String bpmProcDealStyle = activitiDao.getHisVarinst(WorkFlowGlobals.BPM_PROC_DEAL_STYLE, insId);
		ProcDealStyleEnum style = ProcDealStyleEnum.toEnum(bpmProcDealStyle);
		return new ModelAndView("workflow/task/" + style.getViewName() + "task-operate-his");
		// -update-end-----------author:zhoujf date:20151210 for:任务处理页面
	}

	/**
	 * 跳转到我的任务-流程图
	 */
	@RequestMapping(params = "goTaskMap")
	public ModelAndView goTaskMap(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		String mapUrl = "activitiController.do?openProcessPic&tag=task&taskId=" + taskId;
		request.setAttribute("mapUrl", mapUrl);
		return new ModelAndView("workflow/task/task-map");
	}

	/**
	 * 待办任务列表-用户所有的任务
	 */

	@RequestMapping(params = "taskAllList")
	public void taskAllList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		/*
		 * List taskList =
		 * activitiService.findPriTodoTasks(user.getUserName(),request,dataGrid)
		 * .getResultList(); Long taskSize =
		 * activitiService.countPriTodaoTask(user.getUserName(),request);
		 * PageList pageList =
		 * activitiService.findPriTodoTasks(user.getUserName(),request,dataGrid)
		 * ;
		 */
		PageList pageList = null;
		List taskList = null;
		List<TSUserAgentEntity> userList=tSUserAgentService.getTsUserList(user.getUserName());
		if(userList==null || userList.size()<= 0 || (userList.size()>0 && userList.get(0)==null)){
			List<TSUserAgentEntity> userAgentList=tSUserAgentService.getTsUserAgentList(user.getUserName());
			if(userAgentList==null || userAgentList.size()<= 0 || (userAgentList.size()>0 && userAgentList.get(0)==null)){
				pageList = activitiService.findPriTodoTasks(user.getUserName(), request, dataGrid);
				taskList = pageList.getResultList();
			}else{
				StringBuilder sb = new StringBuilder("");
				sb.append(user.getUserName()+",");
				String roleids="";
				for(int i=0;i<userAgentList.size();i++){
					sb.append(userAgentList.get(i).getUserName()+",");
					List<TSRoleUser> roleUserList=roleUserService.getRoleUserListByUserName(userAgentList.get(i).getUserName());
					if(roleUserList!=null && roleUserList.size()>0) {
						for (TSRoleUser ru : roleUserList) {
							TSRole role = ru.getTSRole();
							roleids+=role.getRoleCode()+",";
						}
					}
				}
				String id=sb.toString();
				pageList = activitiService.findPriTodoTasksAndAgent(user.getUserName(),id,roleids, request, dataGrid);
				taskList = pageList.getResultList();
			}
			// Long taskSize = Long.valueOf(pageList.getCount());
			dataGrid.setTotal(pageList.getCount());
			dataGrid.setResults(taskList);
			TagUtil.datagrid(response, dataGrid);
		}
	}

	/**
	 * 待办任务列表-组任务
	 */

	@RequestMapping(params = "taskGroupList")
	public void taskGroupList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		// 获取当前用户角色集
		List<TSRoleUser> roles = systemService.findByProperty(TSRoleUser.class, "TSUser", user);
		List taskList = activitiService.findGroupTodoTasks(roles, request, dataGrid).getResultList();
		Long taskSize = activitiService.countGroupTodoTasks(roles, request);
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);

	}

	/**
	 * 待办任务列表-历史任务
	 */

	@RequestMapping(params = "taskHistoryList")
	public void taskHistoryList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		List taskList = activitiService.findHistoryTasks(user.getUserName(), request);
		Long taskSize = activitiService.countHistoryTasks(user.getUserName(), request);
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);

	}

	// -update-begin-----------author:zhoujf date:20151216 for:增加查询所有的流程历史
	/**
	 * 历史任务(查看所有历史任务)
	 */

	@RequestMapping(params = "taskAllHistoryList")
	public void taskAllHistoryList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<?> taskList = activitiService.findAllHistoryTasks(request);
		Long taskSize = activitiService.countAllHistoryTasks(request);
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);
	}
	// -update-end-----------author:zhoujf date:20151216 for:增加查询所有的流程历史

	/**
	 * 按照任务名称和流程实例ID查询审批意见
	 * 
	 * @param taskId
	 * @return
	 */
	@RequestMapping(params = "findBpmLogsByBpmIDAndTaskID")
	@ResponseBody
	public AjaxJson findBpmLogsByBpmIDAndTaskID(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String taskname = request.getParameter("taskname");
		String bpmid = request.getParameter("bpmid");
		// 流程审批日志
		List<TPBpmLog> bpmLogList = activitiService.findBpmLogsByBpmIDAndTaskID(bpmid, taskname);
		if (bpmLogList.size() > 0)
			j.setMsg(bpmLogList.get(0).getMemo());
		else
			j.setMsg("");
		return j;
	}

	/***********************/
	/*
	 * Horse_little 20170906 获取任务审批意见
	 */
	/***********************/
	@RequestMapping(params = "getBpmLogsByTaskId")
	@ResponseBody
	public AjaxJson getBpmLogsByTaskId(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String taskId = request.getParameter("taskid");
		Task task = activitiService.getTask(taskId);
		// 流程审批日志
		List<TPBpmLog> bpmLogList = activitiService.findBpmLogsByBpmID(task.getProcessInstanceId());
		/*
		 * JSONArray arr2 = new JSONArray(); for(TPBpmLog bmpLog:bpmLogList){
		 * JSONObject nodeJson =new JSONObject();
		 * nodeJson.put("id",bmpLog.getId());
		 * nodeJson.put("taskname",bmpLog.getTask_name());
		 * nodeJson.put("taskcode",bmpLog.getTask_node());
		 * nodeJson.put("memo",bmpLog.getMemo());
		 * nodeJson.put("opcode",bmpLog.getOp_code());
		 * nodeJson.put("opname",bmpLog.getOp_name()); arr2.add(nodeJson); }
		 * j.setMsg(JSONHelper.toJSONString(arr2));
		 */
		j.setMsg(JSONHelper.toJSONString(bpmLogList));
		return j;
	}

	/**
	 * Horse_Little 2017-09-15 我的已办页面跳转
	 */
	@RequestMapping(params = "goMyTaskAlreadyDoList")
	public ModelAndView goMyTaskAlreadyDoList(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-myareadydo");
	}

	/**
	 * Horse_Little 2017-09-15 我的已办页面跳转
	 */
	@RequestMapping(params = "goMyTaskStartList")
	public ModelAndView goMyTaskStartList(HttpServletRequest request) {
		return new ModelAndView("workflow/task/taskList-mystartdo");
	}

	/**
	 * Horse_Little 2017-09-15 我的已经办理的任务列表
	 */

	@RequestMapping(params = "taskMyAreadyDoList")
	public void taskMyAreadyDoList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		PageList pageList = activitiService.findMyAreadyDoTasks(user.getUserName(), request, dataGrid);
		List taskList = pageList.getResultList();
		Long taskSize = Long.valueOf(pageList.getCount());
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);

	}

	/**
	 * Horse_Little 2017-09-15 我的已经办理的任务列表
	 */

	@RequestMapping(params = "taskMyStartList")
	public void taskMyStartList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		PageList pageList = activitiService.findMyStartTasks(user.getUserName(), request, dataGrid);
		List taskList = pageList.getResultList();
		Long taskSize = Long.valueOf(pageList.getCount());
		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "selectSuggestion")
	public ModelAndView selectSuggestion(HttpServletRequest request) {
		return new ModelAndView("workflow/task/selectSuggestion");
	}
	@RequestMapping(params = "suggestionDatagrid")
	public void suggestionDatagrid(HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid) {
		PageList list = activitiService.getTypesByTypegroupcode("basesuggestion",dataGrid);
		dataGrid.setResults(list.getResultList());
        dataGrid.setTotal(list.getCount());
        TagUtil.datagrid(response, dataGrid);

	}
}
