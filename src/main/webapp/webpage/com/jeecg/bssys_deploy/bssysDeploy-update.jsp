<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>系统配置表</title>
<t:base type="jquery,easyui-ss,tools,DatePicker"></t:base>
</head>
<body>
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="bssysDeployController.do?doUpdate" tiptype="1">
		<input id="id" name="id" type="hidden" value="${bssysDeployPage.id }">
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
		<input id="dateExpr" name="dateExpr" type="hidden"
			value="${bssysDeployPage.dateExpr }">

		<input id="code" name="code" type="hidden"
			value="${bssysDeployPage.code }">
		<input id="valuebak" name="valuebak" type="hidden"
			value="${bssysDeployPage.value }">
		<input id="controlType" name="controlType" type="hidden"
			value='${bssysDeployPage.controlType}'>
		<%-- <input id="isBinddis" name="isBinddis" type="hidden"
			value="${bssysDeployPage.isBinddis }"> --%>

		<input name="parent.id" type="hidden" class="inputxt"
			value='${bssysDeployPage.parent.id}'>
		<table class="table-easyui" cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<td style="width: 20%;"><label class="Validform_label">配置名称:</label></td>
					<td><input id="name" name="name" type="text" class="inputxt"
						datatype="*1-100" value='${bssysDeployPage.name}'> <!-- <span class="Validform_checktip"></span> --></td>
				</tr>
				<tr>
					<td><label class="Validform_label">配置编号:</label></td>
					<td><label class="Validform_label">${bssysDeployPage.code}</label></td>
				</tr>
				<tr>
					<td><label class="Validform_label">说明:</label></td>
					<td><textarea id="description" class="inputxt" rows="6"
							name="description" datatype="*0-1000">${bssysDeployPage.description }</textarea>
				</tr>
				<c:if test="${not empty bssysDeployPage.controlType }">
					<tr>
						<td><label class="Validform_label">配置值:</label></td>
						<td><c:choose>
								<c:when test="${bssysDeployPage.controlType==2}">
									<input id="value_int" name="value" type="text" class="inputxt"
										datatype="n1-50" value='${bssysDeployPage.value}'>
								</c:when>
								<c:when test="${bssysDeployPage.controlType==3}">
									<select id="value_select" name="value"
										value="${bssysDeployPage.value}"></select>
								</c:when>
								<c:when test="${bssysDeployPage.controlType==4}">
									<div id="div_date">
										<input id="value_date" name="value" type="text"
											class="easyui-datebox" value='${bssysDeployPage.value}'
											nullmsg="请填写配置日期！">
									</div>
								</c:when>
								<c:otherwise>
									<!-- 1：字符型 -->
									<textarea id="value_txt" name="value" class="inputxt"
										datatype="s1-200" rows="3">${bssysDeployPage.value}</textarea>
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<td><label class="Validform_label">是否绑定区划:</label></td>
						<td><span class="fl">是</span><input
							class="fl radioinput inputxt" id="isBinddis1" name="isBinddis"
							type="radio" style="width: 20px" value="1" datatype="need1"
							nullmsg="请选择是否绑定行政区划！"
							<c:if test="${bssysDeployPage.isBinddis == 1}">checked="cheked"</c:if>>
							<span class="fl">否</span><input class="fl radioinput inputxt"
							id="isBinddis2" name="isBinddis" type="radio" style="width: 20px"
							value="0"
							<c:if test="${bssysDeployPage.isBinddis == 0}">checked="cheked"</c:if>>
							<!-- <span class="Validform_checktip"></span> --></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</t:formvalid>
</body>
<script src="webpage/com/jeecg/bssys_deploy/bssysDeploy.js"></script>
<script>
	$(document).ready(function() {
		ShowControl();
	});
</script>