<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div id="main_role_list" class="easyui-layout" fit="true">

	<!--当前位置信息  -->
	<div region="north" class="loaction"
		style="height: 30px !important; border-top: solid 1px #AFAFAF;">
		<p>
			<span class="pl30">${currentLocation}</span>
		</p>
	</div>
	<div data-options="region:'east',title:' ',split:true,collapsed:true"
		style="width: 600px;" split="true">
		<div tools="#tt" class="easyui-panel"
			title='<t:mutiLang langKey="permission.set"/>' style="padding: 10px;"
			fit="true" border="false" id="function-panel"></div>
	</div>
	<div data-options="region:'center'" style="padding: 0px; border: 0px">
		<t:datagrid name="userAgentList" title="common.userAgent.list"
			actionUrl="userAgentController.do?datagrid" idField="id"
			sortName="createDate" sortOrder="desc">
			<t:dgCol title="common.code" field="id" hidden="true"></t:dgCol>
			<t:dgCol title="common.user.name" field="userName"></t:dgCol>
			<t:dgCol title="common.agent.name" field="agentUserName"></t:dgCol>
			<t:dgCol title="common.status" field="status"
				replace="common.enable_1,common.enable_0"></t:dgCol>
			<t:dgCol title="common.begintime" field="startTime"
				formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
			<t:dgCol title="common.endtime" field="endTime"
				formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
			<t:dgCol title="common.createby" field="createBy" hidden="true"></t:dgCol>
			<t:dgCol title="common.createtime" field="createDate"
				formatter="yyyy-MM-dd hh:mm:ss" hidden="true"></t:dgCol>
			<t:dgCol title="common.updatetime" field="updateDate"
				formatter="yyyy-MM-dd" hidden="true"></t:dgCol>
			<t:dgCol title="common.operation" field="opt"></t:dgCol>
			<t:dgDelOpt title="common.cancel"
				url="userAgentController.do?cancel&id={id}" />
			<t:dgToolBar title="common.add.param" langArg="common.userAgent"
				icon="icon-add" url="userAgentController.do?addorupdate"
				funname="add" operationCode="add"></t:dgToolBar>
			<t:dgToolBar title="common.edit.param" langArg="common.userAgent"
				icon="icon-edit" url="userAgentController.do?addorupdate"
				funname="update"></t:dgToolBar>
		</t:datagrid>
	</div>
</div>

