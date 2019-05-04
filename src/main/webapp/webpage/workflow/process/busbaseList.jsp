<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
<div region="center" style="padding:0px;border:0px">
<t:datagrid name="busbaseList" title="common.buss.list" actionUrl="processController.do?datagridBus&processid=${processid }" idField="id">
 <t:dgCol title="Id" field="id" width="20" hidden="true"></t:dgCol>
 <t:dgCol title="common.buss.name" field="busname" width="70"></t:dgCol>
 <t:dgCol title="common.buss.form.type" field="formType" width="70" dictionary="bpm_form_type"></t:dgCol>
 <t:dgCol title="common.buss.entity.define" field="TSTable_entityName" width="70"></t:dgCol>
 <t:dgCol title="common.buss.entity.online" field="onlineId" width="70"></t:dgCol>
 <t:dgCol title="common.buss.title.expression" field="busTitleExp" width="150"></t:dgCol>
 <t:dgCol title="common.buss.taskpage.style" field="processDealStyle" width="70"></t:dgCol>
 <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
 <t:dgDelOpt url="processController.do?delBus&id={id}" title="common.delete"></t:dgDelOpt>
 <t:dgToolBar title="common.buss.add" icon="icon-add" url="processController.do?addorupdateBus&processid=${processid }" funname="add"></t:dgToolBar>
 <t:dgToolBar title="common.buss.update" icon="icon-edit" url="processController.do?addorupdateBus&processid=${processid }" funname="update"></t:dgToolBar>
</t:datagrid>
</div>
</div>