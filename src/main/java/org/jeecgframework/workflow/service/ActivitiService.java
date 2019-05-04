package org.jeecgframework.workflow.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.workflow.model.activiti.ActivitiCom;
import org.jeecgframework.workflow.model.activiti.ProcessHandle;
import org.jeecgframework.workflow.pojo.activiti.ActHiTaskinst;
import org.jeecgframework.workflow.pojo.activiti.ActIdUser;
import org.jeecgframework.workflow.pojo.base.TPProcess;
import org.jeecgframework.workflow.pojo.base.TPProcessnode;
import org.jeecgframework.workflow.pojo.base.TSBusConfig;
import org.jeecgframework.workflow.pojo.base.TSPrjstatus;
import org.springframework.transaction.annotation.Transactional;


public interface ActivitiService extends CommonService {
	/**
	 * synToActiviti 同步用户到Activiti数据库
	 */
	public void save(TSUser user, String roleIds, Short synToActiviti);

	/**
	 * synToActiviti 同步用户到Activiti数据库
	 * @param userId
	 */
	public void delete(TSUser user);
	/**
	 * 启动流程
	 */
	public ProcessInstance startWorkflow(Object entity,String businessKey,
			Map<String, Object> variables,TSBusConfig tsBusbase);

	
	
	
	/**
	 * 启动流程
	 */
	public ProcessInstance startOnlineWorkflow(String create_by,String businessKey,
			Map<String, Object> variables,TSBusConfig tsBusbase);
	
	//update-begin--Author:zhoujf  Date:20150804 for：启动流程事物处理(online表单流程)
	/**
	 * 启动流程(online表单流程)
	 * @param create_by
	 * @param businessKey
	 * @param variables
	 * @param tsBusbase
	 */
	public void startOnlineProcess(String create_by,String businessKey,
			Map<String, Object> variables,TSBusConfig tsBusbase);
	//update-end--Author:zhoujf  Date:20150804 for：启动流程事物处理(online表单流程)
	
	//update-begin--Author:zhoujf  Date:20150804 for：启动流程事物处理(自定义开发表单流程)
	/**
	 * 启动流程(自定义开发表单流程)
	 * @param create_by
	 * @param businessKey
	 * @param variables
	 * @param tsBusbase
	 */
	public void startUserDefinedProcess(String create_by,String businessKey,
			Map<String, Object> variables,TSBusConfig tsBusbase);
	//update-begin--Author:zhoujf  Date:20150804 for：启动流程事物处理(自定义开发表单流程)
	
	/**
	 * 查询待办任务	
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	public List findTodoTasks(String userId,List<TSBusConfig> tsBusConfigs);
	/**
	 * 获取流程图跟踪信息
	 * 
	 * @param request
	 * @return
	 */
	public List<Map<String, Object>> traceProcess(String processInstanceId)
			throws Exception;

	/**
	 * 获取跟踪信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActivityImpl getProcessMap(String processInstanceId);
	public List<String> highLight(String processInstanceId);
	/**
	 * 获取业务ID
	 * 
	 * @param taskId
	 * @return
	 */
	public String getBusinessKeyByTask(Task task);
	/**
	 * 完成任务
	 */
	public ActivitiCom complete(String taskId, Map<String, Object> map);

	/**
	 * 获取业务Id
	 */
	public String getBusinessKeyByTask(String taskId);
	/**
	 *根据流程KEY和当前流程环节标示获取当前环节对象
	 */
	public TPProcessnode getTPProcessnode(String taskDefinitionKey, String processkey,String taskDefinitionName );
	/**
	 * 
	 * 
	 * 获取全部部署流程
	 */
	/*
	@authhyzx
	@date2017年12月7日
	*/
	
	public TPProcessnode getTPProcessnode(String taskDefinitionKey, String processkey);
	@Transactional(readOnly = true)
	public List processDefinitionList();
	
	//update-begin--Author:zhoujunfeng  Date:20140809 for：流程processkey获取该流程发布的流程-------------------
	/**
	 * 获取流程processkey获取该流程下已经发布的流程
	 */
	@Transactional(readOnly = true)
	public List processDefinitionListByProcesskey(String processkey);
	//update-end--Author:zhoujunfeng  Date:20140809 for：流程processkey获取该流程发布的流程-------------------
	
	/**
	 * 根据taskId获取task对象
	 * @param taskId
	 * @return
	 */
	public Task getTask(String taskId);
	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId);
	/**
	 * 根据taskId封装ProcessHandle对象
	 * @param taskId
	 * @return
	 */
	public ProcessHandle getProcessHandle(String taskId);
	/**
	 * 根据业务ID获取HistoricProcessInstance对象
	 * 
	 * @param businessKey
	 * @return
	 */
	public HistoricProcessInstance getHiProcInstByBusKey(String businessKey);
	/**
	 * 根据父流程业务ID更新子流程的业务ID
	 * 
	 * @param parBusKey
	 *            父流程业务ID
	 * @param subBusKey
	 *            子流程业务ID
	 * @return
	 */
	public void updateHiProcInstBusKey(String parBusKey, String subBusKey);
	
	
	/**
	 * 根据角色编码和当前审批状态值获取审批状态
	 */
	public TSPrjstatus getTBPrjstatusByCode(String prjstate, String rolecode);
	/**
	 * 根据流程编码和业务类名获取业务配置类
	 */
	public TSBusConfig getTSBusConfig(Class classname, String processkey);
	
	/**
	 * 注意会把整个表数据删除
	 * @param <T>
	 */
	public <T> void batchDelete(final Class<T> entityClass);
	/**
	 * 查询待办任务-只查询用户自己的
	 * @param userId
	 * @param tsBusConfigs
	 * @param request 
	 * @return
	 */
	public PageList findPriTodoTasks(String userId,  HttpServletRequest request,DataGrid dataGrid);
	/**
	 * 查询待办任务-查询用户自己的和代理的人的
	 * @param userId
	 * @param tsBusConfigs
	 * @param request 
	 * @return
	 */
	public PageList findPriTodoTasksAndAgent(String userId, String ids, String roleids,HttpServletRequest request,DataGrid dataGrid);
	/**
	 * 查询组待办任务
	 * @param userName
	 * @param tsBusConfigs
	 * @param request 
	 * @return
	 */
	public PageList findGroupTodoTasks(List<TSRoleUser> roles,HttpServletRequest request,DataGrid dataGrid);
	/**
	 * 查询历史任务
	 * @param userName
	 * @param request 
	 * @return
	 */
	public List findHistoryTasks(String userName, HttpServletRequest request);
	/**
	 * 查询所有的历史任务
	 * @param request 
	 * @return
	 */
	public List findAllHistoryTasks(HttpServletRequest request);
	/**
	 * 查询待办任务-只查询用户自己的 (统计总数)
	 * @param userName
	 * @param tsBusConfigs
	 * @param request
	 * @return
	 */
	public Long countPriTodaoTask(String userName,HttpServletRequest request);
	/**
	 * 查询组待办任务 (统计总数)
	 * @param roles
	 * @param tsBusConfigs
	 * @param request
	 * @return
	 */
	public Long countGroupTodoTasks(List<TSRoleUser> roles, HttpServletRequest request);
	/**
	 * 查询历史任务(统计总数)
	 * @param userName
	 * @param request
	 * @return
	 */
	public Long countHistoryTasks(String userName, HttpServletRequest request);
	
	/**
	 * 查询所有的历史任务(统计总数)
	 * @param userName
	 * @param request
	 * @return
	 */
	public Long countAllHistoryTasks(HttpServletRequest request);
	
	/**
	 * 通过节点ID，获取当前节点出发的路径
	 */
	public List<PvmTransition> getOutgoingTransitions(String taskId);
	
	/**
	 * 查询流程下审批意见
	 * @param taskId
	 * @return
	 */
	public List findBpmLogsByBpmID(String bpm_id);
	
	/**
	 * 按照任务名称和流程实例ID查询审批意见
	 * @param taskId
	 * @return
	 */
	public List	findBpmLogsByBpmIDAndTaskID(String bmpid,String taskname);
	
	/**
	 * 通过任务节点ID，获取当前节点 分支
	 */
	public List getOutTransitions(String taskId) ;
	
	/**
	 * 根据taskId,获取根节点Start的信息
	 * 
	 * @param taskId
	 * @return
	 */
	public TPProcessnode getProcessStartNode(String taskId);
	
	/**
	 * 查询流程的历史任务节点
	 * @param proceInsId
	 * @return
	 */
	public List<Map<String,Object>> getHistTaskNodeList(String proceInsId);
	
	/**
	 * 查询流程的历史任务节点
	 * @param proceInsId
	 * @return
	 */
	public List<Map<String,Object>> getHistTaskNodeListByReject(String proceInsId);
	
	/**
	 * 查询流程的所有节点
	 * @param taskId
	 * @return
	 */
	public List getAllTaskNode(String taskId);
	
	/**
	 * 根据流程节点ID 和 菜单Id 获取 具有操作权限的按钮Codes
	 * @param nodeId
	 * @param functionId
	 * @return
	 */
	public Set<String> getNodeOperaCodesByNodeIdAndFunctionId(String nodeId, String functionId);
	
	/**
	 * 
	 * @param nodeId
	 * @param functionId
	 * @return
	 */
	public  Set<String> getOperationCodesByNodeIdAndruleDataId(String nodeId,String functionId);
	
	/**
	 * 用户删除或禁用同步
	 * @param userId
	 */
	public void synUserDel(String userId);
	
	/**
	 * 用户激活同步
	 * @param userId
	 */
	public void synUserActive(String userId);
	
	/**
	 * 角色删除同步
	 * @param roleId
	 */
	public void synRoleDel(String roleId);
	
	/**
	 * 角色禁用同步
	 * @param roleId
	 */
	public void synRoleLock(String roleId);
	
	/**
	 * 角色启用同步
	 * @param roleId
	 */
	public void synRoleUnLock(String roleId);
	
	/**
	 * 角色用户同步（从角色管理中添加关联用户）
	 * @param roleId
	 * @param userIds  多个用户用“,”隔开
	 */
	public void synRoleUser(String roleId,String userIds);
	
	/**
	 * 角色用户同步（从角色管理中删除关联用户）
	 * @param roleUserId
	 */
	public void synRoleUserDel(String roleUserId);
	/**
	 * Horse_little
	 * @param processkey 流程Key
	 * 2017-09-05
	 */
	public List<TPProcessnode> getTPProcessnodeByProcessKey(String processkey);
	/**
	 * Horse_little
	 * @param userName 用户
	 * 2017-09-15
	 */
	public PageList findMyAreadyDoTasks(String userName,HttpServletRequest request, DataGrid dataGrid);
	/**
	 * Horse_little
	 * @param userName 用户
	 * 2017-09-15
	 */
	public PageList findMyStartTasks(String userName,HttpServletRequest request, DataGrid dataGrid);
	
	public String getActHiProcinst(String procInstId);
	//启动流程后，查询节点名称和处理人
	public String getNextTaskNameAndAssignee(String procInstId);
	//查询当前节点角色
	public List<String> getRoleByCurrentTaskId(String taskId);
	//根据组织和角色查找符合条件的委托人
	public List<ActIdUser> getActIdUserByRoleAndDept(String roleIds,String departIds,String userName,ActIdUser actIdUser);
	public List<ActIdUser> getActIdUserByDept(String departIds,String userName,ActIdUser actIdUser);
	//根据流程实例得到定义流程
	public TPProcess getProcessNameByProcessId (String proceId);
	//根据流程实例id|任务名称id 查询历史任务 最新处理时间排序
	public List<ActHiTaskinst> getHisTaskByProId(String proceInsId ,String taskName);
	
	//根据流程实例id|任务定义id 查询历史任务 最新开始时间排序
	public List<ActHiTaskinst> getHisTaskByProIdAndTaskDefinId(String proceInsId ,String taskDefDey);

	/**
	 * @Title: getTypesByTypegroupcode
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param string
	 * @param @param dataGrid
	 * @param @return    参数
	 * @return List<TSType>    返回类型
	 * @throws
	 */
	public PageList getTypesByTypegroupcode(String typegroupcode, DataGrid dataGrid);
}
