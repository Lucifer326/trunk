<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<t:datagrid fitColumns="false"  checkbox="true" name="myTaskList" queryMode="group" title="common.task.my" actionUrl="taskController.do?taskAllList" idField="id">
 <t:dgCol title="标题" field="bpmBizTitle" width="400"></t:dgCol>
 <t:dgCol title="common.process.id" field="processProcessDefinitionId" width="80" hidden="true"></t:dgCol>
 <t:dgCol title="common.process.name" field="processProcessDefinitionName" width="150" query="true"></t:dgCol>
 <t:dgCol title="process.instance" field="processTaskProcessInstanceId" width="100"></t:dgCol>
 <t:dgCol title="common.process.user" field="userRealName" width="100"></t:dgCol>
 <t:dgCol title="common.process.assignee" field="assigneeName" width="100"></t:dgCol>
 <t:dgCol title="common.begintime" field="processTaskCreateTime" width="130" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
 <t:dgCol title="common.endtime" field="processTaskDueTime" width="130" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
 <t:dgCol title="common.status" field="tSPrjstatusDescription" width="80"></t:dgCol>
 <t:dgCol title="common.process.current" field="processTaskName" width="100"></t:dgCol>
 <t:dgCol hidden="true" title="TASK ID(该字段隐藏)" field="processTaskId"></t:dgCol>
 <t:dgCol hidden="true" title="key" field="processTaskTaskDefinitionKey"></t:dgCol>
 <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
 <%-- <t:dgCol title="common.node.timeout.remind" field="timeoutRemaid"  replace="超时_true,未超时_false"></t:dgCol> --%>
 <t:dgCol title="common.node.timeout.remind"  field="imagePath" queryMode="single"  width="80" image="true" imageSize="10,10" align="center" ></t:dgCol>
 <t:dgCol title="超期天数" field="daynums" width="80"></t:dgCol>
 <t:dgCol title="办结时间" field="jiedays" width="80"></t:dgCol>  
 <t:dgToolBar title="办理"  icon="icon-handle"   funname="goBanLi()"   ></t:dgToolBar>  
 <t:dgToolBar title="转办"  icon="icon-entrust"   funname="weiTou()"   ></t:dgToolBar>  
 <%--  <t:dgCol title="common.operation" field="opt" width="200"></t:dgCol>--%>
<%-- <t:dgConfOpt exp="assigneeName#empty#true" url="activitiController.do?claim&taskId={processTaskId}" message="common.claim.sure" title="common.claim"></t:dgConfOpt>
  <t:dgFunOpt exp="Process_task_assignee#empty#false" funname="openhandleMixTab(Process_task_id,Process_task_name)" title="办理"></t:dgFunOpt> --%>
 <%--<t:dgFunOpt exp="assigneeName#empty#false" funname="openhandleMix(processTaskId,processTaskName)" title="process.handle"></t:dgFunOpt>
 <t:dgFunOpt exp="assigneeName#empty#false" funname="selectEntruster(processTaskId,processTaskName)" title="common.entruster"></t:dgFunOpt>--%>
</t:datagrid>

<SCRIPT type="text/javascript">
function timeoutRemaid(value,row,index){
	if(row.timeoutRemaid=='true'){
		return "<img src=\"plug-in/easyui/themes/icons/bpm-iconfont-yaotip.png\" >"+value;
	}
	return value;
}
//委托
function weiTou()
{
	var tag=true;
	var rowsData = $('#myTaskList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择一条数据');
		tag=false;
		return;
	}
	else if(rowsData.length>1){
		tip('只能选择一条数据');
		tag=false;
		return;
	}
	if(tag){
		selectEntruster(rowsData[0].processTaskId,'转办');
	}	
}
 function reloadTable()
{	
	$('#myTaskList').datagrid('load');
	
	} 

//跳转办理页面
function goBanLi(){
	var tag=true;
	var rowsData = $('#myTaskList').datagrid('getSelections');
	if (!rowsData || rowsData.length==0) {
		tip('请选择一条数据');
		tag=false;
		return;
	}
	else if(rowsData.length>1){
		tip('只能选择一条数据');
		tag=false;
		return;
	}
	if(tag){
		
		claimAndgoTaskTab(rowsData[0].processTaskId,rowsData[0].processTaskName,'1150px','600px');
	}
}
</SCRIPT>