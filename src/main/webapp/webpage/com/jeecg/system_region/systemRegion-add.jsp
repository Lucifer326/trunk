<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>行政区划信息</title>
<!--jquery,easyui,tools-->
<t:base type="jquery,easyui,tools"></t:base>
<style type="text/css">
.test {
	color: red;
}
</style>
</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" layout="div" dialog="true"
		action="systemRegionController.do?doAdd">

		<input name="isLeaf" type="hidden" value="">
		<input id="plevel" type="hidden" value="${plevel}">
		<fieldset class="step">
			<div class="form">
				<label class="Validform_label"><span style="color: red;">*</span>行政区划名称:
				</label> <input name="regionName" class="inputxt" datatype="s1-20">
				<span class="Validform_checktip">行政区划名称范围1-20个字符</span>
			</div>
			<div class="form">
				<label class="Validform_label">上级行政区划: </label> <input type="hidden"
					name="parent.id" value="${systemRegion.id}"> <input
					id="cc" class="inputxt" name="" value="${systemRegion.regionName}"
					disabled="disabled"> <span class="Validform_checktip">上级行政区划为只读</span>
			</div>
			<div class="form">
				<label class="Validform_label"> 行政区划简称: </label> <input
					name="regionShortName" class="inputxt" datatype="*0-20"
					ignore="ignore">
			</div>
			<div class="form">
				<label class="Validform_label"><span style="color: red;">*</span>行政区划编码:
				</label> <input name="regionCode" class="inputxt" datatype="*1-20">
				<span class="Validform_checktip">行政区划编码1-20个字符</span>
			</div>
			<div class="form">
				<label class="Validform_label">行政区划级别:</label>
				<t:dictSelect field="regionLevel" typeGroupCode="101010" hasLabel="false"></t:dictSelect>
			</div>
			<div class="form">
				<label class="Validform_label">行政区划区号: </label> <input
					name="areaCode" class="inputxt" datatype="*0-20" ignore="ignore">
			</div>
			<div class="form">
				<label class="Validform_label">行政区划邮政编码: </label> <input
					name="regionPostcode" class="inputxt" datatype="/^$|^\d{6}$/"
					errormsg="邮政编码为6为任意数字" ignore="ignore">
			</div>
		</fieldset>
	</t:formvalid>
</body>
</html>
