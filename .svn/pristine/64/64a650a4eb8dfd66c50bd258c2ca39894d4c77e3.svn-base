<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="dbSourceList" title="common.datasource.manage" actionUrl="dynamicDataSourceController.do?datagrid" idField="id" fit="true" sortName="id" sortOrder="desc">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="common.dbname" field="dbName" hidden="false" width="50"></t:dgCol>
	<t:dgCol title="common.datasrouce.key" field="dbKey" width="50"></t:dgCol>
	<t:dgCol title="common.description" field="description" width="60"></t:dgCol>
	<t:dgCol title="common.driverclass" field="driverClass" width="100"></t:dgCol>
	<t:dgCol title="common.datasrouce.url" field="url" width="120"></t:dgCol>
	<t:dgCol title="common.dbuser" field="dbUser" width="50"></t:dgCol>
	<t:dgCol title="common.dbpassword" field="dbPassword" width="100" hidden="true"></t:dgCol>
	<t:dgCol title="common.dbtype" field="dbType" width="50"></t:dgCol>
	<t:dgCol title="common.operation" field="opt" width="50"></t:dgCol>
	<t:dgDelOpt title="common.delete" url="dynamicDataSourceController.do?del&id={id}" />
	<t:dgToolBar title="common.add" icon="icon-add" url="dynamicDataSourceController.do?addorupdate" funname="add"></t:dgToolBar>
	<t:dgToolBar title="common.edit" icon="icon-edit" url="dynamicDataSourceController.do?addorupdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="common.view" icon="icon-search" url="dynamicDataSourceController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>