package org.jeecgframework.workflow.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.Query;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.DataBaseConstant;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DBTypeUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.minidao.util.MiniDaoUtil;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.build.DataBaseService;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.pojo.base.TSUserOrg;
import org.jeecgframework.web.system.service.RoleService;
import org.jeecgframework.web.system.service.RoleUserService;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.jeecgframework.workflow.common.ProcDealStyleEnum;
import org.jeecgframework.workflow.common.WorkFlowGlobals;
import org.jeecgframework.workflow.dao.ActivitiDao;
import org.jeecgframework.workflow.dao.IActivitiCommonDao;
import org.jeecgframework.workflow.model.activiti.ActivitiCom;
import org.jeecgframework.workflow.model.activiti.Process;
import org.jeecgframework.workflow.model.activiti.ProcessHandle;
import org.jeecgframework.workflow.model.activiti.WorkflowUtils;
import org.jeecgframework.workflow.pojo.activiti.ActHiActinst;
import org.jeecgframework.workflow.pojo.activiti.ActHiProcinst;
import org.jeecgframework.workflow.pojo.activiti.ActHiTaskinst;
import org.jeecgframework.workflow.pojo.activiti.ActHiVarinst;
import org.jeecgframework.workflow.pojo.activiti.ActIdUser;
import org.jeecgframework.workflow.pojo.base.TPBpmLog;
import org.jeecgframework.workflow.pojo.base.TPForm;
import org.jeecgframework.workflow.pojo.base.TPProcess;
import org.jeecgframework.workflow.pojo.base.TPProcessnode;
import org.jeecgframework.workflow.pojo.base.TPProcessnodeFunction;
import org.jeecgframework.workflow.pojo.base.TPProcesspro;
import org.jeecgframework.workflow.pojo.base.TSBusConfig;
import org.jeecgframework.workflow.pojo.base.TSPrjstatus;
import org.jeecgframework.workflow.service.ActivitiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("activitiService")
@Transactional(rollbackFor=Exception.class)
public class ActivitiServiceImpl extends CommonServiceImpl implements ActivitiService {
	private static Logger logger = LoggerFactory.getLogger(ActivitiServiceImpl.class);

	private IdentityService identityService;
	private RuntimeService runtimeService;
	private UserService userService;
	protected TaskService taskService;
	protected HistoryService historyService;
	protected RepositoryService repositoryService;
	protected String hql;
	@Autowired
	private TaskJeecgService taskJeecgService;
	private SystemService systemService;
	private IActivitiCommonDao activitiCommonDao;
	@Autowired
	private ActivitiDao activitiDao;
	@Autowired
	private DataBaseService dataBaseService;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleUserService roleUserService;
	@Autowired
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	@Autowired
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		
		this.userService = userService;
	}
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	@Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@Resource
	public void setActivitiCommonDao(IActivitiCommonDao activitiCommonDao) {
		this.activitiCommonDao = activitiCommonDao;
	}

	public SystemService getSystemService() {
		return systemService;
	}
	@Autowired
	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	/**
	 * synToActiviti 同步用户到Activiti数据库
	 */
	public void save(TSUser user, String roleidstr, Short synToActiviti) {
		if (WorkFlowGlobals.Activiti_Deploy_YES.equals(synToActiviti)) {
			String userId = user.getUserName();
			UserQuery userQuery = identityService.createUserQuery();
			List<User> activitiUsers = userQuery.userId(userId).list();
			if (activitiUsers.size() == 1) {
				updateActivitiData(user, roleidstr, activitiUsers.get(0));
			} else if (activitiUsers.size() > 1) {
				String errorMsg = "发现重复用户：id=" + userId;
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			} else {
				newActivitiUser(user, roleidstr);
			}
		}

	}

	private void newActivitiUser(TSUser user, String roleidstr) {
		String userId = user.getUserName();
		addActivitiGroup(roleidstr);
		// 添加用户
		saveActivitiUser(user);
		// 添加membership
		addMembershipToIdentify(roleidstr, userId);

	}

	private void addActivitiGroup(String roleidstr) {
		GroupQuery groupQuery = identityService.createGroupQuery();
		String[] roleIds = roleidstr.split(",");
		for (String string : roleIds) {
			//由于用户角色模块的改造，当前roleId格式为：deptid_roleid，因此做一处理
			String[] s = string.split("_");
			if(s.length == 1){
				continue;
			}
			TSRole role = commonDao.getEntity(TSRole.class, s[1]);
			if (role != null) {
				List<Group> activitiGroups = groupQuery.groupId(role.getRoleCode()).list();
				if (activitiGroups.size() <= 0) {
					saveActivitiGroup(role);
				}
			}

		}
	}

	/**
	 * 同步角色到到Activiti数据库组表
	 * 
	 * @param role
	 */
	private void saveActivitiGroup(TSRole role) {
		Group activitiGroup = identityService.newGroup(role.getRoleCode());
		activitiGroup.setId(role.getRoleCode());
		activitiGroup.setName(role.getRoleName());
		identityService.saveGroup(activitiGroup);
	}

	private void saveActivitiUser(TSUser user) {
		String userId = oConvertUtils.getString(user.getUserName());
		User activitiUser = identityService.newUser(userId);
		cloneAndSaveActivitiUser(user, activitiUser);

	}

	private void cloneAndSaveActivitiUser(TSUser user, User activitiUser) {
		activitiUser.setFirstName(user.getRealName());
		activitiUser.setLastName(user.getRealName());
		activitiUser.setPassword(user.getPassword());
		activitiUser.setEmail(user.getEmail());
		identityService.saveUser(activitiUser);

	}

	private void addMembershipToIdentify(String roleidstr, String userId) {
		String[] roleIds = roleidstr.split(",");
		for (String string : roleIds) {
			//由于用户角色模块的改造，当前roleId格式为：deptid_roleid，因此做一处理
			String[] s = string.split("_");
			if(s.length == 1){
				continue;
			}
			TSRole role = commonDao.getEntity(TSRole.class, s[1]);
			logger.debug("add role to activit: {}", role);
			if (role != null) {
				identityService.createMembership(userId, role.getRoleCode());
			}
		}
	}

	private void updateActivitiData(TSUser user, String roleidstr, User activitiUser) {
		String[] roleIds = roleidstr.split(",");
		String userId = user.getUserName();
		if (roleIds.length > 0) {
			addActivitiGroup(roleidstr);
		}
		// 更新用户主体信息
		cloneAndSaveActivitiUser(user, activitiUser);
		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery().groupMember(userId).list();
		for (Group group : activitiGroups) {
			logger.debug("delete group from activit: {}", group);
			identityService.deleteMembership(userId, group.getId());
		}
		// 添加membership
		addMembershipToIdentify(roleidstr, userId);

	}

	/**
	 * 同步删除用户和用户组
	 * 
	 * @param userId
	 *            用户id
	 */
	public void delete(TSUser user) {
		if (user == null) {
			logger.debug("删除用户异常");
		}
		// 同步删除Activiti User
		List<TSRoleUser> roleUserList = findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		if (roleUserList.size() >= 1) {
			for (TSRoleUser tRoleUser : roleUserList) {
				TSRole role = tRoleUser.getTSRole();
				if (role != null) {
					identityService.deleteMembership(user.getUserName(), role.getRoleCode());
				}

			}
		}
		// 同步删除Activiti User
		identityService.deleteUser(user.getUserName());
	}

	/**
	 * 启动流程
	 */
	public ProcessInstance startWorkflow(Object entity, String businessKey, Map<String, Object> variables, TSBusConfig tsBusbase) {
		ReflectHelper reflectHelper = new ReflectHelper(entity);
		TSUser tsUser = (TSUser) reflectHelper.getMethodValue("TSUser");// 获取创建人
		identityService.setAuthenticatedUserId(tsUser.getUserName());// 设置流程发起人
		//标准变量
		variables.put(WorkFlowGlobals.BPM_DATA_ID, businessKey);
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(tsBusbase.getTPProcess().getProcesskey(), businessKey, variables);

		//update-begin---author:scott-------date:20160308--------------for:流程实例ID，存在流程变量里面，支持子流程传递-------------
		if(processInstance==null||oConvertUtils.isEmpty(processInstance.getProcessInstanceId())){
			return null;
		}
		//将流程实例ID存在流程变量里面
		runtimeService.setVariable(processInstance.getProcessInstanceId(), WorkFlowGlobals.JG_LOCAL_PROCESS_ID, processInstance.getProcessInstanceId());
		//update-end---author:scott-------date:20160308--------------for:流程实例ID，存在流程变量里面，支持子流程传递-------------
		return processInstance;
	}

	private String getBpmBizTitle(Map<String, Object> variables,String exp){
		try {
			Set<String> varParams = getVarParams(exp);
			for(String var:varParams){
				Object obj = variables.get(var);
				if(obj==null){
					obj = "";
				}else if(obj instanceof Date){
					obj = DateUtils.formatDate((Date)obj);
				}
				exp = exp.replace("${"+var+"}", obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exp;
	}

	private Set<String> getVarParams(String exp) {
		if(oConvertUtils.isEmpty(exp)){
			return null;
		}
		Set<String> varParams = new HashSet<String>();
		String regex = "\\$\\{\\w+\\}";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(exp);
		while(m.find()){
			String var = m.group();
			varParams.add(var.substring(var.indexOf("{")+1,var.indexOf("}")));
		}
		return varParams;
	}

	/**
	 * 启动流程
	 */
	public ProcessInstance startOnlineWorkflow(String create_by, String businessKey, Map<String, Object> variables, TSBusConfig tsBusbase) {
		identityService.setAuthenticatedUserId(create_by);// 设置流程发起人
		//update-begin--Author:zhoujf  Date:20151208 for：增加业务标题表达式
		variables.put(WorkFlowGlobals.BPM_BIZ_TITLE, getBpmBizTitle(variables,tsBusbase.getBusTitleExp()));
		//update-end--Author:zhoujf  Date:20151208 for：增加业务标题表达式
		//update-begin--Author:zhoujf  Date:20151210 for：增加流程办理风格
		variables.put(WorkFlowGlobals.BPM_PROC_DEAL_STYLE, ProcDealStyleEnum.toEnum(tsBusbase.getProcessDealStyle()).getCode());
		//update-end--Author:zhoujf  Date:20151210 for：增加流程办理风格
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(tsBusbase.getTPProcess().getProcesskey(), businessKey, variables);
		//update-begin---author:scott-------date:20160308--------------for:流程实例ID，存在流程变量里面，支持子流程传递-------------
		if(processInstance==null||oConvertUtils.isEmpty(processInstance.getProcessInstanceId())){
			return null;
		}
		//将流程实例ID存在流程变量里面
		runtimeService.setVariable(processInstance.getProcessInstanceId(), WorkFlowGlobals.JG_LOCAL_PROCESS_ID, processInstance.getProcessInstanceId());
		//update-end---author:scott-------date:20160308--------------for:流程实例ID，存在流程变量里面，支持子流程传递-------------
		return processInstance;
	}
	//update-begin--Author:zhoujf  Date:20150804 for：启动流程事物处理(online表单流程)
	/**
	 * 启动流程(online表单流程)
	 * @param create_by
	 * @param businessKey
	 * @param variables
	 * @param tsBusbase
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void startOnlineProcess(String create_by,String businessKey,Map<String, Object> dataForm,TSBusConfig tsBusbase){
		//获取onlinecoding表名
		//1.加载出onlinecoding的表单数据（单表或主表）
		//2.制定表单访问变量content_url,默认等于表单的查看页面
		//3.通过onlinecoding表名,读取流程表单配置,获取流程实例
		String data_id = businessKey;
		this.startOnlineWorkflow(create_by, data_id, dataForm, tsBusbase);
		//4.修改onlinecoding表单的BPM业务流程状态
		Map<String, Object> dataStatus = new HashMap<String,Object>();
		dataStatus.put(WorkFlowGlobals.BPM_STATUS, WorkFlowGlobals.BPM_BUS_STATUS_2);

		String configId = dataForm.get(WorkFlowGlobals.BPM_FORM_KEY).toString();
		try {
			dataBaseService.updateTable(configId, data_id, dataStatus);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		//----------------------------------------------------------------
		//5.往原来的流程设计里面设置数据
		Map mp = new HashMap();
		mp.put("id", data_id);
		mp.put("userid", dataForm.get(DataBaseConstant.CREATE_BY_DB));
		mp.put("prjstateid", 2);
		mp.put("busconfigid", tsBusbase.getId());
		activitiDao.insert(mp);
	}
	//update-end--Author:zhoujf  Date:20150804 for：启动流程事物处理(online表单流程)

	//update-begin--Author:zhoujf  Date:20150804 for：启动流程事物处理(自定义开发表单流程)
	/**
	 * 启动流程(自定义开发表单流程)
	 * @param create_by
	 * @param businessKey
	 * @param variables
	 * @param tsBusbase
	 */
	public void startUserDefinedProcess(String create_by,String businessKey,Map<String, Object> dataForm,TSBusConfig tsBusbase){
		//获取onlinecoding表名
		//1.加载出onlinecoding的表单数据（单表或主表）
		//2.制定表单访问变量content_url,默认等于表单的查看页面
		//3.通过onlinecoding表名,读取流程表单配置,获取流程实例
		String data_id = businessKey;
		this.startOnlineWorkflow(create_by, data_id, dataForm, tsBusbase);
		//4.修改onlinecoding表单的BPM业务流程状态
		String tableName = dataForm.get(WorkFlowGlobals.BPM_FORM_KEY).toString();
		String update_status_sql = "update "+tableName+" set bpm_status = " + WorkFlowGlobals.BPM_BUS_STATUS_2 +" where id="+"'"+data_id+"'";
		systemService.executeSql(update_status_sql);
		//----------------------------------------------------------------
		//5.往原来的流程设计里面设置数据
		Map<String,Object> mp = new HashMap<String,Object>();
		//业务数据ID
		mp.put("id", data_id);
		//创建人
		mp.put("userid", dataForm.get(DataBaseConstant.CREATE_BY_DB));
		//办理中
		mp.put("prjstateid", 2);
		//业务配置ID
		mp.put("busconfigid", tsBusbase.getId());
		activitiDao.insert(mp);
	}
	//update-end--Author:zhoujf  Date:20150804 for：启动流程事物处理(自定义开发表单流程)

	/**
	 * 查询待办任务
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */ 
	@SuppressWarnings("unchecked")
	public List findTodoTasks(String userId, List<TSBusConfig> tsBusConfigs) {
		List results = new ArrayList();
		List<Task> tasks = new ArrayList<Task>();
		Map classMap =  new HashMap<String,Object>();
		String busentity = "";
		List<Task> todoList;
		List<Task> unsignedTasks;
		if (tsBusConfigs.size() > 0) {
			for (TSBusConfig busConfig : tsBusConfigs) {
				try{
					String processKey = busConfig.getTPProcess().getProcesskey();
					busentity = busConfig.getTSTable().getEntityName();
					// 根据当前人的ID查询
					todoList = taskService.createTaskQuery().processDefinitionKey(processKey).taskAssignee(userId).orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
					// 根据当前人未签收的任务
					unsignedTasks = taskService.createTaskQuery().processDefinitionKey(processKey).taskCandidateUser(userId).orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
					// 合并
					//先建立临时集合
					List<Task> tempList = new ArrayList<Task>(0);
					tempList.addAll(todoList);
					tempList.addAll(unsignedTasks);
					//将实体对应到任务列表
					for(Task task:tempList){
						classMap.put(task.getId(), busentity);
					}
					tasks.addAll(tempList);
				}catch (Exception e) {
					//异常情况继续下一次循环
					logger.info(e.getMessage());
					e.printStackTrace();
				}
			}
			// 根据流程的业务ID查询实体并关联
			for (Task task : tasks) {
				String processInstanceId = task.getProcessInstanceId();
				ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
				// String businessKey = processInstance.getBusinessKey();
				String businessKey = getBusinessKeyByTask(task);
				Class entityClass = MyClassLoader.getClassByScn((String)classMap.get(task.getId()));// 业务类
				Object entityObj = getEntity(entityClass, businessKey);
				if (entityObj != null) {
					ReflectHelper reflectHelper = new ReflectHelper(entityObj);
					Process process = (Process) reflectHelper.getMethodValue("Process");
					process.setTask(task);
					process.setProcessInstance(processInstance);
					process.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
					// reflectHelper.setMethodValue("Process", process);
					results.add(entityObj);
				} else {
					return tasks;
				}
			}
		}
		return results;
	}

	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	public ProcessDefinition getProcessDefinition(String processDefinitionId) {
		return repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
	}

	/**
	 * 获取流程图跟踪信息
	 * 
	 * @param request
	 * @return
	 */
	public List<Map<String, Object>> traceProcess(String processInstanceId) throws Exception {
		Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();// 执行实例

		Object property = PropertyUtils.getProperty(execution, "activityId");
		String activityId = "";
		if (property != null) {
			activityId = property.toString();
		}
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		List<ActivityImpl> activitiList = processDefinition.getActivities();// 获得当前任务的所有节点

		List<Map<String, Object>> activityInfos = new ArrayList<Map<String, Object>>();
		for (ActivityImpl activity : activitiList) {

			boolean currentActiviti = false;
			String id = activity.getId();

			// 当前节点
			if (id.equals(activityId)) {
				currentActiviti = true;
			}

			Map<String, Object> activityImageInfo = packageSingleActivitiInfo(activity, processInstance, currentActiviti);

			activityInfos.add(activityImageInfo);
		}

		return activityInfos;

	}

	/**
	 * 封装输出信息，包括：当前节点的X、Y坐标、变量信息、任务类型、任务描述
	 * 
	 * @param activity
	 * @param processInstance
	 * @param currentActiviti
	 * @return
	 */
	private Map<String, Object> packageSingleActivitiInfo(ActivityImpl activity, ProcessInstance processInstance, boolean currentActiviti) throws Exception {
		Map<String, Object> vars = new HashMap<String, Object>();
		Map<String, Object> activityInfo = new HashMap<String, Object>();
		activityInfo.put("currentActiviti", currentActiviti);
		setPosition(activity, activityInfo);
		setWidthAndHeight(activity, activityInfo);

		Map<String, Object> properties = activity.getProperties();
		vars.put("任务类型", WorkflowUtils.parseToZhType(properties.get("type").toString()));

		ActivityBehavior activityBehavior = activity.getActivityBehavior();
		logger.debug("activityBehavior={}", activityBehavior);
		if (activityBehavior instanceof UserTaskActivityBehavior) {

			Task currentTask = null;

			/*
			 * 当前节点的task
			 */
			if (currentActiviti) {
				currentTask = getCurrentTaskInfo(processInstance);
			}

			/*
			 * 当前任务的分配角色
			 */
			UserTaskActivityBehavior userTaskActivityBehavior = (UserTaskActivityBehavior) activityBehavior;
			TaskDefinition taskDefinition = userTaskActivityBehavior.getTaskDefinition();
			Set<Expression> candidateGroupIdExpressions = taskDefinition.getCandidateGroupIdExpressions();
			if (!candidateGroupIdExpressions.isEmpty()) {

				// 任务的处理角色
				setTaskGroup(vars, candidateGroupIdExpressions);

				// 当前处理人
				if (currentTask != null) {
					setCurrentTaskAssignee(vars, currentTask);
				}
			}
		}

		vars.put("节点说明", properties.get("documentation"));

		String description = activity.getProcessDefinition().getDescription();
		vars.put("描述", description);

		logger.debug("trace variables: {}", vars);
		activityInfo.put("vars", vars);
		return activityInfo;
	}

	private void setTaskGroup(Map<String, Object> vars, Set<Expression> candidateGroupIdExpressions) {
		String roles = "";
		for (Expression expression : candidateGroupIdExpressions) {
			String expressionText = expression.getExpressionText();
			if (expressionText.startsWith("$")) {
				expressionText = expressionText.replace("${insuranceType}", "life");
			}
			String roleName = identityService.createGroupQuery().groupId(expressionText).singleResult().getName();
			roles += roleName;
		}
		vars.put("任务所属角色", roles);
	}

	/**
	 * 设置当前处理人信息
	 * 
	 * @param vars
	 * @param currentTask
	 */
	private void setCurrentTaskAssignee(Map<String, Object> vars, Task currentTask) {
		String assignee = currentTask.getAssignee();
		if (assignee != null) {
			User assigneeUser = identityService.createUserQuery().userId(assignee).singleResult();
			String userInfo = assigneeUser.getFirstName() + " " + assigneeUser.getLastName();
			vars.put("当前处理人", userInfo);
		}
	}

	/**
	 * 获取当前节点信息
	 * 
	 * @param processInstance
	 * @return
	 */
	private Task getCurrentTaskInfo(ProcessInstance processInstance) {
		Task currentTask = null;
		try {
			String activitiId = (String) PropertyUtils.getProperty(processInstance, "activityId");
			logger.debug("current activity id: {}", activitiId);

			currentTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey(activitiId).singleResult();
			logger.debug("current task for processInstance: {}", ToStringBuilder.reflectionToString(currentTask));

		} catch (Exception e) {
			logger.error("can not get property activityId from processInstance: {}", processInstance);
		}
		return currentTask;
	}

	/**
	 * 设置宽度、高度属性
	 * 
	 * @param activity
	 * @param activityInfo
	 */
	private void setWidthAndHeight(ActivityImpl activity, Map<String, Object> activityInfo) {
		activityInfo.put("width", activity.getWidth());
		activityInfo.put("height", activity.getHeight());
	}

	/**
	 * 设置坐标位置
	 * 
	 * @param activity
	 * @param activityInfo
	 */
	private void setPosition(ActivityImpl activity, Map<String, Object> activityInfo) {
		activityInfo.put("x", activity.getX());
		activityInfo.put("y", activity.getY());
	}

	/**
	 * 获取跟踪信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public ActivityImpl getProcessMap(String processInstanceId) {
		Execution execution = runtimeService.createExecutionQuery().executionId(processInstanceId).singleResult();// 执行实例
		if(execution==null){return null;}
		Object property = null;
		try {
			property = PropertyUtils.getProperty(execution, "activityId");
		} catch (IllegalAccessException e) {
			logger.error("反射异常", e);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		String activityId = "";
		if (property != null) {
			activityId = property.toString();// 当前实例的执行到哪个节点
		}
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();
		ProcessDefinitionImpl pdImpl = (ProcessDefinitionImpl) processDefinition;
		String processDefinitionId = pdImpl.getId();// 流程标识
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		ActivityImpl actImpl = null;
		List<String> activitiIds = runtimeService.getActiveActivityIds(execution.getId());
		List<String> a = highLight(processInstanceId);
		List<ActivityImpl> activitiList = def.getActivities();// 获得当前任务的所有节点
		// for(String activitiId : activitiIds){
		for (ActivityImpl activityImpl : activitiList) {
			String id = activityImpl.getId();
			if (id.equals(activityId)) {// 获得执行到那个节点
				actImpl = activityImpl;
				break;
			}
		}
		// }
		return actImpl;
	}

	/**
	 * 获取高亮节点
	 */
	public List<String> highLight(String processInstanceId) {
		List<String> highLihth = new ArrayList<String>();
		List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).list();
		for (Execution execution : executions) {
			ExecutionEntity entity = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(execution.getId()).singleResult();
			highLihth.add(entity.getActivityId());
		}
		return highLihth;

	}

	/**
	 * 获取业务ID
	 * 
	 * @param taskId
	 * @return
	 */
	public String oldgetBusinessKeyByTask(Task task) {
		String businessKey = "";
		TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(task.getId()).singleResult();
		ExecutionEntity executionEntity = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(taskEntity.getExecutionId()).singleResult();
		if (executionEntity.getSuperExecutionId() == null) {
			businessKey = executionEntity.getBusinessKey();
		} else {
			ExecutionEntity executionEntity2 = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(executionEntity.getSuperExecutionId()).singleResult();
			ExecutionEntity executionEntity3 = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(executionEntity2.getParentId()).singleResult();
			businessKey = executionEntity3.getBusinessKey();
		}
		return businessKey;
	}

	/**
	 * 获取业务ID
	 * 
	 * @param taskId
	 * @return
	 */
	public String getBusinessKeyByTask(Task task) {
		String businessKey = "";
		TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(task.getId()).singleResult();
		HistoricProcessInstance hiproins = historyService.createHistoricProcessInstanceQuery().processInstanceId(taskEntity.getProcessInstanceId()).singleResult();
		if (hiproins != null) {
			if (hiproins.getSuperProcessInstanceId() != null && hiproins.getBusinessKey() == null) {
				hiproins = historyService.createHistoricProcessInstanceQuery().processInstanceId(hiproins.getSuperProcessInstanceId()).singleResult();
				businessKey = hiproins.getBusinessKey();
			} else {
				businessKey = hiproins.getBusinessKey();
			}
		}
		return businessKey;
	}

	/**
	 * 根据业务ID获取HistoricProcessInstance对象
	 * 
	 * @param businessKey
	 * @return
	 */
	public HistoricProcessInstance getHiProcInstByBusKey(String businessKey) {
		HistoricProcessInstance hiproins = null;
		hiproins = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
		return hiproins;
	}

	/**
	 * 根据父业务ID获取HistoricProcessInstance对象
	 * 
	 * @param businessKey
	 * @return
	 */
	public HistoricProcessInstance getHiProcInstByParprocInstId(String parprocInstId) {
		return historyService.createHistoricProcessInstanceQuery().superProcessInstanceId(parprocInstId).singleResult();
	}

	/**
	 * 根据父流程业务ID更新子流程的业务ID
	 * 
	 * @param parBusKey
	 *            父流程业务ID
	 * @param subBusKey
	 *            子流程业务ID
	 * @return
	 */
	public void updateHiProcInstBusKey(String parBusKey, String subBusKey) {
		HistoricProcessInstance parhiproins = getHiProcInstByBusKey(parBusKey);
		HistoricProcessInstance subhiproins = getHiProcInstByParprocInstId(parhiproins.getId());
		if (subhiproins != null) {
			HistoricProcessInstanceEntity historicProcessInstanceEntity = (HistoricProcessInstanceEntity) subhiproins;
			ActHiProcinst actHiProcinst = getEntity(ActHiProcinst.class, historicProcessInstanceEntity.getId());
			actHiProcinst.setBusinessKey(subBusKey);
			updateEntitie(actHiProcinst);
		}

	}

	/**
	 * 完成任务
	 */
	public ActivitiCom complete(String taskId, Map<String, Object> map) {
		ActivitiCom activitiCom = new ActivitiCom();
		String msg = "";
		Boolean complete = false;
		try {
			//根据taskId获取businessKey
			String businessKey = getBusinessKeyByTask(taskId);

			//update-begin--Author:zhangdaihao  Date:20140825 for：任意节点跳转模式--------------------
			//taskService.complete(taskId, map);
			try {
				//用户手工指定节点
				String USER_SELECT_TASK_NODE =  oConvertUtils.getString(map.get(WorkFlowGlobals.USER_SELECT_TASK_NODE));
				taskJeecgService.goProcessTaskNode(taskId, USER_SELECT_TASK_NODE,map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//update-end--Author:zhangdaihao  Date:20140825 for：任意节点跳转模式----------------------
			//根据businessKey判断是否流程结束
			List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().finished().processInstanceBusinessKey(businessKey).list();
			if(list!=null&&list.size()==1){
				// 流程结束 根据id修改t_s_basebus的状态为结束(id即为businessKey)
				commonDao.updateBySqlString("update t_s_basebus set prjstateid = '3' where id ='"+businessKey+"'");
			}
			complete = true;
			msg = "办理成功";
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				msg = "没有部署子流程!,请在流程配置中部署流程";
				complete = false;
				e.printStackTrace();
			} else {
				msg = "启动流程失败!,内部错误";
				complete = false;
				e.printStackTrace();

			}
		} catch (Exception e) {
			msg = "内部错误";
			complete = false;
			e.printStackTrace();
		}
		activitiCom.setComplete(complete);
		activitiCom.setMsg(msg);
		return activitiCom;
	}

	/**
	 * 获取业务Id
	 */
	public String getBusinessKeyByTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		return getBusinessKeyByTask(task);
	}

	/**
	 * 根据流程ID和当前流程环节标示获取当前环节对象
	 */
	public TPProcessnode getTPProcessnode(String taskDefinitionKey, String processkey) {
		TPProcessnode processnode = null;
		hql = "from TPProcessnode t where t.TPProcess.processkey='" + processkey + "' and t.processnodecode='" + taskDefinitionKey + "'";
		List<TPProcessnode> processnodeList = commonDao.findByQueryString(hql);
		if (processnodeList.size() > 0) {
			processnode = processnodeList.get(0);
		}
		return processnode;
	}
	/*
	@authhyzx
	@date2017年12月7日
	*/
	public TPProcessnode getTPProcessnode(String taskDefinitionKey, String processkey,String taskDefinitionName ){
		TPProcessnode processnode = null;
		hql = "from TPProcessnode t where t.TPProcess.processkey='" + processkey + "' and (t.processnodecode='" + taskDefinitionKey + "' or "+ "t.processnodename='" + taskDefinitionName + "')";
		List<TPProcessnode> processnodeList = commonDao.findByQueryString(hql);
		if (processnodeList.size() > 0) {
			processnode = processnodeList.get(0);
		}
		return processnode;
	}


	/**
	 * 获取全部部署流程
	 */
	@Transactional(readOnly = true)
	public List processDefinitionList() {
		return repositoryService.createProcessDefinitionQuery().list();
	}

	//update-begin--Author:zhoujunfeng  Date:20140809 for：流程processkey获取该流程发布的流程-------------------
	/**
	 * 获取流程processkey获取该流程下已经发布的流程
	 */
	@Transactional(readOnly = true)
	public List processDefinitionListByProcesskey(String processkey){
		return repositoryService.createProcessDefinitionQuery().processDefinitionKey(processkey).list();
	}
	//update-end--Author:zhoujunfeng  Date:20140809 for：流程processkey获取该流程发布的流程-------------------

	/**
	 * 根据taskId获取task对象
	 * 
	 * @param taskId
	 * @return
	 */
	public Task getTask(String taskId) {
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}

	/**
	 * 根据taskId封装ProcessHandle对象
	 * 
	 * @param taskId
	 * @return
	 */
	public ProcessHandle getProcessHandle(String taskId) {
		ProcessHandle processHandle = new ProcessHandle();
		Task task = getTask(taskId);// 引擎任务对象
		String businessKey = getBusinessKeyByTask(taskId);// 业务主键
		String processDefinitionKey = getProcessDefinition(task.getProcessDefinitionId()).getKey();
		String taskDefinitionKey = task.getTaskDefinitionKey();// 节点key
		/** 根据流程名称得到流程对象 */
		TPProcess tpProcess = findUniqueByProperty(TPProcess.class, "processkey", processDefinitionKey);
		TPProcessnode tpProcessnode = getTPProcessnode(taskDefinitionKey, processDefinitionKey);
		TPForm tpForm = tpProcessnode.getTPForm(); // 表单对象
		List<TPProcesspro> tpProcesspros = tpProcessnode.getTPProcesspros();// 流程变量
		processHandle.setProcessDefinitionKey(processDefinitionKey);
		processHandle.setTaskDefinitionKey(taskDefinitionKey);
		processHandle.setBusinessKey(businessKey);
		processHandle.setTaskId(taskId);
		processHandle.setTpForm(tpForm);
		processHandle.setTpProcess(tpProcess);
		processHandle.setTpProcessnode(tpProcessnode);
		processHandle.setTpProcesspros(tpProcesspros);
		return processHandle;
	}


	/**
	 * 根据taskId,获取根节点Start的信息
	 * 
	 * @param taskId
	 * @return
	 */
	public TPProcessnode getProcessStartNode(String taskId) {
		Task task = getTask(taskId);// 引擎任务对象
		String processDefinitionKey = getProcessDefinition(task.getProcessDefinitionId()).getKey();
		/** 根据流程名称得到流程对象 */
		TPProcessnode tpProcessnode = getTPProcessnode("start", processDefinitionKey);
		return tpProcessnode;
	}
	private static SqlSession getSqlSession() {
		ProcessEngineImpl processEngine = null;
		DbSqlSessionFactory dbSqlSessionFactory = (DbSqlSessionFactory) processEngine.getProcessEngineConfiguration().getSessionFactories().get(DbSqlSession.class);
		SqlSessionFactory sqlSessionFactory = dbSqlSessionFactory.getSqlSessionFactory();
		return sqlSessionFactory.openSession();
	}


	/**
	 * 根据角色编码和状态值获取审批状态
	 * 
	 * @param prjstate
	 * @param rolecode
	 * @return
	 */
	public TSPrjstatus getTBPrjstatusByCode(String prjstate, String rolecode) {
		TSPrjstatus tsPrjstatus = null;
		String[] rolecodes = rolecode.split(",");
		String search = "";
		for (int i = 0; i < rolecodes.length; i++) {
			search += "'" + rolecodes[i] + "'";
			if (i < rolecodes.length - 1) {
				search += ",";
			}

		}
		if (search != "") {
			List<TSPrjstatus> tbPrjstatuList = commonDao.findByQueryString("from TSPrjstatus p where p.code = '" + prjstate + "' and p.executerole in(" + search + ")");
			if (tbPrjstatuList.size() > 0) {
				tsPrjstatus = tbPrjstatuList.get(0);
			}
		}

		return tsPrjstatus;
	}

	/**
	 * 根据流程编码和业务类名获取业务配置类
	 */
	public TSBusConfig getTSBusConfig(Class classname, String processkey) {
		TSBusConfig tsBusConfig = null;
		String hql = "from TSBusConfig where TSTable.entityName='" + classname.getName() + "' and TPProcess.processkey='" + processkey + "'";
		List<TSBusConfig> tsBusConfigList = commonDao.findByQueryString(hql);
		if (tsBusConfigList.size() > 0) {
			tsBusConfig = tsBusConfigList.get(0);
		}
		return tsBusConfig;

	}


	public <T> void batchDelete(Class<T> entityClass) {
		this.activitiCommonDao.batchDelete(entityClass);
	}
	/**
	 * 查询待办任务-基础代码
	 * @param isPri 是否只查询用户私有的
	 * @param id 标识：username或者rolecode
	 * @param tsBusConfigs
	 * @return
	 */
	private List findBaseTodoTasks_old(boolean isPri,String id,HttpServletRequest request){
		List results = new ArrayList();
		List<Task> tasks = new ArrayList<Task>();
		List<Task> todoList;
		List<Task> unsignedTasks;
		//分页参数
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		Integer start = (page-1)*rows;
		Integer end = page*rows-1;
		try{
			//建立临时集合
			List<Task> tempList = new ArrayList<Task>();
			if(isPri){
				//-update-begin-----------author:zhangdaihao date:20140917 for:待选人模式下任务查询不到---------------------------------------------------
				//TODO 还得修改加分页
				//				TaskService taskService = processEngine.getTaskService();
				//				List<Task> todoListtemp = taskService.createTaskQuery().taskCandidateUser(id).orderByTaskCreateTime().desc().active().list();//.taskCandidateGroup("hr").active().list();
				//				tempList.addAll(todoListtemp);
				//				//-update-end-----------author:zhangdaihao date:20140917 for:待选人模式下任务查询不到---------------------------------------------------
				//				
				//				// 根据当前人的ID查询
				//				/*
				//				 * 注入查询条件
				//				 */
				//				TaskQuery tq = taskService.createTaskQuery().taskAssignee(id).orderByTaskCreateTime().desc().orderByTaskPriority().desc();
				//				tq = installQueryParam(tq,request);
				//				todoList = tq.listPage(start,end);
				//				tempList.addAll(todoList);

				//-update-begin-----------author:zhoujf date:20151207 for:待办任务分页处理---------------------------------------------------
				StringBuilder sb = new StringBuilder("");
				sb.append("select  * ").append("from (");
				sb.append("(select distinct RES.* ");
				sb.append(" from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ ");
				sb.append("WHERE RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' ");
				sb.append("	and ( I.USER_ID_ = #{userid}  or I.GROUP_ID_ IN ( select g.GROUP_ID_ from ACT_ID_MEMBERSHIP g where g.USER_ID_ = #{userid}  ) ");
				sb.append(" ) ").append(" and RES.SUSPENSION_STATE_ = 1 ");
				sb.append(") union ");
				sb.append("(select distinct RES.* ");
				sb.append(" from ACT_RU_TASK RES ");
				sb.append("WHERE RES.ASSIGNEE_ = #{userid} ");
				sb.append(" )) v ");
				sb.append(" order by v.CREATE_TIME_ desc, v.PRIORITY_ desc ");
				//				sb.append("  LIMIT #{start},#{rows} ");
				String dbType = DBTypeUtil.getDBType();
				String sql = MiniDaoUtil.createPageSql(dbType, sb.toString(), page, rows);

				List<Task> pretasks = taskService.createNativeTaskQuery()
						.sql(sql)
						.parameter("userid", id)  
						//		        .parameter("start", start)  
						//		        .parameter("rows", rows)
						.list(); 
				tempList.addAll(pretasks);
				//-update-end-----------author:zhoujf date:20151207 for:待办任务分页处理---------------------------------------------------
			}else{
				// 根据当前人所在的组查询
				/*
				 * 注入查询条件
				 */
				TaskQuery tq = taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList(id.split(","))).orderByTaskCreateTime().desc().orderByTaskPriority().desc();
				tq = installQueryParam(tq,request);
				unsignedTasks = tq.listPage(start,end);
				tempList.addAll(unsignedTasks);
			}
			tasks.addAll(tempList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		// 根据流程的业务ID查询实体并关联
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			// String businessKey = processInstance.getBusinessKey();
			String businessKey = getBusinessKeyByTask(task);
			//-update-begin-----------author:zhoujf date:20150730 for:对象持久化问题导致数据问题---------------------------------------------------
			Class entityClass = MyClassLoader.getClassByScn("org.jeecgframework.workflow.pojo.base.TSBaseBusQuery");// 业务基类
			Object entityObj = getEntity(entityClass, businessKey);
			Object obj = null;
			try {
				obj = entityClass.newInstance();
				MyBeanUtils.copyBeanNotNull2Bean(entityObj, obj);
			} catch (Exception e) {
			}
			if (obj != null) {
				ReflectHelper reflectHelper = new ReflectHelper(obj);
				//				Process process = (Process) reflectHelper.getMethodValue("Process");
				Process process = new Process();
				process.setTask(task);
				process.setProcessInstance(processInstance);
				process.setProcessDefinition(getProcessDefinition(processInstance.getProcessDefinitionId()));
				reflectHelper.setMethodValue("Process", process);
				String userid = (String) reflectHelper.getMethodValue("userid");
				if(userid!=null){
					TSBaseUser baseUser = activitiCommonDao.findUniqueByProperty(TSBaseUser.class, "userName", userid);
					if(baseUser!=null){
						reflectHelper.setMethodValue("userRealName", baseUser.getRealName());
					}
				}
				String assigneeUserId =  task.getAssignee();
				if(assigneeUserId!=null){
					TSBaseUser baseUser = activitiCommonDao.findUniqueByProperty(TSBaseUser.class, "userName", assigneeUserId);
					if(baseUser!=null){
						reflectHelper.setMethodValue("assigneeName", baseUser.getRealName());
					}
				}
				String bpmBizTitle = (String)taskService.getVariable(task.getId(), WorkFlowGlobals.BPM_BIZ_TITLE);
				if(bpmBizTitle!=null){
					reflectHelper.setMethodValue("bpmBizTitle", bpmBizTitle);
				}
				//判断是否超时
				String processnodecode = task.getTaskDefinitionKey();
				String processDefinitionId = task.getProcessDefinitionId();
				String sql = "select node.NODE_TIMEOUT from T_P_PROCESSNODE node where node.PROCESSID = (select tpp.ID from T_P_PROCESS tpp where tpp.PROCESSKEY = ";
				sql += "(select arp.KEY_ from ACT_RE_PROCDEF arp where arp.ID_ = ?)) and node.PROCESSNODECODE = ?";
				List<Map<String, Object>> list = systemService.findForJdbc(sql, processDefinitionId,processnodecode);
				if(list!=null&&list.size()>0){
					Map<String, Object> map = list.get(0);
					Integer nodeTimeout = (Integer)map.get("NODE_TIMEOUT");
					if(checkTimeOut(task.getCreateTime(),nodeTimeout)){
						reflectHelper.setMethodValue("timeoutRemaid", true);
					}else{
						reflectHelper.setMethodValue("timeoutRemaid", false);
					}
				}
				results.add(obj);
				//-update-end-----------author:zhoujf date:20150730 for:对象持久化问题导致数据问题---------------------------------------------------
			} 
			//-update-begin-----------author:zhangdaihao date:20150506 for:屏蔽业务关联查询不到的情况，导致我的任务字段不对应---------------------------------------------------
			//			else {
			//				return tasks;
			//			}
			//-update-begin-----------author:zhangdaihao date:20150506 for:屏蔽业务关联查询不到的情况，导致我的任务字段不对应---------------------------------------------------
		}
		return results;
	}

	private boolean checkTimeOut(Date startTime,Integer timeout){
		boolean flag = false;
		try {
			if(timeout == null||startTime==null){
				return flag;
			}
			Calendar calSrc = Calendar.getInstance();
			Calendar calDes = Calendar.getInstance();
			calDes.setTime(startTime);
			int diff = DateUtils.dateDiff('h', calSrc, calDes);
			if(diff>=timeout){
				flag = true;
			}
		} catch (Exception e) {
		}
		return flag;
	}


	@SuppressWarnings("unchecked")
	public PageList findPriTodoTasks(String userId,HttpServletRequest request,DataGrid dataGrid) {
		return findBaseTodoTasks(true,userId,request,dataGrid);
	}
	@SuppressWarnings("unchecked")
	public PageList findPriTodoTasksAndAgent(String userId,String ids,String roleids,HttpServletRequest request,DataGrid dataGrid) {
		return findBaseTodoTasksAndAgent(true,userId,ids,roleids,request,dataGrid);
	}

	public PageList findGroupTodoTasks(List<TSRoleUser> roles,HttpServletRequest request,DataGrid dataGrid) {
		StringBuilder roleIds = new StringBuilder();
		//用户所在的组可能有多个，需要合并
		for(TSRoleUser role:roles){
			roleIds.append(role.getTSRole().getRoleCode()).append(",");
		}
		roleIds.deleteCharAt(roleIds.length()-1);
		PageList resulttemp = findBaseTodoTasks(false,roleIds.toString(),request,dataGrid);
		return resulttemp;
	}


	public List findHistoryTasks(String userName,HttpServletRequest request) {
		//历史任务直接查询activiti的act_hi_taskinst表
		//分页参数
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		Integer start = (page-1)*rows;
		Query query = getSession().createQuery(installQueryParamH("from ActHiTaskinst o where o.duration>0 and o.assignee = ? ",request));
		query.setFirstResult(start);
		query.setMaxResults(rows);
		query.setString(0, userName);
		installQueryParamHV(query,request,1);
		List result = query.list();
		for(int i=0;i<result.size();i++){
			ActHiTaskinst hi = (ActHiTaskinst)result.get(i);
			hi.setBpmBizTitle(getHiVariable(hi.getProcInstId(),WorkFlowGlobals.BPM_BIZ_TITLE));
		}
		return result;
	}

	//-update-begin-----------author:zhoujf date:20151216 for:增加查询所有的流程历史
	public List findAllHistoryTasks(HttpServletRequest request) {
		//历史任务直接查询activiti的act_hi_taskinst表
		//分页参数
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		Integer start = (page-1)*rows;
		Query query = getSession().createQuery(installQueryParamH("from ActHiActinst o where o.duration>0 ",request));
		query.setFirstResult(start);
		query.setMaxResults(rows);
		installQueryParamHV(query,request,0);
		List result = query.list();
		for(int i=0;i<result.size();i++){
			ActHiActinst hi = (ActHiActinst)result.get(i);
			hi.setBpmBizTitle(getHiVariable(hi.getProcInstId(),WorkFlowGlobals.BPM_BIZ_TITLE));
			hi.setowner(getHiVariable(hi.getProcInstId(),"CREATE_BY"));
		}
		return result;
	}
	//-update-end-----------author:zhoujf date:20151216 for:增加查询所有的流程历史

	private String getHiVariable(String taskId, String variableName){
		StringBuilder sb = new StringBuilder("");
		sb.append("from ActHiVarinst VAR where VAR.procInstId =? and VAR.name = ?");
		Query query = getSession().createQuery(sb.toString());
		query.setString(0, taskId);
		query.setString(1, variableName);
		ActHiVarinst obj = (ActHiVarinst)query.uniqueResult();
		return obj==null?"":obj.getText();
	}


	public Long countPriTodaoTask(String userName, HttpServletRequest request) {
		//查询条件
		//		String procDefId = request.getParameter("Process.processDefinition.id");
		//		String procName = request.getParameter("Process.processDefinition.name");
		Long size = 0L;
		// 根据当前人的ID查询
		//		TaskQuery  tq = taskService.createTaskQuery().taskAssignee(userName).orderByTaskPriority().desc().orderByTaskCreateTime().desc();
		//		installQueryParam( tq,request);
		//		size = tq.count();

		//-update-begin-----------author:zhoujf date:20151207 for:待办任务分页处理---------------------------------------------------
		StringBuilder sb = new StringBuilder("");
		sb.append("select  count(*) ").append("from (");
		sb.append("(select distinct RES.* ").append("from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ ");
		sb.append("WHERE RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' ");
		sb.append("	and ( I.USER_ID_ = #{userid}  or I.GROUP_ID_ IN ( select g.GROUP_ID_ from ACT_ID_MEMBERSHIP g where g.USER_ID_ = #{userid}  ) ");
		sb.append(" ) ").append(" and RES.SUSPENSION_STATE_ = 1 ");
		sb.append(") union ");
		sb.append("(select distinct RES.* ").append("from ACT_RU_TASK RES ");
		sb.append("WHERE RES.ASSIGNEE_ = #{userid} ");
		sb.append(" )) v ");

		size = taskService.createNativeTaskQuery()  
				.sql(sb.toString())
				.parameter("userid", userName)  
				.count(); 
		//-update-end-----------author:zhoujf date:20151207 for:待办任务分页处理---------------------------------------------------
		return size;
	}


	public Long countGroupTodoTasks(List<TSRoleUser> roles, HttpServletRequest request) {
		//查询条件
		String procDefId = request.getParameter("Process.processDefinition.id");
		String procName = request.getParameter("Process.processDefinition.name");
		Long size = 0L;
		StringBuilder roleIds = new StringBuilder();
		//用户所在的组可能有多个，需要合并
		for(TSRoleUser role:roles){
			roleIds.append(role.getTSRole().getRoleCode()).append(",");
		}
		roleIds.deleteCharAt(roleIds.length()-1);
		// 根据当前组的ID查询
		TaskQuery  tq =taskService.createTaskQuery().taskCandidateGroupIn(Arrays.asList(roleIds.toString().split(","))).orderByTaskPriority().desc().orderByTaskCreateTime().desc();
		installQueryParam( tq,request);
		size = tq.count();
		return size;
	}


	@SuppressWarnings("rawtypes")
	public Long countHistoryTasks(String userName, HttpServletRequest request) {
		Map r = systemService.findOneForJdbc(installCountH("select count(1) as hsize  from act_hi_taskinst o inner join act_re_procdef ap on ap.id_ = o.proc_def_id_ and o.duration_>0 and  o.assignee_ = ?",request),installCountHv(userName,request));
		Long size = Long.parseLong(r.get("hsize").toString());
		return size;
	}

	//-update-begin-----------author:zhoujf date:20151216 for:增加查询所有的流程历史
	@SuppressWarnings("rawtypes")
	public Long countAllHistoryTasks(HttpServletRequest request) {
		Map r = systemService.findOneForJdbc(installCountH("select count(1) as hsize  from act_hi_taskinst o inner join act_re_procdef ap on ap.id_ = o.proc_def_id_ and o.duration_>0 ",request),installCountHv(request));
		Long size = Long.parseLong(r.get("hsize").toString());
		return size;
	}
	//-update-end-----------author:zhoujf date:20151216 for:增加查询所有的流程历史
	/**
	 * 拼装过滤条件
	 * @param tq
	 * @param request
	 * @return
	 */
	private TaskQuery installQueryParam(
			TaskQuery tq,HttpServletRequest request) {
		//查询条件
		String procDefId = request.getParameter("Process.processDefinition.id");
		String procName = request.getParameter("Process.processDefinition.name");
		if(StringUtil.isNotEmpty(procDefId)){
			tq = tq.processDefinitionId(procDefId);
		}
		if(StringUtil.isNotEmpty(procName)){
			tq = tq.processDefinitionName(procName);
		}
		return tq;
	}
	/**
	 * 拼装过滤条件
	 * @param string
	 * @param request
	 * @return
	 */
	private String installQueryParamH(String sql, HttpServletRequest request) {
		//查询条件
		String procDefId = request.getParameter("procDefId");
		String procName = request.getParameter("prodef.name");
		StringBuilder s = new StringBuilder(sql);
		if(StringUtil.isNotEmpty(procDefId)){
			s.append(" and o.procDefId = ? ");
		}
		if(StringUtil.isNotEmpty(procName)){
			s.append(" and o.prodef.name = ? ");
		}
		s.append(" order by o.startTime desc ");
		return s.toString();
	}
	private void installQueryParamHV(Query query,HttpServletRequest request,int index) {
		//查询条件
		String procDefId = request.getParameter("procDefId");
		String procName = request.getParameter("prodef.name");
		if(StringUtil.isNotEmpty(procDefId)){
			query.setParameter(index, procDefId);
			index++;
		}
		if(StringUtil.isNotEmpty(procName)){
			query.setParameter(index, procName);
		}
	}

	private String installCountH(String sql, HttpServletRequest request) {
		//查询条件
		String procDefId = request.getParameter("procDefId");
		String procName = request.getParameter("prodef.name");
		StringBuilder s = new StringBuilder(sql);
		if(StringUtil.isNotEmpty(procDefId)){
			s.append(" and o.proc_def_id_ = ? ");
		}
		if(StringUtil.isNotEmpty(procName)){
			s.append(" and ap.name_ = ? ");
		}
		return s.toString();
	}

	private Object[] installCountHv(String userName,HttpServletRequest request) {
		//查询条件
		List reList = new ArrayList(0);
		reList.add(userName);
		String procDefId = request.getParameter("procDefId");
		String procName = request.getParameter("prodef.name");
		if(StringUtil.isNotEmpty(procDefId)){
			reList.add(procDefId);
		}
		if(StringUtil.isNotEmpty(procName)){
			reList.add(procName);
		}
		return reList.toArray();
	}

	private Object[] installCountHv(HttpServletRequest request) {
		//查询条件
		List reList = new ArrayList(0);
		String procDefId = request.getParameter("procDefId");
		String procName = request.getParameter("prodef.name");
		if(StringUtil.isNotEmpty(procDefId)){
			reList.add(procDefId);
		}
		if(StringUtil.isNotEmpty(procName)){
			reList.add(procName);
		}
		return reList.toArray();
	}

	/**
	 * 通过任务节点ID，获取当前节点出发的路径
	 */
	public List<PvmTransition> getOutgoingTransitions(String taskId) {
		List<PvmTransition> outTransitions = null;
		Task task = getTask(taskId);
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(task.getProcessDefinitionId());
		List<ActivityImpl> activitiList = def.getActivities(); // rs是指RepositoryService的实例

		String excId = task.getExecutionId();
		ExecutionEntity execution = (ExecutionEntity) runtimeService
				.createExecutionQuery().executionId(excId).singleResult();
		String activitiId = execution.getActivityId();

		for (ActivityImpl activityImpl : activitiList) {
			String id = activityImpl.getId();
			if (activitiId.equals(id)) {
				System.out.println("当前任务：" + activityImpl.getProperty("name")); // 输出某个节点的某种属性
				outTransitions = activityImpl.getOutgoingTransitions();// 获取从某个节点出来的所有线路
				for (PvmTransition tr : outTransitions) {
					System.out.println("线路名："+tr.getProperty("name"));
					PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
					System.out.println("下一步任务任务：" + ac.getProperty("name"));
				}
				break;
			}
		}
		return outTransitions;
	}


	/**
	 * 查询流程下审批意见
	 */
	public List findBpmLogsByBpmID(String bpm_id) {
		hql = "from TPBpmLog t where t.bpm_id='" + bpm_id + "' order by op_time";
		List<TPBpmLog> logList = commonDao.findByQueryString(hql);
		return logList;
	}

	/**
	 * 查询流程下单一任务节点的审批意见
	 */
	public List findBpmLogsByBpmIDAndTaskID(String bpm_id,String taskName) {
		hql = "from TPBpmLog t where t.bpm_id='" + bpm_id + "' and  t.task_node = '"+taskName+"' ";
		List<TPBpmLog> logList = commonDao.findByQueryString(hql);
		return logList;
	}

	/**
	 * 通过任务节点ID，获取当前节点出发的分支
	 */
	public List getOutTransitions(String taskId) {
		List<PvmTransition> outTransitions = null;
		List<Map> trans = new ArrayList();
		Task task = getTask(taskId);
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
		List<ActivityImpl> activitiList = def.getActivities(); // rs是指RepositoryService的实例

		String excId = task.getExecutionId();
		ExecutionEntity execution = (ExecutionEntity) runtimeService
				.createExecutionQuery().executionId(excId).singleResult();
		String activitiId = execution.getActivityId();

		for (ActivityImpl activityImpl : activitiList) {
			String id = activityImpl.getId();
			if (activitiId.equals(id)) {
				outTransitions = activityImpl.getOutgoingTransitions();// 获取从某个节点出来的所有线路
				for (PvmTransition tr : outTransitions) {
					if(tr.getId()!=null){
						Map m = new HashMap();
						//获取分支线路的名字，如果名字为空则取线路ID
						String name = (String) (oConvertUtils.isNotEmpty(tr.getProperty("name"))?tr.getProperty("name"):tr.getId());
						m.put("Transition", name);
						PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
						m.put("nextnode",ac.getId());
						trans.add(m);
					}
				}
				break;
			}
		}
		return trans;
	}

	/**
	 * 查询流程的历史任务节点
	 */
	public List<Map<String,Object>> getHistTaskNodeList(String proceInsId){
		ActivitiDao activitiDao=ApplicationContextUtil.getContext().getBean(ActivitiDao.class);
		List<Map<String,Object>> list = activitiDao.getHistTaskNodeList(proceInsId);
		return list;
	}
	
	/**
	 * 查询流程的历史任务节点
	 */
	public List<Map<String,Object>> getHistTaskNodeListByReject(String proceInsId){
		ActivitiDao activitiDao=ApplicationContextUtil.getContext().getBean(ActivitiDao.class);
		List<Map<String,Object>> list = activitiDao.getHistTaskNodeListByReject(proceInsId);
		return list;
	}

	
	/**
	 * 查询流程的所有节点
	 * @param taskId
	 * @return
	 */
	public List getAllTaskNode(String taskId) {
		List<Map> list = new ArrayList();
		Task task = getTask(taskId);
		ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(task.getProcessDefinitionId());
		List<ActivityImpl> activitiList = def.getActivities(); // rs是指RepositoryService的实例
		for (ActivityImpl activityImpl : activitiList) {
			Map m = new HashMap();
			String id = activityImpl.getId();
			m.put("taskKey", id);
			String name = (String)activityImpl.getProperty("name");
			m.put("name", name);
			list.add(m);
		}
		return list;
	}

	/**
	 * 根据流程节点ID 和 菜单Id 获取 具有操作权限的按钮Codes
	 * @param nodeId
	 * @param functionId
	 * @return
	 */
	public Set<String> getNodeOperaCodesByNodeIdAndFunctionId(String nodeId, String functionId) {
		Set<String> operationCodes = new HashSet<String>();
		TPProcessnode proNode = commonDao.get(TPProcessnode.class, nodeId);
		CriteriaQuery cq1 = new CriteriaQuery(TPProcessnodeFunction.class);
		cq1.eq("TPProcessnode.id", proNode.getId());
		cq1.eq("TSFunction.id", functionId);
		cq1.add();
		List<TPProcessnodeFunction> rFunctions = getListByCriteriaQuery(cq1, false);
		if (null != rFunctions && rFunctions.size() > 0) {
			TPProcessnodeFunction tsNodeFunction = rFunctions.get(0);
			if (null != tsNodeFunction.getOperation()) {
				String[] operationArry = tsNodeFunction.getOperation().split(",");
				for (int i = 0; i < operationArry.length; i++) {
					operationCodes.add(operationArry[i]);
				}
			}
		}
		return operationCodes;
	}

	/**
	 * 根据流程节点ID和菜单ID获取 具有操作权限的数据规则
	 */
	public Set<String> getOperationCodesByNodeIdAndruleDataId(String nodeId,String functionId) {
		Set<String> operationCodes = new HashSet<String>();
		TPProcessnode proNode = commonDao.get(TPProcessnode.class, nodeId);
		CriteriaQuery cq1 = new CriteriaQuery(TPProcessnodeFunction.class);
		cq1.eq("TPProcessnode.id", proNode.getId());
		cq1.eq("TSFunction.id", functionId);
		cq1.add();
		List<TPProcessnodeFunction> rFunctions = getListByCriteriaQuery(cq1, false);
		if (null != rFunctions && rFunctions.size() > 0) {
			TPProcessnodeFunction tsNodeFunction = rFunctions.get(0);
			if (null != tsNodeFunction.getDataRule()) {
				String[] operationArry = tsNodeFunction.getDataRule().split(",");
				for (int i = 0; i < operationArry.length; i++) {
					operationCodes.add(operationArry[i]);
				}
			}
		}
		return operationCodes;
	}

	@Override
	public void synUserDel(String userId) {
		TSUser user = commonDao.getEntity(TSUser.class, userId);
		if (user == null) {
			return;
		}
		// 删除用户的membership
		List<Group> activitiGroups = identityService.createGroupQuery().groupMember(user.getUserName()).list();
		for (Group group : activitiGroups) {
			logger.debug("delete group from activit: {}", group);
			identityService.deleteMembership(user.getUserName(), group.getId());
		}
		identityService.deleteUser(user.getUserName());

		//记录日志
		String message = "删除用户: " + user.getUserName() + "时同步删除工作流用户  成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
	}
	
	@Override
	public void synUserActive(String userId) {
		TSUser user = commonDao.getEntity(TSUser.class, userId);
		if (user == null) {
			return;
		}
		
		//把用户信息同步到工作流
		synUserToActivity(user);
		
		//把用户角色同步到工作流
		List<TSRoleUser> roleUsers = roleUserService.getRoleUserListByUserId(userId);
		if(roleUsers == null || roleUsers.size()==0) {
			return ;
		}
		
		for(int i=0; i<roleUsers.size(); i++) {
			TSRoleUser ru = roleUsers.get(i);
			synRoleToActivity(ru.getTSRole());
			synMemberShip(ru.getTSUser(),ru.getTSRole());
		}

		//记录日志
		String message = "激活用户: " + user.getUserName() + "时同步工作流用户  成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
	}

	@Override
	public void synRoleDel(String roleId) {
		TSRole role = commonDao.getEntity(TSRole.class, roleId);
		if (role == null) {
			return;
		}
		// 删除角色的membership
		List<User> userList = identityService.createUserQuery().memberOfGroup(role.getRoleCode()).list();
		for (User user : userList) {
			logger.debug("delete user from activit: {}", user);
			identityService.deleteMembership(user.getId(), role.getRoleCode());
		}
		identityService.deleteGroup(role.getRoleCode());

		//记录日志
		String message = "删除或禁用角色: " + role.getRoleCode() + "时同步删除工作流角色  成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
	}

	@Override
	public void synRoleLock(String roleId) {
		synRoleDel(roleId);
	}

	@Override
	public void synRoleUnLock(String roleId) {
		TSRole role = commonDao.getEntity(TSRole.class, roleId);
		if(role == null) {
			return ;
		}

		synRoleToActivity(role);

		List<TSUser> userList = roleService.getUserListForLockRole(roleId);
		if(userList==null || userList.size()==0){
			return;
		}

		for(int i=0;i<userList.size();i++){
			TSUser user = userList.get(i);
			synUserToActivity(user);
			synMemberShip(user, role);
		}

		//记录日志
		String message = "启用角色: " + role.getRoleCode() + "时同步到工作流成功";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
	}

	/**
	 * 仅把角色同步到工作流
	 * @param role
	 */
	private void synRoleToActivity(TSRole role) {
		if(role == null) {
			return;
		}

		GroupQuery groupQuery = identityService.createGroupQuery();
		List<Group> activitiGroups = groupQuery.groupId(role.getRoleCode()).list();
		if (activitiGroups.size() <= 0) {
			Group activitiGroup = identityService.newGroup(role.getRoleCode());
			activitiGroup.setId(role.getRoleCode());
			activitiGroup.setName(role.getRoleName());
			identityService.saveGroup(activitiGroup);
		}else {
			Group group = activitiGroups.get(0);
			group.setName(role.getRoleName());
			identityService.saveGroup(group);
		}
	}

	/**
	 * 仅把用户同步到工作流
	 * @param user
	 */
	private void synUserToActivity(TSUser user) {
		if(user == null) {
			return ;
		}
		//若用户无需同步到工作流，则直接返回
		if(user.getActivitiSync() != 1){
			return;
		}

		UserQuery userQuery = identityService.createUserQuery();
		List<User> activitiUsers = userQuery.userId(user.getUserName()).list();
		if (activitiUsers.size() == 1) {
			cloneAndSaveActivitiUser(user, activitiUsers.get(0));
		} else if (activitiUsers.size() > 1) {
			String errorMsg = "发现重复用户：id=" + user.getUserName();
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		} else {
			saveActivitiUser(user);
		}
	}

	/**
	 * 把指定用户的指定角色同步到工作流
	 * @param user
	 * @param role
	 */
	private void synMemberShip(TSUser user,TSRole role) {
		if(user == null) {
			return ;
		}

		if(role == null) {
			return;
		}

		identityService.deleteMembership(user.getUserName(), role.getRoleCode());
		identityService.createMembership(user.getUserName(), role.getRoleCode());
	}

	@Override
	public void synRoleUser(String roleId, String userOrgIds) {
		if(roleId == null || "".equals(roleId)){
			return;
		}
		if(userOrgIds == null || "".equals(userOrgIds)){
			return;
		}

		TSRole role = commonDao.getEntity(TSRole.class, roleId);
		if(role == null) {
			return;
		}

		//已禁用角色不做同步操作
		if("0".equals(role.getStatus())){
			return;
		}
		synRoleToActivity(role);

		String[] u = userOrgIds.split(",");
		for(int i=0;i<u.length;i++) {
			TSUserOrg userOrg = commonDao.getEntity(TSUserOrg.class, u[i]);
			if(userOrg == null) {
				continue;
			}
			synUserToActivity(userOrg.getTsUser());
			synMemberShip(userOrg.getTsUser(), role);
		}

	}
	
	@Override
	public void synRoleUserDel(String roleUserId) {
		if(roleUserId == null || "".equals(roleUserId)){
			return;
		}
		
		TSRoleUser roleUser = commonDao.getEntity(TSRoleUser.class, roleUserId);
		if(roleUser == null) {
			return;
		}
		identityService.deleteMembership(roleUser.getTSUser().getUserName(), roleUser.getTSRole().getRoleCode());
	}
	/**
	 * Horse_little
	 * 2017-09-05
	 * 通过流程Key获取定义的任务节点
	 */
	public List<TPProcessnode> getTPProcessnodeByProcessKey(String processkey) {
		TPProcessnode processnode = null;
		hql = "from TPProcessnode t where t.TPProcess.processkey='" + processkey + "'";
		List<TPProcessnode> processnodeList = commonDao.findByQueryString(hql);
		if (processnodeList.size() > 0) {
			return processnodeList;
		}
		return null;
	}
	/**
	 * Horse_little
	 * 2017-09-15
	 * 获取用户已办任务列表
	 */
	public PageList findMyAreadyDoTasks(String userName,HttpServletRequest request, DataGrid dataGrid) {
		//历史任务直接查询activiti的act_hi_taskinst表
		//分页参数
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		Integer start = (page-1)*rows;
		StringBuffer sb = new StringBuffer(100);
		/*sb.append("select '1' as id,cc.PROC_INST_ID_ as proInslprocInstId,cc.TEXT_ as bpmBizTitle,aa.ASSIGNEE_ as assignee,aa.ACT_NAME_ as name,");
		sb.append("to_char(aa.START_TIME_,'yyyy-mm-dd  hh24:mi:ss') as acceptTime,to_char(aa.END_TIME_ ,'yyyy-mm-dd  hh24:mi:ss') as dealTime,bb.START_USER_ID_ as proInslstartUserId,to_char(bb.START_TIME_,'yyyy-mm-dd  hh24:mi:ss') as startTime,to_char(bb.END_TIME_,'yyyy-mm-dd  hh24:mi:ss') as endTime ,");
		sb.append("bb.PRO_INST_STATUS as proInslstatus,");
		sb.append("dd.act_name_ as currenttask, ");
		sb.append("     dd.ASSIGNEE_ as currentassignee, ");
		sb.append("    to_char(dd.start_time_,'yyyy-mm-dd  hh24:mi:ss') as currentstarttime ");
		sb.append(" from (SELECT a.START_TIME_, ");
		sb.append("             a.END_TIME_, ");
		sb.append("             a.ACT_NAME_, ");
		sb.append("           a11.PROC_INST_ID_, ");
		sb.append("            a11.ASSIGNEE_ ");
		sb.append("       from ACT_HI_ACTINST a, ");
		sb.append("            (select a1.ASSIGNEE_, a1.PROC_INST_ID_, MAX(a1.ID_) as ID ");
		sb.append("               FROM ACT_HI_ACTINST a1 ");
		sb.append("               WHERE a1.DURATION_ > 0 ");
		sb.append("                 and a1.ASSIGNEE_='"+userName+"' ");
		sb.append("              group by a1.PROC_INST_ID_, a1.ASSIGNEE_) a11 where a.ID_ = a11.ID) aa, ");
		sb.append("               (SELECT d.start_time_,case when d.ACT_NAME_='End' then '已完成' else to_char(ACT_NAME_) END as ACT_NAME_, ");
		sb.append("             d11.PROC_INST_ID_, ");
		sb.append("              d.ASSIGNEE_ ");
		sb.append("         from ACT_HI_ACTINST d, ");
		sb.append("              (select d1.PROC_INST_ID_, MAX(d1.ID_) as ID ");
		sb.append("                FROM ACT_HI_ACTINST d1 ");
		sb.append("                group by d1.PROC_INST_ID_) d11 where d.ID_ = d11.ID) dd, ");
		sb.append("       (select b.START_USER_ID_, ");
		sb.append("               b.START_TIME_, ");
		sb.append("               END_TIME_, ");
		sb.append("               CASE ");
		sb.append("                 WHEN b.END_TIME_ IS NULL THEN ");
		sb.append("	                  '运行中' ");
		sb.append("	                 ELSE ");
		sb.append("	                  '结束' ");
		sb.append("	               END as PRO_INST_STATUS, ");
		sb.append("	               b.PROC_INST_ID_ ");
		sb.append("	          from ACT_HI_PROCINST b) bb, ");
		sb.append("	       (select TEXT_, PROC_INST_ID_ ");
		sb.append("	          from ACT_HI_DETAIL ");
		sb.append("	         where NAME_ = 'bpm_biz_title') cc where bb.PROC_INST_ID_ = aa.PROC_INST_ID_ and bb.PROC_INST_ID_ = cc.PROC_INST_ID_ ");
		sb.append("	         and bb.PROC_INST_ID_ = dd.PROC_INST_ID_ ");
		sb.append("	 order by cc.PROC_INST_ID_ desc ");*/



		sb.append("	 select  '1' as id, ");
		sb.append("	        cc.PROC_INST_ID_ as proInslprocInstId, ");
		sb.append("	        cc.TEXT_ as bpmBizTitle, ");
		sb.append("	        aa.ASSIGNEE_ as assignee, ");
		sb.append("	        aa.ACT_NAME_ as name, ");
		sb.append("	        to_char(aa.START_TIME_, 'yyyy-mm-dd  hh24:mi:ss') as acceptTime, ");
		sb.append("	        to_char(aa.END_TIME_, 'yyyy-mm-dd  hh24:mi:ss') as dealTime, ");
		sb.append("	        bb.START_USER_ID_ as proInslstartUserId, ");
		sb.append("	        to_char(bb.START_TIME_, 'yyyy-mm-dd  hh24:mi:ss') as startTime, ");
		sb.append("	        to_char(bb.END_TIME_, 'yyyy-mm-dd  hh24:mi:ss') as endTime, ");
		sb.append("	        bb.PRO_INST_STATUS as proInslstatus, ");
		sb.append("	        dd.act_name_ as currenttask, ");
		sb.append("	        nvl(dd.ASSIGNEE_,ee.ASSIGNEE_) as currentassignee, ");
		sb.append("	        to_char(dd.start_time_, 'yyyy-mm-dd  hh24:mi:ss') as currentstarttime ");
		sb.append("  from (select proc_inst_id_, max(start_time_) start_time_");
		sb.append("   from act_hi_taskinst a1");
		sb.append("   where a1.assignee_ = '"+userName+"'");
		sb.append("   group by proc_inst_id_) a11,");
		sb.append("   act_hi_taskinst a1,");
		sb.append("  act_id_user a3");
		sb.append("  where a11.start_time_ = a1.start_time_");
		sb.append("  and a11.proc_inst_id_ = a1.proc_inst_id_");
		sb.append("  and a3.id_ = a1.assignee_) aa ,");
		sb.append("	          ( ");
		sb.append("	          SELECT d1.start_time_, ");
		sb.append("	                case ");
		sb.append("	                  when d1.NAME_ = 'End' then ");
		sb.append("	                   '已完成' ");
		sb.append("	                  else ");
		sb.append("	                   to_char(NAME_) ");
		sb.append("	                END as ACT_NAME_, ");
		sb.append("	                d1.PROC_INST_ID_ as proc_inst_id_, ");
		sb.append("	                d2.first_ as ASSIGNEE_ ");
		sb.append("	           from  ");
		sb.append("	                (select d.PROC_INST_ID_, MAX(d.start_time_) as start_time_ ");
		sb.append("	                   FROM act_hi_taskinst d ");
		sb.append("	                  group by d.PROC_INST_ID_) d3,act_hi_taskinst d1 left join act_id_user d2 ");
		sb.append("	                  on d1.assignee_ = d2.id_ ");
		sb.append("	          where d1.start_time_ = d3.start_time_ ");
		sb.append("	          ) dd, ");
		sb.append("	        (select b2.first_ as START_USER_ID_, ");
		sb.append("	                b.START_TIME_, ");
		sb.append("	                END_TIME_, ");
		sb.append("	                CASE ");
		sb.append("	                  WHEN b.END_TIME_ IS NULL THEN ");
		sb.append("	                   '运行中' ");
		sb.append("	                  ELSE ");
		sb.append("	                   '结束' ");
		sb.append("	                END as PRO_INST_STATUS, ");
		sb.append("	                b.PROC_INST_ID_ ");
		sb.append("	           from ACT_HI_PROCINST b,act_id_user b2 where b.START_USER_ID_=b2.id_ ) bb, ");
		sb.append("	        (select TEXT_, PROC_INST_ID_ ");
		sb.append("	           from ACT_HI_DETAIL ");
		sb.append("	          where NAME_ = 'bpm_biz_title' ) cc, ");
		sb.append("	          ( ");
		sb.append("	          select wm_concat(ASSIGNEE_) ASSIGNEE_,proc_inst_id_ proc_inst_id_ from ( ");
		sb.append("	               select distinct to_char(e2.first_) as ASSIGNEE_, ");
		sb.append("	                      e3.proc_inst_id_ as proc_inst_id_ ");
		sb.append("	                 from act_hi_identitylink e1, act_id_user e2, (    select d2.id_ as id_,d2.proc_inst_id_ as proc_inst_id_ from  ");
		sb.append("	          (select d.PROC_INST_ID_, MAX(d.start_time_) as start_time_ ");
		sb.append("	                   FROM act_hi_taskinst d group by d.PROC_INST_ID_) d1,act_hi_taskinst d2  ");
		sb.append("	                  where d1.start_time_ = d2.start_time_ and d1.proc_inst_id_ = d2.proc_inst_id_) e3 ");
		sb.append("	                where e3.id_ = e1.task_id_ ");
		sb.append("	                  and e1.type_ = 'candidate' ");
		sb.append("	                  and e1.user_id_ = e2.id_ ) group by proc_inst_id_ ");

		sb.append("	 ) ee ");
		sb.append("	  where bb.PROC_INST_ID_ = aa.PROC_INST_ID_ ");
		sb.append("	    and bb.PROC_INST_ID_ = cc.PROC_INST_ID_ ");
		sb.append("	    and bb.proc_inst_id_ = dd.proc_inst_id_ ");
		sb.append("	    and bb.proc_inst_id_ = ee.proc_inst_id_ ");

		sb.append("	  order by cc.PROC_INST_ID_ desc ");
		HqlQuery hqlQuery = new HqlQuery("");
		hqlQuery.setQueryString(sb.toString());
		hqlQuery.setDataGrid(dataGrid);
		hqlQuery.setClass1(org.jeecgframework.workflow.pojo.activiti.ActMyAreadyDoTask.class);
		hqlQuery.setCurPage(page);
		hqlQuery.setPageSize(rows);
		PageList myAreadyDoTaskList = commonDao.getPageListBySql(hqlQuery,true);
		return myAreadyDoTaskList;
	}

	@Override
	/**
	 * Horse_little
	 * 2017-09-15
	 * 获取用户已办任务列表
	 */
	public PageList findMyStartTasks(String userName,HttpServletRequest request, DataGrid dataGrid) {
		//历史任务直接查询activiti的act_hi_taskinst表
		//分页参数
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		Integer start = (page-1)*rows;
		StringBuffer sb = new StringBuffer(100);
		sb.append("select '1' as id,cc.PROC_INST_ID_ as proInslprocInstId,cc.TEXT_ as bpmBizTitle,");
		sb.append("bb.START_USER_ID_ as proInslstartUserId,to_char(bb.START_TIME_,'yyyy-mm-dd  hh24:mi:ss') as startTime,to_char(bb.END_TIME_,'yyyy-mm-dd  hh24:mi:ss') as endTime ,");
		sb.append("bb.PRO_INST_STATUS as proInslstatus,");
		sb.append("dd.act_name_ as currenttask, ");
		sb.append("     dd.ASSIGNEE_ as currentassignee, ");
		sb.append("    to_char(dd.start_time_,'yyyy-mm-dd  hh24:mi:ss') as currentstarttime ");
		sb.append(" from ");
		sb.append("               (SELECT d.start_time_,case when d.ACT_NAME_='End' then '已完成' else to_char(ACT_NAME_) END as ACT_NAME_, ");
		sb.append("             d11.PROC_INST_ID_, ");
		sb.append("              d.ASSIGNEE_ ");
		sb.append("         from ACT_HI_ACTINST d, ");
		sb.append("              (select d1.PROC_INST_ID_, MAX(d1.ID_) as ID ");
		sb.append("                FROM ACT_HI_ACTINST d1 ");
		sb.append("                group by d1.PROC_INST_ID_) d11 where d.ID_ = d11.ID) dd, ");
		sb.append("       (select b.START_USER_ID_, ");
		sb.append("               b.START_TIME_, ");
		sb.append("               END_TIME_, ");
		sb.append("               CASE ");
		sb.append("                 WHEN b.END_TIME_ IS NULL THEN ");
		sb.append("	                  '运行中' ");
		sb.append("	                 ELSE ");
		sb.append("	                  '结束' ");
		sb.append("	               END as PRO_INST_STATUS, ");
		sb.append("	               b.PROC_INST_ID_ ");
		sb.append("	          from ACT_HI_PROCINST b  ,t_s_base_user c ");
		sb.append("	           where (b.START_USER_ID_ = c.username or b.start_user_id_ = c.id) and c.username = '").append(userName).append("' ) bb , ");
		sb.append("	       (select TEXT_, PROC_INST_ID_ ");
		sb.append("	          from ACT_HI_DETAIL ");
		sb.append("	         where NAME_ = 'bpm_biz_title') cc where bb.PROC_INST_ID_ = cc.PROC_INST_ID_ ");
		sb.append("	         and bb.PROC_INST_ID_ = dd.PROC_INST_ID_ ");
		sb.append("	 order by cc.PROC_INST_ID_ desc ");
		HqlQuery hqlQuery = new HqlQuery("");
		hqlQuery.setQueryString(sb.toString());
		hqlQuery.setDataGrid(dataGrid);
		hqlQuery.setClass1(org.jeecgframework.workflow.pojo.activiti.ActMyAreadyDoTask.class);
		hqlQuery.setCurPage(page);
		hqlQuery.setPageSize(rows);
		PageList myAreadyDoTaskList = commonDao.getPageListBySql(hqlQuery,true);
		return myAreadyDoTaskList;
	}

	/**
	 * 查询待办任务-基础代码
	 * @param isPri 是否只查询用户私有的
	 * @param id 标识：username或者rolecode
	 * @param tsBusConfigs
	 * @author Horse_Little
	 * @day	2017-09-25
	 * @return
	 */
	public PageList findBaseTodoTasks(boolean isPri,String id,HttpServletRequest request, DataGrid dataGrid){
		//分页参数
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		Integer start = (page-1)*rows;
		Integer end = page*rows-1;
		String roleids = request.getSession().getAttribute("roleids").toString();
		if (roleids.indexOf(",")>-1){
			roleids = roleids.replaceAll(",", "','");
		}
		TSUser loginUser = userService.getUserByLoginName(id);
		List<TSDepart> departList = userService.getUserNextDepartList(loginUser.getId());
		StringBuilder sb1 = new StringBuilder("");
		for(int i=0;i<departList.size();i++){
			sb1.append(departList.get(i).getId()+",");
		}
		String departId=sb1.toString();
		departId=departId.substring(0, departId.length()-1);
		if (departId.indexOf(",")>-1){
			departId = departId.replaceAll(",", "','");
		}
		//建立临时集合
		if(isPri){
			StringBuilder sb = new StringBuilder("");
			sb.append("select ");
			sb.append(" rvt.TEXT_ as bpmBizTitle, ");
			sb.append("v.proc_def_id_ as processProcessDefinitionId, ");
			sb.append("w.processname as processProcessDefinitionName, ");
			sb.append("v.proc_inst_id_ as processTaskProcessInstanceId, ");
			sb.append(" n.realname as userRealName, ");
			sb.append( "m.realname as assigneeName, ");
			sb.append("to_char(v.create_time_,'yyyy-mm-dd  hh24:mi:ss') as processTaskCreateTime, ");
			sb.append("to_char(v.due_date_,'yyyy-mm-dd  hh24:mi:ss') as processTaskDueTime, ");
			sb.append("'办理中'  as tSPrjstatusDescription, ");
			sb.append(" v.name_ as processTaskName, ");
			sb.append(" v.ID_ as processTaskId, ");
			sb.append("w.processkey as processTaskTaskDefinitionKey, ");
			sb.append(" t.TEXT_ as id, ");
			//对超时时间进行判断,node_timeout为填写节点时的设置超时时间(单位为：天)
			//红黄绿灯 start
			sb.append("  (select case ");
			sb.append("  when max(node.node_timeout) is null then ");
			sb.append("  'images/listwork/warn_green.png' ");
			sb.append("  when sysdate - v.create_time_ >= max(node.node_timeout) then ");
			sb.append("  'images/listwork/warn_red.png' ");
			sb.append("  when sysdate - v.create_time_ >= max(node.node_timeout) - 2 and sysdate - v.create_time_ < max(node.node_timeout) then ");
			sb.append("  'images/listwork/warn_yellow.png' ");
			sb.append("  when sysdate - v.create_time_ < max(node.node_timeout) - 2 then ");
			sb.append("  'images/listwork/warn_green.png' ");
			sb.append("  else ");
			sb.append("  'images/listwork/warn_green.png' "); 
			sb.append("  end ");
			sb.append("  from T_P_PROCESSNODE node ");
			sb.append("  where node.processid = w.id ");
			sb.append("  and node.processnodecode = v.task_def_key_) as imagePath, ");
			sb.append("  (select case ");
			sb.append("  when max(node.node_timeout) is null then ");
			sb.append("  '无限制' ");
			sb.append("  when sysdate - v.create_time_ >= max(node.node_timeout) then ");
			sb.append("  '逾期'|| rtrim(to_char(trunc(sysdate - v.create_time_ - max(node.node_timeout),2),'fm9999990.99'),'.') || '天' "); 
			sb.append("  when sysdate - v.create_time_ < max(node.node_timeout) then ");
			sb.append("  '剩余'|| rtrim(to_char(trunc(max(node.node_timeout) - (sysdate - v.create_time_),2),'fm9999990.99'),'.') || '天' ");
			sb.append("  else ");
			sb.append("   '无限制' ");
			sb.append("   end ");
			sb.append("   from T_P_PROCESSNODE node ");
			sb.append("   where node.processid = w.id ");
			sb.append("    and node.processnodecode = v.task_def_key_) as daynums, ");
			sb.append("   (select  node.node_timeout from T_P_PROCESSNODE node where node.processid = w.id");
			sb.append("           and node.processnodecode = v.task_def_key_ )as jiedays,");
			//红黄绿灯end
			sb.append(" 'closed' as state ");
			sb.append("  from ((select distinct RES.* ");
			sb.append("           from ACT_RU_TASK         RES, ");
			sb.append("                ACT_RU_IDENTITYLINK I ");
			sb.append("          WHERE RES.ASSIGNEE_ is null ");
			sb.append("            and I.TASK_ID_ = RES.ID_ ");
			sb.append("            and I.TYPE_ = 'candidate' ");
			sb.append("            and (I.USER_ID_ = '"+id+"' or I.GROUP_ID_ in ('"+roleids+"'))");
			sb.append("            and RES.SUSPENSION_STATE_ = 1) ");
			sb.append("        union ");
			sb.append("        (select distinct RES.* ");
			sb.append("           from ACT_RU_TASK RES ");
			sb.append("          WHERE RES.ASSIGNEE_ = '"+id+"') ");
			sb.append("       ) v,t_p_process w,t_s_base_user m,act_ru_variable t,act_ru_variable rvt,ACT_RU_IDENTITYLINK q,t_s_base_user n,t_s_depart de,t_s_user_org uo ");
			sb.append("       where w.processkey = substr(v.proc_def_id_,0,instr(v.proc_def_id_,':')-1) ");
			sb.append("       and m.username = '"+id+"' and t.proc_inst_id_= v.proc_inst_id_ and t.name_ = 'ID'  and rvt.proc_inst_id_= v.proc_inst_id_ and rvt.name_ = 'bpm_biz_title' and q.proc_inst_id_ = v.Proc_inst_ID_ ");
			sb.append("       and (N.username = Q.USER_ID_ OR N.Id=Q.USER_ID_) and q.type_='starter' and n.id=uo.user_id and uo.org_id=de.id and de.id in ('"+departId+"')");
			sb.append(" order by v.CREATE_TIME_ desc, v.PRIORITY_ desc ");
			HqlQuery hqlQuery = new HqlQuery("");
			hqlQuery.setQueryString(sb.toString());
			hqlQuery.setDataGrid(dataGrid);
			hqlQuery.setClass1(org.jeecgframework.workflow.pojo.activiti.ActRuWaitDeal.class);
			hqlQuery.setCurPage(page);
			hqlQuery.setPageSize(rows);
			PageList myAreadyDoTaskList = commonDao.getPageListBySql(hqlQuery,true);
			return myAreadyDoTaskList;
		}
		return null;
	}

	/**
	 * 查询待办任务-基础代码
	 * @param isPri 是否只查询用户私有的和代理的人的
	 * @param id 标识：username或者rolecode
	 * @param tsBusConfigs
	 * @author Horse_Little
	 * @day	2017-09-25
	 * @return
	 */
	public PageList findBaseTodoTasksAndAgent(boolean isPri,String id,String ids,String roleids,HttpServletRequest request, DataGrid dataGrid){
		//分页参数
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		Integer start = (page-1)*rows;
		Integer end = page*rows-1;
		String roleids1 = request.getSession().getAttribute("roleids").toString();
		roleids=roleids+roleids1;
		if (roleids.indexOf(",")>-1){
			roleids = roleids.replaceAll(",", "','");
		}
		List<TSDepart> departList = userService.getUserAndAgentNextDepartList(ids);
		if (ids.indexOf(",")>-1){
			ids = ids.replaceAll(",", "','");
		}
		StringBuilder sb1 = new StringBuilder("");
		for(int i=0;i<departList.size();i++){
			sb1.append(departList.get(i).getId()+",");
		}
		String departId=sb1.toString();
		departId=departId.substring(0, departId.length()-1);
		if (departId.indexOf(",")>-1){
			departId = departId.replaceAll(",", "','");
		}
		//建立临时集合
		if(isPri){
			StringBuilder sb = new StringBuilder("");
			sb.append("select ");
			sb.append(" rvt.TEXT_ as bpmBizTitle, ");
			sb.append("v.proc_def_id_ as processProcessDefinitionId, ");
			sb.append("w.processname as processProcessDefinitionName, ");
			sb.append("v.proc_inst_id_ as processTaskProcessInstanceId, ");
			sb.append(" n.realname as userRealName, ");
			sb.append("'"+ id+"' as assigneeName, ");
			sb.append("to_char(v.create_time_,'yyyy-mm-dd  hh24:mi:ss') as processTaskCreateTime, ");
			sb.append("to_char(v.due_date_,'yyyy-mm-dd  hh24:mi:ss') as processTaskDueTime, ");
			sb.append("'办理中'  as tSPrjstatusDescription, ");
			sb.append(" v.name_ as processTaskName, ");
			sb.append(" v.ID_ as processTaskId, ");
			sb.append("w.processkey as processTaskTaskDefinitionKey, ");
			sb.append("  proinst.business_key_ as id, ");
			//对超时时间进行判断,node_timeout为填写节点时的设置超时时间(单位为：天)
			//红黄绿灯 start
			sb.append("  (select case ");
			sb.append("  when max(node.node_timeout) is null then ");
			sb.append("  'images/listwork/warn_green.png' ");
			sb.append("  when sysdate - v.create_time_ >= max(node.node_timeout) then ");
			sb.append("  'images/listwork/warn_red.png' ");
			sb.append("  when sysdate - v.create_time_ >= max(node.node_timeout) - 2 and sysdate - v.create_time_ < max(node.node_timeout) then ");
			sb.append("  'images/listwork/warn_yellow.png' ");
			sb.append("  when sysdate - v.create_time_ < max(node.node_timeout) - 2 then ");
			sb.append("  'images/listwork/warn_green.png' ");
			sb.append("  else ");
			sb.append("  'images/listwork/warn_green.png' ");
			sb.append("  end ");
			sb.append("  from T_P_PROCESSNODE node ");
			sb.append("  where node.processid = w.id ");
			sb.append("  and node.processnodecode = v.task_def_key_) as imagePath, ");
			sb.append("  (select case ");
			sb.append("  when max(node.node_timeout) is null then ");
			sb.append("  '无限制' ");
			sb.append("  when sysdate - v.create_time_ >= max(node.node_timeout) then ");
			sb.append("  '逾期'|| rtrim(to_char(trunc(sysdate - v.create_time_ - max(node.node_timeout),2),'fm9999990.99'),'.') || '天' "); 
			sb.append("  when sysdate - v.create_time_ < max(node.node_timeout) then ");
			sb.append("  '剩余'|| rtrim(to_char(trunc(max(node.node_timeout) - (sysdate - v.create_time_),2),'fm9999990.99'),'.') || '天' ");
			sb.append("  else ");
			sb.append("   '无限制' ");
			sb.append("   end ");
			sb.append("   from T_P_PROCESSNODE node ");
			sb.append("   where node.processid = w.id ");
			sb.append("    and node.processnodecode = v.task_def_key_) as daynums, ");
			sb.append("   (select  node.node_timeout from T_P_PROCESSNODE node where node.processid = w.id");
			sb.append("           and node.processnodecode = v.task_def_key_ )as jiedays,");
			//红黄绿灯end
			sb.append(" 'closed' as state ");
			sb.append("  from ((select distinct RES.* ");
			sb.append("           from ACT_RU_TASK         RES, ");
			sb.append("                ACT_RU_IDENTITYLINK I ");
			sb.append("          WHERE RES.ASSIGNEE_ is null ");
			sb.append("            and I.TASK_ID_ = RES.ID_ ");
			sb.append("            and I.TYPE_ = 'candidate' ");
			sb.append("            and (I.USER_ID_ in ('"+ids+"') or I.GROUP_ID_ in ('"+roleids+"'))");
			sb.append("            and RES.SUSPENSION_STATE_ = 1) ");
			sb.append("        union all");
			sb.append("        (select distinct RES.* ");
			sb.append("           from ACT_RU_TASK RES ");
			sb.append("          WHERE RES.ASSIGNEE_ in ('"+ids+"')) ");
			sb.append("       ) v,t_p_process w,act_hi_procinst proinst,act_ru_variable rvt,t_s_base_user n,t_s_depart de,t_s_user_org uo ");
			sb.append("       where v.proc_inst_id_ = proinst.proc_inst_id_ and w.processkey = substr(v.proc_def_id_,0,instr(v.proc_def_id_,':')-1) ");
			sb.append("       and rvt.proc_inst_id_ = v.proc_inst_id_ and  rvt.name_ = 'bpm_biz_title' ");
			sb.append("       and (N.username =  proinst.start_user_id_ OR N.Id=proinst.start_user_id_) and n.id=uo.user_id and uo.org_id=de.id and de.id in ('"+departId+"')");
			sb.append(" order by v.CREATE_TIME_ desc, v.PRIORITY_ desc ");
			HqlQuery hqlQuery = new HqlQuery("");
			hqlQuery.setQueryString(sb.toString());
			hqlQuery.setDataGrid(dataGrid);
			hqlQuery.setClass1(org.jeecgframework.workflow.pojo.activiti.ActRuWaitDeal.class);
			hqlQuery.setCurPage(page);
			hqlQuery.setPageSize(rows);
			PageList myAreadyDoTaskList = commonDao.getPageListBySql(hqlQuery,true);
			return myAreadyDoTaskList;
		}
		return null;
	}

	@Override
	public String getActHiProcinst(String procInstId){
		StringBuilder sb = new StringBuilder("");
		sb.append("from ActHiProcinst VAR where VAR.procInstId =?");
		Query query = getSession().createQuery(sb.toString());
		query.setString(0, procInstId);
		ActHiProcinst obj = (ActHiProcinst)query.uniqueResult();
		return obj==null?"":obj.getBusinessKey();
	}

	@Override
	public String getNextTaskNameAndAssignee(String procInstId) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer(100);
		sb.append(" select a.processnodename from T_P_PROCESSNODE a,t_p_process b,ACT_RU_TASK c where a.processnodecode = c.task_def_key_ ");
		sb.append("          and a.processid = b.id and b.processkey = substr(proc_def_id_,0,instr(proc_def_id_,':')-1)  ");
		sb.append("          ");
		sb.append("         and c.proc_inst_id_="+procInstId);
		List<String> list1 = this.findListbySql(sb.toString());
		if (list1==null||list1.size()<=0)
			return "";
		//查询任务节点下处理人
		sb.delete(0, sb.length());
		sb.append("          select to_char(wm_concat(to_char(c.first_)))from ACT_RU_IDENTITYLINK a,ACT_RU_TASK b,act_id_user c ");
		sb.append("           where a.task_id_=b.id_  ");
		sb.append("          and a.type_='candidate' and a.user_id_ = c.id_ ");
		sb.append("         and b.proc_inst_id_ ="+procInstId);
		List<String> list2 = this.findListbySql(sb.toString());
		//当只有一个处理人时，在运行task表中是签收人，没有节点处理人
		if(list2.size()>0&&null==list2.get(0))
		{
			sb.delete(0, sb.length());
			sb.append(" select  c.first_ from act_id_user c , ");
			sb.append(" ( select  assignee_ from  act_ru_task where proc_inst_id_ ="+procInstId+" ) b ");
			sb.append(" where c.id_ = b.assignee_ " );
			list2 = this.findListbySql(sb.toString());
		}
		/*if (list2==null||list2.size()<=0||list2.size()>0&&null==list2.get(0))
			return "";*/
		if(null!=list2&&list2.size()>0)
		{
		     return list1.get(0).toString()+"@"+list2.get(0).toString();
		}  		    
		else
		    return list1.get(0).toString(); 
	}

	/**
	 * 根据当前节点的taskId 获取角色
	 */
	public List<String> getRoleByCurrentTaskId(String taskId) {
		String hql = "select  a.groupId from  ActRuIdentitylink a where a.actRuTask.id =? and a.type = 'candidate' and a.groupId is not null";
		
		return commonDao.findHql(hql, taskId);
	}

	/**
	 * 根据当前节点的角色和用户的部门查询可以被委托的用户
	 * 不包含自己
	 */
	public List<ActIdUser> getActIdUserByRoleAndDept(String roleIds,String departIds,String userName,ActIdUser actIdUser) {
		
		String hql = "select u from ActIdUser u where exists "
				+ "(select 1 from ActIdMembership m where u.id = m.actIdUser.id and actIdGroup.id in (" + roleIds + ")) "
				+ "and exists(from TSUserOrg o,TSBaseUser bu where o.tsUser.id = bu.id "
				+ "and bu.userName = u.id and o.tsDepart.id in (" + departIds + ")) "
						+ "and u.id <> '"+ userName +"'";
		
		if(null != actIdUser.getId() && actIdUser.getId() != ""){
			hql += "and u.id like '%" + actIdUser.getId()+"%'";
		}
		
		if(null != actIdUser.getLast() && actIdUser.getLast() != ""){
			
			hql += "and u.last like '%" + actIdUser.getLast()+"%'";
		}
		return commonDao.findHql(hql, null);
	}

	/**
	 * 根据当前节点的用户的部门查询可以被委托的用户
	 * 不包含自己
	 */
	public List<ActIdUser> getActIdUserByDept(String departIds,String userName,ActIdUser actIdUser) {
		
		String hql = "select u from ActIdUser u where "
				+ " exists(from TSUserOrg o,TSBaseUser bu where o.tsUser.id = bu.id "
				+ "and bu.userName = u.id and o.tsDepart.id in (" + departIds + ")) "
						+ "and u.id <> '"+ userName +"'";
		
		if(null != actIdUser.getId() && actIdUser.getId() != ""){
			hql += "and u.id like '%" + actIdUser.getId()+"%'";
		}
		
		if(null != actIdUser.getLast() && actIdUser.getLast() != ""){
			
			hql += "and u.last like '%" + actIdUser.getLast()+"%'";
		}
		return commonDao.findHql(hql, null);
	}

	@Override
	public TPProcess getProcessNameByProcessId(String proceId) {
		return activitiDao.getProcessNameByProcessId(proceId);
	}
    //根据流程实例id|任务名称id 查询历史任务 最新处理时间排序
	@Override
	public List<ActHiTaskinst> getHisTaskByProId(String proceInsId ,String taskName) {
		
		String hql = "from ActHiTaskinst t where t.procInstId =? and t.name= ? and t.deleteReason ='completed' order by t.endTime";
		
		
		return commonDao.findHql(hql, proceInsId,taskName);
	}
	
	
	//根据流程实例id|任务定义id 查询历史任务 最新开始时间排序
	@Override
	public List<ActHiTaskinst> getHisTaskByProIdAndTaskDefinId(String proceInsId ,String taskDefDey) {
		String hql = "from ActHiTaskinst t where t.procInstId =? and t.taskDefKey= ?  order by t.startTime";
		return commonDao.findHql(hql, proceInsId,taskDefDey);
	}

	/* (非 Javadoc)
	 * 
	 * 
	 * @param typegroupcode
	 * @param dataGrid
	 * @return
	 * @see org.jeecgframework.workflow.service.ActivitiService#getTypesByTypegroupcode(java.lang.String, org.jeecgframework.core.common.model.json.DataGrid)
	 */
	@Override
	public PageList getTypesByTypegroupcode(String typegroupcode, DataGrid dataGrid) {

		HqlQuery hqlQuery = new HqlQuery("");
		
//		String hql = " from TSType where isDeleted = 0 and isStop = 0 and typegroupid = "
//				+ "(select id from TSTypegroup where typegroupcode ='basesuggestion' and isDeleted = 0) and id "
//				+ "not in (select dictionaryId from TSTypeUnlessRegionEntity where regionId = null) order by orderInLevel";
		String sql = "select t.id,t.typename from t_s_type t where is_deleted = 0 and is_stop = 0 and typegroupid = (select id from t_s_typegroup where typegroupcode ='basesuggestion' "
				+ "and is_deleted = 0) and id"
				+" not in (select dictionary_id from t_s_type_unless_region where REGION_ID = null) order by order_in_level";
		
		hqlQuery.setQueryString(sql);
		hqlQuery.setDataGrid(dataGrid);
		hqlQuery.setCurPage(dataGrid.getPage());
		hqlQuery.setPageSize(dataGrid.getRows());
		
		PageList pageList = commonDao.getPageListBySql(hqlQuery,false);
		
		return pageList;

	}
}
