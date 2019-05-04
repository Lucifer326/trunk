<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<title>部门集合</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:datagrid pagination="false" name="departList" title="common.role.select"  actionUrl="departController.do?datagridDepart&regionId=${regionId}" idField="id" checkbox="false" showRefresh="false"  fit="true"  queryMode="group" onLoadSuccess="initCheck">
	<t:dgCol title="common.id" field="id" hidden="true"></t:dgCol>
	<t:dgCol title="部门名称" field="departname" width="50" query="true" ></t:dgCol>
</t:datagrid>
</body>
</html>
<script type="text/javascript">
function initCheck(data){
	
	
	/* var ids = "${id}";
	var idArr = ids.split(",");
	for(var i=0;i<idArr.length;i++){
		if(idArr[i]!=""){
			$("#departList").datagrid("selectRecord",idArr[i]);
		}
	} */
	
	
	var id = "${id}";
	
	
	$("#departList").datagrid("selectRecord",id);
}
</script>