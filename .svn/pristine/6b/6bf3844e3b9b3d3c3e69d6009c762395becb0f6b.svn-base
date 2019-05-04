<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
<t:datagrid fitColumns="false" name="myAreadyDoTaskList"
	queryMode="group" title="我的已办任务"
	actionUrl="taskController.do?taskMyStartList" idField="id">
	<t:dgCol title="id" field="id" hidden="true" ></t:dgCol>
	<t:dgCol title="process.instance" field="proInslprocInstId" width="100"></t:dgCol>
	<t:dgCol title="标题" field="bpmBizTitle" width="150"></t:dgCol>	
	<t:dgCol title="common.process.user" field="proInslstartUserId"
		width="100"></t:dgCol>
	<t:dgCol title="common.begintime" field="startTime" width="130"
		formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
	<t:dgCol title="common.endtime" field="endTime" width="130"
		formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
	<t:dgCol title="流程状态" field="proInslstatus" width="100"></t:dgCol>
	<t:dgCol title="当前任务" field="currenttask" width="100"></t:dgCol>
	<t:dgCol title="当前办理人" field="currentassignee" width="100"></t:dgCol>
	<t:dgCol title="收到日期" field="currentstarttime" width="100" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>	
	<t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
	<t:dgFunOpt funname="goProcessHisTab(proInslprocInstId,name)"
		title="查看详情"></t:dgFunOpt>
</t:datagrid>
