<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>系统配置表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="bssysDeployController.do?doAdd" tiptype="1">

		<input id="updateBy" name="updateBy" type="hidden"
			value="${bssysDeployPage.updateBy }">
		<input id="updateName" name="updateName" type="hidden"
			value="${bssysDeployPage.updateName }">
		<input id="updateDate" name="updateDate" type="hidden"
			value="${bssysDeployPage.updateDate }">
		<input id="isdeleted" name="isdeleted" type="hidden"
			value="${bssysDeployPage.isdeleted }">
		<input id="sysOrgCode" name="sysOrgCode" type="hidden"
			value="${bssysDeployPage.sysOrgCode }">

		<input id="valuebak" name="valuebak" type="hidden"
			value="${bssysDeployPage.value }">
		<input id="isBinddisbak" name="isBinddisbak" type="hidden"
			value="${bssysDeployPage.isBinddis }">

		<table class="table-easyui" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td style="width: 20%;"><label class="Validform_label">配置名称:</label></td>
					<td><input id="name" name="name" type="text" class="inputxt"
						datatype="*1-100" value=''></td>
				</tr>
				<tr>
					<td><label class="Validform_label">配置编号:</label></td>
					<td><input id="code" name="code" type="text" class="inputxt"
						value='${maxCode}'></td>
				</tr>
				<tr>
					<td><label class="Validform_label">上级编号:</label></td>
					<td><input name="" type="text" class="inputxt"
						value='${bssysDeployPage.code}' readonly="readonly"> <input
						name="parent.id" type="hidden" class="inputxt"
						value="${bssysDeployPage.id}" readonly="readonly" /></td>

				</tr>
				<tr>
					<td><label class="Validform_label">控件类型:</label></td>
					<td><t:dictSelect field="controlType" id="controlType"
							typeGroupCode="controlType" onchange="chooseControlType();"
							emptyOption="true" hasLabel="false"></t:dictSelect></td>
				</tr>
				<tr id="dd1">
				</tr>
				<tr>
					<td><label class="Validform_label">说明:</label></td>
					<td><textarea id="description" class="inputxt" rows="4"
							name="description" datatype="*0-1000"></textarea></td>
				</tr>
				<tr>
					<td><label class="Validform_label">配置值:</label></td>
					<td id="dd"></td>
				</tr>
				<tr>
					<td><label class="Validform_label">是否绑定区划:</label></td>
					<td><span class="fl">是</span> <input
						class="fl radioinput inputxt" id="isBinddis1" name="isBinddis"
						type="radio" style="width: 20px" value="1" datatype="need1"
						nullmsg="请选择是否绑定行政区划！" checked="checked"> <span class="fl">否</span>
						<input class="fl radioinput inputxt" id="isBinddis2"
						name="isBinddis" type="radio" style="width: 20px" value="0" /></td>
				</tr>
			</tbody>
		</table>
	</t:formvalid>
</body>
<script src="webpage/com/jeecg/bssys_deploy/bssysDeploy.js"></script>
<script>
	function chooseControlType() {
		var ctValue = $("#controlType").val();
		if (ctValue == '') {
			$("#dd1").html('');
			$("#dd").html('');
		} else if (ctValue == 2) {
			$("#dd1").html('');
			$("#dd")
					.html(
							"<input id='value_int' name='value' type='text' class='inputxt' datatype='n1-50'>");
		} else if (ctValue == 3) {
			$("#dd1")
					.html(
							"<td><label class='Validform_label'>数据表达式:</label></td><td><input  id='dateExpr' name='dateExpr' type='text' class='inputxt'  onblur='ShowControl();'></td>");
			$("#dd").html("<select id='value_select' name='value'></select>");
		} else if (ctValue == 4) {
			$("#dd1").html('');
			$("#dd")
					.html(
							"<div id='div_date'><input id='value_date' name='value' type='text' class='easyui-datebox' nullmsg='请填写配置日期！'></div>");
		} else {
			$("#dd1").html('');
			$("#dd")
					.html(
							"<textarea id='value_txt' name='value' class='inputxt' datatype='s1-200' rows='3'></textarea>");
		}
	}
</script>