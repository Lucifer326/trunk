package org.jeecgframework.workflow.controller.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.Query;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.workflow.common.ProcDealStyleEnum;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.dao.ActivitiDao;
import org.jeecgframework.workflow.model.activiti.ProcessHandle;
import org.jeecgframework.workflow.model.diagram.HistoryProcessInstanceDiagramPositionCmd;
import org.jeecgframework.workflow.pojo.activiti.ActHiVarinst;
import org.jeecgframework.workflow.pojo.base.TPProcessnode;
import org.jeecgframework.workflow.service.ActivitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 
 * @ClassName: MobileTaskController
 * @Description: 移动端任务
 * @author zhoujf
 * @date 2016-2-29 下午14:36:06
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/mobileTaskController")
public class MobileTaskController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private ActivitiService activitiService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	private ActivitiDao activitiDao;
	
	/**
	 * 跳转到任务首页
	 */
	@RequestMapping(params = "goTaskIndex")
	public ModelAndView goTaskIndex(HttpServletRequest request) {
		return new ModelAndView("workflow/mobile/app/task/task-index");
	}
	
	/**
	 * 跳转到待我审批
	 */
	@RequestMapping(params = "goRunningTask")
	public ModelAndView goRunningTask(HttpServletRequest request) {
		return new ModelAndView("workflow/mobile/app/task/task-running");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(params = "taskAllList")
	@ResponseBody
	public void taskAllList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		TSUser user = ResourceUtil.getSessionUserName();
		List taskList = activitiService.findPriTodoTasks(user.getUserName(),request,dataGrid).getResultList();
//		Long taskSize = activitiService.countPriTodaoTask(user.getUserName(),request);
//		dataGrid.setTotal(taskSize.intValue());
		dataGrid.setResults(taskList);
		dataGrid.setField("id,bpmBizTitle,Process_processDefinition_id,Process_processDefinition_name,Process_task_processInstanceId,userRealName,assigneeName,Process_task_createTime,Process_task_dueTime,TSPrjstatus_description,Process_task_name,Process_task_id,Process_task_taskDefinitionKey");
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 跳转到业务申请
	 */
	@RequestMapping(params = "goTaskApply")
	public ModelAndView goTaskApply(HttpServletRequest request) {
		return new ModelAndView("workflow/mobile/app/task/task-apply");
	}
	
	/**
	 * 跳转到我发起的
	 */
	@RequestMapping(params = "goMyApply")
	public ModelAndView goMyApply(HttpServletRequest request) {
		return new ModelAndView("workflow/mobile/app/task/task-myapply");
	}
	
	/**
	 * 我发起的流程列表数据
	 * @return
	 */
	@RequestMapping(params = "myRunningProcessListDataGrid")
	public void myRunningProcessListDataGrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String currentUserName = ResourceUtil.getSessionUserName().getUserName();
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery().startedBy(currentUserName);
	    List<HistoricProcessInstance> list = historicProcessInstanceQuery.orderByProcessInstanceStartTime().desc().listPage((dataGrid.getPage()-1)*dataGrid.getRows(), dataGrid.getRows());

	    StringBuffer rows = new StringBuffer();
		for(HistoricProcessInstance hi : list){
			String starttime = DateFormatUtils.format(hi.getStartTime(), "yyyy-MM-dd HH:mm:ss");
			String endtime = hi.getEndTime()==null?"":DateFormatUtils.format(hi.getEndTime(), "yyyy-MM-dd HH:mm:ss");
			
			long totalTimes = hi.getEndTime()==null?(Calendar.getInstance().getTimeInMillis()-hi.getStartTime().getTime()):(hi.getEndTime().getTime()-hi.getStartTime().getTime());
			
			long dayCount = totalTimes /(1000*60*60*24);//计算天
			long restTimes = totalTimes %(1000*60*60*24);//剩下的时间用于计于小时
			long hourCount = restTimes/(1000*60*60);//小时
			restTimes = restTimes % (1000*60*60);
			long minuteCount = restTimes / (1000*60);
			
			String spendTimes = dayCount+"天"+hourCount+"小时"+minuteCount+"分";
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(hi.getProcessDefinitionId()).singleResult();
			//update-begin--Author:zhoujf  Date:20151208 for：增加业务标题
			String bpmBizTitle = getHiVariable(hi.getId(),WorkFlowGlobals.BPM_BIZ_TITLE);
			rows.append("{'id':"+hi.getId()+",'prcocessDefinitionName':'"+StringUtils.trimToEmpty(processDefinition.getName())+"','startUserId':'"+hi.getStartUserId()+"','starttime':'"+starttime+"','endtime':'"+endtime+"','spendTimes':'"+spendTimes+"','processDefinitionId':'"+hi.getProcessDefinitionId() +"','processInstanceId':'"+hi.getId()+"','bpmBizTitle':'"+bpmBizTitle+"'},");
			//update-begin--Author:zhoujf  Date:20151208 for：增加业务标题
		}
		
		String rowStr = StringUtils.substringBeforeLast(rows.toString(), ",");
		
		JSONObject jObject = JSONObject.fromObject("{'rows':["+rowStr+"]}");
		responseDatagrid(response, jObject);
	}
	
	private String getHiVariable(String taskId, String variableName){
		StringBuilder sb = new StringBuilder("");
		sb.append("from ActHiVarinst VAR where VAR.procInstId =? and VAR.name = ?");
		Query query = activitiService.getSession().createQuery(sb.toString());
		query.setString(0, taskId);
		query.setString(1, variableName);
		ActHiVarinst obj = (ActHiVarinst)query.uniqueResult();
		return obj==null?"":obj.getText();
	}
	
	// -----------------------------------------------------------------------------------
	// 以下各函数可以提成共用部件 (Add by Quainty)
	// -----------------------------------------------------------------------------------
	public void responseDatagrid(HttpServletResponse response, JSONObject jObject) {
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		try {
			PrintWriter pw=response.getWriter();
			pw.write(jObject.toString());
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 跳转到任务办理页面
	 */
	@RequestMapping(params = "goTaskTab")
	public ModelAndView goTaskTab(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		request.setAttribute("taskId", taskId);
		//通过任务ID获取流程实例ID
		String processInstanceId = activitiService.getTask(taskId).getProcessInstanceId();
		request.setAttribute("processInstanceId", processInstanceId);
		return new ModelAndView("workflow/mobile/app/task/task-tab");
	}
	
	
	
	/**
	 * 跳转到任务历史页面
	 */
	@RequestMapping(params = "goTaskOperateHis")
	public ModelAndView goTaskOperateHis(HttpServletRequest request) {
		return new ModelAndView("workflow/mobile/app/task/task-operate-his");
	}
	
	
	/**
	 * 跳转到我的任务-附加页面【跳转移动端表单】
	 */
	@RequestMapping(params = "goTaskFormMobile")
	public ModelAndView goTaskFormMobile(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		Task  task = activitiService.getTask(taskId);
		String CONTENT_URL = null;//任务节点对应的单据页面
		//获取流程定义ID
		String insId = task.getProcessInstanceId();
		ProcessHandle processHandle = activitiService.getProcessHandle(taskId);
		
		//update-begin--Author:zhoujf  Date:20150804 for：流程节点权限控制修改
		//首先判断节点里面，是否配置了对应的表单页面
	    CONTENT_URL = oConvertUtils.getString(processHandle.getTpProcessnode().getModelandviewMobile());
		if(oConvertUtils.isNotEmpty(CONTENT_URL)){
			//拼接参数
			CONTENT_URL = CONTENT_URL +"&id="+runtimeService.getVariable(insId, "id");
			//传递到页面
//			request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL);
		}else{
			//如果流程节点没配置表单，则获取流程变量中的CONTENT_URL
		     CONTENT_URL = oConvertUtils.getString(runtimeService.getVariable(insId, WorkFlowGlobals.BPM_FORM_CONTENT_URL_MOBILE));
			if(oConvertUtils.isEmpty(CONTENT_URL)){
				//step.1 获取流程中的start节点配置的表单
				TPProcessnode startNode = activitiService.getProcessStartNode(taskId);
				if(startNode!=null){
					CONTENT_URL = startNode.getModelandviewMobile();
					//拼接参数
					CONTENT_URL = CONTENT_URL +"&id="+runtimeService.getVariable(insId, WorkFlowGlobals.BPM_DATA_ID);
					runtimeService.setVariable(insId,WorkFlowGlobals.BPM_FORM_CONTENT_URL_MOBILE, CONTENT_URL);
				}
			}
		}
		//如果没有配置移动端的from走PC端的Form
		if(oConvertUtils.isNotEmpty(CONTENT_URL)){
			//首先判断节点里面，是否配置了对应的表单页面
		    CONTENT_URL = oConvertUtils.getString(processHandle.getTpProcessnode().getModelandview());
			if(oConvertUtils.isNotEmpty(CONTENT_URL)){
				//拼接参数
				CONTENT_URL = CONTENT_URL +"&id="+runtimeService.getVariable(insId, "id");
				//传递到页面
//				request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL);
			}else{
				//如果流程节点没配置表单，则获取流程变量中的CONTENT_URL
			     CONTENT_URL = oConvertUtils.getString(runtimeService.getVariable(insId, WorkFlowGlobals.BPM_FORM_CONTENT_URL));
				if(oConvertUtils.isEmpty(CONTENT_URL)){
					//step.1 获取流程中的start节点配置的表单
					TPProcessnode startNode = activitiService.getProcessStartNode(taskId);
					if(startNode!=null){
						CONTENT_URL = startNode.getModelandview();
						//拼接参数
						CONTENT_URL = CONTENT_URL +"&id="+runtimeService.getVariable(insId, WorkFlowGlobals.BPM_DATA_ID);
						runtimeService.setVariable(insId,WorkFlowGlobals.BPM_FORM_CONTENT_URL, CONTENT_URL);
					}
				}
			}
		}
		
		String processnodeId = "";
		if(processHandle.getTpProcessnode()!=null){
			processnodeId = processHandle.getTpProcessnode().getId();
		}
		CONTENT_URL = CONTENT_URL+ "&processnodeId="+processnodeId;
		request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL);
		//update-end--Author:zhoujf  Date:20150804 for：流程节点权限控制修改
		
		return new ModelAndView("workflow/mobile/app/task/task-form");
	}
	
	
	/**
	 * 跳转到我的任务-附加页面
	 */
	@RequestMapping(params = "goProcessHisForm")
	public ModelAndView goProcessHisForm(HttpServletRequest request) {
		//获取流程定义ID
		String insId = request.getParameter("processInstanceId");
		//如果流程节点没配置表单，则获取流程变量中的CONTENT_URL
		String CONTENT_URL = activitiDao.getHisVarinst(WorkFlowGlobals.BPM_FORM_CONTENT_URL_MOBILE,insId);
		if(oConvertUtils.isNotEmpty(CONTENT_URL)){
			CONTENT_URL = activitiDao.getHisVarinst(WorkFlowGlobals.BPM_FORM_CONTENT_URL,insId);
		}
		request.setAttribute(WorkFlowGlobals.ProcNode_Start, CONTENT_URL);
		return new ModelAndView("workflow/mobile/app/task/task-form-his");
	}
	
	
	/**
	 * 跳转到我的任务-任务处理页面
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params = "goTaskOperate")
	public ModelAndView goTaskOperate(HttpServletRequest request) {
		String taskId = request.getParameter("taskId");
		Task task = activitiService.getTask(taskId);
		int fromIndex = 0;
		int toIndex = 0;
		//流程下一步节点操作
		List<Map> transList = activitiService.getOutTransitions(taskId);
		
		//判断一下任务节点后续的分支，是否只有一个，如果是且分支的路线中文名字没有的话，默认为提交
		if(transList.size()==1){
			for(Map t:transList){
				t.put("Transition", "确认提交");
			}
		}
		//流程审批日志
		List bpmLogList = activitiService.findBpmLogsByBpmID(task.getProcessInstanceId());
		if(bpmLogList.size()-3>0){
			fromIndex = bpmLogList.size() - 3;
			toIndex =  bpmLogList.size();
		}else{
			fromIndex = 0;
			toIndex =  bpmLogList.size();
		}
		List bpmLogNewList = bpmLogList.subList(fromIndex, toIndex);
		//处理意见信息
		request.setAttribute("bpmLogList", bpmLogList);
		request.setAttribute("taskId", taskId);
		request.setAttribute("taskName", task.getName());
		//当前任务
		request.setAttribute("task",task);
		//流程分支
		request.setAttribute("transitionList", transList);
		//下一步节点数目
		request.setAttribute("nextCodeCount", transList==null?0:transList.size());
		request.setAttribute("bpmLogListCount",bpmLogList.size());
		request.setAttribute("bpmLogNewList", bpmLogNewList);
		request.setAttribute("bpmLogNewListCount", bpmLogNewList.size());
		//历史任务节点
		List<Map<String,Object>> histListNode = activitiService.getHistTaskNodeList(task.getProcessInstanceId());
		request.setAttribute("histListNode", histListNode);
		request.setAttribute("histListSize", histListNode.size());
		return new ModelAndView("/workflow/mobile/app/task/task-operate");
	}
	
	
	/**
	 * easyui 流程历史页面
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(params = "viewProcessInstanceHistory")
	public ModelAndView viewProcessInstanceHistory(@RequestParam("processInstanceId") String processInstanceId,
			HttpServletRequest request, HttpServletResponse respone,Model model) {
		model.addAttribute("processInstanceId", processInstanceId);
		List<HistoricTaskInstance> historicTasks = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
		model.addAttribute(historicTasks);
		request.setAttribute("historicTasks", historicTasks);
		//Command cmd = new HistoryProcessInstanceDiagramPositionCmd(processInstanceId);
		//Command<InputStream> cmd = new HistoryProcessInstanceDiagramPositionCmd(processInstanceId);
		Command<ArrayList> cmd = new HistoryProcessInstanceDiagramPositionCmd(processInstanceId);
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		List is = processEngine.getManagementService().executeCommand(cmd);
		//InputStream is = processEngine.getManagementService().executeCommand(cmd);
		request.setAttribute("listIs", is);

		return new ModelAndView("workflow/mobile/app/task/viewProcessInstanceHistory");
	}
	
	
	
	/**
	 * 跳转到我的任务处理页面
	 */
	@RequestMapping(params = "goProcessHisTab")
	public ModelAndView goProcessHisTab(HttpServletRequest request) {
		String processInstanceId = request.getParameter("processInstanceId");
		request.setAttribute("processInstanceId", processInstanceId);
		return new ModelAndView("workflow/mobile/app/task/process-his-tab");
	}
	
	
	/**
	 * 跳转到我的任务-任务处理页面
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(params = "goProcessHisOperate")
	public ModelAndView goProcessHisOperate(HttpServletRequest request) {
		String insId = request.getParameter("processInstanceId");
		request.setAttribute("processInstanceId", insId);
		int fromIndex = 0;
		int toIndex = 0;
		//流程审批日志
		List bpmLogList = activitiService.findBpmLogsByBpmID(insId);
		if(bpmLogList.size()-3>0){
			fromIndex = bpmLogList.size() - 3;
			toIndex =  bpmLogList.size();
		}else{
			fromIndex = 0;
			toIndex =  bpmLogList.size();
		}
		List bpmLogNewList = bpmLogList.subList(fromIndex, toIndex);
		request.setAttribute("bpmLogList", bpmLogList);
		request.setAttribute("bpmLogListCount",bpmLogList.size());
		request.setAttribute("bpmLogNewList", bpmLogNewList);
		request.setAttribute("bpmLogNewListCount", bpmLogNewList.size());
		return new ModelAndView("workflow/mobile/app/task/task-operate-his");
	}
}
