<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools"></t:base>
 <body>
<t:datagrid name="processproList" title="common.variable.list" actionUrl="processController.do?processproList&processid=${processid }" idField="id">
 <t:dgCol title="Id" field="id" width="20" hidden="true"></t:dgCol>
 <t:dgCol title="common.variable.code" field="processprokey" width="70"></t:dgCol>
 <t:dgCol title="common.variable.name" field="processproname" width="70"></t:dgCol>
 <t:dgCol title="common.variable.node" field="TPProcessnode_processnodename" width="70"></t:dgCol>
 <t:dgCol title="common.operation" field="opt" width="100"></t:dgCol>
 <t:dgDelOpt url="processController.do?delPro&id={id}" title="common.delete"></t:dgDelOpt>
</t:datagrid>
<div id="processproListtb" style="padding:3px; height:25px">
 <div style="float: left;">
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="add('<t:mutiLang langKey="common.variable.add"/>','processController.do?addorupdatePro&processid=${processid }','processproList')"><t:mutiLang langKey="common.variable.add"/></a>
  <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('<t:mutiLang langKey="common.variable.update"/>','processController.do?addorupdatePro&processid=${processid }','processproList')"><t:mutiLang langKey="common.variable.update"/></a>
 </div>
</div>
</body>
