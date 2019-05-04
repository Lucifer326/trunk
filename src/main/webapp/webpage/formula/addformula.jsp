<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>公式新增</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" layout="div" dialog="true" action="defController.do?saveFormulaName">
	<input type="hidden" name="tablename" id="tablename" value="${tablename}">
	<input type="hidden" name="columnname" id="columnname" value="${columnname}">
	<fieldset class="step">
	<div class="form"><label class="Validform_label"> 公式名称： </label> <input name="formulaname" class="inputxt" value="" datatype="s2-30"> <span class="Validform_checktip"></span></div>
	</fieldset>
</t:formvalid>
</body>
</html>
