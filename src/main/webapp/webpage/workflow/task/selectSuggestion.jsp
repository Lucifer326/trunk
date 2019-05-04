<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
<div region="center"  style="padding:0px;border:0px">
<t:datagrid name="typeList"  actionUrl="taskController.do?suggestionDatagrid" idField="id" 
fit="true" 	fitColumns="true" >
	<t:dgCol title="编号" field="id" hidden="true" width="10"></t:dgCol>
	<t:dgCol title="意见内容" field="typename" width="30"></t:dgCol>
</t:datagrid>
</div>
</div>

<script type="text/javascript">
	
</script>
