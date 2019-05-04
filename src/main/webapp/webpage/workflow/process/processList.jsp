<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<SCRIPT type="text/javascript">
	function processcofig(id) {
		addOneTab('<t:mutiLang langKey="common.process.config"/>', 'processController.do?processTabs&processid=' + id,id);
	}
	function addprocess(){
		openwindow('<t:mutiLang langKey="common.process.add"/>','processController.do?processDesigner&typeid=${typeid}','processList',800,500);
	}
</SCRIPT>
<t:datagrid name="processList" title="common.process.config" actionUrl="processController.do?processGrid&typeid=${typeid}" idField="id">
 <t:dgCol title="流程id" field="id" width="100" hidden="true"></t:dgCol>
 <t:dgCol title="common.process.name" query="true" field="processname" width="70"></t:dgCol>
 <t:dgCol title="common.process.key" field="processkey" width="70"></t:dgCol>
 <t:dgCol title="common.process.status" field="TSType.typename" width="70"></t:dgCol>
 <t:dgCol title="common.bpm_status" field="processstate" replace="common.bpmstatus.publish_1,common.bpmstatus.unpublish_0" width="70"></t:dgCol>
 <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
 <t:dgFunOpt funname="processcofig(id)" title="common.process.config"></t:dgFunOpt>
 <t:dgConfOpt url="processController.do?deployProcess&processid={id}" message="common.deplay.sure" title="common.publish"></t:dgConfOpt>
 <t:dgDelOpt  exp="processstate#eq#0" url="processController.do?delprocess&id={id}" title="common.delete"></t:dgDelOpt>
</t:datagrid>
<div id="processListtb" style="padding: 3px; height: 25px">
 <div style="float: left;"><!-- '<t:mutiLang langKey="common.process.add"/>','processController.do?processDesigner','processList',800,500)" -->
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addprocess()"><t:mutiLang langKey="common.process.add"/></a>
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="editfs('<t:mutiLang langKey="common.process.update"/>','processController.do?processDesigner')"><t:mutiLang langKey="common.process.update"/></a>
 </div>
</div>
